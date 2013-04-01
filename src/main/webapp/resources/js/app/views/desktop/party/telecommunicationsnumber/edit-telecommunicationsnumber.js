define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/contactmechanism/contactmechanism',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/telecommunicationsnumber/edit-telecommunicationsnumber.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanisms, contactMechanismListSubViewTemplate, TelecommunicationsNumberEditTemplate) {
	
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var TelecommunicationsNumberEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(telecommunicationsnumber)
                    {
                        utilities.applyTemplate($(self.el), TelecommunicationsNumberEditTemplate,  
                            {model:this.model, telecommunicationsnumber:telecommunicationsnumber, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), TelecommunicationsNumberEditTemplate,  
                    {model:this.model, telecommunicationsnumber:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-telecommunicationsnumber-form':'editTelecommunicationsNumber'
            
        },
        editTelecommunicationsNumber: function(event)
        {
            event.preventDefault();
            var telecommunicationsnumber = $(event.currentTarget).serializeObject();
            this.model.save(telecommunicationsnumber, { 
                'success': function ()
                {
                    utilities.navigate('list-telecommunicationsnumber');
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
            return false;
        },
        renderSubViews:function()
        {
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            if (this.model.attributes.id)
            {
		    	this.contactMechanismId = this.model.attributes.contactMechanism
            }
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
        }
    });

    return TelecommunicationsNumberEditView;
});

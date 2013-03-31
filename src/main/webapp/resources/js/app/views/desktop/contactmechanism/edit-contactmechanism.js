define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/contactmechanismtype/contactmechanismtype',
    'text!../../../../../templates/desktop/contactmechanismtype/contactmechanismtype-list-subview.html',
    'text!../../../../../templates/desktop/contactmechanism/edit-contactmechanism.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanismTypes, contactMechanismTypeListSubViewTemplate, ContactMechanismEditTemplate) {
	
    var ContactMechanismTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismTypeSelectContainerDiv'), contactMechanismTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismTypesFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismTypeSelectContainerDiv'), contactMechanismTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ContactMechanismEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(contactmechanism)
                    {
                        utilities.applyTemplate($(self.el), ContactMechanismEditTemplate,  
                            {model:this.model, contactmechanism:contactmechanism, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ContactMechanismEditTemplate,  
                    {model:this.model, contactmechanism:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-contactmechanism-form':'editContactMechanism'
            
        },
        editContactMechanism: function(event)
        {
            event.preventDefault();
            var contactmechanism = $(event.currentTarget).serializeObject();
            this.model.save(contactmechanism, { 
                'success': function ()
                {
                    utilities.navigate('list-contactmechanism');
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
		    	this.contactMechanismTypeId = this.model.attributes.contactMechanismType
            }
            // ContactMechanismTypes
            var contactMechanismTypes = new ContactMechanismTypes();
            contactMechanismTypeListSubView = new ContactMechanismTypeListSubView({model:contactMechanismTypes, el:$('#contactMechanismTypeSelectContainerDiv'), selectedOption:this.contactMechanismTypeId});
            contactMechanismTypeListSubView.render();
        }
    });

    return ContactMechanismEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/contactmechanism/contactmechanism',
    'app/collections/party/contactmechanism/contactmechanism',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/contactmechanismlink/edit-contactmechanismlink.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanisms, ContactMechanisms, contactMechanismListSubViewTemplate, contactMechanismListSubViewTemplate, ContactMechanismLinkEditTemplate) {
	
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismByToId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismByToId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismByFromId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismByFromId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ContactMechanismLinkEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(contactmechanismlink)
                    {
                        utilities.applyTemplate($(self.el), ContactMechanismLinkEditTemplate,  
                            {model:this.model, contactmechanismlink:contactmechanismlink, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ContactMechanismLinkEditTemplate,  
                    {model:this.model, contactmechanismlink:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-contactmechanismlink-form':'editContactMechanismLink'
            
        },
        editContactMechanismLink: function(event)
        {
            event.preventDefault();
            var contactmechanismlink = $(event.currentTarget).serializeObject();
            this.model.save(contactmechanismlink, { 
                'success': function ()
                {
                    utilities.navigate('list-contactmechanismlink');
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
		    	this.contactMechanismId = this.model.attributes.contactMechanism
            }
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
        }
    });

    return ContactMechanismLinkEditView;
});

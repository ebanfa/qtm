define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/contactmechanismtype/contactmechanismtype',
    'text!../../../../../../templates/desktop/party/contactmechanismtype/contactmechanismtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/contactmechanism/edit-contactmechanism.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ContactMechanismTypes, contactMechanismTypeListSubViewTemplate, ContactMechanismEditTemplate) {
	
    var ContactMechanismTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismTypeSelectContainerDiv'), contactMechanismTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var contactMechanismTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismTypesFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismTypeSelectContainerDiv'), contactMechanismTypeListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"contactMechanismType", 
            	fieldName:entities_strings.contactmechanismtype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ContactMechanismEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ContactMechanismEditTemplate;
        },
        events:
        {
            'submit #edit-contactmechanism-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-contactmechanism');
        },
        renderSubViews:function()
        {
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

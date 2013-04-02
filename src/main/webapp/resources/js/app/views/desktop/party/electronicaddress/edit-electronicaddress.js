define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/contactmechanism/contactmechanism',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/electronicaddress/edit-electronicaddress.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ContactMechanisms, contactMechanismListSubViewTemplate, ElectronicAddressEditTemplate) {
	
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"contactMechanism", 
            	fieldName:entities_strings.contactmechanism, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ElectronicAddressEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ElectronicAddressEditTemplate;
        },
        events:
        {
            'submit #edit-electronicaddress-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-electronicaddress');
        },
        renderSubViews:function()
        {
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

    return ElectronicAddressEditView;
});

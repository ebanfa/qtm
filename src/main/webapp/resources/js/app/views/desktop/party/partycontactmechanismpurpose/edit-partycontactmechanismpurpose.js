define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/contactmechanismpurposetype/contactmechanismpurposetype',
    'app/collections/party/contactmechanism/contactmechanism',
    'text!../../../../../../templates/desktop/party/contactmechanismpurposetype/contactmechanismpurposetype-list-subview.html',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/partycontactmechanismpurpose/edit-partycontactmechanismpurpose.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ContactMechanismPurposeTypes, ContactMechanisms, contactMechanismPurposeTypeListSubViewTemplate, contactMechanismListSubViewTemplate, PartyContactMechanismPurposeEditTemplate) {
	
    var ContactMechanismPurposeTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismPurposeTypeSelectContainerDiv'), contactMechanismPurposeTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var contactMechanismPurposeTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismPurposeTypesFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismPurposeTypeSelectContainerDiv'), contactMechanismPurposeTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"contactMechanismPurposeType", 
            	fieldName:entities_strings.contactmechanismpurposetype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
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
    
	
    var PartyContactMechanismPurposeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyContactMechanismPurposeEditTemplate;
        },
        events:
        {
            'submit #edit-partycontactmechanismpurpose-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partycontactmechanismpurpose');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.contactMechanismPurposeTypeId = this.model.attributes.contactMechanismPurposeType
		    	this.contactMechanismId = this.model.attributes.contactMechanism
            }
            // ContactMechanismPurposeTypes
            var contactMechanismPurposeTypes = new ContactMechanismPurposeTypes();
            contactMechanismPurposeTypeListSubView = new ContactMechanismPurposeTypeListSubView({model:contactMechanismPurposeTypes, el:$('#contactMechanismPurposeTypeSelectContainerDiv'), selectedOption:this.contactMechanismPurposeTypeId});
            contactMechanismPurposeTypeListSubView.render();
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
        }
    });

    return PartyContactMechanismPurposeEditView;
});

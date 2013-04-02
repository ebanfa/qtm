define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/messaging/communicationeventtype/communicationeventtype',
    'app/collections/messaging/communicationeventpurpose/communicationeventpurpose',
    'app/collections/party/contactmechanismtype/contactmechanismtype',
    'app/collections/party/partyrelationship/partyrelationship',
    'app/collections/messaging/communicationeventstatustype/communicationeventstatustype',
    'text!../../../../../../templates/desktop/messaging/communicationeventtype/communicationeventtype-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationeventpurpose/communicationeventpurpose-list-subview.html',
    'text!../../../../../../templates/desktop/party/contactmechanismtype/contactmechanismtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrelationship/partyrelationship-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationeventstatustype/communicationeventstatustype-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationevent/edit-communicationevent.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CommunicationEventTypes, CommunicationEventPurposes, ContactMechanismTypes, PartyRelationships, CommunicationEventStatusTypes, communicationEventTypeListSubViewTemplate, communicationEventPurposeListSubViewTemplate, contactMechanismTypeListSubViewTemplate, partyRelationshipListSubViewTemplate, communicationEventStatusTypeListSubViewTemplate, CommunicationEventEditTemplate) {
	
    var CommunicationEventTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventTypeSelectContainerDiv'), communicationEventTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var communicationEventTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventTypesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventTypeSelectContainerDiv'), communicationEventTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"communicationEventType", 
            	fieldName:entities_strings.communicationeventtype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var CommunicationEventPurposeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventPurposeSelectContainerDiv'), communicationEventPurposeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var communicationEventPurposesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventPurposesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventPurposeSelectContainerDiv'), communicationEventPurposeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"communicationEventPurpose", 
            	fieldName:entities_strings.communicationeventpurpose, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
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
    
    var PartyRelationshipListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRelationshipSelectContainerDiv'), partyRelationshipListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyRelationshipsFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRelationshipsFetch.done(function (){
                utilities.applyTemplate($('#partyRelationshipSelectContainerDiv'), partyRelationshipListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"partyRelationship", 
            	fieldName:entities_strings.partyrelationship, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var CommunicationEventStatusTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventStatusTypeSelectContainerDiv'), communicationEventStatusTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var communicationEventStatusTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventStatusTypesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventStatusTypeSelectContainerDiv'), communicationEventStatusTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"communicationEventStatusType", 
            	fieldName:entities_strings.communicationeventstatustype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var CommunicationEventEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CommunicationEventEditTemplate;
        },
        events:
        {
            'submit #edit-communicationevent-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-communicationevent');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.communicationEventTypeId = this.model.attributes.communicationEventType
		    	this.communicationEventPurposeId = this.model.attributes.communicationEventPurpose
		    	this.contactMechanismTypeId = this.model.attributes.contactMechanismType
		    	this.partyRelationshipId = this.model.attributes.partyRelationship
		    	this.communicationEventStatusTypeId = this.model.attributes.communicationEventStatusType
            }
            // CommunicationEventTypes
            var communicationEventTypes = new CommunicationEventTypes();
            communicationEventTypeListSubView = new CommunicationEventTypeListSubView({model:communicationEventTypes, el:$('#communicationEventTypeSelectContainerDiv'), selectedOption:this.communicationEventTypeId});
            communicationEventTypeListSubView.render();
            // CommunicationEventPurposes
            var communicationEventPurposes = new CommunicationEventPurposes();
            communicationEventPurposeListSubView = new CommunicationEventPurposeListSubView({model:communicationEventPurposes, el:$('#communicationEventPurposeSelectContainerDiv'), selectedOption:this.communicationEventPurposeId});
            communicationEventPurposeListSubView.render();
            // ContactMechanismTypes
            var contactMechanismTypes = new ContactMechanismTypes();
            contactMechanismTypeListSubView = new ContactMechanismTypeListSubView({model:contactMechanismTypes, el:$('#contactMechanismTypeSelectContainerDiv'), selectedOption:this.contactMechanismTypeId});
            contactMechanismTypeListSubView.render();
            // PartyRelationships
            var partyRelationships = new PartyRelationships();
            partyRelationshipListSubView = new PartyRelationshipListSubView({model:partyRelationships, el:$('#partyRelationshipSelectContainerDiv'), selectedOption:this.partyRelationshipId});
            partyRelationshipListSubView.render();
            // CommunicationEventStatusTypes
            var communicationEventStatusTypes = new CommunicationEventStatusTypes();
            communicationEventStatusTypeListSubView = new CommunicationEventStatusTypeListSubView({model:communicationEventStatusTypes, el:$('#communicationEventStatusTypeSelectContainerDiv'), selectedOption:this.communicationEventStatusTypeId});
            communicationEventStatusTypeListSubView.render();
        }
    });

    return CommunicationEventEditView;
});

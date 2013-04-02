define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/caserole/caserole',
    'app/collections/messaging/communicationevent/communicationevent',
    'app/collections/party/casestatustype/casestatustype',
    'text!../../../../../../templates/desktop/party/caserole/caserole-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationevent/communicationevent-list-subview.html',
    'text!../../../../../../templates/desktop/party/casestatustype/casestatustype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partycase/edit-partycase.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CaseRoles, CommunicationEvents, CaseStatusTypes, caseRoleListSubViewTemplate, communicationEventListSubViewTemplate, caseStatusTypeListSubViewTemplate, PartyCaseEditTemplate) {
	
    var CaseRoleListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#caseRoleSelectContainerDiv'), caseRoleListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var caseRolesFetch = this.model.fetch();
            // Re render the template when the data is available    
            caseRolesFetch.done(function (){
                utilities.applyTemplate($('#caseRoleSelectContainerDiv'), caseRoleListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"caseRole", 
            	fieldName:entities_strings.caserole, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var CommunicationEventListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventSelectContainerDiv'), communicationEventListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var communicationEventsFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventsFetch.done(function (){
                utilities.applyTemplate($('#communicationEventSelectContainerDiv'), communicationEventListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"communicationEvent", 
            	fieldName:entities_strings.communicationevent, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var CaseStatusTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#caseStatusTypeSelectContainerDiv'), caseStatusTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var caseStatusTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            caseStatusTypesFetch.done(function (){
                utilities.applyTemplate($('#caseStatusTypeSelectContainerDiv'), caseStatusTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"caseStatusType", 
            	fieldName:entities_strings.casestatustype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var PartyCaseEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyCaseEditTemplate;
        },
        events:
        {
            'submit #edit-partycase-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partycase');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.caseRoleId = this.model.attributes.caseRole
		    	this.communicationEventId = this.model.attributes.communicationEvent
		    	this.caseStatusTypeId = this.model.attributes.caseStatusType
            }
            // CaseRoles
            var caseRoles = new CaseRoles();
            caseRoleListSubView = new CaseRoleListSubView({model:caseRoles, el:$('#caseRoleSelectContainerDiv'), selectedOption:this.caseRoleId});
            caseRoleListSubView.render();
            // CommunicationEvents
            var communicationEvents = new CommunicationEvents();
            communicationEventListSubView = new CommunicationEventListSubView({model:communicationEvents, el:$('#communicationEventSelectContainerDiv'), selectedOption:this.communicationEventId});
            communicationEventListSubView.render();
            // CaseStatusTypes
            var caseStatusTypes = new CaseStatusTypes();
            caseStatusTypeListSubView = new CaseStatusTypeListSubView({model:caseStatusTypes, el:$('#caseStatusTypeSelectContainerDiv'), selectedOption:this.caseStatusTypeId});
            caseStatusTypeListSubView.render();
        }
    });

    return PartyCaseEditView;
});

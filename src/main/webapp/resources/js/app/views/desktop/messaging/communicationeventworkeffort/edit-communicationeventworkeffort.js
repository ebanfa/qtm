define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/workeffort/workeffort/workeffort',
    'app/collections/messaging/communicationevent/communicationevent',
    'text!../../../../../../templates/desktop/workeffort/workeffort/workeffort-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationevent/communicationevent-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationeventworkeffort/edit-communicationeventworkeffort.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, WorkEfforts, CommunicationEvents, workEffortListSubViewTemplate, communicationEventListSubViewTemplate, CommunicationEventWorkEffortEditTemplate) {
	
    var WorkEffortListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#workEffortSelectContainerDiv'), workEffortListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var workEffortsFetch = this.model.fetch();
            // Re render the template when the data is available    
            workEffortsFetch.done(function (){
                utilities.applyTemplate($('#workEffortSelectContainerDiv'), workEffortListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"workEffort", 
            	fieldName:entities_strings.workeffort, 
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
    
	
    var CommunicationEventWorkEffortEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CommunicationEventWorkEffortEditTemplate;
        },
        events:
        {
            'submit #edit-communicationeventworkeffort-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-communicationeventworkeffort');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.workEffortId = this.model.attributes.workEffort
		    	this.communicationEventId = this.model.attributes.communicationEvent
            }
            // WorkEfforts
            var workEfforts = new WorkEfforts();
            workEffortListSubView = new WorkEffortListSubView({model:workEfforts, el:$('#workEffortSelectContainerDiv'), selectedOption:this.workEffortId});
            workEffortListSubView.render();
            // CommunicationEvents
            var communicationEvents = new CommunicationEvents();
            communicationEventListSubView = new CommunicationEventListSubView({model:communicationEvents, el:$('#communicationEventSelectContainerDiv'), selectedOption:this.communicationEventId});
            communicationEventListSubView.render();
        }
    });

    return CommunicationEventWorkEffortEditView;
});

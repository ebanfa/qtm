define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/party/party',
    'app/collections/advice/advicestatus/advicestatus',
    'app/collections/messaging/communicationevent/communicationevent',
    'app/collections/advice/advicetype/advicetype',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/advice/advicestatus/advicestatus-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationevent/communicationevent-list-subview.html',
    'text!../../../../../../templates/desktop/advice/advicetype/advicetype-list-subview.html',
    'text!../../../../../../templates/desktop/advice/advice/edit-advice.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Partys, AdviceStatuss, CommunicationEvents, AdviceTypes, partyListSubViewTemplate, adviceStatusListSubViewTemplate, communicationEventListSubViewTemplate, adviceTypeListSubViewTemplate, AdviceEditTemplate) {
	
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"party", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var AdviceStatusListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#adviceStatusSelectContainerDiv'), adviceStatusListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var adviceStatussFetch = this.model.fetch();
            // Re render the template when the data is available    
            adviceStatussFetch.done(function (){
                utilities.applyTemplate($('#adviceStatusSelectContainerDiv'), adviceStatusListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"adviceStatus", 
            	fieldName:entities_strings.advicestatus, 
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
    
    var AdviceTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#adviceTypeSelectContainerDiv'), adviceTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var adviceTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            adviceTypesFetch.done(function (){
                utilities.applyTemplate($('#adviceTypeSelectContainerDiv'), adviceTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"adviceType", 
            	fieldName:entities_strings.advicetype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var AdviceEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = AdviceEditTemplate;
        },
        events:
        {
            'submit #edit-advice-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-advice');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyId = this.model.attributes.party
		    	this.adviceStatusId = this.model.attributes.adviceStatus
		    	this.communicationEventId = this.model.attributes.communicationEvent
		    	this.adviceTypeId = this.model.attributes.adviceType
            }
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // AdviceStatuss
            var adviceStatuss = new AdviceStatuss();
            adviceStatusListSubView = new AdviceStatusListSubView({model:adviceStatuss, el:$('#adviceStatusSelectContainerDiv'), selectedOption:this.adviceStatusId});
            adviceStatusListSubView.render();
            // CommunicationEvents
            var communicationEvents = new CommunicationEvents();
            communicationEventListSubView = new CommunicationEventListSubView({model:communicationEvents, el:$('#communicationEventSelectContainerDiv'), selectedOption:this.communicationEventId});
            communicationEventListSubView.render();
            // AdviceTypes
            var adviceTypes = new AdviceTypes();
            adviceTypeListSubView = new AdviceTypeListSubView({model:adviceTypes, el:$('#adviceTypeSelectContainerDiv'), selectedOption:this.adviceTypeId});
            adviceTypeListSubView.render();
        }
    });

    return AdviceEditView;
});

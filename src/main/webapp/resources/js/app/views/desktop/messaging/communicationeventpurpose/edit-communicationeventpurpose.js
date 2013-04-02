define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/messaging/communicationeventpurposetype/communicationeventpurposetype',
    'text!../../../../../../templates/desktop/messaging/communicationeventpurposetype/communicationeventpurposetype-list-subview.html',
    'text!../../../../../../templates/desktop/messaging/communicationeventpurpose/edit-communicationeventpurpose.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CommunicationEventPurposeTypes, communicationEventPurposeTypeListSubViewTemplate, CommunicationEventPurposeEditTemplate) {
	
    var CommunicationEventPurposeTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventPurposeTypeSelectContainerDiv'), communicationEventPurposeTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var communicationEventPurposeTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventPurposeTypesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventPurposeTypeSelectContainerDiv'), communicationEventPurposeTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"communicationEventPurposeType", 
            	fieldName:entities_strings.communicationeventpurposetype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var CommunicationEventPurposeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CommunicationEventPurposeEditTemplate;
        },
        events:
        {
            'submit #edit-communicationeventpurpose-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-communicationeventpurpose');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.communicationEventPurposeTypeId = this.model.attributes.communicationEventPurposeType
            }
            // CommunicationEventPurposeTypes
            var communicationEventPurposeTypes = new CommunicationEventPurposeTypes();
            communicationEventPurposeTypeListSubView = new CommunicationEventPurposeTypeListSubView({model:communicationEventPurposeTypes, el:$('#communicationEventPurposeTypeSelectContainerDiv'), selectedOption:this.communicationEventPurposeTypeId});
            communicationEventPurposeTypeListSubView.render();
        }
    });

    return CommunicationEventPurposeEditView;
});

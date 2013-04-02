define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/messaging/communicationeventpurposetype/edit-communicationeventpurposetype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CommunicationEventPurposeTypeEditTemplate) {
	
	
    var CommunicationEventPurposeTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CommunicationEventPurposeTypeEditTemplate;
        },
        events:
        {
            'submit #edit-communicationeventpurposetype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-communicationeventpurposetype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CommunicationEventPurposeTypeEditView;
});

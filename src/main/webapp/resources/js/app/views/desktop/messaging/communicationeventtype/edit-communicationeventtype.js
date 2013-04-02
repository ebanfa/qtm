define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/messaging/communicationeventtype/edit-communicationeventtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CommunicationEventTypeEditTemplate) {
	
	
    var CommunicationEventTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CommunicationEventTypeEditTemplate;
        },
        events:
        {
            'submit #edit-communicationeventtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-communicationeventtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CommunicationEventTypeEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/messaging/communicationeventstatustype/edit-communicationeventstatustype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CommunicationEventStatusTypeEditTemplate) {
	
	
    var CommunicationEventStatusTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CommunicationEventStatusTypeEditTemplate;
        },
        events:
        {
            'submit #edit-communicationeventstatustype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-communicationeventstatustype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CommunicationEventStatusTypeEditView;
});

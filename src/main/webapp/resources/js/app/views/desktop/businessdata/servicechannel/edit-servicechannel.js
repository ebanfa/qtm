define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/businessdata/servicechannel/edit-servicechannel.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceChannelEditTemplate) {
	
	
    var ServiceChannelEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceChannelEditTemplate;
        },
        events:
        {
            'submit #edit-servicechannel-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicechannel');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ServiceChannelEditView;
});

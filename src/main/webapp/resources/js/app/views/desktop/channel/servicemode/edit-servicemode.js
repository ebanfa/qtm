define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/channel/servicemode/edit-servicemode.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceModeEditTemplate) {
	
	
    var ServiceModeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceModeEditTemplate;
        },
        events:
        {
            'submit #edit-servicemode-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicemode');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ServiceModeEditView;
});

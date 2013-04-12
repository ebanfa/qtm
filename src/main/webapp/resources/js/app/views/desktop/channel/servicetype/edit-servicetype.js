define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/channel/servicetype/edit-servicetype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceTypeEditTemplate) {
	
	
    var ServiceTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceTypeEditTemplate;
        },
        events:
        {
            'submit #edit-servicetype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicetype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ServiceTypeEditView;
});

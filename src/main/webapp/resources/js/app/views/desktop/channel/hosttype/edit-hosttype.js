define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/channel/hosttype/edit-hosttype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, HostTypeEditTemplate) {
	
	
    var HostTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = HostTypeEditTemplate;
        },
        events:
        {
            'submit #edit-hosttype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-hosttype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return HostTypeEditView;
});

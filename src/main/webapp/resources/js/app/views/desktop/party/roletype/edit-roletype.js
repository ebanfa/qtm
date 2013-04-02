define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/party/roletype/edit-roletype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, RoleTypeEditTemplate) {
	
	
    var RoleTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = RoleTypeEditTemplate;
        },
        events:
        {
            'submit #edit-roletype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-roletype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return RoleTypeEditView;
});

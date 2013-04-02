define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/party/caseroletype/edit-caseroletype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CaseRoleTypeEditTemplate) {
	
	
    var CaseRoleTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CaseRoleTypeEditTemplate;
        },
        events:
        {
            'submit #edit-caseroletype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-caseroletype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CaseRoleTypeEditView;
});

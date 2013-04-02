define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/party/casestatustype/edit-casestatustype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CaseStatusTypeEditTemplate) {
	
	
    var CaseStatusTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CaseStatusTypeEditTemplate;
        },
        events:
        {
            'submit #edit-casestatustype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-casestatustype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CaseStatusTypeEditView;
});

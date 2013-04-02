define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/workeffort/workefforttype/edit-workefforttype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, WorkEffortTypeEditTemplate) {
	
	
    var WorkEffortTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = WorkEffortTypeEditTemplate;
        },
        events:
        {
            'submit #edit-workefforttype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-workefforttype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return WorkEffortTypeEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/businessdata/statustype/edit-statustype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, StatusTypeEditTemplate) {
	
	
    var StatusTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = StatusTypeEditTemplate;
        },
        events:
        {
            'submit #edit-statustype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-statustype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return StatusTypeEditView;
});

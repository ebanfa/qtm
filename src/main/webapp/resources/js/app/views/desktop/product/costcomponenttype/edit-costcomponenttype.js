define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/product/costcomponenttype/edit-costcomponenttype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CostComponentTypeEditTemplate) {
	
	
    var CostComponentTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CostComponentTypeEditTemplate;
        },
        events:
        {
            'submit #edit-costcomponenttype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-costcomponenttype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CostComponentTypeEditView;
});

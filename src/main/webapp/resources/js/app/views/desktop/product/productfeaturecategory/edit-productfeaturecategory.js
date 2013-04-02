define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/product/productfeaturecategory/edit-productfeaturecategory.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductFeatureCategoryEditTemplate) {
	
	
    var ProductFeatureCategoryEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductFeatureCategoryEditTemplate;
        },
        events:
        {
            'submit #edit-productfeaturecategory-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productfeaturecategory');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductFeatureCategoryEditView;
});

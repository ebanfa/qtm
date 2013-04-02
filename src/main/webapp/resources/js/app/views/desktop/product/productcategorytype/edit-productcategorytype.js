define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/product/productcategorytype/edit-productcategorytype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductCategoryTypeEditTemplate) {
	
	
    var ProductCategoryTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductCategoryTypeEditTemplate;
        },
        events:
        {
            'submit #edit-productcategorytype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productcategorytype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductCategoryTypeEditView;
});

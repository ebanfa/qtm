define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/product/productfeaturetype/edit-productfeaturetype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductFeatureTypeEditTemplate) {
	
	
    var ProductFeatureTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductFeatureTypeEditTemplate;
        },
        events:
        {
            'submit #edit-productfeaturetype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productfeaturetype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductFeatureTypeEditView;
});

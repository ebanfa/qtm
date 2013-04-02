define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/product/producttype/edit-producttype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductTypeEditTemplate) {
	
	
    var ProductTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductTypeEditTemplate;
        },
        events:
        {
            'submit #edit-producttype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-producttype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductTypeEditView;
});

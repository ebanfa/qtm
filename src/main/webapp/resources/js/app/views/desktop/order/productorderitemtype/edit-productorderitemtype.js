define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/order/productorderitemtype/edit-productorderitemtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductOrderItemTypeEditTemplate) {
	
	
    var ProductOrderItemTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductOrderItemTypeEditTemplate;
        },
        events:
        {
            'submit #edit-productorderitemtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productorderitemtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductOrderItemTypeEditView;
});

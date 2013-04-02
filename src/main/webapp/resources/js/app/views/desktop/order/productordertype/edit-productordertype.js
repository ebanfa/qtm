define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/order/productordertype/edit-productordertype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductOrderTypeEditTemplate) {
	
	
    var ProductOrderTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductOrderTypeEditTemplate;
        },
        events:
        {
            'submit #edit-productordertype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productordertype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductOrderTypeEditView;
});

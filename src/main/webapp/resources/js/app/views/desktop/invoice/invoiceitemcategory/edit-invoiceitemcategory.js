define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/invoice/invoiceitemcategory/edit-invoiceitemcategory.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceItemCategoryEditTemplate) {
	
	
    var InvoiceItemCategoryEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceItemCategoryEditTemplate;
        },
        events:
        {
            'submit #edit-invoiceitemcategory-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoiceitemcategory');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return InvoiceItemCategoryEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/invoice/invoiceitemtype/edit-invoiceitemtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceItemTypeEditTemplate) {
	
	
    var InvoiceItemTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceItemTypeEditTemplate;
        },
        events:
        {
            'submit #edit-invoiceitemtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoiceitemtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return InvoiceItemTypeEditView;
});

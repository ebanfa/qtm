define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/invoice/invoicetype/edit-invoicetype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceTypeEditTemplate) {
	
	
    var InvoiceTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceTypeEditTemplate;
        },
        events:
        {
            'submit #edit-invoicetype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoicetype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return InvoiceTypeEditView;
});

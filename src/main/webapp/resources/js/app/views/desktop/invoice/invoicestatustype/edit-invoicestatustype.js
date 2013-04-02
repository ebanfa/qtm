define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/invoice/invoicestatustype/edit-invoicestatustype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceStatusTypeEditTemplate) {
	
	
    var InvoiceStatusTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceStatusTypeEditTemplate;
        },
        events:
        {
            'submit #edit-invoicestatustype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoicestatustype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return InvoiceStatusTypeEditView;
});

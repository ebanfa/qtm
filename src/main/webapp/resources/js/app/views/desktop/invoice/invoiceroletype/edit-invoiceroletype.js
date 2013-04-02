define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/invoice/invoiceroletype/edit-invoiceroletype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceRoleTypeEditTemplate) {
	
	
    var InvoiceRoleTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceRoleTypeEditTemplate;
        },
        events:
        {
            'submit #edit-invoiceroletype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoiceroletype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return InvoiceRoleTypeEditView;
});

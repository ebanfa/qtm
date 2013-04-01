define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/invoiceitemtype/list-invoiceitemtype.html'
], function (
    utilities,
    entities_strings,
    invoiceItemTypesTemplate) {

    var InvoiceItemTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceItemTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoiceitemtype-search':'handleInvoiceItemTypeSearch',
            'click #show-invoiceitemtype-search-dialog':'showInvoiceItemTypeSearchDialog',
            'click #hide-invoiceitemtype-dialog':'hideInvoiceItemTypeSearchDialog'
            
        },
        showInvoiceItemTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceitemtype-search-dialog').modal('show');
            
        },
        handleInvoiceItemTypeSearch: function(event)
        {
            event.preventDefault();
            $('#invoiceitemtype-search-dialog').modal('hide');
            var invoiceItemTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceItemTypeCode} });
            
        },
        hideInvoiceItemTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceitemtype-search-dialog').modal('hide');
            
        }
    });

    return InvoiceItemTypesView;
});
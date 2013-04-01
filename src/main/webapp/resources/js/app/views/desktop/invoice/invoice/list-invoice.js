define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/invoice/list-invoice.html'
], function (
    utilities,
    entities_strings,
    invoicesTemplate) {

    var InvoicesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoicesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoice-search':'handleInvoiceSearch',
            'click #show-invoice-search-dialog':'showInvoiceSearchDialog',
            'click #hide-invoice-dialog':'hideInvoiceSearchDialog'
            
        },
        showInvoiceSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoice-search-dialog').modal('show');
            
        },
        handleInvoiceSearch: function(event)
        {
            event.preventDefault();
            $('#invoice-search-dialog').modal('hide');
            var invoiceCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceCode} });
            
        },
        hideInvoiceSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoice-search-dialog').modal('hide');
            
        }
    });

    return InvoicesView;
});
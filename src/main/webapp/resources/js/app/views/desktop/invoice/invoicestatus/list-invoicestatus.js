define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/invoicestatus/list-invoicestatus.html'
], function (
    utilities,
    entities_strings,
    invoiceStatussTemplate) {

    var InvoiceStatussView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceStatussTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoicestatus-search':'handleInvoiceStatusSearch',
            'click #show-invoicestatus-search-dialog':'showInvoiceStatusSearchDialog',
            'click #hide-invoicestatus-dialog':'hideInvoiceStatusSearchDialog'
            
        },
        showInvoiceStatusSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicestatus-search-dialog').modal('show');
            
        },
        handleInvoiceStatusSearch: function(event)
        {
            event.preventDefault();
            $('#invoicestatus-search-dialog').modal('hide');
            var invoiceStatusCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceStatusCode} });
            
        },
        hideInvoiceStatusSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicestatus-search-dialog').modal('hide');
            
        }
    });

    return InvoiceStatussView;
});
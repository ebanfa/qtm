define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/invoicetype/list-invoicetype.html'
], function (
    utilities,
    entities_strings,
    invoiceTypesTemplate) {

    var InvoiceTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoicetype-search':'handleInvoiceTypeSearch',
            'click #show-invoicetype-search-dialog':'showInvoiceTypeSearchDialog',
            'click #hide-invoicetype-dialog':'hideInvoiceTypeSearchDialog'
            
        },
        showInvoiceTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicetype-search-dialog').modal('show');
            
        },
        handleInvoiceTypeSearch: function(event)
        {
            event.preventDefault();
            $('#invoicetype-search-dialog').modal('hide');
            var invoiceTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceTypeCode} });
            
        },
        hideInvoiceTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicetype-search-dialog').modal('hide');
            
        }
    });

    return InvoiceTypesView;
});
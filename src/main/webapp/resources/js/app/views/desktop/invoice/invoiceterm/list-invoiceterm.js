define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/invoiceterm/list-invoiceterm.html'
], function (
    utilities,
    entities_strings,
    invoiceTermsTemplate) {

    var InvoiceTermsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceTermsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoiceterm-search':'handleInvoiceTermSearch',
            'click #show-invoiceterm-search-dialog':'showInvoiceTermSearchDialog',
            'click #hide-invoiceterm-dialog':'hideInvoiceTermSearchDialog'
            
        },
        showInvoiceTermSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceterm-search-dialog').modal('show');
            
        },
        handleInvoiceTermSearch: function(event)
        {
            event.preventDefault();
            $('#invoiceterm-search-dialog').modal('hide');
            var invoiceTermCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceTermCode} });
            
        },
        hideInvoiceTermSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceterm-search-dialog').modal('hide');
            
        }
    });

    return InvoiceTermsView;
});
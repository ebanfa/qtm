define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/invoiceitem/list-invoiceitem.html'
], function (
    utilities,
    entities_strings,
    invoiceItemsTemplate) {

    var InvoiceItemsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceItemsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoiceitem-search':'handleInvoiceItemSearch',
            'click #show-invoiceitem-search-dialog':'showInvoiceItemSearchDialog',
            'click #hide-invoiceitem-dialog':'hideInvoiceItemSearchDialog'
            
        },
        showInvoiceItemSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceitem-search-dialog').modal('show');
            
        },
        handleInvoiceItemSearch: function(event)
        {
            event.preventDefault();
            $('#invoiceitem-search-dialog').modal('hide');
            var invoiceItemCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceItemCode} });
            
        },
        hideInvoiceItemSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceitem-search-dialog').modal('hide');
            
        }
    });

    return InvoiceItemsView;
});
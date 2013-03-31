define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/invoicestatustype/list-invoicestatustype.html'
], function (
    utilities,
    entities_strings,
    invoiceStatusTypesTemplate) {

    var InvoiceStatusTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceStatusTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoicestatustype-search':'handleInvoiceStatusTypeSearch',
            'click #show-invoicestatustype-search-dialog':'showInvoiceStatusTypeSearchDialog',
            'click #hide-invoicestatustype-dialog':'hideInvoiceStatusTypeSearchDialog'
            
        },
        showInvoiceStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicestatustype-search-dialog').modal('show');
            
        },
        handleInvoiceStatusTypeSearch: function(event)
        {
            event.preventDefault();
            $('#invoicestatustype-search-dialog').modal('hide');
            var invoiceStatusTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceStatusTypeCode} });
            
        },
        hideInvoiceStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicestatustype-search-dialog').modal('hide');
            
        }
    });

    return InvoiceStatusTypesView;
});
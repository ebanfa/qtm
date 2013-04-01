define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/invoiceroletype/list-invoiceroletype.html'
], function (
    utilities,
    entities_strings,
    invoiceRoleTypesTemplate) {

    var InvoiceRoleTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceRoleTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoiceroletype-search':'handleInvoiceRoleTypeSearch',
            'click #show-invoiceroletype-search-dialog':'showInvoiceRoleTypeSearchDialog',
            'click #hide-invoiceroletype-dialog':'hideInvoiceRoleTypeSearchDialog'
            
        },
        showInvoiceRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceroletype-search-dialog').modal('show');
            
        },
        handleInvoiceRoleTypeSearch: function(event)
        {
            event.preventDefault();
            $('#invoiceroletype-search-dialog').modal('hide');
            var invoiceRoleTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceRoleTypeCode} });
            
        },
        hideInvoiceRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceroletype-search-dialog').modal('hide');
            
        }
    });

    return InvoiceRoleTypesView;
});
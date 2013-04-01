define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/invoiceitemcategory/list-invoiceitemcategory.html'
], function (
    utilities,
    entities_strings,
    invoiceItemCategorysTemplate) {

    var InvoiceItemCategorysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceItemCategorysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoiceitemcategory-search':'handleInvoiceItemCategorySearch',
            'click #show-invoiceitemcategory-search-dialog':'showInvoiceItemCategorySearchDialog',
            'click #hide-invoiceitemcategory-dialog':'hideInvoiceItemCategorySearchDialog'
            
        },
        showInvoiceItemCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceitemcategory-search-dialog').modal('show');
            
        },
        handleInvoiceItemCategorySearch: function(event)
        {
            event.preventDefault();
            $('#invoiceitemcategory-search-dialog').modal('hide');
            var invoiceItemCategoryCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceItemCategoryCode} });
            
        },
        hideInvoiceItemCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoiceitemcategory-search-dialog').modal('hide');
            
        }
    });

    return InvoiceItemCategorysView;
});
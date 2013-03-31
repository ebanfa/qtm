define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/invoicerole/list-invoicerole.html'
], function (
    utilities,
    entities_strings,
    invoiceRolesTemplate) {

    var InvoiceRolesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), invoiceRolesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-invoicerole-search':'handleInvoiceRoleSearch',
            'click #show-invoicerole-search-dialog':'showInvoiceRoleSearchDialog',
            'click #hide-invoicerole-dialog':'hideInvoiceRoleSearchDialog'
            
        },
        showInvoiceRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicerole-search-dialog').modal('show');
            
        },
        handleInvoiceRoleSearch: function(event)
        {
            event.preventDefault();
            $('#invoicerole-search-dialog').modal('hide');
            var invoiceRoleCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: invoiceRoleCode} });
            
        },
        hideInvoiceRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#invoicerole-search-dialog').modal('hide');
            
        }
    });

    return InvoiceRolesView;
});
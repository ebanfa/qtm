define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/invoice/orderitembilling/list-orderitembilling.html'
], function (
    utilities,
    entities_strings,
    orderItemBillingsTemplate) {

    var OrderItemBillingsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), orderItemBillingsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-orderitembilling-search':'handleOrderItemBillingSearch',
            'click #show-orderitembilling-search-dialog':'showOrderItemBillingSearchDialog',
            'click #hide-orderitembilling-dialog':'hideOrderItemBillingSearchDialog'
            
        },
        showOrderItemBillingSearchDialog: function(event)
        {
            event.preventDefault();
            $('#orderitembilling-search-dialog').modal('show');
            
        },
        handleOrderItemBillingSearch: function(event)
        {
            event.preventDefault();
            $('#orderitembilling-search-dialog').modal('hide');
            var orderItemBillingCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: orderItemBillingCode} });
            
        },
        hideOrderItemBillingSearchDialog: function(event)
        {
            event.preventDefault();
            $('#orderitembilling-search-dialog').modal('hide');
            
        }
    });

    return OrderItemBillingsView;
});
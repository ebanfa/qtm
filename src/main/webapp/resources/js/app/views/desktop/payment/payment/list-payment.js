define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/payment/payment/list-payment.html'
], function (
    utilities,
    entities_strings,
    paymentsTemplate) {

    var PaymentsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), paymentsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-payment-search':'handlePaymentSearch',
            'click #show-payment-search-dialog':'showPaymentSearchDialog',
            'click #hide-payment-dialog':'hidePaymentSearchDialog'
            
        },
        showPaymentSearchDialog: function(event)
        {
            event.preventDefault();
            $('#payment-search-dialog').modal('show');
            
        },
        handlePaymentSearch: function(event)
        {
            event.preventDefault();
            $('#payment-search-dialog').modal('hide');
            var paymentCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: paymentCode} });
            
        },
        hidePaymentSearchDialog: function(event)
        {
            event.preventDefault();
            $('#payment-search-dialog').modal('hide');
            
        }
    });

    return PaymentsView;
});
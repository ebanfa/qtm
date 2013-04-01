define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/payment/paymentapplication/list-paymentapplication.html'
], function (
    utilities,
    entities_strings,
    paymentApplicationsTemplate) {

    var PaymentApplicationsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), paymentApplicationsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-paymentapplication-search':'handlePaymentApplicationSearch',
            'click #show-paymentapplication-search-dialog':'showPaymentApplicationSearchDialog',
            'click #hide-paymentapplication-dialog':'hidePaymentApplicationSearchDialog'
            
        },
        showPaymentApplicationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymentapplication-search-dialog').modal('show');
            
        },
        handlePaymentApplicationSearch: function(event)
        {
            event.preventDefault();
            $('#paymentapplication-search-dialog').modal('hide');
            var paymentApplicationCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: paymentApplicationCode} });
            
        },
        hidePaymentApplicationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymentapplication-search-dialog').modal('hide');
            
        }
    });

    return PaymentApplicationsView;
});
define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/paymentmethodtype/list-paymentmethodtype.html'
], function (
    utilities,
    entities_strings,
    paymentMethodTypesTemplate) {

    var PaymentMethodTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), paymentMethodTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-paymentmethodtype-search':'handlePaymentMethodTypeSearch',
            'click #show-paymentmethodtype-search-dialog':'showPaymentMethodTypeSearchDialog',
            'click #hide-paymentmethodtype-dialog':'hidePaymentMethodTypeSearchDialog'
            
        },
        showPaymentMethodTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymentmethodtype-search-dialog').modal('show');
            
        },
        handlePaymentMethodTypeSearch: function(event)
        {
            event.preventDefault();
            $('#paymentmethodtype-search-dialog').modal('hide');
            var paymentMethodTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: paymentMethodTypeCode} });
            
        },
        hidePaymentMethodTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymentmethodtype-search-dialog').modal('hide');
            
        }
    });

    return PaymentMethodTypesView;
});
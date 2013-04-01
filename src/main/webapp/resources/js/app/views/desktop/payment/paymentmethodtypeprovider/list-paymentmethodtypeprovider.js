define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/payment/paymentmethodtypeprovider/list-paymentmethodtypeprovider.html'
], function (
    utilities,
    entities_strings,
    paymentMethodTypeProvidersTemplate) {

    var PaymentMethodTypeProvidersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), paymentMethodTypeProvidersTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-paymentmethodtypeprovider-search':'handlePaymentMethodTypeProviderSearch',
            'click #show-paymentmethodtypeprovider-search-dialog':'showPaymentMethodTypeProviderSearchDialog',
            'click #hide-paymentmethodtypeprovider-dialog':'hidePaymentMethodTypeProviderSearchDialog'
            
        },
        showPaymentMethodTypeProviderSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymentmethodtypeprovider-search-dialog').modal('show');
            
        },
        handlePaymentMethodTypeProviderSearch: function(event)
        {
            event.preventDefault();
            $('#paymentmethodtypeprovider-search-dialog').modal('hide');
            var paymentMethodTypeProviderCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: paymentMethodTypeProviderCode} });
            
        },
        hidePaymentMethodTypeProviderSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymentmethodtypeprovider-search-dialog').modal('hide');
            
        }
    });

    return PaymentMethodTypeProvidersView;
});
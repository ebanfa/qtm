define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/payment-home.html'
], function (utilities, config, homeTemplate) {

    var PaymentHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return PaymentHomeView;
});
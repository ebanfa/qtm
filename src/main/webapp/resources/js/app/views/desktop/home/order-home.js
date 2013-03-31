define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/order-home.html'
], function (utilities, config, homeTemplate) {

    var OrderHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return OrderHomeView;
});
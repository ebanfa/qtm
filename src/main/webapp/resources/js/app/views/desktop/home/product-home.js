define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/product-home.html'
], function (utilities, config, homeTemplate) {

    var ProductHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return ProductHomeView;
});
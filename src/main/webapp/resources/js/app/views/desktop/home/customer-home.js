define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/customer-home.html'
], function (utilities, config, homeTemplate) {

    var CustomerHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return CustomerHomeView;
});
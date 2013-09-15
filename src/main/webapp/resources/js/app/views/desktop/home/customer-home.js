define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/customer-home.html'
], function (utilities, config, homeTemplate) {

    var CustomerHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            $("#tabber").tabs({active: 1});
            return this;
        }
    });
    return CustomerHomeView;
});
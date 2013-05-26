define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/datasource-home.html'
], function (utilities, config, homeTemplate) {

    var InvoiceHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return InvoiceHomeView;
});
define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/reports-home.html'
], function (utilities, config, homeTemplate) {

    var ReportsHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return ReportsHomeView;
});
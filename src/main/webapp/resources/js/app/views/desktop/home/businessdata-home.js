define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/businessdata-home.html'
], function (utilities, config, homeTemplate) {

    var BusinessDataHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return BusinessDataHomeView;
});
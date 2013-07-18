define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/security-home.html'
], function (utilities, config, homeTemplate) {

    var SecurityHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return SecurityHomeView;
});
define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/notification-home.html'
], function (utilities, config, homeTemplate) {

    var NotificationHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return NotificationHomeView;
});
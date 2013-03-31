define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/messaging-home.html'
], function (utilities, config, homeTemplate) {

    var MessagingHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return MessagingHomeView;
});
define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/channel-home.html'
], function (utilities, config, homeTemplate) {

    var ChannelHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return ChannelHomeView;
});
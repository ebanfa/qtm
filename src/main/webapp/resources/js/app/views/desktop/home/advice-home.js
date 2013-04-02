define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/advice-home.html'
], function (utilities, config, homeTemplate) {

    var AdviceHomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return AdviceHomeView;
});
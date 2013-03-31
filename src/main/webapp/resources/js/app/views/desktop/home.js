define([
    'utilities',
    'configuration',
    'text!../../../../templates/desktop/home/home.html'
], function (utilities, config, homeTemplate) {

    var HomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        }
    });
    return HomeView;
});
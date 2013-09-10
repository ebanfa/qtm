define([
    'utilities',
    'configuration',
    'text!../../../../templates/desktop/home/home.html'
], function (utilities, config, homeTemplate) {

    var HomeView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            $.jqplot('chartdiv',  [[[1, 2],[3,5.12],[5,13.1],[7,33.6],[9,85.9],[11,219.9]]]);
            return this;
        }
    });
    return HomeView;
});
define([
    'utilities',
    'jquerymobile',
    'configuration',
    'text!../../../../../templates/mobile/login/login.html'
], function (utilities, jqm, config, loginViewTemplate) {

    var LoginView = Backbone.View.extend({

        initialize: function(){
            _.bindAll(this, "render");
        },
        render:function () {
            utilities.applyTemplate($(this.el), loginViewTemplate, {model:this.model});
            return this;
        },
        events:
        {
            "submit #loginForm ":"handleLogin"
        },
        handleLogin: function(event)
        {
            event.preventDefault();
            event.stopImmediatePropagation();
            alert('Brand new nine');
            utilities.navigate("home");
        }
    });
    return LoginView;
});
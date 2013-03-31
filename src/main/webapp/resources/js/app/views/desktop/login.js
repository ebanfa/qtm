define([
    'utilities',
    'configuration',
    'text!../../../../templates/desktop/login/login.html'
], function (utilities, config, loginViewTemplate) {

var LoginView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {           
            utilities.applyTemplate($(this.el), loginViewTemplate,  {});
            this.delegateEvents();
            return this;
        },
        events:
        {
            'submit #login-form':'handleLogin'
        },
        handleLogin:function(event)
        {
            event.preventDefault();
            console.log('>>>>>>>>>>>>>>Login user')
            Backbone.history.navigate('home');
            window.location.reload();
        }
    });
    return LoginView;
});
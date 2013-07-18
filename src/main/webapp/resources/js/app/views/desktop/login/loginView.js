define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityviewview',
    'text!../../../../../templates/desktop/login/login.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityViewView, LoginTempl) {
    
    var LoginView = Backbone.View.extend({
    
        initialize:function () {
            console.log('Initializing Login View');
        },

        events: {
            "submit #login-form": "login"
        },

        render:function () {
            utilities.applyTemplate($(this.el), LoginTempl,  {entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },

        login:function (event) {
            event.preventDefault(); // Don't let this button submit the form
            $('.alert-error').empty(); // Empty previous errors if any
            $('.alert-error').hide(); // Hide any errors on a new submit
            var url = config.baseUrl + 'rest/login/';
            console.log('Loggin in... ');
            var formValues = {
                username: $('#username').val(),
                password: $('#password').val()
            };
            var errors = [];
            if(formValues.username == null || formValues.username == "") {
                errors.push({name: 'username', message: entities_strings.alantra_form_field_required + entities_strings.alantra_login_username + '.'});
            }
            if(formValues.password == null || formValues.password == "") {
                errors.push({name: 'password', message: entities_strings.alantra_form_field_required + entities_strings.alantra_login_password + '.'});
            }
            // If we have any form validation errors...
            var errorMessage = "";
             _.each(errors, function (error) {
                errorMessage += "<div>" + error.message + "<div/>";
            }, this);
            // Alert the user
            if(errorMessage != "") {
                // If there is an error, show the error messages
                $('.alert-error').append(errorMessage);
                $('.alert-error').show();
                //alert(errorMessage);
            }
            else 
            {
                $.ajax({
                    url:url,
                    type:'POST',
                    //contentType: 'application/json; charset=utf-8',
                    dataType:"json",
                    data: formValues,
                    success:function (data) {
                        console.log(["Login request details: ", data]);
                       
                        if(data.error) {  
                            // If there is an error, show the error messages
                            $('.alert-error').text(data.error.text).show();
                        }
                        else { // If not, send them back to the home page
                            //window.location.replace('#');
                            Backbone.history.navigate('home');
                            window.location.reload();
                        }
                    }
                });
            }
        }
    });

    return LoginView;
});

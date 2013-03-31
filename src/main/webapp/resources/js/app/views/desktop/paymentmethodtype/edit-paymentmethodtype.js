define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/paymentmethodtype/edit-paymentmethodtype.html'
], function (utilities, config, formUtilities, entities_strings, PaymentMethodTypeEditTemplate) {
	
	
    var PaymentMethodTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(paymentmethodtype)
                    {
                        utilities.applyTemplate($(self.el), PaymentMethodTypeEditTemplate,  
                            {model:this.model, paymentmethodtype:paymentmethodtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PaymentMethodTypeEditTemplate,  
                    {model:this.model, paymentmethodtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-paymentmethodtype-form':'editPaymentMethodType'
            
        },
        editPaymentMethodType: function(event)
        {
            event.preventDefault();
            var paymentmethodtype = $(event.currentTarget).serializeObject();
            this.model.save(paymentmethodtype, { 
                'success': function ()
                {
                    utilities.navigate('list-paymentmethodtype');
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
            return false;
        },
        renderSubViews:function()
        {
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            if (this.model.attributes.id)
            {
            }
        }
    });

    return PaymentMethodTypeEditView;
});

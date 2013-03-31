define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/paymenttype/edit-paymenttype.html'
], function (utilities, config, formUtilities, entities_strings, PaymentTypeEditTemplate) {
	
	
    var PaymentTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(paymenttype)
                    {
                        utilities.applyTemplate($(self.el), PaymentTypeEditTemplate,  
                            {model:this.model, paymenttype:paymenttype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PaymentTypeEditTemplate,  
                    {model:this.model, paymenttype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-paymenttype-form':'editPaymentType'
            
        },
        editPaymentType: function(event)
        {
            event.preventDefault();
            var paymenttype = $(event.currentTarget).serializeObject();
            this.model.save(paymenttype, { 
                'success': function ()
                {
                    utilities.navigate('list-paymenttype');
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

    return PaymentTypeEditView;
});

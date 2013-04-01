define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/customer/billingaccount/edit-billingaccount.html'
], function (utilities, config, formUtilities, entities_strings, BillingAccountEditTemplate) {
	
	
    var BillingAccountEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(billingaccount)
                    {
                        utilities.applyTemplate($(self.el), BillingAccountEditTemplate,  
                            {model:this.model, billingaccount:billingaccount, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), BillingAccountEditTemplate,  
                    {model:this.model, billingaccount:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-billingaccount-form':'editBillingAccount'
            
        },
        editBillingAccount: function(event)
        {
            event.preventDefault();
            var billingaccount = $(event.currentTarget).serializeObject();
            this.model.save(billingaccount, { 
                'success': function ()
                {
                    utilities.navigate('list-billingaccount');
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

    return BillingAccountEditView;
});

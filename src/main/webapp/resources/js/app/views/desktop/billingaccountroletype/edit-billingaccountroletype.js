define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/billingaccountroletype/edit-billingaccountroletype.html'
], function (utilities, config, formUtilities, entities_strings, BillingAccountRoleTypeEditTemplate) {
	
	
    var BillingAccountRoleTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(billingaccountroletype)
                    {
                        utilities.applyTemplate($(self.el), BillingAccountRoleTypeEditTemplate,  
                            {model:this.model, billingaccountroletype:billingaccountroletype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), BillingAccountRoleTypeEditTemplate,  
                    {model:this.model, billingaccountroletype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-billingaccountroletype-form':'editBillingAccountRoleType'
            
        },
        editBillingAccountRoleType: function(event)
        {
            event.preventDefault();
            var billingaccountroletype = $(event.currentTarget).serializeObject();
            this.model.save(billingaccountroletype, { 
                'success': function ()
                {
                    utilities.navigate('list-billingaccountroletype');
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

    return BillingAccountRoleTypeEditView;
});

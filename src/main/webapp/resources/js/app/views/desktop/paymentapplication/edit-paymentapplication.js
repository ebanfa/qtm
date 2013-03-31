define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/payment/payment',
    'app/collections/billingaccount/billingaccount',
    'app/collections/invoiceitem/invoiceitem',
    'text!../../../../../templates/desktop/payment/payment-list-subview.html',
    'text!../../../../../templates/desktop/billingaccount/billingaccount-list-subview.html',
    'text!../../../../../templates/desktop/invoiceitem/invoiceitem-list-subview.html',
    'text!../../../../../templates/desktop/paymentapplication/edit-paymentapplication.html'
], function (utilities, config, formUtilities, entities_strings, Payments, BillingAccounts, InvoiceItems, paymentListSubViewTemplate, billingAccountListSubViewTemplate, invoiceItemListSubViewTemplate, PaymentApplicationEditTemplate) {
	
    var PaymentListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentSelectContainerDiv'), paymentListSubViewTemplate,  {model:self.model, relatedFieldName:"payment", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var paymentsFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentsFetch.done(function (){
                utilities.applyTemplate($('#paymentSelectContainerDiv'), paymentListSubViewTemplate,  {model:self.model, relatedFieldName:"payment", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var BillingAccountListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  {model:self.model, relatedFieldName:"billingAccount", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var billingAccountsFetch = this.model.fetch();
            // Re render the template when the data is available    
            billingAccountsFetch.done(function (){
                utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  {model:self.model, relatedFieldName:"billingAccount", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var InvoiceItemListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceItemSelectContainerDiv'), invoiceItemListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceItem", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceItemsFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceItemsFetch.done(function (){
                utilities.applyTemplate($('#invoiceItemSelectContainerDiv'), invoiceItemListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceItem", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PaymentApplicationEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(paymentapplication)
                    {
                        utilities.applyTemplate($(self.el), PaymentApplicationEditTemplate,  
                            {model:this.model, paymentapplication:paymentapplication, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PaymentApplicationEditTemplate,  
                    {model:this.model, paymentapplication:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-paymentapplication-form':'editPaymentApplication'
            
        },
        editPaymentApplication: function(event)
        {
            event.preventDefault();
            var paymentapplication = $(event.currentTarget).serializeObject();
            this.model.save(paymentapplication, { 
                'success': function ()
                {
                    utilities.navigate('list-paymentapplication');
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
		    	this.paymentId = this.model.attributes.payment
		    	this.billingAccountId = this.model.attributes.billingAccount
		    	this.invoiceItemId = this.model.attributes.invoiceItem
            }
            // Payments
            var payments = new Payments();
            paymentListSubView = new PaymentListSubView({model:payments, el:$('#paymentSelectContainerDiv'), selectedOption:this.paymentId});
            paymentListSubView.render();
            // BillingAccounts
            var billingAccounts = new BillingAccounts();
            billingAccountListSubView = new BillingAccountListSubView({model:billingAccounts, el:$('#billingAccountSelectContainerDiv'), selectedOption:this.billingAccountId});
            billingAccountListSubView.render();
            // InvoiceItems
            var invoiceItems = new InvoiceItems();
            invoiceItemListSubView = new InvoiceItemListSubView({model:invoiceItems, el:$('#invoiceItemSelectContainerDiv'), selectedOption:this.invoiceItemId});
            invoiceItemListSubView.render();
        }
    });

    return PaymentApplicationEditView;
});

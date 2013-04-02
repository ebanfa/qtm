define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/payment/payment/payment',
    'app/collections/customer/billingaccount/billingaccount',
    'app/collections/invoice/invoiceitem/invoiceitem',
    'text!../../../../../../templates/desktop/payment/payment/payment-list-subview.html',
    'text!../../../../../../templates/desktop/customer/billingaccount/billingaccount-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoiceitem/invoiceitem-list-subview.html',
    'text!../../../../../../templates/desktop/payment/paymentapplication/edit-paymentapplication.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Payments, BillingAccounts, InvoiceItems, paymentListSubViewTemplate, billingAccountListSubViewTemplate, invoiceItemListSubViewTemplate, PaymentApplicationEditTemplate) {
	
    var PaymentListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentSelectContainerDiv'), paymentListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var paymentsFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentsFetch.done(function (){
                utilities.applyTemplate($('#paymentSelectContainerDiv'), paymentListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"payment", 
            	fieldName:entities_strings.payment, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var BillingAccountListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var billingAccountsFetch = this.model.fetch();
            // Re render the template when the data is available    
            billingAccountsFetch.done(function (){
                utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"billingAccount", 
            	fieldName:entities_strings.billingaccount, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var InvoiceItemListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceItemSelectContainerDiv'), invoiceItemListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceItemsFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceItemsFetch.done(function (){
                utilities.applyTemplate($('#invoiceItemSelectContainerDiv'), invoiceItemListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"invoiceItem", 
            	fieldName:entities_strings.invoiceitem, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var PaymentApplicationEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PaymentApplicationEditTemplate;
        },
        events:
        {
            'submit #edit-paymentapplication-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-paymentapplication');
        },
        renderSubViews:function()
        {
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

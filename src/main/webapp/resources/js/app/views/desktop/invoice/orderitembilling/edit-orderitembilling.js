define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/invoice/invoiceitem/invoiceitem',
    'app/collections/order/productorderitem/productorderitem',
    'text!../../../../../../templates/desktop/invoice/invoiceitem/invoiceitem-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorderitem/productorderitem-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/orderitembilling/edit-orderitembilling.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceItems, ProductOrderItems, invoiceItemListSubViewTemplate, productOrderItemListSubViewTemplate, OrderItemBillingEditTemplate) {
	
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
    
    var ProductOrderItemListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderItemSelectContainerDiv'), productOrderItemListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrderItem", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productOrderItemsFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrderItemsFetch.done(function (){
                utilities.applyTemplate($('#productOrderItemSelectContainerDiv'), productOrderItemListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrderItem", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var OrderItemBillingEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(orderitembilling)
                    {
                        utilities.applyTemplate($(self.el), OrderItemBillingEditTemplate,  
                            {model:this.model, orderitembilling:orderitembilling, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), OrderItemBillingEditTemplate,  
                    {model:this.model, orderitembilling:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-orderitembilling-form':'editOrderItemBilling'
            
        },
        editOrderItemBilling: function(event)
        {
            event.preventDefault();
            var orderitembilling = $(event.currentTarget).serializeObject();
            this.model.save(orderitembilling, { 
                'success': function ()
                {
                    utilities.navigate('list-orderitembilling');
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
		    	this.invoiceItemId = this.model.attributes.invoiceItem
		    	this.productOrderItemId = this.model.attributes.productOrderItem
            }
            // InvoiceItems
            var invoiceItems = new InvoiceItems();
            invoiceItemListSubView = new InvoiceItemListSubView({model:invoiceItems, el:$('#invoiceItemSelectContainerDiv'), selectedOption:this.invoiceItemId});
            invoiceItemListSubView.render();
            // ProductOrderItems
            var productOrderItems = new ProductOrderItems();
            productOrderItemListSubView = new ProductOrderItemListSubView({model:productOrderItems, el:$('#productOrderItemSelectContainerDiv'), selectedOption:this.productOrderItemId});
            productOrderItemListSubView.render();
        }
    });

    return OrderItemBillingEditView;
});

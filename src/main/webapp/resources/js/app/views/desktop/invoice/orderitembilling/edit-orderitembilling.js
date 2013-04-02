define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/invoice/invoiceitem/invoiceitem',
    'app/collections/order/productorderitem/productorderitem',
    'text!../../../../../../templates/desktop/invoice/invoiceitem/invoiceitem-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorderitem/productorderitem-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/orderitembilling/edit-orderitembilling.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceItems, ProductOrderItems, invoiceItemListSubViewTemplate, productOrderItemListSubViewTemplate, OrderItemBillingEditTemplate) {
	
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
    
    var ProductOrderItemListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderItemSelectContainerDiv'), productOrderItemListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productOrderItemsFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrderItemsFetch.done(function (){
                utilities.applyTemplate($('#productOrderItemSelectContainerDiv'), productOrderItemListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productOrderItem", 
            	fieldName:entities_strings.productorderitem, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var OrderItemBillingEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = OrderItemBillingEditTemplate;
        },
        events:
        {
            'submit #edit-orderitembilling-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-orderitembilling');
        },
        renderSubViews:function()
        {
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

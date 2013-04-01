define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/order/productorderitemtype/productorderitemtype',
    'app/collections/order/productorder/productorder',
    'app/collections/product/product/product',
    'app/collections/product/productfeature/productfeature',
    'text!../../../../../../templates/desktop/order/productorderitemtype/productorderitemtype-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorder/productorder-list-subview.html',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorderitem/edit-productorderitem.html'
], function (utilities, config, formUtilities, entities_strings, ProductOrderItemTypes, ProductOrders, Products, ProductFeatures, productOrderItemTypeListSubViewTemplate, productOrderListSubViewTemplate, productListSubViewTemplate, productFeatureListSubViewTemplate, ProductOrderItemEditTemplate) {
	
    var ProductOrderItemTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderItemTypeSelectContainerDiv'), productOrderItemTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrderItemType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productOrderItemTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrderItemTypesFetch.done(function (){
                utilities.applyTemplate($('#productOrderItemTypeSelectContainerDiv'), productOrderItemTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrderItemType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ProductOrderListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderSelectContainerDiv'), productOrderListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrder", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productOrdersFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrdersFetch.done(function (){
                utilities.applyTemplate($('#productOrderSelectContainerDiv'), productOrderListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrder", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ProductListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:self.model, relatedFieldName:"product", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productsFetch = this.model.fetch();
            // Re render the template when the data is available    
            productsFetch.done(function (){
                utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:self.model, relatedFieldName:"product", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ProductFeatureListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureSelectContainerDiv'), productFeatureListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeature", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productFeaturesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeaturesFetch.done(function (){
                utilities.applyTemplate($('#productFeatureSelectContainerDiv'), productFeatureListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeature", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductOrderItemEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productorderitem)
                    {
                        utilities.applyTemplate($(self.el), ProductOrderItemEditTemplate,  
                            {model:this.model, productorderitem:productorderitem, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductOrderItemEditTemplate,  
                    {model:this.model, productorderitem:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productorderitem-form':'editProductOrderItem'
            
        },
        editProductOrderItem: function(event)
        {
            event.preventDefault();
            var productorderitem = $(event.currentTarget).serializeObject();
            this.model.save(productorderitem, { 
                'success': function ()
                {
                    utilities.navigate('list-productorderitem');
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
		    	this.productOrderItemTypeId = this.model.attributes.productOrderItemType
		    	this.productOrderId = this.model.attributes.productOrder
		    	this.productId = this.model.attributes.product
		    	this.productFeatureId = this.model.attributes.productFeature
            }
            // ProductOrderItemTypes
            var productOrderItemTypes = new ProductOrderItemTypes();
            productOrderItemTypeListSubView = new ProductOrderItemTypeListSubView({model:productOrderItemTypes, el:$('#productOrderItemTypeSelectContainerDiv'), selectedOption:this.productOrderItemTypeId});
            productOrderItemTypeListSubView.render();
            // ProductOrders
            var productOrders = new ProductOrders();
            productOrderListSubView = new ProductOrderListSubView({model:productOrders, el:$('#productOrderSelectContainerDiv'), selectedOption:this.productOrderId});
            productOrderListSubView.render();
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
            // ProductFeatures
            var productFeatures = new ProductFeatures();
            productFeatureListSubView = new ProductFeatureListSubView({model:productFeatures, el:$('#productFeatureSelectContainerDiv'), selectedOption:this.productFeatureId});
            productFeatureListSubView.render();
        }
    });

    return ProductOrderItemEditView;
});

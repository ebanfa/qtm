define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/order/productorderitemtype/productorderitemtype',
    'app/collections/order/productorder/productorder',
    'app/collections/product/product/product',
    'app/collections/product/productfeature/productfeature',
    'text!../../../../../../templates/desktop/order/productorderitemtype/productorderitemtype-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorder/productorder-list-subview.html',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorderitem/edit-productorderitem.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductOrderItemTypes, ProductOrders, Products, ProductFeatures, productOrderItemTypeListSubViewTemplate, productOrderListSubViewTemplate, productListSubViewTemplate, productFeatureListSubViewTemplate, ProductOrderItemEditTemplate) {
	
    var ProductOrderItemTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderItemTypeSelectContainerDiv'), productOrderItemTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productOrderItemTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrderItemTypesFetch.done(function (){
                utilities.applyTemplate($('#productOrderItemTypeSelectContainerDiv'), productOrderItemTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productOrderItemType", 
            	fieldName:entities_strings.productorderitemtype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ProductOrderListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderSelectContainerDiv'), productOrderListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productOrdersFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrdersFetch.done(function (){
                utilities.applyTemplate($('#productOrderSelectContainerDiv'), productOrderListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productOrder", 
            	fieldName:entities_strings.productorder, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ProductListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productsFetch = this.model.fetch();
            // Re render the template when the data is available    
            productsFetch.done(function (){
                utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"product", 
            	fieldName:entities_strings.product, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ProductFeatureListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureSelectContainerDiv'), productFeatureListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productFeaturesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeaturesFetch.done(function (){
                utilities.applyTemplate($('#productFeatureSelectContainerDiv'), productFeatureListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productFeature", 
            	fieldName:entities_strings.productfeature, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ProductOrderItemEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductOrderItemEditTemplate;
        },
        events:
        {
            'submit #edit-productorderitem-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productorderitem');
        },
        renderSubViews:function()
        {
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

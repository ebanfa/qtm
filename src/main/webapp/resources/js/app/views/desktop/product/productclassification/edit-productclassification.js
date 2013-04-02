define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/product/productcategory/productcategory',
    'app/collections/product/product/product',
    'text!../../../../../../templates/desktop/product/productcategory/productcategory-list-subview.html',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/productclassification/edit-productclassification.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductCategorys, Products, productCategoryListSubViewTemplate, productListSubViewTemplate, ProductClassificationEditTemplate) {
	
    var ProductCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productCategorySelectContainerDiv'), productCategoryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            productCategorysFetch.done(function (){
                utilities.applyTemplate($('#productCategorySelectContainerDiv'), productCategoryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productCategory", 
            	fieldName:entities_strings.productcategory, 
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
    
	
    var ProductClassificationEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductClassificationEditTemplate;
        },
        events:
        {
            'submit #edit-productclassification-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productclassification');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.productCategoryId = this.model.attributes.productCategory
		    	this.productId = this.model.attributes.product
            }
            // ProductCategorys
            var productCategorys = new ProductCategorys();
            productCategoryListSubView = new ProductCategoryListSubView({model:productCategorys, el:$('#productCategorySelectContainerDiv'), selectedOption:this.productCategoryId});
            productCategoryListSubView.render();
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
        }
    });

    return ProductClassificationEditView;
});

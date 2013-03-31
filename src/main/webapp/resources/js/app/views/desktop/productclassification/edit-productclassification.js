define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/productcategory/productcategory',
    'app/collections/product/product',
    'text!../../../../../templates/desktop/productcategory/productcategory-list-subview.html',
    'text!../../../../../templates/desktop/product/product-list-subview.html',
    'text!../../../../../templates/desktop/productclassification/edit-productclassification.html'
], function (utilities, config, formUtilities, entities_strings, ProductCategorys, Products, productCategoryListSubViewTemplate, productListSubViewTemplate, ProductClassificationEditTemplate) {
	
    var ProductCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productCategorySelectContainerDiv'), productCategoryListSubViewTemplate,  {model:self.model, relatedFieldName:"productCategory", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            productCategorysFetch.done(function (){
                utilities.applyTemplate($('#productCategorySelectContainerDiv'), productCategoryListSubViewTemplate,  {model:self.model, relatedFieldName:"productCategory", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
    
	
    var ProductClassificationEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productclassification)
                    {
                        utilities.applyTemplate($(self.el), ProductClassificationEditTemplate,  
                            {model:this.model, productclassification:productclassification, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductClassificationEditTemplate,  
                    {model:this.model, productclassification:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productclassification-form':'editProductClassification'
            
        },
        editProductClassification: function(event)
        {
            event.preventDefault();
            var productclassification = $(event.currentTarget).serializeObject();
            this.model.save(productclassification, { 
                'success': function ()
                {
                    utilities.navigate('list-productclassification');
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

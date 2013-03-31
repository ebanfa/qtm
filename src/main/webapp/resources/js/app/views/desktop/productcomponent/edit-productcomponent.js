define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/product/product',
    'app/collections/product/product',
    'text!../../../../../templates/desktop/product/product-list-subview.html',
    'text!../../../../../templates/desktop/product/product-list-subview.html',
    'text!../../../../../templates/desktop/productcomponent/edit-productcomponent.html'
], function (utilities, config, formUtilities, entities_strings, Products, Products, productListSubViewTemplate, productListSubViewTemplate, ProductComponentEditTemplate) {
	
    var ProductListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:self.model, relatedFieldName:"productByInProdId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productsFetch = this.model.fetch();
            // Re render the template when the data is available    
            productsFetch.done(function (){
                utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:self.model, relatedFieldName:"productByInProdId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
            utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:self.model, relatedFieldName:"productByForProdId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productsFetch = this.model.fetch();
            // Re render the template when the data is available    
            productsFetch.done(function (){
                utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:self.model, relatedFieldName:"productByForProdId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductComponentEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productcomponent)
                    {
                        utilities.applyTemplate($(self.el), ProductComponentEditTemplate,  
                            {model:this.model, productcomponent:productcomponent, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductComponentEditTemplate,  
                    {model:this.model, productcomponent:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productcomponent-form':'editProductComponent'
            
        },
        editProductComponent: function(event)
        {
            event.preventDefault();
            var productcomponent = $(event.currentTarget).serializeObject();
            this.model.save(productcomponent, { 
                'success': function ()
                {
                    utilities.navigate('list-productcomponent');
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
		    	this.productId = this.model.attributes.product
		    	this.productId = this.model.attributes.product
            }
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
        }
    });

    return ProductComponentEditView;
});

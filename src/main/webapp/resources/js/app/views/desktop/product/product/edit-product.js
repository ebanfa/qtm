define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/product/producttype/producttype',
    'text!../../../../../../templates/desktop/product/producttype/producttype-list-subview.html',
    'text!../../../../../../templates/desktop/product/product/edit-product.html'
], function (utilities, config, formUtilities, entities_strings, ProductTypes, productTypeListSubViewTemplate, ProductEditTemplate) {
	
    var ProductTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productTypeSelectContainerDiv'), productTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productTypesFetch.done(function (){
                utilities.applyTemplate($('#productTypeSelectContainerDiv'), productTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(product)
                    {
                        utilities.applyTemplate($(self.el), ProductEditTemplate,  
                            {model:this.model, product:product, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductEditTemplate,  
                    {model:this.model, product:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-product-form':'editProduct'
            
        },
        editProduct: function(event)
        {
            event.preventDefault();
            var product = $(event.currentTarget).serializeObject();
            this.model.save(product, { 
                'success': function ()
                {
                    utilities.navigate('list-product');
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
		    	this.productTypeId = this.model.attributes.productType
            }
            // ProductTypes
            var productTypes = new ProductTypes();
            productTypeListSubView = new ProductTypeListSubView({model:productTypes, el:$('#productTypeSelectContainerDiv'), selectedOption:this.productTypeId});
            productTypeListSubView.render();
        }
    });

    return ProductEditView;
});

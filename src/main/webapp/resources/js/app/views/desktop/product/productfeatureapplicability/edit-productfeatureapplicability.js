define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/product/product/product',
    'app/collections/product/productfeature/productfeature',
    'app/collections/product/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeatureapplicabilitytype/productfeatureapplicabilitytype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeatureapplicability/edit-productfeatureapplicability.html'
], function (utilities, config, formUtilities, entities_strings, Products, ProductFeatures, ProductFeatureApplicabilityTypes, productListSubViewTemplate, productFeatureListSubViewTemplate, productFeatureApplicabilityTypeListSubViewTemplate, ProductFeatureApplicabilityEditTemplate) {
	
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
    
    var ProductFeatureApplicabilityTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureApplicabilityTypeSelectContainerDiv'), productFeatureApplicabilityTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeatureApplicabilityType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productFeatureApplicabilityTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeatureApplicabilityTypesFetch.done(function (){
                utilities.applyTemplate($('#productFeatureApplicabilityTypeSelectContainerDiv'), productFeatureApplicabilityTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeatureApplicabilityType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductFeatureApplicabilityEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productfeatureapplicability)
                    {
                        utilities.applyTemplate($(self.el), ProductFeatureApplicabilityEditTemplate,  
                            {model:this.model, productfeatureapplicability:productfeatureapplicability, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductFeatureApplicabilityEditTemplate,  
                    {model:this.model, productfeatureapplicability:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productfeatureapplicability-form':'editProductFeatureApplicability'
            
        },
        editProductFeatureApplicability: function(event)
        {
            event.preventDefault();
            var productfeatureapplicability = $(event.currentTarget).serializeObject();
            this.model.save(productfeatureapplicability, { 
                'success': function ()
                {
                    utilities.navigate('list-productfeatureapplicability');
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
		    	this.productFeatureId = this.model.attributes.productFeature
		    	this.productFeatureApplicabilityTypeId = this.model.attributes.productFeatureApplicabilityType
            }
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
            // ProductFeatures
            var productFeatures = new ProductFeatures();
            productFeatureListSubView = new ProductFeatureListSubView({model:productFeatures, el:$('#productFeatureSelectContainerDiv'), selectedOption:this.productFeatureId});
            productFeatureListSubView.render();
            // ProductFeatureApplicabilityTypes
            var productFeatureApplicabilityTypes = new ProductFeatureApplicabilityTypes();
            productFeatureApplicabilityTypeListSubView = new ProductFeatureApplicabilityTypeListSubView({model:productFeatureApplicabilityTypes, el:$('#productFeatureApplicabilityTypeSelectContainerDiv'), selectedOption:this.productFeatureApplicabilityTypeId});
            productFeatureApplicabilityTypeListSubView.render();
        }
    });

    return ProductFeatureApplicabilityEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/product/product/product',
    'app/collections/product/costcomponenttype/costcomponenttype',
    'app/collections/product/productfeature/productfeature',
    'app/collections/businessdata/geoboundry/geoboundry',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/costcomponenttype/costcomponenttype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../../templates/desktop/party/estimatedproductcost/edit-estimatedproductcost.html'
], function (utilities, config, formUtilities, entities_strings, Products, CostComponentTypes, ProductFeatures, GeoBoundrys, productListSubViewTemplate, costComponentTypeListSubViewTemplate, productFeatureListSubViewTemplate, geoBoundryListSubViewTemplate, EstimatedProductCostEditTemplate) {
	
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
    
    var CostComponentTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#costComponentTypeSelectContainerDiv'), costComponentTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"costComponentType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var costComponentTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            costComponentTypesFetch.done(function (){
                utilities.applyTemplate($('#costComponentTypeSelectContainerDiv'), costComponentTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"costComponentType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
    
    var GeoBoundryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundry", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundry", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var EstimatedProductCostEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(estimatedproductcost)
                    {
                        utilities.applyTemplate($(self.el), EstimatedProductCostEditTemplate,  
                            {model:this.model, estimatedproductcost:estimatedproductcost, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), EstimatedProductCostEditTemplate,  
                    {model:this.model, estimatedproductcost:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-estimatedproductcost-form':'editEstimatedProductCost'
            
        },
        editEstimatedProductCost: function(event)
        {
            event.preventDefault();
            var estimatedproductcost = $(event.currentTarget).serializeObject();
            this.model.save(estimatedproductcost, { 
                'success': function ()
                {
                    utilities.navigate('list-estimatedproductcost');
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
		    	this.costComponentTypeId = this.model.attributes.costComponentType
		    	this.productFeatureId = this.model.attributes.productFeature
		    	this.geoBoundryId = this.model.attributes.geoBoundry
            }
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
            // CostComponentTypes
            var costComponentTypes = new CostComponentTypes();
            costComponentTypeListSubView = new CostComponentTypeListSubView({model:costComponentTypes, el:$('#costComponentTypeSelectContainerDiv'), selectedOption:this.costComponentTypeId});
            costComponentTypeListSubView.render();
            // ProductFeatures
            var productFeatures = new ProductFeatures();
            productFeatureListSubView = new ProductFeatureListSubView({model:productFeatures, el:$('#productFeatureSelectContainerDiv'), selectedOption:this.productFeatureId});
            productFeatureListSubView.render();
            // GeoBoundrys
            var geoBoundrys = new GeoBoundrys();
            geoBoundryListSubView = new GeoBoundryListSubView({model:geoBoundrys, el:$('#geoBoundrySelectContainerDiv'), selectedOption:this.geoBoundryId});
            geoBoundryListSubView.render();
        }
    });

    return EstimatedProductCostEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/product/product/product',
    'app/collections/product/costcomponenttype/costcomponenttype',
    'app/collections/product/productfeature/productfeature',
    'app/collections/businessdata/geoboundry/geoboundry',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/costcomponenttype/costcomponenttype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../../templates/desktop/party/estimatedproductcost/edit-estimatedproductcost.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Products, CostComponentTypes, ProductFeatures, GeoBoundrys, productListSubViewTemplate, costComponentTypeListSubViewTemplate, productFeatureListSubViewTemplate, geoBoundryListSubViewTemplate, EstimatedProductCostEditTemplate) {
	
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
    
    var CostComponentTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#costComponentTypeSelectContainerDiv'), costComponentTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var costComponentTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            costComponentTypesFetch.done(function (){
                utilities.applyTemplate($('#costComponentTypeSelectContainerDiv'), costComponentTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"costComponentType", 
            	fieldName:entities_strings.costcomponenttype, 
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
    
    var GeoBoundryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"geoBoundry", 
            	fieldName:entities_strings.geoboundry, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var EstimatedProductCostEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = EstimatedProductCostEditTemplate;
        },
        events:
        {
            'submit #edit-estimatedproductcost-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-estimatedproductcost');
        },
        renderSubViews:function()
        {
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

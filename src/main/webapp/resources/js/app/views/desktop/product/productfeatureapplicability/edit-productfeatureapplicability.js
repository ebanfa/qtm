define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/product/product/product',
    'app/collections/product/productfeature/productfeature',
    'app/collections/product/productfeatureapplicabilitytype/productfeatureapplicabilitytype',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeatureapplicabilitytype/productfeatureapplicabilitytype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeatureapplicability/edit-productfeatureapplicability.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Products, ProductFeatures, ProductFeatureApplicabilityTypes, productListSubViewTemplate, productFeatureListSubViewTemplate, productFeatureApplicabilityTypeListSubViewTemplate, ProductFeatureApplicabilityEditTemplate) {
	
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
    
    var ProductFeatureApplicabilityTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureApplicabilityTypeSelectContainerDiv'), productFeatureApplicabilityTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productFeatureApplicabilityTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeatureApplicabilityTypesFetch.done(function (){
                utilities.applyTemplate($('#productFeatureApplicabilityTypeSelectContainerDiv'), productFeatureApplicabilityTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productFeatureApplicabilityType", 
            	fieldName:entities_strings.productfeatureapplicabilitytype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ProductFeatureApplicabilityEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductFeatureApplicabilityEditTemplate;
        },
        events:
        {
            'submit #edit-productfeatureapplicability-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productfeatureapplicability');
        },
        renderSubViews:function()
        {
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

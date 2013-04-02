define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/product/productfeaturetype/productfeaturetype',
    'app/collections/product/productfeaturecategory/productfeaturecategory',
    'text!../../../../../../templates/desktop/product/productfeaturetype/productfeaturetype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeaturecategory/productfeaturecategory-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/edit-productfeature.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductFeatureTypes, ProductFeatureCategorys, productFeatureTypeListSubViewTemplate, productFeatureCategoryListSubViewTemplate, ProductFeatureEditTemplate) {
	
    var ProductFeatureTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureTypeSelectContainerDiv'), productFeatureTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productFeatureTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeatureTypesFetch.done(function (){
                utilities.applyTemplate($('#productFeatureTypeSelectContainerDiv'), productFeatureTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productFeatureType", 
            	fieldName:entities_strings.productfeaturetype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ProductFeatureCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureCategorySelectContainerDiv'), productFeatureCategoryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productFeatureCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeatureCategorysFetch.done(function (){
                utilities.applyTemplate($('#productFeatureCategorySelectContainerDiv'), productFeatureCategoryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productFeatureCategory", 
            	fieldName:entities_strings.productfeaturecategory, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ProductFeatureEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductFeatureEditTemplate;
        },
        events:
        {
            'submit #edit-productfeature-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productfeature');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.productFeatureTypeId = this.model.attributes.productFeatureType
		    	this.productFeatureCategoryId = this.model.attributes.productFeatureCategory
            }
            // ProductFeatureTypes
            var productFeatureTypes = new ProductFeatureTypes();
            productFeatureTypeListSubView = new ProductFeatureTypeListSubView({model:productFeatureTypes, el:$('#productFeatureTypeSelectContainerDiv'), selectedOption:this.productFeatureTypeId});
            productFeatureTypeListSubView.render();
            // ProductFeatureCategorys
            var productFeatureCategorys = new ProductFeatureCategorys();
            productFeatureCategoryListSubView = new ProductFeatureCategoryListSubView({model:productFeatureCategorys, el:$('#productFeatureCategorySelectContainerDiv'), selectedOption:this.productFeatureCategoryId});
            productFeatureCategoryListSubView.render();
        }
    });

    return ProductFeatureEditView;
});

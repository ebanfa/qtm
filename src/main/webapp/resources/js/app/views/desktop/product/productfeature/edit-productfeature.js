define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/product/productfeaturetype/productfeaturetype',
    'app/collections/product/productfeaturecategory/productfeaturecategory',
    'text!../../../../../../templates/desktop/product/productfeaturetype/productfeaturetype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeaturecategory/productfeaturecategory-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/edit-productfeature.html'
], function (utilities, config, formUtilities, entities_strings, ProductFeatureTypes, ProductFeatureCategorys, productFeatureTypeListSubViewTemplate, productFeatureCategoryListSubViewTemplate, ProductFeatureEditTemplate) {
	
    var ProductFeatureTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureTypeSelectContainerDiv'), productFeatureTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeatureType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productFeatureTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeatureTypesFetch.done(function (){
                utilities.applyTemplate($('#productFeatureTypeSelectContainerDiv'), productFeatureTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeatureType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ProductFeatureCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productFeatureCategorySelectContainerDiv'), productFeatureCategoryListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeatureCategory", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productFeatureCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            productFeatureCategorysFetch.done(function (){
                utilities.applyTemplate($('#productFeatureCategorySelectContainerDiv'), productFeatureCategoryListSubViewTemplate,  {model:self.model, relatedFieldName:"productFeatureCategory", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductFeatureEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productfeature)
                    {
                        utilities.applyTemplate($(self.el), ProductFeatureEditTemplate,  
                            {model:this.model, productfeature:productfeature, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductFeatureEditTemplate,  
                    {model:this.model, productfeature:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productfeature-form':'editProductFeature'
            
        },
        editProductFeature: function(event)
        {
            event.preventDefault();
            var productfeature = $(event.currentTarget).serializeObject();
            this.model.save(productfeature, { 
                'success': function ()
                {
                    utilities.navigate('list-productfeature');
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

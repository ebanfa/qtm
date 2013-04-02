define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/product/productcategorytype/productcategorytype',
    'text!../../../../../../templates/desktop/product/productcategorytype/productcategorytype-list-subview.html',
    'text!../../../../../../templates/desktop/product/productcategory/edit-productcategory.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductCategoryTypes, productCategoryTypeListSubViewTemplate, ProductCategoryEditTemplate) {
	
    var ProductCategoryTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productCategoryTypeSelectContainerDiv'), productCategoryTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productCategoryTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productCategoryTypesFetch.done(function (){
                utilities.applyTemplate($('#productCategoryTypeSelectContainerDiv'), productCategoryTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productCategoryType", 
            	fieldName:entities_strings.productcategorytype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ProductCategoryEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductCategoryEditTemplate;
        },
        events:
        {
            'submit #edit-productcategory-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productcategory');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.productCategoryTypeId = this.model.attributes.productCategoryType
            }
            // ProductCategoryTypes
            var productCategoryTypes = new ProductCategoryTypes();
            productCategoryTypeListSubView = new ProductCategoryTypeListSubView({model:productCategoryTypes, el:$('#productCategoryTypeSelectContainerDiv'), selectedOption:this.productCategoryTypeId});
            productCategoryTypeListSubView.render();
        }
    });

    return ProductCategoryEditView;
});

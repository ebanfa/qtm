define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/product/producttype/producttype',
    'text!../../../../../../templates/desktop/product/producttype/producttype-list-subview.html',
    'text!../../../../../../templates/desktop/product/product/edit-product.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductTypes, productTypeListSubViewTemplate, ProductEditTemplate) {
	
    var ProductTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productTypeSelectContainerDiv'), productTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productTypesFetch.done(function (){
                utilities.applyTemplate($('#productTypeSelectContainerDiv'), productTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productType", 
            	fieldName:entities_strings.producttype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ProductEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductEditTemplate;
        },
        events:
        {
            'submit #edit-product-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-product');
        },
        renderSubViews:function()
        {
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

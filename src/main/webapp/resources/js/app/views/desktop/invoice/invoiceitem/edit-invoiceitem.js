define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/invoice/invoiceitemtype/invoiceitemtype',
    'app/collections/invoice/invoiceitemcategory/invoiceitemcategory',
    'app/collections/product/product/product',
    'app/collections/product/productfeature/productfeature',
    'app/collections/invoice/invoice/invoice',
    'text!../../../../../../templates/desktop/invoice/invoiceitemtype/invoiceitemtype-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoiceitemcategory/invoiceitemcategory-list-subview.html',
    'text!../../../../../../templates/desktop/product/product/product-list-subview.html',
    'text!../../../../../../templates/desktop/product/productfeature/productfeature-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoice/invoice-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoiceitem/edit-invoiceitem.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceItemTypes, InvoiceItemCategorys, Products, ProductFeatures, Invoices, invoiceItemTypeListSubViewTemplate, invoiceItemCategoryListSubViewTemplate, productListSubViewTemplate, productFeatureListSubViewTemplate, invoiceListSubViewTemplate, InvoiceItemEditTemplate) {
	
    var InvoiceItemTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceItemTypeSelectContainerDiv'), invoiceItemTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceItemTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceItemTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceItemTypeSelectContainerDiv'), invoiceItemTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"invoiceItemType", 
            	fieldName:entities_strings.invoiceitemtype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var InvoiceItemCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceItemCategorySelectContainerDiv'), invoiceItemCategoryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceItemCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceItemCategorysFetch.done(function (){
                utilities.applyTemplate($('#invoiceItemCategorySelectContainerDiv'), invoiceItemCategoryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"invoiceItemCategory", 
            	fieldName:entities_strings.invoiceitemcategory, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
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
    
    var InvoiceListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoicesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoicesFetch.done(function (){
                utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"invoice", 
            	fieldName:entities_strings.invoice, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var InvoiceItemEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceItemEditTemplate;
        },
        events:
        {
            'submit #edit-invoiceitem-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoiceitem');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.invoiceItemTypeId = this.model.attributes.invoiceItemType
		    	this.invoiceItemCategoryId = this.model.attributes.invoiceItemCategory
		    	this.productId = this.model.attributes.product
		    	this.productFeatureId = this.model.attributes.productFeature
		    	this.invoiceId = this.model.attributes.invoice
            }
            // InvoiceItemTypes
            var invoiceItemTypes = new InvoiceItemTypes();
            invoiceItemTypeListSubView = new InvoiceItemTypeListSubView({model:invoiceItemTypes, el:$('#invoiceItemTypeSelectContainerDiv'), selectedOption:this.invoiceItemTypeId});
            invoiceItemTypeListSubView.render();
            // InvoiceItemCategorys
            var invoiceItemCategorys = new InvoiceItemCategorys();
            invoiceItemCategoryListSubView = new InvoiceItemCategoryListSubView({model:invoiceItemCategorys, el:$('#invoiceItemCategorySelectContainerDiv'), selectedOption:this.invoiceItemCategoryId});
            invoiceItemCategoryListSubView.render();
            // Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productSelectContainerDiv'), selectedOption:this.productId});
            productListSubView.render();
            // ProductFeatures
            var productFeatures = new ProductFeatures();
            productFeatureListSubView = new ProductFeatureListSubView({model:productFeatures, el:$('#productFeatureSelectContainerDiv'), selectedOption:this.productFeatureId});
            productFeatureListSubView.render();
            // Invoices
            var invoices = new Invoices();
            invoiceListSubView = new InvoiceListSubView({model:invoices, el:$('#invoiceSelectContainerDiv'), selectedOption:this.invoiceId});
            invoiceListSubView.render();
        }
    });

    return InvoiceItemEditView;
});

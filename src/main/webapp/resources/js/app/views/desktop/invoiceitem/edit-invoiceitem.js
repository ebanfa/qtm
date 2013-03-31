define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/invoiceitemtype/invoiceitemtype',
    'app/collections/invoiceitemcategory/invoiceitemcategory',
    'app/collections/product/product',
    'app/collections/productfeature/productfeature',
    'app/collections/invoice/invoice',
    'text!../../../../../templates/desktop/invoiceitemtype/invoiceitemtype-list-subview.html',
    'text!../../../../../templates/desktop/invoiceitemcategory/invoiceitemcategory-list-subview.html',
    'text!../../../../../templates/desktop/product/product-list-subview.html',
    'text!../../../../../templates/desktop/productfeature/productfeature-list-subview.html',
    'text!../../../../../templates/desktop/invoice/invoice-list-subview.html',
    'text!../../../../../templates/desktop/invoiceitem/edit-invoiceitem.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceItemTypes, InvoiceItemCategorys, Products, ProductFeatures, Invoices, invoiceItemTypeListSubViewTemplate, invoiceItemCategoryListSubViewTemplate, productListSubViewTemplate, productFeatureListSubViewTemplate, invoiceListSubViewTemplate, InvoiceItemEditTemplate) {
	
    var InvoiceItemTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceItemTypeSelectContainerDiv'), invoiceItemTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceItemType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceItemTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceItemTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceItemTypeSelectContainerDiv'), invoiceItemTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceItemType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var InvoiceItemCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceItemCategorySelectContainerDiv'), invoiceItemCategoryListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceItemCategory", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceItemCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceItemCategorysFetch.done(function (){
                utilities.applyTemplate($('#invoiceItemCategorySelectContainerDiv'), invoiceItemCategoryListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceItemCategory", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
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
    
    var InvoiceListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  {model:self.model, relatedFieldName:"invoice", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoicesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoicesFetch.done(function (){
                utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  {model:self.model, relatedFieldName:"invoice", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var InvoiceItemEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoiceitem)
                    {
                        utilities.applyTemplate($(self.el), InvoiceItemEditTemplate,  
                            {model:this.model, invoiceitem:invoiceitem, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceItemEditTemplate,  
                    {model:this.model, invoiceitem:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoiceitem-form':'editInvoiceItem'
            
        },
        editInvoiceItem: function(event)
        {
            event.preventDefault();
            var invoiceitem = $(event.currentTarget).serializeObject();
            this.model.save(invoiceitem, { 
                'success': function ()
                {
                    utilities.navigate('list-invoiceitem');
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

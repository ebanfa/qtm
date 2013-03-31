define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/productcategorytype/productcategorytype',
    'text!../../../../../templates/desktop/productcategorytype/productcategorytype-list-subview.html',
    'text!../../../../../templates/desktop/productcategory/edit-productcategory.html'
], function (utilities, config, formUtilities, entities_strings, ProductCategoryTypes, productCategoryTypeListSubViewTemplate, ProductCategoryEditTemplate) {
	
    var ProductCategoryTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productCategoryTypeSelectContainerDiv'), productCategoryTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productCategoryType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productCategoryTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productCategoryTypesFetch.done(function (){
                utilities.applyTemplate($('#productCategoryTypeSelectContainerDiv'), productCategoryTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productCategoryType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductCategoryEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productcategory)
                    {
                        utilities.applyTemplate($(self.el), ProductCategoryEditTemplate,  
                            {model:this.model, productcategory:productcategory, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductCategoryEditTemplate,  
                    {model:this.model, productcategory:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productcategory-form':'editProductCategory'
            
        },
        editProductCategory: function(event)
        {
            event.preventDefault();
            var productcategory = $(event.currentTarget).serializeObject();
            this.model.save(productcategory, { 
                'success': function ()
                {
                    utilities.navigate('list-productcategory');
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

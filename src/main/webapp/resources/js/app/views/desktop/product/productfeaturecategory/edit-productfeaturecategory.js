define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/product/productfeaturecategory/edit-productfeaturecategory.html'
], function (utilities, config, formUtilities, entities_strings, ProductFeatureCategoryEditTemplate) {
	
	
    var ProductFeatureCategoryEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productfeaturecategory)
                    {
                        utilities.applyTemplate($(self.el), ProductFeatureCategoryEditTemplate,  
                            {model:this.model, productfeaturecategory:productfeaturecategory, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductFeatureCategoryEditTemplate,  
                    {model:this.model, productfeaturecategory:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productfeaturecategory-form':'editProductFeatureCategory'
            
        },
        editProductFeatureCategory: function(event)
        {
            event.preventDefault();
            var productfeaturecategory = $(event.currentTarget).serializeObject();
            this.model.save(productfeaturecategory, { 
                'success': function ()
                {
                    utilities.navigate('list-productfeaturecategory');
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
            }
        }
    });

    return ProductFeatureCategoryEditView;
});

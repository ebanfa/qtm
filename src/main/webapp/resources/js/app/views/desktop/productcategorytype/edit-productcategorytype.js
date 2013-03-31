define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/productcategorytype/edit-productcategorytype.html'
], function (utilities, config, formUtilities, entities_strings, ProductCategoryTypeEditTemplate) {
	
	
    var ProductCategoryTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productcategorytype)
                    {
                        utilities.applyTemplate($(self.el), ProductCategoryTypeEditTemplate,  
                            {model:this.model, productcategorytype:productcategorytype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductCategoryTypeEditTemplate,  
                    {model:this.model, productcategorytype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productcategorytype-form':'editProductCategoryType'
            
        },
        editProductCategoryType: function(event)
        {
            event.preventDefault();
            var productcategorytype = $(event.currentTarget).serializeObject();
            this.model.save(productcategorytype, { 
                'success': function ()
                {
                    utilities.navigate('list-productcategorytype');
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

    return ProductCategoryTypeEditView;
});

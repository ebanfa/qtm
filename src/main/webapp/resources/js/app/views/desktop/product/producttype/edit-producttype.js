define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/product/producttype/edit-producttype.html'
], function (utilities, config, formUtilities, entities_strings, ProductTypeEditTemplate) {
	
	
    var ProductTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(producttype)
                    {
                        utilities.applyTemplate($(self.el), ProductTypeEditTemplate,  
                            {model:this.model, producttype:producttype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductTypeEditTemplate,  
                    {model:this.model, producttype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-producttype-form':'editProductType'
            
        },
        editProductType: function(event)
        {
            event.preventDefault();
            var producttype = $(event.currentTarget).serializeObject();
            this.model.save(producttype, { 
                'success': function ()
                {
                    utilities.navigate('list-producttype');
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

    return ProductTypeEditView;
});

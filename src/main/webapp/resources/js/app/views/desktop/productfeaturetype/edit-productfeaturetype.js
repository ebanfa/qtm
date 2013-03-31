define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/productfeaturetype/edit-productfeaturetype.html'
], function (utilities, config, formUtilities, entities_strings, ProductFeatureTypeEditTemplate) {
	
	
    var ProductFeatureTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productfeaturetype)
                    {
                        utilities.applyTemplate($(self.el), ProductFeatureTypeEditTemplate,  
                            {model:this.model, productfeaturetype:productfeaturetype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductFeatureTypeEditTemplate,  
                    {model:this.model, productfeaturetype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productfeaturetype-form':'editProductFeatureType'
            
        },
        editProductFeatureType: function(event)
        {
            event.preventDefault();
            var productfeaturetype = $(event.currentTarget).serializeObject();
            this.model.save(productfeaturetype, { 
                'success': function ()
                {
                    utilities.navigate('list-productfeaturetype');
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

    return ProductFeatureTypeEditView;
});

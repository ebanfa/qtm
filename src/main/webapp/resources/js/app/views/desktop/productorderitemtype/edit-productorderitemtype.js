define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/productorderitemtype/edit-productorderitemtype.html'
], function (utilities, config, formUtilities, entities_strings, ProductOrderItemTypeEditTemplate) {
	
	
    var ProductOrderItemTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productorderitemtype)
                    {
                        utilities.applyTemplate($(self.el), ProductOrderItemTypeEditTemplate,  
                            {model:this.model, productorderitemtype:productorderitemtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductOrderItemTypeEditTemplate,  
                    {model:this.model, productorderitemtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productorderitemtype-form':'editProductOrderItemType'
            
        },
        editProductOrderItemType: function(event)
        {
            event.preventDefault();
            var productorderitemtype = $(event.currentTarget).serializeObject();
            this.model.save(productorderitemtype, { 
                'success': function ()
                {
                    utilities.navigate('list-productorderitemtype');
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

    return ProductOrderItemTypeEditView;
});

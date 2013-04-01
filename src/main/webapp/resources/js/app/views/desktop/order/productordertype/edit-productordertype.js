define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/order/productordertype/edit-productordertype.html'
], function (utilities, config, formUtilities, entities_strings, ProductOrderTypeEditTemplate) {
	
	
    var ProductOrderTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productordertype)
                    {
                        utilities.applyTemplate($(self.el), ProductOrderTypeEditTemplate,  
                            {model:this.model, productordertype:productordertype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductOrderTypeEditTemplate,  
                    {model:this.model, productordertype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productordertype-form':'editProductOrderType'
            
        },
        editProductOrderType: function(event)
        {
            event.preventDefault();
            var productordertype = $(event.currentTarget).serializeObject();
            this.model.save(productordertype, { 
                'success': function ()
                {
                    utilities.navigate('list-productordertype');
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

    return ProductOrderTypeEditView;
});

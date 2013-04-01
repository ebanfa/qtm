define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/invoice/invoiceitemcategory/edit-invoiceitemcategory.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceItemCategoryEditTemplate) {
	
	
    var InvoiceItemCategoryEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoiceitemcategory)
                    {
                        utilities.applyTemplate($(self.el), InvoiceItemCategoryEditTemplate,  
                            {model:this.model, invoiceitemcategory:invoiceitemcategory, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceItemCategoryEditTemplate,  
                    {model:this.model, invoiceitemcategory:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoiceitemcategory-form':'editInvoiceItemCategory'
            
        },
        editInvoiceItemCategory: function(event)
        {
            event.preventDefault();
            var invoiceitemcategory = $(event.currentTarget).serializeObject();
            this.model.save(invoiceitemcategory, { 
                'success': function ()
                {
                    utilities.navigate('list-invoiceitemcategory');
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

    return InvoiceItemCategoryEditView;
});

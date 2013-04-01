define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/invoice/invoiceitemtype/edit-invoiceitemtype.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceItemTypeEditTemplate) {
	
	
    var InvoiceItemTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoiceitemtype)
                    {
                        utilities.applyTemplate($(self.el), InvoiceItemTypeEditTemplate,  
                            {model:this.model, invoiceitemtype:invoiceitemtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceItemTypeEditTemplate,  
                    {model:this.model, invoiceitemtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoiceitemtype-form':'editInvoiceItemType'
            
        },
        editInvoiceItemType: function(event)
        {
            event.preventDefault();
            var invoiceitemtype = $(event.currentTarget).serializeObject();
            this.model.save(invoiceitemtype, { 
                'success': function ()
                {
                    utilities.navigate('list-invoiceitemtype');
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

    return InvoiceItemTypeEditView;
});

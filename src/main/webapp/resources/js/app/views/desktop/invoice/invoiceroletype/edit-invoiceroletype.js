define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/invoice/invoiceroletype/edit-invoiceroletype.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceRoleTypeEditTemplate) {
	
	
    var InvoiceRoleTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoiceroletype)
                    {
                        utilities.applyTemplate($(self.el), InvoiceRoleTypeEditTemplate,  
                            {model:this.model, invoiceroletype:invoiceroletype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceRoleTypeEditTemplate,  
                    {model:this.model, invoiceroletype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoiceroletype-form':'editInvoiceRoleType'
            
        },
        editInvoiceRoleType: function(event)
        {
            event.preventDefault();
            var invoiceroletype = $(event.currentTarget).serializeObject();
            this.model.save(invoiceroletype, { 
                'success': function ()
                {
                    utilities.navigate('list-invoiceroletype');
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

    return InvoiceRoleTypeEditView;
});

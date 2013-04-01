define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/invoice/invoicestatustype/edit-invoicestatustype.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceStatusTypeEditTemplate) {
	
	
    var InvoiceStatusTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoicestatustype)
                    {
                        utilities.applyTemplate($(self.el), InvoiceStatusTypeEditTemplate,  
                            {model:this.model, invoicestatustype:invoicestatustype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceStatusTypeEditTemplate,  
                    {model:this.model, invoicestatustype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoicestatustype-form':'editInvoiceStatusType'
            
        },
        editInvoiceStatusType: function(event)
        {
            event.preventDefault();
            var invoicestatustype = $(event.currentTarget).serializeObject();
            this.model.save(invoicestatustype, { 
                'success': function ()
                {
                    utilities.navigate('list-invoicestatustype');
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

    return InvoiceStatusTypeEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/invoicetype/edit-invoicetype.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceTypeEditTemplate) {
	
	
    var InvoiceTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoicetype)
                    {
                        utilities.applyTemplate($(self.el), InvoiceTypeEditTemplate,  
                            {model:this.model, invoicetype:invoicetype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceTypeEditTemplate,  
                    {model:this.model, invoicetype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoicetype-form':'editInvoiceType'
            
        },
        editInvoiceType: function(event)
        {
            event.preventDefault();
            var invoicetype = $(event.currentTarget).serializeObject();
            this.model.save(invoicetype, { 
                'success': function ()
                {
                    utilities.navigate('list-invoicetype');
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

    return InvoiceTypeEditView;
});

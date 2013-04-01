define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/invoice/invoicestatustype/invoicestatustype',
    'app/collections/invoice/invoice/invoice',
    'text!../../../../../../templates/desktop/invoice/invoicestatustype/invoicestatustype-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoice/invoice-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoicestatus/edit-invoicestatus.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceStatusTypes, Invoices, invoiceStatusTypeListSubViewTemplate, invoiceListSubViewTemplate, InvoiceStatusEditTemplate) {
	
    var InvoiceStatusTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceStatusTypeSelectContainerDiv'), invoiceStatusTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceStatusType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceStatusTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceStatusTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceStatusTypeSelectContainerDiv'), invoiceStatusTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceStatusType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var InvoiceListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  {model:self.model, relatedFieldName:"invoice", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoicesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoicesFetch.done(function (){
                utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  {model:self.model, relatedFieldName:"invoice", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var InvoiceStatusEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoicestatus)
                    {
                        utilities.applyTemplate($(self.el), InvoiceStatusEditTemplate,  
                            {model:this.model, invoicestatus:invoicestatus, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceStatusEditTemplate,  
                    {model:this.model, invoicestatus:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoicestatus-form':'editInvoiceStatus'
            
        },
        editInvoiceStatus: function(event)
        {
            event.preventDefault();
            var invoicestatus = $(event.currentTarget).serializeObject();
            this.model.save(invoicestatus, { 
                'success': function ()
                {
                    utilities.navigate('list-invoicestatus');
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
		    	this.invoiceStatusTypeId = this.model.attributes.invoiceStatusType
		    	this.invoiceId = this.model.attributes.invoice
            }
            // InvoiceStatusTypes
            var invoiceStatusTypes = new InvoiceStatusTypes();
            invoiceStatusTypeListSubView = new InvoiceStatusTypeListSubView({model:invoiceStatusTypes, el:$('#invoiceStatusTypeSelectContainerDiv'), selectedOption:this.invoiceStatusTypeId});
            invoiceStatusTypeListSubView.render();
            // Invoices
            var invoices = new Invoices();
            invoiceListSubView = new InvoiceListSubView({model:invoices, el:$('#invoiceSelectContainerDiv'), selectedOption:this.invoiceId});
            invoiceListSubView.render();
        }
    });

    return InvoiceStatusEditView;
});

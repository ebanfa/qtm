define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/invoice/invoicestatustype/invoicestatustype',
    'app/collections/invoice/invoice/invoice',
    'text!../../../../../../templates/desktop/invoice/invoicestatustype/invoicestatustype-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoice/invoice-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoicestatus/edit-invoicestatus.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceStatusTypes, Invoices, invoiceStatusTypeListSubViewTemplate, invoiceListSubViewTemplate, InvoiceStatusEditTemplate) {
	
    var InvoiceStatusTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceStatusTypeSelectContainerDiv'), invoiceStatusTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceStatusTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceStatusTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceStatusTypeSelectContainerDiv'), invoiceStatusTypeListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"invoiceStatusType", 
            	fieldName:entities_strings.invoicestatustype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var InvoiceListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoicesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoicesFetch.done(function (){
                utilities.applyTemplate($('#invoiceSelectContainerDiv'), invoiceListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"invoice", 
            	fieldName:entities_strings.invoice, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var InvoiceStatusEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceStatusEditTemplate;
        },
        events:
        {
            'submit #edit-invoicestatus-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoicestatus');
        },
        renderSubViews:function()
        {
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

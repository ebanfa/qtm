define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/invoice/invoiceroletype/invoiceroletype',
    'app/collections/party/party/party',
    'app/collections/invoice/invoice/invoice',
    'text!../../../../../../templates/desktop/invoice/invoiceroletype/invoiceroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoice/invoice-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoicerole/edit-invoicerole.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceRoleTypes, Partys, Invoices, invoiceRoleTypeListSubViewTemplate, partyListSubViewTemplate, invoiceListSubViewTemplate, InvoiceRoleEditTemplate) {
	
    var InvoiceRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceRoleTypeSelectContainerDiv'), invoiceRoleTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceRoleTypeSelectContainerDiv'), invoiceRoleTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"invoiceRoleType", 
            	fieldName:entities_strings.invoiceroletype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"party", 
            	fieldName:entities_strings.party, 
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
    
	
    var InvoiceRoleEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceRoleEditTemplate;
        },
        events:
        {
            'submit #edit-invoicerole-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoicerole');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.invoiceRoleTypeId = this.model.attributes.invoiceRoleType
		    	this.partyId = this.model.attributes.party
		    	this.invoiceId = this.model.attributes.invoice
            }
            // InvoiceRoleTypes
            var invoiceRoleTypes = new InvoiceRoleTypes();
            invoiceRoleTypeListSubView = new InvoiceRoleTypeListSubView({model:invoiceRoleTypes, el:$('#invoiceRoleTypeSelectContainerDiv'), selectedOption:this.invoiceRoleTypeId});
            invoiceRoleTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // Invoices
            var invoices = new Invoices();
            invoiceListSubView = new InvoiceListSubView({model:invoices, el:$('#invoiceSelectContainerDiv'), selectedOption:this.invoiceId});
            invoiceListSubView.render();
        }
    });

    return InvoiceRoleEditView;
});

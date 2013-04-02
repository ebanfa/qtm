define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/invoice/invoicetype/invoicetype',
    'app/collections/party/party/party',
    'app/collections/party/contactmechanism/contactmechanism',
    'app/collections/party/party/party',
    'app/collections/invoice/invoiceterm/invoiceterm',
    'text!../../../../../../templates/desktop/invoice/invoicetype/invoicetype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoiceterm/invoiceterm-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoice/edit-invoice.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, InvoiceTypes, Partys, ContactMechanisms, Partys, InvoiceTerms, invoiceTypeListSubViewTemplate, partyListSubViewTemplate, contactMechanismListSubViewTemplate, partyListSubViewTemplate, invoiceTermListSubViewTemplate, InvoiceEditTemplate) {
	
    var InvoiceTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceTypeSelectContainerDiv'), invoiceTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceTypeSelectContainerDiv'), invoiceTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"invoiceType", 
            	fieldName:entities_strings.invoicetype, 
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
            	relatedFieldName:"partyByToPartyId", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"contactMechanism", 
            	fieldName:entities_strings.contactmechanism, 
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
            	relatedFieldName:"partyByFromPartyId", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var InvoiceTermListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceTermSelectContainerDiv'), invoiceTermListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var invoiceTermsFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceTermsFetch.done(function (){
                utilities.applyTemplate($('#invoiceTermSelectContainerDiv'), invoiceTermListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"invoiceTerm", 
            	fieldName:entities_strings.invoiceterm, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var InvoiceEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceEditTemplate;
        },
        events:
        {
            'submit #edit-invoice-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoice');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.invoiceTypeId = this.model.attributes.invoiceType
		    	this.partyId = this.model.attributes.party
		    	this.contactMechanismId = this.model.attributes.contactMechanism
		    	this.partyId = this.model.attributes.party
		    	this.invoiceTermId = this.model.attributes.invoiceTerm
            }
            // InvoiceTypes
            var invoiceTypes = new InvoiceTypes();
            invoiceTypeListSubView = new InvoiceTypeListSubView({model:invoiceTypes, el:$('#invoiceTypeSelectContainerDiv'), selectedOption:this.invoiceTypeId});
            invoiceTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // InvoiceTerms
            var invoiceTerms = new InvoiceTerms();
            invoiceTermListSubView = new InvoiceTermListSubView({model:invoiceTerms, el:$('#invoiceTermSelectContainerDiv'), selectedOption:this.invoiceTermId});
            invoiceTermListSubView.render();
        }
    });

    return InvoiceEditView;
});

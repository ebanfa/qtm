define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
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
], function (utilities, config, formUtilities, entities_strings, InvoiceTypes, Partys, ContactMechanisms, Partys, InvoiceTerms, invoiceTypeListSubViewTemplate, partyListSubViewTemplate, contactMechanismListSubViewTemplate, partyListSubViewTemplate, invoiceTermListSubViewTemplate, InvoiceEditTemplate) {
	
    var InvoiceTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceTypeSelectContainerDiv'), invoiceTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceTypeSelectContainerDiv'), invoiceTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByToPartyId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByToPartyId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByFromPartyId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByFromPartyId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var InvoiceTermListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceTermSelectContainerDiv'), invoiceTermListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceTerm", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceTermsFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceTermsFetch.done(function (){
                utilities.applyTemplate($('#invoiceTermSelectContainerDiv'), invoiceTermListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceTerm", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var InvoiceEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoice)
                    {
                        utilities.applyTemplate($(self.el), InvoiceEditTemplate,  
                            {model:this.model, invoice:invoice, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceEditTemplate,  
                    {model:this.model, invoice:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoice-form':'editInvoice'
            
        },
        editInvoice: function(event)
        {
            event.preventDefault();
            var invoice = $(event.currentTarget).serializeObject();
            this.model.save(invoice, { 
                'success': function ()
                {
                    utilities.navigate('list-invoice');
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

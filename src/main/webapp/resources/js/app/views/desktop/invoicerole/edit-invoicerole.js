define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/invoiceroletype/invoiceroletype',
    'app/collections/party/party',
    'app/collections/invoice/invoice',
    'text!../../../../../templates/desktop/invoiceroletype/invoiceroletype-list-subview.html',
    'text!../../../../../templates/desktop/party/party-list-subview.html',
    'text!../../../../../templates/desktop/invoice/invoice-list-subview.html',
    'text!../../../../../templates/desktop/invoicerole/edit-invoicerole.html'
], function (utilities, config, formUtilities, entities_strings, InvoiceRoleTypes, Partys, Invoices, invoiceRoleTypeListSubViewTemplate, partyListSubViewTemplate, invoiceListSubViewTemplate, InvoiceRoleEditTemplate) {
	
    var InvoiceRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#invoiceRoleTypeSelectContainerDiv'), invoiceRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceRoleType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var invoiceRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            invoiceRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#invoiceRoleTypeSelectContainerDiv'), invoiceRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"invoiceRoleType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
    
	
    var InvoiceRoleEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoicerole)
                    {
                        utilities.applyTemplate($(self.el), InvoiceRoleEditTemplate,  
                            {model:this.model, invoicerole:invoicerole, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceRoleEditTemplate,  
                    {model:this.model, invoicerole:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoicerole-form':'editInvoiceRole'
            
        },
        editInvoiceRole: function(event)
        {
            event.preventDefault();
            var invoicerole = $(event.currentTarget).serializeObject();
            this.model.save(invoicerole, { 
                'success': function ()
                {
                    utilities.navigate('list-invoicerole');
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

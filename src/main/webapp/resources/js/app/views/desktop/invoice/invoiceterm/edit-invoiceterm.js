define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/businessdata/termtype/termtype',
    'text!../../../../../../templates/desktop/businessdata/termtype/termtype-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoiceterm/edit-invoiceterm.html'
], function (utilities, config, formUtilities, entities_strings, TermTypes, termTypeListSubViewTemplate, InvoiceTermEditTemplate) {
	
    var TermTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#termTypeSelectContainerDiv'), termTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"termType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var termTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            termTypesFetch.done(function (){
                utilities.applyTemplate($('#termTypeSelectContainerDiv'), termTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"termType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var InvoiceTermEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(invoiceterm)
                    {
                        utilities.applyTemplate($(self.el), InvoiceTermEditTemplate,  
                            {model:this.model, invoiceterm:invoiceterm, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), InvoiceTermEditTemplate,  
                    {model:this.model, invoiceterm:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-invoiceterm-form':'editInvoiceTerm'
            
        },
        editInvoiceTerm: function(event)
        {
            event.preventDefault();
            var invoiceterm = $(event.currentTarget).serializeObject();
            this.model.save(invoiceterm, { 
                'success': function ()
                {
                    utilities.navigate('list-invoiceterm');
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
		    	this.termTypeId = this.model.attributes.termType
            }
            // TermTypes
            var termTypes = new TermTypes();
            termTypeListSubView = new TermTypeListSubView({model:termTypes, el:$('#termTypeSelectContainerDiv'), selectedOption:this.termTypeId});
            termTypeListSubView.render();
        }
    });

    return InvoiceTermEditView;
});

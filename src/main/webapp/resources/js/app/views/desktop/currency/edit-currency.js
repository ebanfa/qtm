define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/currency/edit-currency.html'
], function (utilities, config, formUtilities, entities_strings, CurrencyEditTemplate) {
	
	
    var CurrencyEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(currency)
                    {
                        utilities.applyTemplate($(self.el), CurrencyEditTemplate,  
                            {model:this.model, currency:currency, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CurrencyEditTemplate,  
                    {model:this.model, currency:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-currency-form':'editCurrency'
            
        },
        editCurrency: function(event)
        {
            event.preventDefault();
            var currency = $(event.currentTarget).serializeObject();
            this.model.save(currency, { 
                'success': function ()
                {
                    utilities.navigate('list-currency');
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

    return CurrencyEditView;
});

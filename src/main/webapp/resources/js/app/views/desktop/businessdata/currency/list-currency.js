define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/currency/list-currency.html'
], function (
    utilities,
    entities_strings,
    currencysTemplate) {

    var CurrencysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), currencysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-currency-search':'handleCurrencySearch',
            'click #show-currency-search-dialog':'showCurrencySearchDialog',
            'click #hide-currency-dialog':'hideCurrencySearchDialog'
            
        },
        showCurrencySearchDialog: function(event)
        {
            event.preventDefault();
            $('#currency-search-dialog').modal('show');
            
        },
        handleCurrencySearch: function(event)
        {
            event.preventDefault();
            $('#currency-search-dialog').modal('hide');
            var currencyCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: currencyCode} });
            
        },
        hideCurrencySearchDialog: function(event)
        {
            event.preventDefault();
            $('#currency-search-dialog').modal('hide');
            
        }
    });

    return CurrencysView;
});
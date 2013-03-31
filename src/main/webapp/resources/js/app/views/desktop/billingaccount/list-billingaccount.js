define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/billingaccount/list-billingaccount.html'
], function (
    utilities,
    entities_strings,
    billingAccountsTemplate) {

    var BillingAccountsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), billingAccountsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-billingaccount-search':'handleBillingAccountSearch',
            'click #show-billingaccount-search-dialog':'showBillingAccountSearchDialog',
            'click #hide-billingaccount-dialog':'hideBillingAccountSearchDialog'
            
        },
        showBillingAccountSearchDialog: function(event)
        {
            event.preventDefault();
            $('#billingaccount-search-dialog').modal('show');
            
        },
        handleBillingAccountSearch: function(event)
        {
            event.preventDefault();
            $('#billingaccount-search-dialog').modal('hide');
            var billingAccountCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: billingAccountCode} });
            
        },
        hideBillingAccountSearchDialog: function(event)
        {
            event.preventDefault();
            $('#billingaccount-search-dialog').modal('hide');
            
        }
    });

    return BillingAccountsView;
});
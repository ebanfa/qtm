define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/customer/list-customer.html'
], function (
    utilities,
    entities_strings,
    customersTemplate) {

    var CustomersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), customersTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-customer-search':'handleCustomerSearch',
            'click #show-customer-search-dialog':'showCustomerSearchDialog',
            'click #hide-customer-dialog':'hideCustomerSearchDialog'
            
        },
        showCustomerSearchDialog: function(event)
        {
            event.preventDefault();
            $('#customer-search-dialog').modal('show');
            
        },
        handleCustomerSearch: function(event)
        {
            event.preventDefault();
            $('#customer-search-dialog').modal('hide');
            var customerCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: customerCode} });
            
        },
        hideCustomerSearchDialog: function(event)
        {
            event.preventDefault();
            $('#customer-search-dialog').modal('hide');
            
        }
    });

    return CustomersView;
});
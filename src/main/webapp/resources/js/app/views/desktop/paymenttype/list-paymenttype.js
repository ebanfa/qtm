define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/paymenttype/list-paymenttype.html'
], function (
    utilities,
    entities_strings,
    paymentTypesTemplate) {

    var PaymentTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), paymentTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-paymenttype-search':'handlePaymentTypeSearch',
            'click #show-paymenttype-search-dialog':'showPaymentTypeSearchDialog',
            'click #hide-paymenttype-dialog':'hidePaymentTypeSearchDialog'
            
        },
        showPaymentTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymenttype-search-dialog').modal('show');
            
        },
        handlePaymentTypeSearch: function(event)
        {
            event.preventDefault();
            $('#paymenttype-search-dialog').modal('hide');
            var paymentTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: paymentTypeCode} });
            
        },
        hidePaymentTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#paymenttype-search-dialog').modal('hide');
            
        }
    });

    return PaymentTypesView;
});
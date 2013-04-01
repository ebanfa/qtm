define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/customer/billingaccountroletype/list-billingaccountroletype.html'
], function (
    utilities,
    entities_strings,
    billingAccountRoleTypesTemplate) {

    var BillingAccountRoleTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), billingAccountRoleTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-billingaccountroletype-search':'handleBillingAccountRoleTypeSearch',
            'click #show-billingaccountroletype-search-dialog':'showBillingAccountRoleTypeSearchDialog',
            'click #hide-billingaccountroletype-dialog':'hideBillingAccountRoleTypeSearchDialog'
            
        },
        showBillingAccountRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#billingaccountroletype-search-dialog').modal('show');
            
        },
        handleBillingAccountRoleTypeSearch: function(event)
        {
            event.preventDefault();
            $('#billingaccountroletype-search-dialog').modal('hide');
            var billingAccountRoleTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: billingAccountRoleTypeCode} });
            
        },
        hideBillingAccountRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#billingaccountroletype-search-dialog').modal('hide');
            
        }
    });

    return BillingAccountRoleTypesView;
});
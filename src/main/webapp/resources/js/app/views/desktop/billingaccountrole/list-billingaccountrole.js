define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/billingaccountrole/list-billingaccountrole.html'
], function (
    utilities,
    entities_strings,
    billingAccountRolesTemplate) {

    var BillingAccountRolesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), billingAccountRolesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-billingaccountrole-search':'handleBillingAccountRoleSearch',
            'click #show-billingaccountrole-search-dialog':'showBillingAccountRoleSearchDialog',
            'click #hide-billingaccountrole-dialog':'hideBillingAccountRoleSearchDialog'
            
        },
        showBillingAccountRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#billingaccountrole-search-dialog').modal('show');
            
        },
        handleBillingAccountRoleSearch: function(event)
        {
            event.preventDefault();
            $('#billingaccountrole-search-dialog').modal('hide');
            var billingAccountRoleCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: billingAccountRoleCode} });
            
        },
        hideBillingAccountRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#billingaccountrole-search-dialog').modal('hide');
            
        }
    });

    return BillingAccountRolesView;
});
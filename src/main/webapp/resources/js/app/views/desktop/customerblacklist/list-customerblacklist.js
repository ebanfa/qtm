define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/customerblacklist/list-customerblacklist.html'
], function (
    utilities,
    entities_strings,
    customerBlacklistsTemplate) {

    var CustomerBlacklistsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), customerBlacklistsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-customerblacklist-search':'handleCustomerBlacklistSearch',
            'click #show-customerblacklist-search-dialog':'showCustomerBlacklistSearchDialog',
            'click #hide-customerblacklist-dialog':'hideCustomerBlacklistSearchDialog'
            
        },
        showCustomerBlacklistSearchDialog: function(event)
        {
            event.preventDefault();
            $('#customerblacklist-search-dialog').modal('show');
            
        },
        handleCustomerBlacklistSearch: function(event)
        {
            event.preventDefault();
            $('#customerblacklist-search-dialog').modal('hide');
            var customerBlacklistCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: customerBlacklistCode} });
            
        },
        hideCustomerBlacklistSearchDialog: function(event)
        {
            event.preventDefault();
            $('#customerblacklist-search-dialog').modal('hide');
            
        }
    });

    return CustomerBlacklistsView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/servicetransaction/list-servicetransaction.html'
], function (
    utilities,
    entities_strings,
    serviceTransactionsTemplate) {

    var ServiceTransactionsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceTransactionsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicetransaction-search':'handleServiceTransactionSearch',
            'click #show-servicetransaction-search-dialog':'showServiceTransactionSearchDialog',
            'click #hide-servicetransaction-dialog':'hideServiceTransactionSearchDialog'
            
        },
        showServiceTransactionSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicetransaction-search-dialog').modal('show');
            
        },
        handleServiceTransactionSearch: function(event)
        {
            event.preventDefault();
            $('#servicetransaction-search-dialog').modal('hide');
            var serviceTransactionCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceTransactionCode} });
            
        },
        hideServiceTransactionSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicetransaction-search-dialog').modal('hide');
            
        }
    });

    return ServiceTransactionsView;
});
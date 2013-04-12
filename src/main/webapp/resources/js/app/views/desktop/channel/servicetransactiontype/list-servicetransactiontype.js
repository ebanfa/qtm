define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/servicetransactiontype/list-servicetransactiontype.html'
], function (
    utilities,
    entities_strings,
    serviceTransactionTypesTemplate) {

    var ServiceTransactionTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceTransactionTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicetransactiontype-search':'handleServiceTransactionTypeSearch',
            'click #show-servicetransactiontype-search-dialog':'showServiceTransactionTypeSearchDialog',
            'click #hide-servicetransactiontype-dialog':'hideServiceTransactionTypeSearchDialog'
            
        },
        showServiceTransactionTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicetransactiontype-search-dialog').modal('show');
            
        },
        handleServiceTransactionTypeSearch: function(event)
        {
            event.preventDefault();
            $('#servicetransactiontype-search-dialog').modal('hide');
            var serviceTransactionTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceTransactionTypeCode} });
            
        },
        hideServiceTransactionTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicetransactiontype-search-dialog').modal('hide');
            
        }
    });

    return ServiceTransactionTypesView;
});
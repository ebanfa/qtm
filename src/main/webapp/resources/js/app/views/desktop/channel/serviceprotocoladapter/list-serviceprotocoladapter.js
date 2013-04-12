define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/serviceprotocoladapter/list-serviceprotocoladapter.html'
], function (
    utilities,
    entities_strings,
    serviceProtocolAdaptersTemplate) {

    var ServiceProtocolAdaptersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceProtocolAdaptersTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-serviceprotocoladapter-search':'handleServiceProtocolAdapterSearch',
            'click #show-serviceprotocoladapter-search-dialog':'showServiceProtocolAdapterSearchDialog',
            'click #hide-serviceprotocoladapter-dialog':'hideServiceProtocolAdapterSearchDialog'
            
        },
        showServiceProtocolAdapterSearchDialog: function(event)
        {
            event.preventDefault();
            $('#serviceprotocoladapter-search-dialog').modal('show');
            
        },
        handleServiceProtocolAdapterSearch: function(event)
        {
            event.preventDefault();
            $('#serviceprotocoladapter-search-dialog').modal('hide');
            var serviceProtocolAdapterCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceProtocolAdapterCode} });
            
        },
        hideServiceProtocolAdapterSearchDialog: function(event)
        {
            event.preventDefault();
            $('#serviceprotocoladapter-search-dialog').modal('hide');
            
        }
    });

    return ServiceProtocolAdaptersView;
});
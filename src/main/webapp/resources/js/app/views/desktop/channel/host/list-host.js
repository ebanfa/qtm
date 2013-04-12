define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/host/list-host.html'
], function (
    utilities,
    entities_strings,
    hostsTemplate) {

    var HostsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), hostsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-host-search':'handleHostSearch',
            'click #show-host-search-dialog':'showHostSearchDialog',
            'click #hide-host-dialog':'hideHostSearchDialog'
            
        },
        showHostSearchDialog: function(event)
        {
            event.preventDefault();
            $('#host-search-dialog').modal('show');
            
        },
        handleHostSearch: function(event)
        {
            event.preventDefault();
            $('#host-search-dialog').modal('hide');
            var hostCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: hostCode} });
            
        },
        hideHostSearchDialog: function(event)
        {
            event.preventDefault();
            $('#host-search-dialog').modal('hide');
            
        }
    });

    return HostsView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/hosttype/list-hosttype.html'
], function (
    utilities,
    entities_strings,
    hostTypesTemplate) {

    var HostTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), hostTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-hosttype-search':'handleHostTypeSearch',
            'click #show-hosttype-search-dialog':'showHostTypeSearchDialog',
            'click #hide-hosttype-dialog':'hideHostTypeSearchDialog'
            
        },
        showHostTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#hosttype-search-dialog').modal('show');
            
        },
        handleHostTypeSearch: function(event)
        {
            event.preventDefault();
            $('#hosttype-search-dialog').modal('hide');
            var hostTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: hostTypeCode} });
            
        },
        hideHostTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#hosttype-search-dialog').modal('hide');
            
        }
    });

    return HostTypesView;
});
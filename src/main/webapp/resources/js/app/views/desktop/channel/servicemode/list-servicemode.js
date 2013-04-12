define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/servicemode/list-servicemode.html'
], function (
    utilities,
    entities_strings,
    serviceModesTemplate) {

    var ServiceModesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceModesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicemode-search':'handleServiceModeSearch',
            'click #show-servicemode-search-dialog':'showServiceModeSearchDialog',
            'click #hide-servicemode-dialog':'hideServiceModeSearchDialog'
            
        },
        showServiceModeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicemode-search-dialog').modal('show');
            
        },
        handleServiceModeSearch: function(event)
        {
            event.preventDefault();
            $('#servicemode-search-dialog').modal('hide');
            var serviceModeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceModeCode} });
            
        },
        hideServiceModeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicemode-search-dialog').modal('hide');
            
        }
    });

    return ServiceModesView;
});
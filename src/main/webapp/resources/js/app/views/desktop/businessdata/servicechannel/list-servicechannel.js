define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/servicechannel/list-servicechannel.html'
], function (
    utilities,
    entities_strings,
    serviceChannelsTemplate) {

    var ServiceChannelsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceChannelsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicechannel-search':'handleServiceChannelSearch',
            'click #show-servicechannel-search-dialog':'showServiceChannelSearchDialog',
            'click #hide-servicechannel-dialog':'hideServiceChannelSearchDialog'
            
        },
        showServiceChannelSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicechannel-search-dialog').modal('show');
            
        },
        handleServiceChannelSearch: function(event)
        {
            event.preventDefault();
            $('#servicechannel-search-dialog').modal('hide');
            var serviceChannelCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceChannelCode} });
            
        },
        hideServiceChannelSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicechannel-search-dialog').modal('hide');
            
        }
    });

    return ServiceChannelsView;
});
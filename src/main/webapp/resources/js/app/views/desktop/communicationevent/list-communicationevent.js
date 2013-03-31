define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/communicationevent/list-communicationevent.html'
], function (
    utilities,
    entities_strings,
    communicationEventsTemplate) {

    var CommunicationEventsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), communicationEventsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-communicationevent-search':'handleCommunicationEventSearch',
            'click #show-communicationevent-search-dialog':'showCommunicationEventSearchDialog',
            'click #hide-communicationevent-dialog':'hideCommunicationEventSearchDialog'
            
        },
        showCommunicationEventSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationevent-search-dialog').modal('show');
            
        },
        handleCommunicationEventSearch: function(event)
        {
            event.preventDefault();
            $('#communicationevent-search-dialog').modal('hide');
            var communicationEventCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: communicationEventCode} });
            
        },
        hideCommunicationEventSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationevent-search-dialog').modal('hide');
            
        }
    });

    return CommunicationEventsView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/messaging/communicationeventpurpose/list-communicationeventpurpose.html'
], function (
    utilities,
    entities_strings,
    communicationEventPurposesTemplate) {

    var CommunicationEventPurposesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), communicationEventPurposesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-communicationeventpurpose-search':'handleCommunicationEventPurposeSearch',
            'click #show-communicationeventpurpose-search-dialog':'showCommunicationEventPurposeSearchDialog',
            'click #hide-communicationeventpurpose-dialog':'hideCommunicationEventPurposeSearchDialog'
            
        },
        showCommunicationEventPurposeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventpurpose-search-dialog').modal('show');
            
        },
        handleCommunicationEventPurposeSearch: function(event)
        {
            event.preventDefault();
            $('#communicationeventpurpose-search-dialog').modal('hide');
            var communicationEventPurposeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: communicationEventPurposeCode} });
            
        },
        hideCommunicationEventPurposeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventpurpose-search-dialog').modal('hide');
            
        }
    });

    return CommunicationEventPurposesView;
});
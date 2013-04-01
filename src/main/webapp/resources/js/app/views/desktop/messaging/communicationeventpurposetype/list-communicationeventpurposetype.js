define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/messaging/communicationeventpurposetype/list-communicationeventpurposetype.html'
], function (
    utilities,
    entities_strings,
    communicationEventPurposeTypesTemplate) {

    var CommunicationEventPurposeTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), communicationEventPurposeTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-communicationeventpurposetype-search':'handleCommunicationEventPurposeTypeSearch',
            'click #show-communicationeventpurposetype-search-dialog':'showCommunicationEventPurposeTypeSearchDialog',
            'click #hide-communicationeventpurposetype-dialog':'hideCommunicationEventPurposeTypeSearchDialog'
            
        },
        showCommunicationEventPurposeTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventpurposetype-search-dialog').modal('show');
            
        },
        handleCommunicationEventPurposeTypeSearch: function(event)
        {
            event.preventDefault();
            $('#communicationeventpurposetype-search-dialog').modal('hide');
            var communicationEventPurposeTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: communicationEventPurposeTypeCode} });
            
        },
        hideCommunicationEventPurposeTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventpurposetype-search-dialog').modal('hide');
            
        }
    });

    return CommunicationEventPurposeTypesView;
});
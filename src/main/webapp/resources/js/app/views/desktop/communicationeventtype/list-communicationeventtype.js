define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/communicationeventtype/list-communicationeventtype.html'
], function (
    utilities,
    entities_strings,
    communicationEventTypesTemplate) {

    var CommunicationEventTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), communicationEventTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-communicationeventtype-search':'handleCommunicationEventTypeSearch',
            'click #show-communicationeventtype-search-dialog':'showCommunicationEventTypeSearchDialog',
            'click #hide-communicationeventtype-dialog':'hideCommunicationEventTypeSearchDialog'
            
        },
        showCommunicationEventTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventtype-search-dialog').modal('show');
            
        },
        handleCommunicationEventTypeSearch: function(event)
        {
            event.preventDefault();
            $('#communicationeventtype-search-dialog').modal('hide');
            var communicationEventTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: communicationEventTypeCode} });
            
        },
        hideCommunicationEventTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventtype-search-dialog').modal('hide');
            
        }
    });

    return CommunicationEventTypesView;
});
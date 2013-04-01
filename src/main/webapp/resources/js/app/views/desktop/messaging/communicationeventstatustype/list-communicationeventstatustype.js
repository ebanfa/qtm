define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/messaging/communicationeventstatustype/list-communicationeventstatustype.html'
], function (
    utilities,
    entities_strings,
    communicationEventStatusTypesTemplate) {

    var CommunicationEventStatusTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), communicationEventStatusTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-communicationeventstatustype-search':'handleCommunicationEventStatusTypeSearch',
            'click #show-communicationeventstatustype-search-dialog':'showCommunicationEventStatusTypeSearchDialog',
            'click #hide-communicationeventstatustype-dialog':'hideCommunicationEventStatusTypeSearchDialog'
            
        },
        showCommunicationEventStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventstatustype-search-dialog').modal('show');
            
        },
        handleCommunicationEventStatusTypeSearch: function(event)
        {
            event.preventDefault();
            $('#communicationeventstatustype-search-dialog').modal('hide');
            var communicationEventStatusTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: communicationEventStatusTypeCode} });
            
        },
        hideCommunicationEventStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventstatustype-search-dialog').modal('hide');
            
        }
    });

    return CommunicationEventStatusTypesView;
});
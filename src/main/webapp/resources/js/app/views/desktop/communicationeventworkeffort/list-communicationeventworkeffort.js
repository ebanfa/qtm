define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/communicationeventworkeffort/list-communicationeventworkeffort.html'
], function (
    utilities,
    entities_strings,
    communicationEventWorkEffortsTemplate) {

    var CommunicationEventWorkEffortsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), communicationEventWorkEffortsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-communicationeventworkeffort-search':'handleCommunicationEventWorkEffortSearch',
            'click #show-communicationeventworkeffort-search-dialog':'showCommunicationEventWorkEffortSearchDialog',
            'click #hide-communicationeventworkeffort-dialog':'hideCommunicationEventWorkEffortSearchDialog'
            
        },
        showCommunicationEventWorkEffortSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventworkeffort-search-dialog').modal('show');
            
        },
        handleCommunicationEventWorkEffortSearch: function(event)
        {
            event.preventDefault();
            $('#communicationeventworkeffort-search-dialog').modal('hide');
            var communicationEventWorkEffortCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: communicationEventWorkEffortCode} });
            
        },
        hideCommunicationEventWorkEffortSearchDialog: function(event)
        {
            event.preventDefault();
            $('#communicationeventworkeffort-search-dialog').modal('hide');
            
        }
    });

    return CommunicationEventWorkEffortsView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/partyclassification/list-partyclassification.html'
], function (
    utilities,
    entities_strings,
    partyClassificationsTemplate) {

    var PartyClassificationsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyClassificationsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partyclassification-search':'handlePartyClassificationSearch',
            'click #show-partyclassification-search-dialog':'showPartyClassificationSearchDialog',
            'click #hide-partyclassification-dialog':'hidePartyClassificationSearchDialog'
            
        },
        showPartyClassificationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyclassification-search-dialog').modal('show');
            
        },
        handlePartyClassificationSearch: function(event)
        {
            event.preventDefault();
            $('#partyclassification-search-dialog').modal('hide');
            var partyClassificationCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyClassificationCode} });
            
        },
        hidePartyClassificationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyclassification-search-dialog').modal('hide');
            
        }
    });

    return PartyClassificationsView;
});
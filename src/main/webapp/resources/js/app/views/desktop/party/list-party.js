define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/party/list-party.html'
], function (
    utilities,
    entities_strings,
    partysTemplate) {

    var PartysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-party-search':'handlePartySearch',
            'click #show-party-search-dialog':'showPartySearchDialog',
            'click #hide-party-dialog':'hidePartySearchDialog'
            
        },
        showPartySearchDialog: function(event)
        {
            event.preventDefault();
            $('#party-search-dialog').modal('show');
            
        },
        handlePartySearch: function(event)
        {
            event.preventDefault();
            $('#party-search-dialog').modal('hide');
            var partyCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyCode} });
            
        },
        hidePartySearchDialog: function(event)
        {
            event.preventDefault();
            $('#party-search-dialog').modal('hide');
            
        }
    });

    return PartysView;
});
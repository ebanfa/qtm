define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/partytype/list-partytype.html'
], function (
    utilities,
    entities_strings,
    partyTypesTemplate) {

    var PartyTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partytype-search':'handlePartyTypeSearch',
            'click #show-partytype-search-dialog':'showPartyTypeSearchDialog',
            'click #hide-partytype-dialog':'hidePartyTypeSearchDialog'
            
        },
        showPartyTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partytype-search-dialog').modal('show');
            
        },
        handlePartyTypeSearch: function(event)
        {
            event.preventDefault();
            $('#partytype-search-dialog').modal('hide');
            var partyTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyTypeCode} });
            
        },
        hidePartyTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partytype-search-dialog').modal('hide');
            
        }
    });

    return PartyTypesView;
});
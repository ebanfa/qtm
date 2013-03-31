define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/partyclassificationtype/list-partyclassificationtype.html'
], function (
    utilities,
    entities_strings,
    partyClassificationTypesTemplate) {

    var PartyClassificationTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyClassificationTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partyclassificationtype-search':'handlePartyClassificationTypeSearch',
            'click #show-partyclassificationtype-search-dialog':'showPartyClassificationTypeSearchDialog',
            'click #hide-partyclassificationtype-dialog':'hidePartyClassificationTypeSearchDialog'
            
        },
        showPartyClassificationTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyclassificationtype-search-dialog').modal('show');
            
        },
        handlePartyClassificationTypeSearch: function(event)
        {
            event.preventDefault();
            $('#partyclassificationtype-search-dialog').modal('hide');
            var partyClassificationTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyClassificationTypeCode} });
            
        },
        hidePartyClassificationTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyclassificationtype-search-dialog').modal('hide');
            
        }
    });

    return PartyClassificationTypesView;
});
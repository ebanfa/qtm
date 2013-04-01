define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/partyrelationshiptype/list-partyrelationshiptype.html'
], function (
    utilities,
    entities_strings,
    partyRelationshipTypesTemplate) {

    var PartyRelationshipTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyRelationshipTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partyrelationshiptype-search':'handlePartyRelationshipTypeSearch',
            'click #show-partyrelationshiptype-search-dialog':'showPartyRelationshipTypeSearchDialog',
            'click #hide-partyrelationshiptype-dialog':'hidePartyRelationshipTypeSearchDialog'
            
        },
        showPartyRelationshipTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyrelationshiptype-search-dialog').modal('show');
            
        },
        handlePartyRelationshipTypeSearch: function(event)
        {
            event.preventDefault();
            $('#partyrelationshiptype-search-dialog').modal('hide');
            var partyRelationshipTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyRelationshipTypeCode} });
            
        },
        hidePartyRelationshipTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyrelationshiptype-search-dialog').modal('hide');
            
        }
    });

    return PartyRelationshipTypesView;
});
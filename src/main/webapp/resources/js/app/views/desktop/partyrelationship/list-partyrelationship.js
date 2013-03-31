define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/partyrelationship/list-partyrelationship.html'
], function (
    utilities,
    entities_strings,
    partyRelationshipsTemplate) {

    var PartyRelationshipsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyRelationshipsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partyrelationship-search':'handlePartyRelationshipSearch',
            'click #show-partyrelationship-search-dialog':'showPartyRelationshipSearchDialog',
            'click #hide-partyrelationship-dialog':'hidePartyRelationshipSearchDialog'
            
        },
        showPartyRelationshipSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyrelationship-search-dialog').modal('show');
            
        },
        handlePartyRelationshipSearch: function(event)
        {
            event.preventDefault();
            $('#partyrelationship-search-dialog').modal('hide');
            var partyRelationshipCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyRelationshipCode} });
            
        },
        hidePartyRelationshipSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyrelationship-search-dialog').modal('hide');
            
        }
    });

    return PartyRelationshipsView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/partycontactmechanismpurpose/list-partycontactmechanismpurpose.html'
], function (
    utilities,
    entities_strings,
    partyContactMechanismPurposesTemplate) {

    var PartyContactMechanismPurposesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyContactMechanismPurposesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partycontactmechanismpurpose-search':'handlePartyContactMechanismPurposeSearch',
            'click #show-partycontactmechanismpurpose-search-dialog':'showPartyContactMechanismPurposeSearchDialog',
            'click #hide-partycontactmechanismpurpose-dialog':'hidePartyContactMechanismPurposeSearchDialog'
            
        },
        showPartyContactMechanismPurposeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partycontactmechanismpurpose-search-dialog').modal('show');
            
        },
        handlePartyContactMechanismPurposeSearch: function(event)
        {
            event.preventDefault();
            $('#partycontactmechanismpurpose-search-dialog').modal('hide');
            var partyContactMechanismPurposeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyContactMechanismPurposeCode} });
            
        },
        hidePartyContactMechanismPurposeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partycontactmechanismpurpose-search-dialog').modal('hide');
            
        }
    });

    return PartyContactMechanismPurposesView;
});
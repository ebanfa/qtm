define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/partycontactmechanism/list-partycontactmechanism.html'
], function (
    utilities,
    entities_strings,
    partyContactMechanismsTemplate) {

    var PartyContactMechanismsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyContactMechanismsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partycontactmechanism-search':'handlePartyContactMechanismSearch',
            'click #show-partycontactmechanism-search-dialog':'showPartyContactMechanismSearchDialog',
            'click #hide-partycontactmechanism-dialog':'hidePartyContactMechanismSearchDialog'
            
        },
        showPartyContactMechanismSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partycontactmechanism-search-dialog').modal('show');
            
        },
        handlePartyContactMechanismSearch: function(event)
        {
            event.preventDefault();
            $('#partycontactmechanism-search-dialog').modal('hide');
            var partyContactMechanismCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyContactMechanismCode} });
            
        },
        hidePartyContactMechanismSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partycontactmechanism-search-dialog').modal('hide');
            
        }
    });

    return PartyContactMechanismsView;
});
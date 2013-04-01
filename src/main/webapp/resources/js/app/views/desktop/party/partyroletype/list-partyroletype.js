define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/partyroletype/list-partyroletype.html'
], function (
    utilities,
    entities_strings,
    partyRoleTypesTemplate) {

    var PartyRoleTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyRoleTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partyroletype-search':'handlePartyRoleTypeSearch',
            'click #show-partyroletype-search-dialog':'showPartyRoleTypeSearchDialog',
            'click #hide-partyroletype-dialog':'hidePartyRoleTypeSearchDialog'
            
        },
        showPartyRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyroletype-search-dialog').modal('show');
            
        },
        handlePartyRoleTypeSearch: function(event)
        {
            event.preventDefault();
            $('#partyroletype-search-dialog').modal('hide');
            var partyRoleTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyRoleTypeCode} });
            
        },
        hidePartyRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyroletype-search-dialog').modal('hide');
            
        }
    });

    return PartyRoleTypesView;
});
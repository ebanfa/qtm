define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/partyrole/list-partyrole.html'
], function (
    utilities,
    entities_strings,
    partyRolesTemplate) {

    var PartyRolesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyRolesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partyrole-search':'handlePartyRoleSearch',
            'click #show-partyrole-search-dialog':'showPartyRoleSearchDialog',
            'click #hide-partyrole-dialog':'hidePartyRoleSearchDialog'
            
        },
        showPartyRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyrole-search-dialog').modal('show');
            
        },
        handlePartyRoleSearch: function(event)
        {
            event.preventDefault();
            $('#partyrole-search-dialog').modal('hide');
            var partyRoleCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyRoleCode} });
            
        },
        hidePartyRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partyrole-search-dialog').modal('hide');
            
        }
    });

    return PartyRolesView;
});
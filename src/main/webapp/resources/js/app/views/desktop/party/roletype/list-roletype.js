define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/roletype/list-roletype.html'
], function (
    utilities,
    entities_strings,
    roleTypesTemplate) {

    var RoleTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), roleTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-roletype-search':'handleRoleTypeSearch',
            'click #show-roletype-search-dialog':'showRoleTypeSearchDialog',
            'click #hide-roletype-dialog':'hideRoleTypeSearchDialog'
            
        },
        showRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#roletype-search-dialog').modal('show');
            
        },
        handleRoleTypeSearch: function(event)
        {
            event.preventDefault();
            $('#roletype-search-dialog').modal('hide');
            var roleTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: roleTypeCode} });
            
        },
        hideRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#roletype-search-dialog').modal('hide');
            
        }
    });

    return RoleTypesView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/organization/list-organization.html'
], function (
    utilities,
    entities_strings,
    organizationsTemplate) {

    var OrganizationsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), organizationsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-organization-search':'handleOrganizationSearch',
            'click #show-organization-search-dialog':'showOrganizationSearchDialog',
            'click #hide-organization-dialog':'hideOrganizationSearchDialog'
            
        },
        showOrganizationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#organization-search-dialog').modal('show');
            
        },
        handleOrganizationSearch: function(event)
        {
            event.preventDefault();
            $('#organization-search-dialog').modal('hide');
            var organizationCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: organizationCode} });
            
        },
        hideOrganizationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#organization-search-dialog').modal('hide');
            
        }
    });

    return OrganizationsView;
});
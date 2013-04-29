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
            $("#delete-organization-button").hide();
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-organization-search':'handleOrganizationSearch',
            'click #show-organization-search-dialog':'showOrganizationSearchDialog',
            'click #hide-organization-dialog':'hideOrganizationSearchDialog',
            'click #table-header-checkbox':'checkAllItems',
            'click .delete-organization-checkbox':'showDeleteOrganizationButton',
            'click #delete-organization-button':'deleteSelectedOrganizations'
            
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
            
        },
        checkAllItems: function(event)
        {
            console.log('Was designed to do it');
            var headerChecked = $("#table-header-checkbox");
            if (headerChecked.is(':checked'))
            {
                $('.delete-organization-checkbox').prop('checked', true);
            }
            else
            {
                $('.delete-organization-checkbox').prop('checked', false);
            }
            this.showDeleteOrganizationButton(event);
        },
        showDeleteOrganizationButton: function(event)
        {
            var showDeleteButton = false;
            $(".delete-organization-checkbox").each(function(index) 
            {
                if ($(this).is(':checked'))
                {
                    showDeleteButton = true;
                }
            });
            if (showDeleteButton)
            {
                $("#delete-organization-button").show();
            }
            else
            {
                $("#delete-organization-button").hide();
            }
        },
        deleteSelectedOrganizations: function(event)
        {
            event.preventDefault();
            var ids = [];
            $('.delete-organization-checkbox:checked').each( function(index)
            {
                var modelId = $(this).val();
                ids.push(modelId);
            });
            this.model.deleteByIds(ids);
        }
    });

    return OrganizationsView;
});
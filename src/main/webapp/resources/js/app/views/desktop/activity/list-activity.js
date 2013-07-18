define([
    'utilities',
    'i18n!app/nls/entities',
    'app/util/formUtilities',
    'text!../../../../../templates/desktop/activity/list-activity.html'
], function (
    utilities,
    entities_strings, 
    formUtilities, 
    activityListTemplate) {

    var ActivityListView = Backbone.View.extend({
        initialize: function()
        {
            var self = this;
            this.model.bind("reset",
                function () {
                    utilities.viewManager.showView(self);
            }).fetch();
        },
        render:function () 
        { 
            var listData = {};

            if(this.model.length > 0)
            {
                listData = this.model.at(0).attributes;
            }
            else
            {
                listData.data = [];
            }
            utilities.applyTemplate($(this.el), activityListTemplate,  {model:listData, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            $("#delete-activity-button").hide();
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #create-activity-button':'showCreatePage',
            'click #search-activity-button':'showSearchDialog',
            'click #delete-activity-button':'deleteSelectedItems',

            'click #do-activity-search':'handleActivitySearch',
            'click #hide-activity-dialog':'hideActivitySearchDialog',
            'click #table-header-checkbox':'checkAllItems',
            'click .delete-activity-checkbox':'showDeleteActivityButton'
            
        },
        showCreatePage: function(event)
        {
            event.preventDefault();
            var activityListURL = "edit/" + this.model.activityURL;
            utilities.navigate(activityListURL);
        },
        showSearchDialog: function(event)
        {
            event.preventDefault();
            $('#activity-search-dialog').modal('show');
        },
        deleteSelectedItems: function(event)
        {
            event.preventDefault();
            var ids = [];
            $('.delete-activity-checkbox:checked').each( function(index)
            {
                var modelId = $(this).val();
                ids.push(modelId);
            });
            this.model.deleteByIds(ids);
            this.navigateToActivityList();
        },
        handleActivitySearch: function(event)
        {
            event.preventDefault();
            $('#activity-search-dialog').modal('hide');
            var activityCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: activityCode} });
            
        },
        hideActivitySearchDialog: function(event)
        {
            event.preventDefault();
            $('#activity-search-dialog').modal('hide');
            
        },
        checkAllItems: function(event)
        {
            console.log('Was designed to do it');
            var headerChecked = $("#table-header-checkbox");
            if (headerChecked.is(':checked'))
            {
                $('.delete-activity-checkbox').prop('checked', true);
            }
            else
            {
                $('.delete-activity-checkbox').prop('checked', false);
            }
            this.showDeleteActivityButton(event);
        },
        showDeleteActivityButton: function(event)
        {
            var showDeleteButton = false;
            $(".delete-activity-checkbox").each(function(index) 
            {
                if ($(this).is(':checked'))
                {
                    showDeleteButton = true;
                }
            });
            if (showDeleteButton)
            {
                $("#delete-activity-button").show();
            }
            else
            {
                $("#delete-activity-button").hide();
            }
        },
        navigateToActivityList:function()
        {
            this.render();
        }
    });

    return ActivityListView;
});
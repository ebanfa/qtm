define([
    'utilities',
    'configuration',
    'app/util/ajax-utilities',
    'app/util/formUtilities',
    'app/util/dialog-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/activity/list-activity',
    'app/views/desktop/activity/entity-search',
    'text!../../../../../../templates/desktop/activity/transaction/list-activity.html'
], function (utilities, config, ajaxUtil, formUtilities, 
    dialogUtilities, entities_strings, ActivityListView, EntitySearchDialogView, activityListTemplate) {
	
    var TransactionListView = ActivityListView.extend({
    
       /*
        * Additional initialization activities
        */
        initializeImpl: function(options)
        {
            var self = this;
            this.entitySearchDialogView = null;
            // Replace the parent template with ours
            this.activityListTemplate = activityListTemplate;
        },
        renderAdditional: function()
        {
            $("#auto-match-activity-button").hide();
            $("#manual-match-activity-button").hide();
        },
       /*
        * Do a related entity search
        */
        doDefaultSearchImpl:function(event)
        {
            var buttonClicked = $(event.currentTarget);
            if(buttonClicked.attr("id") == "do-related-entity-search-btn")
                this.entitySearchDialogView.doSearch(event);
        },
       /*
        * Show the auto match and the manual match buttons
        */
        showListItemSelectedButtonsImpl: function(idsOfSelectedItems)
        {
            $("#auto-match-activity-button").show();
            if(idsOfSelectedItems.length == 1)
                $("#manual-match-activity-button").show();
        },
       /*
        * An item from the search result list has been selected
        */
        handleListItemSelectedImpl:function(event)
        {
            if (this.entitySearchDialogView != null) 
                this.entitySearchDialogView.selectEntity(event, this);
        },
       /*
        * Hide the auto match and the manual match buttons
        */
        hideListItemSelectedButtonsImpl: function()
        {
            $("#auto-match-activity-button").hide();
            $("#manual-match-activity-button").hide();
        },
       /*
        * Check if the list item button clicked was either the auto or manual match button
        * and process accordingly
        */
        handleListItemButtonClickedImpl:function(clickedListItemButton, idsOfSelectedItems)
        {
            if(clickedListItemButton.attr("id") == "auto-match-activity-button")
                this.doAutoMatch(idsOfSelectedItems);
            else
                this.doManualMatch(idsOfSelectedItems);
        },
        handleEntitySearchResultButtonClickedImpl:function(event)
        {

        },
        doAutoMatch: function(idsOfSelectedItems)
        {
            ajaxUtil.ajaxGET(this.activityURL + '/autoMatch', {idsOfTransactions:idsOfSelectedItems},
                this.onAutoMatchSuccessCallBack, this.onAutoMatchErrorCallBack);
            $(this.el).trigger('pagecreate');
        },
        doManualMatch: function(idsOfSelectedItems)
        {
            var modalEntityName = 'Advice';
            var modalEntityActivityURL = 'advice';
            // Advice is not related to message, but this hack should allows
            // to use the related entity search functionality
            var modalEntityFieldName = modalEntityActivityURL;
            // These properties will be accessed/used by the search view
            this.currentModalEntity = modalEntityName;
            this.currentModalField = modalEntityFieldName;
            // Create and render the search view
            this.entitySearchDialogView = 
                new EntitySearchDialogView({ 
                    parentView : this,
                    modalEntityName : modalEntityName,
                    activityURL : modalEntityActivityURL,
                    modalFieldName : modalEntityFieldName
                });
            this.name = 'EntitySearchDialogView';
            this.entitySearchDialogView.render();
        },
        onAutoMatchSuccessCallBack:function(data)
        {
            if(data.errorCode == 1)
                dialogUtilities.warn("Transacion matching completed with errors.\n" + data.errorMessage, $("#content-container"));
            else
                dialogUtilities.success("Transacion matching completed successfully.\n", $("#content-container"));
        },
        onAutoMatchErrorCallBack:function(request, status, error)
        {
            dialogUtilities.error("Error executing request. status: " + status +" Error: " + error, $("#content-container"));
        }
    });

    return TransactionListView;
});

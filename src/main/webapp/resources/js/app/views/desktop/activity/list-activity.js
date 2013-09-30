define([
    'utilities',
    'configuration',
    'i18n!app/nls/entities',
    'app/util/formUtilities',
    'app/views/desktop/activity/entity-search',
    'text!../../../../../templates/desktop/activity/list-activity.html'
], function (utilities, config, entities_strings, formUtilities, EntitySearchDialogView, activityListTemplate) {

    var ActivityListView = Backbone.View.extend({
        initialize: function(options)
        {
            this.name = 'ActivityListView';
            this.activityURL = this.model.activityURL;
            this.activityListTemplate = activityListTemplate;
            this.activityEditURL = "edit/" + this.model.activityURL;
            // Should we display the list item buttons ?
            this.showListItemButtons = false;
            // The entity record that was selected from the search results page
            this.selectedRelatedEntity = null;
            this.entityLite = null;
            // The entity we are opening the modal form for
            this.currentModalEntity = null;
            // The relationship field we are opening the modal form for.
            // This helps avoid confusion in the case were we have two 
            // relationship fields to the same target entity
            this.currentModalField = null;
            // Holds all the related entities records that have been selected
            // (ie if we search multiples times we keep each selected record here)
            this.selectedRelatedEntities = [];
            var self = this;
            this.model.bind("reset",
                function () {
                    utilities.viewManager.showView(self);
            }).fetch();
            this.initializeImpl(options);
        }, 
       /*
        * Subclasses will override to do additional initialization activities.
        */
        initializeImpl: function(options)
        {
        },
        render:function () 
        { 
            var listData = {};

            if(this.model.length > 0)
            {
                listData = this.model.at(0).attributes;
                console.log("List data is:" + listData);
                console.log("List data activityName:" + this.model.activityName);
            }
            else
            {
                listData.data = [];
            }
            utilities.applyTemplate($(this.el), this.activityListTemplate,  {model:listData, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            $(".list-item-btn").hide();
            this.renderAdditional();
            this.delegateEvents();
            return this;
        },
        events:
        {
            // Non list item buttons (Search, Create etc)
            'click .non-list-item-btn':'handleNonListItemButtonClicked',
            // List item related buttons (Delete)
            'click .list-item-btn':'handleListItemButtonClicked',
            // Search button clicked (does actual search)
            'click .search-btn':'handleSearchButtonClicked',
            // Button to close the entity search view (dialog)
            'click .cancel-search-btn':'cancelSearchButtonClicked',
            // Check box to select all entity search result items
            'click .select-all-list-items': 'selectAllListResultItems',
            // Search result item selected (radio button or check box)
            'click .entity-list-item':'handleListItemSelected',
            // Button to delete a search result item
            'click .delete-list-item-btn':'deleteEntityListItemButtonClicked',
            // When a search result is rendered, additional buttons may be rendered too.
            // This event is tiggered when any of such buttons is clicked
            'click .search-result-button' : 'handleEntitySearchResultButtonClicked'
        },
        renderAdditional: function()
        {

        },
       /*
        * A non list item button has been clicked
        */
        handleNonListItemButtonClicked: function(event)
        {
            event.preventDefault();
            var buttonClicked = $(event.currentTarget);
            if(buttonClicked.attr("id") == "show-create-view-btn")
                utilities.navigate(this.activityEditURL);
            else {

            	
                //$('#activity-search-dialog').modal('show');
            }

        	var modalEntityName = "Customer";
        	var modalEntityFieldName = "name";
        	var modalEntityActivityURL = "customer";

        	this.currentModalEntity = modalEntityName;
            this.currentModalField = modalEntityFieldName;
            this.entitySearchDialogView = 
                new EntitySearchDialogView({parentView:this, modalEntityName:modalEntityName, activityURL:modalEntityActivityURL, modalFieldName: modalEntityFieldName});
            this.entitySearchDialogView.render();

        },
       /*
        * Renders the entity create view
        */
        showEntityCreateView: function(event)
        {
            event.preventDefault();
            var activityListURL = "edit/" + this.model.activityURL;
            utilities.navigate(activityListURL);
        },
       /*
        * Renders the entity searc view (dialog)
        */
        showEntitySearchView: function(event)
        {
            event.preventDefault();
        },
       /*
        * Search button has been clicked
        */
        handleSearchButtonClicked:function(event)
        {
            event.preventDefault();
            var buttonClicked = $(event.currentTarget);
            if(buttonClicked.attr("id") == "default-entity-search-btn")
                this.doSearch();
            else
                this.doDefaultSearchImpl(event);
        },
       /*
        * Default search
        */
        doSearch:function()
        {
            $('#activity-search-dialog').modal('hide');
            var activityCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: activityCode} });
        },
       /*
        * Perform a customize search
        */
        doDefaultSearchImpl:function()
        {
           
        },
       /*
        * Cancel search button has been clicked.
        * Default behaviour is to close the search dialog.
        */
        cancelSearchButtonClicked:function(event){
            event.preventDefault();
            $('#activity-search-dialog').modal('hide');
        },
        deleteEntityListItemButtonClicked:function(event){},
       /*
        * This is called when a search result entry/item is clicked (checkbox or radio button)
        */
        handleListItemSelected: function(event)
        {
            if(this.name == 'ActivityListView')
            {
                // this will also set the showListItemButtons flag to true
                var idsOfSelectedItems = this.getSelectedListItems();
                if (this.showListItemButtons)
                    this.showListItemSelectedButtons(idsOfSelectedItems);
                else
                    this.hideListItemSelectedButtons();
            }
            else
               this.handleListItemSelectedImpl(event); 
        },
       /*
        * Show the list item buttons (e.g Delete)
        */
        showListItemSelectedButtons: function(idsOfSelectedItems)
        {
            $("#delete-list-item-btn").show();
            this.showListItemSelectedButtonsImpl(idsOfSelectedItems);
        },
       /*
        * Hide the list item buttons (e.g Delete)
        */
        hideListItemSelectedButtons: function(idsOfSelectedItems)
        {
            $("#delete-list-item-btn").hide();
            this.hideListItemSelectedButtonsImpl();
        },
       /*
        * Get the selected list items
        */
        getSelectedListItems: function()
        {
            var self = this;
            var idsOfSelectedItems = [];
            $(".entity-list-item").each(function(index) 
            {
                if ($(this).is(':checked'))
                {
                    idsOfSelectedItems.push($(this).val());
                    self.showListItemButtons = true;
                }
            });
            return idsOfSelectedItems;
        },
       /*
        * Trigger when entity list item button (e.g delete) is clicked
        */
        handleListItemButtonClicked: function(event)
        {
            event.preventDefault();
            var buttonClicked = $(event.currentTarget);
            var idsOfSelectedItems = [];
            $('.entity-list-item:checked').each( function(index)
            {
                var modelId = $(this).val();
                idsOfSelectedItems.push(modelId);
            });

            if(buttonClicked.attr("id") == "delete-list-item-btn")
            {
                this.model.deleteByIds(idsOfSelectedItems);
                this.navigateToActivityList();
            }
            else 
                this.handleListItemButtonClickedImpl($(event.currentTarget), idsOfSelectedItems);
        },
       /*
        * Search result based button has been clicked
        */
        handleEntitySearchResultButtonClicked:function(event)
        {
            event.preventDefault();
            handleEntitySearchResultButtonClickedImpl(event);
        },
       /*
        * Not current used in this class but functionallty is provided
        * for use by subclasses
        */
        handleEntitySearchResultButtonClickedImpl:function(event)
        {

        },
        handleListItemButtonClickedImpl:function(clickedListItemButton)
        {

        },
        hideActivitySearchDialog: function(event)
        {
            event.preventDefault();
            $('#activity-search-dialog').modal('hide');
        },
        addSelectedEntity: function(event)
        {
            this.entitySearchDialogView.addSelectedEntity(event);
        },
        addNextSelectedEntity: function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.addNextSelectedEntity(event);
        },
        selectAllListResultItems: function(event)
        {
            var headerChecked = $(".select-all-list-items");
            if (headerChecked.is(':checked'))
            {
                $('.entity-list-item').prop('checked', true);
            }
            else
            {
                $('.entity-list-item').prop('checked', false);
            }
            this.handleListItemSelected(event);
        },
        showListItemSelectedButtonsImpl: function(idsOfSelectedItems)
        {
            
        },
        hideListItemSelectedButtonsImpl: function()
        {
           
        },
        navigateToActivityList:function()
        {
            this.render();
        }
    });

    return ActivityListView;
});
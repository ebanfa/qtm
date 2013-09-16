/**
 * 
 * @author Edward Banfa
 */
define([
    'utilities',
    'configuration',
    'i18n!app/nls/entities',
    'app/util/formUtilities',
    'app/util/qui',
    'app/views/desktop/activity/entity-search',
    'text!../../../../../templates/desktop/activity/list-table.html',
    'text!../../../../../templates/desktop/activity/list-activity.html'
], function (utilities, config, entities_strings, formUtilities, QUI, EntitySearchDialogView, ListTableTemplate, ListActivityTemplate) {

    /**
     * This view is in charge of rendering the search
     * result.
     */
	var ListTableView = Backbone.View.extend({
		
		initialize: function(options)
        {
			this.listData = options.listData;
			this.listFields = options.listFields;
			this.linkURL = options.linkURL;
        },
        
	    /**
	     * Renders this view.
	     */
	    render: function () 
	    { 
	    	utilities.applyTemplate($('#list-table'), ListTableTemplate, 
	    	{
				linkURL : this.linkURL,
				listData : this.listData,
				listFields : this.listFields,
			});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
	    },
        reload: function(listData, listFields, linkURL){
        	this.linkURL = linkURL;
        	this.listData = listData;
        	this.listFields = listFields;
        	this.render();
        }
	});
	
    /**
     * The view for the list activity
     */
    var ListActivityView = Backbone.View.extend(
    {
        initialize: function(options)
        {
			var self = this;
			this.listTableView = null;
			this.activity = QUI.ListActivity({
				data : [],
				name : 'ActivityListView',
				baseURL : 'rest/',
				parentView : self,
				entityName : '',
				activityURL : self.model.activityURL,
				searchURLPrefix : 'search/',
				activityEditURL : "edit/" + self.model.activityURL,
				listActivityTemplate : ListActivityTemplate
			});
			this.model.bind("reset", function() {
				utilities.viewManager.showView(self);
			}).fetch();
			this.initializeImpl(options);
        }, 
        
       /**
		 * Subclasses will override to do additional initialization activities.
		 */
        initializeImpl: function(options)
        {
        },

        /**
         * Renders this view.
         */
        render:function () 
        { 
        	// Load the data from the model
			if (this.model.length > 0) {
				var data = this.model.at(0).attributes;
				this.activity.data = data;
				this.activity.entityName = data.displayNm;
			} else {
				this.activity.data = [];
			}
			console.log("West  side" + this.activity.entityName);
			// Render the template
			utilities.applyTemplate($(this.el),
					ListActivityTemplate, {
					model : this.activity.data,
					activity : this.activity,
					entities_strings : entities_strings
			});
			// Create and render the list table view
			this.listTableView = new ListTableView(
			{
				listData : this.activity.data.data,
				listFields : this.activity.data.fields,
				linkURL : this.activity.activityEditURL,
			});
			this.listTableView.render();

			$(this.el).trigger('pagecreate');
			$(".list-item-btn").hide();
			this.renderAdditional();
			this.delegateEvents();
			return this;
        },

        /**
		 * Event handler mapping
		 */
        events:
        {
            // Non list item buttons (Search, Create etc)
            'focus  .search-field':'handleSearchFieldFocusEvent',
            'click .non-list-item-btn':'handleNonListItemButtonClicked',
            'click  #do-related-entity-search-btn':'handleSearchButtonClicked',
        },

        /**
         * Subclasses will override to do additional rendering activities.
         */
        renderAdditional: function()
        {
        	console.log('Ouncezzz');
        },
        
       /**
        * A non list item button has been clicked.
        * Non list item buttons are those buttons that
        * are not dependent of the list of items we are (or we are not)
        * displaying. Examples are the Search and create buttons.
        * Subclasses should be able to add additional non list
        * item buttons and then override this function to
        * handle the event
        */
        handleNonListItemButtonClicked: function(event)
        {
            event.preventDefault();
            // Get the button that was clicked
            var buttonClicked = $(event.currentTarget);
            
            if(buttonClicked.attr("id") == "show-create-view-btn") {
            	this.showEntityCreateView(event);
            }
            else {
                this.showEntitySearchView(event);
            }
        },
        
       /**
        * Renders the entity create view.
        */
        showEntityCreateView: function(event)
        {
        	utilities.navigate(this.activity.activityEditURL);
        },
        
       /**
        * Renders the entity search view (dialog).
        * The default implementation uses the entity
        * search dialog to display the search form.
        */
        showEntitySearchView: function(event)
        {
			var self = this;
			console.log("Down south" + self.activity.entityName);
			this.entitySearchDialogView = new EntitySearchDialogView(
			{
				searchView : self,
				parentView : self,
				modalEntityName : self.activity.entityName,
				activityURL : self.activity.searchURLPrefix,
				modalFieldName : self.activity.activityURL
			});
			this.entitySearchDialogView.render();
        },
        
       /**
		 * Search button has been clicked. This initiates the search process.
		 */
        handleSearchButtonClicked:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.doSearch();
            
        },
        
       /**
        * Default search implementation.
        */
        doSearch:function()
        {
            this.entitySearchDialogView.closeSearchDialog();
            $.fn.formSerializer = formUtilities.formSerializer;
            var searchData = $('#search-form').formSerializer();
            var self = this;
            QUI.ajaxGET(this.activity.searchURLPrefix, searchData,
            			function(data){self.listTableView.render()}, this.onSearchFailureCallBack);
        },

        /**
         * Call back upon successfully completing search operation
         */
        onSearchSuccessCallBack: function (data)
        {
        	;
        },
        
        /**
         * Call back when an error was encountered during the search operation
         */
        onSearchFailureCallBack: function (data)
        {
        	console.log('We back there');
        },
        
        /**
         * Handles search input field focus event
         */
        handleSearchFieldFocusEvent: function(event){
        	// Hide other field options
        	$('.search_options').hide();
        	var fieldContainerElement = $(event.currentTarget).parent();
        	var fieldOptionsElement = fieldContainerElement.find('.search_options');
        	fieldOptionsElement.show();
        },
        
       /**
        * Closes the search dialog.
        */
        cancelSearchButtonClicked:function(event){
            event.preventDefault();
            $('#activity-search-dialog').modal('hide');
        },

       /**
        * Deletes an item from the search list.
        */
        deleteEntityListItemButtonClicked:function(event){},
        
       /**
        * This is called when a search result entry/item is clicked (checkbox or radio button)
        */
        handleListItemSelected: function(event)
        {
            
        },
        
       /**
        * Show the list item buttons (e.g Delete)
        */
        showListItemSelectedButtons: function(idsOfSelectedItems)
        {
           
        },
        
       /**
        * Hide the list item buttons (e.g Delete)
        */
        hideListItemSelectedButtons: function(idsOfSelectedItems)
        {
           
        },
        
       /**
        * Get the selected list items
        */
        getSelectedListItems: function()
        {
            
        },
        
       /**
        * Triggered when entity list item button (e.g delete) is clicked
        */
        handleListItemButtonClicked: function(event)
        {
            
        },
        
       /**
        * Search result based button has been clicked
        */
        handleEntitySearchResultButtonClicked:function(event)
        {
            
        },
        
       /**
        * Not current used in this class but functionality is provided
        * for use by subclasses
        */
        handleEntitySearchResultButtonClickedImpl:function(event)
        {

        },
        
       /**
        * Not current used in this class but functionality is provided
        * for use by subclasses
        */
        handleListItemButtonClickedImpl:function(clickedListItemButton)
        {
        },
        
       /**
        * Hide the search dialog
        */
        hideActivitySearchDialog: function(event)
        {
        },
        
       /**
        * Add the selected entity to
        * the list of currently selected entities
        */
        addSelectedEntity: function(event)
        {
        },

       /**
        * Add the selected entity to
        * the list of currently selected entities and
        * prepare to add another
        */
        addNextSelectedEntity: function(event)
        {
            
        },

       /**
        * Select all items in the list that is 
        * currently being displayed
        */
        selectAllListResultItems: function(event)
        {
            
        },

       /**
        * Not current used in this class but functionality is provided
        * for use by subclasses
        */
        showListItemSelectedButtonsImpl: function(idsOfSelectedItems)
        {
            
        },

       /**
        * Not current used in this class but functionality is provided
        * for use by subclasses
        */
        hideListItemSelectedButtonsImpl: function()
        {
           
        },

       /**
        * Not current used in this class but functionality is provided
        * for use by subclasses
        */
        navigateToActivityList:function()
        {
            this.render();
        }
    });

    return ListActivityView;
});
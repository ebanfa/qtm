define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'i18n!app/nls/entities',
    'app/views/desktop/activity/entity-search',
    'app/views/desktop/base/baseentityeditview',
    'app/views/desktop/activity/messaging/compose',
    'text!../../../../../templates/desktop/activity/edit-activity.html'
], function (utilities, config, formUtilities, entities_strings, EntitySearchDialogView, BaseEntityEditView, ComposeMsgView, ActivityEditTemplate) {
	
    var ActivityEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.activityTemplate = ActivityEditTemplate;
            this.name = 'ActivityViewView';
            this.activityURL = this.model.activityURL;
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
        },
        events:
        {
            'submit #edit-activity-form':'saveEntity',
            'click  #cancel-edit-activity-form':'cancelEdit',
            'click  .lookup_field':'showEntityLookupDialog',
            'click  #do-related-entity-search-btn':'doRelatedEntitySearch',
            'click  .entity-list-item':'selectEntity',
            'click  #add-selected-item-btn':'handleEntitySearchResultButtonClicked',
            'click  #add-next-selected-item-btn':'handleEntitySearchResultButtonClicked'
            
        },
        navigateToActivityList:function()
        {
            var activityListURL = "list/" + this.model.activityURL;
            utilities.navigate(activityListURL);
        },
        getComposeTemplate: function()
        {
            return MessageTempl;
        },
        renderAlternateView: function(form)
        {
            console.log("Alternate view rendered");
            composeTempl = new ComposeMsgView({el:$(this.el), form:form});
            composeTempl.render();
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	//this.partyId = this.model.attributes.party
            }
        },
        /*
         * Show the related entity search dialog
         */
        showEntityLookupDialog:function(event)
        {
        	event.preventDefault();
        	var fieldName = event.currentTarget.getAttribute("href");
        	var modalEntityName = null;;
        	var modalEntityFieldName = null;;
        	var modalEntityActivityURL = null;;
        	var fields = this.form.fields;
            for (var i=0; i<fields.length;i++)
            {
                if(fields[i].name == fieldName){
                	modalEntityFieldName = fields[i].name;
                	modalEntityName = fields[i].applicationRelatedEntityText;
                	modalEntityActivityURL = fields[i].applicationRelatedEntityText.toLowerCase();
                }
            }
            // Customer is not related to message, but this hack should allows
            // to use the related entity search functionality
            this.currentModalEntity = modalEntityName;
            this.currentModalField = modalEntityFieldName;
            this.entitySearchDialogView = 
                new EntitySearchDialogView({ 
                	parentView:this,
                    modalEntityName:modalEntityName,
                    activityURL:modalEntityActivityURL,
                    modalFieldName: modalEntityFieldName
                });
            this.entitySearchDialogView.render();
        },
        /*
         * Search button was clicked so we do the search
         */
        doRelatedEntitySearch:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.doSearch();
        },
        /*
         * Listen for when a search result entry has been selected
         * (radio button)
         */
        selectEntity:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.selectEntity(event, this);
        },
        handleEntitySearchResultButtonClicked:function(event)
        {
            event.preventDefault();
        	this.entitySearchDialogView.handleEntitySearchResultButtonClicked(event);
        },
        addCurrentEntity:function()
        {
        	// The entity that was selected from the search list
            var entityLite = this.selectedRelatedEntity.entityLite;
            // Get the relationship field that we are currently processing
            var fieldName = this.selectedRelatedEntity.fieldName;
            console.log(">>>>>>>>>>>>>>" + fieldName);
            // Build the selector for the field
            var fieldSelector = "#" + fieldName + "Id";
            console.log("fieldSelector>>>>>>>>>>>>>>" + fieldSelector);
            // Build the new value for the field 
           var newFieldValue = "<option value=" + entityLite.id + ">" + entityLite.code + "</option>";
           var relatedEntityField = $(fieldSelector);
           console.log("#########" + relatedEntityField.html());
           // Replace the html content of the field with newFieldValue
           relatedEntityField.html(newFieldValue);
        }
    });

    return ActivityEditView;
});

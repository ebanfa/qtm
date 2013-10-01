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
    'text!../../../../../templates/desktop/activity/view-activity.html'
], function (utilities, config, entities_strings, formUtilities, QUI, EntitySearchDialogView, ViewActivityTemplate) {

    /**
     * The view for the view activity
     */
    var ViewActivityView = Backbone.View.extend(
    {
        /**
         * 
         */
        initialize: function(options)
        {
			var self = this;
			this.form = {};
			this.listTableView = null;
			this.entitySearchDialogView = null;
			this.activity = QUI.ViewActivity({
				data : {},
				name : 'ActivityViewView',
				baseURL : 'rest/',
				parentView : self,
				entityName : '',
				activityURL : self.model.activityURL,
				searchURLPrefix : 'search/',
				activityEditURL : "edit/" + self.model.activityURL,
				viewActivityTemplate : ViewActivityTemplate
			});
        },
        events:
        {
            // Change related entity field value element clicked
            'click  .lookup_field':'showEntityLookupDialog',
        },

        /**
         * 
         */
        render:function ()  
        {
        	var self = this;
            // This will load the activity from the DB.
			this.model.fetch({ 
                success: function(activity) {
                    self.renderActivity(activity);
                },
                error: function(model, response, options) {
                    console.log(response);
                }
            });
            return this;
        },
        renderActivity:function(activity)
        {
        	//console.log("This is the activity " + JSON.stringify(activity, null, 4));
        	if(activity) {
        		this.activity = activity;
            	this.form = this.buildActivityForm(activity);
    		    utilities.applyTemplate($(this.el), ViewActivityTemplate, {form:this.form, entities_strings:entities_strings}); 
                $(this.el).trigger('pagecreate');
            	this.delegateEvents();
        	}
        },
        /**
         * 
         */
        buildActivityForm:function(activity)
        {
        	var formBuilder = formUtilities.formBuilder;
            var form = formBuilder(activity);
            return form;
        },
        
        /**
         * Show the related entity search dialog
         */
        showEntityLookupDialog:function(event)
        {
        	event.preventDefault();
        	var fieldName = event.currentTarget.getAttribute("href");
        	var modalEntityName = null;
        	var modalEntityFieldName = null;
        	var modalEntityActivityURL = null;
        	var fields = this.form.fields;
            for (var i=0; i<fields.length;i++)
            {
                if(fields[i].fieldName == fieldName){
                	modalEntityFieldName = fields[i].fieldName;
                	modalEntityName = fields[i].relatedBusinessObjectName;
                	modalEntityActivityURL = fields[i].relatedBusinessObjectName.toLowerCase() + "/";
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
                    activityURL:'activity/searchFields',
                    modalFieldName: modalEntityFieldName
                });
            this.entitySearchDialogView.render();
        },
        
        /**
         * Search button was clicked so we do the search
         */
        doRelatedEntitySearch:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.doSearch();
        },
        
        /**
         * Listen for when a search result entry has been selected
         * (radio button)
         */
        selectEntity:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.selectEntity(event, this);
        },
        
        /**
         * 
         */
        handleEntitySearchResultButtonClicked:function(event)
        {
            event.preventDefault();
        	this.entitySearchDialogView.handleEntitySearchResultButtonClicked(event);
        },
        
        /**
         * 
         */
        addCurrentEntity:function()
        {
        	// The entity that was selected from the search list
            var entityLite = this.selectedRelatedEntity.entityLite;
            // Get the relationship field that we are currently processing
            var fieldName = this.selectedRelatedEntity.fieldName;
            // Build the selector for the field
            var fieldSelector = "#" + fieldName + "Id";
            // Build the new value for the field 
           var newFieldValue = "<option value=" + entityLite.id + ">" + entityLite.code + "</option>";
           var relatedEntityField = $(fieldSelector);
           // Replace the html content of the field with newFieldValue
           relatedEntityField.html(newFieldValue);
        },
    });

    return ViewActivityView;
});
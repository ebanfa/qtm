define([
    'utilities',
    'configuration',
    'app/util/ajax-utilities',
    'app/util/formUtilities',
    'app/collections/activity/activity',
    'text!../../../../../templates/desktop/activity/entity-search.html',
    'text!../../../../../templates/desktop/activity/entity-search-result.html',
    'i18n!app/nls/entities'
], function (utilities, config, ajaxUtil, formUtil, ActivityCollection, EntitySearchTemplate, EntitySearchResultTemplate, entities_strings) {
    
    var EntitySearchView = Backbone.View.extend({
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.searchData = options.searchData;
            this.parentView = options.parentView;
            this.activityURL = options.activityURL;
        },
        render:function () 
        {   var self = this;
            this.model.bind("reset",
                function (model) {
                    var listData = {};
                    if(self.model.length > 0)
                        listData = self.model.at(0).attributes;
                    else
                        listData.data = [];
                    utilities.applyTemplate($('#search-result-div'), EntitySearchResultTemplate,  {model:listData});
                    $(self.el).trigger('pagecreate');
                    self.delegateEvents();
            }).fetch({ data: this.searchData});
            return this;
        },
        doSearch:function () 
        { 
        	this.render();
        },
        selectEntity:function(event)
        {
            // Properties of the selected record
            var recordId = null;
            var recordCode = null;
            var recordName = null;

            var parentTd = $(event.currentTarget).parent();
            var parentTr = null;
            if(parentTd != null)
                parentTr = parentTd.parent();
            if(parentTr != null)
            {
                var codeFieldCell = parentTr.find('.code-field-cell :first');
                if(codeFieldCell != null)
                {
                    recordCode = codeFieldCell.text().trim();
                    recordId = codeFieldCell.attr('href');
                }
                var codeFieldName = parentTr.find('.name-field-cell :first');
                if(codeFieldName != null)
                    recordName = codeFieldName.text().trim();
                $.fn.entityLite = formUtil.entityLite;
                var entityLite = $.fn.entityLite(recordId, recordCode, recordName, null);
                this.parentView.entityLite = entityLite;
                // Set up the current selected related entity (field name and entity lite)
                $.fn.createSelectedRelatedEntityInfo = formUtil.createSelectedRelatedEntityInfo;
                this.parentView.selectedRelatedEntity = 
                    $.fn.createSelectedRelatedEntityInfo(this.parentView.currentModalField, entityLite);
            }
        },
        addSelectedEntity:function(event)
        {
            this.addNextSelectedEntity(event);
            $('#entity-search-dialog').modal('hide');
        },
        addNextSelectedEntity:function(event)
        {
            if (this.parentView.entityLite != null) 
                this.parentView.addCurrentEntity();  
        }
    });
    
    var EntitySearchDialogView = Backbone.View.extend({
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.searchView = options.searchView;
            this.parentView = options.parentView;
            this.activityURL = options.activityURL;
            this.fieldName = options.modalFieldName;
            this.entityName = options.modalEntityName;
        },
        
       /**
        * Render function. This loads the search fields for the given entity
        */
        render: function () 
        {           
            var self = this;
            // do an ajax query to fetch the sarch fields of the entity
            ajaxUtil.ajaxGET(this.activityURL + 'searchFields', {entityName:this.entityName},
                this.onSearchFieldsSuccessCallBack, this.onSearchFieldsErrorCallBack);
            return this;
        },
        
       /**
        * Called if search fields are successfully loaded
        */
        onSearchFieldsSuccessCallBack: function(data)
        {

            var blockBuilder = formUtil.blockBuilder;
            var form = blockBuilder(data);
            utilities.applyTemplate($('#entity-search-dialog-div'), 
                EntitySearchTemplate,  {model:{entityName:this.entityName}, form:form, entities_strings:entities_strings});
            $('#entity-search-dialog').modal('show');
        	$('.search_options').hide();
        },
        
       /**
        * Called if there was an error while attempting to load search fields
        */
        onSearchFieldsErrorCallBack: function(error)
        {
        },
        
       /**
        * An item from the search result list has been selected
        */
        selectEntity:function(event, parentView)
        {
            // Delegate to the search view
            this.searchView.selectEntity(event, parentView);
        },
        
       /**
        * Called when the entity search result button has been clicked
        */
        handleEntitySearchResultButtonClicked: function( event)
        {
            var buttonClicked = $(event.currentTarget);
            if(buttonClicked.attr("id") == "add-selected-item-btn")
                this.searchView.addSelectedEntity(event);
            else
                this.searchView.addNextSelectedEntity(event);
        },
        
       /**
        * Calls the search view to perform the search and render the results
        */
        doSearch:function(event)
        {   
            var searchData = {name:'Bristol Text'};
            var modelToSearch = new ActivityCollection({activityURL:this.activityURL});
            if(this.searchView == null){
                this.searchView = new EntitySearchView({
                    model : modelToSearch, 
                    searchData : searchData, 
                    parentView : this.parentView
                });
            }
            this.searchView.doSearch();
        },
        closeSearchDialog: function(){
            $('#entity-search-dialog').modal('hide');
        }
    });
    return EntitySearchDialogView;
});

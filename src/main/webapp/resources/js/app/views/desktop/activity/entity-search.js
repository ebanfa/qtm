define([
    'utilities',
    'configuration',
    'app/util/ajax-utilities',
    'app/collections/activity/activity',
    'text!../../../../../templates/desktop/activity/entity-search.html',
    'text!../../../../../templates/desktop/activity/entity-search-result.html',
    'i18n!app/nls/entities'
], function (utilities, config, ajaxUtil, ActivityCollection, EntitySearchTemplate, EntitySearchResultTemplate, entities_strings) {
    
    var EntitySearchView = Backbone.View.extend({
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.searchData = options.searchData;
            this.activityURL = options.activityURL;
        },
        events : 
        {
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
        }
    });
    
    var EntitySearchDialogView = Backbone.View.extend({
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.activityURL = options.activityURL;
            this.fieldName = options.modalFieldName;
            this.entityName = options.modalEntityName;
        },
        render:function () 
        {           
            var self = this;
            // do an ajax query to fetch the sarch fields of the entity
            ajaxUtil.ajaxGET(this.activityURL + '/searchFields', {entityName:this.entityName},
                this.onSearchFieldsSuccessCallBack, this.onSearchFieldsErrorCallBack);
            return this;
        },
        onSearchFieldsSuccessCallBack: function(data)
        {
            console.log("Callback success from the mechanic:" + config);
            utilities.applyTemplate($('#entity-search-dialog-div'), EntitySearchTemplate,  {model:{}, fields:data, entities_strings:entities_strings});
            $('#entity-search-dialog').modal('show');
            //$(this.el).trigger('pagecreate');
            //this.delegateEvents();
            // get the search fields and display the template
        },
        onSearchFieldsErrorCallBack: function(error)
        {
            console.log("Callback error from the mechanic");
            // get the search fields and display the template
        },
        doSearch:function(event)
        {   
            console.log("Searching");
            var searchData = {name:'Bristol Text'};
            var modelToSearch = new ActivityCollection({activityURL:this.activityURL});
            var searchView = new EntitySearchView({model:modelToSearch, searchData:searchData});
            searchView.render();
        }
    });
    return EntitySearchDialogView;
});

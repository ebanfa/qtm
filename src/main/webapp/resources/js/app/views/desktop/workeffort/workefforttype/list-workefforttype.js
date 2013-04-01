define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/workeffort/workefforttype/list-workefforttype.html'
], function (
    utilities,
    entities_strings,
    workEffortTypesTemplate) {

    var WorkEffortTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), workEffortTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-workefforttype-search':'handleWorkEffortTypeSearch',
            'click #show-workefforttype-search-dialog':'showWorkEffortTypeSearchDialog',
            'click #hide-workefforttype-dialog':'hideWorkEffortTypeSearchDialog'
            
        },
        showWorkEffortTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#workefforttype-search-dialog').modal('show');
            
        },
        handleWorkEffortTypeSearch: function(event)
        {
            event.preventDefault();
            $('#workefforttype-search-dialog').modal('hide');
            var workEffortTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: workEffortTypeCode} });
            
        },
        hideWorkEffortTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#workefforttype-search-dialog').modal('hide');
            
        }
    });

    return WorkEffortTypesView;
});
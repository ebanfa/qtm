define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/workeffort/list-workeffort.html'
], function (
    utilities,
    entities_strings,
    workEffortsTemplate) {

    var WorkEffortsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), workEffortsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-workeffort-search':'handleWorkEffortSearch',
            'click #show-workeffort-search-dialog':'showWorkEffortSearchDialog',
            'click #hide-workeffort-dialog':'hideWorkEffortSearchDialog'
            
        },
        showWorkEffortSearchDialog: function(event)
        {
            event.preventDefault();
            $('#workeffort-search-dialog').modal('show');
            
        },
        handleWorkEffortSearch: function(event)
        {
            event.preventDefault();
            $('#workeffort-search-dialog').modal('hide');
            var workEffortCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: workEffortCode} });
            
        },
        hideWorkEffortSearchDialog: function(event)
        {
            event.preventDefault();
            $('#workeffort-search-dialog').modal('hide');
            
        }
    });

    return WorkEffortsView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/advice/advicestatus/list-advicestatus.html'
], function (
    utilities,
    entities_strings,
    adviceStatussTemplate) {

    var AdviceStatussView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), adviceStatussTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-advicestatus-search':'handleAdviceStatusSearch',
            'click #show-advicestatus-search-dialog':'showAdviceStatusSearchDialog',
            'click #hide-advicestatus-dialog':'hideAdviceStatusSearchDialog'
            
        },
        showAdviceStatusSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advicestatus-search-dialog').modal('show');
            
        },
        handleAdviceStatusSearch: function(event)
        {
            event.preventDefault();
            $('#advicestatus-search-dialog').modal('hide');
            var adviceStatusCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: adviceStatusCode} });
            
        },
        hideAdviceStatusSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advicestatus-search-dialog').modal('hide');
            
        }
    });

    return AdviceStatussView;
});
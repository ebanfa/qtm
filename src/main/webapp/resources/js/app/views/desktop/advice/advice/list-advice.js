define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/advice/advice/list-advice.html'
], function (
    utilities,
    entities_strings,
    advicesTemplate) {

    var AdvicesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), advicesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-advice-search':'handleAdviceSearch',
            'click #show-advice-search-dialog':'showAdviceSearchDialog',
            'click #hide-advice-dialog':'hideAdviceSearchDialog'
            
        },
        showAdviceSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advice-search-dialog').modal('show');
            
        },
        handleAdviceSearch: function(event)
        {
            event.preventDefault();
            $('#advice-search-dialog').modal('hide');
            var adviceCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: adviceCode} });
            
        },
        hideAdviceSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advice-search-dialog').modal('hide');
            
        }
    });

    return AdvicesView;
});
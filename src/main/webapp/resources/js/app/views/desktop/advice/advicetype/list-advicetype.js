define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/advice/advicetype/list-advicetype.html'
], function (
    utilities,
    entities_strings,
    adviceTypesTemplate) {

    var AdviceTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), adviceTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-advicetype-search':'handleAdviceTypeSearch',
            'click #show-advicetype-search-dialog':'showAdviceTypeSearchDialog',
            'click #hide-advicetype-dialog':'hideAdviceTypeSearchDialog'
            
        },
        showAdviceTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advicetype-search-dialog').modal('show');
            
        },
        handleAdviceTypeSearch: function(event)
        {
            event.preventDefault();
            $('#advicetype-search-dialog').modal('hide');
            var adviceTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: adviceTypeCode} });
            
        },
        hideAdviceTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advicetype-search-dialog').modal('hide');
            
        }
    });

    return AdviceTypesView;
});
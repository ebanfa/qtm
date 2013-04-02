define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/advice/advicetypetag/list-advicetypetag.html'
], function (
    utilities,
    entities_strings,
    adviceTypeTagsTemplate) {

    var AdviceTypeTagsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), adviceTypeTagsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-advicetypetag-search':'handleAdviceTypeTagSearch',
            'click #show-advicetypetag-search-dialog':'showAdviceTypeTagSearchDialog',
            'click #hide-advicetypetag-dialog':'hideAdviceTypeTagSearchDialog'
            
        },
        showAdviceTypeTagSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advicetypetag-search-dialog').modal('show');
            
        },
        handleAdviceTypeTagSearch: function(event)
        {
            event.preventDefault();
            $('#advicetypetag-search-dialog').modal('hide');
            var adviceTypeTagCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: adviceTypeTagCode} });
            
        },
        hideAdviceTypeTagSearchDialog: function(event)
        {
            event.preventDefault();
            $('#advicetypetag-search-dialog').modal('hide');
            
        }
    });

    return AdviceTypeTagsView;
});
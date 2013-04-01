define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/termtype/list-termtype.html'
], function (
    utilities,
    entities_strings,
    termTypesTemplate) {

    var TermTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), termTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-termtype-search':'handleTermTypeSearch',
            'click #show-termtype-search-dialog':'showTermTypeSearchDialog',
            'click #hide-termtype-dialog':'hideTermTypeSearchDialog'
            
        },
        showTermTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#termtype-search-dialog').modal('show');
            
        },
        handleTermTypeSearch: function(event)
        {
            event.preventDefault();
            $('#termtype-search-dialog').modal('hide');
            var termTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: termTypeCode} });
            
        },
        hideTermTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#termtype-search-dialog').modal('hide');
            
        }
    });

    return TermTypesView;
});
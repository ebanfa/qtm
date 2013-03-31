define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/partycase/list-partycase.html'
], function (
    utilities,
    entities_strings,
    partyCasesTemplate) {

    var PartyCasesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), partyCasesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-partycase-search':'handlePartyCaseSearch',
            'click #show-partycase-search-dialog':'showPartyCaseSearchDialog',
            'click #hide-partycase-dialog':'hidePartyCaseSearchDialog'
            
        },
        showPartyCaseSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partycase-search-dialog').modal('show');
            
        },
        handlePartyCaseSearch: function(event)
        {
            event.preventDefault();
            $('#partycase-search-dialog').modal('hide');
            var partyCaseCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: partyCaseCode} });
            
        },
        hidePartyCaseSearchDialog: function(event)
        {
            event.preventDefault();
            $('#partycase-search-dialog').modal('hide');
            
        }
    });

    return PartyCasesView;
});
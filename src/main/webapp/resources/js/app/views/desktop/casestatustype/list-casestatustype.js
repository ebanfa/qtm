define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/casestatustype/list-casestatustype.html'
], function (
    utilities,
    entities_strings,
    caseStatusTypesTemplate) {

    var CaseStatusTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), caseStatusTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-casestatustype-search':'handleCaseStatusTypeSearch',
            'click #show-casestatustype-search-dialog':'showCaseStatusTypeSearchDialog',
            'click #hide-casestatustype-dialog':'hideCaseStatusTypeSearchDialog'
            
        },
        showCaseStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#casestatustype-search-dialog').modal('show');
            
        },
        handleCaseStatusTypeSearch: function(event)
        {
            event.preventDefault();
            $('#casestatustype-search-dialog').modal('hide');
            var caseStatusTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: caseStatusTypeCode} });
            
        },
        hideCaseStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#casestatustype-search-dialog').modal('hide');
            
        }
    });

    return CaseStatusTypesView;
});
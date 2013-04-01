define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/caseroletype/list-caseroletype.html'
], function (
    utilities,
    entities_strings,
    caseRoleTypesTemplate) {

    var CaseRoleTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), caseRoleTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-caseroletype-search':'handleCaseRoleTypeSearch',
            'click #show-caseroletype-search-dialog':'showCaseRoleTypeSearchDialog',
            'click #hide-caseroletype-dialog':'hideCaseRoleTypeSearchDialog'
            
        },
        showCaseRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#caseroletype-search-dialog').modal('show');
            
        },
        handleCaseRoleTypeSearch: function(event)
        {
            event.preventDefault();
            $('#caseroletype-search-dialog').modal('hide');
            var caseRoleTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: caseRoleTypeCode} });
            
        },
        hideCaseRoleTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#caseroletype-search-dialog').modal('hide');
            
        }
    });

    return CaseRoleTypesView;
});
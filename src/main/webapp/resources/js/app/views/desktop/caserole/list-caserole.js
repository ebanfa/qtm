define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/caserole/list-caserole.html'
], function (
    utilities,
    entities_strings,
    caseRolesTemplate) {

    var CaseRolesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), caseRolesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-caserole-search':'handleCaseRoleSearch',
            'click #show-caserole-search-dialog':'showCaseRoleSearchDialog',
            'click #hide-caserole-dialog':'hideCaseRoleSearchDialog'
            
        },
        showCaseRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#caserole-search-dialog').modal('show');
            
        },
        handleCaseRoleSearch: function(event)
        {
            event.preventDefault();
            $('#caserole-search-dialog').modal('hide');
            var caseRoleCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: caseRoleCode} });
            
        },
        hideCaseRoleSearchDialog: function(event)
        {
            event.preventDefault();
            $('#caserole-search-dialog').modal('hide');
            
        }
    });

    return CaseRolesView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/statustype/list-statustype.html'
], function (
    utilities,
    entities_strings,
    statusTypesTemplate) {

    var StatusTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), statusTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-statustype-search':'handleStatusTypeSearch',
            'click #show-statustype-search-dialog':'showStatusTypeSearchDialog',
            'click #hide-statustype-dialog':'hideStatusTypeSearchDialog'
            
        },
        showStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#statustype-search-dialog').modal('show');
            
        },
        handleStatusTypeSearch: function(event)
        {
            event.preventDefault();
            $('#statustype-search-dialog').modal('hide');
            var statusTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: statusTypeCode} });
            
        },
        hideStatusTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#statustype-search-dialog').modal('hide');
            
        }
    });

    return StatusTypesView;
});
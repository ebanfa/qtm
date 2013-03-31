define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/uom/list-uom.html'
], function (
    utilities,
    entities_strings,
    uomsTemplate) {

    var UomsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), uomsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-uom-search':'handleUomSearch',
            'click #show-uom-search-dialog':'showUomSearchDialog',
            'click #hide-uom-dialog':'hideUomSearchDialog'
            
        },
        showUomSearchDialog: function(event)
        {
            event.preventDefault();
            $('#uom-search-dialog').modal('show');
            
        },
        handleUomSearch: function(event)
        {
            event.preventDefault();
            $('#uom-search-dialog').modal('hide');
            var uomCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: uomCode} });
            
        },
        hideUomSearchDialog: function(event)
        {
            event.preventDefault();
            $('#uom-search-dialog').modal('hide');
            
        }
    });

    return UomsView;
});
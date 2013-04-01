define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/product/costcomponenttype/list-costcomponenttype.html'
], function (
    utilities,
    entities_strings,
    costComponentTypesTemplate) {

    var CostComponentTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), costComponentTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-costcomponenttype-search':'handleCostComponentTypeSearch',
            'click #show-costcomponenttype-search-dialog':'showCostComponentTypeSearchDialog',
            'click #hide-costcomponenttype-dialog':'hideCostComponentTypeSearchDialog'
            
        },
        showCostComponentTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#costcomponenttype-search-dialog').modal('show');
            
        },
        handleCostComponentTypeSearch: function(event)
        {
            event.preventDefault();
            $('#costcomponenttype-search-dialog').modal('hide');
            var costComponentTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: costComponentTypeCode} });
            
        },
        hideCostComponentTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#costcomponenttype-search-dialog').modal('hide');
            
        }
    });

    return CostComponentTypesView;
});
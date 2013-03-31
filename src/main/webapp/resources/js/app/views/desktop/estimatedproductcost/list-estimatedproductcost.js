define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/estimatedproductcost/list-estimatedproductcost.html'
], function (
    utilities,
    entities_strings,
    estimatedProductCostsTemplate) {

    var EstimatedProductCostsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), estimatedProductCostsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-estimatedproductcost-search':'handleEstimatedProductCostSearch',
            'click #show-estimatedproductcost-search-dialog':'showEstimatedProductCostSearchDialog',
            'click #hide-estimatedproductcost-dialog':'hideEstimatedProductCostSearchDialog'
            
        },
        showEstimatedProductCostSearchDialog: function(event)
        {
            event.preventDefault();
            $('#estimatedproductcost-search-dialog').modal('show');
            
        },
        handleEstimatedProductCostSearch: function(event)
        {
            event.preventDefault();
            $('#estimatedproductcost-search-dialog').modal('hide');
            var estimatedProductCostCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: estimatedProductCostCode} });
            
        },
        hideEstimatedProductCostSearchDialog: function(event)
        {
            event.preventDefault();
            $('#estimatedproductcost-search-dialog').modal('hide');
            
        }
    });

    return EstimatedProductCostsView;
});
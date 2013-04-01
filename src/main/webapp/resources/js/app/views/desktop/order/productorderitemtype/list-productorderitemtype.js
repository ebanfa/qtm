define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/order/productorderitemtype/list-productorderitemtype.html'
], function (
    utilities,
    entities_strings,
    productOrderItemTypesTemplate) {

    var ProductOrderItemTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productOrderItemTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productorderitemtype-search':'handleProductOrderItemTypeSearch',
            'click #show-productorderitemtype-search-dialog':'showProductOrderItemTypeSearchDialog',
            'click #hide-productorderitemtype-dialog':'hideProductOrderItemTypeSearchDialog'
            
        },
        showProductOrderItemTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productorderitemtype-search-dialog').modal('show');
            
        },
        handleProductOrderItemTypeSearch: function(event)
        {
            event.preventDefault();
            $('#productorderitemtype-search-dialog').modal('hide');
            var productOrderItemTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productOrderItemTypeCode} });
            
        },
        hideProductOrderItemTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productorderitemtype-search-dialog').modal('hide');
            
        }
    });

    return ProductOrderItemTypesView;
});
define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/productorderitem/list-productorderitem.html'
], function (
    utilities,
    entities_strings,
    productOrderItemsTemplate) {

    var ProductOrderItemsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productOrderItemsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productorderitem-search':'handleProductOrderItemSearch',
            'click #show-productorderitem-search-dialog':'showProductOrderItemSearchDialog',
            'click #hide-productorderitem-dialog':'hideProductOrderItemSearchDialog'
            
        },
        showProductOrderItemSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productorderitem-search-dialog').modal('show');
            
        },
        handleProductOrderItemSearch: function(event)
        {
            event.preventDefault();
            $('#productorderitem-search-dialog').modal('hide');
            var productOrderItemCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productOrderItemCode} });
            
        },
        hideProductOrderItemSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productorderitem-search-dialog').modal('hide');
            
        }
    });

    return ProductOrderItemsView;
});
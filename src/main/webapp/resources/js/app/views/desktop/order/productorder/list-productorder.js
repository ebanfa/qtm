define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/order/productorder/list-productorder.html'
], function (
    utilities,
    entities_strings,
    productOrdersTemplate) {

    var ProductOrdersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productOrdersTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productorder-search':'handleProductOrderSearch',
            'click #show-productorder-search-dialog':'showProductOrderSearchDialog',
            'click #hide-productorder-dialog':'hideProductOrderSearchDialog'
            
        },
        showProductOrderSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productorder-search-dialog').modal('show');
            
        },
        handleProductOrderSearch: function(event)
        {
            event.preventDefault();
            $('#productorder-search-dialog').modal('hide');
            var productOrderCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productOrderCode} });
            
        },
        hideProductOrderSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productorder-search-dialog').modal('hide');
            
        }
    });

    return ProductOrdersView;
});
define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/product/list-product.html'
], function (
    utilities,
    entities_strings,
    productsTemplate) {

    var ProductsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-product-search':'handleProductSearch',
            'click #show-product-search-dialog':'showProductSearchDialog',
            'click #hide-product-dialog':'hideProductSearchDialog'
            
        },
        showProductSearchDialog: function(event)
        {
            event.preventDefault();
            $('#product-search-dialog').modal('show');
            
        },
        handleProductSearch: function(event)
        {
            event.preventDefault();
            $('#product-search-dialog').modal('hide');
            var productCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productCode} });
            
        },
        hideProductSearchDialog: function(event)
        {
            event.preventDefault();
            $('#product-search-dialog').modal('hide');
            
        }
    });

    return ProductsView;
});
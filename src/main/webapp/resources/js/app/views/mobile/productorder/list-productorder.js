define([
    'utilities',
    'text!../../../../../templates/mobile/product/list-product.html'
], function (
    utilities,
    productOrdersView) {

    var ProductOrdersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productOrdersView,  {model:this.model});
            //console.log(this.collection.toJSON());
            //console.log(this.model.models.toJSON());
            $(this.el).trigger('pagecreate');
            return this;
        }
    });

    return ProductOrdersView;
});
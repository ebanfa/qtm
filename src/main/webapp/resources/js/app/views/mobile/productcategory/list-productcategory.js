define([
    'utilities',
    'text!../../../../../templates/mobile/productcategory/list-productcategory.html'
], function (
    utilities,
    productCategoriesTemplate) {

    var ProductCategoriesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productCategoriesTemplate,  {model:this.model});
            //console.log(this.collection.toJSON());
            //console.log(this.model.models.toJSON());
            $(this.el).trigger('pagecreate');
            return this;
        }
    });

    return ProductCategoriesView;
});
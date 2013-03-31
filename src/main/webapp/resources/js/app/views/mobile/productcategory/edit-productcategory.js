define([
    'utilities',
    'configuration',
    'text!../../../../../templates/mobile/productcategory/list-productcategory.html'
], function (utilities, config, ProductCategoryDetailsTemplate) {

    var ProductCategoryDetailView = Backbone.View.extend({
        render:function () {
            var self = this;
            // retrieve the details
            $.getJSON(config.baseUrl + 'rest/productcategories/'
                + this.model.attributes.id,
                function (retrievedProductCategory) {
                utilities.applyTemplate($(self.el),
                                          ProductCategoryDetailsTemplate,
                                          self.model.attributes);
            });
            return this;
        }
    });

    return ProductCategoryDetailView;
});
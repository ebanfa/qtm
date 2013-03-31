define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/productcategory/list-productcategory.html'
], function (
    utilities,
    entities_strings,
    productCategorysTemplate) {

    var ProductCategorysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productCategorysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productcategory-search':'handleProductCategorySearch',
            'click #show-productcategory-search-dialog':'showProductCategorySearchDialog',
            'click #hide-productcategory-dialog':'hideProductCategorySearchDialog'
            
        },
        showProductCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#productcategory-search-dialog').modal('show');
            
        },
        handleProductCategorySearch: function(event)
        {
            event.preventDefault();
            $('#productcategory-search-dialog').modal('hide');
            var productCategoryCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productCategoryCode} });
            
        },
        hideProductCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#productcategory-search-dialog').modal('hide');
            
        }
    });

    return ProductCategorysView;
});
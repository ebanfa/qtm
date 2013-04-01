define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/product/productcategorytype/list-productcategorytype.html'
], function (
    utilities,
    entities_strings,
    productCategoryTypesTemplate) {

    var ProductCategoryTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productCategoryTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productcategorytype-search':'handleProductCategoryTypeSearch',
            'click #show-productcategorytype-search-dialog':'showProductCategoryTypeSearchDialog',
            'click #hide-productcategorytype-dialog':'hideProductCategoryTypeSearchDialog'
            
        },
        showProductCategoryTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productcategorytype-search-dialog').modal('show');
            
        },
        handleProductCategoryTypeSearch: function(event)
        {
            event.preventDefault();
            $('#productcategorytype-search-dialog').modal('hide');
            var productCategoryTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productCategoryTypeCode} });
            
        },
        hideProductCategoryTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productcategorytype-search-dialog').modal('hide');
            
        }
    });

    return ProductCategoryTypesView;
});
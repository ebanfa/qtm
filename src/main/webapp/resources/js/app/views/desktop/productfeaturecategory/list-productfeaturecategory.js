define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/productfeaturecategory/list-productfeaturecategory.html'
], function (
    utilities,
    entities_strings,
    productFeatureCategorysTemplate) {

    var ProductFeatureCategorysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productFeatureCategorysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productfeaturecategory-search':'handleProductFeatureCategorySearch',
            'click #show-productfeaturecategory-search-dialog':'showProductFeatureCategorySearchDialog',
            'click #hide-productfeaturecategory-dialog':'hideProductFeatureCategorySearchDialog'
            
        },
        showProductFeatureCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeaturecategory-search-dialog').modal('show');
            
        },
        handleProductFeatureCategorySearch: function(event)
        {
            event.preventDefault();
            $('#productfeaturecategory-search-dialog').modal('hide');
            var productFeatureCategoryCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productFeatureCategoryCode} });
            
        },
        hideProductFeatureCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeaturecategory-search-dialog').modal('hide');
            
        }
    });

    return ProductFeatureCategorysView;
});
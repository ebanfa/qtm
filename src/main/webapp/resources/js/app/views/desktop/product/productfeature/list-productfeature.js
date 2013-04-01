define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/product/productfeature/list-productfeature.html'
], function (
    utilities,
    entities_strings,
    productFeaturesTemplate) {

    var ProductFeaturesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productFeaturesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productfeature-search':'handleProductFeatureSearch',
            'click #show-productfeature-search-dialog':'showProductFeatureSearchDialog',
            'click #hide-productfeature-dialog':'hideProductFeatureSearchDialog'
            
        },
        showProductFeatureSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeature-search-dialog').modal('show');
            
        },
        handleProductFeatureSearch: function(event)
        {
            event.preventDefault();
            $('#productfeature-search-dialog').modal('hide');
            var productFeatureCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productFeatureCode} });
            
        },
        hideProductFeatureSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeature-search-dialog').modal('hide');
            
        }
    });

    return ProductFeaturesView;
});
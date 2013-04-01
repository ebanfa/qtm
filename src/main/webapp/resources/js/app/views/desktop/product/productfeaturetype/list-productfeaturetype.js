define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/product/productfeaturetype/list-productfeaturetype.html'
], function (
    utilities,
    entities_strings,
    productFeatureTypesTemplate) {

    var ProductFeatureTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productFeatureTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productfeaturetype-search':'handleProductFeatureTypeSearch',
            'click #show-productfeaturetype-search-dialog':'showProductFeatureTypeSearchDialog',
            'click #hide-productfeaturetype-dialog':'hideProductFeatureTypeSearchDialog'
            
        },
        showProductFeatureTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeaturetype-search-dialog').modal('show');
            
        },
        handleProductFeatureTypeSearch: function(event)
        {
            event.preventDefault();
            $('#productfeaturetype-search-dialog').modal('hide');
            var productFeatureTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productFeatureTypeCode} });
            
        },
        hideProductFeatureTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeaturetype-search-dialog').modal('hide');
            
        }
    });

    return ProductFeatureTypesView;
});
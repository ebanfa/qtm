define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/product/producttype/list-producttype.html'
], function (
    utilities,
    entities_strings,
    productTypesTemplate) {

    var ProductTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-producttype-search':'handleProductTypeSearch',
            'click #show-producttype-search-dialog':'showProductTypeSearchDialog',
            'click #hide-producttype-dialog':'hideProductTypeSearchDialog'
            
        },
        showProductTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#producttype-search-dialog').modal('show');
            
        },
        handleProductTypeSearch: function(event)
        {
            event.preventDefault();
            $('#producttype-search-dialog').modal('hide');
            var productTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productTypeCode} });
            
        },
        hideProductTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#producttype-search-dialog').modal('hide');
            
        }
    });

    return ProductTypesView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/order/productordertype/list-productordertype.html'
], function (
    utilities,
    entities_strings,
    productOrderTypesTemplate) {

    var ProductOrderTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productOrderTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productordertype-search':'handleProductOrderTypeSearch',
            'click #show-productordertype-search-dialog':'showProductOrderTypeSearchDialog',
            'click #hide-productordertype-dialog':'hideProductOrderTypeSearchDialog'
            
        },
        showProductOrderTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productordertype-search-dialog').modal('show');
            
        },
        handleProductOrderTypeSearch: function(event)
        {
            event.preventDefault();
            $('#productordertype-search-dialog').modal('hide');
            var productOrderTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productOrderTypeCode} });
            
        },
        hideProductOrderTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productordertype-search-dialog').modal('hide');
            
        }
    });

    return ProductOrderTypesView;
});
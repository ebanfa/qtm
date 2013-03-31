define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/productclassification/list-productclassification.html'
], function (
    utilities,
    entities_strings,
    productClassificationsTemplate) {

    var ProductClassificationsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productClassificationsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productclassification-search':'handleProductClassificationSearch',
            'click #show-productclassification-search-dialog':'showProductClassificationSearchDialog',
            'click #hide-productclassification-dialog':'hideProductClassificationSearchDialog'
            
        },
        showProductClassificationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productclassification-search-dialog').modal('show');
            
        },
        handleProductClassificationSearch: function(event)
        {
            event.preventDefault();
            $('#productclassification-search-dialog').modal('hide');
            var productClassificationCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productClassificationCode} });
            
        },
        hideProductClassificationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productclassification-search-dialog').modal('hide');
            
        }
    });

    return ProductClassificationsView;
});
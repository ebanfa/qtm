define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/productfeatureapplicabilitytype/list-productfeatureapplicabilitytype.html'
], function (
    utilities,
    entities_strings,
    productFeatureApplicabilityTypesTemplate) {

    var ProductFeatureApplicabilityTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productFeatureApplicabilityTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productfeatureapplicabilitytype-search':'handleProductFeatureApplicabilityTypeSearch',
            'click #show-productfeatureapplicabilitytype-search-dialog':'showProductFeatureApplicabilityTypeSearchDialog',
            'click #hide-productfeatureapplicabilitytype-dialog':'hideProductFeatureApplicabilityTypeSearchDialog'
            
        },
        showProductFeatureApplicabilityTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeatureapplicabilitytype-search-dialog').modal('show');
            
        },
        handleProductFeatureApplicabilityTypeSearch: function(event)
        {
            event.preventDefault();
            $('#productfeatureapplicabilitytype-search-dialog').modal('hide');
            var productFeatureApplicabilityTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productFeatureApplicabilityTypeCode} });
            
        },
        hideProductFeatureApplicabilityTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeatureapplicabilitytype-search-dialog').modal('hide');
            
        }
    });

    return ProductFeatureApplicabilityTypesView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/product/productfeatureapplicability/list-productfeatureapplicability.html'
], function (
    utilities,
    entities_strings,
    productFeatureApplicabilitysTemplate) {

    var ProductFeatureApplicabilitysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productFeatureApplicabilitysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productfeatureapplicability-search':'handleProductFeatureApplicabilitySearch',
            'click #show-productfeatureapplicability-search-dialog':'showProductFeatureApplicabilitySearchDialog',
            'click #hide-productfeatureapplicability-dialog':'hideProductFeatureApplicabilitySearchDialog'
            
        },
        showProductFeatureApplicabilitySearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeatureapplicability-search-dialog').modal('show');
            
        },
        handleProductFeatureApplicabilitySearch: function(event)
        {
            event.preventDefault();
            $('#productfeatureapplicability-search-dialog').modal('hide');
            var productFeatureApplicabilityCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productFeatureApplicabilityCode} });
            
        },
        hideProductFeatureApplicabilitySearchDialog: function(event)
        {
            event.preventDefault();
            $('#productfeatureapplicability-search-dialog').modal('hide');
            
        }
    });

    return ProductFeatureApplicabilitysView;
});
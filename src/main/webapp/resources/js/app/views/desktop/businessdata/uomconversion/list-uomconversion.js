define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/uomconversion/list-uomconversion.html'
], function (
    utilities,
    entities_strings,
    uomConversionsTemplate) {

    var UomConversionsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), uomConversionsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-uomconversion-search':'handleUomConversionSearch',
            'click #show-uomconversion-search-dialog':'showUomConversionSearchDialog',
            'click #hide-uomconversion-dialog':'hideUomConversionSearchDialog'
            
        },
        showUomConversionSearchDialog: function(event)
        {
            event.preventDefault();
            $('#uomconversion-search-dialog').modal('show');
            
        },
        handleUomConversionSearch: function(event)
        {
            event.preventDefault();
            $('#uomconversion-search-dialog').modal('hide');
            var uomConversionCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: uomConversionCode} });
            
        },
        hideUomConversionSearchDialog: function(event)
        {
            event.preventDefault();
            $('#uomconversion-search-dialog').modal('hide');
            
        }
    });

    return UomConversionsView;
});
define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/geoboundarytype/list-geoboundarytype.html'
], function (
    utilities,
    entities_strings,
    geoBoundaryTypesTemplate) {

    var GeoBoundaryTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), geoBoundaryTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-geoboundarytype-search':'handleGeoBoundaryTypeSearch',
            'click #show-geoboundarytype-search-dialog':'showGeoBoundaryTypeSearchDialog',
            'click #hide-geoboundarytype-dialog':'hideGeoBoundaryTypeSearchDialog'
            
        },
        showGeoBoundaryTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#geoboundarytype-search-dialog').modal('show');
            
        },
        handleGeoBoundaryTypeSearch: function(event)
        {
            event.preventDefault();
            $('#geoboundarytype-search-dialog').modal('hide');
            var geoBoundaryTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: geoBoundaryTypeCode} });
            
        },
        hideGeoBoundaryTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#geoboundarytype-search-dialog').modal('hide');
            
        }
    });

    return GeoBoundaryTypesView;
});
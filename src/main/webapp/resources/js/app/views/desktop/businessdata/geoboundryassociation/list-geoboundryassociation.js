define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/geoboundryassociation/list-geoboundryassociation.html'
], function (
    utilities,
    entities_strings,
    geoBoundryAssociationsTemplate) {

    var GeoBoundryAssociationsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), geoBoundryAssociationsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-geoboundryassociation-search':'handleGeoBoundryAssociationSearch',
            'click #show-geoboundryassociation-search-dialog':'showGeoBoundryAssociationSearchDialog',
            'click #hide-geoboundryassociation-dialog':'hideGeoBoundryAssociationSearchDialog'
            
        },
        showGeoBoundryAssociationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#geoboundryassociation-search-dialog').modal('show');
            
        },
        handleGeoBoundryAssociationSearch: function(event)
        {
            event.preventDefault();
            $('#geoboundryassociation-search-dialog').modal('hide');
            var geoBoundryAssociationCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: geoBoundryAssociationCode} });
            
        },
        hideGeoBoundryAssociationSearchDialog: function(event)
        {
            event.preventDefault();
            $('#geoboundryassociation-search-dialog').modal('hide');
            
        }
    });

    return GeoBoundryAssociationsView;
});
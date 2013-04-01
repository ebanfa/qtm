define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/list-geoboundry.html'
], function (
    utilities,
    entities_strings,
    geoBoundrysTemplate) {

    var GeoBoundrysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), geoBoundrysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-geoboundry-search':'handleGeoBoundrySearch',
            'click #show-geoboundry-search-dialog':'showGeoBoundrySearchDialog',
            'click #hide-geoboundry-dialog':'hideGeoBoundrySearchDialog'
            
        },
        showGeoBoundrySearchDialog: function(event)
        {
            event.preventDefault();
            $('#geoboundry-search-dialog').modal('show');
            
        },
        handleGeoBoundrySearch: function(event)
        {
            event.preventDefault();
            $('#geoboundry-search-dialog').modal('hide');
            var geoBoundryCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: geoBoundryCode} });
            
        },
        hideGeoBoundrySearchDialog: function(event)
        {
            event.preventDefault();
            $('#geoboundry-search-dialog').modal('hide');
            
        }
    });

    return GeoBoundrysView;
});
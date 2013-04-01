define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/businessdata/postaladdressboundry/list-postaladdressboundry.html'
], function (
    utilities,
    entities_strings,
    postalAddressBoundrysTemplate) {

    var PostalAddressBoundrysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), postalAddressBoundrysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-postaladdressboundry-search':'handlePostalAddressBoundrySearch',
            'click #show-postaladdressboundry-search-dialog':'showPostalAddressBoundrySearchDialog',
            'click #hide-postaladdressboundry-dialog':'hidePostalAddressBoundrySearchDialog'
            
        },
        showPostalAddressBoundrySearchDialog: function(event)
        {
            event.preventDefault();
            $('#postaladdressboundry-search-dialog').modal('show');
            
        },
        handlePostalAddressBoundrySearch: function(event)
        {
            event.preventDefault();
            $('#postaladdressboundry-search-dialog').modal('hide');
            var postalAddressBoundryCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: postalAddressBoundryCode} });
            
        },
        hidePostalAddressBoundrySearchDialog: function(event)
        {
            event.preventDefault();
            $('#postaladdressboundry-search-dialog').modal('hide');
            
        }
    });

    return PostalAddressBoundrysView;
});
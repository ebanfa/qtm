define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/postaladdress/list-postaladdress.html'
], function (
    utilities,
    entities_strings,
    postalAddresssTemplate) {

    var PostalAddresssView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), postalAddresssTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-postaladdress-search':'handlePostalAddressSearch',
            'click #show-postaladdress-search-dialog':'showPostalAddressSearchDialog',
            'click #hide-postaladdress-dialog':'hidePostalAddressSearchDialog'
            
        },
        showPostalAddressSearchDialog: function(event)
        {
            event.preventDefault();
            $('#postaladdress-search-dialog').modal('show');
            
        },
        handlePostalAddressSearch: function(event)
        {
            event.preventDefault();
            $('#postaladdress-search-dialog').modal('hide');
            var postalAddressCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: postalAddressCode} });
            
        },
        hidePostalAddressSearchDialog: function(event)
        {
            event.preventDefault();
            $('#postaladdress-search-dialog').modal('hide');
            
        }
    });

    return PostalAddresssView;
});
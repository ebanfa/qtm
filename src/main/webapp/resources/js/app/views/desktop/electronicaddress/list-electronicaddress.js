define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/electronicaddress/list-electronicaddress.html'
], function (
    utilities,
    entities_strings,
    electronicAddresssTemplate) {

    var ElectronicAddresssView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), electronicAddresssTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-electronicaddress-search':'handleElectronicAddressSearch',
            'click #show-electronicaddress-search-dialog':'showElectronicAddressSearchDialog',
            'click #hide-electronicaddress-dialog':'hideElectronicAddressSearchDialog'
            
        },
        showElectronicAddressSearchDialog: function(event)
        {
            event.preventDefault();
            $('#electronicaddress-search-dialog').modal('show');
            
        },
        handleElectronicAddressSearch: function(event)
        {
            event.preventDefault();
            $('#electronicaddress-search-dialog').modal('hide');
            var electronicAddressCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: electronicAddressCode} });
            
        },
        hideElectronicAddressSearchDialog: function(event)
        {
            event.preventDefault();
            $('#electronicaddress-search-dialog').modal('hide');
            
        }
    });

    return ElectronicAddresssView;
});
define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/servicepeer/list-servicepeer.html'
], function (
    utilities,
    entities_strings,
    servicePeersTemplate) {

    var ServicePeersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), servicePeersTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicepeer-search':'handleServicePeerSearch',
            'click #show-servicepeer-search-dialog':'showServicePeerSearchDialog',
            'click #hide-servicepeer-dialog':'hideServicePeerSearchDialog'
            
        },
        showServicePeerSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicepeer-search-dialog').modal('show');
            
        },
        handleServicePeerSearch: function(event)
        {
            event.preventDefault();
            $('#servicepeer-search-dialog').modal('hide');
            var servicePeerCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: servicePeerCode} });
            
        },
        hideServicePeerSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicepeer-search-dialog').modal('hide');
            
        }
    });

    return ServicePeersView;
});
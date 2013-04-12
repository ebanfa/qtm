define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/service/list-service.html'
], function (
    utilities,
    entities_strings,
    servicesTemplate) {

    var ServicesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), servicesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-service-search':'handleServiceSearch',
            'click #show-service-search-dialog':'showServiceSearchDialog',
            'click #hide-service-dialog':'hideServiceSearchDialog'
            
        },
        showServiceSearchDialog: function(event)
        {
            event.preventDefault();
            $('#service-search-dialog').modal('show');
            
        },
        handleServiceSearch: function(event)
        {
            event.preventDefault();
            $('#service-search-dialog').modal('hide');
            var serviceCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceCode} });
            
        },
        hideServiceSearchDialog: function(event)
        {
            event.preventDefault();
            $('#service-search-dialog').modal('hide');
            
        }
    });

    return ServicesView;
});
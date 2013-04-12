define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/servicetype/list-servicetype.html'
], function (
    utilities,
    entities_strings,
    serviceTypesTemplate) {

    var ServiceTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicetype-search':'handleServiceTypeSearch',
            'click #show-servicetype-search-dialog':'showServiceTypeSearchDialog',
            'click #hide-servicetype-dialog':'hideServiceTypeSearchDialog'
            
        },
        showServiceTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicetype-search-dialog').modal('show');
            
        },
        handleServiceTypeSearch: function(event)
        {
            event.preventDefault();
            $('#servicetype-search-dialog').modal('hide');
            var serviceTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceTypeCode} });
            
        },
        hideServiceTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicetype-search-dialog').modal('hide');
            
        }
    });

    return ServiceTypesView;
});
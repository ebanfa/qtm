define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/productcomponent/list-productcomponent.html'
], function (
    utilities,
    entities_strings,
    productComponentsTemplate) {

    var ProductComponentsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), productComponentsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-productcomponent-search':'handleProductComponentSearch',
            'click #show-productcomponent-search-dialog':'showProductComponentSearchDialog',
            'click #hide-productcomponent-dialog':'hideProductComponentSearchDialog'
            
        },
        showProductComponentSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productcomponent-search-dialog').modal('show');
            
        },
        handleProductComponentSearch: function(event)
        {
            event.preventDefault();
            $('#productcomponent-search-dialog').modal('hide');
            var productComponentCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: productComponentCode} });
            
        },
        hideProductComponentSearchDialog: function(event)
        {
            event.preventDefault();
            $('#productcomponent-search-dialog').modal('hide');
            
        }
    });

    return ProductComponentsView;
});
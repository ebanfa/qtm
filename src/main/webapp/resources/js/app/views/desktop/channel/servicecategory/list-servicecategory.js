define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/channel/servicecategory/list-servicecategory.html'
], function (
    utilities,
    entities_strings,
    serviceCategorysTemplate) {

    var ServiceCategorysView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), serviceCategorysTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-servicecategory-search':'handleServiceCategorySearch',
            'click #show-servicecategory-search-dialog':'showServiceCategorySearchDialog',
            'click #hide-servicecategory-dialog':'hideServiceCategorySearchDialog'
            
        },
        showServiceCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicecategory-search-dialog').modal('show');
            
        },
        handleServiceCategorySearch: function(event)
        {
            event.preventDefault();
            $('#servicecategory-search-dialog').modal('hide');
            var serviceCategoryCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: serviceCategoryCode} });
            
        },
        hideServiceCategorySearchDialog: function(event)
        {
            event.preventDefault();
            $('#servicecategory-search-dialog').modal('hide');
            
        }
    });

    return ServiceCategorysView;
});
define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/contactmechanismpurposetype/list-contactmechanismpurposetype.html'
], function (
    utilities,
    entities_strings,
    contactMechanismPurposeTypesTemplate) {

    var ContactMechanismPurposeTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), contactMechanismPurposeTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-contactmechanismpurposetype-search':'handleContactMechanismPurposeTypeSearch',
            'click #show-contactmechanismpurposetype-search-dialog':'showContactMechanismPurposeTypeSearchDialog',
            'click #hide-contactmechanismpurposetype-dialog':'hideContactMechanismPurposeTypeSearchDialog'
            
        },
        showContactMechanismPurposeTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanismpurposetype-search-dialog').modal('show');
            
        },
        handleContactMechanismPurposeTypeSearch: function(event)
        {
            event.preventDefault();
            $('#contactmechanismpurposetype-search-dialog').modal('hide');
            var contactMechanismPurposeTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: contactMechanismPurposeTypeCode} });
            
        },
        hideContactMechanismPurposeTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanismpurposetype-search-dialog').modal('hide');
            
        }
    });

    return ContactMechanismPurposeTypesView;
});
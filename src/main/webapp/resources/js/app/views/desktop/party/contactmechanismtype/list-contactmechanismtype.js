define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/contactmechanismtype/list-contactmechanismtype.html'
], function (
    utilities,
    entities_strings,
    contactMechanismTypesTemplate) {

    var ContactMechanismTypesView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), contactMechanismTypesTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-contactmechanismtype-search':'handleContactMechanismTypeSearch',
            'click #show-contactmechanismtype-search-dialog':'showContactMechanismTypeSearchDialog',
            'click #hide-contactmechanismtype-dialog':'hideContactMechanismTypeSearchDialog'
            
        },
        showContactMechanismTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanismtype-search-dialog').modal('show');
            
        },
        handleContactMechanismTypeSearch: function(event)
        {
            event.preventDefault();
            $('#contactmechanismtype-search-dialog').modal('hide');
            var contactMechanismTypeCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: contactMechanismTypeCode} });
            
        },
        hideContactMechanismTypeSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanismtype-search-dialog').modal('hide');
            
        }
    });

    return ContactMechanismTypesView;
});
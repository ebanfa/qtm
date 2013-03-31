define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/contactmechanismlink/list-contactmechanismlink.html'
], function (
    utilities,
    entities_strings,
    contactMechanismLinksTemplate) {

    var ContactMechanismLinksView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), contactMechanismLinksTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-contactmechanismlink-search':'handleContactMechanismLinkSearch',
            'click #show-contactmechanismlink-search-dialog':'showContactMechanismLinkSearchDialog',
            'click #hide-contactmechanismlink-dialog':'hideContactMechanismLinkSearchDialog'
            
        },
        showContactMechanismLinkSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanismlink-search-dialog').modal('show');
            
        },
        handleContactMechanismLinkSearch: function(event)
        {
            event.preventDefault();
            $('#contactmechanismlink-search-dialog').modal('hide');
            var contactMechanismLinkCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: contactMechanismLinkCode} });
            
        },
        hideContactMechanismLinkSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanismlink-search-dialog').modal('hide');
            
        }
    });

    return ContactMechanismLinksView;
});
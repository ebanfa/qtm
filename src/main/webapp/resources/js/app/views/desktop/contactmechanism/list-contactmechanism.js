define([
    'utilities',
    'i18n!../../../../../js/app/nls/entities',
    'text!../../../../../templates/desktop/contactmechanism/list-contactmechanism.html'
], function (
    utilities,
    entities_strings,
    contactMechanismsTemplate) {

    var ContactMechanismsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), contactMechanismsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-contactmechanism-search':'handleContactMechanismSearch',
            'click #show-contactmechanism-search-dialog':'showContactMechanismSearchDialog',
            'click #hide-contactmechanism-dialog':'hideContactMechanismSearchDialog'
            
        },
        showContactMechanismSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanism-search-dialog').modal('show');
            
        },
        handleContactMechanismSearch: function(event)
        {
            event.preventDefault();
            $('#contactmechanism-search-dialog').modal('hide');
            var contactMechanismCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: contactMechanismCode} });
            
        },
        hideContactMechanismSearchDialog: function(event)
        {
            event.preventDefault();
            $('#contactmechanism-search-dialog').modal('hide');
            
        }
    });

    return ContactMechanismsView;
});
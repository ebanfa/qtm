define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/telecommunicationsnumber/list-telecommunicationsnumber.html'
], function (
    utilities,
    entities_strings,
    telecommunicationsNumbersTemplate) {

    var TelecommunicationsNumbersView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), telecommunicationsNumbersTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-telecommunicationsnumber-search':'handleTelecommunicationsNumberSearch',
            'click #show-telecommunicationsnumber-search-dialog':'showTelecommunicationsNumberSearchDialog',
            'click #hide-telecommunicationsnumber-dialog':'hideTelecommunicationsNumberSearchDialog'
            
        },
        showTelecommunicationsNumberSearchDialog: function(event)
        {
            event.preventDefault();
            $('#telecommunicationsnumber-search-dialog').modal('show');
            
        },
        handleTelecommunicationsNumberSearch: function(event)
        {
            event.preventDefault();
            $('#telecommunicationsnumber-search-dialog').modal('hide');
            var telecommunicationsNumberCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: telecommunicationsNumberCode} });
            
        },
        hideTelecommunicationsNumberSearchDialog: function(event)
        {
            event.preventDefault();
            $('#telecommunicationsnumber-search-dialog').modal('hide');
            
        }
    });

    return TelecommunicationsNumbersView;
});
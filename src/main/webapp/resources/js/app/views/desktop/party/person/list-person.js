define([
    'utilities',
    'i18n!app/nls/entities',
    'text!../../../../../../templates/desktop/party/person/list-person.html'
], function (
    utilities,
    entities_strings,
    personsTemplate) {

    var PersonsView = Backbone.View.extend({
        render:function () {            
            utilities.applyTemplate($(this.el), personsTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        },
        events:
        {
            'click #do-person-search':'handlePersonSearch',
            'click #show-person-search-dialog':'showPersonSearchDialog',
            'click #hide-person-dialog':'hidePersonSearchDialog'
            
        },
        showPersonSearchDialog: function(event)
        {
            event.preventDefault();
            $('#person-search-dialog').modal('show');
            
        },
        handlePersonSearch: function(event)
        {
            event.preventDefault();
            $('#person-search-dialog').modal('hide');
            var personCode = $('#code').val();
            var self = this;
            this.model.bind("reset",
                function () {
                    self.render();
            }).fetch({ data: {code: personCode} });
            
        },
        hidePersonSearchDialog: function(event)
        {
            event.preventDefault();
            $('#person-search-dialog').modal('hide');
            
        }
    });

    return PersonsView;
});
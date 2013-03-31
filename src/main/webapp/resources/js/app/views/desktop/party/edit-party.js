define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/partytype/partytype',
    'text!../../../../../templates/desktop/partytype/partytype-list-subview.html',
    'text!../../../../../templates/desktop/party/edit-party.html'
], function (utilities, config, formUtilities, entities_strings, PartyTypes, partyTypeListSubViewTemplate, PartyEditTemplate) {
	
    var PartyTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyTypeSelectContainerDiv'), partyTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyTypesFetch.done(function (){
                utilities.applyTemplate($('#partyTypeSelectContainerDiv'), partyTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PartyEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(party)
                    {
                        utilities.applyTemplate($(self.el), PartyEditTemplate,  
                            {model:this.model, party:party, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyEditTemplate,  
                    {model:this.model, party:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-party-form':'editParty'
            
        },
        editParty: function(event)
        {
            event.preventDefault();
            var party = $(event.currentTarget).serializeObject();
            this.model.save(party, { 
                'success': function ()
                {
                    utilities.navigate('list-party');
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
            return false;
        },
        renderSubViews:function()
        {
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            if (this.model.attributes.id)
            {
		    	this.partyTypeId = this.model.attributes.partyType
            }
            // PartyTypes
            var partyTypes = new PartyTypes();
            partyTypeListSubView = new PartyTypeListSubView({model:partyTypes, el:$('#partyTypeSelectContainerDiv'), selectedOption:this.partyTypeId});
            partyTypeListSubView.render();
        }
    });

    return PartyEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/contactmechanism/contactmechanism',
    'app/collections/party/party',
    'text!../../../../../templates/desktop/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../templates/desktop/party/party-list-subview.html',
    'text!../../../../../templates/desktop/partycontactmechanism/edit-partycontactmechanism.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanisms, Partys, contactMechanismListSubViewTemplate, partyListSubViewTemplate, PartyContactMechanismEditTemplate) {
	
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PartyContactMechanismEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partycontactmechanism)
                    {
                        utilities.applyTemplate($(self.el), PartyContactMechanismEditTemplate,  
                            {model:this.model, partycontactmechanism:partycontactmechanism, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyContactMechanismEditTemplate,  
                    {model:this.model, partycontactmechanism:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partycontactmechanism-form':'editPartyContactMechanism'
            
        },
        editPartyContactMechanism: function(event)
        {
            event.preventDefault();
            var partycontactmechanism = $(event.currentTarget).serializeObject();
            this.model.save(partycontactmechanism, { 
                'success': function ()
                {
                    utilities.navigate('list-partycontactmechanism');
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
		    	this.contactMechanismId = this.model.attributes.contactMechanism
		    	this.partyId = this.model.attributes.party
            }
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return PartyContactMechanismEditView;
});

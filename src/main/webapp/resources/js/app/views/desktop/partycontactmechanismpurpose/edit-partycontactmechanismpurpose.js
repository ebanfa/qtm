define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/contactmechanismpurposetype/contactmechanismpurposetype',
    'app/collections/contactmechanism/contactmechanism',
    'text!../../../../../templates/desktop/contactmechanismpurposetype/contactmechanismpurposetype-list-subview.html',
    'text!../../../../../templates/desktop/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../templates/desktop/partycontactmechanismpurpose/edit-partycontactmechanismpurpose.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanismPurposeTypes, ContactMechanisms, contactMechanismPurposeTypeListSubViewTemplate, contactMechanismListSubViewTemplate, PartyContactMechanismPurposeEditTemplate) {
	
    var ContactMechanismPurposeTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismPurposeTypeSelectContainerDiv'), contactMechanismPurposeTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismPurposeType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismPurposeTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismPurposeTypesFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismPurposeTypeSelectContainerDiv'), contactMechanismPurposeTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismPurposeType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
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
    
	
    var PartyContactMechanismPurposeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partycontactmechanismpurpose)
                    {
                        utilities.applyTemplate($(self.el), PartyContactMechanismPurposeEditTemplate,  
                            {model:this.model, partycontactmechanismpurpose:partycontactmechanismpurpose, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyContactMechanismPurposeEditTemplate,  
                    {model:this.model, partycontactmechanismpurpose:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partycontactmechanismpurpose-form':'editPartyContactMechanismPurpose'
            
        },
        editPartyContactMechanismPurpose: function(event)
        {
            event.preventDefault();
            var partycontactmechanismpurpose = $(event.currentTarget).serializeObject();
            this.model.save(partycontactmechanismpurpose, { 
                'success': function ()
                {
                    utilities.navigate('list-partycontactmechanismpurpose');
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
		    	this.contactMechanismPurposeTypeId = this.model.attributes.contactMechanismPurposeType
		    	this.contactMechanismId = this.model.attributes.contactMechanism
            }
            // ContactMechanismPurposeTypes
            var contactMechanismPurposeTypes = new ContactMechanismPurposeTypes();
            contactMechanismPurposeTypeListSubView = new ContactMechanismPurposeTypeListSubView({model:contactMechanismPurposeTypes, el:$('#contactMechanismPurposeTypeSelectContainerDiv'), selectedOption:this.contactMechanismPurposeTypeId});
            contactMechanismPurposeTypeListSubView.render();
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
        }
    });

    return PartyContactMechanismPurposeEditView;
});

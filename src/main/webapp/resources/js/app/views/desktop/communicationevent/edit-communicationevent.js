define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/communicationeventtype/communicationeventtype',
    'app/collections/communicationeventpurpose/communicationeventpurpose',
    'app/collections/contactmechanismtype/contactmechanismtype',
    'app/collections/partyrelationship/partyrelationship',
    'app/collections/communicationeventstatustype/communicationeventstatustype',
    'text!../../../../../templates/desktop/communicationeventtype/communicationeventtype-list-subview.html',
    'text!../../../../../templates/desktop/communicationeventpurpose/communicationeventpurpose-list-subview.html',
    'text!../../../../../templates/desktop/contactmechanismtype/contactmechanismtype-list-subview.html',
    'text!../../../../../templates/desktop/partyrelationship/partyrelationship-list-subview.html',
    'text!../../../../../templates/desktop/communicationeventstatustype/communicationeventstatustype-list-subview.html',
    'text!../../../../../templates/desktop/communicationevent/edit-communicationevent.html'
], function (utilities, config, formUtilities, entities_strings, CommunicationEventTypes, CommunicationEventPurposes, ContactMechanismTypes, PartyRelationships, CommunicationEventStatusTypes, communicationEventTypeListSubViewTemplate, communicationEventPurposeListSubViewTemplate, contactMechanismTypeListSubViewTemplate, partyRelationshipListSubViewTemplate, communicationEventStatusTypeListSubViewTemplate, CommunicationEventEditTemplate) {
	
    var CommunicationEventTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventTypeSelectContainerDiv'), communicationEventTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var communicationEventTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventTypesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventTypeSelectContainerDiv'), communicationEventTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var CommunicationEventPurposeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventPurposeSelectContainerDiv'), communicationEventPurposeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventPurpose", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var communicationEventPurposesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventPurposesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventPurposeSelectContainerDiv'), communicationEventPurposeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventPurpose", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var ContactMechanismTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismTypeSelectContainerDiv'), contactMechanismTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismTypesFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismTypeSelectContainerDiv'), contactMechanismTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanismType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyRelationshipListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRelationshipSelectContainerDiv'), partyRelationshipListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRelationship", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRelationshipsFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRelationshipsFetch.done(function (){
                utilities.applyTemplate($('#partyRelationshipSelectContainerDiv'), partyRelationshipListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRelationship", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var CommunicationEventStatusTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventStatusTypeSelectContainerDiv'), communicationEventStatusTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventStatusType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var communicationEventStatusTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventStatusTypesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventStatusTypeSelectContainerDiv'), communicationEventStatusTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventStatusType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var CommunicationEventEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(communicationevent)
                    {
                        utilities.applyTemplate($(self.el), CommunicationEventEditTemplate,  
                            {model:this.model, communicationevent:communicationevent, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CommunicationEventEditTemplate,  
                    {model:this.model, communicationevent:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-communicationevent-form':'editCommunicationEvent'
            
        },
        editCommunicationEvent: function(event)
        {
            event.preventDefault();
            var communicationevent = $(event.currentTarget).serializeObject();
            this.model.save(communicationevent, { 
                'success': function ()
                {
                    utilities.navigate('list-communicationevent');
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
		    	this.communicationEventTypeId = this.model.attributes.communicationEventType
		    	this.communicationEventPurposeId = this.model.attributes.communicationEventPurpose
		    	this.contactMechanismTypeId = this.model.attributes.contactMechanismType
		    	this.partyRelationshipId = this.model.attributes.partyRelationship
		    	this.communicationEventStatusTypeId = this.model.attributes.communicationEventStatusType
            }
            // CommunicationEventTypes
            var communicationEventTypes = new CommunicationEventTypes();
            communicationEventTypeListSubView = new CommunicationEventTypeListSubView({model:communicationEventTypes, el:$('#communicationEventTypeSelectContainerDiv'), selectedOption:this.communicationEventTypeId});
            communicationEventTypeListSubView.render();
            // CommunicationEventPurposes
            var communicationEventPurposes = new CommunicationEventPurposes();
            communicationEventPurposeListSubView = new CommunicationEventPurposeListSubView({model:communicationEventPurposes, el:$('#communicationEventPurposeSelectContainerDiv'), selectedOption:this.communicationEventPurposeId});
            communicationEventPurposeListSubView.render();
            // ContactMechanismTypes
            var contactMechanismTypes = new ContactMechanismTypes();
            contactMechanismTypeListSubView = new ContactMechanismTypeListSubView({model:contactMechanismTypes, el:$('#contactMechanismTypeSelectContainerDiv'), selectedOption:this.contactMechanismTypeId});
            contactMechanismTypeListSubView.render();
            // PartyRelationships
            var partyRelationships = new PartyRelationships();
            partyRelationshipListSubView = new PartyRelationshipListSubView({model:partyRelationships, el:$('#partyRelationshipSelectContainerDiv'), selectedOption:this.partyRelationshipId});
            partyRelationshipListSubView.render();
            // CommunicationEventStatusTypes
            var communicationEventStatusTypes = new CommunicationEventStatusTypes();
            communicationEventStatusTypeListSubView = new CommunicationEventStatusTypeListSubView({model:communicationEventStatusTypes, el:$('#communicationEventStatusTypeSelectContainerDiv'), selectedOption:this.communicationEventStatusTypeId});
            communicationEventStatusTypeListSubView.render();
        }
    });

    return CommunicationEventEditView;
});

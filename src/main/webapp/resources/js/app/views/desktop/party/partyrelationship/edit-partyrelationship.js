define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/partyrole/partyrole',
    'app/collections/party/partyrole/partyrole',
    'app/collections/party/partyrelationshiptype/partyrelationshiptype',
    'text!../../../../../../templates/desktop/party/partyrole/partyrole-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrole/partyrole-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrelationshiptype/partyrelationshiptype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrelationship/edit-partyrelationship.html'
], function (utilities, config, formUtilities, entities_strings, PartyRoles, PartyRoles, PartyRelationshipTypes, partyRoleListSubViewTemplate, partyRoleListSubViewTemplate, partyRelationshipTypeListSubViewTemplate, PartyRelationshipEditTemplate) {
	
    var PartyRoleListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  {model:self.model, relatedFieldName:"fromPartyRole", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRolesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRolesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  {model:self.model, relatedFieldName:"fromPartyRole", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyRoleListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  {model:self.model, relatedFieldName:"toPartyRole", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRolesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRolesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  {model:self.model, relatedFieldName:"toPartyRole", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyRelationshipTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRelationshipTypeSelectContainerDiv'), partyRelationshipTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRelationshipType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRelationshipTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRelationshipTypesFetch.done(function (){
                utilities.applyTemplate($('#partyRelationshipTypeSelectContainerDiv'), partyRelationshipTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRelationshipType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PartyRelationshipEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partyrelationship)
                    {
                        utilities.applyTemplate($(self.el), PartyRelationshipEditTemplate,  
                            {model:this.model, partyrelationship:partyrelationship, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyRelationshipEditTemplate,  
                    {model:this.model, partyrelationship:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partyrelationship-form':'editPartyRelationship'
            
        },
        editPartyRelationship: function(event)
        {
            event.preventDefault();
            var partyrelationship = $(event.currentTarget).serializeObject();
            this.model.save(partyrelationship, { 
                'success': function ()
                {
                    utilities.navigate('list-partyrelationship');
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
		    	this.partyRoleId = this.model.attributes.partyRole
		    	this.partyRoleId = this.model.attributes.partyRole
		    	this.partyRelationshipTypeId = this.model.attributes.partyRelationshipType
            }
            // PartyRoles
            var partyRoles = new PartyRoles();
            partyRoleListSubView = new PartyRoleListSubView({model:partyRoles, el:$('#partyRoleSelectContainerDiv'), selectedOption:this.partyRoleId});
            partyRoleListSubView.render();
            // PartyRoles
            var partyRoles = new PartyRoles();
            partyRoleListSubView = new PartyRoleListSubView({model:partyRoles, el:$('#partyRoleSelectContainerDiv'), selectedOption:this.partyRoleId});
            partyRoleListSubView.render();
            // PartyRelationshipTypes
            var partyRelationshipTypes = new PartyRelationshipTypes();
            partyRelationshipTypeListSubView = new PartyRelationshipTypeListSubView({model:partyRelationshipTypes, el:$('#partyRelationshipTypeSelectContainerDiv'), selectedOption:this.partyRelationshipTypeId});
            partyRelationshipTypeListSubView.render();
        }
    });

    return PartyRelationshipEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/partyrole/partyrole',
    'app/collections/party/partyrole/partyrole',
    'app/collections/party/partyrelationshiptype/partyrelationshiptype',
    'text!../../../../../../templates/desktop/party/partyrole/partyrole-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrole/partyrole-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrelationshiptype/partyrelationshiptype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrelationship/edit-partyrelationship.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PartyRoles, PartyRoles, PartyRelationshipTypes, partyRoleListSubViewTemplate, partyRoleListSubViewTemplate, partyRelationshipTypeListSubViewTemplate, PartyRelationshipEditTemplate) {
	
    var PartyRoleListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyRolesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRolesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"fromPartyRole", 
            	fieldName:entities_strings.partyrole, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyRoleListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyRolesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRolesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleSelectContainerDiv'), partyRoleListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"toPartyRole", 
            	fieldName:entities_strings.partyrole, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyRelationshipTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRelationshipTypeSelectContainerDiv'), partyRelationshipTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyRelationshipTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRelationshipTypesFetch.done(function (){
                utilities.applyTemplate($('#partyRelationshipTypeSelectContainerDiv'), partyRelationshipTypeListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"partyRelationshipType", 
            	fieldName:entities_strings.partyrelationshiptype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var PartyRelationshipEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyRelationshipEditTemplate;
        },
        events:
        {
            'submit #edit-partyrelationship-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partyrelationship');
        },
        renderSubViews:function()
        {
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

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/roletype/roletype',
    'app/collections/party/partyroletype/partyroletype',
    'text!../../../../../../templates/desktop/party/roletype/roletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyroletype/partyroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyroletype/edit-partyroletype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, RoleTypes, PartyRoleTypes, roleTypeListSubViewTemplate, partyRoleTypeListSubViewTemplate, PartyRoleTypeEditTemplate) {
	
    var RoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#roleTypeSelectContainerDiv'), roleTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var roleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            roleTypesFetch.done(function (){
                utilities.applyTemplate($('#roleTypeSelectContainerDiv'), roleTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"roleType", 
            	fieldName:entities_strings.roletype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"partyRoleType", 
            	fieldName:entities_strings.partyroletype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var PartyRoleTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyRoleTypeEditTemplate;
        },
        events:
        {
            'submit #edit-partyroletype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partyroletype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.roleTypeId = this.model.attributes.roleType
		    	this.partyRoleTypeId = this.model.attributes.partyRoleType
            }
            // RoleTypes
            var roleTypes = new RoleTypes();
            roleTypeListSubView = new RoleTypeListSubView({model:roleTypes, el:$('#roleTypeSelectContainerDiv'), selectedOption:this.roleTypeId});
            roleTypeListSubView.render();
            // PartyRoleTypes
            var partyRoleTypes = new PartyRoleTypes();
            partyRoleTypeListSubView = new PartyRoleTypeListSubView({model:partyRoleTypes, el:$('#partyRoleTypeSelectContainerDiv'), selectedOption:this.partyRoleTypeId});
            partyRoleTypeListSubView.render();
        }
    });

    return PartyRoleTypeEditView;
});

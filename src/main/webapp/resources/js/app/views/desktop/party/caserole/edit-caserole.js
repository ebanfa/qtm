define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/party/party',
    'app/collections/party/caseroletype/caseroletype',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/caseroletype/caseroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/caserole/edit-caserole.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Partys, CaseRoleTypes, partyListSubViewTemplate, caseRoleTypeListSubViewTemplate, CaseRoleEditTemplate) {
	
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"party", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var CaseRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#caseRoleTypeSelectContainerDiv'), caseRoleTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var caseRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            caseRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#caseRoleTypeSelectContainerDiv'), caseRoleTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"caseRoleType", 
            	fieldName:entities_strings.caseroletype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var CaseRoleEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CaseRoleEditTemplate;
        },
        events:
        {
            'submit #edit-caserole-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-caserole');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyId = this.model.attributes.party
		    	this.caseRoleTypeId = this.model.attributes.caseRoleType
            }
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // CaseRoleTypes
            var caseRoleTypes = new CaseRoleTypes();
            caseRoleTypeListSubView = new CaseRoleTypeListSubView({model:caseRoleTypes, el:$('#caseRoleTypeSelectContainerDiv'), selectedOption:this.caseRoleTypeId});
            caseRoleTypeListSubView.render();
        }
    });

    return CaseRoleEditView;
});

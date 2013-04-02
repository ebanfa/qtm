define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/partyroletype/partyroletype',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/party/partyroletype/partyroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrole/edit-partyrole.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PartyRoleTypes, Partys, partyRoleTypeListSubViewTemplate, partyListSubViewTemplate, PartyRoleEditTemplate) {
	
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
    
	
    var PartyRoleEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyRoleEditTemplate;
        },
        events:
        {
            'submit #edit-partyrole-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partyrole');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyRoleTypeId = this.model.attributes.partyRoleType
		    	this.partyId = this.model.attributes.party
            }
            // PartyRoleTypes
            var partyRoleTypes = new PartyRoleTypes();
            partyRoleTypeListSubView = new PartyRoleTypeListSubView({model:partyRoleTypes, el:$('#partyRoleTypeSelectContainerDiv'), selectedOption:this.partyRoleTypeId});
            partyRoleTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return PartyRoleEditView;
});

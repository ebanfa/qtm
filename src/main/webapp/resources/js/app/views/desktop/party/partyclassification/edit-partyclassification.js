define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/partytype/partytype',
    'app/collections/party/partyclassificationtype/partyclassificationtype',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/party/partytype/partytype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyclassificationtype/partyclassificationtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyclassification/edit-partyclassification.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PartyTypes, PartyClassificationTypes, Partys, partyTypeListSubViewTemplate, partyClassificationTypeListSubViewTemplate, partyListSubViewTemplate, PartyClassificationEditTemplate) {
	
    var PartyTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyTypeSelectContainerDiv'), partyTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyTypesFetch.done(function (){
                utilities.applyTemplate($('#partyTypeSelectContainerDiv'), partyTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"partyType", 
            	fieldName:entities_strings.partytype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyClassificationTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyClassificationTypeSelectContainerDiv'), partyClassificationTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partyClassificationTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyClassificationTypesFetch.done(function (){
                utilities.applyTemplate($('#partyClassificationTypeSelectContainerDiv'), partyClassificationTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"partyClassificationType", 
            	fieldName:entities_strings.partyclassificationtype, 
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
    
	
    var PartyClassificationEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyClassificationEditTemplate;
        },
        events:
        {
            'submit #edit-partyclassification-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partyclassification');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyTypeId = this.model.attributes.partyType
		    	this.partyClassificationTypeId = this.model.attributes.partyClassificationType
		    	this.partyId = this.model.attributes.party
            }
            // PartyTypes
            var partyTypes = new PartyTypes();
            partyTypeListSubView = new PartyTypeListSubView({model:partyTypes, el:$('#partyTypeSelectContainerDiv'), selectedOption:this.partyTypeId});
            partyTypeListSubView.render();
            // PartyClassificationTypes
            var partyClassificationTypes = new PartyClassificationTypes();
            partyClassificationTypeListSubView = new PartyClassificationTypeListSubView({model:partyClassificationTypes, el:$('#partyClassificationTypeSelectContainerDiv'), selectedOption:this.partyClassificationTypeId});
            partyClassificationTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return PartyClassificationEditView;
});

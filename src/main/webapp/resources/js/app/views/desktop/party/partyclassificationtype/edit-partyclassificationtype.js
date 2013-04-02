define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/partyclassificationtype/partyclassificationtype',
    'text!../../../../../../templates/desktop/party/partyclassificationtype/partyclassificationtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyclassificationtype/edit-partyclassificationtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PartyClassificationTypes, partyClassificationTypeListSubViewTemplate, PartyClassificationTypeEditTemplate) {
	
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
    
	
    var PartyClassificationTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyClassificationTypeEditTemplate;
        },
        events:
        {
            'submit #edit-partyclassificationtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-partyclassificationtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyClassificationTypeId = this.model.attributes.partyClassificationType
            }
            // PartyClassificationTypes
            var partyClassificationTypes = new PartyClassificationTypes();
            partyClassificationTypeListSubView = new PartyClassificationTypeListSubView({model:partyClassificationTypes, el:$('#partyClassificationTypeSelectContainerDiv'), selectedOption:this.partyClassificationTypeId});
            partyClassificationTypeListSubView.render();
        }
    });

    return PartyClassificationTypeEditView;
});

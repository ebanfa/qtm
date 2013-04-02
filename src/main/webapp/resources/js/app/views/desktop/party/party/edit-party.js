define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/partytype/partytype',
    'text!../../../../../../templates/desktop/party/partytype/partytype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/edit-party.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PartyTypes, partyTypeListSubViewTemplate, PartyEditTemplate) {
	
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
    
	
    var PartyEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PartyEditTemplate;
        },
        events:
        {
            'submit #edit-party-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-party');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyTypeId = this.model.attributes.partyType
            }
            // PartyTypes
            var partyTypes = new PartyTypes();
            partyTypeListSubView = new PartyTypeListSubView({model:partyTypes, el:$('#partyTypeSelectContainerDiv'), selectedOption:this.partyTypeId});
            partyTypeListSubView.render();
        }
    });

    return PartyEditView;
});

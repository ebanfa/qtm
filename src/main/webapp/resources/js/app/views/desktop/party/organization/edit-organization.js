define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/organization/edit-organization.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Partys, partyListSubViewTemplate, OrganizationEditTemplate) {
	
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
    
	
    var OrganizationEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = OrganizationEditTemplate;
        },
        events:
        {
            'submit #edit-organization-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-organization');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyId = this.model.attributes.party
            }
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return OrganizationEditView;
});

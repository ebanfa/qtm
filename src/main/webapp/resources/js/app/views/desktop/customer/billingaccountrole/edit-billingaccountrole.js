define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/customer/billingaccountroletype/billingaccountroletype',
    'app/collections/party/party/party',
    'app/collections/customer/billingaccount/billingaccount',
    'text!../../../../../../templates/desktop/customer/billingaccountroletype/billingaccountroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/customer/billingaccount/billingaccount-list-subview.html',
    'text!../../../../../../templates/desktop/customer/billingaccountrole/edit-billingaccountrole.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, BillingAccountRoleTypes, Partys, BillingAccounts, billingAccountRoleTypeListSubViewTemplate, partyListSubViewTemplate, billingAccountListSubViewTemplate, BillingAccountRoleEditTemplate) {
	
    var BillingAccountRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#billingAccountRoleTypeSelectContainerDiv'), billingAccountRoleTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var billingAccountRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            billingAccountRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#billingAccountRoleTypeSelectContainerDiv'), billingAccountRoleTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"billingAccountRoleType", 
            	fieldName:entities_strings.billingaccountroletype, 
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
    
    var BillingAccountListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var billingAccountsFetch = this.model.fetch();
            // Re render the template when the data is available    
            billingAccountsFetch.done(function (){
                utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"billingAccount", 
            	fieldName:entities_strings.billingaccount, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var BillingAccountRoleEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = BillingAccountRoleEditTemplate;
        },
        events:
        {
            'submit #edit-billingaccountrole-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-billingaccountrole');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.billingAccountRoleTypeId = this.model.attributes.billingAccountRoleType
		    	this.partyId = this.model.attributes.party
		    	this.billingAccountId = this.model.attributes.billingAccount
            }
            // BillingAccountRoleTypes
            var billingAccountRoleTypes = new BillingAccountRoleTypes();
            billingAccountRoleTypeListSubView = new BillingAccountRoleTypeListSubView({model:billingAccountRoleTypes, el:$('#billingAccountRoleTypeSelectContainerDiv'), selectedOption:this.billingAccountRoleTypeId});
            billingAccountRoleTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // BillingAccounts
            var billingAccounts = new BillingAccounts();
            billingAccountListSubView = new BillingAccountListSubView({model:billingAccounts, el:$('#billingAccountSelectContainerDiv'), selectedOption:this.billingAccountId});
            billingAccountListSubView.render();
        }
    });

    return BillingAccountRoleEditView;
});

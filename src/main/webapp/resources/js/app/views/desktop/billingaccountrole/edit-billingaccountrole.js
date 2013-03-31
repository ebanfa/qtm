define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/billingaccountroletype/billingaccountroletype',
    'app/collections/party/party',
    'app/collections/billingaccount/billingaccount',
    'text!../../../../../templates/desktop/billingaccountroletype/billingaccountroletype-list-subview.html',
    'text!../../../../../templates/desktop/party/party-list-subview.html',
    'text!../../../../../templates/desktop/billingaccount/billingaccount-list-subview.html',
    'text!../../../../../templates/desktop/billingaccountrole/edit-billingaccountrole.html'
], function (utilities, config, formUtilities, entities_strings, BillingAccountRoleTypes, Partys, BillingAccounts, billingAccountRoleTypeListSubViewTemplate, partyListSubViewTemplate, billingAccountListSubViewTemplate, BillingAccountRoleEditTemplate) {
	
    var BillingAccountRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#billingAccountRoleTypeSelectContainerDiv'), billingAccountRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"billingAccountRoleType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var billingAccountRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            billingAccountRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#billingAccountRoleTypeSelectContainerDiv'), billingAccountRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"billingAccountRoleType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var BillingAccountListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  {model:self.model, relatedFieldName:"billingAccount", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var billingAccountsFetch = this.model.fetch();
            // Re render the template when the data is available    
            billingAccountsFetch.done(function (){
                utilities.applyTemplate($('#billingAccountSelectContainerDiv'), billingAccountListSubViewTemplate,  {model:self.model, relatedFieldName:"billingAccount", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var BillingAccountRoleEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(billingaccountrole)
                    {
                        utilities.applyTemplate($(self.el), BillingAccountRoleEditTemplate,  
                            {model:this.model, billingaccountrole:billingaccountrole, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), BillingAccountRoleEditTemplate,  
                    {model:this.model, billingaccountrole:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-billingaccountrole-form':'editBillingAccountRole'
            
        },
        editBillingAccountRole: function(event)
        {
            event.preventDefault();
            var billingaccountrole = $(event.currentTarget).serializeObject();
            this.model.save(billingaccountrole, { 
                'success': function ()
                {
                    utilities.navigate('list-billingaccountrole');
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

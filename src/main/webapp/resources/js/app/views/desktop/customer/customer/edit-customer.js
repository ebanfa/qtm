define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/collections/party/party/party',
    'app/collections/party/partytype/partytype',
    'app/views/desktop/base/baseentityeditview',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/partytype/partytype-list-subview.html',
    'text!../../../../../../templates/desktop/customer/customer/edit-customer.html'
], function (utilities, config, formUtilities, entities_strings, Partys, PartyTypes, BaseEntityEditView, partyListSubViewTemplate, partyTypeListSubViewTemplate, CustomerEditTemplate) {
	
    

    var PartyTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyTypeSelectContainerDiv'), 
                partyTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data and Re render the template when the data is available
            var partyTypesFetch = this.model.fetch();
            partyTypesFetch.done(function ()
            {
                utilities.applyTemplate($('#partyTypeSelectContainerDiv'), 
                    partyTypeListSubViewTemplate, self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = {
                idField:'code', 
                model:self.model, 
                relatedFieldName:"party", 
                fieldName:entities_strings.customer_customertype, 
                entities_strings:entities_strings, 
                selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var CustomerEditView = BaseEntityEditView.extend({

        initialize: function(options)
        {
            this.entityTemplate = CustomerEditTemplate;
        },
        events:
        {
            'change #partyTypeSelect':'changeCustomerEditForm',
            'submit #edit-customer-form':'saveEntity'
        },
        changeCustomerEditForm: function(event)
        {
            var customerType = $(event.currentTarget).val();
            console.log('Option selected:' + customerType);
            if (customerType == "ORGANIZATION")
            {
                this.renderOrganizationView();
            }
            else if (customerType == "INDIVIDUAL") 
            {
                this.renderPersonView();
            }
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-customer');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.partyId = this.model.attributes.party
            }
            // Partys
            var partytypes = new PartyTypes();
            partyTypeListSubView = new PartyTypeListSubView({model:partytypes, el:$('#partyTypeSelectContainerDiv'), selectedOption:this.partyTypeId});
            partyTypeListSubView.render();
        },
        renderPersonView: function()
        {
            utilities.navigate('edit-person');
        },
        renderOrganizationView: function()
        {
            utilities.navigate('edit-organization');
        }

    });

    return CustomerEditView;
});

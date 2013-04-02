define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/payment/paymenttype/paymenttype',
    'app/collections/party/party/party',
    'app/collections/party/party/party',
    'app/collections/payment/paymentmethodtypeprovider/paymentmethodtypeprovider',
    'app/collections/payment/paymentmethodtype/paymentmethodtype',
    'text!../../../../../../templates/desktop/payment/paymenttype/paymenttype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/payment/paymentmethodtypeprovider/paymentmethodtypeprovider-list-subview.html',
    'text!../../../../../../templates/desktop/payment/paymentmethodtype/paymentmethodtype-list-subview.html',
    'text!../../../../../../templates/desktop/payment/payment/edit-payment.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PaymentTypes, Partys, Partys, PaymentMethodTypeProviders, PaymentMethodTypes, paymentTypeListSubViewTemplate, partyListSubViewTemplate, partyListSubViewTemplate, paymentMethodTypeProviderListSubViewTemplate, paymentMethodTypeListSubViewTemplate, PaymentEditTemplate) {
	
    var PaymentTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentTypeSelectContainerDiv'), paymentTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var paymentTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentTypesFetch.done(function (){
                utilities.applyTemplate($('#paymentTypeSelectContainerDiv'), paymentTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"paymentType", 
            	fieldName:entities_strings.paymenttype, 
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
            	relatedFieldName:"partyByToPartyId", 
            	fieldName:entities_strings.party, 
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
            	relatedFieldName:"partyByFromPartyId", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PaymentMethodTypeProviderListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentMethodTypeProviderSelectContainerDiv'), paymentMethodTypeProviderListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var paymentMethodTypeProvidersFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentMethodTypeProvidersFetch.done(function (){
                utilities.applyTemplate($('#paymentMethodTypeProviderSelectContainerDiv'), paymentMethodTypeProviderListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"paymentMethodTypeProvider", 
            	fieldName:entities_strings.paymentmethodtypeprovider, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PaymentMethodTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentMethodTypeSelectContainerDiv'), paymentMethodTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var paymentMethodTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentMethodTypesFetch.done(function (){
                utilities.applyTemplate($('#paymentMethodTypeSelectContainerDiv'), paymentMethodTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"paymentMethodType", 
            	fieldName:entities_strings.paymentmethodtype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var PaymentEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PaymentEditTemplate;
        },
        events:
        {
            'submit #edit-payment-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-payment');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.paymentTypeId = this.model.attributes.paymentType
		    	this.partyId = this.model.attributes.party
		    	this.partyId = this.model.attributes.party
		    	this.paymentMethodTypeProviderId = this.model.attributes.paymentMethodTypeProvider
		    	this.paymentMethodTypeId = this.model.attributes.paymentMethodType
            }
            // PaymentTypes
            var paymentTypes = new PaymentTypes();
            paymentTypeListSubView = new PaymentTypeListSubView({model:paymentTypes, el:$('#paymentTypeSelectContainerDiv'), selectedOption:this.paymentTypeId});
            paymentTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // PaymentMethodTypeProviders
            var paymentMethodTypeProviders = new PaymentMethodTypeProviders();
            paymentMethodTypeProviderListSubView = new PaymentMethodTypeProviderListSubView({model:paymentMethodTypeProviders, el:$('#paymentMethodTypeProviderSelectContainerDiv'), selectedOption:this.paymentMethodTypeProviderId});
            paymentMethodTypeProviderListSubView.render();
            // PaymentMethodTypes
            var paymentMethodTypes = new PaymentMethodTypes();
            paymentMethodTypeListSubView = new PaymentMethodTypeListSubView({model:paymentMethodTypes, el:$('#paymentMethodTypeSelectContainerDiv'), selectedOption:this.paymentMethodTypeId});
            paymentMethodTypeListSubView.render();
        }
    });

    return PaymentEditView;
});

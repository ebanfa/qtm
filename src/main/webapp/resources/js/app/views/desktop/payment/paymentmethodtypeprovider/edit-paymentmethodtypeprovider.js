define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/payment/paymentmethodtype/paymentmethodtype',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/payment/paymentmethodtype/paymentmethodtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/payment/paymentmethodtypeprovider/edit-paymentmethodtypeprovider.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PaymentMethodTypes, Partys, paymentMethodTypeListSubViewTemplate, partyListSubViewTemplate, PaymentMethodTypeProviderEditTemplate) {
	
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
    
	
    var PaymentMethodTypeProviderEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PaymentMethodTypeProviderEditTemplate;
        },
        events:
        {
            'submit #edit-paymentmethodtypeprovider-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-paymentmethodtypeprovider');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.paymentMethodTypeId = this.model.attributes.paymentMethodType
		    	this.partyId = this.model.attributes.party
            }
            // PaymentMethodTypes
            var paymentMethodTypes = new PaymentMethodTypes();
            paymentMethodTypeListSubView = new PaymentMethodTypeListSubView({model:paymentMethodTypes, el:$('#paymentMethodTypeSelectContainerDiv'), selectedOption:this.paymentMethodTypeId});
            paymentMethodTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return PaymentMethodTypeProviderEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
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
], function (utilities, config, formUtilities, entities_strings, PaymentTypes, Partys, Partys, PaymentMethodTypeProviders, PaymentMethodTypes, paymentTypeListSubViewTemplate, partyListSubViewTemplate, partyListSubViewTemplate, paymentMethodTypeProviderListSubViewTemplate, paymentMethodTypeListSubViewTemplate, PaymentEditTemplate) {
	
    var PaymentTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentTypeSelectContainerDiv'), paymentTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"paymentType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var paymentTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentTypesFetch.done(function (){
                utilities.applyTemplate($('#paymentTypeSelectContainerDiv'), paymentTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"paymentType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByToPartyId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByToPartyId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByFromPartyId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"partyByFromPartyId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PaymentMethodTypeProviderListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentMethodTypeProviderSelectContainerDiv'), paymentMethodTypeProviderListSubViewTemplate,  {model:self.model, relatedFieldName:"paymentMethodTypeProvider", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var paymentMethodTypeProvidersFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentMethodTypeProvidersFetch.done(function (){
                utilities.applyTemplate($('#paymentMethodTypeProviderSelectContainerDiv'), paymentMethodTypeProviderListSubViewTemplate,  {model:self.model, relatedFieldName:"paymentMethodTypeProvider", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PaymentMethodTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentMethodTypeSelectContainerDiv'), paymentMethodTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"paymentMethodType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var paymentMethodTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            paymentMethodTypesFetch.done(function (){
                utilities.applyTemplate($('#paymentMethodTypeSelectContainerDiv'), paymentMethodTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"paymentMethodType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PaymentEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(payment)
                    {
                        utilities.applyTemplate($(self.el), PaymentEditTemplate,  
                            {model:this.model, payment:payment, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PaymentEditTemplate,  
                    {model:this.model, payment:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-payment-form':'editPayment'
            
        },
        editPayment: function(event)
        {
            event.preventDefault();
            var payment = $(event.currentTarget).serializeObject();
            this.model.save(payment, { 
                'success': function ()
                {
                    utilities.navigate('list-payment');
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

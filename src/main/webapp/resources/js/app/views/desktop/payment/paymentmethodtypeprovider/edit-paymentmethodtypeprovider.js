define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/payment/paymentmethodtype/paymentmethodtype',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/payment/paymentmethodtype/paymentmethodtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/payment/paymentmethodtypeprovider/edit-paymentmethodtypeprovider.html'
], function (utilities, config, formUtilities, entities_strings, PaymentMethodTypes, Partys, paymentMethodTypeListSubViewTemplate, partyListSubViewTemplate, PaymentMethodTypeProviderEditTemplate) {
	
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
    
	
    var PaymentMethodTypeProviderEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(paymentmethodtypeprovider)
                    {
                        utilities.applyTemplate($(self.el), PaymentMethodTypeProviderEditTemplate,  
                            {model:this.model, paymentmethodtypeprovider:paymentmethodtypeprovider, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PaymentMethodTypeProviderEditTemplate,  
                    {model:this.model, paymentmethodtypeprovider:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-paymentmethodtypeprovider-form':'editPaymentMethodTypeProvider'
            
        },
        editPaymentMethodTypeProvider: function(event)
        {
            event.preventDefault();
            var paymentmethodtypeprovider = $(event.currentTarget).serializeObject();
            this.model.save(paymentmethodtypeprovider, { 
                'success': function ()
                {
                    utilities.navigate('list-paymentmethodtypeprovider');
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

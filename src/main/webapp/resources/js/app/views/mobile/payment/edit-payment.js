define([
    'utilities',
    'configuration',
    'app/collections/paymentmethod/paymentmethod',
    'app/collections/paymentprovider/paymentprovider',
    'text!../../../../../templates/mobile/payment/edit-payment.html',
    'text!../../../../../templates/mobile/payment/paymentmethod-list-subview.html',
    'text!../../../../../templates/mobile/payment/paymentprovider-list-subview.html'
], function (utilities, config, PaymentMethods, PaymentProviders, paymentCreateTemplate, paymentMethodListSubViewTemplate, paymentProviderListSubViewTemplate) {

    var PaymentMethodListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#paymentMethodSelectContainerDiv'), paymentMethodListSubViewTemplate,  {model:self.model});
            var paymentMethodsFetch = this.model.fetch();    
            paymentMethodsFetch.done(function (){
                utilities.applyTemplate($('#paymentMethodSelectContainerDiv'), paymentMethodListSubViewTemplate,  {model:self.model});
                $("#paymentMethodSelect").selectmenu();
                $(".ui-page").trigger("create");
            });
            return this;
        }
    });

    var PaymentProviderListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {   
            var self = this;
            utilities.applyTemplate($('#paymentProviderSelectContainerDiv'), paymentProviderListSubViewTemplate,  {model:this.model});
            var paymentProvidersFetch = this.model.fetch();    
            paymentProvidersFetch.done(function (){
                utilities.applyTemplate($('#paymentProviderSelectContainerDiv'), paymentProviderListSubViewTemplate,  {model:self.model});
                $("#paymentProviderSelect").selectmenu();
                $(".ui-page").trigger("create");
            });
            return this;
        }
    });

    var PaymentCreateView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), paymentCreateTemplate, {model:this.model, payment:null});
            // Payment methods
            var paymentMethods = new PaymentMethods();
            paymentMethodListSubView = new PaymentMethodListSubView({model:paymentMethods, el:$('#paymentMethodSelectContainerDiv')});
            paymentMethodListSubView.render();
            // Payment providers
            var paymentProviders = new PaymentProviders();
            paymentProviderListSubView = new PaymentProviderListSubView({model:paymentProviders, el:$('#paymentProviderSelectContainerDiv')});
            paymentProviderListSubView.render();

            $(this.el).trigger('pagecreate');
            return this;
        }
    });

    return PaymentCreateView;
});
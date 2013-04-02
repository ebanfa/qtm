define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/payment/paymentmethodtype/edit-paymentmethodtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PaymentMethodTypeEditTemplate) {
	
	
    var PaymentMethodTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PaymentMethodTypeEditTemplate;
        },
        events:
        {
            'submit #edit-paymentmethodtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-paymentmethodtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return PaymentMethodTypeEditView;
});

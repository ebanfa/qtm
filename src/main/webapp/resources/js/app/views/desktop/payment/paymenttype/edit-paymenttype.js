define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/payment/paymenttype/edit-paymenttype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PaymentTypeEditTemplate) {
	
	
    var PaymentTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PaymentTypeEditTemplate;
        },
        events:
        {
            'submit #edit-paymenttype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-paymenttype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return PaymentTypeEditView;
});

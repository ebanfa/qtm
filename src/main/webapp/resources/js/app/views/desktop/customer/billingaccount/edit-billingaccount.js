define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/customer/billingaccount/edit-billingaccount.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, BillingAccountEditTemplate) {
	
	
    var BillingAccountEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = BillingAccountEditTemplate;
        },
        events:
        {
            'submit #edit-billingaccount-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-billingaccount');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return BillingAccountEditView;
});

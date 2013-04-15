define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/customer/billingaccountroletype/edit-billingaccountroletype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, BillingAccountRoleTypeEditTemplate) {
	
	
    var BillingAccountRoleTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = BillingAccountRoleTypeEditTemplate;
        },
        events:
        {
            'submit #edit-billingaccountroletype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-billingaccountroletype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return BillingAccountRoleTypeEditView;
});

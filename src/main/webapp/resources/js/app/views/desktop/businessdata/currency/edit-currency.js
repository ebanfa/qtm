define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/businessdata/currency/edit-currency.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, CurrencyEditTemplate) {
	
	
    var CurrencyEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = CurrencyEditTemplate;
        },
        events:
        {
            'submit #edit-currency-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-currency');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return CurrencyEditView;
});

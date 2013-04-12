define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/channel/servicetransactiontype/edit-servicetransactiontype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceTransactionTypeEditTemplate) {
	
	
    var ServiceTransactionTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceTransactionTypeEditTemplate;
        },
        events:
        {
            'submit #edit-servicetransactiontype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicetransactiontype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ServiceTransactionTypeEditView;
});

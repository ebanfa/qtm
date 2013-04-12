define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/channel/serviceprotocoladapter/edit-serviceprotocoladapter.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceProtocolAdapterEditTemplate) {
	
	
    var ServiceProtocolAdapterEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceProtocolAdapterEditTemplate;
        },
        events:
        {
            'submit #edit-serviceprotocoladapter-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-serviceprotocoladapter');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ServiceProtocolAdapterEditView;
});

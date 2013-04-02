define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/advice/advicestatus/edit-advicestatus.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, AdviceStatusEditTemplate) {
	
	
    var AdviceStatusEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = AdviceStatusEditTemplate;
        },
        events:
        {
            'submit #edit-advicestatus-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-advicestatus');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return AdviceStatusEditView;
});

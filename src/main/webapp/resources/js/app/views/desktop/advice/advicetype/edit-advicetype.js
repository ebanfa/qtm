define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/advice/advicetype/edit-advicetype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, AdviceTypeEditTemplate) {
	
	
    var AdviceTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = AdviceTypeEditTemplate;
        },
        events:
        {
            'submit #edit-advicetype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-advicetype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return AdviceTypeEditView;
});

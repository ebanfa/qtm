define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/businessdata/termtype/edit-termtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, TermTypeEditTemplate) {
	
	
    var TermTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = TermTypeEditTemplate;
        },
        events:
        {
            'submit #edit-termtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-termtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return TermTypeEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/party/contactmechanismtype/edit-contactmechanismtype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ContactMechanismTypeEditTemplate) {
	
	
    var ContactMechanismTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ContactMechanismTypeEditTemplate;
        },
        events:
        {
            'submit #edit-contactmechanismtype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-contactmechanismtype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ContactMechanismTypeEditView;
});

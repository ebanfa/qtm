define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/party/contactmechanismpurposetype/edit-contactmechanismpurposetype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ContactMechanismPurposeTypeEditTemplate) {
	
	
    var ContactMechanismPurposeTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ContactMechanismPurposeTypeEditTemplate;
        },
        events:
        {
            'submit #edit-contactmechanismpurposetype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-contactmechanismpurposetype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ContactMechanismPurposeTypeEditView;
});

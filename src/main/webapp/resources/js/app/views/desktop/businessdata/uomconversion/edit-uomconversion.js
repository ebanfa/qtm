define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/businessdata/uomconversion/edit-uomconversion.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, UomConversionEditTemplate) {
	
	
    var UomConversionEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = UomConversionEditTemplate;
        },
        events:
        {
            'submit #edit-uomconversion-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-uomconversion');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return UomConversionEditView;
});

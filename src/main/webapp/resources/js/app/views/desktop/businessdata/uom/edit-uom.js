define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/businessdata/uom/edit-uom.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, UomEditTemplate) {
	
	
    var UomEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = UomEditTemplate;
        },
        events:
        {
            'submit #edit-uom-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-uom');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return UomEditView;
});

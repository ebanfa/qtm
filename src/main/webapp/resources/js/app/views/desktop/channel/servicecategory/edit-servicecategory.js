define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/channel/servicecategory/edit-servicecategory.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceCategoryEditTemplate) {
	
	
    var ServiceCategoryEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceCategoryEditTemplate;
        },
        events:
        {
            'submit #edit-servicecategory-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicecategory');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ServiceCategoryEditView;
});

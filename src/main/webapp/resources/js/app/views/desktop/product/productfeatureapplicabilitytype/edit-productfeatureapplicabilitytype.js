define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'text!../../../../../../templates/desktop/product/productfeatureapplicabilitytype/edit-productfeatureapplicabilitytype.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductFeatureApplicabilityTypeEditTemplate) {
	
	
    var ProductFeatureApplicabilityTypeEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductFeatureApplicabilityTypeEditTemplate;
        },
        events:
        {
            'submit #edit-productfeatureapplicabilitytype-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productfeatureapplicabilitytype');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductFeatureApplicabilityTypeEditView;
});

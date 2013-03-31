define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/productfeatureapplicabilitytype/edit-productfeatureapplicabilitytype.html'
], function (utilities, config, formUtilities, entities_strings, ProductFeatureApplicabilityTypeEditTemplate) {
	
	
    var ProductFeatureApplicabilityTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productfeatureapplicabilitytype)
                    {
                        utilities.applyTemplate($(self.el), ProductFeatureApplicabilityTypeEditTemplate,  
                            {model:this.model, productfeatureapplicabilitytype:productfeatureapplicabilitytype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductFeatureApplicabilityTypeEditTemplate,  
                    {model:this.model, productfeatureapplicabilitytype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productfeatureapplicabilitytype-form':'editProductFeatureApplicabilityType'
            
        },
        editProductFeatureApplicabilityType: function(event)
        {
            event.preventDefault();
            var productfeatureapplicabilitytype = $(event.currentTarget).serializeObject();
            this.model.save(productfeatureapplicabilitytype, { 
                'success': function ()
                {
                    utilities.navigate('list-productfeatureapplicabilitytype');
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
            return false;
        },
        renderSubViews:function()
        {
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            if (this.model.attributes.id)
            {
            }
        }
    });

    return ProductFeatureApplicabilityTypeEditView;
});

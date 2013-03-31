define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/uomconversion/edit-uomconversion.html'
], function (utilities, config, formUtilities, entities_strings, UomConversionEditTemplate) {
	
	
    var UomConversionEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(uomconversion)
                    {
                        utilities.applyTemplate($(self.el), UomConversionEditTemplate,  
                            {model:this.model, uomconversion:uomconversion, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), UomConversionEditTemplate,  
                    {model:this.model, uomconversion:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-uomconversion-form':'editUomConversion'
            
        },
        editUomConversion: function(event)
        {
            event.preventDefault();
            var uomconversion = $(event.currentTarget).serializeObject();
            this.model.save(uomconversion, { 
                'success': function ()
                {
                    utilities.navigate('list-uomconversion');
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

    return UomConversionEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/businessdata/uom/edit-uom.html'
], function (utilities, config, formUtilities, entities_strings, UomEditTemplate) {
	
	
    var UomEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(uom)
                    {
                        utilities.applyTemplate($(self.el), UomEditTemplate,  
                            {model:this.model, uom:uom, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), UomEditTemplate,  
                    {model:this.model, uom:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-uom-form':'editUom'
            
        },
        editUom: function(event)
        {
            event.preventDefault();
            var uom = $(event.currentTarget).serializeObject();
            this.model.save(uom, { 
                'success': function ()
                {
                    utilities.navigate('list-uom');
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

    return UomEditView;
});

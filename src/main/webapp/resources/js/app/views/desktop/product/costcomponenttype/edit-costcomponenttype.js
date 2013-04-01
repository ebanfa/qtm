define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/product/costcomponenttype/edit-costcomponenttype.html'
], function (utilities, config, formUtilities, entities_strings, CostComponentTypeEditTemplate) {
	
	
    var CostComponentTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(costcomponenttype)
                    {
                        utilities.applyTemplate($(self.el), CostComponentTypeEditTemplate,  
                            {model:this.model, costcomponenttype:costcomponenttype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CostComponentTypeEditTemplate,  
                    {model:this.model, costcomponenttype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-costcomponenttype-form':'editCostComponentType'
            
        },
        editCostComponentType: function(event)
        {
            event.preventDefault();
            var costcomponenttype = $(event.currentTarget).serializeObject();
            this.model.save(costcomponenttype, { 
                'success': function ()
                {
                    utilities.navigate('list-costcomponenttype');
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

    return CostComponentTypeEditView;
});

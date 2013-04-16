define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities'
], function (utilities, config, formUtilities, entities_strings) {
    
    
    var BaseEntityEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(entity)
                    {
                        utilities.applyTemplate($(self.el), self.entityTemplate,  
                            {model:self.model, entity:entity, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                        self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), this.entityTemplate,  
                    {model:this.model, entity:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            return this;
        },
        saveEntity: function(event)
        {

            var self = this;
            event.preventDefault();
            var entity = $(event.currentTarget).serializeObject();

            this.model.save(entity, { 
                'success': function ()
                {
                    self.navigateToEntityList();
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
        }
     });
    return BaseEntityEditView;
});

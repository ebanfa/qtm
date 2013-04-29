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
                console.log('Please say something4:' + this.model.attributes.id);
                this.model.fetch(
                { 
                    success: function(entity)
                    {
                        console.log('Seriously I saw this two kids fronting');
                        utilities.applyTemplate($(self.el), self.entityTemplate,  
                            {model:self.model, entity:entity, entities_strings:entities_strings}); 

                        console.log('Please say something1');
                        $(self.el).trigger('pagecreate');
                        self.renderSubViews();
                    },
                    error: function(model, response, options) {
                        console.log(response);
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

define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'i18n!app/nls/entities'
], function (utilities, config, formUtil, entities_strings) {
    

    
    var BaseEntityEditView = Backbone.View.extend({

        fetchActivity:function()
        {
            var self = this;
            this.model.fetch({ 
                success: function(activity) {
                    console.log(">>>>>>>>>>>>>>>" + model.url);
                    $.ajax({url:model.url,success:function(result){
                        $("#div1").html(result);
                    }});
                    self.renderActivity(activity);
                },
                error: function(model, response, options) {
                    console.log(response);
                }
            });

        },
        renderActivity:function(activity)
        {
            var formBuilder = formUtil.formBuilder;
            var form = formBuilder(activity);
            utilities.applyTemplate($(this.el), this.activityTemplate, {form:form, entities_strings:entities_strings}); 
            $(this.el).trigger('pagecreate');
        },
        render:function ()  
        {
            if (this.model.attributes.id) {
                this.fetchActivity();
            }
            else {
                self.renderActivity(null);
            }
            $('.date-picker').datetimepicker({format: 'dd/MM/yyyy', pickTime: false});
            return this;
        },
        saveEntity: function(event)
        {

            var self = this;
            event.preventDefault();
            $.fn.formSerializer = formUtil.formSerializer;
            var entity = $(event.currentTarget).formSerializer();

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

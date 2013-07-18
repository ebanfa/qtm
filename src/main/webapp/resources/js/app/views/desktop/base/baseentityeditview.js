define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'app/views/desktop/base/relatedactivitiesView',
    'i18n!app/nls/entities'
], function (utilities, config, formUtil, RelatedActivitiesView, entities_strings) {
    
    var BaseEntityEditView = Backbone.View.extend({

        render:function ()  
        {
            if (this.model.attributes.id) {
                this.fetchAndRenderActivity();
            }
            else {
                this.fetchAndRenderActivity();
            }
            this.renderSubViews();
            return this;
        },
        fetchAndRenderActivity:function()
        {
            var self = this;
            this.model.fetch({ 
                success: function(activity) {
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
            this.form = formBuilder(activity);
            if(this.form.entity != null ) {
                this.renderEditActivity(this.form);
            } 
            else {
                this.renderCreateActivity(this.form);
            }

            this.relatedActivities = this.model.attributes.relatedActivities;
            var relatedActivitiesView = new RelatedActivitiesView({activities:this.relatedActivities});
            relatedActivitiesView.render();
        },
        renderEditActivity:function(form)
        {
            utilities.applyTemplate($(this.el), this.activityTemplate, {form:form, entities_strings:entities_strings}); 
            this.renderedActivity();
        },
        renderCreateActivity:function(form)
        {
            if(this.model.activityURL == "message")
            {
                this.renderAlternateView(form);
            }
            else
            {
                utilities.applyTemplate($(this.el), this.activityTemplate, {form:form, entities_strings:entities_strings}); 
                this.renderedActivity();
            }
        },
        renderedActivity: function()
        {
            $(this.el).trigger('pagecreate');
            $(".form_date").datetimepicker({format: "dd/mm/yyyy", autoclose:true});
            $(".form_datetime").datetimepicker({format: "dd/mm/yyyy"});
        },
        saveEntity: function(event)
        {
            var self = this;
            event.preventDefault();
            $.fn.formSerializer = formUtil.formSerializer;
            var entity = $(event.currentTarget).formSerializer();
            if (this.form.mode == "CREATE") {
                entity.id = null;
            }

            this.model.save(entity, { 
                'success': function ()
                {
                    self.navigateToActivityList();
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
        cancelEdit: function(event)
        {
            event.preventDefault();
            this.navigateToActivityList();
        }
     });
    return BaseEntityEditView;
});

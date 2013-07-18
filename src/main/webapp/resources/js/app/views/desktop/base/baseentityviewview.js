define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'app/views/desktop/base/relatedactivitiesView',
    'i18n!app/nls/entities'
], function (utilities, config, formUtil, RelatedActivitiesView, entities_strings) {
    
    var BaseEntityViewView = Backbone.View.extend({

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
                this.renderViewActivity(this.form);
            } 
            else {
                this.renderCreateActivity(this.form);
            }

            this.relatedActivities = this.model.attributes.relatedActivities;
            var relatedActivitiesView = new RelatedActivitiesView({activities:this.relatedActivities});
            relatedActivitiesView.render();
        },
        renderViewActivity:function(form)
        {
            utilities.applyTemplate($(this.el), this.activityTemplate, {form:form, entities_strings:entities_strings}); 
            this.renderedActivity();
        },
        renderCreateActivity:function(form)
        {
            utilities.applyTemplate($(this.el), this.activityTemplate, {form:form, entities_strings:entities_strings}); 
            this.renderedActivity();
        },
        renderedActivity: function()
        {
            $(this.el).trigger('pagecreate');
            $(".form_date").datetimepicker({format: "dd/mm/yyyy", autoclose:true});
            $(".form_datetime").datetimepicker({format: "dd/mm/yyyy"});
        },
        showEditEntityView: function(event)
        {
            
            event.preventDefault();
            this.navigateToActivityEdit();
        },
        showListEntityView: function(event)
        {
            event.preventDefault();
            this.navigateToActivityList();
        },
        navigateToActivityList:function()
        {
            var activityListURL = "list/" + this.model.activityURL;
            utilities.navigate(activityListURL);
        },
        navigateToActivityEdit:function()
        {
            var activityListURL = "edit/" + this.model.activityURL + "/" + this.model.attributes.id;
            utilities.navigate(activityListURL);
        }
     });
    return BaseEntityViewView;
});

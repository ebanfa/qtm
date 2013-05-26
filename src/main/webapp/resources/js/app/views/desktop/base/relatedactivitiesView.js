define([
    'utilities',
    'configuration',
    'i18n!app/nls/entities',
    'text!../../../../../templates/desktop/home/related-activities.html'
], function (utilities, config, entities_strings, relatedActivityTemplate) {
	
    var RelatedActivitiesView = Backbone.View.extend({
        el: '#related-activities-container',
        render:function () {
            console.log("this.options.activities:" + this.options.activities);
            utilities.applyTemplate($(this.el), relatedActivityTemplate, {activities:this.options.activities});
            
            return this;
        }
    });

    return RelatedActivitiesView;
});

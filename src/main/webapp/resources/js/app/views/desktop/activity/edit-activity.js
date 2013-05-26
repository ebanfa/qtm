define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
    'text!../../../../../templates/desktop/activity/edit-activity.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ActivityEditTemplate) {
	
    var ActivityEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.activityTemplate = ActivityEditTemplate;
        },
        events:
        {
            'submit #edit-activity-form':'saveEntity',
            'click  #cancel-edit-activity-form':'cancelEdit'
            
        },
        navigateToActivityList:function()
        {
            var activityListURL = "list/" + this.model.activityURL;
            utilities.navigate(activityListURL);
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	//this.partyId = this.model.attributes.party
            }
        }
    });

    return ActivityEditView;
});

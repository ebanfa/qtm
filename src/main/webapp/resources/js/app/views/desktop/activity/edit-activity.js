define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
    'app/views/desktop/activity/messaging/compose',
    'text!../../../../../templates/desktop/activity/edit-activity.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ComposeMsgView, ActivityEditTemplate) {
	
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
        getComposeTemplate: function()
        {
            return MessageTempl;
        },
        renderAlternateView: function(form)
        {
            console.log("Alternate view rendered");
            composeTempl = new ComposeMsgView({el:$(this.el), form:form});
            composeTempl.render();
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

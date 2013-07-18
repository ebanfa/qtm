define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityviewview',
    'text!../../../../../templates/desktop/activity/messaging/compose.html',
    'text!../../../../../templates/desktop/activity/view-activity.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityViewView, MessageTempl, ActivityViewTemplate) {
	
    var ActivityViewView = BaseEntityViewView.extend({
    
        initialize: function(options)
        {
            this.activityTemplate = ActivityViewTemplate;
        },
        events:
        {
            'submit #entity-view-form':'showEditEntityView',
            'click  #entity-view-done-btn':'showListEntityView'
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	//this.partyId = this.model.attributes.party
            }
        }
    });

    return ActivityViewView;
});

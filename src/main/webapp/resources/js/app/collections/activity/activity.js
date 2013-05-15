/**
 * Module for the Activity collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/activity/activity',
    'configuration',
    'backbone'
], function (ActivityModel, config) {
    /**
     *  Here we define the Activity collection
     *  We will use it for CRUD operations on entities
     */
    var ActivityCollection = Backbone.Collection.extend({
        initialize: function(props){
            this.url = config.baseUrl + 'rest/' + props.activityUrl; // the URL for performing CRUD operations
            this.model = ActivityModel;
            this.id = "id"; // the 'id' property of the model is the identifier
        },
        deleteByIds: function(arrayOfIds) 
        {
            console.log("Deleting entities with the following ids:"+arrayOfIds);
            $.ajax({
                type: 'DELETE',
                url: "rest/activity/delete",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(arrayOfIds),
                dataType: "json",
                success: function(data){ 
                    alert(arrayOfIds); 
                },
                error: function(request, status, error){
                    alert("Received from the server: request:" + request.responseText + " status:" + status +" Error:" + error);
                }
            });
        },
        setActivity: function(activityName)
        {
            console.log('>>>>>>>>>>>>>>>' + activityName);
            this.activityName = activityName;
        }
    });
    return ActivityCollection;
});
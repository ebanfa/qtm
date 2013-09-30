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
        initialize: function(props)
        {
            this.id = "id";
            this.model = ActivityModel;
            this.activityURL = props.activityURL;
            this.entityName = 'ApplicationActivity';
            this.baseURL = config.baseUrl + 'rest/';
            this.url = config.baseUrl + 'rest/activity/?activityURL=' + this.activityURL;            
        },
        deleteByIds: function(arrayOfIds) 
        {
            console.log("Deleting entities with the following ids:"+arrayOfIds);
            $.ajax({
                type: 'DELETE',
                url: "rest/" + this.activityURL + "/delete",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(arrayOfIds),
                dataType: "json",
                success: function(data){ 
                    //alert(arrayOfIds); 
                    for (var i = 0; i < arrayOfIds.length; i++) {
                      this.remove(this.findWhere({id:arrayOfIds[i]}));
                    }
                },
                error: function(request, status, error){
                    alert("Received from the server: request:" + request.responseText + " status:" + status +" Error:" + error);
                }
            });
        }
    });
    return ActivityCollection;
});
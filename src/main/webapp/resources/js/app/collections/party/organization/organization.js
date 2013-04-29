/**
 * Module for the Organizations collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/organization/organization',
    'configuration',
    'backbone'
], function (Organization, config) {
    /**
     *  Here we define the Organization collection
     *  We will use it for CRUD operations on Bookings
     */
    var Organizations = Backbone.Collection.extend({
        url: config.baseUrl + "rest/organization", // the URL for performing CRUD operations
        model: Organization,
        id:"id", // the 'id' property of the model is the identifier
        deleteByIds: function(arrayOfIds) 
        {
            console.log("Deleting Organizations with the following ids:"+arrayOfIds);
            $.ajax({
                type: 'DELETE',
                url: "rest/organization/delete",
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
        }
    });
    return Organizations;
});
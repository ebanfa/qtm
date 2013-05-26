define([
    'utilities',
    'configuration',
    'text!../../../../../templates/desktop/home/advice-home.html'
], function (utilities, config, homeTemplate) {

    var ActivityGroupDashboardView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), homeTemplate, {});
            
            return this;
        },
        getGroupsInModule()
        {
            $.ajax({
                type: 'GET',
                url: "rest/applicationactivitygroup/modules/" + this.moduleId,
                contentType: 'application/json; charset=utf-8',
                //data: JSON.stringify(arrayOfIds),
                dataType: "json",
                success: function(data){ 
                    this.render();
                },
                error: function(request, status, error){
                    alert("Received from the server: request:" + request.responseText + " status:" + status +" Error:" + error);
                }
            });
        }
    });
    return ActivityGroupDashboardView;
});
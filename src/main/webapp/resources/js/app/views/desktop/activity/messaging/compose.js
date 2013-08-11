define([
    'utilities',
    'configuration',
    'app/util/formUtilities',
    'app/collections/activity/activity',
    'app/views/desktop/activity/entity-search',
    'app/views/desktop/base/relatedactivitiesView',
    'text!../../../../../../templates/desktop/activity/messaging/selected-entities-textbox.html',
    'text!../../../../../../templates/desktop/activity/messaging/customer-search.html',
    'text!../../../../../../templates/desktop/activity/messaging/compose.html',
    'i18n!app/nls/entities'
], function (utilities, config, formUtil, ActivityCollection, EntitySearchDialogView, RelatedActivitiesView, SelectedEntitiesTemplate, CustomerSearchTemplate, ComposeTemplate, entities_strings) {

   /*
    * This view is in charge for rendering the entity textbox list.
    */
    var SelectedEntitiesView = Backbone.View.extend({
        initialize: function (options) {
            _.bindAll(this, 'render');
            this.elObject = options.elObject;
            this.selectedEntities = options.selectedEntities;
            this.entitySearchDialogView = null;
            this.fieldName = options.fieldName;
        },
        render:function () 
        {           
            utilities.applyTemplate(this.elObject, SelectedEntitiesTemplate,  
                {
                    model:this.selectedEntities, 
                    fieldName:this.fieldName
                });
            return this;
        }
    });

    var ComposeMessageView = Backbone.View.extend({
        initialize: function(options)
        {
            this.messageTemplate = ComposeTemplate;
            window.targetItems = $('#category');
            this.requestUrl = 'rest/message/target';
            this.form = options.form;
            // The entity record that was selected from the search results page
            this.selectedRelatedEntity = null;
            this.entityLite = null;
            // The entity we are opening the modal form for
            this.currentModalEntity = null;
            // The relationship field we are opening the modal form for.
            // This helps avoid confusion in the case were we have two 
            // relationship fields to the same target entity
            this.currentModalField = null;
            // Holds all the related entities records that have been selected
            // (ie if we search multiples times we keep each selected record here)
            this.selectedRelatedEntities = [];
            // All the related fields in the field list
            this.relatedEntityFields = this.getRelatedEntitFields(this.form.fields);

            /*this.selectedEntities = [];
            */
        },
       /*
        * Loop through all the fields and get all relation fields.
        */
        getRelatedEntitFields:function(fields)
        {
            var relatedEntityFields = [];
            for (var i=0; i<fields.length;i++)
            {
                if(fields[i].applicationEntityFieldTypeText == "RELATIONSHIP")
                    relatedEntityFields.push(fields[i]);
            }
            return relatedEntityFields;
        },
        events:
        {
            'submit #compose-message-form':'sendMessage',
            'click  #done-cancel-compose':'doneCancelForm',
            'click  #show-customer-search-dialog-btn':'showCustomerSearchDialog',
            'click  #show-user-search-dialog-btn':'showUserSearchDialog',
            'click  #do-customer-search-btn':'doSearch',
            'click  #close-entity-search-dialog-btn':'closeCustomerSearchDialog',
            'change #target-mode':'updateTargetMode',
            'change #transport-mode':'updateTransportMode',
            'keydown  #customerlistitem div':'deleteCustomerListItem',
            'keydown  #systemuserlistitem div':'deleteCustomerListItem',
            'click #add-selected-customer-btn':'addSelectedEntity',
            'click #add-next-selected-customer-btn':'addNextSelectedEntity',
            'click .select-entity-radio':'selectEntity'
        },
        render:function ()  
        {
            utilities.applyTemplate($(this.el), this.messageTemplate, {model:{fields:[], data:[]}, entities_strings:entities_strings}); 
            this.hideAll();
            $('#toDiv').show();
            //$(this.el).trigger('pagecreate');
            //this.delegateEvents();
            
    
        },
        sendMessage: function(event)
        {
            event.preventDefault();
            var formData = $('#compose-message-form').serializeObject();
            formData['toList'] = this.getToCustomerList();
            formData['ccList'] = this.getToUserList();
            delete formData.entityId;
            console.log("Sending message:" + formData);   
            $.ajax({
                type: 'POST',
                url: "rest/message/sendMessage",
                contentType: 'application/json; charset=utf-8',
                data: JSON.stringify(formData),
                dataType: "json",
                success: function(data){ 
                    alert('Outbound message queued for delivery');
                },
                error: function(request, status, error){
                    alert('Message delivery failed. Please consult log file for detailed output');
                }
            });
        },
        doneCancelForm: function(data)
        {
            window.targetItems.empty();
            var options = "";
            $.each(data, function(index, item) {
                options += '<option value="' + item.itemCode + '">' + item.itemName + '</option>' ;
            });
            window.targetItems.html(options);
        },
        hideAll:function()
        {
            $('#toDiv').hide();
            $('#typeDiv').hide();
            $('#classificationDiv').hide();
            $('#categoryDiv').hide();
        },
        updateTargetMode: function()
        {
            var targetMode = $('#target-mode').val();
            if(targetMode == "CATEGORY") 
            {
                this.hideAll();
                $('#categoryDiv').show();
                window.targetItems = $('#category');
                $.getJSON(this.requestUrl, {'targetMode':'CATEGORY'}, this.doneCancelForm);    

                console.log("Mode CAT");
            }
            else if(targetMode == "TYPE") 
            {
                this.hideAll();
                $('#typeDiv').show();
                window.targetItems = $('#type');
                $.getJSON(this.requestUrl, {'targetMode':'TYPE'}, this.doneCancelForm);

                console.log("Mode TYPE");
            }
            else if(targetMode == "CLASSIFICATION") 
            {
                this.hideAll();
                $('#classificationDiv').show();
                window.targetItems = $('#classification');
                $.getJSON(this.requestUrl, {'targetMode':'CLASSIFICATION'}, this.doneCancelForm);

                console.log("Mode CLASS");
            }
            else if(targetMode == "ALL") 
            {
                this.hideAll();
                console.log("Mode ALL");
            }
            else 
            {  
                this.hideAll();
                $('#toDiv').show();

                console.log("Mode ADD");
            }
            console.log("Mode change");

        },
        updateTransportMode: function()
        {
            var transportMode = $('#transport-mode').val();
            if(transportMode == "SMS") 
            {
                $('#subjectDiv').hide();
            }
            else {  $('#subjectDiv').show();}
            console.log("Mode change");
        },
        showCustomerSearchDialog: function(event)
        {
            event.preventDefault();
            var modalEntityName = 'Customer';
            var modalEntityActivityURL = 'customer';
            // Customer is not related to message, but this hack should allows
            // to use the related entity search functionality
            var modalEntityFieldName = modalEntityActivityURL;
            this.currentModalEntity = modalEntityName;
            this.currentModalField = modalEntityFieldName;
            this.entitySearchDialogView = 
                new EntitySearchDialogView({ 
                    modalEntityName:modalEntityName,
                    activityURL:modalEntityActivityURL,
                    modalFieldName: modalEntityFieldName
                });
            this.entitySearchDialogView.render();
        },
        showUserSearchDialog: function(event)
        {
            console.log('Showing user console!!');
            event.preventDefault();
            var modalEntityName = 'SystemUser';
            var modalEntityActivityURL = 'systemuser';
            // SystemUser is not related to message, but this hack should allows
            // to use the related entity search functionality
            var modalEntityFieldName = modalEntityActivityURL;
            this.currentModalEntity = modalEntityName;
            this.currentModalField = modalEntityFieldName;
            this.entitySearchDialogView = 
                new EntitySearchDialogView({
                    modalEntityName:modalEntityName,
                    activityURL:modalEntityActivityURL,
                    modalFieldName: modalEntityFieldName
                });
            this.entitySearchDialogView.render();
        },
        doSearch:function(event)
        {
            event.preventDefault();
            this.entitySearchDialogView.doSearch();
        },
        doUserSearch:function(event)
        {
            event.preventDefault();
            var customerName = $('#name').val();
            var searchModel = new ActivityCollection({activityURL:this.currentModalEntity});
            var searchView = new SearchView({model:searchModel, customerName:customerName, parentView:this});
            searchView.render();
        },
        closeCustomerSearchDialog:function(event)
        {
            event.preventDefault();
            $('#entity-search-dialog').modal('hide');
        },
        selectEntity:function(event)
        {
            // Properties of the selected record
            var recordId = null;
            var recordCode = null;
            var recordName = null;

            var parentTd = $(event.currentTarget).parent();
            var parentTr = null;
            if(parentTd != null)
                parentTr = parentTd.parent();
            if(parentTr != null)
            {
                var codeFieldCell = parentTr.find('.code-field-cell :first')
                if(codeFieldCell != null)
                {
                    recordCode = codeFieldCell.text().trim();
                    recordId = codeFieldCell.attr('href');
                }
                var codeFieldName = parentTr.find('.name-field-cell :first')
                if(codeFieldName != null)
                    recordName = codeFieldName.text().trim();
                $.fn.entityLite = formUtil.entityLite;
                var entityLite = $.fn.entityLite(recordId, recordCode, recordName, null);
                this.entityLite = entityLite;
                // Set up the current selected related entity (field name and entity lite)
                $.fn.createSelectedRelatedEntityInfo = formUtil.createSelectedRelatedEntityInfo;
                this.selectedRelatedEntity = 
                    $.fn.createSelectedRelatedEntityInfo(this.currentModalField, entityLite);
            }
        },
        addSelectedEntity:function(event)
        {
            this.addNextSelectedEntity(event);
            $('#entity-search-dialog').modal('hide');

        },
        addNextSelectedEntity:function(event)
        {
            event.preventDefault();
            if (this.entityLite != null) 
            {
                this.addCurrentEntity();   
            };
        },
        addCurrentEntity:function()
        {
            // check for duplicates before adding
            var addCurrentEntityFg = true;
            var entityLite = this.selectedRelatedEntity.entityLite;
            for(var i = 0; i < this.selectedRelatedEntities.length; i++) 
            {  
                if (this.selectedRelatedEntities[i].fieldName == this.selectedRelatedEntity.fieldName) {
                    if(this.selectedRelatedEntities[i].entityLite.id == entityLite.id) {
                        addCurrentEntityFg = false;
                    }
                }
            }
            if (addCurrentEntityFg){
                this.selectedRelatedEntities.push(this.selectedRelatedEntity);
            }
                
            var elObject = null;
            if(this.currentModalEntity == 'Customer')
                elObject = $('#customerlistitem');
            else 
                elObject = $('#systemuserlistitem');

            var selectedEntitiesView = new SelectedEntitiesView({
                elObject:elObject, 
                selectedEntities:this.selectedRelatedEntities,
                fieldName:this.selectedRelatedEntity.fieldName});
            selectedEntitiesView.render();
        },
        deleteCustomerListItem:function(event)
        {
            event.preventDefault();
            var divElement = $(event.currentTarget);
            if(event.keyCode == 8)
            {
                var hiddenIdInput = divElement.find('.entityId');
                if (hiddenIdInput != null)
                {
                    var newSelectedEntities = [];
                    for(var i = 0; i < this.selectedRelatedEntities.length; i++) 
                    {  
                        if(this.selectedRelatedEntities[i].entityLite.id != hiddenIdInput.val())
                            newSelectedEntities.push(this.selectedRelatedEntities[i]);
                    }
                    this.selectedRelatedEntities = newSelectedEntities;
                }
                console.log("hiddenIdInput.val():" + hiddenIdInput.val());
                divElement.remove();
            }
            console.log("keydown" + event.keyCode);
        },
        getToCustomerList:function()
        {
            var customerIds = [];
            for(var i = 0; i < this.selectedRelatedEntities.length; i++) 
            {  
                console.log('this.selectedRelatedEntities[i].fieldName:' + this.selectedRelatedEntities[i].fieldName);
                if(this.selectedRelatedEntities[i].fieldName == 'customer')
                    customerIds.push(this.selectedRelatedEntities[i].entityLite.id);
            }
            return customerIds;
        },
        getToUserList:function()
        {
            var customerIds = [];
            for(var i = 0; i < this.selectedRelatedEntities.length; i++) 
            {  
                console.log('this.selectedRelatedEntities[i].fieldName:' + this.selectedRelatedEntities[i].fieldName);
                if(this.selectedRelatedEntities[i].fieldName == 'systemuser')
                    customerIds.push(this.selectedRelatedEntities[i].entityLite.id);
            }
            return customerIds;
        }
     });
    return ComposeMessageView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/workeffort/workeffort',
    'app/collections/communicationevent/communicationevent',
    'text!../../../../../templates/desktop/workeffort/workeffort-list-subview.html',
    'text!../../../../../templates/desktop/communicationevent/communicationevent-list-subview.html',
    'text!../../../../../templates/desktop/communicationeventworkeffort/edit-communicationeventworkeffort.html'
], function (utilities, config, formUtilities, entities_strings, WorkEfforts, CommunicationEvents, workEffortListSubViewTemplate, communicationEventListSubViewTemplate, CommunicationEventWorkEffortEditTemplate) {
	
    var WorkEffortListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#workEffortSelectContainerDiv'), workEffortListSubViewTemplate,  {model:self.model, relatedFieldName:"workEffort", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var workEffortsFetch = this.model.fetch();
            // Re render the template when the data is available    
            workEffortsFetch.done(function (){
                utilities.applyTemplate($('#workEffortSelectContainerDiv'), workEffortListSubViewTemplate,  {model:self.model, relatedFieldName:"workEffort", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var CommunicationEventListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventSelectContainerDiv'), communicationEventListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEvent", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var communicationEventsFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventsFetch.done(function (){
                utilities.applyTemplate($('#communicationEventSelectContainerDiv'), communicationEventListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEvent", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var CommunicationEventWorkEffortEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(communicationeventworkeffort)
                    {
                        utilities.applyTemplate($(self.el), CommunicationEventWorkEffortEditTemplate,  
                            {model:this.model, communicationeventworkeffort:communicationeventworkeffort, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CommunicationEventWorkEffortEditTemplate,  
                    {model:this.model, communicationeventworkeffort:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-communicationeventworkeffort-form':'editCommunicationEventWorkEffort'
            
        },
        editCommunicationEventWorkEffort: function(event)
        {
            event.preventDefault();
            var communicationeventworkeffort = $(event.currentTarget).serializeObject();
            this.model.save(communicationeventworkeffort, { 
                'success': function ()
                {
                    utilities.navigate('list-communicationeventworkeffort');
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
            return false;
        },
        renderSubViews:function()
        {
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            if (this.model.attributes.id)
            {
		    	this.workEffortId = this.model.attributes.workEffort
		    	this.communicationEventId = this.model.attributes.communicationEvent
            }
            // WorkEfforts
            var workEfforts = new WorkEfforts();
            workEffortListSubView = new WorkEffortListSubView({model:workEfforts, el:$('#workEffortSelectContainerDiv'), selectedOption:this.workEffortId});
            workEffortListSubView.render();
            // CommunicationEvents
            var communicationEvents = new CommunicationEvents();
            communicationEventListSubView = new CommunicationEventListSubView({model:communicationEvents, el:$('#communicationEventSelectContainerDiv'), selectedOption:this.communicationEventId});
            communicationEventListSubView.render();
        }
    });

    return CommunicationEventWorkEffortEditView;
});

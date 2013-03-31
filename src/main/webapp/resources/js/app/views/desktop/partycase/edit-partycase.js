define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/caserole/caserole',
    'app/collections/communicationevent/communicationevent',
    'app/collections/casestatustype/casestatustype',
    'text!../../../../../templates/desktop/caserole/caserole-list-subview.html',
    'text!../../../../../templates/desktop/communicationevent/communicationevent-list-subview.html',
    'text!../../../../../templates/desktop/casestatustype/casestatustype-list-subview.html',
    'text!../../../../../templates/desktop/partycase/edit-partycase.html'
], function (utilities, config, formUtilities, entities_strings, CaseRoles, CommunicationEvents, CaseStatusTypes, caseRoleListSubViewTemplate, communicationEventListSubViewTemplate, caseStatusTypeListSubViewTemplate, PartyCaseEditTemplate) {
	
    var CaseRoleListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#caseRoleSelectContainerDiv'), caseRoleListSubViewTemplate,  {model:self.model, relatedFieldName:"caseRole", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var caseRolesFetch = this.model.fetch();
            // Re render the template when the data is available    
            caseRolesFetch.done(function (){
                utilities.applyTemplate($('#caseRoleSelectContainerDiv'), caseRoleListSubViewTemplate,  {model:self.model, relatedFieldName:"caseRole", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
    
    var CaseStatusTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#caseStatusTypeSelectContainerDiv'), caseStatusTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"caseStatusType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var caseStatusTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            caseStatusTypesFetch.done(function (){
                utilities.applyTemplate($('#caseStatusTypeSelectContainerDiv'), caseStatusTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"caseStatusType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PartyCaseEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partycase)
                    {
                        utilities.applyTemplate($(self.el), PartyCaseEditTemplate,  
                            {model:this.model, partycase:partycase, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyCaseEditTemplate,  
                    {model:this.model, partycase:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partycase-form':'editPartyCase'
            
        },
        editPartyCase: function(event)
        {
            event.preventDefault();
            var partycase = $(event.currentTarget).serializeObject();
            this.model.save(partycase, { 
                'success': function ()
                {
                    utilities.navigate('list-partycase');
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
		    	this.caseRoleId = this.model.attributes.caseRole
		    	this.communicationEventId = this.model.attributes.communicationEvent
		    	this.caseStatusTypeId = this.model.attributes.caseStatusType
            }
            // CaseRoles
            var caseRoles = new CaseRoles();
            caseRoleListSubView = new CaseRoleListSubView({model:caseRoles, el:$('#caseRoleSelectContainerDiv'), selectedOption:this.caseRoleId});
            caseRoleListSubView.render();
            // CommunicationEvents
            var communicationEvents = new CommunicationEvents();
            communicationEventListSubView = new CommunicationEventListSubView({model:communicationEvents, el:$('#communicationEventSelectContainerDiv'), selectedOption:this.communicationEventId});
            communicationEventListSubView.render();
            // CaseStatusTypes
            var caseStatusTypes = new CaseStatusTypes();
            caseStatusTypeListSubView = new CaseStatusTypeListSubView({model:caseStatusTypes, el:$('#caseStatusTypeSelectContainerDiv'), selectedOption:this.caseStatusTypeId});
            caseStatusTypeListSubView.render();
        }
    });

    return PartyCaseEditView;
});

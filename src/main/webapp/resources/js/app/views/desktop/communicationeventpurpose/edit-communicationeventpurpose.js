define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/communicationeventpurposetype/communicationeventpurposetype',
    'text!../../../../../templates/desktop/communicationeventpurposetype/communicationeventpurposetype-list-subview.html',
    'text!../../../../../templates/desktop/communicationeventpurpose/edit-communicationeventpurpose.html'
], function (utilities, config, formUtilities, entities_strings, CommunicationEventPurposeTypes, communicationEventPurposeTypeListSubViewTemplate, CommunicationEventPurposeEditTemplate) {
	
    var CommunicationEventPurposeTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#communicationEventPurposeTypeSelectContainerDiv'), communicationEventPurposeTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventPurposeType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var communicationEventPurposeTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            communicationEventPurposeTypesFetch.done(function (){
                utilities.applyTemplate($('#communicationEventPurposeTypeSelectContainerDiv'), communicationEventPurposeTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"communicationEventPurposeType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var CommunicationEventPurposeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(communicationeventpurpose)
                    {
                        utilities.applyTemplate($(self.el), CommunicationEventPurposeEditTemplate,  
                            {model:this.model, communicationeventpurpose:communicationeventpurpose, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CommunicationEventPurposeEditTemplate,  
                    {model:this.model, communicationeventpurpose:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-communicationeventpurpose-form':'editCommunicationEventPurpose'
            
        },
        editCommunicationEventPurpose: function(event)
        {
            event.preventDefault();
            var communicationeventpurpose = $(event.currentTarget).serializeObject();
            this.model.save(communicationeventpurpose, { 
                'success': function ()
                {
                    utilities.navigate('list-communicationeventpurpose');
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
		    	this.communicationEventPurposeTypeId = this.model.attributes.communicationEventPurposeType
            }
            // CommunicationEventPurposeTypes
            var communicationEventPurposeTypes = new CommunicationEventPurposeTypes();
            communicationEventPurposeTypeListSubView = new CommunicationEventPurposeTypeListSubView({model:communicationEventPurposeTypes, el:$('#communicationEventPurposeTypeSelectContainerDiv'), selectedOption:this.communicationEventPurposeTypeId});
            communicationEventPurposeTypeListSubView.render();
        }
    });

    return CommunicationEventPurposeEditView;
});

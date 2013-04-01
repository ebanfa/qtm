define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/messaging/communicationeventtype/edit-communicationeventtype.html'
], function (utilities, config, formUtilities, entities_strings, CommunicationEventTypeEditTemplate) {
	
	
    var CommunicationEventTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(communicationeventtype)
                    {
                        utilities.applyTemplate($(self.el), CommunicationEventTypeEditTemplate,  
                            {model:this.model, communicationeventtype:communicationeventtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CommunicationEventTypeEditTemplate,  
                    {model:this.model, communicationeventtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-communicationeventtype-form':'editCommunicationEventType'
            
        },
        editCommunicationEventType: function(event)
        {
            event.preventDefault();
            var communicationeventtype = $(event.currentTarget).serializeObject();
            this.model.save(communicationeventtype, { 
                'success': function ()
                {
                    utilities.navigate('list-communicationeventtype');
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
            }
        }
    });

    return CommunicationEventTypeEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/communicationeventstatustype/edit-communicationeventstatustype.html'
], function (utilities, config, formUtilities, entities_strings, CommunicationEventStatusTypeEditTemplate) {
	
	
    var CommunicationEventStatusTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(communicationeventstatustype)
                    {
                        utilities.applyTemplate($(self.el), CommunicationEventStatusTypeEditTemplate,  
                            {model:this.model, communicationeventstatustype:communicationeventstatustype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CommunicationEventStatusTypeEditTemplate,  
                    {model:this.model, communicationeventstatustype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-communicationeventstatustype-form':'editCommunicationEventStatusType'
            
        },
        editCommunicationEventStatusType: function(event)
        {
            event.preventDefault();
            var communicationeventstatustype = $(event.currentTarget).serializeObject();
            this.model.save(communicationeventstatustype, { 
                'success': function ()
                {
                    utilities.navigate('list-communicationeventstatustype');
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

    return CommunicationEventStatusTypeEditView;
});

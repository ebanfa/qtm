define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/communicationeventpurposetype/edit-communicationeventpurposetype.html'
], function (utilities, config, formUtilities, entities_strings, CommunicationEventPurposeTypeEditTemplate) {
	
	
    var CommunicationEventPurposeTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(communicationeventpurposetype)
                    {
                        utilities.applyTemplate($(self.el), CommunicationEventPurposeTypeEditTemplate,  
                            {model:this.model, communicationeventpurposetype:communicationeventpurposetype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CommunicationEventPurposeTypeEditTemplate,  
                    {model:this.model, communicationeventpurposetype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-communicationeventpurposetype-form':'editCommunicationEventPurposeType'
            
        },
        editCommunicationEventPurposeType: function(event)
        {
            event.preventDefault();
            var communicationeventpurposetype = $(event.currentTarget).serializeObject();
            this.model.save(communicationeventpurposetype, { 
                'success': function ()
                {
                    utilities.navigate('list-communicationeventpurposetype');
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

    return CommunicationEventPurposeTypeEditView;
});

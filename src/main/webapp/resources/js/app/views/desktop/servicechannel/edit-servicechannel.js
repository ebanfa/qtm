define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/servicechannel/edit-servicechannel.html'
], function (utilities, config, formUtilities, entities_strings, ServiceChannelEditTemplate) {
	
	
    var ServiceChannelEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(servicechannel)
                    {
                        utilities.applyTemplate($(self.el), ServiceChannelEditTemplate,  
                            {model:this.model, servicechannel:servicechannel, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ServiceChannelEditTemplate,  
                    {model:this.model, servicechannel:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-servicechannel-form':'editServiceChannel'
            
        },
        editServiceChannel: function(event)
        {
            event.preventDefault();
            var servicechannel = $(event.currentTarget).serializeObject();
            this.model.save(servicechannel, { 
                'success': function ()
                {
                    utilities.navigate('list-servicechannel');
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

    return ServiceChannelEditView;
});

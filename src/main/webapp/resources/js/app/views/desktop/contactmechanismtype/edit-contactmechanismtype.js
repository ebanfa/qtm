define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/contactmechanismtype/edit-contactmechanismtype.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanismTypeEditTemplate) {
	
	
    var ContactMechanismTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(contactmechanismtype)
                    {
                        utilities.applyTemplate($(self.el), ContactMechanismTypeEditTemplate,  
                            {model:this.model, contactmechanismtype:contactmechanismtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ContactMechanismTypeEditTemplate,  
                    {model:this.model, contactmechanismtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-contactmechanismtype-form':'editContactMechanismType'
            
        },
        editContactMechanismType: function(event)
        {
            event.preventDefault();
            var contactmechanismtype = $(event.currentTarget).serializeObject();
            this.model.save(contactmechanismtype, { 
                'success': function ()
                {
                    utilities.navigate('list-contactmechanismtype');
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

    return ContactMechanismTypeEditView;
});

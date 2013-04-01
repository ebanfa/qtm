define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/party/contactmechanismpurposetype/edit-contactmechanismpurposetype.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanismPurposeTypeEditTemplate) {
	
	
    var ContactMechanismPurposeTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(contactmechanismpurposetype)
                    {
                        utilities.applyTemplate($(self.el), ContactMechanismPurposeTypeEditTemplate,  
                            {model:this.model, contactmechanismpurposetype:contactmechanismpurposetype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ContactMechanismPurposeTypeEditTemplate,  
                    {model:this.model, contactmechanismpurposetype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-contactmechanismpurposetype-form':'editContactMechanismPurposeType'
            
        },
        editContactMechanismPurposeType: function(event)
        {
            event.preventDefault();
            var contactmechanismpurposetype = $(event.currentTarget).serializeObject();
            this.model.save(contactmechanismpurposetype, { 
                'success': function ()
                {
                    utilities.navigate('list-contactmechanismpurposetype');
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

    return ContactMechanismPurposeTypeEditView;
});

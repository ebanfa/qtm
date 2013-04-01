define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/party/roletype/edit-roletype.html'
], function (utilities, config, formUtilities, entities_strings, RoleTypeEditTemplate) {
	
	
    var RoleTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(roletype)
                    {
                        utilities.applyTemplate($(self.el), RoleTypeEditTemplate,  
                            {model:this.model, roletype:roletype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), RoleTypeEditTemplate,  
                    {model:this.model, roletype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-roletype-form':'editRoleType'
            
        },
        editRoleType: function(event)
        {
            event.preventDefault();
            var roletype = $(event.currentTarget).serializeObject();
            this.model.save(roletype, { 
                'success': function ()
                {
                    utilities.navigate('list-roletype');
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

    return RoleTypeEditView;
});

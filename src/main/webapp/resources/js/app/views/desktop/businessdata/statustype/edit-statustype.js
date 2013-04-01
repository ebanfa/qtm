define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/businessdata/statustype/edit-statustype.html'
], function (utilities, config, formUtilities, entities_strings, StatusTypeEditTemplate) {
	
	
    var StatusTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(statustype)
                    {
                        utilities.applyTemplate($(self.el), StatusTypeEditTemplate,  
                            {model:this.model, statustype:statustype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), StatusTypeEditTemplate,  
                    {model:this.model, statustype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-statustype-form':'editStatusType'
            
        },
        editStatusType: function(event)
        {
            event.preventDefault();
            var statustype = $(event.currentTarget).serializeObject();
            this.model.save(statustype, { 
                'success': function ()
                {
                    utilities.navigate('list-statustype');
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

    return StatusTypeEditView;
});

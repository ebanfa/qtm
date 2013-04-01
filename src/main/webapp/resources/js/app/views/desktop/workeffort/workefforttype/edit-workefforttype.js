define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/workeffort/workefforttype/edit-workefforttype.html'
], function (utilities, config, formUtilities, entities_strings, WorkEffortTypeEditTemplate) {
	
	
    var WorkEffortTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(workefforttype)
                    {
                        utilities.applyTemplate($(self.el), WorkEffortTypeEditTemplate,  
                            {model:this.model, workefforttype:workefforttype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), WorkEffortTypeEditTemplate,  
                    {model:this.model, workefforttype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-workefforttype-form':'editWorkEffortType'
            
        },
        editWorkEffortType: function(event)
        {
            event.preventDefault();
            var workefforttype = $(event.currentTarget).serializeObject();
            this.model.save(workefforttype, { 
                'success': function ()
                {
                    utilities.navigate('list-workefforttype');
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

    return WorkEffortTypeEditView;
});

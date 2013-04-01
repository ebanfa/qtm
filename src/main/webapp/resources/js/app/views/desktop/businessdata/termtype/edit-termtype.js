define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../../templates/desktop/businessdata/termtype/edit-termtype.html'
], function (utilities, config, formUtilities, entities_strings, TermTypeEditTemplate) {
	
	
    var TermTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(termtype)
                    {
                        utilities.applyTemplate($(self.el), TermTypeEditTemplate,  
                            {model:this.model, termtype:termtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), TermTypeEditTemplate,  
                    {model:this.model, termtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-termtype-form':'editTermType'
            
        },
        editTermType: function(event)
        {
            event.preventDefault();
            var termtype = $(event.currentTarget).serializeObject();
            this.model.save(termtype, { 
                'success': function ()
                {
                    utilities.navigate('list-termtype');
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

    return TermTypeEditView;
});

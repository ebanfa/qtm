define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/caseroletype/edit-caseroletype.html'
], function (utilities, config, formUtilities, entities_strings, CaseRoleTypeEditTemplate) {
	
	
    var CaseRoleTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(caseroletype)
                    {
                        utilities.applyTemplate($(self.el), CaseRoleTypeEditTemplate,  
                            {model:this.model, caseroletype:caseroletype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CaseRoleTypeEditTemplate,  
                    {model:this.model, caseroletype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-caseroletype-form':'editCaseRoleType'
            
        },
        editCaseRoleType: function(event)
        {
            event.preventDefault();
            var caseroletype = $(event.currentTarget).serializeObject();
            this.model.save(caseroletype, { 
                'success': function ()
                {
                    utilities.navigate('list-caseroletype');
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

    return CaseRoleTypeEditView;
});

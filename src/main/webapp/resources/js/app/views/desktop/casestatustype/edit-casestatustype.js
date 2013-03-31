define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'text!../../../../../templates/desktop/casestatustype/edit-casestatustype.html'
], function (utilities, config, formUtilities, entities_strings, CaseStatusTypeEditTemplate) {
	
	
    var CaseStatusTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(casestatustype)
                    {
                        utilities.applyTemplate($(self.el), CaseStatusTypeEditTemplate,  
                            {model:this.model, casestatustype:casestatustype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CaseStatusTypeEditTemplate,  
                    {model:this.model, casestatustype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-casestatustype-form':'editCaseStatusType'
            
        },
        editCaseStatusType: function(event)
        {
            event.preventDefault();
            var casestatustype = $(event.currentTarget).serializeObject();
            this.model.save(casestatustype, { 
                'success': function ()
                {
                    utilities.navigate('list-casestatustype');
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

    return CaseStatusTypeEditView;
});

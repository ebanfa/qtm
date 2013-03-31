define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/workefforttype/workefforttype',
    'text!../../../../../templates/desktop/workefforttype/workefforttype-list-subview.html',
    'text!../../../../../templates/desktop/workeffort/edit-workeffort.html'
], function (utilities, config, formUtilities, entities_strings, WorkEffortTypes, workEffortTypeListSubViewTemplate, WorkEffortEditTemplate) {
	
    var WorkEffortTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#workEffortTypeSelectContainerDiv'), workEffortTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"workEffortType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var workEffortTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            workEffortTypesFetch.done(function (){
                utilities.applyTemplate($('#workEffortTypeSelectContainerDiv'), workEffortTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"workEffortType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var WorkEffortEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(workeffort)
                    {
                        utilities.applyTemplate($(self.el), WorkEffortEditTemplate,  
                            {model:this.model, workeffort:workeffort, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), WorkEffortEditTemplate,  
                    {model:this.model, workeffort:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-workeffort-form':'editWorkEffort'
            
        },
        editWorkEffort: function(event)
        {
            event.preventDefault();
            var workeffort = $(event.currentTarget).serializeObject();
            this.model.save(workeffort, { 
                'success': function ()
                {
                    utilities.navigate('list-workeffort');
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
		    	this.workEffortTypeId = this.model.attributes.workEffortType
            }
            // WorkEffortTypes
            var workEffortTypes = new WorkEffortTypes();
            workEffortTypeListSubView = new WorkEffortTypeListSubView({model:workEffortTypes, el:$('#workEffortTypeSelectContainerDiv'), selectedOption:this.workEffortTypeId});
            workEffortTypeListSubView.render();
        }
    });

    return WorkEffortEditView;
});

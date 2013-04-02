define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/workeffort/workefforttype/workefforttype',
    'text!../../../../../../templates/desktop/workeffort/workefforttype/workefforttype-list-subview.html',
    'text!../../../../../../templates/desktop/workeffort/workeffort/edit-workeffort.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, WorkEffortTypes, workEffortTypeListSubViewTemplate, WorkEffortEditTemplate) {
	
    var WorkEffortTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#workEffortTypeSelectContainerDiv'), workEffortTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var workEffortTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            workEffortTypesFetch.done(function (){
                utilities.applyTemplate($('#workEffortTypeSelectContainerDiv'), workEffortTypeListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"workEffortType", 
            	fieldName:entities_strings.workefforttype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var WorkEffortEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = WorkEffortEditTemplate;
        },
        events:
        {
            'submit #edit-workeffort-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-workeffort');
        },
        renderSubViews:function()
        {
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

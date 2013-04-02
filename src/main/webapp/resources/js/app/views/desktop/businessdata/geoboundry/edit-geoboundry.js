define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/businessdata/geoboundarytype/geoboundarytype',
    'text!../../../../../../templates/desktop/businessdata/geoboundarytype/geoboundarytype-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/edit-geoboundry.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, GeoBoundaryTypes, geoBoundaryTypeListSubViewTemplate, GeoBoundryEditTemplate) {
	
    var GeoBoundaryTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundaryTypeSelectContainerDiv'), geoBoundaryTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var geoBoundaryTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundaryTypesFetch.done(function (){
                utilities.applyTemplate($('#geoBoundaryTypeSelectContainerDiv'), geoBoundaryTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"geoBoundaryType", 
            	fieldName:entities_strings.geoboundarytype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var GeoBoundryEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = GeoBoundryEditTemplate;
        },
        events:
        {
            'submit #edit-geoboundry-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-geoboundry');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.geoBoundaryTypeId = this.model.attributes.geoBoundaryType
            }
            // GeoBoundaryTypes
            var geoBoundaryTypes = new GeoBoundaryTypes();
            geoBoundaryTypeListSubView = new GeoBoundaryTypeListSubView({model:geoBoundaryTypes, el:$('#geoBoundaryTypeSelectContainerDiv'), selectedOption:this.geoBoundaryTypeId});
            geoBoundaryTypeListSubView.render();
        }
    });

    return GeoBoundryEditView;
});

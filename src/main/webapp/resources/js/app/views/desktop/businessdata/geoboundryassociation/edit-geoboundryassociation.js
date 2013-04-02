define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/businessdata/geoboundry/geoboundry',
    'app/collections/businessdata/geoboundry/geoboundry',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundryassociation/edit-geoboundryassociation.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, GeoBoundrys, GeoBoundrys, geoBoundryListSubViewTemplate, geoBoundryListSubViewTemplate, GeoBoundryAssociationEditTemplate) {
	
    var GeoBoundryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"geoBoundryByToGeoId", 
            	fieldName:entities_strings.geoboundry, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var GeoBoundryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"geoBoundryByFromGeoId", 
            	fieldName:entities_strings.geoboundry, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var GeoBoundryAssociationEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = GeoBoundryAssociationEditTemplate;
        },
        events:
        {
            'submit #edit-geoboundryassociation-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-geoboundryassociation');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.geoBoundryId = this.model.attributes.geoBoundry
		    	this.geoBoundryId = this.model.attributes.geoBoundry
            }
            // GeoBoundrys
            var geoBoundrys = new GeoBoundrys();
            geoBoundryListSubView = new GeoBoundryListSubView({model:geoBoundrys, el:$('#geoBoundrySelectContainerDiv'), selectedOption:this.geoBoundryId});
            geoBoundryListSubView.render();
            // GeoBoundrys
            var geoBoundrys = new GeoBoundrys();
            geoBoundryListSubView = new GeoBoundryListSubView({model:geoBoundrys, el:$('#geoBoundrySelectContainerDiv'), selectedOption:this.geoBoundryId});
            geoBoundryListSubView.render();
        }
    });

    return GeoBoundryAssociationEditView;
});

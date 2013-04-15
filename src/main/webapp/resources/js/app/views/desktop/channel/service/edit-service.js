define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/channel/servicetype/servicetype',
    'app/collections/channel/serviceprotocoladapter/serviceprotocoladapter',
    'app/collections/channel/servicemode/servicemode',
    'app/collections/channel/servicecategory/servicecategory',
    'text!../../../../../../templates/desktop/channel/servicetype/servicetype-list-subview.html',
    'text!../../../../../../templates/desktop/channel/serviceprotocoladapter/serviceprotocoladapter-list-subview.html',
    'text!../../../../../../templates/desktop/channel/servicemode/servicemode-list-subview.html',
    'text!../../../../../../templates/desktop/channel/servicecategory/servicecategory-list-subview.html',
    'text!../../../../../../templates/desktop/channel/service/edit-service.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceTypes, ServiceProtocolAdapters, ServiceModes, ServiceCategorys, serviceTypeListSubViewTemplate, serviceProtocolAdapterListSubViewTemplate, serviceModeListSubViewTemplate, serviceCategoryListSubViewTemplate, ServiceEditTemplate) {
	
    var ServiceTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#serviceTypeSelectContainerDiv'), serviceTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var serviceTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            serviceTypesFetch.done(function (){
                utilities.applyTemplate($('#serviceTypeSelectContainerDiv'), serviceTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"serviceType", 
            	fieldName:entities_strings.servicetype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ServiceProtocolAdapterListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#serviceProtocolAdapterSelectContainerDiv'), serviceProtocolAdapterListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var serviceProtocolAdaptersFetch = this.model.fetch();
            // Re render the template when the data is available    
            serviceProtocolAdaptersFetch.done(function (){
                utilities.applyTemplate($('#serviceProtocolAdapterSelectContainerDiv'), serviceProtocolAdapterListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"serviceProtocolAdapter", 
            	fieldName:entities_strings.serviceprotocoladapter, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ServiceModeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#serviceModeSelectContainerDiv'), serviceModeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var serviceModesFetch = this.model.fetch();
            // Re render the template when the data is available    
            serviceModesFetch.done(function (){
                utilities.applyTemplate($('#serviceModeSelectContainerDiv'), serviceModeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"serviceMode", 
            	fieldName:entities_strings.servicemode, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ServiceCategoryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#serviceCategorySelectContainerDiv'), serviceCategoryListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var serviceCategorysFetch = this.model.fetch();
            // Re render the template when the data is available    
            serviceCategorysFetch.done(function (){
                utilities.applyTemplate($('#serviceCategorySelectContainerDiv'), serviceCategoryListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"serviceCategory", 
            	fieldName:entities_strings.servicecategory, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ServiceEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceEditTemplate;
        },
        events:
        {
            'submit #edit-service-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-service');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.serviceTypeId = this.model.attributes.serviceType
		    	this.serviceProtocolAdapterId = this.model.attributes.serviceProtocolAdapter
		    	this.serviceModeId = this.model.attributes.serviceMode
		    	this.serviceCategoryId = this.model.attributes.serviceCategory
            }
            // ServiceTypes
            var serviceTypes = new ServiceTypes();
            serviceTypeListSubView = new ServiceTypeListSubView({model:serviceTypes, el:$('#serviceTypeSelectContainerDiv'), selectedOption:this.serviceTypeId});
            serviceTypeListSubView.render();
            // ServiceProtocolAdapters
            var serviceProtocolAdapters = new ServiceProtocolAdapters();
            serviceProtocolAdapterListSubView = new ServiceProtocolAdapterListSubView({model:serviceProtocolAdapters, el:$('#serviceProtocolAdapterSelectContainerDiv'), selectedOption:this.serviceProtocolAdapterId});
            serviceProtocolAdapterListSubView.render();
            // ServiceModes
            var serviceModes = new ServiceModes();
            serviceModeListSubView = new ServiceModeListSubView({model:serviceModes, el:$('#serviceModeSelectContainerDiv'), selectedOption:this.serviceModeId});
            serviceModeListSubView.render();
            // ServiceCategorys
            var serviceCategorys = new ServiceCategorys();
            serviceCategoryListSubView = new ServiceCategoryListSubView({model:serviceCategorys, el:$('#serviceCategorySelectContainerDiv'), selectedOption:this.serviceCategoryId});
            serviceCategoryListSubView.render();
        }
    });

    return ServiceEditView;
});

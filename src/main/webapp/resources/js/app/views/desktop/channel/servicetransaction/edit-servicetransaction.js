define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/channel/service/service',
    'app/collections/channel/servicetransactiontype/servicetransactiontype',
    'text!../../../../../../templates/desktop/channel/service/service-list-subview.html',
    'text!../../../../../../templates/desktop/channel/servicetransactiontype/servicetransactiontype-list-subview.html',
    'text!../../../../../../templates/desktop/channel/servicetransaction/edit-servicetransaction.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Services, ServiceTransactionTypes, serviceListSubViewTemplate, serviceTransactionTypeListSubViewTemplate, ServiceTransactionEditTemplate) {
	
    var ServiceListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#serviceSelectContainerDiv'), serviceListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var servicesFetch = this.model.fetch();
            // Re render the template when the data is available    
            servicesFetch.done(function (){
                utilities.applyTemplate($('#serviceSelectContainerDiv'), serviceListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"service", 
            	fieldName:entities_strings.service, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var ServiceTransactionTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#serviceTransactionTypeSelectContainerDiv'), serviceTransactionTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var serviceTransactionTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            serviceTransactionTypesFetch.done(function (){
                utilities.applyTemplate($('#serviceTransactionTypeSelectContainerDiv'), serviceTransactionTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"serviceTransactionType", 
            	fieldName:entities_strings.servicetransactiontype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ServiceTransactionEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServiceTransactionEditTemplate;
        },
        events:
        {
            'submit #edit-servicetransaction-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicetransaction');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.serviceId = this.model.attributes.service
		    	this.serviceTransactionTypeId = this.model.attributes.serviceTransactionType
            }
            // Services
            var services = new Services();
            serviceListSubView = new ServiceListSubView({model:services, el:$('#serviceSelectContainerDiv'), selectedOption:this.serviceId});
            serviceListSubView.render();
            // ServiceTransactionTypes
            var serviceTransactionTypes = new ServiceTransactionTypes();
            serviceTransactionTypeListSubView = new ServiceTransactionTypeListSubView({model:serviceTransactionTypes, el:$('#serviceTransactionTypeSelectContainerDiv'), selectedOption:this.serviceTransactionTypeId});
            serviceTransactionTypeListSubView.render();
        }
    });

    return ServiceTransactionEditView;
});

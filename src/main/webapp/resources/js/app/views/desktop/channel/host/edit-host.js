define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/channel/serviceprotocoladapter/serviceprotocoladapter',
    'app/collections/channel/hosttype/hosttype',
    'text!../../../../../../templates/desktop/channel/serviceprotocoladapter/serviceprotocoladapter-list-subview.html',
    'text!../../../../../../templates/desktop/channel/hosttype/hosttype-list-subview.html',
    'text!../../../../../../templates/desktop/channel/host/edit-host.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ServiceProtocolAdapters, HostTypes, serviceProtocolAdapterListSubViewTemplate, hostTypeListSubViewTemplate, HostEditTemplate) {
	
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
    
    var HostTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#hostTypeSelectContainerDiv'), hostTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var hostTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            hostTypesFetch.done(function (){
                utilities.applyTemplate($('#hostTypeSelectContainerDiv'), hostTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"hostType", 
            	fieldName:entities_strings.hosttype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var HostEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = HostEditTemplate;
        },
        events:
        {
            'submit #edit-host-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-host');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.serviceProtocolAdapterId = this.model.attributes.serviceProtocolAdapter
		    	this.hostTypeId = this.model.attributes.hostType
            }
            // ServiceProtocolAdapters
            var serviceProtocolAdapters = new ServiceProtocolAdapters();
            serviceProtocolAdapterListSubView = new ServiceProtocolAdapterListSubView({model:serviceProtocolAdapters, el:$('#serviceProtocolAdapterSelectContainerDiv'), selectedOption:this.serviceProtocolAdapterId});
            serviceProtocolAdapterListSubView.render();
            // HostTypes
            var hostTypes = new HostTypes();
            hostTypeListSubView = new HostTypeListSubView({model:hostTypes, el:$('#hostTypeSelectContainerDiv'), selectedOption:this.hostTypeId});
            hostTypeListSubView.render();
        }
    });

    return HostEditView;
});

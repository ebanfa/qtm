define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/channel/service/service',
    'app/collections/channel/host/host',
    'text!../../../../../../templates/desktop/channel/service/service-list-subview.html',
    'text!../../../../../../templates/desktop/channel/host/host-list-subview.html',
    'text!../../../../../../templates/desktop/channel/servicepeer/edit-servicepeer.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, Services, Hosts, serviceListSubViewTemplate, hostListSubViewTemplate, ServicePeerEditTemplate) {
	
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
    
    var HostListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#hostSelectContainerDiv'), hostListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var hostsFetch = this.model.fetch();
            // Re render the template when the data is available    
            hostsFetch.done(function (){
                utilities.applyTemplate($('#hostSelectContainerDiv'), hostListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"host", 
            	fieldName:entities_strings.host, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ServicePeerEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ServicePeerEditTemplate;
        },
        events:
        {
            'submit #edit-servicepeer-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-servicepeer');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.serviceId = this.model.attributes.service
		    	this.hostId = this.model.attributes.host
            }
            // Services
            var services = new Services();
            serviceListSubView = new ServiceListSubView({model:services, el:$('#serviceSelectContainerDiv'), selectedOption:this.serviceId});
            serviceListSubView.render();
            // Hosts
            var hosts = new Hosts();
            hostListSubView = new HostListSubView({model:hosts, el:$('#hostSelectContainerDiv'), selectedOption:this.hostId});
            hostListSubView.render();
        }
    });

    return ServicePeerEditView;
});

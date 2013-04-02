define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/order/productordertype/productordertype',
    'app/collections/party/party/party',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/order/productordertype/productordertype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/order/productorder/edit-productorder.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, ProductOrderTypes, Partys, Partys, productOrderTypeListSubViewTemplate, partyListSubViewTemplate, partyListSubViewTemplate, ProductOrderEditTemplate) {
	
    var ProductOrderTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderTypeSelectContainerDiv'), productOrderTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var productOrderTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrderTypesFetch.done(function (){
                utilities.applyTemplate($('#productOrderTypeSelectContainerDiv'), productOrderTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"productOrderType", 
            	fieldName:entities_strings.productordertype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"fromParty", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"toParty", 
            	fieldName:entities_strings.party, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var ProductOrderEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = ProductOrderEditTemplate;
        },
        events:
        {
            'submit #edit-productorder-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-productorder');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.productOrderTypeId = this.model.attributes.productOrderType
		    	this.partyId = this.model.attributes.party
		    	this.partyId = this.model.attributes.party
            }
            // ProductOrderTypes
            var productOrderTypes = new ProductOrderTypes();
            productOrderTypeListSubView = new ProductOrderTypeListSubView({model:productOrderTypes, el:$('#productOrderTypeSelectContainerDiv'), selectedOption:this.productOrderTypeId});
            productOrderTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return ProductOrderEditView;
});

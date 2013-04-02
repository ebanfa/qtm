define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/party/postaladdress/postaladdress',
    'app/collections/businessdata/geoboundry/geoboundry',
    'text!../../../../../../templates/desktop/party/postaladdress/postaladdress-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/postaladdressboundry/edit-postaladdressboundry.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, PostalAddresss, GeoBoundrys, postalAddressListSubViewTemplate, geoBoundryListSubViewTemplate, PostalAddressBoundryEditTemplate) {
	
    var PostalAddressListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#postalAddressSelectContainerDiv'), postalAddressListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var postalAddresssFetch = this.model.fetch();
            // Re render the template when the data is available    
            postalAddresssFetch.done(function (){
                utilities.applyTemplate($('#postalAddressSelectContainerDiv'), postalAddressListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"postalAddress", 
            	fieldName:entities_strings.postaladdress, 
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
            	relatedFieldName:"geoBoundry", 
            	fieldName:entities_strings.geoboundry, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var PostalAddressBoundryEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = PostalAddressBoundryEditTemplate;
        },
        events:
        {
            'submit #edit-postaladdressboundry-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-postaladdressboundry');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.postalAddressId = this.model.attributes.postalAddress
		    	this.geoBoundryId = this.model.attributes.geoBoundry
            }
            // PostalAddresss
            var postalAddresss = new PostalAddresss();
            postalAddressListSubView = new PostalAddressListSubView({model:postalAddresss, el:$('#postalAddressSelectContainerDiv'), selectedOption:this.postalAddressId});
            postalAddressListSubView.render();
            // GeoBoundrys
            var geoBoundrys = new GeoBoundrys();
            geoBoundryListSubView = new GeoBoundryListSubView({model:geoBoundrys, el:$('#geoBoundrySelectContainerDiv'), selectedOption:this.geoBoundryId});
            geoBoundryListSubView.render();
        }
    });

    return PostalAddressBoundryEditView;
});

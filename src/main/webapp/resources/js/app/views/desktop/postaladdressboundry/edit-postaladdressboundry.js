define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/postaladdress/postaladdress',
    'app/collections/geoboundry/geoboundry',
    'text!../../../../../templates/desktop/postaladdress/postaladdress-list-subview.html',
    'text!../../../../../templates/desktop/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../templates/desktop/postaladdressboundry/edit-postaladdressboundry.html'
], function (utilities, config, formUtilities, entities_strings, PostalAddresss, GeoBoundrys, postalAddressListSubViewTemplate, geoBoundryListSubViewTemplate, PostalAddressBoundryEditTemplate) {
	
    var PostalAddressListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#postalAddressSelectContainerDiv'), postalAddressListSubViewTemplate,  {model:self.model, relatedFieldName:"postalAddress", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var postalAddresssFetch = this.model.fetch();
            // Re render the template when the data is available    
            postalAddresssFetch.done(function (){
                utilities.applyTemplate($('#postalAddressSelectContainerDiv'), postalAddressListSubViewTemplate,  {model:self.model, relatedFieldName:"postalAddress", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var GeoBoundryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundry", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundry", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PostalAddressBoundryEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(postaladdressboundry)
                    {
                        utilities.applyTemplate($(self.el), PostalAddressBoundryEditTemplate,  
                            {model:this.model, postaladdressboundry:postaladdressboundry, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PostalAddressBoundryEditTemplate,  
                    {model:this.model, postaladdressboundry:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-postaladdressboundry-form':'editPostalAddressBoundry'
            
        },
        editPostalAddressBoundry: function(event)
        {
            event.preventDefault();
            var postaladdressboundry = $(event.currentTarget).serializeObject();
            this.model.save(postaladdressboundry, { 
                'success': function ()
                {
                    utilities.navigate('list-postaladdressboundry');
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

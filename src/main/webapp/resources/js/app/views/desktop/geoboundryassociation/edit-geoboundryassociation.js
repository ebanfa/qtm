define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/geoboundry/geoboundry',
    'app/collections/geoboundry/geoboundry',
    'text!../../../../../templates/desktop/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../templates/desktop/geoboundry/geoboundry-list-subview.html',
    'text!../../../../../templates/desktop/geoboundryassociation/edit-geoboundryassociation.html'
], function (utilities, config, formUtilities, entities_strings, GeoBoundrys, GeoBoundrys, geoBoundryListSubViewTemplate, geoBoundryListSubViewTemplate, GeoBoundryAssociationEditTemplate) {
	
    var GeoBoundryListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundryByToGeoId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundryByToGeoId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
            utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundryByFromGeoId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var geoBoundrysFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundrysFetch.done(function (){
                utilities.applyTemplate($('#geoBoundrySelectContainerDiv'), geoBoundryListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundryByFromGeoId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var GeoBoundryAssociationEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(geoboundryassociation)
                    {
                        utilities.applyTemplate($(self.el), GeoBoundryAssociationEditTemplate,  
                            {model:this.model, geoboundryassociation:geoboundryassociation, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), GeoBoundryAssociationEditTemplate,  
                    {model:this.model, geoboundryassociation:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-geoboundryassociation-form':'editGeoBoundryAssociation'
            
        },
        editGeoBoundryAssociation: function(event)
        {
            event.preventDefault();
            var geoboundryassociation = $(event.currentTarget).serializeObject();
            this.model.save(geoboundryassociation, { 
                'success': function ()
                {
                    utilities.navigate('list-geoboundryassociation');
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

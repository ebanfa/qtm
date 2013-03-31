define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/geoboundarytype/geoboundarytype',
    'text!../../../../../templates/desktop/geoboundarytype/geoboundarytype-list-subview.html',
    'text!../../../../../templates/desktop/geoboundry/edit-geoboundry.html'
], function (utilities, config, formUtilities, entities_strings, GeoBoundaryTypes, geoBoundaryTypeListSubViewTemplate, GeoBoundryEditTemplate) {
	
    var GeoBoundaryTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#geoBoundaryTypeSelectContainerDiv'), geoBoundaryTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundaryType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var geoBoundaryTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            geoBoundaryTypesFetch.done(function (){
                utilities.applyTemplate($('#geoBoundaryTypeSelectContainerDiv'), geoBoundaryTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"geoBoundaryType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var GeoBoundryEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(geoboundry)
                    {
                        utilities.applyTemplate($(self.el), GeoBoundryEditTemplate,  
                            {model:this.model, geoboundry:geoboundry, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), GeoBoundryEditTemplate,  
                    {model:this.model, geoboundry:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-geoboundry-form':'editGeoBoundry'
            
        },
        editGeoBoundry: function(event)
        {
            event.preventDefault();
            var geoboundry = $(event.currentTarget).serializeObject();
            this.model.save(geoboundry, { 
                'success': function ()
                {
                    utilities.navigate('list-geoboundry');
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

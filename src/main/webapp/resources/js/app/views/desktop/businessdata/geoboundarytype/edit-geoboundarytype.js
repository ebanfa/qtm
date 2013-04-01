define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/businessdata/geoboundarytype/geoboundarytype',
    'text!../../../../../../templates/desktop/businessdata/geoboundarytype/geoboundarytype-list-subview.html',
    'text!../../../../../../templates/desktop/businessdata/geoboundarytype/edit-geoboundarytype.html'
], function (utilities, config, formUtilities, entities_strings, GeoBoundaryTypes, geoBoundaryTypeListSubViewTemplate, GeoBoundaryTypeEditTemplate) {
	
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
    
	
    var GeoBoundaryTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(geoboundarytype)
                    {
                        utilities.applyTemplate($(self.el), GeoBoundaryTypeEditTemplate,  
                            {model:this.model, geoboundarytype:geoboundarytype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), GeoBoundaryTypeEditTemplate,  
                    {model:this.model, geoboundarytype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-geoboundarytype-form':'editGeoBoundaryType'
            
        },
        editGeoBoundaryType: function(event)
        {
            event.preventDefault();
            var geoboundarytype = $(event.currentTarget).serializeObject();
            this.model.save(geoboundarytype, { 
                'success': function ()
                {
                    utilities.navigate('list-geoboundarytype');
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

    return GeoBoundaryTypeEditView;
});

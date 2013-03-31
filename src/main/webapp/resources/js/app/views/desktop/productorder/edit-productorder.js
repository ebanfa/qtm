define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/productordertype/productordertype',
    'text!../../../../../templates/desktop/productordertype/productordertype-list-subview.html',
    'text!../../../../../templates/desktop/productorder/edit-productorder.html'
], function (utilities, config, formUtilities, entities_strings, ProductOrderTypes, productOrderTypeListSubViewTemplate, ProductOrderEditTemplate) {
	
    var ProductOrderTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#productOrderTypeSelectContainerDiv'), productOrderTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrderType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var productOrderTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            productOrderTypesFetch.done(function (){
                utilities.applyTemplate($('#productOrderTypeSelectContainerDiv'), productOrderTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"productOrderType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ProductOrderEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(productorder)
                    {
                        utilities.applyTemplate($(self.el), ProductOrderEditTemplate,  
                            {model:this.model, productorder:productorder, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ProductOrderEditTemplate,  
                    {model:this.model, productorder:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-productorder-form':'editProductOrder'
            
        },
        editProductOrder: function(event)
        {
            event.preventDefault();
            var productorder = $(event.currentTarget).serializeObject();
            this.model.save(productorder, { 
                'success': function ()
                {
                    utilities.navigate('list-productorder');
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
		    	this.productOrderTypeId = this.model.attributes.productOrderType
            }
            // ProductOrderTypes
            var productOrderTypes = new ProductOrderTypes();
            productOrderTypeListSubView = new ProductOrderTypeListSubView({model:productOrderTypes, el:$('#productOrderTypeSelectContainerDiv'), selectedOption:this.productOrderTypeId});
            productOrderTypeListSubView.render();
        }
    });

    return ProductOrderEditView;
});

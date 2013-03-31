define([
    'utilities',
    'configuration',
    'app/collections/product/product',
    'app/collections/producttype/producttype',
    'text!../../../../../templates/mobile/productorder/edit-productorder.html',
    'text!../../../../../templates/mobile/product/product-list-subview.html',
    'text!../../../../../templates/mobile/producttype/producttype-list-subview.html'
], function (utilities, config, Products,  ProductTypes, productOrderCreateTemplate, productListSubViewTemplate, productTypeListSubViewTemplate) {

    var ProductTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'productTypeSelected');
        },
        events: {
            "change .ui-btn-text": "productTypeSelected"
        },
        productTypeSelected: function() {
            console.log('Crazy world');
        },
        render:function () 
        {     
            var self = this;  
            utilities.applyTemplate($('#productTypeSelectContainerDiv'), productTypeListSubViewTemplate,  {model:self.model});
            // Fetch data and reload template when done.
            var productTypesFetch = self.model.fetch();
            productTypesFetch.done(function (){
                utilities.applyTemplate($('#productTypeSelectContainerDiv'), productTypeListSubViewTemplate,  {model:self.model});
                $('#productTypeSelect').selectmenu();
                $('.ui-page').trigger('create');
                $('#productTypeSelect').trigger('change');
            });
            return self;
        }
    });

    var ProductListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {      
            utilities.applyTemplate($('#productSelectContainerDiv'), productListSubViewTemplate,  {model:this.model});
        }


    });
    
    var ProductOrderCreateView = Backbone.View.extend({

        render:function () {
            utilities.applyTemplate($(this.el), productOrderCreateTemplate, {model:this.model, productorder:null});
            //Product types
            var productTypes = new ProductTypes();
            productTypeListSubView = new ProductTypeListSubView({model:productTypes, el:$('#productTypesSelectContainerDiv')});
            productTypeListSubView.render();
            //Products
            var products = new Products();
            productListSubView = new ProductListSubView({model:products, el:$('#productsSelectContainerDiv')});
            productListSubView.render();
            /*//Product uoms
            var productUoms = new ProductUoms();
            productUomListSubView = new ProductUomListSubView({model:productUoms, el:$('#productUomsSelectContainerDiv')});
            productUomListSubView.render();*/

            //productTypeListSubView.productListSubView = productListSubView;

            $(this.el).trigger('pagecreate');
            return this;
        }

    });

    return ProductOrderCreateView;
});
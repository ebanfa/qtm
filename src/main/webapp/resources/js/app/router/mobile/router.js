/**
 * A module for the router of the desktop application.
 *
 */
define("router",[
    'jquery',
    'jquerymobile',
    'underscore',
    'utilities',
    'app/models/productorder/productorder',
    'app/collections/productorder/productorder',
    'app/models/productcategory/productcategory',
    'app/collections/productcategory/productcategory',
    'app/views/mobile/login/login',
    'app/views/mobile/productorder/list-productorder',
    'app/views/mobile/productorder/edit-productorder',
    'app/views/mobile/productcategory/list-productcategory',
    'app/views/mobile/productcategory/edit-productcategory',
    'text!../templates/mobile/home.html'
],function ($,
            jqm,
            _,
            utilities,
            ProductOrder,
            ProductOrders,
            ProductCategory,
            ProductCategories,
            LoginView,
            ProductOrdersView,
            ProductOrderCreateView,
            ProductCategoriesView,
            ProductCategoryDetailView,
            HomeViewTemplate) {

    // prior to creating an starting the router, we disable jQuery Mobile's own routing mechanism
    $.mobile.hashListeningEnabled = false;
    $.mobile.linkBindingEnabled = false;
    $.mobile.pushStateEnabled = false;

    /**
     * The Router class contains all the routes within the application - i.e. URLs and the actions
     * that will be taken as a result.
     *
     * @type {Router}
     */
    var Router = Backbone.Router.extend({
        routes:{
            "home":"home",
            "":"showLoginPage",
            "product-categories":"productCategories",
            "product-categories/:id":"productCategoryDetail",
            "product-orders":"productOrders",
            "product-order-create":"createProductOrder",
            "payment-create":"createPayment",
            "messages":"showMailBox",
            "edit-outbound-message":"editOutboundMessage",
            "edit-outbound-message/:id":"editOutboundMessage",
            "ignore":"ignore",
            "*actions":"defaultHandler"
        },

        defaultHandler:function (actions) {
            if ("" != actions) {
                $.mobile.changePage("#" + actions, {transition:'slide', changeHash:false, allowSamePageTransition:true});
            }
        },
        home:function () {
            utilities.applyTemplateAndTrigger(HomeViewTemplate);
        },
        showLoginPage: function(id) { 
            //var model = new ProductCategories();
            var loginView = new LoginView({model:null, el:$("#container")});
            utilities.viewManager.showView(loginView)
        },
        productCategories:function () {
            var model = new ProductCategories();
            var productCategoriesView = new ProductCategoriesView({model:model, el:$("#container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productCategoriesView);
                }).fetch();
        },
        productCategoryDetail:function (id) {
            var model = new ProductCategory({id:id});
            var productCategoryDetailView = new ProductCategoryDetailView({model:model, el:$("#container")});
            model.bind("change",
                function () {
                    utilities.viewManager.showView(productCategoryDetailView);
                    $.mobile.changePage($("#container"), {transition:'slide', changeHash:false});
                }).fetch();
        },
        productOrders:function () {
            var model = new ProductOrders();
            var productOrdersView = new ProductOrdersView({model:model, el:$("#container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(productOrdersView);
                }).fetch();
        },
        createProductOrder:function (id) {
            var model = new ProductOrder({id:id});
            var productOrderCreateView = new ProductOrderCreateView({model:model, el:$("#container")});
            utilities.viewManager.showView(productOrderCreateView);
        },
        createPayment:function (id) {
            var model = new Payment({id:id});
            var paymentCreateView = new PaymentCreateView({model:model, el:$("#container")});
            utilities.viewManager.showView(paymentCreateView);
        },
        showMailBox:  function() {
            var model = new OutboundMessages();
            var outboundMessagesView = new OutboundMessagesView({model:model, el:$("#container")});
            model.bind("reset",
                function () {
                    utilities.viewManager.showView(outboundMessagesView);
                }).fetch();
        }
    });

    // Create a router instance
    var router = new Router();

    // Begin routing
    Backbone.history.start();

    return router;
});
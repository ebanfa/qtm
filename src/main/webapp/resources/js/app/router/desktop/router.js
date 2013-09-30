/**
 * A module for the router of the desktop application
 */
define("router", [
    'jquery',
    'underscore',
    'configuration',
    'utilities',
    'app/views/desktop/login/loginView',
    'app/views/desktop/home',
    'app/views/desktop/home/customer-home',
    'app/views/desktop/home/advice-home',
    'app/views/desktop/home/channel-home',
    'app/views/desktop/home/notification-home',
    'app/views/desktop/home/order-home',
    'app/views/desktop/home/invoice-home',
    'app/views/desktop/home/payment-home',
    'app/views/desktop/home/messaging-home',
    'app/views/desktop/home/businessdata-home',
    'app/views/desktop/home/report-home',
    'app/views/desktop/home/security-home',
    'app/models/activity/activity',
    'app/collections/activity/activity',
    'app/views/desktop/base/base-entity-view-view',
   // 'app/views/desktop/activity/view-activity',
    'app/views/desktop/activity/edit-activity',
    'app/views/desktop/base/base-entity-list-view',
  //  'app/views/desktop/base/list-activity',
    'app/views/desktop/activity/transaction/list-activity',
    'text!../templates/desktop/main.html',
    'text!../templates/desktop/home/navbar.html',
    'text!../templates/desktop/home/footer.html',
    'text!../templates/desktop/home/sidebar.html',
    'text!../templates/desktop/home/subnavbar.html'
],function ($,
            _,
            config,
            utilities,
            LoginView,
            HomeView,
            CustomerHomeView,
            AdviceHomeView,
            ChannelHomeView,
            NotificationHomeView,
            OrderHomeView,
            InvoiceHomeView,
            PaymentHomeView,
            MessagingHomeView,
            BusinessDataHomeView,
            ReportsHomeView,
            SecurityHomeView,
            ActivityModel,
            ActivityCollection,
            //ActivityViewView,
            ViewActivityView,
            ActivityEditView,
            ListActivityView,
           // ActivityListView,
            TransactionListView,
            MainTemplate, navBarTemplate, footerTemplate, 
            sideBarTemplate, subNavBarTemplate, homeContentTemplate) {

    $(document).ready(new function() 
    {
        var NavBarView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                utilities.applyTemplate($('#navbar-container'), navBarTemplate,  {});
                return this;
            },
            events:
            {
                'click .logout-link':'handleLogout'
            },
            handleLogout:function(event)
            {
                //event.preventDefault();
            }
        });

        var SubNavBarView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                utilities.applyTemplate($('#subnavbar-container'), subNavBarTemplate,  {});
                return this;
            }
        });

        var SideBarView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                utilities.applyTemplate($('#sidebar-container'), sideBarTemplate,  {});
                return this;
            }
        });

        var FooterView = Backbone.View.extend({
            initialize: function () {
                _.bindAll(this, 'render');
            },
            render:function () 
            {           
                utilities.applyTemplate($('#footer-container'), footerTemplate,  {});
                return this;
            }
        });

        utilities.applyTemplate($('body'), MainTemplate)
        // Render NavBar
        navbarView = new NavBarView({el:$('#navbar-container')});
        navbarView.render();
        // Render SubNavBar
        subNavBarView = new SubNavBarView({el:$('#subnavbar-container')});
        subNavBarView.render();
        // Render SideBarView
        sideView = new SideBarView({el:$('#sidebar-container')});
        sideView.render();
        // Render SideBarView
        footerView = new FooterView({el:$('#footer-container')});
        footerView.render();
    });

    /**
     * The Router class contains all the routes within the application -
     * i.e. URLs and the actions that will be taken as a result.
     *
     * @type {Router}
     */

    var Router = Backbone.Router.extend({
        routes : {
            "":"login",
            "login":"login",
            "home":"home",
            "customer-module":"customerModuleIndex",
            "advice-module":"adviceModuleIndex",
            "channel-module":"channelModuleIndex",
            "notification-module":"notificationModuleIndex",
            "order-module":"orderModuleIndex",
            "datasource-module":"datasourceModuleIndex",
            "payment-module":"paymentModuleIndex",
            "messaging-module":"messagingModuleIndex",
            "businessdata-module":"businessDataModuleIndex",
            "reports-module":"reportsModuleIndex",
            "security-module":"securityModuleIndex",

            "list/:activityURL":"showActivityList",
            //"view/:activityURL":"showActivityView",
            "view/:activityURL/:entityId":"showActivityView",
            "edit/:activityURL":"showActivityEdit",
            "edit/:activityURL/:entityId":"showActivityEdit",

            "about":"home"
        },
        home : function () {
            utilities.viewManager.showView(new HomeView({el:$("#content-container")}));
        },
        login:function()
        {
            var loginView = new LoginView({el:$("#page-container")});
            utilities.viewManager.showView(loginView);

        },
        showActivityList:function(activityURL)
        {
            var model = new ActivityCollection({activityURL:activityURL});
            if(activityURL == 'servicetransaction')
                var activityListView = new TransactionListView({model:model, el:$("#content-container")});
            else
                var activityListView = new ListActivityView({model:model, el:$("#content-container")});
                //var activityListView = new ActivityListView({model:model, el:$("#content-container")});
        },
     
        showActivityView:function(activityURL, entityId)
        {
        	console.log('The activity URL is:' + activityURL + ' the entity id is: ' + entityId);
            var model = new ActivityModel({activityURL:activityURL, entityId:entityId});
            var viewActivityView = new ViewActivityView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(viewActivityView);
        },
        showActivityEdit:function(activityURL, id)
        {
            var model = new ActivityModel({activityURL:activityURL, id:id});
            var activityEditView = new ActivityEditView({model:model, el:$("#content-container")});
            utilities.viewManager.showView(activityEditView);
        },
        customerModuleIndex:function()
        {
            utilities.viewManager.showView(new CustomerHomeView({el:$("#content-container")}));
        },
        adviceModuleIndex:function()
        {
            utilities.viewManager.showView(new AdviceHomeView({el:$("#content-container")}));
        },
        channelModuleIndex:function()
        {
            utilities.viewManager.showView(new ChannelHomeView({el:$("#content-container")}));
        },
        notificationModuleIndex:function()
        {
            utilities.viewManager.showView(new NotificationHomeView({el:$("#content-container")}));
        },
        orderModuleIndex:function()
        {
            utilities.viewManager.showView(new OrderHomeView({el:$("#content-container")}));
        },
        datasourceModuleIndex:function()
        {
            utilities.viewManager.showView(new InvoiceHomeView({el:$("#content-container")}));
        },
        paymentModuleIndex:function()
        {
            utilities.viewManager.showView(new PaymentHomeView({el:$("#content-container")}));
        },
        messagingModuleIndex:function()
        {
            utilities.viewManager.showView(new MessagingHomeView({el:$("#content-container")}));
        },
        businessDataModuleIndex:function()
        {
            utilities.viewManager.showView(new BusinessDataHomeView({el:$("#content-container")}));
        },
        reportsModuleIndex:function()
        {
            utilities.viewManager.showView(new ReportsHomeView({el:$("#content-container")}));
        },
        securityModuleIndex:function()
        {
            utilities.viewManager.showView(new SecurityHomeView({el:$("#content-container")}));
        }
    });
    
    // Create a router instance
    var router = new Router();

    //Begin routing
    Backbone.history.start();

    return router;
});
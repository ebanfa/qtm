/**
 * Shortcut alias definitions - will come in handy when declaring dependencies
 * Also, they allow you to keep the code free of any knowledge about library
 * locations and versions
 */
requirejs.config({
    baseUrl: "resources/js",
    paths: {
        jquery:'libs/jquery-1.7.1',
        underscore:'libs/underscore',
        text:'libs/text',
        i18n:'libs/i18n',
        order:'libs/order',
        bootstrap: 'libs/bootstrap',
        backbone: 'libs/backbone',
        utilities: 'app/utilities',
        router:'app/router/desktop/router'
    },
    // We shim Backbone since it doesn't declare an AMD module
    shim: {
        'backbone': {
            deps: ['jquery', 'underscore'],
            exports: 'Backbone'
        }
    }
});

define("initializer", ["jquery"],
    function ($) {
    //$('head').append('<link type="text/css" rel="stylesheet" href="resources/css/screen.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/jquery-ui.min.css" />');
    $('head').append('<link rel="stylesheet" href="resources/css/bootstrap.css" type="text/css" media="all"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/bootstrap-responsive.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/css.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/font-awesome.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/base-admin-2.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/base-admin-responsive.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/dashboard.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/signin.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/datetimepicker.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/alantra.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/notifier-theme-plastic.css"/>');
    $('head').append('<link rel="stylesheet" href="resources/css/jquery.jqplot.min.css" />');

    $('head').append('<script type="text/javascript" src="resources/js/libs/jquery-ui.min.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/excanvas.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/jquery.jqplot.min.js"></script>');
    
    
    $('head').append('<script type="text/javascript" src="resources/js/libs/bootstrap.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/base.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/area.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/donut.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/signin.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/bootstrap-datetimepicker.min.js"></script>');
    $('head').append('<script type="text/javascript" src="resources/js/libs/backbone.notifier.js"></script>');

    //$('head').append('<link rel="stylesheet" href="resources/css/custom.css" type="text/css" media="all">');
    //$('head').append('<link href="http://fonts.googleapis.com/css?family=Rokkitt" rel="stylesheet" type="text/css">');
});

// Now we declare all the dependencies
require([
    'order!initializer',
    'order!router'
], function(){
});

define("configuration", {
    baseUrl : "",
    entitySearchFieldPath: "/searchFields"
});
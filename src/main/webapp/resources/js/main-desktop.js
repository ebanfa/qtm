/**
 * Shortcut alias definitions - will come in handy when declaring dependencies
 * Also, they allow you to keep the code free of any knowledge about library
 * locations and versions
 */
require.config({
    paths: {
        jquery:'libs/jquery-1.7.1',
        underscore:'libs/underscore',
        text:'libs/text',
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

// Backbone is not AMD-ready, so a individual module is declared
define("backbone", [
    // the order plugin is used to ensure that the modules are loaded in the right order
    'order!jquery',
    'order!underscore',
    'order!libs/backbone'], function(){
    return Backbone;
});

// Now we declare all the dependencies
require([
    'order!jquery',
    'order!underscore',
    'order!backbone',
    'text',
    'order!bootstrap',
], function(){
    console.log('all loaded')
});
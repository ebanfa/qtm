/**
 * Module for the TelecommunicationsNumbers collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/telecommunicationsnumber/telecommunicationsnumber',
    'configuration',
    'backbone'
], function (TelecommunicationsNumber, config) {
    /**
     *  Here we define the TelecommunicationsNumber collection
     *  We will use it for CRUD operations on Bookings
     */
    var TelecommunicationsNumbers = Backbone.Collection.extend({
        url: config.baseUrl + "rest/telecommunicationsnumber", // the URL for performing CRUD operations
        model: TelecommunicationsNumber,
        id:"id" // the 'id' property of the model is the identifier
    });
    return TelecommunicationsNumbers;
});
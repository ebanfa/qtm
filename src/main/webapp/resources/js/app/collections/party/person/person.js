/**
 * Module for the Persons collection
 */
define([
    // The collection element type and configuration are dependencies
    'app/models/party/person/person',
    'configuration',
    'backbone'
], function (Person, config) {
    /**
     *  Here we define the Person collection
     *  We will use it for CRUD operations on Bookings
     */
    var Persons = Backbone.Collection.extend({
        url: config.baseUrl + "rest/person", // the URL for performing CRUD operations
        model: Person,
        id:"id" // the 'id' property of the model is the identifier
    });
    return Persons;
});
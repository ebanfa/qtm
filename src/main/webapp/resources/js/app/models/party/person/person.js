/**
 * Module for the Person model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The Person model class definition
     * Used for CRUD operations against individual Person
     */
    var Person = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/person', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.party) {
            	errors.push({name: 'party', message: entities_strings.alantra_form_field_required + entities_strings.person_party + '.'});
        	}	
            if (!attrs.currentFNm) {
            	errors.push({name: 'currentFNm', message: entities_strings.alantra_form_field_required + entities_strings.person_currentfnm + '.'});
        	}	
            if (!attrs.gender) {
            	errors.push({name: 'gender', message: entities_strings.alantra_form_field_required + entities_strings.person_gender + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.person_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.person_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.person_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the Person class
    return Person;
});
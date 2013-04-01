/**
 * Module for the CommunicationEventPurposeType model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CommunicationEventPurposeType model class definition
     * Used for CRUD operations against individual CommunicationEventPurposeType
     */
    var CommunicationEventPurposeType = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/communicationeventpurposetype', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurposetype_name + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurposetype_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurposetype_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurposetype_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CommunicationEventPurposeType class
    return CommunicationEventPurposeType;
});
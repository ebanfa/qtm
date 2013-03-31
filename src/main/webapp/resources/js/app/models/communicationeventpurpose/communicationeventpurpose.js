/**
 * Module for the CommunicationEventPurpose model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CommunicationEventPurpose model class definition
     * Used for CRUD operations against individual CommunicationEventPurpose
     */
    var CommunicationEventPurpose = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/communicationeventpurpose', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.communicationEventPurposeType) {
            	errors.push({name: 'communicationEventPurposeType', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurpose_communicationeventpurposetype + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurpose_code + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurpose_name + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurpose_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.communicationeventpurpose_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CommunicationEventPurpose class
    return CommunicationEventPurpose;
});
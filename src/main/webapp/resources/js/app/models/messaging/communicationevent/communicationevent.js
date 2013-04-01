/**
 * Module for the CommunicationEvent model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The CommunicationEvent model class definition
     * Used for CRUD operations against individual CommunicationEvent
     */
    var CommunicationEvent = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/communicationevent', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.communicationEventType) {
            	errors.push({name: 'communicationEventType', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_communicationeventtype + '.'});
        	}	
            if (!attrs.communicationEventPurpose) {
            	errors.push({name: 'communicationEventPurpose', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_communicationeventpurpose + '.'});
        	}	
            if (!attrs.contactMechanismType) {
            	errors.push({name: 'contactMechanismType', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_contactmechanismtype + '.'});
        	}	
            if (!attrs.communicationEventStatusType) {
            	errors.push({name: 'communicationEventStatusType', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_communicationeventstatustype + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.communicationevent_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the CommunicationEvent class
    return CommunicationEvent;
});
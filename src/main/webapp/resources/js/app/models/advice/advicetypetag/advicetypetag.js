/**
 * Module for the AdviceTypeTag model
 */
define([ 
    'configuration',
    'i18n!app/nls/entities',
    'backbone'
], function (config, entities_strings) {
    /**
     * The AdviceTypeTag model class definition
     * Used for CRUD operations against individual AdviceTypeTag
     */
    var AdviceTypeTag = Backbone.Model.extend({
        urlRoot: config.baseUrl + 'rest/advicetypetag', // the URL for performing CRUD operations
        
        validate: function (attrs) {
            var errors = [];
            if (!attrs.adviceType) {
            	errors.push({name: 'adviceType', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_advicetype + '.'});
        	}	
            if (!attrs.name) {
            	errors.push({name: 'name', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_name + '.'});
        	}	
            if (!attrs.adviceTyTagVal) {
            	errors.push({name: 'adviceTyTagVal', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_advicetytagval + '.'});
        	}	
            if (!attrs.isRegexFg) {
            	errors.push({name: 'isRegexFg', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_isregexfg + '.'});
        	}	
            if (!attrs.code) {
            	errors.push({name: 'code', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_code + '.'});
        	}	
            if (!attrs.effectiveDt) {
            	errors.push({name: 'effectiveDt', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_effectivedt + '.'});
        	}	
            if (!attrs.recSt) {
            	errors.push({name: 'recSt', message: entities_strings.alantra_form_field_required + entities_strings.advicetypetag_recst + '.'});
        	}	
            return errors.length > 0 ? errors : false;
        }
    });
    // export the AdviceTypeTag class
    return AdviceTypeTag;
});
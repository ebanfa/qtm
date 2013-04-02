define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/advice/advicetype/advicetype',
    'text!../../../../../../templates/desktop/advice/advicetype/advicetype-list-subview.html',
    'text!../../../../../../templates/desktop/advice/advicetypetag/edit-advicetypetag.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, AdviceTypes, adviceTypeListSubViewTemplate, AdviceTypeTagEditTemplate) {
	
    var AdviceTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#adviceTypeSelectContainerDiv'), adviceTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var adviceTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            adviceTypesFetch.done(function (){
                utilities.applyTemplate($('#adviceTypeSelectContainerDiv'), adviceTypeListSubViewTemplate,  self.getTemplateData());
            });
            return this;
        },
        getTemplateData: function()
        {
            var self = this;
            var templateData = 
            {
                idField:'id', 
            	model:self.model, 
            	relatedFieldName:"adviceType", 
            	fieldName:entities_strings.advicetype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var AdviceTypeTagEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = AdviceTypeTagEditTemplate;
        },
        events:
        {
            'submit #edit-advicetypetag-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-advicetypetag');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.adviceTypeId = this.model.attributes.adviceType
            }
            // AdviceTypes
            var adviceTypes = new AdviceTypes();
            adviceTypeListSubView = new AdviceTypeListSubView({model:adviceTypes, el:$('#adviceTypeSelectContainerDiv'), selectedOption:this.adviceTypeId});
            adviceTypeListSubView.render();
        }
    });

    return AdviceTypeTagEditView;
});

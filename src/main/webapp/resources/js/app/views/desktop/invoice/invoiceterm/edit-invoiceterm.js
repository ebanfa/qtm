define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
    'app/views/desktop/base/baseentityeditview',
        'app/collections/businessdata/termtype/termtype',
    'text!../../../../../../templates/desktop/businessdata/termtype/termtype-list-subview.html',
    'text!../../../../../../templates/desktop/invoice/invoiceterm/edit-invoiceterm.html'
], function (utilities, config, formUtilities, entities_strings, BaseEntityEditView, TermTypes, termTypeListSubViewTemplate, InvoiceTermEditTemplate) {
	
    var TermTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#termTypeSelectContainerDiv'), termTypeListSubViewTemplate,  this.getTemplateData());
            // Fetch data
            var termTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            termTypesFetch.done(function (){
                utilities.applyTemplate($('#termTypeSelectContainerDiv'), termTypeListSubViewTemplate,  self.getTemplateData());
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
            	relatedFieldName:"termType", 
            	fieldName:entities_strings.termtype, 
            	entities_strings:entities_strings, 
            	selectedOption:self.options.selectedOption
            };
            return templateData;
        }
    });
    
	
    var InvoiceTermEditView = BaseEntityEditView.extend({
    
        initialize: function(options)
        {
            this.entityTemplate = InvoiceTermEditTemplate;
        },
        events:
        {
            'submit #edit-invoiceterm-form':'saveEntity'
            
        },
        navigateToEntityList:function()
        {
            utilities.navigate('list-invoiceterm');
        },
        renderSubViews:function()
        {
            if (this.model.attributes.id)
            {
		    	this.termTypeId = this.model.attributes.termType
            }
            // TermTypes
            var termTypes = new TermTypes();
            termTypeListSubView = new TermTypeListSubView({model:termTypes, el:$('#termTypeSelectContainerDiv'), selectedOption:this.termTypeId});
            termTypeListSubView.render();
        }
    });

    return InvoiceTermEditView;
});

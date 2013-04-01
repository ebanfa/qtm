define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/party/party',
    'app/collections/party/caseroletype/caseroletype',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/caseroletype/caseroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/caserole/edit-caserole.html'
], function (utilities, config, formUtilities, entities_strings, Partys, CaseRoleTypes, partyListSubViewTemplate, caseRoleTypeListSubViewTemplate, CaseRoleEditTemplate) {
	
    var PartyListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partysFetch = this.model.fetch();
            // Re render the template when the data is available    
            partysFetch.done(function (){
                utilities.applyTemplate($('#partySelectContainerDiv'), partyListSubViewTemplate,  {model:self.model, relatedFieldName:"party", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var CaseRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#caseRoleTypeSelectContainerDiv'), caseRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"caseRoleType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var caseRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            caseRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#caseRoleTypeSelectContainerDiv'), caseRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"caseRoleType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var CaseRoleEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(caserole)
                    {
                        utilities.applyTemplate($(self.el), CaseRoleEditTemplate,  
                            {model:this.model, caserole:caserole, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CaseRoleEditTemplate,  
                    {model:this.model, caserole:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-caserole-form':'editCaseRole'
            
        },
        editCaseRole: function(event)
        {
            event.preventDefault();
            var caserole = $(event.currentTarget).serializeObject();
            this.model.save(caserole, { 
                'success': function ()
                {
                    utilities.navigate('list-caserole');
                },
                error: function (model, errors) 
                {
                    var errorMessage = "";
                     _.each(errors, function (error) {
                        errorMessage += error.message + "\n";
                    }, this);
                    alert(errorMessage);
                }
            });
            return false;
        },
        renderSubViews:function()
        {
            $('.date-picker').datetimepicker({
              format: 'dd/MM/yyyy',
              pickTime: false
            });
            if (this.model.attributes.id)
            {
		    	this.partyId = this.model.attributes.party
		    	this.caseRoleTypeId = this.model.attributes.caseRoleType
            }
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
            // CaseRoleTypes
            var caseRoleTypes = new CaseRoleTypes();
            caseRoleTypeListSubView = new CaseRoleTypeListSubView({model:caseRoleTypes, el:$('#caseRoleTypeSelectContainerDiv'), selectedOption:this.caseRoleTypeId});
            caseRoleTypeListSubView.render();
        }
    });

    return CaseRoleEditView;
});

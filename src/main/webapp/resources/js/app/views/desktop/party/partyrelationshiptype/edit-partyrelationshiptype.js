define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/partyroletype/partyroletype',
    'app/collections/party/partyroletype/partyroletype',
    'text!../../../../../../templates/desktop/party/partyroletype/partyroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyroletype/partyroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrelationshiptype/edit-partyrelationshiptype.html'
], function (utilities, config, formUtilities, entities_strings, PartyRoleTypes, PartyRoleTypes, partyRoleTypeListSubViewTemplate, partyRoleTypeListSubViewTemplate, PartyRelationshipTypeEditTemplate) {
	
    var PartyRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRoleTypeByFromRoleTyId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRoleTypeByFromRoleTyId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyRoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRoleTypeByToRoleTyId", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRoleTypeByToRoleTyId", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PartyRelationshipTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partyrelationshiptype)
                    {
                        utilities.applyTemplate($(self.el), PartyRelationshipTypeEditTemplate,  
                            {model:this.model, partyrelationshiptype:partyrelationshiptype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyRelationshipTypeEditTemplate,  
                    {model:this.model, partyrelationshiptype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partyrelationshiptype-form':'editPartyRelationshipType'
            
        },
        editPartyRelationshipType: function(event)
        {
            event.preventDefault();
            var partyrelationshiptype = $(event.currentTarget).serializeObject();
            this.model.save(partyrelationshiptype, { 
                'success': function ()
                {
                    utilities.navigate('list-partyrelationshiptype');
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
		    	this.partyRoleTypeId = this.model.attributes.partyRoleType
		    	this.partyRoleTypeId = this.model.attributes.partyRoleType
            }
            // PartyRoleTypes
            var partyRoleTypes = new PartyRoleTypes();
            partyRoleTypeListSubView = new PartyRoleTypeListSubView({model:partyRoleTypes, el:$('#partyRoleTypeSelectContainerDiv'), selectedOption:this.partyRoleTypeId});
            partyRoleTypeListSubView.render();
            // PartyRoleTypes
            var partyRoleTypes = new PartyRoleTypes();
            partyRoleTypeListSubView = new PartyRoleTypeListSubView({model:partyRoleTypes, el:$('#partyRoleTypeSelectContainerDiv'), selectedOption:this.partyRoleTypeId});
            partyRoleTypeListSubView.render();
        }
    });

    return PartyRelationshipTypeEditView;
});

define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/roletype/roletype',
    'app/collections/party/partyroletype/partyroletype',
    'text!../../../../../../templates/desktop/party/roletype/roletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyroletype/partyroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyroletype/edit-partyroletype.html'
], function (utilities, config, formUtilities, entities_strings, RoleTypes, PartyRoleTypes, roleTypeListSubViewTemplate, partyRoleTypeListSubViewTemplate, PartyRoleTypeEditTemplate) {
	
    var RoleTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#roleTypeSelectContainerDiv'), roleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"roleType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var roleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            roleTypesFetch.done(function (){
                utilities.applyTemplate($('#roleTypeSelectContainerDiv'), roleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"roleType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
            utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRoleType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyRoleTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyRoleTypesFetch.done(function (){
                utilities.applyTemplate($('#partyRoleTypeSelectContainerDiv'), partyRoleTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyRoleType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var PartyRoleTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partyroletype)
                    {
                        utilities.applyTemplate($(self.el), PartyRoleTypeEditTemplate,  
                            {model:this.model, partyroletype:partyroletype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyRoleTypeEditTemplate,  
                    {model:this.model, partyroletype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partyroletype-form':'editPartyRoleType'
            
        },
        editPartyRoleType: function(event)
        {
            event.preventDefault();
            var partyroletype = $(event.currentTarget).serializeObject();
            this.model.save(partyroletype, { 
                'success': function ()
                {
                    utilities.navigate('list-partyroletype');
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
		    	this.roleTypeId = this.model.attributes.roleType
		    	this.partyRoleTypeId = this.model.attributes.partyRoleType
            }
            // RoleTypes
            var roleTypes = new RoleTypes();
            roleTypeListSubView = new RoleTypeListSubView({model:roleTypes, el:$('#roleTypeSelectContainerDiv'), selectedOption:this.roleTypeId});
            roleTypeListSubView.render();
            // PartyRoleTypes
            var partyRoleTypes = new PartyRoleTypes();
            partyRoleTypeListSubView = new PartyRoleTypeListSubView({model:partyRoleTypes, el:$('#partyRoleTypeSelectContainerDiv'), selectedOption:this.partyRoleTypeId});
            partyRoleTypeListSubView.render();
        }
    });

    return PartyRoleTypeEditView;
});

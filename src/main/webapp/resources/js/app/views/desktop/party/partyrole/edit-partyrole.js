define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/partyroletype/partyroletype',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/party/partyroletype/partyroletype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyrole/edit-partyrole.html'
], function (utilities, config, formUtilities, entities_strings, PartyRoleTypes, Partys, partyRoleTypeListSubViewTemplate, partyListSubViewTemplate, PartyRoleEditTemplate) {
	
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
    
	
    var PartyRoleEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partyrole)
                    {
                        utilities.applyTemplate($(self.el), PartyRoleEditTemplate,  
                            {model:this.model, partyrole:partyrole, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyRoleEditTemplate,  
                    {model:this.model, partyrole:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partyrole-form':'editPartyRole'
            
        },
        editPartyRole: function(event)
        {
            event.preventDefault();
            var partyrole = $(event.currentTarget).serializeObject();
            this.model.save(partyrole, { 
                'success': function ()
                {
                    utilities.navigate('list-partyrole');
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
		    	this.partyId = this.model.attributes.party
            }
            // PartyRoleTypes
            var partyRoleTypes = new PartyRoleTypes();
            partyRoleTypeListSubView = new PartyRoleTypeListSubView({model:partyRoleTypes, el:$('#partyRoleTypeSelectContainerDiv'), selectedOption:this.partyRoleTypeId});
            partyRoleTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return PartyRoleEditView;
});

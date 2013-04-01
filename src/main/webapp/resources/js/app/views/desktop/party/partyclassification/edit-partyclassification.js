define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/partytype/partytype',
    'app/collections/party/partyclassificationtype/partyclassificationtype',
    'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/party/partytype/partytype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyclassificationtype/partyclassificationtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyclassification/edit-partyclassification.html'
], function (utilities, config, formUtilities, entities_strings, PartyTypes, PartyClassificationTypes, Partys, partyTypeListSubViewTemplate, partyClassificationTypeListSubViewTemplate, partyListSubViewTemplate, PartyClassificationEditTemplate) {
	
    var PartyTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyTypeSelectContainerDiv'), partyTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyTypesFetch.done(function (){
                utilities.applyTemplate($('#partyTypeSelectContainerDiv'), partyTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
    var PartyClassificationTypeListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#partyClassificationTypeSelectContainerDiv'), partyClassificationTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyClassificationType", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var partyClassificationTypesFetch = this.model.fetch();
            // Re render the template when the data is available    
            partyClassificationTypesFetch.done(function (){
                utilities.applyTemplate($('#partyClassificationTypeSelectContainerDiv'), partyClassificationTypeListSubViewTemplate,  {model:self.model, relatedFieldName:"partyClassificationType", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
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
    
	
    var PartyClassificationEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partyclassification)
                    {
                        utilities.applyTemplate($(self.el), PartyClassificationEditTemplate,  
                            {model:this.model, partyclassification:partyclassification, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyClassificationEditTemplate,  
                    {model:this.model, partyclassification:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partyclassification-form':'editPartyClassification'
            
        },
        editPartyClassification: function(event)
        {
            event.preventDefault();
            var partyclassification = $(event.currentTarget).serializeObject();
            this.model.save(partyclassification, { 
                'success': function ()
                {
                    utilities.navigate('list-partyclassification');
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
		    	this.partyTypeId = this.model.attributes.partyType
		    	this.partyClassificationTypeId = this.model.attributes.partyClassificationType
		    	this.partyId = this.model.attributes.party
            }
            // PartyTypes
            var partyTypes = new PartyTypes();
            partyTypeListSubView = new PartyTypeListSubView({model:partyTypes, el:$('#partyTypeSelectContainerDiv'), selectedOption:this.partyTypeId});
            partyTypeListSubView.render();
            // PartyClassificationTypes
            var partyClassificationTypes = new PartyClassificationTypes();
            partyClassificationTypeListSubView = new PartyClassificationTypeListSubView({model:partyClassificationTypes, el:$('#partyClassificationTypeSelectContainerDiv'), selectedOption:this.partyClassificationTypeId});
            partyClassificationTypeListSubView.render();
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return PartyClassificationEditView;
});

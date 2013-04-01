define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/partyclassificationtype/partyclassificationtype',
    'text!../../../../../../templates/desktop/party/partyclassificationtype/partyclassificationtype-list-subview.html',
    'text!../../../../../../templates/desktop/party/partyclassificationtype/edit-partyclassificationtype.html'
], function (utilities, config, formUtilities, entities_strings, PartyClassificationTypes, partyClassificationTypeListSubViewTemplate, PartyClassificationTypeEditTemplate) {
	
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
    
	
    var PartyClassificationTypeEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(partyclassificationtype)
                    {
                        utilities.applyTemplate($(self.el), PartyClassificationTypeEditTemplate,  
                            {model:this.model, partyclassificationtype:partyclassificationtype, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), PartyClassificationTypeEditTemplate,  
                    {model:this.model, partyclassificationtype:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-partyclassificationtype-form':'editPartyClassificationType'
            
        },
        editPartyClassificationType: function(event)
        {
            event.preventDefault();
            var partyclassificationtype = $(event.currentTarget).serializeObject();
            this.model.save(partyclassificationtype, { 
                'success': function ()
                {
                    utilities.navigate('list-partyclassificationtype');
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
		    	this.partyClassificationTypeId = this.model.attributes.partyClassificationType
            }
            // PartyClassificationTypes
            var partyClassificationTypes = new PartyClassificationTypes();
            partyClassificationTypeListSubView = new PartyClassificationTypeListSubView({model:partyClassificationTypes, el:$('#partyClassificationTypeSelectContainerDiv'), selectedOption:this.partyClassificationTypeId});
            partyClassificationTypeListSubView.render();
        }
    });

    return PartyClassificationTypeEditView;
});

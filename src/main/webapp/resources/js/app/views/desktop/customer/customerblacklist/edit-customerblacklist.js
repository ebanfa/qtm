define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/party/party',
    'text!../../../../../../templates/desktop/party/party/party-list-subview.html',
    'text!../../../../../../templates/desktop/customer/customerblacklist/edit-customerblacklist.html'
], function (utilities, config, formUtilities, entities_strings, Partys, partyListSubViewTemplate, CustomerBlacklistEditTemplate) {
	
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
    
	
    var CustomerBlacklistEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(customerblacklist)
                    {
                        utilities.applyTemplate($(self.el), CustomerBlacklistEditTemplate,  
                            {model:this.model, customerblacklist:customerblacklist, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), CustomerBlacklistEditTemplate,  
                    {model:this.model, customerblacklist:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-customerblacklist-form':'editCustomerBlacklist'
            
        },
        editCustomerBlacklist: function(event)
        {
            event.preventDefault();
            var customerblacklist = $(event.currentTarget).serializeObject();
            this.model.save(customerblacklist, { 
                'success': function ()
                {
                    utilities.navigate('list-customerblacklist');
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
            }
            // Partys
            var partys = new Partys();
            partyListSubView = new PartyListSubView({model:partys, el:$('#partySelectContainerDiv'), selectedOption:this.partyId});
            partyListSubView.render();
        }
    });

    return CustomerBlacklistEditView;
});

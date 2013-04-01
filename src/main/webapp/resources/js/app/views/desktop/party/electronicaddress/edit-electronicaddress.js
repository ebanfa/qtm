define([
    'utilities',
    'configuration',
    'app/util/form-utilities',
    'i18n!app/nls/entities',
        'app/collections/party/contactmechanism/contactmechanism',
    'text!../../../../../../templates/desktop/party/contactmechanism/contactmechanism-list-subview.html',
    'text!../../../../../../templates/desktop/party/electronicaddress/edit-electronicaddress.html'
], function (utilities, config, formUtilities, entities_strings, ContactMechanisms, contactMechanismListSubViewTemplate, ElectronicAddressEditTemplate) {
	
    var ContactMechanismListSubView = Backbone.View.extend({
        initialize: function () {
            _.bindAll(this, 'render');
        },
        render:function () 
        {     
            var self = this;            
            utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:this.options.selectedOption});
            // Fetch data
            var contactMechanismsFetch = this.model.fetch();
            // Re render the template when the data is available    
            contactMechanismsFetch.done(function (){
                utilities.applyTemplate($('#contactMechanismSelectContainerDiv'), contactMechanismListSubViewTemplate,  {model:self.model, relatedFieldName:"contactMechanism", entities_strings:entities_strings, selectedOption:self.options.selectedOption});
            });
            return this;
        }
    });
    
	
    var ElectronicAddressEditView = Backbone.View.extend({
        render:function () {
            var self = this;
            if (this.model.attributes.id)
            {
                var self = this;
                this.model.fetch(
                {
                    success: function(electronicaddress)
                    {
                        utilities.applyTemplate($(self.el), ElectronicAddressEditTemplate,  
                            {model:this.model, electronicaddress:electronicaddress, entities_strings:entities_strings}); 
                        $(self.el).trigger('pagecreate');
                		self.renderSubViews();
                    }
                });
            }
            else
            {
                utilities.applyTemplate($(this.el), ElectronicAddressEditTemplate,  
                    {model:this.model, electronicaddress:null, entities_strings:entities_strings});
                $(this.el).trigger('pagecreate');
                this.renderSubViews();
            }
            return this;
        },
        events:
        {
            'submit #edit-electronicaddress-form':'editElectronicAddress'
            
        },
        editElectronicAddress: function(event)
        {
            event.preventDefault();
            var electronicaddress = $(event.currentTarget).serializeObject();
            this.model.save(electronicaddress, { 
                'success': function ()
                {
                    utilities.navigate('list-electronicaddress');
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
		    	this.contactMechanismId = this.model.attributes.contactMechanism
            }
            // ContactMechanisms
            var contactMechanisms = new ContactMechanisms();
            contactMechanismListSubView = new ContactMechanismListSubView({model:contactMechanisms, el:$('#contactMechanismSelectContainerDiv'), selectedOption:this.contactMechanismId});
            contactMechanismListSubView.render();
        }
    });

    return ElectronicAddressEditView;
});

define([
    'utilities',
    'i18n!../../../../../../js/app/nls/entities',
    'text!../../../../../../templates/desktop/home/subnavbar.html'
], function (
    utilities,
    entities_strings,
    menuTemplate) {

    var ModuleMenuView = Backbone.View.extend({
        render:function () {    
        console.log("######## Rendering!!!!");        
            utilities.applyTemplate($(this.el), menuTemplate,  {model:this.model, entities_strings:entities_strings});
            $(this.el).trigger('pagecreate');
            this.delegateEvents();
            return this;
        }
    });

    return ModuleMenuView;
});
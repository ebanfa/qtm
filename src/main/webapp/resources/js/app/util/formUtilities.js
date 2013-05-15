define([
    'jquery'
], function ($) {

  function Field(fieldInfo, fieldValue)
  {
    this.fieldInfo = fieldInfo;
    this.fieldValue = fieldValue;
  }

  function FieldBlock()
  {
    this.fields = [];
    this.fieldsPerBlock = 2;
  }

  function Form ()
  {
    this.fields = [];
    this.entity = null;
    this.activity = null;
    this.fieldBlocks = [];
  };

  $.fn.serializeObject = function() {
    var arrayData, objectData;
    arrayData = this.serializeArray();
    objectData = {};

    $.each(arrayData, function() {
      var value;

      if (this.value != null) {
        value = this.value;
      } else {
        value = '';
      }

      if (objectData[this.name] != null) {
        if (!objectData[this.name].push) {
          objectData[this.name] = [objectData[this.name]];
        }

        objectData[this.name].push(value);
      } else {
        objectData[this.name] = value;
      }
    });

    return objectData;
  };

  $.fn.populateFieldBlocks = function(form)
  {
    var currentFieldBlock = new FieldBlock();
    for (var index=0; index<form.fields.length; index++) 
    {
      console.log('Processing field: '+form.fields[index].name);
      if (currentFieldBlock.fields.length < currentFieldBlock.fieldsPerBlock) 
      {
        var field = new Field(form.fields[index], form.entity[form.fields[index].name]);
        currentFieldBlock.fields.push(field);
        if(index == (form.fields.length -1)) 
        {
          form.fieldBlocks.push(currentFieldBlock);
        }
      }
      else 
      {
        form.fieldBlocks.push(currentFieldBlock);
        currentFieldBlock = new FieldBlock();
        var field = new Field(form.fields[index], form.entity[form.fields[index].name]);
        currentFieldBlock.fields.push(field);
        if(index == (form.fields.length -1)) 
        {
          form.fieldBlocks.push(currentFieldBlock);
        }
      }
    }
    console.log("Current no of fieldBlocks in form: " + form.fieldBlocks.length);
  }

  $.fn.formBuilder = function(activity)
  {
    var form = new Form();
    if(activity) {
      form.activity = activity;
      form.fields = activity.attributes.fields;
      form.entity = activity.attributes.entity;
      console.log("activity.attributes.fields:" +activity.attributes.fields.length);
      $.fn.populateFieldBlocks(form);

    }
    return form;
  };

  //return $.fn.formBuilder;

  return {formSerializer:$.fn.serializeObject, formBuilder:$.fn.formBuilder}
});




/**
 * A module for the router of the desktop application.
 *
define("form-utilities",['jquery'],function ($) 
{
  
  function Form()
  {
    this.fields = [];
  }


  $.fn.serializeObject = function() {
    var arrayData, objectData;
    arrayData = this.serializeArray();
    objectData = {};

    $.each(arrayData, function() {
      var value;

      if (this.value != null) {
        value = this.value;
      } else {
        value = '';
      }

      if (objectData[this.name] != null) {
        if (!objectData[this.name].push) {
          objectData[this.name] = [objectData[this.name]];
        }

        objectData[this.name].push(value);
      } else {
        objectData[this.name] = value;
      }
    });

    return objectData;
  };
}); 
 */

/*function Form()
{
   var form={fields:[], fieldBlocks:[]}
   return form;
}

jQuery.fn.serializeObject = function() {
  var arrayData, objectData;
  arrayData = this.serializeArray();
  objectData = {};

  $.each(arrayData, function() {
    var value;

    if (this.value != null) {
      value = this.value;
    } else {
      value = '';
    }

    if (objectData[this.name] != null) {
      if (!objectData[this.name].push) {
        objectData[this.name] = [objectData[this.name]];
      }

      objectData[this.name].push(value);
    } else {
      objectData[this.name] = value;
    }
  });

  return objectData;
};*/

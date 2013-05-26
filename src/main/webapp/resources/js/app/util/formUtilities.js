define([
    'jquery'
], function ($) {
  
 /*
  * FieldInfo object's constructor function.
  */
  function FieldInfo(name, fieldTypeCode, description)
  {
    this.name = name;
    this.description = description;
    this.fieldTypeCode = fieldTypeCode;
  }

 /*
  * Field object's constructor function.
  */
  function Field(fieldInfo, fieldValue, listOptions)
  {
    // Field info contains name and field type
    this.fieldInfo = fieldInfo;
    // The value of this field
    this.fieldValue = fieldValue;
    // List item to populate list based widgets e.g
    // drop downs but will be null for non list based 
    // widgets
    this.listOptions = listOptions;
  }

 /*
  * FieldBlock object's constructor function.
  */
  function FieldBlock()
  {
    this.fields = [];
    this.fieldsPerBlock = 2;
  }

 /*
  * Form object's constructor function.
  */
  function Form ()
  {
    this.mode = null;
    this.fields = [];
    this.entity = null;
    this.activity = null;
    this.fieldBlocks = [];
    this.relatedEntities = null;
  };

 /*
  * Serializes a form into an array.
  */
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

 /*
  * Function to initialize a form's field.
  */
  $.fn.initializeField = function(form, index)
  {
      // The name and the type of the field
      var fieldValue = null;
      var fieldName = form.fields[index].name;
      var fieldDescription = form.fields[index].description;
      var fieldType = form.fields[index].fieldTypeCode;
      var relationshipFieldSuffix = "Id";
      // Are we dealing with a relationship field
      if (fieldType == "RELATIONSHIP")
      {
        if(form.mode == "EDIT")  {
          form.name = "Edit"
          fieldValue = form.entity[fieldName + relationshipFieldSuffix];
        } else  {
          form.name = "Create"
          fieldValue = null;
        }
        return new Field(form.fields[index], fieldValue, form.relatedEntities[fieldName]);
      }
      // No we are dealing with a non relationship field
      else
      {
        if(form.mode == "EDIT")  {
          form.name = "Edit"
          fieldValue = form.entity[fieldName];
        } else  {
          form.name = "Create"
          fieldValue = null;
        }
        return new Field(form.fields[index], fieldValue, null);
      }
  }

 /*
  * Function to populate the field blocks on a form.
  */
  $.fn.populateFieldBlocks = function(form)
  {
    var currentFieldBlock = new FieldBlock();
    for (var index=0; index<form.fields.length; index++) 
    {
      if(form.fields[index].fieldTypeCode != "ID")
      {
        // Do we have space in the current field block
        if (currentFieldBlock.fields.length < currentFieldBlock.fieldsPerBlock) 
        {
          // Initialize the field and add to field list
          currentFieldBlock.fields.push($.fn.initializeField(form, index));
          // If this is the last field in the list the we add the field block
          // which contains only one field
          if(index == (form.fields.length -1)) 
          {
            form.fieldBlocks.push(currentFieldBlock);
          }
        }
        // We don't have enough space in the field block so we add the current
        // field block to the field block lis and create a new one to hold the current field.
        else 
        {
          form.fieldBlocks.push(currentFieldBlock);
          currentFieldBlock = new FieldBlock();
          currentFieldBlock.fields.push($.fn.initializeField(form, index));
          if(index == (form.fields.length -1)) 
          {
            form.fieldBlocks.push(currentFieldBlock);
          }
        }
      }
    }
    console.log("Current no of fieldBlocks in form: " + form.fields.length);
  }

 /*
  * Function to build an activity's form.
  */
  $.fn.formBuilder = function(activity)
  {
    var form = new Form();
    if(activity) {
      form.mode = "CREATE";
      form.activity = activity;
      form.fields = activity.attributes.fields;
      form.entity = activity.attributes.entity;
      form.relatedEntities = activity.attributes.relatedEntitiesListData;
      if(form.entity != null ) {
        form.mode = "EDIT"
      }
      // Loop through the related entity fields and 
      // and each to the form fields
      /*for (var property in relatedEntities) {
        if (relatedEntities.hasOwnProperty(property)) {
          var relationshipFieldInfo = new FieldInfo(property, "RELATIONSHIP");
          var relationshipField = new Field(relationshipFieldInfo, relatedEntities[property]);
          form.fields.push(relationshipField);
        }
      }*/

      console.log("Crack music!!2");
      $.fn.populateFieldBlocks(form);
    }
    return form;
  };
  return {formSerializer:$.fn.serializeObject, formBuilder:$.fn.formBuilder}
});


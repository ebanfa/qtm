define([
    'jquery'
], function ($) {
  
 /*
  * Information of an entity that was selected from the entity search page.
  */
  function SelectedRelatedEntityInfo(fieldName, entityLite)
  {
    this.fieldName = fieldName;
    this.entityLite = entityLite;
  }

 /*
  * Entity lite.
  */
  function EntityLite(id, code, name, description)
  {
    this.id = id;
    this.code = code;
    this.name = name;
    this.description = description;
  }

 /*
  * Field object's constructor function.
  */
  function Field(fieldInfo, fieldValue, listOptions, fieldClassName)
  {
    // Field info contains name and field type
    this.fieldInfo = fieldInfo;
    // The value of this field
    this.fieldValue = fieldValue;
    // List item to populate list based widgets e.g
    // drop downs but will be null for non list based 
    // widgets
    this.listOptions = listOptions;
    this.fieldClassName = fieldClassName;
  }

 /*
  * FieldInfo object's constructor function.
  */
  function FieldInfo(name, applicationEntityFieldTypeText, description)
  {
    this.name = name;
    this.description = description;
    this.applicationEntityFieldTypeText = applicationEntityFieldTypeText;
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

  $.fn.createEntityLite = function(id, code, name, description) {
    return new EntityLite(id, code, name, description);
  }

  $.fn.createSelectedRelatedEntityInfo = function (fieldName, entityLite) {
    return new SelectedRelatedEntityInfo(fieldName, entityLite);
  }

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
      var fieldClassName = null;
      var fieldName = form.fields[index].name;
      var requiredFg = form.fields[index].requiredFg;
      var fieldDescription = form.fields[index].description;
      var fieldType = form.fields[index].applicationEntityFieldTypeText;
      var relationshipFieldSuffix = "Id";
      // Are we dealing with a required field
      if (requiredFg == "Y") 
      {
        fieldClassName = "field_required";
      } else 
      {
        fieldClassName = "field";
      }
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
        return new Field(form.fields[index], fieldValue, form.relatedEntities[fieldName], fieldClassName);
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
        return new Field(form.fields[index], fieldValue, null, fieldClassName);
      }
  };

  function sortFields(first, second)
  {
    //var val = first.sequenceNo - second.sequenceNo;
     console.log("Comparing" + first.sequenceNo + ":" + second.sequenceNo);
    if(first.sequenceNo < second.sequenceNo)
    {
      console.log("first.sequenceNo: Before");
      return -1;
    }
    if(first.sequenceNo > second.sequenceNo)
    {
      console.log("first.sequenceNo: Later");
      return 1;
    }
    if(first.sequenceNo == second.sequenceNo)
    {
      console.log("first.sequenceNo: Equal ");
      return 0;
    }
  }
 /*
  * Function to populate the field blocks on a form.
  */
  $.fn.populateFieldBlocks = function(form)
  {
    var currentFieldBlock = new FieldBlock();
    // Sort fields
    form.fields.sort(sortFields);
    for (var index=0; index<form.fields.length; index++) 
    {
      if(form.fields[index].applicationEntityFieldTypeText != "ID")
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

      $.fn.populateFieldBlocks(form);
    }
    return form;
  };
  return {formSerializer: $.fn.serializeObject, 
    formBuilder: $.fn.formBuilder, entityLite: $.fn.createEntityLite, 
    createSelectedRelatedEntityInfo: $.fn.createSelectedRelatedEntityInfo}
});


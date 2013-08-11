define([
    'jquery'
], function ($) {
  
 /*
  * Function to execute a HTTP GET request for JSON data.
  */
  $.fn.ajaxGET = function(url, data, onSuccessCallBack, onErrorCallBack)
  {
    $.ajax({
        type: 'GET',
        url: 'rest/' + url,
        contentType: 'application/json; charset=utf-8',
        data: data,
        dataType: "json",
        success: function(data){ 
          onSuccessCallBack(data);
        },
        error: function(request, status, error){
          onErrorCallBack(request, status, error);
        }
    });
  };

  $.fn.ajaxPOST = function(url, data, onSuccessCallBack, onErrorCallBack)
  {
    $.ajax({
        type: 'POST',
        url: 'rest/' + url,
        contentType: 'application/json; charset=utf-8',
        data: data,
        dataType: "json",
        success: function(data){ 
          onSuccessCallBack(data);
        },
        error: function(request, status, error){
          onErrorCallBack(request, status, error);
        }
    });
  };

  var ajaxUtil = 
  {
    ajaxGET: $.fn.ajaxGET,
    ajaxPOST: $.fn.ajaxPOST
  }
  return ajaxUtil;
});


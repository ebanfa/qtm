define([ 'jquery' ], function($) {

	// Initial Setup
	// -------------
	// Save a reference to the global object (`window` in the browser, `global`
	// on the server).
	var root = this;
	// The top-level namespace.

	/*
	 * Function to execute a HTTP GET request for JSON data.
	 */
	var ajaxGET = function(url, data, onSuccessCallBack, onErrorCallBack) {
		$.ajax({
			type : 'GET',
			url : 'rest/' + url,
			contentType : 'application/json; charset=utf-8',
			data : data,
			dataType : "json",
			success : function(data) {
				onSuccessCallBack(data);
			},
			error : function(request, status, error) {
				onErrorCallBack(request, status, error);
			}
		});
	};

	var ajaxPOST = function(url, data, onSuccessCallBack, onErrorCallBack) {
		$.ajax({
			type : 'POST',
			url : 'rest/' + url,
			contentType : 'application/json; charset=utf-8',
			data : data,
			dataType : "json",
			success : function(data) {
				onSuccessCallBack(data);
			},
			error : function(request, status, error) {
				onErrorCallBack(request, status, error);
			}
		});
	};

	/**
	 * List Activity Object.
	 */
	var ListActivity = function (options){
		return { 
			data : [],
			name : options.name,
			baseURL : options.baseURL,
			parentView : options.parentView,
			entityName : options.entityName,
			activityURL : options.activityURL,
			searchURLPrefix : options.searchURLPrefix,
			activityEditURL : options.activityEditURL,
			activityListTemplate : options.activityListTemplate,
		};
	};
	
	/**
	 * View Activity Object.
	 */
	var ViewActivity = function (options){
		return { 
			data : {},
			name : options.name,
			baseURL : options.baseURL,
			parentView : options.parentView,
			entityName : options.entityName,
			activityURL : options.activityURL,
			searchURLPrefix : options.searchURLPrefix,
			activityEditURL : options.activityEditURL,
			activityListTemplate : options.activityListTemplate,
		};
	};
	
	var QUI = root.QUI = {
			ajaxGET: ajaxGET,
			ListActivity: ListActivity,
			ViewActivity: ViewActivity
	};
	return QUI;
});
define([
    'jquery'
], function ($) {
	
  
 /*
  * Simple notification.
  */
  $.fn.notify = function(message, container)
  {
    var notifier = new Backbone.Notifier({
                el:container,
                ms: 5000,
                position: 'center',
                offsetY: -500,
                theme: 'plastic'
            });
    notifier.notify(message); 
  };
   
  /*
  * Simple notification.
  */
  $.fn.info = function(message, container)
  {
    var info = new Backbone.Notifier({
                el:container,
                'type': 'info',
                ms: 5000,
                position: 'center',
                offsetY: -500,
                theme: 'plastic'
    });
    info.notify(message); 
  };

  /*
  * Simple notification.
  */
  $.fn.success = function(message, container)
  {
    var success = new Backbone.Notifier({
                el:container,
                'type': 'success',
                ms: 5000,
                position: 'center',
                offsetY: -500,
                theme: 'plastic'
    });
    success.notify(message); 
  };

  /*
  * Simple notification.
  */
  $.fn.warn = function(message, container)
  {
    var warn = new Backbone.Notifier({
                el:container,
                'type': 'warning',
                ms: 5000,
                position: 'center',
                offsetY: -500,
                theme: 'plastic'
    });
    warn.notify(message); 
  };

  /*
  * Simple notification.
  */
  $.fn.error = function(message, container)
  {
    var error = new Backbone.Notifier({
                el:container,
                ms: 5000,
                position: 'center',
                offsetY: -500,
                theme: 'plastic'
    });
    error.error(message); 
  };

  var dialogUtil = 
  {
    notify: $.fn.notify,
    warn: $.fn.warn,
    info: $.fn.info,
    success: $.fn.success,
    error: $.fn.error
  }
  return dialogUtil;
});


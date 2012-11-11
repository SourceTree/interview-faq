/*
**	Venkaiah Chowdary Koneru
**	jQuery Simple and light weight Infinite Scroll Pagination
**	11th/Nov/2012
**	http://koneru.posterous.com/
**	You may use this script for free, but keep my credits.
**	Thank you.
*/
(function( $ ){
 $.fn.infinitePaging = function(options) {
  	
		var opts = $.extend($.fn.infinitePaging.defaults, options);  
		var target = opts.scrollTarget;
		if (target == null){
			target = obj; 
	 	}
		opts.scrollTarget = target;
	 
		return this.each(function() {
		  $.fn.infinitePaging.init($(this), opts);
		});

  };
  
  $.fn.stopInfinitePaging = function(){
	  return this.each(function() {
	 	$(this).attr('infinitePaging', 'disabled');
	  });
	  
  };
  
  $.fn.infinitePaging.loadContent = function(obj, opts){
	 var target = opts.scrollTarget;
	 var mayLoadContent = $(target).scrollTop()+opts.heightOffset >= $(document).height() - $(target).height();
	 if (mayLoadContent){
		 if (opts.beforeLoad != null){
			opts.beforeLoad(); 
		 }
		 $(obj).children().attr('rel', 'loaded');
		 var localUrl = opts.url + '/' + (opts.page + 1);
		 if(opts.sortProperty != null){
			 localUrl = localUrl + '?sort=' + opts.sortProperty + '&order=' + opts.sortOrder;
		}
		 
		 opts.isLoading = true;
		 
		 $.ajax({
			  type: opts.ajaxType,
			  url: localUrl,
			  data: opts.contentData,
			  dataType: opts.dataType,
			  success: function(data){
				$(obj).append(opts.renderData(data)); 
				var objectsRendered = $(obj).children('[rel!=loaded]');
				
				opts.page = opts.page + 1;
				opts.isLoading = false;
				if (opts.afterLoad != null){
					opts.afterLoad(objectsRendered);
				} 
				
				//Logic to stop paging
				if((data.listProp.endIndex +1) == data.listProp.totalRecords){
					$(obj).stopInfinitePaging();
				}
			  }
		 });
	 }
	 
  };
  
  $.fn.infinitePaging.init = function(obj, opts){
	 var target = opts.scrollTarget;
	 $(obj).attr('infinitePaging', 'enabled');
	
	 $(target).scroll(function(event){
		if ($(obj).attr('infinitePaging') == 'enabled'){
			if(!opts.isLoading){
				$.fn.infinitePaging.loadContent(obj, opts);
			}
		}
		else {
			event.stopPropagation();	
		}
	 });
	 
	 $.fn.infinitePaging.loadContent(obj, opts);
	 
 };
	
 $.fn.infinitePaging.defaults = {
      	 'url' : null,
      	 'page': 1,
      	 'sortProperty' : null,
      	 'sortOrder' : 'ASC',
     	 'contentData' : {},
		 'beforeLoad': null,
		 'afterLoad': null	,
		 'scrollTarget': $(window),
		 'heightOffset': 0,
		 'dataType': 'json',
		 'ajaxType':'GET',
		 'totalPages':null,
		 'renderData':null,
		 'isLoading':false
 };	
})( jQuery );
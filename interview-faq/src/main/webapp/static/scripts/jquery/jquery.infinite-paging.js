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
	 
		return this.each(function() {
		  $.fn.infinitePaging.init($(this), opts);
		});

  };
  
  $.fn.stopInfinitePaging = function(){
	  return this.each(function() {
	 	$(this).attr('infinitePaging', 'disabled');
	  });
	  
  };
  
  $.fn.loadMore = function(opts){
	  if ($(this).attr('infinitePaging') == 'enabled'){
			$.fn.infinitePaging.loadContent(this, opts);
	  }
  };
  
  $.fn.infinitePaging.loadContent = function(obj, opts){
		 if (opts.beforeLoad != null){
			opts.beforeLoad(); 
		 
		 }
		 $('.pagination').remove();
		 $(obj).children().attr('rel', 'loaded');
		 var localUrl = opts.url + '/' + (opts.page + 1);
		 if(opts.sortProperty != null){
			 localUrl = localUrl + '?sort=' + opts.sortProperty + '&order=' + opts.sortOrder;
		}
		 
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
				
				$(obj).append('<div class="pagination"> <span class="pagination_total">Total Records :'+data.listProp.totalRecords+'</span><span class="pager">LoadMore</span><span class="pagination_page">Page '+ data.listProp.page +' of'+(data.listProp.totalRecords/10)+' </span></div>');
				//Logic to stop paging
				if((data.listProp.endIndex +1) >= data.listProp.totalRecords){
					$(obj).stopInfinitePaging();
				} else {
					$('.pager').bind('click', function(){
						$(obj).loadMore(opts);
					});
				}
			  }
		 });
  };
  
  $.fn.infinitePaging.init = function(obj, opts){
	 $(obj).attr('infinitePaging', 'enabled');	 
	 
	 if(opts.page == 0){
		 $(obj).loadMore(opts);
	}
	 
	 $('.pager').bind('click', function(){
			$(obj).loadMore(opts);
		});
 };
	

 $.fn.infinitePaging.defaults = {
      	 'url' : null,
      	 'page': 0,
      	 'sortProperty' : null,
      	 'sortOrder' : 'ASC',
     	 'contentData' : {},
		 'beforeLoad': null,
		 'afterLoad': null	,
		 'dataType': 'json',
		 'ajaxType':'GET',
		 'totalPages':null,
		 'renderData':null,
		 'isLoading':false
 };	
})( jQuery );
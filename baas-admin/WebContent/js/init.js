$(function(){
	$(".thumbnails a").attr('rel', 'gallery').fancybox();

	$("#nav-list li, #scroll_up").click(function(e) {
		
		if(!$(this).hasClass("notNavegate")){
		
			e.preventDefault();
			$('html, body').animate({
					scrollTop: $($(this).children("a").attr("href")).offset().top
			},1500);
		}
	 });
 });
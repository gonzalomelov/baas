$(document).ready(function(){
			
	$(".appNamePosta").keyup(function() {
				var valor = $(this).val();
				$(".appNameInput").val(valor);
			});
		});
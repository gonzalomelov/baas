$(document).ready(function(){
	
	// Cuando se carga la app se copia el valor por defecto para todos los input
	$(".appNameInput").val($(".appNamePosta").val());
			
	$(".appNamePosta").keyup(function() {
				var valor = $(this).val();
				$(".appNameInput").val(valor);
			});
		});
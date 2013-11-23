package uy.trueques_beta.entities;

public class RSS {
	private String mensaje;
	private String titulo;

	public RSS(String titulo, String msj){
		this.mensaje=msj;
		this.titulo = titulo;
	}
	
	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	

}

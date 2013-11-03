package uy.trueques_beta.entities;


public class Oferta {

	private int idOferta;
	private Objeto objeto;
	private boolean rechazada;
	private int  idTrueque;
	private String ubicacion;
	
	
	public Oferta(int idOferta, Objeto objeto, int idTrueque, String ubicacion){
		this.idOferta=idOferta;
		this.objeto=objeto;
		this.setRechazada(false);
		this.idTrueque = idTrueque;
		this.ubicacion= ubicacion;
	}
	
	public int getIdOferta() {
		return idOferta;
	}
	public void setIdOferta(int idOferta) {
		this.idOferta = idOferta;
	}
	public Objeto getObjeto() {
		return objeto;
	}
	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}
	public boolean isRechazada() {
		return rechazada;
	}

	public void setRechazada(boolean rechazada) {
		this.rechazada = rechazada;
	}
	
	//++++++++++++
	public Usuario getUsuario(){
		return this.objeto.getDuenio();
	}

	@Override
	public boolean equals(Object o){
		if (o == null) return false;
	    if (o == this) return true;
	    if (!(o instanceof Oferta)) return false;
	    Oferta ofer = (Oferta)o;
	    return this.idOferta == ofer.getIdOferta();
	}

	public int getIdTrueque() {
		return idTrueque;
	}

	public void setTrueque(int trueque) {
		this.idTrueque = trueque;
	}

	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	
	
	
}

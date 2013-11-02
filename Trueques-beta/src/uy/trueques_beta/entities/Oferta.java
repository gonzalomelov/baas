package uy.trueques_beta.entities;


public class Oferta {

	private int idOferta;
	private Objeto objeto;
	private boolean rechazada;
	
	
	public Oferta(int idOferta, Objeto objeto){
		this.idOferta=idOferta;
		this.objeto=objeto;
		this.setRechazada(false);
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
	
	
	
}

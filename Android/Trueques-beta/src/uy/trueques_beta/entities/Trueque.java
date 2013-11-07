package uy.trueques_beta.entities;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.graphics.Bitmap;

public class Trueque {
	
	private int idTrueque;
	private Date fechaIni;
	private Date fechaFin;
	private Objeto objeto;
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	private boolean activa;
	private Oferta ganadora;
	//++++
	private String buscado;
	private float minVal;
	private String ubicacion;
	private int puntosTrueque;
	private int puntosGanadora;
	private Bitmap imagen;
	
	public Trueque (int id, Objeto obj, String busca, float minVal, String ubicacion){
		this.idTrueque =id;
		this.objeto=obj;
		this.activa=true;
		this.fechaIni= new Date();
		this.ganadora =null;
		this.buscado=busca;
		this.setMinVal(minVal);
		this.ubicacion=ubicacion;
		this.puntosGanadora=0;
		this.puntosTrueque=0;
	}

	public int getIdTrueque() {
		return idTrueque;
	}

	public void setIdTrueque(int idTrueque) {
		this.idTrueque = idTrueque;
	}

	public Objeto getObjeto() {
		return objeto;
	}

	public void setObjeto(Objeto objeto) {
		this.objeto = objeto;
	}

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}

	public boolean isActiva() {
		return activa;
	}

	public void setActiva(boolean activa) {
		this.activa = activa;
	}

	public Oferta getGanadora() {
		return ganadora;
	}

	public void setGanadora(Oferta ganadora) {
		this.ganadora = ganadora;
	}
	
	public Date getFechaIni() {
		return fechaIni;
	}

	public void setFechaIni(Date fechaIni) {
		this.fechaIni = fechaIni;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}
	public String getBuscado() {
		return buscado;
	}

	public void setBuscado(String buscado) {
		this.buscado = buscado;
	}
	
	public float getMinVal() {
		return minVal;
	}

	public void setMinVal(float minVal) {
		this.minVal = minVal;
	}
	public String getUbicacion() {
		return ubicacion;
	}

	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getPuntosGanadora() {
		return puntosGanadora;
	}

	public void setPuntosGanadora(int puntosGanadora) {
		this.puntosGanadora = puntosGanadora;
	}

	public int getPuntosTrueque() {
		return puntosTrueque;
	}

	public void setPuntosTrueque(int puntosTrueque) {
		this.puntosTrueque = puntosTrueque;
	}
	//+++++++++++++
	public Usuario getUsuario(){
		return this.objeto.getDuenio();
	}

	public void addOferta(Oferta ofer){
		this.ofertas.add(ofer);
	}
	
	public boolean existOferta(int idOferta){
		if(this.ofertas==null || this.ofertas.isEmpty())
			return false;
		
		boolean existe=false;
		for(Oferta ofer: this.ofertas){
			if(ofer.getIdOferta()==idOferta)
				existe = true;
		}
		
		return existe;
	}
	
	public boolean rechazarOferta(int idOferta){
		if(this.ofertas==null || this.ofertas.isEmpty())
			return false;
		
		//boolean existe=false;
		for(Oferta ofer: this.ofertas){
			if(ofer.getIdOferta()==idOferta)
				ofer.setRechazada(true);
		}
		
		return true;	
	}
	
	public List<Oferta> getOfertasPendientes(){
		List<Oferta> pends = new ArrayList<Oferta>();
		
		if(this.ofertas==null || this.ofertas.isEmpty() || !this.activa)
			return pends;
		
		for(Oferta ofer: this.ofertas){
			if(!ofer.isRechazada())
				pends.add(ofer);
		}
		return pends;
	}

	public void puntuarTrueque(int pts){
		this.puntosTrueque=pts;
		this.objeto.getDuenio().puntuar(pts);
	}
	public void puntuarGanadora(int pts){
		this.puntosGanadora=pts;
		this.ganadora.getUsuario().puntuar(pts);
	}

	public Bitmap getImagen() {
		return imagen;
	}

	public void setImagen(Bitmap imagen) {
		this.imagen = imagen;
	}



	

	

}

package uy.trueques_beta.entities;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

public class Trueque {
	
	private String idTrueque;
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
	//private Bitmap imagen;
	private String imagen;
	/*
	 * ByteArrayOutputStream baos = new ByteArrayOutputStream();
photo.compress(Bitmap.CompressFormat.JPEG, 100, baos);
byte[] imageBytes = baos.toByteArray();
String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
	 */
	
	public Trueque (Objeto obj, String busca, float minVal, String ubicacion, Bitmap bitmap) 
			throws UnsupportedEncodingException, ClientProtocolException, IOException{
		this.idTrueque = UUID.randomUUID().toString();//(mostSigBits, leastSigBits);
		this.objeto=obj;
		this.activa=true;
		this.fechaIni= new Date();
		this.ganadora =null;
		this.buscado=busca;
		this.setMinVal(minVal);
		this.ubicacion=ubicacion;
		this.puntosGanadora=0;
		this.puntosTrueque=0;
		this.imagen=encodeTobase64(bitmap);
		
//		// POST de la entidad nueva
//		Gson gson = new Gson();
//		String json = gson.toJson(this, Trueque.class);
//		String entity="Trueque";
//		Log.i("POST","Trueque= "+json);
//		boolean ok = SDKFactory.getAPIFacade(context).post(entity, json);
//		Log.i("POST","-"+ok);
//		// POST.
	}
	
	

	public String getIdTrueque() {
		return idTrueque;
	}

	public void setIdTrueque(String idTrueque) {
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
	public Bitmap getImagen() {
		return decodeBase64(imagen);
		//return imagen;
	}

	public void setImagen(Context context, Bitmap imagen) 
			throws UnsupportedEncodingException, ClientProtocolException, IOException {
		
		this.imagen = encodeTobase64(imagen);
	}
	
	public static String encodeTobase64(Bitmap image)
	{
	    Bitmap immagex=image;
	    ByteArrayOutputStream baos = new ByteArrayOutputStream();
	    immagex.compress(Bitmap.CompressFormat.JPEG, 100, baos);
	    byte[] b = baos.toByteArray();
	    String imageEncoded = Base64.encodeToString(b,Base64.DEFAULT);

	    Log.e("LOOK", imageEncoded);
	    return imageEncoded;
	}
	public static Bitmap decodeBase64(String input) 
	{
	    byte[] decodedByte = Base64.decode(input, 0);
	    return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length); 
	}
	
	
	
	//+++++++++++++
	//+++++++++++++
	public String getUsuario(){
		return this.objeto.getDuenio();
	}

	public void addOferta(Oferta ofer){
		this.ofertas.add(ofer);
	}
	
	public boolean existOferta(String idOferta){
		if(this.ofertas==null || this.ofertas.isEmpty())
			return false;
		
		boolean existe=false;
		for(Oferta ofer: this.ofertas){
			if(ofer.getIdOferta().equals(idOferta))
				existe = true;
		}
		
		return existe;
	}
	
	public boolean rechazarOferta(String idOferta){
		if(this.ofertas==null || this.ofertas.isEmpty())
			return false;
		
		//boolean existe=false;
		for(Oferta ofer: this.ofertas){
			if(ofer.getIdOferta().equals(idOferta))
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

//	public void puntuarTrueque(int pts){
//		this.puntosTrueque=pts;
//		this.objeto.getDuenio().puntuar(pts);
//	}
//	public void puntuarGanadora(int pts){
//		this.puntosGanadora=pts;
//		this.ganadora.getUsuario().puntuar(pts);
//	}


	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, Trueque.class);
		return json;
	}
	
	public static Trueque fromJson(String json) {
		Gson gson = new Gson();
		Trueque t = gson.fromJson(json, Trueque.class);
		return t;
	}



	

	

}

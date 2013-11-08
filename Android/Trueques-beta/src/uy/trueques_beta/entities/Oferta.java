package uy.trueques_beta.entities;

import java.io.ByteArrayOutputStream;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;


public class Oferta {

	private int idOferta;
	private Objeto objeto;
	private boolean rechazada;
	private int  idTrueque;
	private String ubicacion;
	//private Bitmap imagen;
	private String imagen;
	
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
	public String getUsuario(){
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

	public Bitmap getImagen() {
		//return imagen;
		return decodeBase64(imagen);
	}

	public void setImagen(Bitmap imagen) {
		//this.imagen = imagen;
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
	
}

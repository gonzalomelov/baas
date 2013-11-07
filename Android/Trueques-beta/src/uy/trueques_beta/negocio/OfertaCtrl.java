package uy.trueques_beta.negocio;

import java.util.HashMap;
import java.util.Map;

import android.graphics.Bitmap;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Usuario;

public class OfertaCtrl {
	
	private Map<Integer, Oferta> ofertas = new HashMap<Integer, Oferta>();
	static private int idCont = 1;
	

	public Oferta getOferta(int id) {
		return ofertas.get(id);
	}
	

	public int crearOferta(String mail, Objeto obj, int idTrueque, String ubicacion, Bitmap imagen) {
		Usuario u = Factory.getUsuarioCtrl().getUsuario(mail);
		if (u==null){
			return -1;
		}
		Oferta ofer = new Oferta(idCont, obj, idTrueque, ubicacion);
		ofer.setImagen(imagen);
		this.ofertas.put(idCont, ofer);
		idCont++;
		return ofer.getIdOferta();
	}
	

}

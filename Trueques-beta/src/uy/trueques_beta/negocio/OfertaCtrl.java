package uy.trueques_beta.negocio;

import java.util.HashMap;
import java.util.Map;

import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Usuario;

public class OfertaCtrl {
	
	private Map<Integer, Oferta> ofertas = new HashMap<Integer, Oferta>();
	static private int idCont = 1;
	

	public Oferta getOferta(int id) {
		return ofertas.get(id);
	}
	

	public int crearOferta(String mail, Objeto obj) {
		Usuario u = Factory.getUsuarioCtrl().getUsuario(mail);
		if (u==null){
			return -1;
		}
		Oferta ofer = new Oferta(idCont, obj);
		this.ofertas.put(idCont, ofer);
		idCont++;
		return ofer.getIdOferta();
	}
	

}

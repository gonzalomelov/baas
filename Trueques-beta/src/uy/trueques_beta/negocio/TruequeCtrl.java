package uy.trueques_beta.negocio;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;

public class TruequeCtrl {
	
	private Map<Integer, Trueque> trueques = new HashMap<Integer, Trueque>();
	static private int idCont = 1;
	
	public Trueque getTrueque(int id) {
		return trueques.get(id);
	}

	public int crearTrueque(Objeto obj, String busca, float minVal, String ubicacion) {
		if (obj==null || !Factory.getObjetoCtrl().existObjeto((obj.getId()))){
			return -1;
		}
		
		Trueque t = new Trueque(idCont, obj, busca, minVal, ubicacion);
		t.getUsuario().setPublicados(t.getUsuario().getPublicados()+1);
		this.trueques.put(idCont, t);
		idCont++;
		return t.getIdTrueque();
	}
	
	public boolean aceptarOferta(int idTrueque, int idOferta){
		if(!this.trueques.containsKey(idTrueque) || !this.trueques.get(idTrueque).existOferta(idOferta))
			return false;
		
		//++++ ACEPTAR UNA OFERTA ++++//
		Trueque t= this.trueques.get(idTrueque);
		if (t.getGanadora()==null && t.isActiva()){
			Oferta ofer = Factory.getOfertaCtrl().getOferta(idOferta);
			if (!ofer.isRechazada()){
				t.setGanadora(ofer);
				t.setActiva(false);
				t.setFechaFin(new Date());
				t.getUsuario().setRealizados(t.getUsuario().getRealizados()+1);
				ofer.getUsuario().setAceptados(t.getUsuario().getAceptados()+1);
				ofer.getUsuario().setRealizados(ofer.getUsuario().getRealizados()+1);
				//Marco las demas ofertas como rechazadas
				for(Oferta o: t.getOfertas()){
					if(!o.equals(ofer))
						o.setRechazada(true);
				}
			}
		}
		return true;
			///+++++++++++++++///
	}
	
	public boolean rechazarOferta(int idTrueque, int idOferta){
		if(!this.trueques.containsKey(idTrueque) || !this.trueques.get(idTrueque).existOferta(idOferta))
			return false;
		
		return this.trueques.get(idTrueque).rechazarOferta(idOferta);
	}
	
	public List<Trueque> getTruequesActivos(){
		
		List<Trueque> trueques = new ArrayList<Trueque>();
		for(Trueque t: this.trueques.values()){
			if (t.isActiva())
				trueques.add(t);
		}
		
		return trueques;
	}
	
	public List<Trueque> getTruequesUsuario(String mail){
		
		List<Trueque> trueques = new ArrayList<Trueque>();
		for(Trueque t: this.trueques.values()){
			if ((t.getUsuario().getMail().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().getMail().equals(mail)))
				trueques.add(t);
		}
		
		return trueques;
	}
	
	public List<Trueque> getTrueques(){
		return new ArrayList<Trueque>(this.trueques.values());
	}
	
	public boolean crearOferta(int idTrueque, String mail, String nomObj, String desc, float valor, String ubicacion){
		Trueque t = this.trueques.get(idTrueque);
		if (t==null)
			return false;
		
		int idObj = Factory.getObjetoCtrl().crearObjeto(mail, nomObj, desc, valor);
		Objeto obj = Factory.getObjetoCtrl().getObjeto(idObj);
		if (obj==null)
			return false;
		int idOfer = Factory.getOfertaCtrl().crearOferta(mail, obj, idTrueque, ubicacion);
		Oferta ofer = Factory.getOfertaCtrl().getOferta(idOfer);
		if (ofer==null)
			return false;
		t.addOferta(ofer);
		return true;
	}
	
	public List<Oferta> getOfertasPendientes(String mail){
		if (mail == null || mail.isEmpty()){
			return new ArrayList<Oferta>();
		}
		
		List<Oferta> ofers = new ArrayList<Oferta>();
		for(Trueque t: this.trueques.values()){
			if (t.getUsuario().getMail().equals(mail))
				ofers.addAll(t.getOfertasPendientes());
		}
		return ofers;	
	}
}

package uy.trueques_beta.negocio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import uy.com.group05.baasclient.sdk.SDKFactory;
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

	public int crearTrueque(Context context, Objeto obj, String busca, float minVal, String ubicacion) {
		if (obj==null || !Factory.getObjetoCtrl().existObjeto((obj.getId()))){
			return -1;
		}
		
		Trueque t = new Trueque(idCont, obj, busca, minVal, ubicacion);
		//+++ crear usuario en baas
//		try{
			Gson gson = new Gson();
			String json = gson.toJson(t, Trueque.class);
			String entity="Trueque";
			Log.i("POST","Trueque= "+json);
		
//		boolean ok = SDKFactory.getAPIFacade(context).post(entity, json);
//		Log.i("POST","-"+ok);
//		}
//		catch (UnsupportedEncodingException e) {
//			Log.i("POST",e.getMessage());
//		}
//		catch (ClientProtocolException e) {
//			Log.i("POST",e.getMessage());
//		}
//		catch (IOException e) {
//			Log.i("POST",e.getMessage());
//		}
		//++++
		Usuario u = Factory.getUsuarioCtrl().getUsuario(t.getUsuario());
		u.setPublicados(u.getPublicados()+1);
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
				//DUENIO DE TRUEQUE
				Usuario u = Factory.getUsuarioCtrl().getUsuario(t.getUsuario());
				u.setRealizados(u.getRealizados()+1);
				//OFERTANTE
				u = Factory.getUsuarioCtrl().getUsuario(ofer.getUsuario());
				u.setAceptados(u.getAceptados()+1);
				u.setRealizados(u.getRealizados()+1);
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
			if ((t.getUsuario().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().equals(mail)))
				trueques.add(t);
		}
		
		return trueques;
	}
	
	public List<Trueque> getTrueques(){
		return new ArrayList<Trueque>(this.trueques.values());
	}
	
	public boolean crearOferta(int idTrueque, String mail, String nomObj, String desc, float valor, String ubicacion, Bitmap imagen){
		Trueque t = this.trueques.get(idTrueque);
		if (t==null)
			return false;
		
		int idObj = Factory.getObjetoCtrl().crearObjeto(mail, nomObj, desc, valor);
		Objeto obj = Factory.getObjetoCtrl().getObjeto(idObj);
		if (obj==null)
			return false;
		int idOfer = Factory.getOfertaCtrl().crearOferta(mail, obj, idTrueque, ubicacion, imagen);
		Oferta ofer = Factory.getOfertaCtrl().getOferta(idOfer);
		if (ofer==null)
			return false;
		
		//+++GSON
		Gson gson = new Gson();
		String json = gson.toJson(ofer, Oferta.class);
		String entity="Oferta";
		Log.i("POST","Oferta= "+json);
		//+++
		t.addOferta(ofer);
		return true;
	}
	
	public List<Oferta> getOfertasPendientes(String mail){
		if (mail == null || mail.isEmpty()){
			return new ArrayList<Oferta>();
		}
		
		List<Oferta> ofers = new ArrayList<Oferta>();
		for(Trueque t: this.trueques.values()){
			if (t.getUsuario().equals(mail))
				ofers.addAll(t.getOfertasPendientes());
		}
		return ofers;	
	}
	
	public void puntuarTrueque(int idTrueque, int pts){
		Trueque t = this.trueques.get(idTrueque);
		if(t!=null){
			t.setPuntosTrueque(pts);
			Factory.getUsuarioCtrl().getUsuario(t.getObjeto().getDuenio()).puntuar(pts);
		}
	}
	public void puntuarGanadora(int idTrueque, int pts){
		Trueque t = this.trueques.get(idTrueque);
		if(t!=null){
			t.setPuntosGanadora(pts);
			Factory.getUsuarioCtrl().getUsuario(t.getGanadora().getUsuario()).puntuar(pts);
		}
	}
}

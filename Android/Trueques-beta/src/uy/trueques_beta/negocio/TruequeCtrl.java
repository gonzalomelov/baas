package uy.trueques_beta.negocio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import uy.com.group05.baassdk.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;

public class TruequeCtrl {
	
	//private Map<Integer, Trueque> trueques = new HashMap<Integer, Trueque>();
	//static private int idCont = 1;
	private Activity act;
	
	public Trueque getTrueque(Context context, String id) {
		//SDK
		try
		{
			String entity = "Trueque";
			String query = "{idTrueque:\""+id+"\"}";
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			Log.i("[TruequeCtrl]:", json);
			//if (!json.isEmpty()){
			Gson gson = new Gson();
			
			Trueque[] ts = gson.fromJson(json, Trueque[].class);
			if(ts.length>0)
				return ts[0];
			//}
			else{
				return null;
			}
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[GetTrueque]:", e.getMessage());
			return null;
		}
		catch (ClientProtocolException e) {
			Log.i("[GetTrueque]:", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Log.i("[GetTrueque]:", e.getMessage());
			return null;
		}
		//SDK
		//return trueques.get(id);
	}

	public String crearTrueque(Context context, Objeto obj, String busca, float minVal, String ubicacion, Bitmap bitmap) {
		if (obj==null || !Factory.getObjetoCtrl().existObjeto(context, obj.getIdObjeto())){
			return "";
		}
		
		Trueque t= null;
		//Trueque t = new Trueque(idCont, obj, busca, minVal, ubicacion);
		//+++ crear usuario en baas
		try{
			
			t = new Trueque(obj, busca, minVal, ubicacion, bitmap);
			
			Gson gson = new Gson();
			String json = gson.toJson(t, Trueque.class);
			String entity="Trueque";
			Log.i("[crearTrueque]:","Trueque= "+json);
			boolean ok = SDKFactory.getAPIFacade(context).post(entity, json);
			Log.i("[crearTrueque]:","-"+ok);
			
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[crearTrueque]:",e.getMessage());
			return "";
		}
		catch (ClientProtocolException e) {
			Log.i("[crearTrueque]:",e.getMessage());
			return "";
		}
		catch (IOException e) {
			Log.i("[crearTrueque]:",e.getMessage());
			return "";
		}
		//++++
		if (t!=null){
			Usuario u = Factory.getUsuarioCtrl().getUsuario(context, t.getUsuario());
			u.setPublicados(u.getPublicados()+1);
			u.addTrueque(t.getIdTrueque());
			//this.trueques.put(idCont, t);
			//idCont++;
			
			try{
				Gson gson = new Gson();
				
				String query =  "{mail:\""+u.getMail()+"\"}";
				
				String trueques = gson.toJson(u.getTrueques());
				String values = "{publicados:\""+u.getPublicados()+"\", trueques:"+trueques+"}";
				
				SDKFactory.getAPIFacade(context).update("Usuario", query, values);
				
			}
			catch (UnsupportedEncodingException e) {
				Log.i("[updateUsuario]:",e.getMessage());
				return "";
			}
			catch (ClientProtocolException e) {
				Log.i("[crearTrueque]:",e.getMessage());
				return "";
			}
			catch (IOException e) {
				Log.i("[crearTrueque]:",e.getMessage());
				return "";
			}
			
			return t.getIdTrueque();
		}
		return "";
	}
	
	public boolean aceptarOferta(Context context, String idTrueque, String idOferta){
		this.act = (Activity) context;
		Trueque t = getTrueque(context, idTrueque);
		//(!this.trueques.containsKey(idTrueque) || !this.trueques.get(idTrueque).existOferta(idOferta))
		if(t==null || !t.existOferta(idOferta))
			return false;
		
		//++++ ACEPTAR UNA OFERTA ++++//
		//Trueque t= this.trueques.get(idTrueque);
		if (t.getGanadora()==null && t.isActiva()){
			Oferta ofer = Factory.getOfertaCtrl().getOferta(context, idOferta);
			if (ofer!=null && !ofer.isRechazada()){
				
				try
				{
					Gson gson = new Gson();
					
					t.setGanadora(ofer);
					t.setActiva(false);
					t.setFechaFin(new Date());

					String query =  "{idTrueque:\""+t.getIdTrueque()+"\"}";
					String values = "{ganadora:"+t.getGanadora().toJson()+", activa:\""+t.isActiva()+"\", fechaFin: "+gson.toJson(t.getFechaFin()) +"}";
					
					SDKFactory.getAPIFacade(context).update("Trueque", query, values);
					
					//DUENIO DE TRUEQUE
					Usuario u = Factory.getUsuarioCtrl().getUsuario(context, t.getUsuario());
					u.setRealizados(u.getRealizados()+1);
					
					query =  "{mail:\""+u.getMail()+"\"}";
					values = "{realizados:"+u.getRealizados()+"}";
					
					SDKFactory.getAPIFacade(context).update("Usuario", query, values);	
					
					//OFERTANTE
					Usuario uOfertante = Factory.getUsuarioCtrl().getUsuario(context, ofer.getUsuario());
					uOfertante.setAceptados(uOfertante.getAceptados()+1);
					uOfertante.setRealizados(uOfertante.getRealizados()+1);
					
					query =  "{mail:\""+uOfertante.getMail()+"\"}";
					values = "{aceptados:"+uOfertante.getAceptados()+", realizados:"+uOfertante.getRealizados()+"}";
					
					SDKFactory.getAPIFacade(context).update("Usuario", query, values);
					
					// Notificacion
					GCMService gcms = SDKFactory.getGCMService(act);
					gcms.sendNotificationToClient(uOfertante.getMail(), "message", t.getUsuario() + " aceptó tu oferta!", "dif", "ofertaAceptada");
					
					//Marco las demas ofertas como rechazadas
					for(Oferta o: t.getOfertas()){
						if(!o.equals(ofer))
							o.setRechazada(true);
					}
					
					query =  "{idTrueque:\""+t.getIdTrueque()+"\"}";
					values = "{rechazada:true}";
					
					SDKFactory.getAPIFacade(context).update("Oferta", query, values);
					
					//Actualizo la oferta ganadora
					query =  "{idOferta:\""+idOferta+"\"}";
					values = "{rechazada:false}";
					
					SDKFactory.getAPIFacade(context).update("Oferta", query, values);
				}
				catch (UnsupportedEncodingException e) {
					Log.i("[GetUsuario]:", e.getMessage());
					return false;
				}
				catch (ClientProtocolException e) {
					Log.i("[GetUsuario]:", e.getMessage());
					return false;
				}
				catch (IOException e) {
					Log.i("[GetUsuario]:", e.getMessage());
					return false;
				}
			}
		}
		return true;
			///+++++++++++++++///
	}
	
	public boolean rechazarOferta(Context context, String idTrueque, String idOferta){
		//if(!this.trueques.containsKey(idTrueque) || !this.trueques.get(idTrueque).existOferta(idOferta))
		Trueque t = getTrueque(context, idTrueque);
		if(t==null || !t.existOferta(idOferta))
			return false;
		
		try{
			
			if (!t.rechazarOferta(idOferta))//this.trueques.get(idTrueque).rechazarOferta(idOferta);
				return false;
			//Actualizo la oferta
			String query =  "{idOferta:\""+idOferta+"\"}";
			String values = "{rechazada:true}";
			
			SDKFactory.getAPIFacade(context).update("Oferta", query, values);
			
			//+++ RECHAZO LA OFERTA DENTRO DEL Trueque
			query = "{idTrueque:\""+t.getIdTrueque()+"\"}";
			//t.getOfertas();
			Gson gson = new Gson();
			String ofrs= gson.toJson(t.getOfertas());
			values = "{ofertas:"+ofrs+"}";
			
			SDKFactory.getAPIFacade(context).update("Trueque", query, values);
			//+++
			
			// Notificacion
			GCMService gcms = SDKFactory.getGCMService(act);
			gcms.sendNotificationToClient(Factory.getOfertaCtrl().getOferta(context, idOferta).getUsuario(), "message", t.getUsuario() + " rechazó tu oferta.", "dif", "ofertaRechazada");
			
			return true;
		}
		catch (UnsupportedEncodingException e) {
			Log.e("[RechazarOferta]:", e.getMessage());
			return false;
		}
		catch (ClientProtocolException e) {
			Log.e("[RechazarOferta]:", e.getMessage());
			return false;
		}
		catch (IOException e) {
			Log.e("[RechazarOferta]:", e.getMessage());
			return false;
		}
	}
	
	public List<Trueque> getTruequesActivos(Context context){
		//SDK
		try
		{
			String entity = "Trueque";
			String query = "{activa:true}";
			
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			Log.i("[TruequeCtrl]:", json);
			Gson gson = new Gson();
			Trueque[] arrayTrueques = gson.fromJson(json, Trueque[].class);
			return Arrays.asList(arrayTrueques);
		}
		catch (UnsupportedEncodingException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (ClientProtocolException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (IOException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		//SDK
		
		
//		List<Trueque> trueques = new ArrayList<Trueque>();
//		for(Trueque t: this.trueques.values()){
//			if (t.isActiva())
//				trueques.add(t);
//		}
//		
//		return trueques;
	}
	
	public List<Trueque> getTruequesHechosUsuario(Context context, String mail){
		
//		List<Trueque> trueques = new ArrayList<Trueque>();
//		for(Trueque t: this.trueques.values()){
//			if ((t.getUsuario().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().equals(mail)))
//				trueques.add(t);
//		}
		//SDK
		try
		{
			String entity = "Trueque";
			String query = "{}"; //if ((t.getUsuario().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().equals(mail)))
			
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			//Log.i("[TruequeCtrl]:", json);
			Gson gson = new Gson();
			Trueque[] arrayTrueques = gson.fromJson(json, Trueque[].class);
			
			List<Trueque> truequesHechos = new ArrayList<Trueque>();
			List<Trueque> trueques = Arrays.asList(arrayTrueques);
			
			for (Trueque t : trueques) {
				if ((t.getUsuario().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().equals(mail))) {
					truequesHechos.add(t);
				}
			}
			
			return truequesHechos;
		}
		catch (UnsupportedEncodingException e) {
			Log.e("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (ClientProtocolException e) {
			Log.e("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (IOException e) {
			Log.e("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		//SDK
		
		//return trueques;
	}
	
	public List<Trueque> getTruequesUsuario(Context context, String mail){
		
//		List<Trueque> trueques = new ArrayList<Trueque>();
//		for(Trueque t: this.trueques.values()){
//			if ((t.getUsuario().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().equals(mail)))
//				trueques.add(t);
//		}
		//SDK
		try
		{
			String entity = "Trueque";
			String query = "{}"; //if ((t.getUsuario().equals(mail) && !t.isActiva()) || (t.getGanadora()!=null && t.getGanadora().getUsuario().equals(mail)))
			
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			//Log.i("[TruequeCtrl]:", json);
			Gson gson = new Gson();
			Trueque[] arrayTrueques = gson.fromJson(json, Trueque[].class);
			
			List<Trueque> trueques = Arrays.asList(arrayTrueques);
			
			return trueques;
		}
		catch (UnsupportedEncodingException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (ClientProtocolException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (IOException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		//SDK
		
		//return trueques;
	}
	
	public List<Trueque> getTruequesDeUsuario(Context context, String mail){
		//SDK
		try
		{
			String entity = "Trueque";
			String query = "{\"objeto.duenio\":\""+mail+"\"}"; //{"objeto.duenio" : "w@"}
			
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			//Log.i("[TruequeCtrl]:", json);
			Gson gson = new Gson();
			Trueque[] arrayTrueques = gson.fromJson(json, Trueque[].class);
			return Arrays.asList(arrayTrueques);
		}
		catch (UnsupportedEncodingException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (ClientProtocolException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (IOException e) {
			Log.i("GetTruequesActivos:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		//SDK
		
		//return trueques;
	}
	
	public List<Trueque> getTrueques(Context context){
		//return new ArrayList<Trueque>(this.trueques.values());
		//SDK
		try
		{
			String entity = "Trueque";
			String query = ""; //
			
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			//Log.i("[TruequeCtrl]:", json);
			Gson gson = new Gson();
			Trueque[] arrayTrueques = gson.fromJson(json, Trueque[].class);
			return Arrays.asList(arrayTrueques);
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[GetTrueques]:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (ClientProtocolException e) {
			Log.i("[GetTrueques]:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		catch (IOException e) {
			Log.i("[GetTrueques]:", e.getMessage());
			return new ArrayList<Trueque>();
		}
		//SDK		
	}
	
	public boolean crearOferta(Context context, String idTrueque, String mail, String nomObj, String desc, float valor, String ubicacion, Bitmap imagen){
		this.act = (Activity) context;
		Trueque t = getTrueque(context, idTrueque); //this.trueques.get(idTrueque);
		if (t==null)
			return false;
		
		String idObj = Factory.getObjetoCtrl().crearObjeto(context, mail, nomObj, desc, valor);
		Objeto obj = Factory.getObjetoCtrl().getObjeto(context, idObj);
		if (obj==null){
			//SDKFactory.getAPIFacade(context).delete(entity, query)
			return false;
		}
		String idOfer = Factory.getOfertaCtrl().crearOferta(context, mail, obj, idTrueque, ubicacion, imagen);
		Oferta ofer = Factory.getOfertaCtrl().getOferta(context, idOfer);
		if (ofer==null){ //Borro el objeto
			String entity_aux = "Objeto";
			String query_aux = "{idObjeto:\""+idObj+"\"}";
			try {
				SDKFactory.getAPIFacade(context).delete(entity_aux, query_aux);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				return false;
			}
			return false;
		}

		t.addOferta(ofer);
		
		try{
			Gson gson = new Gson();
			
			String query =  "{idTrueque:\""+idTrueque+"\"}";
			
			String ofertas = gson.toJson(t.getOfertas());
			String values = "{ofertas:"+ofertas+"}";
			
			boolean ok = SDKFactory.getAPIFacade(context).update("Trueque", query, values);
			if (ok) {
				// Notificacion
				GCMService gcms = SDKFactory.getGCMService(act);
				gcms.sendNotificationToClient(t.getUsuario(), "message", "Nueva oferta de " + mail, "dif", "ofertaNueva");
			}
			
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[crearOferta]:",e.getMessage());
			return false;
		}
		catch (ClientProtocolException e) {
			Log.i("[crearOferta]:",e.getMessage());
			return false;
		}
		catch (IOException e) {
			Log.i("[crearOferta]:",e.getMessage());
			return false;
		}
		
		
		return true;
	}
	
	public List<Oferta> getOfertasPendientes(Context context, String mail){
		if (mail == null || mail.isEmpty()){
			return new ArrayList<Oferta>();
		}
		
		List<Trueque> trueques = getTruequesDeUsuario(context, mail);
		List<Oferta> ofers = new ArrayList<Oferta>();
		for(Trueque t: trueques){
			if (t.getUsuario().equals(mail))
				ofers.addAll(t.getOfertasPendientes());
		}
		return ofers;	
	}
	
	public void puntuarTrueque(Context context, String idTrueque, int pts){
		Trueque t = getTrueque(context, idTrueque);//this.trueques.get(idTrueque);
		if(t!=null){
			t.setPuntosTrueque(pts);
			Factory.getUsuarioCtrl().getUsuario(context, t.getObjeto().getDuenio()).puntuar(pts);
			
			try {
				String query =  "{idTrueque:\""+idTrueque+"\"}";
				String values = "{puntosTrueque:"+pts+"}";
				
				SDKFactory.getAPIFacade(context).update("Trueque", query, values);
				
				Usuario usu = Factory.getUsuarioCtrl().getUsuario(context, t.getObjeto().getDuenio());
				
				query =  "{mail:\""+usu.getMail()+"\"}";
				values = "{total:"+(usu.getTotal()+1)+", puntos:"+(usu.getPuntos()+pts)+"}";
				
				SDKFactory.getAPIFacade(context).update("Usuario", query, values);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void puntuarGanadora(Context context, String idTrueque, int pts){
		Trueque t = getTrueque(context, idTrueque);//this.trueques.get(idTrueque);
		if(t!=null){
			t.setPuntosGanadora(pts);
			//Factory.getUsuarioCtrl().getUsuario(context, t.getGanadora().getObjeto().getDuenio()).puntuar(pts);
//			this.total++;
//			this.puntos+=pts;
			
			try {
				String query =  "{idTrueque:\""+idTrueque+"\"}";
				String values = "{puntosGanadora:"+pts+"}";
				
				SDKFactory.getAPIFacade(context).update("Trueque", query, values);
				
				Usuario usu = Factory.getUsuarioCtrl().getUsuario(context, t.getGanadora().getObjeto().getDuenio());
				
				query =  "{mail:\""+usu.getMail()+"\"}";
				values = "{total:"+(usu.getTotal()+1)+", puntos:"+(usu.getPuntos()+pts)+"}";
				
				SDKFactory.getAPIFacade(context).update("Usuario", query, values);
				
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean setImagen(Context context, String idTrueque, Bitmap bitmap) {
		Trueque t = getTrueque(context, idTrueque);//this.trueques.get(idTrueque);
		if(t!=null){
			try {
				t.setImagen(context, bitmap);
			} catch (UnsupportedEncodingException e) {
				Log.i("SETIMAGEN: ",e.getMessage());
				return false;
			} catch (ClientProtocolException e) {
				Log.i("SETIMAGEN: ",e.getMessage());
				return false;
			} catch (IOException e) {
				Log.i("SETIMAGEN: ",e.getMessage());
				return false;
			}
			return true;
		}
		return false;
		
	}
}

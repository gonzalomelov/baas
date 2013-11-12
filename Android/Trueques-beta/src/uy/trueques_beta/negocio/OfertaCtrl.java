package uy.trueques_beta.negocio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Oferta;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;

public class OfertaCtrl {
	
//	private Map<Integer, Oferta> ofertas = new HashMap<Integer, Oferta>();
//	static private int idCont = 1;
	

	public Oferta getOferta(Context context, String id) {
		//SDK
		try
		{
			String entity = "Oferta";
			String query = "{idOferta:\""+id+"\"}";
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			Log.i("[OfertaCtrl]:", json);
			//if (!json.isEmpty()){
			Gson gson = new Gson();
			Oferta[] os = gson.fromJson(json, Oferta[].class);
			if(os.length>0)
				return os[0];
			//}
			else{
				return null;
			}
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[GetOferta]:", e.getMessage());
			return null;
		}
		catch (ClientProtocolException e) {
			Log.i("[GetOferta]:", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Log.i("[GetOferta]:", e.getMessage());
			return null;
		}
		//SDK
		//return ofertas.get(id);
	}
	

	public String crearOferta(Context context, String mail, Objeto obj, String idTrueque, String ubicacion, Bitmap imagen) {
		Usuario u = Factory.getUsuarioCtrl().getUsuario(context, mail);
		if (u==null){
			return "";
		}
		Oferta ofer= null;
		//+++ crear usuario en baas
		try{
			
			ofer= new Oferta(obj, idTrueque, ubicacion, imagen);
			
			Gson gson = new Gson();
			String json = gson.toJson(ofer, Oferta.class);
			String entity="Oferta";
			Log.i("[crearOferta]:","Oferta= "+json);
			boolean ok = SDKFactory.getAPIFacade(context).post(entity, json);
			Log.i("[crearOferta]:","-"+ok);
			
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[crearOferta]:",e.getMessage());
			return "";
		}
		catch (ClientProtocolException e) {
			Log.i("[crearOferta]:",e.getMessage());
			return "";
		}
		catch (IOException e) {
			Log.i("[crearOferta]:",e.getMessage());
			return "";
		}
		//++++
		if (ofer!=null){
			
			u.addOferta(ofer.getIdOferta());
			
			try{
				Gson gson = new Gson();
				
				String query =  "{mail:\""+u.getMail()+"\"}";
				
				String ofertas = gson.toJson(u.getOfertas());
				String values = "{ofertas:"+ofertas+"}";
				
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
			
			return ofer.getIdOferta();
		}
		return "";
		//return ofer.getIdOferta();
	}
	

}

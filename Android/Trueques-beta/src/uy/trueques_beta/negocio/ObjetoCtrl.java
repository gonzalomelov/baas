package uy.trueques_beta.negocio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import uy.com.group05.baassdk.SDKFactory;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Trueque;
import uy.trueques_beta.entities.Usuario;

public class ObjetoCtrl {
	
//	private Map<Integer, Objeto> objetos;
//	static private int idCont = 1;
	
	public ObjetoCtrl(){
//		this.objetos = new HashMap<Integer, Objeto>();
//		//Inicio con algo..
//		Usuario u = Factory.getUsuarioCtrl().getUsuario("n@");
//		Objeto obj = new Objeto("PC de escritorio", "PC: PIV-HT 2.6GHz, 500GB, 2GB, teclado y mouse. Sin monitor", 150, u.getMail());
//		u.addObjeto(obj);
//		this.objetos.put(idCont, obj);
//		idCont++;
//		//UsuarioCtrl.idCont++;
//		//++++
	}

	public Objeto getObjeto(Context context, String id) {
		//SDK
		try
		{
			String entity = "Objeto";
			String query = "{idObjeto:\""+id+"\"}";
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			Log.i("[ObjetoCtrl]:", json);
			//if (!json.isEmpty()){
			Gson gson = new Gson();
			Objeto[] os = gson.fromJson(json, Objeto[].class);
			if (os.length>0)
				return os[0];
			//}
			else{
				return null;
			}
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[GetObjeto]:", e.getMessage());
			return null;
		}
		catch (ClientProtocolException e) {
			Log.i("[GetObjeto]:", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Log.i("[GetObjeto]:", e.getMessage());
			return null;
		}
		//SDK
		//return trueques.get(id);
		//return objetos.get(id);
	}
	
	public boolean existObjeto(Context context, String id){
		return getObjeto(context, id)!=null;
	}

	public String crearObjeto(Context context, String mail, String nomObj, String desc, float valor) {
		Usuario u = Factory.getUsuarioCtrl().getUsuario(context, mail);
		if (u==null){
			return "";
		}
		
		Objeto obj =null;
		try{
			
			obj =  new Objeto(nomObj, desc, valor, u.getMail());
			
			Gson gson = new Gson();
			String json = gson.toJson(obj, Objeto.class);
			String entity="Objeto";
			Log.i("[crearObjeto]:","Objeto= "+json);
			boolean ok = SDKFactory.getAPIFacade(context).post(entity, json);
			Log.i("[crearObjeto]:","-"+ok);
			
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[crearObjeto]:",e.getMessage());
			return "";
		}
		catch (ClientProtocolException e) {
			Log.i("[crearObjeto]:",e.getMessage());
			return "";
		}
		catch (IOException e) {
			Log.i("[crearObjeto]:",e.getMessage());
			return "";
		}
		//++++
		if (obj!=null){
			u.addTrueque(obj.getIdObjeto());
			return obj.getIdObjeto();
		}else{
			return "";
		}//return obj.getIdObjeto();
	}
	
	//++++++++

}

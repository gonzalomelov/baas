package uy.trueques_beta.negocio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;

import persistence.Cursor;
import persistence.Operador;
import persistence.Query;












import com.google.gson.Gson;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import sdk.application.ApplicationInfo;
import sdk.classes.JSON;
import sdk.servicios.Clientes;
import sdk.servicios.Persistencia;
import uy.com.group05.baassdk.GCMService;
import uy.com.group05.baassdk.MyApplication;
import uy.com.group05.baassdk.SDKFactory;
import uy.com.group05.baassdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baassdk.entities.ClientRegistrationDTO;
import uy.trueques_beta.entities.Admin;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.entities.Trueque;

public class UsuarioCtrl {

	//private Map<String, Usuario> usuarios;
	private Map<String, Admin> admins;
	//static private int idCont = 1;
	
	public UsuarioCtrl(){
		//this.usuarios = new HashMap<String, Usuario>();
		this.admins = new HashMap<String, Admin>();
		//Inicio con algo..
		//List<Objeto> objs = new ArrayList<Objeto>();
		//objs.add(new Objeto(1, "PC de escritorio", "PC: PIV-HT 2.6GHz, 500GB, 2GB, teclado y mouse. Sin monitor", 150));
//		for (int i=1; i<25; i++)
//			this.usuarios.put("u"+i+"@", new Usuario("user"+i, "u"+i+"@", "1234"));
//		
//		this.usuarios.put("n@", new Usuario("nacho","n@", "1234"));
//		this.usuarios.put("w@", new Usuario("nacho","w@", "1234"));
		this.admins.put("admin@", new Admin("Admin","admin@", "1234"));
		//UsuarioCtrl.idCont++;
		//++++
	}

	public Usuario getUsuario(Context context, String mail) {
		//SDK
		try
		{
			String entity = "Usuario";
			String query = "{mail:\""+mail+"\"}";
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			Log.i("[UsuarioCtrl]:", json);
			//if (!json.isEmpty()){
				Gson gson = new Gson();
				Usuario[] us = gson.fromJson(json, Usuario[].class);
				if (us.length>0){
					return us[0];
				}
				else{
					return null;
				}
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[GetUsuario]:", e.getMessage());
			return null;
		}
		catch (ClientProtocolException e) {
			Log.i("[GetUsuario]:", e.getMessage());
			return null;
		}
		catch (IOException e) {
			Log.i("[GetUsuario]:", e.getMessage());
			return null;
		}
		//SDK
//		return usuarios.get(mail);
	}
	
//	public Admin getAdmin(Context context, String mail) {
//		//SDK
//		try
//		{
//			String entity = "Admin";
//			String query = "{mail:\""+mail+"\"}";
//			String json = SDKFactory.getAPIFacade(context).get(entity, query);
//			
//			Log.i("[UsuarioCtrl]:", json);
//			if (!json.isEmpty()){
//				Gson gson = new Gson();
//				Admin a = gson.fromJson(json, Admin.class);
//				return a;
//			}
//			else{
//				return null;
//			}
//		}
//		catch (UnsupportedEncodingException e) {
//			Log.i("[GetAdmin]:", e.getMessage());
//			return null;
//		}
//		catch (ClientProtocolException e) {
//			Log.i("[GetAdmin]:", e.getMessage());
//			return null;
//		}
//		catch (IOException e) {
//			Log.i("[GetAdmin]:", e.getMessage());
//			return null;
//		}
//		//SDK
//		//return admins.get(mail);
//	}
	
	public Admin getAdmin(Context context, String mail) {
		//SDK
		try
		{
			Gson gson = new Gson();
			
			Query q = new Query();
            q.setTabla("admin");
            q.setAtributo("_id");
            q.setOperador(Operador.igual);
            q.setValor("1");
            
            Cursor c = Persistencia.selectJson(q);
            JSON[] jsons = c.getJsons();
            JSON jsonObj = jsons[0];
            Map<String, Object> jsonMap = jsonObj.getJson();
            
            String json = (String)jsonMap.get("json");
            
			Log.i("[UsuarioCtrl]:", json);
			if (!json.isEmpty()){
				Admin a = gson.fromJson(json, Admin.class);
				return a;
			}
			else{
				return null;
			}
		}
		catch (Exception e) {
			Log.i("[GetAdmin]:", e.getMessage());
			return null;
		}
	}
	
	public List<Usuario> getUsuarios(Context context){
		//SDK
		try
		{
			String entity = "Usuario";
			String query = "{}";
			
			String json = SDKFactory.getAPIFacade(context).get(entity, query);
			
			Log.i("[UsuarioCtrl]:", json);
			Gson gson = new Gson();
			Usuario[] arrayTrueques = gson.fromJson(json, Usuario[].class);
			return Arrays.asList(arrayTrueques);
		}
		catch (UnsupportedEncodingException e) {
			Log.i("[GetUsuarios]:", e.getMessage());
			return new ArrayList<Usuario>();
		}
		catch (ClientProtocolException e) {
			Log.i("[GetUsuarios]:", e.getMessage());
			return new ArrayList<Usuario>();
		}
		catch (IOException e) {
			Log.i("[GetUsuarios]:", e.getMessage());
			return new ArrayList<Usuario>();
		}
		//SDK
		//return new ArrayList<Usuario>(this.usuarios.values());
	}

	public boolean registrarUsuario(Context context, String mail, String nombre, String pass) {
		if (getUsuario(context, mail)!=null){
			return false;
		}
		//this.usuarios.put(mail, u);
		//SDK
		
		ClientRegistrationDTO clientRegistration;
		ClientAuthenticationDTO auten ;
		boolean ok=false;
		try {
			//Registro al cliente
			clientRegistration = SDKFactory.getClientFacade(context).register(mail, pass, nombre, "");
			Log.i("REGISTRO","-"+clientRegistration.isOk());
			//Autentico
			if (clientRegistration.isOk()){
				auten = SDKFactory.getClientFacade(context).authenticate(mail, pass);
				Log.i("LOGIN","-"+auten.isOk());
				
				//Creo la entidad usuario asociada
				Usuario u = new Usuario(nombre,mail, pass);
				Gson gson = new Gson();
				String json = gson.toJson(u, Usuario.class);
				String entity="Usuario";
				//SDKFactory.getAPIFacade(context).post(entity, json);
				
				Log.i("[registrarUsuario]:","Usuario= "+json);
				ok = SDKFactory.getAPIFacade(context).post(entity, json);
				
				// Inicializo GCM con usuario vacío
				SDKFactory.getGCMService((Activity) context, "");
			
				//Para actualizar todos los datos
				MyApplication myApplication = (MyApplication)(context.getApplicationContext());
				SDKFactory.getAPIFacade(context).updateAll(myApplication.getmTablesDB());
				
				return clientRegistration.isOk() && auten.isOk() && ok;
			}	
			return clientRegistration.isOk();
		}
		catch (UnsupportedEncodingException e) {
			Log.i("REGISTRO",e.getMessage());
			return false;
		}
		catch (ClientProtocolException e) {
			Log.i("REGISTRO",e.getMessage());
			return false;
		}
		catch (IOException e) {
			Log.i("REGISTRO",e.getMessage());
			return false;
		}
		//SDK
	
		//return true;
	}
	
//	public boolean registrarUsuarioAdmin(String mail, String nombre, String pass) {
//		//++++++FALTA++++++
//		if (this.admins.containsKey(mail)){
//			return false;
//		}
//		this.admins.put(mail, new Admin(nombre,mail, pass));
//		return true;
//	}
	
	public boolean registrarUsuarioAdmin(Context context, String mail, String nombre, String pass) {
		
		try {
			String registro = Clientes.registrarse(mail, pass, nombre, nombre, ApplicationInfo.getDeviceId(), (Activity)context);
			
			//Autentico
			if (registro.contains("OK")){
				String login = Clientes.login(mail, pass, (Activity)context);
				
				Log.i("LOGIN","-"+login.contains("OK"));
				
				//Creo la entidad usuario asociada
				Admin admin = new Admin(nombre, mail, pass);
	        	
	        	Gson gson = new Gson();
	            
	            JSON json = new JSON();
	            json.addAtributo("nombre", nombre);
	            json.addAtributo("mail", mail);
	            json.addAtributo("pass", pass);
	            json.addAtributo("json", gson.toJson(admin));
	            
	            int insert = Persistencia.insertJson(json, "admin");
			
				//Para actualizar todos los datos
				MyApplication myApplication = (MyApplication)(context.getApplicationContext());
				SDKFactory.getAPIFacade(context).updateAll(myApplication.getmTablesDB());
				
				return registro.contains("OK") && login.contains("OK") && insert > 0;
			}
			
			return registro.equals("OK");
		}
		catch (Exception e) {
			
		}
		
		return false;
	}
	
	
	public Boolean login(Context context, String email, String password) {
		//SDK
		ClientAuthenticationDTO auten ;
		try
		{
			auten = SDKFactory.getClientFacade(context).authenticate(email, password);
			
			MyApplication myApplication = (MyApplication)(context.getApplicationContext());
			SDKFactory.getAPIFacade(context).updateAll(myApplication.getmTablesDB());
			
			Log.i("LOGIN","-"+auten.isOk());
			if (auten.isOk()) {
				SharedPreferences prefs = context.getSharedPreferences("TruequesData", Context.MODE_PRIVATE);
				SDKFactory.getGCMService((Activity) context, prefs.getString("mail", ""));
			}
			return auten.isOk();
		}
		catch (UnsupportedEncodingException e) {
			Log.i("LOGIN",e.getMessage());
			return false;
		}
		catch (ClientProtocolException e) {
			Log.i("LOGIN",e.getMessage());
			return false;
		}
		catch (IOException e) {
			Log.i("LOGIN",e.getMessage());
			return false;
		}
		//SDK
//		Usuario u= usuarios.get(email);
//		if (u==null)
//			return false;
//		else if (u.getPass().equals(password))
//			return true;
//		else 
//			return false;
	}

//	public boolean loginAsAdmin(Context context, String email, String password) {
//		//SDK
//		ClientAuthenticationDTO auten ;
//		try
//		{
//			auten = SDKFactory.getClientFacade(context).authenticate(email, password);
//			Log.i("LOGIN","-"+auten.isOk());
//			return auten.isOk();
//		}
//		catch (UnsupportedEncodingException e) {
//			Log.i("LOGIN",e.getMessage());
//			return false;
//		}
//		catch (ClientProtocolException e) {
//			Log.i("LOGIN",e.getMessage());
//			return false;
//		}
//		catch (IOException e) {
//			Log.i("LOGIN",e.getMessage());
//			return false;
//		}
//		//SDK
////		Admin a= admins.get(email);
////		if (a==null)
////			return false;
////		else if (a.getPass().equals(password))
////			return true;
////		else 
////			return false;
//	}
	
	public boolean loginAsAdmin(Context context, String email, String password) {

		boolean ret = false;
		
		try {
			String login = Clientes.login(email, password, (Activity)context);
			ret = login.contains("OK");
		}
		catch (Exception e) {
			
		}
		
		return ret;
	}
	
	public boolean bloquear(Context context, String mail){
		Usuario u=getUsuario(context, mail);//this.usuarios.get(mail);
		if (u==null)
			return false;
		u.setBloqueado(true);
		
		String query =  "{mail:\""+mail+"\"}";
		String values = "{bloqueado:"+true+"}";
		
		try {
			SDKFactory.getAPIFacade(context).update("Usuario", query, values);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	public boolean desbloquear(Context context, String mail){
		Usuario u=getUsuario(context, mail);//this.usuarios.get(mail);
		if (u==null)
			return false;
		
		u.setBloqueado(false);
		
		String query =  "{mail:\""+mail+"\"}";
		String values = "{bloqueado:"+false+"}";
		
		try {
			SDKFactory.getAPIFacade(context).update("Usuario", query, values);
		}
		catch (Exception e) {
			return false;
		}
		
		return true;
	}
	
	
	//++++++++

	
}

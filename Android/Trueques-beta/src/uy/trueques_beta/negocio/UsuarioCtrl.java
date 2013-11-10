package uy.trueques_beta.negocio;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.client.ClientProtocolException;








import com.google.gson.Gson;

import android.content.Context;
import android.util.Log;
import uy.com.group05.baassdk.SDKFactory;
import uy.com.group05.baassdk.entities.ClientAuthenticationDTO;
import uy.com.group05.baassdk.entities.ClientRegistrationDTO;
import uy.trueques_beta.entities.Admin;
import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Usuario;
import uy.trueques_beta.entities.Trueque;

public class UsuarioCtrl {

	private Map<String, Usuario> usuarios;
	private Map<String, Admin> admins;
	//static private int idCont = 1;
	
	public UsuarioCtrl(){
		this.usuarios = new HashMap<String, Usuario>();
		this.admins = new HashMap<String, Admin>();
		//Inicio con algo..
		//List<Objeto> objs = new ArrayList<Objeto>();
		//objs.add(new Objeto(1, "PC de escritorio", "PC: PIV-HT 2.6GHz, 500GB, 2GB, teclado y mouse. Sin monitor", 150));
		for (int i=1; i<25; i++)
			this.usuarios.put("u"+i+"@", new Usuario("user"+i, "u"+i+"@", "1234", new ArrayList<Objeto>()));
		
		this.usuarios.put("n@", new Usuario("nacho","n@", "1234", new ArrayList<Objeto>()));
		this.usuarios.put("w@", new Usuario("nacho","w@", "1234", new ArrayList<Objeto>()));
		this.admins.put("admin@", new Admin("Admin","admin@", "1234"));
		//UsuarioCtrl.idCont++;
		//++++
	}

	public Usuario getUsuario(String mail) {
		return usuarios.get(mail);
	}
	
	public Admin getAdmin(String mail) {
		return admins.get(mail);
	}
	
	public List<Usuario> getUsuarios(){
		return new ArrayList<Usuario>(this.usuarios.values());
	}

	public boolean registrarUsuario(Context context, String mail, String nombre, String pass) {
		if (this.usuarios.containsKey(mail)){
			return false;
		}
		this.usuarios.put(mail, new Usuario(nombre,mail, pass,new ArrayList<Objeto>()));
		//SDK
		
		ClientRegistrationDTO clientRegistration;
		ClientAuthenticationDTO auten ;
		try {
			clientRegistration = SDKFactory.getClientFacade(context).register(mail, pass, nombre, "");
			Log.i("REGISTRO","-"+clientRegistration.isOk());
			
			auten = SDKFactory.getClientFacade(context).authenticate(mail, pass);
			Log.i("LOGIN","-"+auten.isOk());
			
			return clientRegistration.isOk() && auten.isOk();
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
	
	public boolean registrarUsuarioAdmin(String mail, String nombre, String pass) {
		if (this.admins.containsKey(mail)){
			return false;
		}
		this.admins.put(mail, new Admin(nombre,mail, pass));
		return true;
	}
	

	public Boolean login(Context context, String email, String password) {
		//SDK
//		ClientAuthenticationDTO auten ;
//		try
//		{
//			auten = SDKFactory.getClientFacade(context).authenticate(email, password);
//			Log.i("LOGIN","-"+auten.isOk());
//			
////			//+++ crear usuario en baas
////			Gson gson = new Gson();
////			String json = gson.toJson(this.getUsuario("u2@"), Usuario.class);
////			String entity="Usuario";
////			Log.i("POST","Usuario= "+json);
////			
////			boolean ok = SDKFactory.getAPIFacade(context).post(entity, json);
////			Log.i("POST","-"+ok);
////			//++++
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
		//SDK
		Usuario u= usuarios.get(email);
		if (u==null)
			return false;
		else if (u.getPass().equals(password))
			return true;
		else 
			return false;
	}

	public boolean loginAsAdmin(String mEmail, String mPassword) {
		Admin a= admins.get(mEmail);
		if (a==null)
			return false;
		else if (a.getPass().equals(mPassword))
			return true;
		else 
			return false;
	}
	
	public boolean bloquear(String mail){
		Usuario u=this.usuarios.get(mail);
		if (u==null)
			return false;
		u.setBloqueado(true);
		return true;
	}
	
	public boolean desbloquear(String mail){
		Usuario u=this.usuarios.get(mail);
		if (u==null)
			return false;
		u.setBloqueado(false);
		return true;
	}
	
	
	//++++++++

	
}

package uy.trueques_beta.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public boolean registrarUsuario(String mail, String nombre, String pass) {
		if (this.usuarios.containsKey(mail)){
			return false;
		}
		this.usuarios.put(mail, new Usuario(nombre,mail, pass,new ArrayList<Objeto>()));
		return true;
	}
	
	public boolean registrarAdmin(String mail, String nombre, String pass) {
		if (this.admins.containsKey(mail)){
			return false;
		}
		this.admins.put(mail, new Admin(nombre,mail, pass));
		return true;
	}

	public Boolean login(String mEmail, String mPassword) {
		Usuario u= usuarios.get(mEmail);
		if (u==null)
			return false;
		else if (u.getPass().equals(mPassword))
			return true;
		else 
			return false;
	}

	public Boolean loginAsAdmin(String mEmail, String mPassword) {
		Admin a= admins.get(mEmail);
		if (a==null)
			return false;
		else if (a.getPass().equals(mPassword))
			return true;
		else 
			return false;
	}
	
	
	//++++++++

	
}

package uy.trueques_beta.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uy.trueques_beta.entities.Objeto;
import uy.trueques_beta.entities.Usuario;

public class ObjetoCtrl {
	
	private Map<Integer, Objeto> objetos;
	static private int idCont = 1;
	
	public ObjetoCtrl(){
		this.objetos = new HashMap<Integer, Objeto>();
		//Inicio con algo..
		Usuario u = Factory.getUsuarioCtrl().getUsuario("n@");
		Objeto obj = new Objeto(1, "PC de escritorio", "PC: PIV-HT 2.6GHz, 500GB, 2GB, teclado y mouse. Sin monitor", 150, u);
		u.addObjeto(obj);
		this.objetos.put(idCont, obj);
		idCont++;
		//UsuarioCtrl.idCont++;
		//++++
	}

	public Objeto getObjeto(int id) {
		return objetos.get(id);
	}
	
	public boolean existObjeto(int id){
		return objetos.containsKey(id);
	}

	public int crearObjeto(String mail, String nomObj, String desc, float valor) {
		Usuario u = Factory.getUsuarioCtrl().getUsuario(mail);
		if (u==null){
			return -1;
		}
		Objeto obj = new Objeto(idCont, nomObj, desc, valor, u);
		u.addObjeto(obj);
		this.objetos.put(idCont, obj);
		idCont++;
		return obj.getId();
	}
	
	//++++++++

}

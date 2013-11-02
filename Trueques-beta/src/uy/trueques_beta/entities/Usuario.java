package uy.trueques_beta.entities;

import java.util.ArrayList;
import java.util.List;

public class Usuario {

	//private int id;
	private String nombre;
	private String mail;
	private String pass;
	private List<Objeto> objetos = new ArrayList<Objeto>();
	private List<Oferta> ofertas = new ArrayList<Oferta>();
	private List<Trueque> trueques = new ArrayList<Trueque>();
	//+++
	private int publicados; //trueques
	private int aceptados; //trueques aceptados
	private int realizados; //trueques propuestos y aceptados
	
	public Usuario(String nombre, String mail, String pass, List<Objeto> objetos) {
		super();
		//this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.pass = pass;
		this.objetos = objetos;
		
		this.publicados =0;
		this.aceptados =0;
		this.realizados =0;
	}
	
//	public int getId() {
//		return id;
//	}
//	public void setId(int id) {
//		this.id = id;
//	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public List<Objeto> getObjetos() {
		return objetos;
	}
	public void setObjetos(List<Objeto> objetos) {
		this.objetos = objetos;
	}
	public int getPublicados() {
		return publicados;
	}

	public void setPublicados(int publicados) {
		this.publicados = publicados;
	}

	public int getAceptados() {
		return aceptados;
	}

	public void setAceptados(int aceptados) {
		this.aceptados = aceptados;
	}

	public int getRealizados() {
		return realizados;
	}

	public void setRealizados(int realizados) {
		this.realizados = realizados;
	}
	
	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	//+++++++

	public void addObjeto(Objeto obj){
		if (this.objetos==null)
			this.objetos = new ArrayList<Objeto>();
		this.objetos.add(obj);
	}
	
	public void addOferta(Oferta oferta){
		if (this.ofertas==null)
			this.ofertas = new ArrayList<Oferta>();
		this.ofertas.add(oferta);
	}
	
	public void addTrueque(Trueque t){
		if (this.trueques==null)
			this.trueques = new ArrayList<Trueque>();
		this.trueques.add(t);
	}

	
	
}

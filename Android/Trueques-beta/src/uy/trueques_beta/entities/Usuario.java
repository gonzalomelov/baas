package uy.trueques_beta.entities;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class Usuario {

	//private int id;
	private String nombre;
	private String mail;
	private String pass;
	private List<String> objetos = new ArrayList<String>();//<Objeto>();
	private List<String> ofertas = new ArrayList<String>();//<Oferta>();
	private List<String> trueques = new ArrayList<String>();//<Trueque>();
	//+++
	private int publicados; //trueques
	private int aceptados; //trueques aceptados
	private int realizados; //trueques propuestos y aceptados
	private int puntos;
	private int total;
	private boolean bloqueado;
	
	public Usuario(String nombre, String mail, String pass) {
		super();
		//this.id = id;
		this.nombre = nombre;
		this.mail = mail;
		this.pass = pass;
		//this.objetos = objetos;
		
		this.publicados =0;
		this.aceptados =0;
		this.realizados =0;
		this.setPuntos(0);
		this.total= 0;
		this.setBloqueado(false);
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
	public List<String> getObjetos() {
		return objetos;
	}
	public void setObjetos(List<String> objetos) {
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
	
	public List<String> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<String> ofertas) {
		this.ofertas = ofertas;
	}
	
	public List<String> getTrueques() {
		return trueques;
	}

	public void setTrueques(List<String> trueques) {
		this.trueques = trueques;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}
	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	public boolean isBloqueado() {
		return this.bloqueado;
	}

	public void setBloqueado(boolean bloqueado) {
		this.bloqueado = bloqueado;
	}
	
	//+++++++

	public void addObjeto(String obj){
		if (this.objetos==null)
			this.objetos = new ArrayList<String>();
		this.objetos.add(obj);
	}
	
	public void addOferta(String oferta){
		if (this.ofertas==null)
			this.ofertas = new ArrayList<String>();
		this.ofertas.add(oferta);
	}
	
	public void addTrueque(String t){
		if (this.trueques==null)
			this.trueques = new ArrayList<String>();
		this.trueques.add(t);
	}

	public void puntuar(int puntos){
		this.total++;
		this.puntos+=puntos;
	}
	public float getPuntaje(){
		if (this.total==0)
			return 0;
		else
			return (this.puntos/this.total);
	}


	public String toJson() {
		Gson gson = new Gson();
		String json = gson.toJson(this, Usuario.class);
		return json;
	}
	
	public static Usuario fromJson(String json) {
		Gson gson = new Gson();
		Usuario u = gson.fromJson(json, Usuario.class);
		return u;
	}
	
}

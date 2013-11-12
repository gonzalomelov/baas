package uy.trueques_beta.entities;

import java.util.UUID;



public class Objeto {

	private String idObjeto;
	private String nombre;
	private String descripcion;
	private float valor;
	private String duenio;
	//private BufferedImage foto;
	
	
	public Objeto(String nombre, String descripcion, float valor, String duenio) {
		this.idObjeto = UUID.randomUUID().toString();
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.duenio = duenio;
	}
	
	public String getIdObjeto() {
		return idObjeto;
	}
	public void setIdObjeto(String id) {
		this.idObjeto = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getValor() {
		return valor;
	}
	public void setValor(float valor) {
		this.valor = valor;
	}
	public String getDuenio() {
		return duenio;
	}
	public void setDuenio(String duenio) {
		this.duenio = duenio;
	}
	
	//++++++++++++++++
	
	
	
}

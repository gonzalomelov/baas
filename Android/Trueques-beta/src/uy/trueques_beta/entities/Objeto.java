package uy.trueques_beta.entities;



public class Objeto {

	private int id;
	private String nombre;
	private String descripcion;
	private float valor;
	private String duenio;
	//private BufferedImage foto;
	
	
	public Objeto(int id, String nombre, String descripcion, float valor, String duenio) {
		this.id = id;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.valor = valor;
		this.duenio = duenio;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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

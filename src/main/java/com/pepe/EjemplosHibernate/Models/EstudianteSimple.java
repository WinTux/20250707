package com.pepe.EjemplosHibernate.Models;

public class EstudianteSimple {
	private String nombre;
	private String apellido;
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public EstudianteSimple() {
		super();
		// TODO Auto-generated constructor stub
	}
	public EstudianteSimple(String nombre, String apellido) {
		super();
		this.nombre = nombre;
		this.apellido = apellido;
	}
	
}

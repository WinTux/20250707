package com.pepe.EjemplosHibernate.Models;


import java.util.Set;

public class Asignatura {
	private String Sigla;
	private String Titulo;
	private String Descripcion;
	private Integer Creditos; //no int, porque Integer puede recibir nulos (todos los tipos primitivos con Mayes admiten nulos)
	private Carrera carrerita;
	private Set<Inscripcion> Inscripciones;//Omitible al inicio
	public String getSigla() {
		return Sigla;
	}
	public void setSigla(String sigla) {
		Sigla = sigla;
	}
	public String getTitulo() {
		return Titulo;
	}
	public void setTitulo(String titulo) {
		Titulo = titulo;
	}
	public String getDescripcion() {
		return Descripcion;
	}
	public void setDescripcion(String descripcion) {
		Descripcion = descripcion;
	}
	public Integer getCreditos() {
		return Creditos;
	}
	public void setCreditos(Integer creditos) {
		Creditos = creditos;
	}
	public Carrera getCarrerita() {
		return carrerita;
	}
	public void setCarrerita(Carrera carrerita) {
		this.carrerita = carrerita;
	}
	public Set<Inscripcion> getInscripciones() {
		return Inscripciones;
	}
	public void setInscripciones(Set<Inscripcion> inscripciones) {
		Inscripciones = inscripciones;
	}
}
package com.pepe.EjemplosHibernate.Models;

import java.io.Serializable;
import java.util.Set;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
@Entity
@Table(name="Carrera", schema="pregrado")
public class Carrera implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int CarreraID;
	private String Nombre;
	private String Edificio;
	@OneToMany(fetch=FetchType.LAZY,mappedBy="carr",targetEntity=Estudiante.class)
	private Set estudiantes;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="carr",targetEntity=Docente.class)
	private Set docentes;
	
	@OneToMany(fetch=FetchType.LAZY,mappedBy="carrerita",targetEntity=Asignatura.class)
	private Set asignaturas;
	
	@Column(name="CarreraID", nullable=false)
	public int getCarreraID() {
		return CarreraID;
	}
	public void setCarreraID(int carreraID) {
		CarreraID = carreraID;
	}
	@Column(name="Nombre", nullable=false)
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		this.Nombre = nombre;
	}
	@Column(name="Edificio", nullable=true)
	public String getEdificio() {
		return Edificio;
	}
	public void setEdificio(String edificio) {
		this.Edificio = edificio;
	}
	public Set getEstudiantes() {
		return estudiantes;
	}
	public void setEstudiantes(Set<Estudiante> estudiantes) {
		this.estudiantes = estudiantes;
	}
	public Set getDocentes() {
		return docentes;
	}
	public void setDocentes(Set<Docente> docentes) {
		this.docentes = docentes;
	}
	public Set getAsignaturas() {
		return docentes;
	}
	public void setAsignaturas(Set<Asignatura> asignaturas) {
		this.asignaturas = asignaturas;
	}
}
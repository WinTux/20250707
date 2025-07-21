package com.pepe.EjemplosHibernate.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="Docente", schema="pregrado")
public class Docente {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int DocenteID;
	@Column(name="Nombre", nullable = false, length=100)
	private String Nombre;
	@Column(name="Apellido", nullable = false, length=100)
	private String Apellido;
	@Column(name="Password", nullable = false, length=250)
	private String Password;
	@Column(name="Email", nullable = false, length=250)
	private String Email;
	@Column(name="SitioWeb", nullable = false, length=250)
	private String SitioWeb;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CarreraID")
	private Carrera carr;
	public int getDocenteID() {
		return DocenteID;
	}
	public void setDocenteID(int docenteID) {
		DocenteID = docenteID;
	}
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apelliodo) {
		Apellido = apelliodo;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSitioWeb() {
		return SitioWeb;
	}
	public void setSitioWeb(String sitioWeb) {
		SitioWeb = sitioWeb;
	}
	public void ponerCarrera(Carrera c) {carr = c;}
}
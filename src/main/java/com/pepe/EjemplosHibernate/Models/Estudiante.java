package com.pepe.EjemplosHibernate.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Estudiante", schema="pregrado")
public class Estudiante implements Serializable {
	@Id
	private int Matricula;
	private String Nombre;
	private String Apellido;
	private Date FechaNacimiento;
	private String Password;
	private String Email;
	private Boolean Estado;
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="CarreraID")
	private Carrera carr; // <— sin getter
	@Column(name="Matricula", nullable = false)
	public int getMatricula() {
		return Matricula;
	}
	public void setMatricula(int matricula) {
		Matricula = matricula;
	}
	@Column(name="Nombre", nullable = false, length=100)
	public String getNombre() {
		return Nombre;
	}
	public void setNombre(String nombre) {
		Nombre = nombre;
	}
	@Column(name="Apellido", nullable = false, length=100)
	public String getApellido() {
		return Apellido;
	}
	public void setApellido(String apellido) {
		Apellido = apellido;
	}
	@Column(name="FechaNacimiento", nullable = true)
	public Date getFechaNacimiento() {
		return FechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		FechaNacimiento = fechaNacimiento;
	}
	@Column(name="Password", nullable = false)
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	@Column(name="Email", nullable = false)
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	@Column(name="Estado", nullable = false)
	public Boolean getEstado() {
		return Estado;
	}
	public void setEstado(Boolean estado) {
		Estado = estado;
	}
	
	public void ponerCarrera(Carrera c) {carr = c;}
}

package com.pepe.EjemplosHibernate.Models;

import java.math.BigDecimal;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name="Estudiante")
public class Estudiante {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private int id;
	@Column(name="nombre", nullable= false)
	private String nombre;
	private String apellido;
	private Date fechaNacimiento;
	private Boolean pregrado;
	@Column(precision = 10, scale = 2)
	private BigDecimal promedio;

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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public Boolean getPregrado() {
		return pregrado;
	}
	public void setPregrado(Boolean pregrado) {
		this.pregrado = pregrado;
	}
	public BigDecimal getPromedio() {
		return promedio;
	}
	public void setPromedio(BigDecimal promedio) {
		this.promedio = promedio;
	}
	
}

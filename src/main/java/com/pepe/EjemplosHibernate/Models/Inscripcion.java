package com.pepe.EjemplosHibernate.Models;

import java.math.BigDecimal;
import java.util.Date;

public class Inscripcion {
	private int InscripcionID;
	private Date FechaInscripcion;
	private BigDecimal Calificacion;
	private Asignatura asig;
	private Estudiante est;
	public int getInscripcionID() {
		return InscripcionID;
	}
	public void setInscripcionID(int inscripcionID) {
		InscripcionID = inscripcionID;
	}
	public Date getFechaInscripcion() {
		return FechaInscripcion;
	}
	public void setFechaInscripcion(Date fechaInscripcion) {
		FechaInscripcion = fechaInscripcion;
	}
	public BigDecimal getCalificacion() {
		return Calificacion;
	}
	public void setCalificacion(BigDecimal calificacion) {
		Calificacion = calificacion;
	}
	public Asignatura getAsig() {
		return asig;
	}
	public void setAsig(Asignatura asig) {
		this.asig = asig;
	}
	public Estudiante getEst() {
		return est;
	}
	public void setEst(Estudiante est) {
		this.est = est;
	}
}
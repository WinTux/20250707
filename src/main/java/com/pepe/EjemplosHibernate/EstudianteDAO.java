package com.pepe.EjemplosHibernate;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import com.pepe.EjemplosHibernate.Models.Estudiante;
import com.pepe.EjemplosHibernate.Models.EstudianteSimple;
import com.pepe.EjemplosHibernate.Util.HibernateUtil;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class EstudianteDAO {
	public void crearEstudiante(Estudiante est) {
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			sesion.persist(est);
			tx.commit();
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			sesion.close();
		}
	}
	public void editarEstudiante(Estudiante est) {
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			
			// Rescatar al estudiante desde DDBB
			Estudiante estDDBB = sesion.get(Estudiante.class, est.getId());
			if(estDDBB != null) {
				estDDBB.setNombre(est.getNombre());
				estDDBB.setApellido(est.getApellido());
				estDDBB.setFechaNacimiento(est.getFechaNacimiento());
				estDDBB.setPregrado(est.getPregrado());
				estDDBB.setPromedio(est.getPromedio());
				//sesion.update(estDDBB);
				sesion.persist(estDDBB);
				tx.commit();
			}
			
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			sesion.close();
		}
	}
	public void eliminarEstudiante(int id) {
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			
			// Rescatar al estudiante desde DDBB
			Estudiante estDDBB = sesion.get(Estudiante.class, id);
			if(estDDBB != null) {
				sesion.remove(estDDBB);
				tx.commit();
			}
			
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			sesion.close();
		}
	}
	
	public static ObservableList<Estudiante> obtenerEstudiantes(){
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			List<Estudiante> listaAux = sesion.createQuery("FROM Estudiante").list();
			for(Estudiante es : listaAux)
				lista.add(es);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static ObservableList<EstudianteSimple> obtenerEstudiantesPregrado(Boolean esPregrado){
		ObservableList<EstudianteSimple> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			Query consulta = sesion.createQuery("SELECT new com.pepe.EjemplosHibernate.Models.EstudianteSimple(E.nombre, E.apellido) FROM Estudiante E WHERE E.pregrado = :esPre");
			consulta.setParameter("esPre",esPregrado);
			List<EstudianteSimple> listaAux = consulta.list();
			for(EstudianteSimple es : listaAux)
				lista.add(es);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public Estudiante getEstudianteById(int id) {
		Estudiante est = new Estudiante();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			
			// Rescatar al estudiante desde DDBB
			est = sesion.get(Estudiante.class, id);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			sesion.close();
		}
		return est;
	}
}

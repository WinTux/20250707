package com.pepe.EjemplosHibernate;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.pepe.EjemplosHibernate.Models.Estudiante;
import com.pepe.EjemplosHibernate.Util.HibernateUtil;

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
			}
			
		}catch(Exception e) {
			if(tx != null)
				tx.rollback();
			e.printStackTrace();
		}finally {
			sesion.close();
		}
	}
}

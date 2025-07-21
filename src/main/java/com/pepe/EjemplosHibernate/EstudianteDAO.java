package com.pepe.EjemplosHibernate;

import java.math.BigDecimal;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.procedure.ProcedureCall;
import org.hibernate.procedure.ProcedureOutputs;
import org.hibernate.query.Query;
import org.hibernate.result.ResultSetOutput;

import com.pepe.EjemplosHibernate.Models.Estudiante;
import com.pepe.EjemplosHibernate.Models.EstudianteSimple;
import com.pepe.EjemplosHibernate.Util.HibernateUtil;

import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
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
			Estudiante estDDBB = sesion.get(Estudiante.class, est.getMatricula());
			if(estDDBB != null) {
				estDDBB.setNombre(est.getNombre());
				estDDBB.setApellido(est.getApellido());
				estDDBB.setFechaNacimiento(est.getFechaNacimiento());
				estDDBB.setEmail(est.getEmail());
				estDDBB.setEstado(est.getEstado());
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
	
	public static ObservableList<Estudiante> obtenerEstudiantesPromedio(BigDecimal prom){
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			// Utilizando una consulta nativa (SQL)
			Query consulta = sesion.createNativeQuery("SELECT * FROM estudiantes_promedio_mayor(?)", Estudiante.class);
			consulta.setParameter(1,prom);
			List<Estudiante> listaAux = consulta.getResultList();
			for(Estudiante es : listaAux)
				lista.add(es);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public static ObservableList<Estudiante> storedProcedureEstudiantesPostgreSQL(BigDecimal param){
		// Este es un ejemplo aislado que no funciona con SP SQL Server.
		
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			ProcedureCall pc = sesion.createStoredProcedureCall("estudiantes_promedio_mayor");
			pc.registerParameter("param1", BigDecimal.class, ParameterMode.IN);
			pc.setParameter("param1", param); 
			
			ProcedureOutputs po = pc.getOutputs();
			ResultSetOutput rso = (ResultSetOutput) po.getCurrent();
			List resultados = rso.getResultList();
			for(int i = 0; i< resultados.size();i++) {
				Object[] registro = (Object[]) resultados.get(i);
				// Acá armamos un objeto Estudiante y lo agregamos a la lista
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	public static ObservableList<Estudiante> storedProcedureEstudiantesJakartaPersistence(BigDecimal prom){
		// Este es un ejemplo aislado usando Jakarta (sin Hibernate)
		
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			StoredProcedureQuery spq = sesion.createStoredProcedureQuery("estudiantes_promedio_mayor", Estudiante.class);
			spq.registerStoredProcedureParameter("param1", BigDecimal.class, ParameterMode.IN);
			spq.setParameter("param1", prom);
			List<Estudiante> ests = spq.getResultList();
			for(Estudiante es : ests)
				lista.add(es);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	
	// Criteria Query
	public static ObservableList<Estudiante> estudiantesCriteriaQuery(BigDecimal prom){
		// Este es un ejemplo aislado usando Jakarta (sin Hibernate)
		
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			CriteriaQuery<Estudiante> cq = sesion.getCriteriaBuilder().createQuery(Estudiante.class);
			cq.from(Estudiante.class);// select * from estudiante;
			List<Estudiante> ests = sesion.createQuery(cq).getResultList();
			for(Estudiante es : ests)
				lista.add(es);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public static ObservableList<Estudiante> estudiantesCriteriaQuery2(int id){
		// Este es un ejemplo aislado usando Jakarta (sin Hibernate)
		
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery<Estudiante> cq = builder.createQuery(Estudiante.class);
			Root<Estudiante> raiz = cq.from(Estudiante.class);// select * from estudiante where id = 3;
			cq.select(raiz).where(builder.ge(raiz.get("id"), id));
			List<Estudiante> ests = sesion.createQuery(cq).getResultList();
			for(Estudiante es : ests)
				lista.add(es);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public static ObservableList<Estudiante> estudiantesCriteriaQuery3(int id){
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery<Estudiante> consulta = builder.createQuery(Estudiante.class);
			Root<Estudiante> raiz = consulta.from(Estudiante.class);// select * from estudiante where id = 3;
			consulta.select(raiz).orderBy(builder.desc(raiz.get("apellido")));
			List<Estudiante> ests = sesion.createQuery(consulta).getResultList();
			for(Estudiante es : ests)
				lista.add(es);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
	public static ObservableList<Estudiante> estudiantesCriteriaQuery4(int id){
		ObservableList<Estudiante> lista = FXCollections.observableArrayList();
		Session sesion = HibernateUtil
				.getSessionfactory().openSession();
		Transaction tx = null;
		try {
			tx = sesion.beginTransaction();
			CriteriaBuilder builder = sesion.getCriteriaBuilder();
			CriteriaQuery<Estudiante> consulta = builder.createQuery(Estudiante.class);
			Root<Estudiante> raiz = consulta.from(Estudiante.class);// select top 3 * from estudiante order by apellido;
			consulta.select(raiz).orderBy(builder.desc(raiz.get("apellido")));
			List<Estudiante> ests = sesion.createQuery(consulta).setMaxResults(3).getResultList();
			for(Estudiante es : ests)
				lista.add(es);
		}catch(Exception e) {
			e.printStackTrace();
		}
		return lista;
	}
}

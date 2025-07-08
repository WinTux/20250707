package com.pepe.EjemplosHibernate.Util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
	private static final SessionFactory sessionFactory;
	static {
		try {
			sessionFactory = new Configuration()
					.configure() // <-- acá lee el archivo xml
					.buildSessionFactory();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}

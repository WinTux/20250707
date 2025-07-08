module com.pepe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires jakarta.persistence;
	requires org.hibernate.orm.core;
    opens com.pepe.EjemplosHibernate to javafx.fxml;
    exports com.pepe.EjemplosHibernate;
}
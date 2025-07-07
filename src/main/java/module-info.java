module com.pepe {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
	requires jakarta.persistence;
    opens com.pepe.EjemplosHibernate to javafx.fxml;
    exports com.pepe.EjemplosHibernate;
}
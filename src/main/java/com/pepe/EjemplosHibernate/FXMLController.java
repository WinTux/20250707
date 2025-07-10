package com.pepe.EjemplosHibernate;
/*
Put header here


 */

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

import com.pepe.EjemplosHibernate.Models.Estudiante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class FXMLController implements Initializable {
    
    @FXML
    private Label lblOut;
    
    @FXML
    private void btnClickAction(ActionEvent event) {
    	EstudianteDAO estDAO = new EstudianteDAO();
    	Estudiante est = new Estudiante();
    	est.setNombre("Sara");
    	est.setApellido("Rocha");
    	est.setFechaNacimiento(new Date());
    	est.setPregrado(true);
    	estDAO.crearEstudiante(est);
    	
        lblOut.setText("Registrado");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}

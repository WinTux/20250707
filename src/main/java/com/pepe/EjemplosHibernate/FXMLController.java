package com.pepe.EjemplosHibernate;
/*
Put header here


 */

import java.math.BigDecimal;
import java.net.URL;
import java.time.ZoneId;
import java.util.Date;
import java.util.ResourceBundle;

import com.pepe.EjemplosHibernate.Models.Estudiante;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;

public class FXMLController implements Initializable {
    
    @FXML
    private Label lblOut;
    @FXML
    private TextField txtId;
    @FXML
    private TableView<Estudiante> tblEstudiantes;
    @FXML
    private TextField txtIdMod;

    @FXML
    private TextField txtNomMod;

    @FXML
    private TextField txtProdMod;
    @FXML
    private TextField txtApeMod;
    @FXML
    private DatePicker pickFechaNacMod;
    @FXML
    private CheckBox chkPregradoMod;
    
    @FXML
    private void btnClickAction(ActionEvent event) {
    	EstudianteDAO estDAO = new EstudianteDAO();
    	Estudiante est = new Estudiante();
    	est.setNombre("Juan");
    	est.setApellido("Fernandez");
    	est.setFechaNacimiento(new Date());
    	est.setPregrado(false);
    	estDAO.crearEstudiante(est);
    	
        lblOut.setText("Registrado");
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    } 
    
    @FXML
    void OnClicEliminar(ActionEvent event) {
    	EstudianteDAO estDAO = new EstudianteDAO();
    	estDAO.eliminarEstudiante(Integer.parseInt(txtId.getText()));
    	lblOut.setText("Eliminado :D");
    }
    
    @FXML
    void OnCargarClic(ActionEvent event) {
    	TableColumn<Estudiante,Integer> colId = new TableColumn<>("ID");
    	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	TableColumn<Estudiante,String> colNombre = new TableColumn<>("NOMBRE");
    	colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
    	TableColumn<Estudiante,String> colApellido = new TableColumn<>("APELLIDO");
    	colApellido.setCellValueFactory(new PropertyValueFactory<>("apellido"));
    	TableColumn<Estudiante,Date> colFechaNacimiento = new TableColumn<>("FECHA DE NACIMIENTO");
    	colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
    	TableColumn<Estudiante,Boolean> colPregrado = new TableColumn<>("ES PREGRADO");
    	colPregrado.setCellValueFactory(new PropertyValueFactory<>("pregrado"));
    	TableColumn<Estudiante,BigDecimal> colPromedio = new TableColumn<>("PROMEDIO");
    	colPromedio.setCellValueFactory(new PropertyValueFactory<>("promedio"));
    	tblEstudiantes.getColumns().addAll(colId,colNombre,colApellido,colFechaNacimiento,colPregrado,colPromedio);
    	
        ObservableList<Estudiante> datos = new EstudianteDAO().obtenerEstudiantes();
        tblEstudiantes.setItems(datos);
        lblOut.setText("Lista cargada");
    }
    @FXML
    void OnBuscarIdClic(ActionEvent event) {
    	Estudiante es = new EstudianteDAO().getEstudianteById(Integer.parseInt(txtIdMod.getText()));
    	if(es.getId() > 0) {
    		// Sí es válido
    		txtNomMod.setText(es.getNombre());
    		txtApeMod.setText(es.getApellido());
    		pickFechaNacMod.setValue(es.getFechaNacimiento().toInstant()
    			      .atZone(ZoneId.systemDefault())
    			      .toLocalDate());
    		chkPregradoMod.setSelected(es.getPregrado());
    		txtProdMod.setText(es.getPromedio() != null? es.getPromedio().toString() : "");
    	}else {
    		// No es válido
    		lblOut.setText("Registro no encontrado");
    	}
    }
    @FXML
    void OnModificarClic(ActionEvent event) {
    	Estudiante estNuevo = new Estudiante();
    	estNuevo.setId(Integer.parseInt(txtIdMod.getText()));
    	estNuevo.setNombre(txtNomMod.getText());
    	estNuevo.setApellido(txtApeMod.getText());
    	estNuevo.setFechaNacimiento(java.util.Date.from(pickFechaNacMod.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant()));
    	estNuevo.setPregrado(chkPregradoMod.isSelected());
    	estNuevo.setPromedio(BigDecimal.valueOf(Double.parseDouble(txtProdMod.getText())));
    	new EstudianteDAO().editarEstudiante(estNuevo);
    	lblOut.setText("Estudiante modificado ;D");
    }
}

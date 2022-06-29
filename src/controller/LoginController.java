package controller;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coneccion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private Button idButton;

    @FXML
    private Label idLabel;

    @FXML
    private TextField idName;

    @FXML
    private PasswordField idPassword;
    
    
    Connection con =null;
    PreparedStatement preparedStatamend = null;
    ResultSet resulSet =null;

    @FXML
    void ingresarBtn(ActionEvent event) throws SQLException {
    	
    	String login = idName.getText();
    	
        String clave = idPassword.getText();
        Conexion conect = new Conexion();
        conect.conectar();
        if(conect.isConectado()){
            String query = "SELECT id from users where usuario = '"+login+"' AND contra = '"+clave+"'";
            String query2 = "SELECT id from admins where usuario = '"+login+"' AND contra = '"+clave+"'";
            try (Statement stm = conect.getCon().createStatement()){
            	Statement stme = conect.getCon().createStatement();
            	System.out.println("entrotry");
                ResultSet rst = stm.executeQuery(query);
                ResultSet rste = stme.executeQuery(query2);
                if(rst.next()){
                	System.out.println("entroif");
                    Stage stage = (Stage) idButton.getScene().getWindow();
                    stage.close();
                    //Crear la nueva ventana
                    System.out.println("entro");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistasInstructor/HomeInstructor.fxml"));
                    System.out.println("oe");
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage = new Stage();
                    //stage.close();
                    stage.setTitle("Home");
                    stage.setScene(scene);
                    
                    stage.show();
                    conect.desconectar();
                       
                    
                }else if (rste.next()) {
                    Stage stage = (Stage) idButton.getScene().getWindow();
                    stage.close();
                    //Crear la nueva ventana
                    System.out.println("entro");
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/home.fxml"));
                    System.out.println("oe");
                    Parent root = loader.load();

                    Scene scene = new Scene(root);
                    stage = new Stage();
                    //stage.close();
                    stage.setTitle("Home");
                    stage.setScene(scene);
                    
                    stage.show();
                    conect.desconectar();
					
				}else {
                	idLabel.setText("Usuario o Clave no validos");
				}
                
            } catch (Exception e) {
                System.out.println("ERROR: Aborting...");
              e.printStackTrace();
      
        }
        }

    }

}
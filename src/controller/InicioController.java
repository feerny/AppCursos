package controller;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnAprendiz;

    @FXML
    private Button btnInstructor;

    @FXML
    void clickAdmin(ActionEvent event) throws IOException {
    	
    	
    	 Stage stage = (Stage) btnAdmin.getScene().getWindow();
         stage.close();
         //Crear la nueva ventana
         System.out.println("entro");
         FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/loginUsers.fxml"));
         System.out.println("oe");
         Parent root = loader.load();

         Scene scene = new Scene(root);
         stage = new Stage();
         //stage.close();
         stage.setTitle("Home");
         stage.setScene(scene);
         
         stage.show();

    }

    @FXML
    void clickAprendiz(ActionEvent event) throws IOException {
    	
    	
   	 	Stage stage = (Stage) btnAdmin.getScene().getWindow();
        stage.close();
        //Crear la nueva ventana
        System.out.println("entro");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistasAprendices/HomeAprendiz.fxml"));
        System.out.println("oe");
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage = new Stage();
        //stage.close();
        stage.setTitle("Home");
        stage.setScene(scene);
        
        stage.show();

    }

    @FXML
    void clickInstructor(ActionEvent event) throws IOException {
    	
    	
   	 Stage stage = (Stage) btnInstructor.getScene().getWindow();
     stage.close();
     //Crear la nueva ventana
     System.out.println("entro");
     FXMLLoader loader = new FXMLLoader(getClass().getResource("/vistas/loginUsers.fxml"));
     System.out.println("oe");
     Parent root = loader.load();

     Scene scene = new Scene(root);
     stage = new Stage();
     //stage.close();
     stage.setTitle("Home");
     stage.setScene(scene);
     
     stage.show();

    }

}

package instructorController;


import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class HomeInstruController {

    @FXML
    private Button AGaprendices;

    @FXML
    private Button idLaprendices;

    @FXML
    private Button idLcursos;

    @FXML
    void clickAprendices(ActionEvent event) throws IOException {
    	Parent root = (new FXMLLoader(getClass().getResource("/vistas/aprendiz.fxml"))).load();
    	Scene scene = new Scene(root);
    	Stage teatro = new Stage();
    	teatro.setTitle("Home");
    	teatro.setScene(scene);
        
    	teatro.show();

    }

    @FXML
    void clickLaprendices(ActionEvent event) throws IOException {
    	
    	Parent root = (new FXMLLoader(getClass().getResource("/vistas/listaAprendiz.fxml"))).load();
    	Scene scene = new Scene(root);
    	Stage teatro = new Stage();
    	teatro.setTitle("Home");
    	teatro.setScene(scene);
        
    	teatro.show();

    }

    @FXML
    void clickLcursos(ActionEvent event) throws IOException {
    	Parent root = (new FXMLLoader(getClass().getResource("/vistas/listaCursos.fxml"))).load();
    	Scene scene = new Scene(root);
    	Stage teatro = new Stage();
    	teatro.setTitle("Home");
    	teatro.setScene(scene);
        
    	teatro.show();

    }

}

package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coneccion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class CursosController {

    @FXML
    private ImageView BuscarImg;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLimpiar;

    @FXML
    private ComboBox<String> combCursi;

    @FXML
    private TextArea txtAviso;

    @FXML
    private TextField txtDocumento;
    
	String dato,query;
    int codigoProducto;
    //consulta de cadena
    Conexion con = new Conexion();
    
    @FXML
    void clickBuscar(MouseEvent event) throws SQLException {
    	
    	String iden = txtDocumento.getText();
    	
        if (iden==null || iden.isEmpty()) {
        	txtAviso.setText("Digite un curso valido para poder buscar");
        }else {
        	
        	 con.conectar();
        	 
             try(Statement stm = con.getCon().createStatement()){
            	 
            	
            	 
                 String ident = txtDocumento.getText();
                 ResultSet rta = stm.executeQuery("select * from cursos where nombre = '"+ident+"'");
                 
                 
                 if(rta.next()){
                	 codigoProducto = rta.getInt("codigo");
                	int hora = rta.getInt("horario");
                	 
                	 String query2 = "SELECT idHorario,dia,hora from horario where idHorario = '"+hora+"'";
                	 ResultSet rte = stm.executeQuery(query2);
                	 if(rte.next()){
                    	 dato = String.format("%d %s %s",rte.getInt("idHorario"), rte.getString("dia"), rte.getString("hora"));
                         
                         combCursi.setValue(dato);
                	 }
                	 
                 	
                 	
                 	txtAviso.setText("curso existente");
                 	
     
                    btnAgregar.setText("actualizar");
               
       
                    btnAgregar.setDisable(false);
                    
                   
                    
                 }
                 else {
                	 txtAviso.setText("curso no existe");
                     btnAgregar.setText("Agregar");
                 }
             }
        	
        }

    }

    @FXML
    void clickAgregar(ActionEvent event) throws SQLException {
    	
    	
    	String iden = txtDocumento.getText();
    	String horario = combCursi.getValue();
    	
        if (iden==null || iden.isEmpty()) {
        	txtAviso.setText("Digite un curso valido");
        }
        if (horario==null || horario.isEmpty()) {
        	txtAviso.setText("elija un horario valido");
        } else{
        	
            String[] tmp = horario.split(" ");
            int clie = Integer.parseInt(tmp[0]);
    
            
        		String acc = btnAgregar.getText();
        		String query1;
        	   if ("Agregar".equalsIgnoreCase(acc)) {
        		    query1 = "insert into cursos (nombre,horario)values ('"+iden+"','"+clie+"')";
               } else {
            	   query1 = "UPDATE cursos set nombre = '"+iden+"',horario = '"+clie+"' where codigo="+codigoProducto;
               }
           
            con.conectar();
            try (Statement stm = con.getCon().createStatement()){
                int rest = stm.executeUpdate(query1);
                if(rest != 0){
                	txtAviso.clear();
                	txtAviso.setText("Datos Registrados con exito");
                	
                	txtDocumento.clear();
                	btnAgregar.setText("Agregar");
                	combCursi.setValue("Horario");
                }
                else{
                	txtAviso.setText("Error al grabar los datos por favor verifique");
                }
            } catch (Exception e) {
               // e.printStackTrace();
                txtAviso.setText("curso existente");
            }
            con.desconectar();
        }

    }

    @FXML
    void clickLimpiar(ActionEvent event) {
    	txtAviso.clear();
    	txtDocumento.clear();
    	combCursi.setValue("Horario");
    	btnAgregar.setText("Agregar");
    	

    }
    
    
    
    
    
    @FXML
    void initialize() throws IOException, SQLException{
        //Declaro variable
        
        ResultSet rst;
        //Conectarme a la base de datos        
        con.conectar();
        System.out.println("Voy bien antes del combo");
        //Preparar para recuperar datos de la base de datos. Tabla clientes
        query = "SELECT idHorario,dia,hora from horario order by hora,dia";
        try (Statement stm = con.getCon().createStatement()){ //Preparo el area para las consultas
            System.out.println("Voy bien de la consulta combo");
            rst = stm.executeQuery(query);
            System.out.println("Voy bien dentro combo");
            while (rst.next()) {
            	System.out.println("Voy bien en combo");
            	 dato = String.format("%d %s %s",rst.getInt("idHorario"), rst.getString("dia"), rst.getString("hora"));
                
                combCursi.getItems().add(dato);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }

}


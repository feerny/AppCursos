package controller;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coneccion.Conexion;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ListaAprenController {

    @FXML
    private Button idActivos;
    
    @FXML
    private TextArea cursostxt;

    @FXML
    private Button idInactivos;

    @FXML
    private TextArea text;
    
	String dato,query2,query;
    int codigoProducto;
    int tipo,curso;
    //consulta de cadena
    Conexion con = new Conexion();
    String valiText;

    @FXML
    void clickActivos(ActionEvent event) throws SQLException {
    	text.clear();
    	
      	 con.conectar();
       	 
         try(Statement stm = con.getCon().createStatement()){
        	 
             ResultSet rta = stm.executeQuery("select * from personas where tipoPersona=2");



             while(rta.next()){
            codigoProducto = rta.getInt("id");
         	String estado = rta.getString("estado");
         	tipo = rta.getInt("tipoPersona");

 
         	
         	if (estado.equalsIgnoreCase("A")) {

         		


                     		System.out.println("entro");
                     		
                     		
                            dato = String.format("%d %s %s %s %d",rta.getInt("identificación"), rta.getString("nombre"), rta.getString("apellido")," ||codigo curso: ",rta.getInt("cursoAsignado"));
                                     
                              		
                            String an = text.getText();
               
                            String texto = an+" "+"\n "+dato;
                               		
                               		
                            text.setText(texto);
                            valiText=text.getText();

                      }
         				if(valiText==null || valiText.isEmpty()) {
         				text.setText("no hay aprendices activos");
         				}
				} 
        	 

         }

    }
    
    String valiText2;

    @FXML
    void clickInactivos(ActionEvent event) throws SQLException {
    	text.clear();
    	
     	 con.conectar();
      	 
        try(Statement stm = con.getCon().createStatement()){
       	 
            ResultSet rta = stm.executeQuery("select * from personas where tipoPersona=2");



            while(rta.next()){
           codigoProducto = rta.getInt("id");
        	String estado = rta.getString("estado");
        	tipo = rta.getInt("tipoPersona");
        	

        	
        	if (estado.equalsIgnoreCase("I")) {

                    		System.out.println("entro");
                    		
                    		
                           dato = String.format("%d %s %s %s %d",rta.getInt("identificación"), rta.getString("nombre"), rta.getString("apellido")," ||codigo curso: ",rta.getInt("cursoAsignado"));
                                    
                             		
                           String an = text.getText();
              
                           String texto = an+" "+"\n "+dato;
                              		
                              		
                           text.setText(texto);
                           
                           valiText2=text.getText();

                     }  if(valiText2==null || valiText2.isEmpty()) {
          				text.setText("no hay aprendices activos");
          				}
				} 
       	 

        }

    }
    
    @FXML
    void initialize() throws IOException, SQLException{
        //Declaro variable
        
        ResultSet rst;
        //Conectarme a la base de datos        
        con.conectar();
        System.out.println("Voy bien antes del combo");
        //Preparar para recuperar datos de la base de datos. Tabla clientes
        query = "SELECT codigo,nombre from cursos";
        try (Statement stm = con.getCon().createStatement()){ //Preparo el area para las consultas
            System.out.println("Voy bien de la consulta combo");
            rst = stm.executeQuery(query);
            System.out.println("Voy bien dentro combo");
            while (rst.next()) {
            	System.out.println("Voy bien en combo");
            	 dato = String.format("%d %s",rst.getInt("codigo"), rst.getString("nombre"));
            	 
                 String an = cursostxt.getText();
                 
                 String texto = an+" "+"\n "+dato;
                
            	 cursostxt.setText(texto);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }

}

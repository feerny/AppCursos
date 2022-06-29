package controller;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import coneccion.Conexion;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ListaCursoController {

    @FXML
    private ImageView BuscarImg;

    @FXML
    private TextArea textInstru;

    @FXML
    private TextArea txtAprendiz;
    
    @FXML
    private TextArea txtLcursos;

    @FXML
    private TextField txtCurso;
    
	String dato,query;
    int codigoProducto;
    //consulta de cadena
    Conexion con = new Conexion();

    @FXML
    void clickBuscar(MouseEvent event) throws SQLException {
    	txtAprendiz.clear();
    	textInstru.clear();
    	
    	String iden = txtCurso.getText();


    	
        if (iden==null || iden.isEmpty()) {
        	txtAprendiz.setText("Digite un curso valido para poder buscar");
        }else {
        	
        	 con.conectar();
        	 
             try(Statement stm = con.getCon().createStatement()){
            	 
            	
            	 
                 String ident = txtCurso.getText();
                 ResultSet rta = stm.executeQuery("select * from cursos where nombre = '"+ident+"'");
                 
                 
                 if(rta.next()){
                	 codigoProducto = rta.getInt("codigo");

                	 int cod = codigoProducto;

                     ResultSet rte = stm.executeQuery("select * from personas where cursoAsignado='"+cod+"'and tipoPersona=2");
                     
                     while (rte.next()) {
                         dato = String.format("%d %s %s",rte.getInt("identificación"), rte.getString("nombre"), rte.getString("apellido"));
                         System.out.println("oeeeeeeeeee");
                   		
                         String an = txtAprendiz.getText();
    
                         String texto = an+" "+"\n "+dato;
                         txtAprendiz.setText(texto);
					}
                 	String txtA = txtAprendiz.getText();
                     
                     if (txtA==null || txtA.isEmpty()) {
                    	 textInstru.setText("sin aprendices asignados");
					}
                     
                     ResultSet rti = stm.executeQuery("select * from personas where cursoAsignado='"+cod+"'and tipoPersona=1");
                     
                     while (rti.next()) {
                         dato = String.format("%d %s %s",rti.getInt("identificación"), rti.getString("nombre"), rti.getString("apellido"));
                         System.out.println("oeeeeeeeeee");
                   		
                         String an = textInstru.getText();
    
                         String texto = an+" "+"\n "+dato;
                         textInstru.setText(texto);
					}
                 	String txtI = textInstru.getText();
                     
                     if (txtI==null || txtI.isEmpty()) {
                    	 textInstru.setText("sin instructor asignado");
					}
                     

                    
                 }
                 
                 else {
                	 txtAprendiz.setText("curso no existe");

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
            	 
                 String an = txtLcursos.getText();
                 
                 String texto = an+" "+"\n "+dato;
                
                 txtLcursos.setText(texto);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }

}

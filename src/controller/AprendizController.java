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

public class AprendizController {

    @FXML
    private ImageView BuscarImg;

    @FXML
    private Button btnActualizar;

    @FXML
    private Button btnAgregar;

    @FXML
    private Button btnEliminar;

    @FXML
    private Button btnHome;

    @FXML
    private Button btnLimpiar;

    @FXML
    private ComboBox<String> combCursi;

    @FXML
    private TextField txtApellido;

    @FXML
    private TextArea txtAviso;

    @FXML
    private TextField txtDocumento;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtNombre;
    
	String dato,query;
    int codigoProducto;
    int tipo;
    //consulta de cadena
    Conexion con = new Conexion();
    
    @FXML
    void clickImg(MouseEvent event) throws SQLException {
    	
	 		txtNombre.clear();
	 		txtApellido.clear();
	 		txtEdad.clear();
	 		combCursi.setValue("curso");

    	String iden = txtDocumento.getText();
    	
        if (iden==null || iden.isEmpty()) {
        	txtAviso.setText("Digite un documento valido para poder buscar");
        }else {
        	
       	 con.conectar();
       	 
            try(Statement stm = con.getCon().createStatement()){
           	 
           	
           	 
                int ident = Integer.parseInt(iden);
                ResultSet rta = stm.executeQuery("select * from personas where identificación = '"+ident+"'");
                
                
                if(rta.next()){
               	 codigoProducto = rta.getInt("id");
               	String nombre = rta.getString("nombre");
            	String apellido = rta.getString("apellido");
            	String edad =  ""+rta.getInt("edad");
            	String estado = rta.getString("estado");
            	tipo = rta.getInt("tipoPersona");
            	
            	int curso = rta.getInt("cursoAsignado");
            	
            	if (tipo==2) {
                 	  String query2 = "SELECT codigo,nombre from cursos where codigo = '"+curso+"'";
                    	 ResultSet rte = stm.executeQuery(query2);
                    	 if(rte.next()){
                    		dato = String.format("%d %s",rte.getInt("codigo"), rte.getString("nombre"));
                             
                             combCursi.setValue(dato);
                    		 
                    	 }
                     	
                        	 

                    	 		txtNombre.setText(nombre);
                    	 		txtApellido.setText(apellido);
                    	 		txtEdad.setText(edad);
                         	
                         	
                         	txtAviso.setText("persona existente");
                         	
             
                         	 if (estado.equalsIgnoreCase("A")) {
                         		 btnEliminar.setText("Borrar");
                              } else {
                             	 btnEliminar.setText("Recuperar");
                              }
                       
               
                            btnAgregar.setDisable(false);
                            
                           
                            
                         }
                         else {
                        	 
                        	 txtAviso.setText("la persona no es un aprendiz");
                         }
				}  else {
					txtAviso.setText("persona no existe");
               	 
                }
           	 

            }
       	
       }
    	
    	
    }

    @FXML
    void clickActualizar(ActionEvent event) throws SQLException {
    	

    	
    	  String ide = txtDocumento.getText();
          String nom = txtNombre.getText();
          String ape = txtApellido.getText();
          String edad = txtEdad.getText();
          String curso = combCursi.getValue();
          
          
          
          if(ide==null || ide.isEmpty())
        	  txtAviso.setText("Debe ingresar una Identificacion valida");
          else if(nom==null || nom.isEmpty())
        	  txtAviso.setText("Debe ingresar un Nombre valido");
          else if(ape==null || ape.isEmpty())
        	  txtAviso.setText("Debe ingresar un Apellido valido");
          else if(edad==null || edad.isEmpty())
        	  txtAviso.setText("Debe ingresar una edad valida");
          else if(curso==null || curso.isEmpty())
        	  txtAviso.setText("Debe ingresar un curso valido");
          else{
          	
              String[] tmp = curso.split(" ");
              int clie = Integer.parseInt(tmp[0]);
              
              String query1 = "UPDATE personas set identificación = '"+ide+"', nombre = '"+nom+"' , apellido = '"+ape+"', edad = '"+Integer.parseInt(edad)+"',cursoAsignado = '"+clie+"',tipoPersona = '"+2+"' where identificación = "+ide;
              
              con.conectar();
              try (Statement stm = con.getCon().createStatement()){
                  int rest = stm.executeUpdate(query1);
                  if(rest != 0){
                  	txtAviso.clear();
                  	txtAviso.setText("Datos Registrados con exito");
                  	
                  	
              	  txtDocumento.clear();
                  txtNombre.clear();
                  txtApellido.clear();
                  txtEdad.clear();
              	  combCursi.setValue("Horario");
                  	
                  }
                  else{
                  	txtAviso.setText("Error al grabar los datos por favor verifique");
                  }
              } catch (Exception e) {
                  e.printStackTrace();
              }
              con.desconectar();
          }

    }

    @FXML
    void clickAgregar(ActionEvent event) throws SQLException {
    	
  	  String ide = txtDocumento.getText();
      String nom = txtNombre.getText();
      String ape = txtApellido.getText();
      String edad = txtEdad.getText();
      String curso = combCursi.getValue();
      
      
      
      if(ide==null || ide.isEmpty())
    	  txtAviso.setText("Debe ingresar una Identificacion valida");
      else if(nom==null || nom.isEmpty())
    	  txtAviso.setText("Debe ingresar un Nombre valido");
      else if(ape==null || ape.isEmpty())
    	  txtAviso.setText("Debe ingresar un Apellido valido");
      else if(edad==null || edad.isEmpty())
    	  txtAviso.setText("Debe ingresar una edad valida");
      else if(curso==null || curso.isEmpty())
    	  txtAviso.setText("Debe ingresar un curso valido");
      else{
      	
    	if (tipo==2) {
    	       String[] tmp = curso.split(" ");
    	          int clie = Integer.parseInt(tmp[0]);
    	          
    	          String query1 = "INSERT INTO personas (identificación, nombre, apellido, edad,email, cursoAsignado, tipoPersona, estado) VALUES ('"+Integer.parseInt(ide)+"', '"+nom+"', '"+ape+"', '"+Integer.parseInt(edad)+"','', '"+clie+"', '"+2+"', 'A')";
    	          
    	          con.conectar();
    	          try (Statement stm = con.getCon().createStatement()){
    	              int rest = stm.executeUpdate(query1);
    	              if(rest != 0){
    	              	txtAviso.clear();
    	              	txtAviso.setText("Datos Registrados con exito");
    	              	
    	              	
    	            	  txtDocumento.clear();
    	                  txtNombre.clear();
    	                  txtApellido.clear();
    	                  txtEdad.clear();
    	              	combCursi.setValue("Horario");
    	              }
    	              else{
    	              	txtAviso.setText("Error al grabar los datos por favor verifique");
    	              }
    	          } catch (Exception e) {
    	              //e.printStackTrace();
    	              txtAviso.setText("documento registrado");
    	          }
    	          con.desconectar();
		} else {
          	 txtAviso.setText("no se puede crear, verifique si no es un instructor");
           }  
    	  
   
      }

    }

    @FXML
    void clickEliminar(ActionEvent event) throws SQLException {
    	
        //Recuperar el texto del boton
        //Preparar la consulta a utilizar (Borrar --> estado=I, recuperar--> estado =A)
        //Ejecuta la consulta
        String acc = btnEliminar.getText();
        if ("Borrar".equalsIgnoreCase(acc)) {
            query = "UPDATE personas set estado = 'I' where id="+codigoProducto;
        } else {
            query = "UPDATE personas set estado = 'A' where id="+codigoProducto;
        }
        con.conectar();
        try(Statement stm = con.getCon().createStatement()){
        //Ejecuta comando de accion
        int res = stm.executeUpdate(query);
        if(res!=0){
        	txtAviso.setText("Registro Recuperado/Borrado con exito");
        }else
        	txtAviso.setText("Error al recuperar/Borrar registro");
    	  txtDocumento.clear();
          txtNombre.clear();
          txtApellido.clear();
          txtEdad.clear();
      	  combCursi.setValue("Horario");
        }
        con.desconectar();

    }



    @FXML
    void clickLimpiar(ActionEvent event) {
    	
  	  txtDocumento.clear();
      txtNombre.clear();
      txtApellido.clear();
      txtEdad.clear();
  	  combCursi.setValue("Horario");

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
                
                combCursi.getItems().add(dato);
                
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        


    }

}


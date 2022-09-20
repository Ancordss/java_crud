/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import javax.swing.JOptionPane;


/**
 *
 * @author james
 */
public class Estudiante extends Persona {
    private int id;
    private String carnet, genero, email;
    Conexion cn;
    
    public Estudiante(){}

    public Estudiante(int id, String carnet,String nombres, String apellidos, String direccion, String telefono, String genero, String email,  String fn) {
        super(nombres, apellidos, direccion, telefono, fn);
        this.id = id;
        this.carnet = carnet;
        this.genero = genero;
        this.email = email;
    }
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCarnet() {
        return carnet;
    }

    public void setCarnet(String carnet) {
        this.carnet = carnet;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.Conectar();
            String query;
            query = "SELECT id_estudiantes,carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento FROM db_empresa_j.estudiantes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]= {"id_estudiantes","nit","nombres","apellidos","direccion","telefono","genero","email","fecha_nacimiento"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[9];
            while(consulta.next()){
                datos[0]=consulta.getString("id_estudiantes");
                datos[1]=consulta.getString("carnet");
                datos[2]=consulta.getString("nombres");
                datos[3]=consulta.getString("apellidos");
                datos[4]=consulta.getString("direccion");
                datos[5]=consulta.getString("telefono");
                datos[6]=consulta.getString("genero");
                datos[7]=consulta.getString("email");
                datos[8]=consulta.getString("fecha_nacimiento");
                tabla.addRow(datos);
            }
            cn.Cerrar();
        }catch(SQLException ex){
            System.out.println("Error" + ex.getMessage());
        }
        return tabla;
        
    }
    
    
    @Override
    public void agregar(){
        try{
            cn = new Conexion();
            PreparedStatement parametro;
            cn.Conectar();
            String query;
            query = "INSERT INTO estudiantes(carnet,nombres,apellidos,direccion,telefono,genero,email,fecha_nacimiento) VALUES(?,?,?,?,?,?,?,?);";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCarnet());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getGenero());
            parametro.setString(7,getEmail());
            parametro.setString(8,getFn());
            int execute = parametro.executeUpdate();
            JOptionPane.showMessageDialog(null, Integer.toString(execute) + "registros ingresador","Message",JOptionPane.INFORMATION_MESSAGE);
            cn.Cerrar();
            
            
        
        
        
        }catch(Exception ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        /*
        System.out.println("NIt: "+ this.getNit());
        System.out.println("Nombres: "+ this.getNombres());
        System.out.println("apellidos: "+ this.getApellidos());
        System.out.println("direccion: "+ this.getDireccion());
        System.out.println("telefono: "+ this.getTelefono());
        System.out.println("fecha de nacimiento : "+ this.getFn());*/
        }
    @Override
    public void modificar(){
    
            try{
            cn = new Conexion();
            PreparedStatement parametro;
            cn.Conectar();
            String query;
            query = "UPDATE estudiantes set carnet=?,nombres=?,apellidos=?,direccion=?,telefono=?,genero=?,email=?,fecha_nacimiento=? WHERE id_estudiantes=?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setString(1,getCarnet());
            parametro.setString(2,getNombres());
            parametro.setString(3,getApellidos());
            parametro.setString(4,getDireccion());
            parametro.setString(5,getTelefono());
            parametro.setString(6,getGenero());
            parametro.setString(7,getEmail());
            parametro.setString(8,getFn());
            parametro.setInt(9, getId());
            int execute = parametro.executeUpdate();
            JOptionPane.showMessageDialog(null, Integer.toString(execute) + "registros modificado","Message",JOptionPane.INFORMATION_MESSAGE);
            cn.Cerrar();
            
            
        
        
        
        }catch(Exception ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        /*
        System.out.println("NIt: "+ this.getNit());
        System.out.println("Nombres: "+ this.getNombres());
        System.out.println("apellidos: "+ this.getApellidos());
        System.out.println("direccion: "+ this.getDireccion());
        System.out.println("telefono: "+ this.getTelefono());
        System.out.println("fecha de nacimiento : "+ this.getFn());*/
        }
    
    
    
    @Override
       public void eliminar(){
    
            try{
            cn = new Conexion();
            PreparedStatement parametro;
            cn.Conectar();
            String query;
            query = "DELETE FROM estudiantes WHERE id_estudiantes=?;";
            parametro = (PreparedStatement) cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            int execute = parametro.executeUpdate();
            JOptionPane.showMessageDialog(null, Integer.toString(execute) + "registro eliminados","Message",JOptionPane.INFORMATION_MESSAGE);
            cn.Cerrar();
            
            
        
        
        
        }catch(Exception ex){
            System.out.println("Error" + ex.getMessage());
        }
        
        /*
        System.out.println("NIt: "+ this.getNit());
        System.out.println("Nombres: "+ this.getNombres());
        System.out.println("apellidos: "+ this.getApellidos());
        System.out.println("direccion: "+ this.getDireccion());
        System.out.println("telefono: "+ this.getTelefono());
        System.out.println("fecha de nacimiento : "+ this.getFn());*/
        }

 
}

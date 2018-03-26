/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package telepizza;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alumno
 */
public class BBDD {

    private String url;
    private String usuario;
    private String contraseña;
    private Connection con;

    public BBDD() {
        this.url = "jdbc:mysql://localhost:3307/telepizza";
        this.usuario = "root";
        this.contraseña = "usbw";
    }

    public BBDD(String url, String usuario, String contraseña) {
        this.url = url;
        this.usuario = usuario;
        this.contraseña = contraseña;
    }

    public void conectar() {
        try {
            con = (Connection) DriverManager.getConnection(url, usuario, contraseña);
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void desconectar() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Cliente buscarClienteTelefono(String telefono) {
        Statement st;
        Cliente nuevoC = null;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from cliente where Telefono='" + telefono + "'");
            if (rs.next())
                nuevoC = new Cliente(rs.getString(1), rs.getString(2), rs.getString(3));
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevoC;
    }
    public void insertarClienteTelefono(Cliente nuevoClietne) {
        Statement st;
        try {
            conectar();
            st = con.createStatement();
            int rs = st.executeUpdate("insert into cliente  values('"+nuevoClietne.getNombre()+"','"+nuevoClietne.getTelefono()+"','"+nuevoClietne.getDireccion()+"')");
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Cliente ya existe");;
        }
        
    }
    public void insertarPedido(String Telefono,String vPedido,double factura) {
         Statement st;
        try {
            conectar();
            st = con.createStatement();
            int rs = st.executeUpdate("insert into pedido values('"+Telefono+"','"+vPedido+"','"+factura+"')");
            desconectar();
        } catch (SQLException ex) {
            System.out.println("Cliente ya existe");;
        }
    }
/**
 * Ver los datos de una columna dada una tala
 * @param columna el nombre de la columna
 * @param tabla el nombre de la tabla
 * @return 
 */
    public ArrayList<String> verColumnaTabla(String columna, String tabla){
        Statement st;
        ArrayList<String> vString = new ArrayList<>();
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select "+columna+" from "+tabla+";");
            while (rs.next()) {
                vString.add(rs.getString(1));
            }
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vString;
    }
    
    public Pizzas buscarPizza(String nombre){
        Statement st;
        Pizzas nuevoP=null;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from pizza where Nombre='"+nombre+"';");
           
            if (rs.next())       
                nuevoP = new Pizzas(rs.getString(1),rs.getInt(2));
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevoP;
    }
    public Ingredientes buscarIngrediente(String nombre){
        Statement st;
        Ingredientes nuevoI=null;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from ingredientes where Nombre='"+nombre+"';");
            if(rs.next()){
            nuevoI = new Ingredientes(rs.getString(1));}
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevoI;
    }
    
    
    
   public ArrayList<Ingredientes> MostrarIngredientes() {
        Statement st;
        
        ArrayList<Ingredientes> vIngredientes = new ArrayList<>();
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from ingredientes");

            while (rs.next()) {
                Ingredientes nuevoIngre = new Ingredientes(rs.getString(1));
                vIngredientes.add(nuevoIngre);
            }
            
            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(BBDD.class.getName()).log(Level.SEVERE, null, ex);
        }
        return vIngredientes;
    }
/*
    public ArrayList<alumno> buscarAlumoDNIvector(String dni) {
        Statement st;
        String aux1 = "";
        String aux2 = "";
        String aux3 = "";
        ArrayList<alumno> nuevoA = null;
        try {
            conectar();
            st = con.createStatement();
            ResultSet rs = st.executeQuery("Select * from alumnos where nombre='" + dni + "'");
            nuevoA = new ArrayList<>();
            alumno a = null;
            while (rs.next()) {
                aux1 = rs.getString(1);
                aux2 = rs.getString(2);
                aux3 = rs.getString(3);
                a = new alumno(aux1, aux2, aux3);
                nuevoA.add(a);
            }

            desconectar();
        } catch (SQLException ex) {
            Logger.getLogger(conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nuevoA;
    }*/
}

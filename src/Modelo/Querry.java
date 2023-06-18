
package Modelo;
//package
import Controlador.ConexionMysql;
import java.awt.HeadlessException;
import java.sql.PreparedStatement;
//
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Querry extends ConexionMysql {

    Statement statement;
    private PreparedStatement ps;
    ResultSet rs;
    //clases usadas
    Empleados empleados = new Empleados();

    //clases usadas    
    ////////CONSULTAS "LOGIN'
    //consulta para el login del sistema
    public int login(String correo, String contrasena) {
        conectarBDD();
        int cont = 10;
        try {
            statement = conexion.createStatement();
            String query = "SELECT COUNT(*) AS cuentas FROM empleados WHERE Correo='" + correo + "' AND Contraseña='" + contrasena + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                cont = rs.getInt("cuentas");
            }
        } catch (SQLException e) {
            cont = 11;
            JOptionPane.showMessageDialog(null, e);
        }
        return cont;
    }

    //obtener tipo de usuario
    public int logintipodeusuario(String correo) {
        conectarBDD();
        int cont = 0;
        try {
            statement = conexion.createStatement();
            String query = "SELECT idTipo FROM empleados WHERE Correo='" + correo + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                cont = rs.getInt("idTipo");
            }
        } catch (SQLException e) {
        }
        return cont;
    }

    //obtener id empleado
    public int loginobteneridempleado(String correo) {
        conectarBDD();
        int cont = 0;
        try {
            statement = conexion.createStatement();
            String query = "SELECT idEmpleado FROM empleados WHERE Correo='" + correo + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                cont = (rs.getInt("idEmpleado"));
            }
        } catch (SQLException e) {
        }
        return cont;
    }

    //obenter Empleado
    public String loginobtenertipoempleado(String correo) {
        conectarBDD();
        String cont = "ERROR";
        try {
            statement = conexion.createStatement();
            String query = "SELECT tipo_empleado.Nombre FROM empleados JOIN tipo_empleado ON empleados.idTipo=tipo_empleado.idTipo WHERE Correo='" + correo + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                cont = (rs.getString(1));
            }
        } catch (SQLException e) {
        }
        return cont;
    }

    public int loginobteneridtipoempleado(String correo) {
        conectarBDD();
        int cont = 0;
        try {
            statement = conexion.createStatement();
            String query = "SELECT empleados.idTipo FROM empleados WHERE Correo='" + correo + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                cont = (rs.getInt(1));
            }
        } catch (SQLException e) {
        }
        return cont;
    }
    //////// CONSULTAS "REGISTRO EMPLEADO"
    //Registar Empleados
    public void consultaBD(String sql) {
        //Conectarse ala Base de Datos
        conectarBDD();
        //Querry
        //Try Cactch para detectar errores
        try {
            ps = getconexion().prepareStatement(sql);
            ps.execute();
            ps.close();
            //CERRAR base de datos
            cerrarDB();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Fallo en la base de datos :" + e);
        }

    }

    //contar empleados por dni y correo
    public int consultadni(String dni) {
        conectarBDD();
        int rt = 10;
        try {
            statement = conexion.createStatement();
            String query = "select COUNT(Dni) AS consultadni from Empleados WHERE Dni='" + dni + "'";
            rs = statement.executeQuery(query);

            if (rs.next()) {
                rt = rs.getInt("consultadni");
            }
        } catch (SQLException e) {
            rt = -10;
        }

        return rt;
    }

    public int consultemail(String email) {
        conectarBDD();
        int rt = 10;
        try {
            statement = conexion.createStatement();
            String query = "select COUNT(Correo) AS consultacorreo from Empleados WHERE Correo='" + email + "'";
            rs = statement.executeQuery(query);

            if (rs.next()) {
                rt = rs.getInt("consultacorreo");
            }
        } catch (SQLException e) {
            rt = -10;
        }

        return rt;
    }

    //Vista CHEF
    //Rellenar Banner
    public void Banner(JLabel banner, int Empleado, String Tipo) {
        conectarBDD();

        try {
            statement = conexion.createStatement();
            String query = "select * from empleados where empleados.idEmpleado='" + Empleado + "'";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                String nombrecompleto = rs.getString(3) + " " + rs.getString(4);
                banner.setText("Bienvenido  " + nombrecompleto.toUpperCase() + "         ID: " + rs.getString(1) + "            Tipo Empleado: " + Tipo);
            }
        } catch (SQLException e) {
        }

    }
    //Mostar Datos de Pedidos

    public ResultSet mostrarDatosPedidos(int idempleado, int Encargado, String querry) {
        conectarBDD();
        try {
            statement = conexion.createStatement();
            String query = "SELECT * FROM lista JOIN pedido ON lista.idPedido=pedido.idPedido JOIN producto on lista.idProducto=producto.idProducto JOIN tipo_producto on tipo_producto.idTipo_Producto=producto.idTipo_Producto WHERE (lista.idEncargado='" + idempleado + "' OR lista.idEncargado='" + Encargado + "') AND (tipo_producto.idTipo_Producto='" + querry + "') AND (NOT lista.Estado=15)";

            rs = statement.executeQuery(query);
        } catch (SQLException e) {
        }

        return rs;
    }

    //conusltar empleado
    public String consultanombreempleado(String id) {
        conectarBDD();
        String Nombre = "";
        try {
            statement = conexion.createStatement();
            String query = "select * from empleados where empleados.idEmpleado='" + id + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                Nombre = rs.getString("Nombre") + " " + rs.getString("Apellido");
            }
        } catch (SQLException e) {
        }

        return Nombre;
    }

    //conusltar cliente
    public String consultanombrcliente(String id) {
        conectarBDD();
        String Nombre = "";
        try {
            statement = conexion.createStatement();
            String query = "select * from cliente where cliente.idcliente='" + id + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                Nombre = rs.getString("Nombre") + " " + rs.getString("Apellido");
            }
        } catch (SQLException e) {
        }

        return Nombre;
    }

    //consultar estado
    public String consultaestado(String id) {
        conectarBDD();
        String Nombre = "";
        try {
            statement = conexion.createStatement();
            String query = "select * from estado_pedido where estado_pedido.idEstado='" + id + "'";
            rs = statement.executeQuery(query);
            if (rs.next()) {
                Nombre = rs.getString("Nombre");
            }
        } catch (SQLException e) {
        }

        return Nombre;
    }

    //Actualizar estado 
    public void Actualizarestado(String ID, int estado) {
        conectarBDD();
        try {
            statement = conexion.createStatement();
            String query = "UPDATE lista set Estado='" + estado + "' WHERE idLista='" + ID + "'";
            statement.executeUpdate(query);
            JOptionPane.showMessageDialog(null, "Estado Cambiado ");
        } catch (HeadlessException | SQLException e) {

        }
    }

    //Actulizar CHEF
    public void Actulizarchefdepedido(int idlista, int idencargado) {
        conectarBDD();
        try {
            statement = conexion.createStatement();
            String query = "Update lista set idEncargado='" + idencargado + "' where idLista='" + idlista + "'";
            statement.executeUpdate(query);
        } catch (SQLException e) {
        }

    }

    //mostrar lista de estados
    public void listarestado(JComboBox combo) {
        conectarBDD();

        try {
            statement = conexion.createStatement();
            String query = "select * from estado_pedido";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                combo.addItem(rs.getString(2));
            }
        } catch (SQLException e) {
        }

    }

    //consultar id de estado
    public int consultaridestadodecliente(int idestado) {
        conectarBDD();
        int estado = 0;
        try {
            statement = conexion.createStatement();
            String query = "select * from lista where Estado='" + idestado + "'";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                estado = rs.getInt(7);
            }
        } catch (SQLException e) {
        }
        return estado;
    }

    //consultar id de estado
    public int consultaridestado(String nombre) {
        conectarBDD();
        int estado = 0;
        try {
            statement = conexion.createStatement();
            String query = "select * from estado_pedido where Nombre='" + nombre + "'";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                estado = rs.getInt(1);
            }
        } catch (SQLException e) {
        }
        return estado;
    }
    //==================Mesero=======================

    public ResultSet Buscarclien(String dato) {
        conectarBDD();
        try {
            statement = conexion.createStatement();
            String query = "SELECT * FROM lista JOIN pedido ON lista.idPedido=pedido.idPedido JOIN producto ON lista.idProducto=producto.idProducto JOIN cliente ON cliente.idCliente=pedido.idCliente JOIN empleados ON lista.idEncargado=empleados.idEmpleado JOIN estado_pedido ON estado_pedido.idEstado=lista.Estado WHERE pedido.idCliente LIKE '%" + dato + "%' OR cliente.Nombre LIKE '%" + dato + "%';";
            rs = statement.executeQuery(query);
        } catch (Exception e) {
        }

        return rs;
    }
    
    
    //==============================Factura=========================
    public ResultSet BuscarFac(String dato, String date) {
        conectarBDD();
        try {
            statement = conexion.createStatement();
            String query = "SELECT * FROM lista JOIN pedido ON lista.idPedido=pedido.idPedido JOIN producto ON lista.idProducto=producto.idProducto JOIN cliente ON cliente.idCliente=pedido.idCliente JOIN empleados ON lista.idEncargado=empleados.idEmpleado JOIN estado_pedido ON estado_pedido.idEstado=lista.Estado WHERE pedido.Fecha LIKE '%" + date + "%' AND cliente.Nombre LIKE '%" + dato + "%';";
            rs = statement.executeQuery(query);
        } catch (Exception e) {
        }

        return rs;
    }
    
    
    public int RegistroFac(Factura fc){
        
        int r=0;
        conectarBDD();
        
        String sql="INSERT INTO factura(idFactura, idpedido, subTotal, IGV, Total, direccion, telefono, ruc, observaciones, fecha_emitida) VALUES (?,?,?,?,?,?,?,?,?,?) ";
        try {
            
            ps=conexion.prepareStatement(sql);
            
            ps.setInt(1, fc.getIdFac());
            ps.setInt(2, fc.getIdPed());
            ps.setDouble(3, fc.getSub());
            ps.setDouble(4, fc.getIgv());
            ps.setDouble(5, fc.getTotal());
            ps.setString(6, fc.getDir());
            ps.setInt(7, fc.getTelef());
            ps.setFloat(8, fc.getRuc());
            ps.setString(9, fc.getObs());
            ps.setString(10, fc.getFecha());
            r=ps.executeUpdate();
            if(r==1){
                return 1;
            }
            else{
                return 0;
            }
            
        } catch (Exception e) {
             Logger.getLogger(Querry.class.getName()).log(Level.SEVERE,null,e);
        }
        return r;
    }

//=================Vista Administrador========//
      
    //===============CRUD ADMINISTRADOR =================
    
    
    //LISTAR USUARIOS
     public List listar() {
       
       conectarBDD();
        List<Empleados> datos = new ArrayList<>();
        try {
            ps = conexion.prepareStatement("select * from empleados");
            rs = ps.executeQuery();
            while (rs.next()) {
                Empleados p = new Empleados();
                p.setId(rs.getInt(1));
                p.setTipo_empleado(rs.getInt(2));
                p.setNombre(rs.getString(3));
                p.setApellido(rs.getString(4));
                p.setDni(rs.getInt(5));
                p.setCoreo(rs.getString(6));
                p.setContraseña(rs.getString(7));
                datos.add(p);
            }
        } catch (Exception e) {
        }
        return datos;
        }
     
     
     //LISTAR CLIENTES
     public List cliente(){
         conectarBDD();
         List<cliente> datos=new ArrayList<>();
         try {
              ps = conexion.prepareStatement("SELECT cliente.*, pedido.Fecha FROM cliente LEFT JOIN pedido ON pedido.idCliente=cliente.idCliente");
                                             
              rs=ps.executeQuery();
              while (rs.next()) {
                  cliente p= new cliente();
                  p.setID(rs.getInt(1));
                  p.setNombre(rs.getString(2));
                  p.setApellido(rs.getString(3));
                  p.setDni(rs.getInt(4));
                  p.setFecha(rs.getDate(5));
                  datos.add(p);
              }
              
             } catch (Exception e) {
             }
         return datos;
     }
        
    //Boton Elimiar Registro
     public void EliminarRegistro(int id){
        conectarBDD();
        try {
            
            String SQL="delete from empleados where idEmpleado="+id;
            
            Statement st=conexion.createStatement();
            st.executeUpdate(SQL);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar registro"+e.getMessage());
        }
    }
    
     
      //Boton Actualizar Registro
      public void ActualizarRegistro(Empleados p){
        conectarBDD();
       
        String sql="update empleados set idTipo=?,Nombre=?,Apellido=?,Correo=?,Contraseña=? where Dni=?";        
        try {
            
           ps = conexion.prepareStatement(sql);
                        
            ps.setInt(1,p.getTipo_empleado());
            ps.setString(2,p.getNombre());
            ps.setString(3,p.getApellido());
            ps.setString(4,p.getCoreo());
            ps.setString(5,p.getContraseña());
            ps.setInt(6,p.getDni());
            
            ps.executeUpdate(); 
            
        }catch (Exception e) {
            
        }
        
           
    }
      //ADMINSTRADOR
    //Mostar Datos de Pedidos (ACTIVIDADES)
    public ResultSet mostrarDatosPedidos() {
        conectarBDD();
        
        try {
            st = conexion.createStatement();
            String query = "SELECT * FROM lista JOIN pedido JOIN producto on lista.idProducto=producto.idProducto and lista.idPedido=pedido.idPedido";
            rs = st.executeQuery(query);
        } catch (SQLException e) {
        }

        return rs;
    }
}



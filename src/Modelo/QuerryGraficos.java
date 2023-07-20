package Modelo;

import Controlador.ConexionMysql;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Panel;
import java.sql.JDBCType;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javax.swing.JOptionPane;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.data.category.DefaultCategoryDataset;

public class QuerryGraficos extends ConexionMysql {

    Statement statement;
    ResultSet rs;

    //Graficar
    public void graficar(DefaultCategoryDataset datos, Panel panel,String Titulo,String leyenda) {
        //Creacion de Grafico
        JFreeChart garficos = ChartFactory.createBarChart(
                Titulo, leyenda,"",
                datos, PlotOrientation.VERTICAL,
                true, true, false);
        //Cambiar COLORES
        garficos.setBackgroundPaint(Color.white);
        //color fondo
        CategoryPlot plot = garficos.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        //Dar color a las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);
        //Agregar grafico a Panel
        ChartPanel chartpanel = new ChartPanel(garficos);
        chartpanel.setMouseWheelEnabled(true);
        chartpanel.setPreferredSize(new Dimension(870, 345));
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel, BorderLayout.NORTH);
    }
    //Graficar vertical
    public void graficarvertical(DefaultCategoryDataset datos, Panel panel,String Titulo,String leyenda) {
        //Creacion de Grafico
        JFreeChart garficos = ChartFactory.createBarChart(
                Titulo, leyenda, "Cantidad",
                datos, PlotOrientation.HORIZONTAL,
                true, true, false);
        //Cambiar COLORES
        garficos.setBackgroundPaint(Color.white);
        //color fondo
        CategoryPlot plot = garficos.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        //Dar color a las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, 0.0f, new Color(0, 64, 0));
        renderer.setSeriesPaint(0, gp);
        //Agregar grafico a Panel
        ChartPanel chartpanel = new ChartPanel(garficos);
        chartpanel.setMouseWheelEnabled(true);
        chartpanel.setPreferredSize(new Dimension(870, 345));
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel, BorderLayout.NORTH);
    }

    //listar a todos los mozos
    public int[] listaIdMozos() {
        conectarBDD();
        int idmozos[] = {0};
        try {
            statement = conexion.createStatement();
            //consultar cantidad de mozos
            String querycantidad = "SELECT Count(*) FROM empleados WHERE (empleados.idTipo=13) AND (NOT empleados.idEmpleado=10000000)";
            rs = statement.executeQuery(querycantidad);
            while (rs.next()) {
                idmozos = new int[rs.getInt(1)];
            }
            //guardar ids
            String query = "SELECT empleados.idEmpleado FROM empleados WHERE (empleados.idTipo=13) AND (NOT empleados.idEmpleado=10000000)";
            rs = statement.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                idmozos[i] = rs.getInt(1);
                i++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        cerrarDB();
        return idmozos;
    }

    //Graficar clientes
    public void graficarclientes(String fechainicio, String Fechafin, Panel panel) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (int i = 0; i < listaIdMozos().length; i++) {
            //query boleto
            String queryboleto = "SELECT COUNT(boleta.idMozo) AS cantidad FROM boleta JOIN empleados ON "
                    + "empleados.idEmpleado=boleta.idMozo WHERE (boleta.fecha_emitida BETWEEN '" + fechainicio + "' "
                    + "AND '" + Fechafin + "') AND (boleta.idMozo='" + listaIdMozos()[i] + "')";
            //query Factura
            String query = "SELECT factura.idMozo,empleados.Nombre,empleados.Apellido,COUNT(factura.idMozo) "
                    + "AS cantidad FROM factura JOIN empleados ON empleados.idEmpleado=factura.idMozo "
                    + "WHERE (factura.fecha_emitida BETWEEN '" + fechainicio + "' AND '" + Fechafin + "')"
                    + " AND (factura.idMozo='" + listaIdMozos()[i] + "')";
            try {
                conectarBDD();
                statement = conexion.createStatement();
                //Boleto
                int cantBoleto = 0;
                rs = statement.executeQuery(queryboleto);
                while (rs.next()) {
                    cantBoleto = rs.getInt(1);
                }
                //Factura 
                rs = statement.executeQuery(query);
                while (rs.next()) {
                    if (rs.getInt(4) + cantBoleto != 0) {
                        if (rs.getString(3).equals(" ") || rs.getString(2).equals(" ")) {
                            datos.setValue(rs.getInt(4) + cantBoleto, "Cantidad de Ventas", rs.getString(3) + " " + rs.getString(2));
                        } else {
                            datos.setValue(rs.getInt(4) + cantBoleto, "Cantidad de Ventas", rs.getString(3).split(" ")[0] + " " + rs.getString(2).split(" ")[0]);
                        }
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        }
        cerrarDB();
        //graficar
        graficar(datos, panel,"Resumen de Mozos","Cantidad");
    }

    //==========================================CLIENTES RRECURRENTES===============================================================
    //Graficar
    public void graficarConcurrentes(DefaultCategoryDataset datos, Panel panel) {
        //Creacion de Grafico
        JFreeChart garficos = ChartFactory.createBarChart(
                "Grafico Clientes", "Clientes", "Cantidad",
                datos, PlotOrientation.VERTICAL,
                true, true, false);
        //Cambiar COLORES
        garficos.setBackgroundPaint(Color.white);
        //color fondo
        CategoryPlot plot = garficos.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        //Dar color a las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, 0.0f, new Color(20, 129, 192));
        renderer.setSeriesPaint(0, gp);
        //Agregar grafico a Panel
        ChartPanel chartpanel = new ChartPanel(garficos);
        chartpanel.setMouseWheelEnabled(true);
        chartpanel.setPreferredSize(new Dimension(870, 345));
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel, BorderLayout.NORTH);

    }

    //listar a todos los clientes
    public int[] listaIdClientes() {
        conectarBDD();
        int idClientes[] = {0};
        try {
            statement = conexion.createStatement();
            //consultar cantidad de mozos
            String querycantidad = "SELECT Count(*) FROM cliente WHERE NOT cliente.Dni=0";
            rs = statement.executeQuery(querycantidad);
            while (rs.next()) {
                idClientes = new int[rs.getInt(1)];
            }
            //guardar ids
            String query = "SELECT cliente.idCliente FROM cliente WHERE NOT cliente.Dni=0";
            rs = statement.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                idClientes[i] = rs.getInt(1);
                i++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        cerrarDB();
        return idClientes;
    }

    //Graficar clientes
    public void graficarclientesConcurridos(String fechainicio, String Fechafin, Panel panel) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (int i = 0; i < listaIdClientes().length; i++) {
            //query boleto
            String queryboleto = "SELECT COUNT(boleta.idCliente) AS cantidad FROM boleta JOIN cliente ON "
                    + "cliente.idCliente=boleta.idCliente WHERE (boleta.fecha_emitida BETWEEN '" + fechainicio + "' "
                    + "AND '" + Fechafin + "') AND (boleta.idCliente='" + listaIdClientes()[i] + "')";
            //query Factura
            String query = "SELECT factura.idCliente,cliente.Nombre,cliente.Apellido,COUNT(factura.idCliente) "
                    + "AS cantidad FROM factura JOIN cliente ON cliente.idCliente=factura.idCliente "
                    + "WHERE (factura.fecha_emitida BETWEEN '" + fechainicio + "' AND '" + Fechafin + "')"
                    + " AND (factura.idCliente='" + listaIdClientes()[i] + "')";
            try {
                conectarBDD();
                statement = conexion.createStatement();
                //Boleto
                int cantBoleto = 0;
                rs = statement.executeQuery(queryboleto);
                while (rs.next()) {
                    cantBoleto = rs.getInt(1);
                }
                //Factura 
                rs = statement.executeQuery(query);
                while (rs.next()) {
                    if (rs.getInt(4) + cantBoleto != 0) {
                        if (rs.getString(3).equals(" ") || rs.getString(2).equals(" ")) {
                            datos.setValue(rs.getInt(4) + cantBoleto, "Cantidad de Visitas", rs.getString(3) + " " + rs.getString(2));
                        } else {
                            datos.setValue(rs.getInt(4) + cantBoleto, "Cantidad de Visitas", rs.getString(3).split(" ")[0] + " " + rs.getString(2).split(" ")[0]);
                        }
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        }
        cerrarDB();
        //graficar
        graficarConcurrentes(datos, panel);
    }

    //==========================================GRAFICAR VENTAS===============================================================
    public void graficarVentas(String fechainicio, String Fechafin, Panel panel) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        String rangosfechas[][] = calcularfecha(fechainicio, Fechafin);
        String fechain = null, fechafin = null;
        for (int i = 0; i < rangosfechas.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    fechain = rangosfechas[i][j];
                } else if (j == 1) {
                    fechafin = rangosfechas[i][j];
                }
            }
            //query boleto
            String queryboleto = "SELECT SUM(boleta.Total) FROM boleta WHERE boleta.fecha_emitida BETWEEN '" + fechain + "' AND '" + fechafin + "'";
            //query Factura
            String query = "SELECT SUM(factura.Total) FROM factura WHERE factura.fecha_emitida BETWEEN '" + fechain + "' AND '" + fechafin + "'";
            //split fechas
            String fechainisplit = fechain.split("-")[1] + "-" + fechain.split("-")[2];
            String fechafinsplit = fechafin.split("-")[1] + "-" + fechafin.split("-")[2];
            String leyendafecha;
            if (fechainisplit.equals(fechafinsplit)) {
                leyendafecha = fechainisplit;
            } else {
                leyendafecha = fechainisplit + " a " + fechafinsplit;
            }
            try {
                conectarBDD();
                statement = conexion.createStatement();
                //Boleto
                Double cantBoleto = 0.0;
                rs = statement.executeQuery(queryboleto);
                while (rs.next()) {
                    cantBoleto = rs.getDouble(1);
                }
                //Factura 
                rs = statement.executeQuery(query);
                //rellenar tabla
                while (rs.next()) {
                    if (rs.getDouble(1) + cantBoleto != 0) {
                        datos.setValue(rs.getDouble(1) + cantBoleto, "Cantidad de Ventas", leyendafecha);
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        }
        cerrarDB();
        //graficar
        graficar(datos, panel,"Resumen de Ventas","Ventas Totales");
    }

    //Calcular fechass 
    public String[][] calcularfecha(String fechainicio, String Fechafin) {
        String fechas[][] = null;
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fechai = formato.parse(fechainicio);
            Date fechafin = formato.parse(Fechafin);
            long dias = fechafin.getTime() - fechai.getTime();
            TimeUnit tiempo = TimeUnit.DAYS;
            long tiempotrans = tiempo.convert(dias, TimeUnit.MILLISECONDS);
            int tiempotranscurrido = Integer.parseInt(String.valueOf(tiempotrans)) + 1;
            if (tiempotranscurrido <= 7) {
                fechas = new String[tiempotranscurrido][2];
            } else if (tiempotranscurrido >= 8 && tiempotranscurrido <= 15) {
                fechas = new String[2][2];
            } else if (tiempotranscurrido >= 16 && tiempotranscurrido <= 31) {
                fechas = new String[4][2];
            } else if (tiempotranscurrido >= 32 && tiempotranscurrido <= 124) {
                fechas = new String[4][2];
            } else if (tiempotranscurrido >= 125) {
                fechas = new String[7][2];
            }
            //convertir strinf a local date
            LocalDate fechainicial = LocalDate.parse(fechainicio);
            LocalDate fechafinal = LocalDate.parse(Fechafin);
            int lastloop = fechas.length - 1;
            for (int i = 0; i < fechas.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        fechas[i][j] = fechainicial.toString();

                    } else if (j == 1) {
                        if (lastloop == i) {
                            fechas[i][j] = Fechafin;
                        } else {
                            fechas[i][j] = fechainicial.plusDays(tiempotranscurrido / fechas.length).minusDays(1).toString();
                        }

                    }
                }
                fechainicial = fechainicial.plusDays(tiempotranscurrido / fechas.length);
            }

        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Error al introducir fecha");
        }

        return fechas;
    }
//==========================================GRAFICAR PRODUCTOS MAS CONSUMIDOS===============================================================
    public void graficarProductosMas(String fechainicio, String Fechafin, Panel panel) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        String cantidad [][]=consultarproductos();
        String id="", nombre=""; 
        String Top10[][] = new String[10][2];
        for (int i = 0; i < 10; i++) {
            Top10[i][0]="";
            Top10[i][1]="0";
        }
        
        for (int i = 0; i < cantidad.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    id=cantidad[i][j];
                } else if (j == 1) {
                    nombre=cantidad[i][j];
                }
            }
            //query 
            String query = "SELECT SUM(Cantidad) FROM lista JOIN pedido on lista.idPedido=pedido.idPedido "
                    + "WHERE (lista.idProducto='"+id+"') and (pedido.Fecha BETWEEN '"+fechainicio+"' AND '"+Fechafin+ "')";
            try {
                conectarBDD();
                statement = conexion.createStatement();
                rs = statement.executeQuery(query);
                //rellenar 
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        //System.out.println(rs.getInt(1) +"nombre "+nombre);
                        for (int j = 0; j < 10; j++) {
                            if (rs.getInt(1) > Integer.parseInt(Top10[j][1])) {
                                int a = i + 1;
                                //System.out.println(id+" "+Top10[j][1]);
                                if (i < 9) {
                                    for (int k = 9; k > a; k--) {
                                        Top10[k][0] = Top10[k - 1][0];
                                        Top10[k][1] = Top10[k - 1][1];
                                    }

                                }
                                Top10[j][0] = nombre;
                                Top10[j][1] = String.valueOf(rs.getInt(1));
                                break;
                            };
                        }
                    }
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        }
        for (int j = 0; j < 10; j++) {
            datos.setValue(Integer.parseInt(Top10[j][1]), "Cantidad", Top10[j][0]);
        }
        cerrarDB();
        //graficar
        
        graficarvertical(datos, panel,"Productos mas consumidos","Productos");
    }

    //productos
    public String[][] consultarproductos() {
        String productos[][] = null;
        int i=0;
        try {
            conectarBDD();
            statement = conexion.createStatement();
            String query="SELECT idProducto,Nombre FROM producto";
            rs = statement.executeQuery(query);
            //cantidad productos
            int extension = 0;
            while (rs.next()) {
               extension+=1;
            }
            //agregar productos
            ResultSet r = statement.executeQuery(query);
            productos=new String[extension][2];
            while (r.next()) {
                productos[i][0]=r.getString(1);
                productos[i][1]=r.getString(2);
                i++;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        cerrarDB();
        return productos;
    }
    //
    //
     //
    // //
    //
     //
    // //
    // //
    // //
    //
    
    
    
    
    //==========================================GRAFICAR PRODUCTOS MENOS CONSUMIDOS===============================================================
   

    //Graficar
    public void graficarPro(DefaultCategoryDataset datos, Panel panel) {
        //Creacion de Grafico
        JFreeChart garficos = ChartFactory.createBarChart(
                "GRAFICO PRODUCTO", "PLATOS", "CANTIDAD",
                datos, PlotOrientation.VERTICAL,
                true, true, false);
        //Cambiar COLORES
        garficos.setBackgroundPaint(Color.white);
        //color fondo
        CategoryPlot plot = garficos.getCategoryPlot();
        plot.setBackgroundPaint(Color.white);
        //Dar color a las barras
        BarRenderer renderer = (BarRenderer) plot.getRenderer();
        GradientPaint gp = new GradientPaint(0.0f, 0.0f, Color.BLACK, 0.0f, 0.0f, new Color(20, 129, 192));
        renderer.setSeriesPaint(0, gp);
        //Agregar grafico a Panel
        ChartPanel chartpanel = new ChartPanel(garficos);
        chartpanel.setMouseWheelEnabled(true);
        chartpanel.setPreferredSize(new Dimension(870, 345));
        panel.setLayout(new BorderLayout());
        panel.add(chartpanel, BorderLayout.NORTH);

    }
//
    //listar a todos los clientes
    public int[] listaIdPro() {
        conectarBDD();
        int idClientes[] = {0};
        try {
            statement = conexion.createStatement();
            //consultar cantidad de pedidos
            String querycantidad = "SELECT count(*) FROM producto ";
            rs = statement.executeQuery(querycantidad);
            while (rs.next()) {
                idClientes = new int[rs.getInt(1)];
            }
            //guardar ids
            String query = "SELECT producto.idProducto FROM producto";
            rs = statement.executeQuery(query);
            int i = 0;
            while (rs.next()) {
                idClientes[i] = rs.getInt(1);
                i++;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        cerrarDB();
        return idClientes;
    }

    //Graficar clientes
    public void graficarProductosMenos(String fechainicio, String Fechafin, Panel panel) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        for (int i = 0; i < listaIdPro().length; i++) {
            
            //query Factura
            String query = "SELECT producto.idProducto, producto.Nombre,SUM(lista.Cantidad) AS cantidad from lista "
                    + "JOIN pedido ON (lista.idPedido=pedido.idPedido) JOIN producto ON (producto.idProducto=lista.idProducto)"
                    + "where (pedido.Fecha BETWEEN '" + fechainicio + "' AND '" + Fechafin + "')"
                    + " AND (producto.idProducto='" + listaIdPro()[i] + "') ";
            
             
            try {
                conectarBDD();
                statement = conexion.createStatement();
                
                //Factura 
                rs = statement.executeQuery(query);
                while (rs.next()) {
                    if (rs.getInt(3) != 0) {
                        if (rs.getString(1).equals(" ")) {
                            datos.setValue(rs.getInt(3) , "Cantidad de Visitas",  rs.getString(2));
                        } else {
                            datos.setValue(rs.getInt(3) , "Cantidad de Visitas",  rs.getString(2));
                        }
                    }
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        }
        cerrarDB();
        //graficar
        graficarvertical(datos, panel,"Productos consumidos","Productos");
    }
    
    //==========================================GRAFICAR CLIENTES MAS RECURRENTES===============================================================
    public void graficarClientesMas(String fechainicio, String Fechafin, Panel panel) {
        DefaultCategoryDataset datos = new DefaultCategoryDataset();
        String cantidad [][]=consultarClientes();
        String id="", nombre=""; 
        String Top10[][] = new String[10][2];
        for (int i = 0; i < 10; i++) {
            Top10[i][0]="";
            Top10[i][1]="0";
        }
        
        for (int i = 0; i < cantidad.length; i++) {
            for (int j = 0; j < 2; j++) {
                if (j == 0) {
                    id=cantidad[i][j];
                } else if (j == 1) {
                    nombre=cantidad[i][j];
                }
            }
            //query 
            String query = "SELECT count(cliente.idCliente) FROM pedido JOIN cliente on cliente.idCliente=pedido.idCliente "
                    + "WHERE (cliente.idCliente='"+id+"') and (pedido.Fecha BETWEEN '"+fechainicio+"' AND '"+Fechafin+ "')";
            try {
                conectarBDD ();
                statement = conexion.createStatement();
                rs = statement.executeQuery(query);
                //rellenar 
                while (rs.next()) {
                    if (rs.getInt(1) > 0) {
                        //System.out.println(rs.getInt(1) +"nombre "+nombre);
                        for (int j = 0; j < 10; j++) {
                            if (rs.getInt(1) > Integer.parseInt(Top10[j][1])) {
                                int a = i + 1;
                                //System.out.println(id+" "+Top10[j][1]);
                                if (i < 9) {
                                    for (int k = 9; k > a; k--) {
                                        Top10[k][0] = Top10[k - 1][0];
                                        Top10[k][1] = Top10[k - 1][1];
                                    }

                                }
                                Top10[j][0] = nombre;
                                Top10[j][1] = String.valueOf(rs.getInt(1));
                                break;
                            };
                        }
                    }
                }
                
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error: " + e);
            }

        }
        for (int j = 0; j < 10; j++) {
            datos.setValue(Integer.parseInt(Top10[j][1]), "Cantidad", Top10[j][0]);
        }
        cerrarDB();
        //graficar
        
        graficarvertical(datos, panel,"clientes mas recurrentes","Clientes");
    }

    //productos
    public String[][] consultarClientes() {
        String productos[][] = null;
        int i=0;
        try {
            conectarBDD();
            statement = conexion.createStatement();
            String query="SELECT idCliente,Nombre FROM cliente";
            rs = statement.executeQuery(query);
            //cantidad productos
            int extension = 0;
            while (rs.next()) {
               extension+=1;
            }
            //agregar productos
            ResultSet r = statement.executeQuery(query);
            productos=new String[extension][2];
            while (r.next()) {
                productos[i][0]=r.getString(1);
                productos[i][1]=r.getString(2);
                i++;
            }
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e);
        }
        cerrarDB();
        return productos;
    }
}

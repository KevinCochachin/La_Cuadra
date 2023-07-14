
package Modelo;
import Controlador.ConexionMysql;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Panel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public void graficar(DefaultCategoryDataset datos,Panel panel) {
        //Creacion de Grafico
        JFreeChart garficos = ChartFactory.createBarChart(
                "Grafico Mozos", "Mozos", "Cantidad",
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
        graficar(datos, panel);
    }

    //==========================================CLIENTES RRECURRENTES===============================================================
    
    //Graficar
    public void graficarConcurrentes(DefaultCategoryDataset datos,Panel panel) {
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
            //consultar cantidad de clientes
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
    
    
    //=====================================================CLIENTES NUEVOS ======================================================
    
    //==========================================CLIENTES RRECURRENTES===============================================================
    
    //Graficar
    public void graficarNuevos(DefaultCategoryDataset datos,Panel panel) {
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
    public int[] listaIdClientesNuevos() {
        conectarBDD();
        int idClientes[] = {0};
        try {
            statement = conexion.createStatement();
            //consultar cantidad de clientes
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
    public void graficarclientesNuevos(String fechainicio, String Fechafin, Panel panel) {
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
        for (int i = 0; i < listaIdClientes().length; i++) {
            //query boleto
            String queryboleto = "SELECT fecha_emitida,SUM(Total) FROM factura "
                    + " WHERE (boleta.fecha_emitida BETWEEN '" + fechainicio + "' "
                    + "AND '" + Fechafin + "')";
            //query Factura
            String query = "SELECT factura.idCliente,cliente.Nombre,cliente.Apellido,COUNT(factura.idCliente) "
                    + "AS cantidad FROM factura JOIN cliente ON cliente.idCliente=factura.idCliente "
                    + "WHERE (factura.fecha_emitida BETWEEN '" + fechainicio + "' AND '" + Fechafin + "')";
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

    //Calcular fechass 
    public String calcularfecha(String fechainicio, String Fechafin)  {
        String fechas=" ";
        try {
            SimpleDateFormat formato = new SimpleDateFormat("YYYY-MM-dd");
            Date fechai = formato.parse(fechainicio);
            Date fechafin = formato.parse(Fechafin);
            long dias = fechafin.getTime() - fechai.getTime();
            TimeUnit tiempo = TimeUnit.DAYS;
            long tiempotrans = tiempo.convert(dias, tiempo);
            System.out.println(fechainicio+" "+Fechafin);
            System.out.println(fechafin + " " + fechai + "  " + tiempotrans);
        } catch (ParseException e) {
            System.out.println(""+e);
        }
        return fechas;
    }
}

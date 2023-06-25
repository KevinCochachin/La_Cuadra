
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
        JFreeChart garficos = ChartFactory.createBarChart3D(
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

    

}

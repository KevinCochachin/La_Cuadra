
package Controlador;

import Modelo.registro_pedido;
import Modelo.Registro_pedidosDAO;
import Vista.Registro_Pedidos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.lang.Override;

public class Controlpedido {
    
    Registro_pedidosDAO RPdao = new Registro_pedidosDAO();
    registro_pedido ped = new registro_pedido();
    Registro_Pedidos RegPed = new Registro_Pedidos();
    DefaultTableModel modelo = new DefaultTableModel();

    public Controlpedido(Registro_Pedidos RP) {
        this.RegPed = RP;
        this.RegPed.btneliminar.addActionListener((ActionListener) this);
        this.RegPed.btnregistrar.addActionListener((ActionListener) this);
        this.RegPed.btnagregar.addActionListener((ActionListener) this);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == RegPed.btneliminar) {
            eliminar();
            listar(RegPed.Pedidos);
            nuevo();
        }
        if (e.getSource() == RegPed.btnregistrar) {
            listar(RegPed.Pedidos);
            agregarListaPedidos();
            listarPedidos(RegPed.Pedidos);
            nuevo();
        }
        if (e.getSource() == RegPed.btnagregar) {
            agregar();
            listar(RegPed.Pedidos);
            nuevo();
        }
    }

    void nuevo() {
        RegPed.itemmesa.setSelectedItem("");
        RegPed.itemproducto.setSelectedItem("");
        RegPed.txtdescripcion.setText("");
        RegPed.txtcliente.setText("");
        RegPed.cantidad.setValue("");
    }

    public void eliminar() {
        int fila = RegPed.Pedidos.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(RegPed, "Debe Seleccionar una Fila...!!!");
        } else {
            int id = Integer.parseInt((String) RegPed.Pedidos.getValueAt(fila, 0).toString());
//            RPdao.Delete(id);
            JOptionPane.showMessageDialog(RegPed, "Orden eliminada");
        }

    }

    public void agregar() {
        String mesa = (String) RegPed.itemmesa.getSelectedItem();
        String producto = (String) RegPed.itemproducto.getSelectedItem();
        String descripcion = RegPed.txtdescripcion.getText();
        String cliente = RegPed.txtcliente.getText();
        int cantidad = (int) RegPed.cantidad.getValue();
        ped.setMesa(mesa);
        ped.setProducto(producto);
        ped.setDescripcion(descripcion);
        ped.setCliente(cliente);
        ped.setCantidad(cantidad);
        int r = RPdao.agregar(ped);
        if (r == 1) {
            JOptionPane.showMessageDialog(RegPed, "Orden agregada");
        } else {
            JOptionPane.showMessageDialog(RegPed, "Error");
        }
    }

    public void listar(JTable Pedidos) {
        modelo = (DefaultTableModel) Pedidos.getModel();
        Pedidos.setModel(modelo);
        List<registro_pedido> lista = RPdao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdPedido();
            objeto[1] = lista.get(i).getCantidad();
            objeto[2] = lista.get(i).getProducto();
            objeto[3] = lista.get(i).getCliente();
            objeto[4] = lista.get(i).getMesa();
            objeto[5] = lista.get(i).getDescripcion();
            modelo.addRow(objeto);
        }
        Pedidos.setRowHeight(35);
        Pedidos.setRowMargin(10);

    }

    public void agregarListaPedidos() {
        String mesa = (String) RegPed.itemmesa.getSelectedItem();
        String producto = (String) RegPed.itemproducto.getSelectedItem();
        String descripcion = RegPed.txtdescripcion.getText();
        String cliente = RegPed.txtcliente.getText();
        int cantidad = (int) RegPed.cantidad.getValue();
        ped.setMesa(mesa);
        ped.setProducto(producto);
        ped.setDescripcion(descripcion);
        ped.setCliente(cliente);
        ped.setCantidad(cantidad);
        int r = RPdao.agregar(ped);
        if (r == 1) {
            JOptionPane.showMessageDialog(RegPed, "Orden agregada");
        } else {
            JOptionPane.showMessageDialog(RegPed, "Error");
        }
    }

    public void listarPedidos(JTable lista_pedidos) {
        modelo = (DefaultTableModel) lista_pedidos.getModel();
        lista_pedidos.setModel(modelo);
        List<registro_pedido> lista = RPdao.listar();
        Object[] objeto = new Object[4];
        for (int i = 0; i < lista.size(); i++) {
            objeto[0] = lista.get(i).getIdPedido();
            objeto[1] = lista.get(i).getCantidad();
            objeto[2] = lista.get(i).getProducto();
            objeto[3] = lista.get(i).getCliente();
            objeto[4] = lista.get(i).getMesa();
            objeto[5] = lista.get(i).getDescripcion();
            modelo.addRow(objeto);
        }
        lista_pedidos.setRowHeight(35);
        lista_pedidos.setRowMargin(10);

    }

}

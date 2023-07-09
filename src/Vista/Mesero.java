/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.Querry;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Kevin
 */
public class Mesero extends javax.swing.JFrame {
    int idInciado;
    
    public Mesero(int idEmpleado, String NombreVentana,int TipoEmpleado) {
        this.idInciado=idEmpleado;
        Querry query=new Querry();
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Icon.png")).getImage());
        this.setTitle("Mesero");
        this.setLocationRelativeTo(null);
        Resultadobusqueda("");
        query.Banner(banner, idEmpleado, NombreVentana);
    }

    
    public final void Resultadobusqueda(String dato){
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("idPedido");
        modelo.addColumn("idLista");
        modelo.addColumn("Mesa");
        modelo.addColumn("Cant.");
        modelo.addColumn("Plato");
        modelo.addColumn("Cliente");
        modelo.addColumn("Chef");
        modelo.addColumn("Atendido");
        modelo.addColumn("Descripcion");
        Tabla.setModel(modelo);
        //Clasequery
        String[] datos = new String[9];
        Querry qr = new Querry();
        try {
            ResultSet rs=qr.Buscarclien(dato,idInciado);
            while (rs.next()){
            //idpedido
            datos[0] = rs.getString(2);
            //idLista
            datos [1] = rs.getString(1);
             //mesa
            datos[2] = rs.getString(12);
             //cantidad
            datos[3] = rs.getString(4);
             //Plato
            datos[4] = rs.getString(15);
            //Cliente
            datos[5] = rs.getString(19);
             //Chef
            datos[6] = rs.getString(24);
            //Atendido
            datos [7] = rs.getString(30);
            //Descripcion
            datos[8] = rs.getString(5);
            modelo.addRow(datos);
            }   
        } catch (SQLException e) {
        }
    }
    
    public void colorbtnhorver(JButton boton) {
        boton.setBackground(Color.WHITE);
        boton.setForeground(Color.BLACK);
    }
    
    public void colorbtn(JButton boton) {
        boton.setBackground(Color.BLACK);
        boton.setForeground(Color.WHITE);
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        NuevoPedido = new javax.swing.JButton();
        GenerarBoleta = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tabla = new javax.swing.JTable();
        jLabel3 = new javax.swing.JLabel();
        Txtdato = new javax.swing.JTextField();
        btnbuscar = new javax.swing.JButton();
        bntActualizarTabla = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        banner = new javax.swing.JLabel();
        btnCerrarSecion = new javax.swing.JTextField();
        GenerarBoleta1 = new javax.swing.JButton();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        NuevoPedido.setBackground(new java.awt.Color(0, 0, 0));
        NuevoPedido.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        NuevoPedido.setForeground(new java.awt.Color(255, 255, 255));
        NuevoPedido.setText("Nuevo Pedido");
        NuevoPedido.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        NuevoPedido.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                NuevoPedidoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                NuevoPedidoMouseExited(evt);
            }
        });
        NuevoPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NuevoPedidoActionPerformed(evt);
            }
        });

        GenerarBoleta.setBackground(new java.awt.Color(0, 0, 0));
        GenerarBoleta.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        GenerarBoleta.setForeground(new java.awt.Color(255, 255, 255));
        GenerarBoleta.setText("Generar Factura");
        GenerarBoleta.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GenerarBoleta.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GenerarBoletaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GenerarBoletaMouseExited(evt);
            }
        });
        GenerarBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarBoletaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel2.setText("LISTA DE PEDIDOS");

        Tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Nro Pedido", "Mesa", "Cantidad", "Plato", "Descripcion", "Cliente", "Chef", "Estado", "Atendido"
            }
        ));
        Tabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jScrollPane1.setViewportView(Tabla);

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        jLabel3.setText("Buscar por Cliente");

        Txtdato.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Txtdato.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        Txtdato.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtdatoActionPerformed(evt);
            }
        });

        btnbuscar.setBackground(new java.awt.Color(0, 0, 0));
        btnbuscar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnbuscar.setForeground(new java.awt.Color(255, 255, 255));
        btnbuscar.setText("Buscar");
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnbuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnbuscarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnbuscarMouseExited(evt);
            }
        });
        btnbuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnbuscarActionPerformed(evt);
            }
        });

        bntActualizarTabla.setBackground(new java.awt.Color(0, 0, 0));
        bntActualizarTabla.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        bntActualizarTabla.setForeground(new java.awt.Color(255, 255, 255));
        bntActualizarTabla.setText("Actualizar tabla");
        bntActualizarTabla.setBorderPainted(false);
        bntActualizarTabla.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bntActualizarTabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                bntActualizarTablaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                bntActualizarTablaMouseExited(evt);
            }
        });
        bntActualizarTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntActualizarTablaActionPerformed(evt);
            }
        });

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/acceso.png"))); // NOI18N

        banner.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        banner.setForeground(new java.awt.Color(0, 153, 255));

        btnCerrarSecion.setEditable(false);
        btnCerrarSecion.setBackground(new java.awt.Color(255, 0, 51));
        btnCerrarSecion.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        btnCerrarSecion.setForeground(new java.awt.Color(255, 255, 255));
        btnCerrarSecion.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        btnCerrarSecion.setText("Cerrar sesión");
        btnCerrarSecion.setToolTipText("");
        btnCerrarSecion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnCerrarSecion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrarSecion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarSecionMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarSecionMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarSecionMouseExited(evt);
            }
        });
        btnCerrarSecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarSecionActionPerformed(evt);
            }
        });

        GenerarBoleta1.setBackground(new java.awt.Color(0, 0, 0));
        GenerarBoleta1.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        GenerarBoleta1.setForeground(new java.awt.Color(255, 255, 255));
        GenerarBoleta1.setText("Generar Boleta");
        GenerarBoleta1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        GenerarBoleta1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                GenerarBoleta1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                GenerarBoleta1MouseExited(evt);
            }
        });
        GenerarBoleta1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GenerarBoleta1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(banner, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnCerrarSecion, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addGap(39, 39, 39))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(NuevoPedido)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GenerarBoleta)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(GenerarBoleta1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(Txtdato, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btnbuscar))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1039, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(bntActualizarTabla)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap(25, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel5))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(25, 25, 25)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(banner, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnCerrarSecion, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(Txtdato, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnbuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(GenerarBoleta1, javax.swing.GroupLayout.DEFAULT_SIZE, 35, Short.MAX_VALUE)
                            .addComponent(GenerarBoleta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(NuevoPedido, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bntActualizarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 377, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void NuevoPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NuevoPedidoActionPerformed
        RegistroPedido registro=new RegistroPedido(idInciado);
        registro.setVisible(true);
    }//GEN-LAST:event_NuevoPedidoActionPerformed

    private void GenerarBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarBoletaActionPerformed
        Facturacion fact= new Facturacion();
        fact.setVisible(true);
        
    }//GEN-LAST:event_GenerarBoletaActionPerformed

    private void btnbuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnbuscarActionPerformed
        String dato=Txtdato.getText();
        Resultadobusqueda(dato);
       
    }//GEN-LAST:event_btnbuscarActionPerformed

    private void TxtdatoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtdatoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtdatoActionPerformed

    private void btnCerrarSecionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSecionMouseClicked
        Login nwlg=new Login();
        nwlg.setVisible(true);
        dispose();
    }//GEN-LAST:event_btnCerrarSecionMouseClicked

    private void btnCerrarSecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCerrarSecionActionPerformed
        Login lg=new Login();
        lg.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnCerrarSecionActionPerformed

    private void bntActualizarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntActualizarTablaActionPerformed
        Resultadobusqueda("");
    }//GEN-LAST:event_bntActualizarTablaActionPerformed

    private void GenerarBoleta1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GenerarBoleta1ActionPerformed
       Boleta boleta= new Boleta();
       boleta.setVisible(true);
    }//GEN-LAST:event_GenerarBoleta1ActionPerformed

    private void NuevoPedidoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevoPedidoMouseEntered
        colorbtnhorver(NuevoPedido);
    }//GEN-LAST:event_NuevoPedidoMouseEntered

    private void NuevoPedidoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_NuevoPedidoMouseExited
        colorbtn(NuevoPedido);
    }//GEN-LAST:event_NuevoPedidoMouseExited

    private void GenerarBoletaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerarBoletaMouseEntered
        colorbtnhorver(GenerarBoleta);
        
    }//GEN-LAST:event_GenerarBoletaMouseEntered

    private void GenerarBoletaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerarBoletaMouseExited
        colorbtn(GenerarBoleta);
    }//GEN-LAST:event_GenerarBoletaMouseExited

    private void GenerarBoleta1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerarBoleta1MouseEntered
        colorbtnhorver(GenerarBoleta1);
    }//GEN-LAST:event_GenerarBoleta1MouseEntered

    private void GenerarBoleta1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_GenerarBoleta1MouseExited
        colorbtn(GenerarBoleta1);
    }//GEN-LAST:event_GenerarBoleta1MouseExited

    private void btnbuscarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbuscarMouseEntered
        colorbtnhorver(btnbuscar);
    }//GEN-LAST:event_btnbuscarMouseEntered

    private void btnbuscarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnbuscarMouseExited
        colorbtn(btnbuscar);
    }//GEN-LAST:event_btnbuscarMouseExited

    private void bntActualizarTablaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntActualizarTablaMouseEntered
        colorbtnhorver(bntActualizarTabla);
    }//GEN-LAST:event_bntActualizarTablaMouseEntered

    private void bntActualizarTablaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bntActualizarTablaMouseExited
        
        colorbtn(bntActualizarTabla);
    }//GEN-LAST:event_bntActualizarTablaMouseExited

    private void btnCerrarSecionMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSecionMouseEntered
        btnCerrarSecion.setBackground(Color.WHITE);
        btnCerrarSecion.setForeground(Color.RED);
    }//GEN-LAST:event_btnCerrarSecionMouseEntered

    private void btnCerrarSecionMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarSecionMouseExited
        btnCerrarSecion.setBackground(Color.RED);
        btnCerrarSecion.setForeground(Color.WHITE);
    }//GEN-LAST:event_btnCerrarSecionMouseExited

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Mesero.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Mesero(0,"Sin signar",0).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton GenerarBoleta;
    private javax.swing.JButton GenerarBoleta1;
    private javax.swing.JButton NuevoPedido;
    public javax.swing.JTable Tabla;
    private javax.swing.JTextField Txtdato;
    private javax.swing.JLabel banner;
    private javax.swing.JButton bntActualizarTabla;
    private javax.swing.JTextField btnCerrarSecion;
    private javax.swing.JButton btnbuscar;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}

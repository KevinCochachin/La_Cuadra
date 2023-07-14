/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import Modelo.QuerryGraficos;
import java.awt.Color;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JTextField;

/**
 *
 * @author Kevin
 */
public class Administrador extends javax.swing.JFrame {

    /**
     * Creates new form Mesero
     */
    public Administrador() {
        initComponents();
        setIconImage(new ImageIcon(getClass().getResource("/Imagenes/Icon.png")).getImage());
        this.setTitle("Administrador");
        this.setLocationRelativeTo(null);
        seleccionarcombofecha();
        agruparbotones();
        
    }
    private void seleccionarcombofecha() {
        if (ComboElecion.getSelectedIndex() ==4) {
            ActivarFecha();
        } else {
            DesactivarFecha();
        }
    }

    private void DesactivarFecha() {
        lblHasta.setVisible(false);
        lbldesde.setVisible(false);
        JCDesde.setEnabled(false);
        JcHasta.setEnabled(false);
        JCDesde.setVisible(false);
        JcHasta.setVisible(false);
    }
    
    private void ActivarFecha() {
        lblHasta.setVisible(true);
        lbldesde.setVisible(true);
        JCDesde.setEnabled(true);
        JcHasta.setEnabled(true);
        JCDesde.setVisible(true);
        JcHasta.setVisible(true);
    }
    
    private String Obtenerfecha(){
        String FechaFinSplit;
        String Fechainicio="";
        String FechaFin="";
        //
        Date fecha=new Date();
        SimpleDateFormat formatofecha=new SimpleDateFormat("YYYY-MM-dd");
        FechaFinSplit=formatofecha.format(fecha);
        LocalDate localtime=LocalDate.parse(FechaFinSplit);
        switch (ComboElecion.getSelectedIndex()) {
            case 0:
                Fechainicio=formatofecha.format(fecha);
                FechaFin=formatofecha.format(fecha);
            break;
            case 1:
                Fechainicio=formatofecha.format(fecha);
                //restar 7 dias
                
                FechaFin=localtime.minusDays(7).toString();
            break;
            case 2:
                Fechainicio=formatofecha.format(fecha);
                //restar 15 dias
                FechaFin=localtime.minusDays(15).toString();
            break;
            case 3:
                Fechainicio=formatofecha.format(fecha);
                //restar 1 mes
                FechaFin=localtime.minusMonths(1).toString();
            break;
            case 4:
                Fechainicio=((JTextField)JcHasta.getDateEditor().getUiComponent()).getText();
                FechaFin=((JTextField)JCDesde.getDateEditor().getUiComponent()).getText();
            break;   
        }
        return Fechainicio+"separacion"+FechaFin;
    }
    
    public void agruparbotones(){
        GrupoBotones.add(btMesero);
        GrupoBotones.add(btClientesNuevos);
        GrupoBotones.add(btClientesrecurrentes);
        GrupoBotones.add(btProductosmas);
        GrupoBotones.add(btProductosmenos);
        GrupoBotones.add(btVentas);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        GrupoBotones = new javax.swing.ButtonGroup();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        btProductosmas = new javax.swing.JRadioButton();
        btMesero = new javax.swing.JRadioButton();
        btVentas = new javax.swing.JRadioButton();
        btClientesrecurrentes = new javax.swing.JRadioButton();
        btClientesNuevos = new javax.swing.JRadioButton();
        btProductosmenos = new javax.swing.JRadioButton();
        ComboElecion = new javax.swing.JComboBox<>();
        GraficarTablas = new javax.swing.JButton();
        panelGrafico = new java.awt.Panel();
        JCDesde = new com.toedter.calendar.JDateChooser();
        JcHasta = new com.toedter.calendar.JDateChooser();
        lblHasta = new javax.swing.JLabel();
        lbldesde = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        inicio = new javax.swing.JTextField();
        lista = new javax.swing.JTextField();
        cliente = new javax.swing.JTextField();
        actividades = new javax.swing.JTextField();
        cerrar = new javax.swing.JTextField();
        listapoducto = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();

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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 2));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setText("Tablas");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setText("Rango");

        btProductosmas.setText("Productos Mas Consumidos");
        btProductosmas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductosmasActionPerformed(evt);
            }
        });

        btMesero.setText("Atencion de Mozos");

        btVentas.setText("Ventas");

        btClientesrecurrentes.setText("Clientes Recurrentes");
        btClientesrecurrentes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClientesrecurrentesActionPerformed(evt);
            }
        });

        btClientesNuevos.setText("Clientes Nuevos");
        btClientesNuevos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btClientesNuevosActionPerformed(evt);
            }
        });

        btProductosmenos.setText("Productos Menos Consumidos");
        btProductosmenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btProductosmenosActionPerformed(evt);
            }
        });

        ComboElecion.setEditable(true);
        ComboElecion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoy", "Ultima semana", "Ultimos 15 dias", "Ultimo mes", "Manualmente" }));
        ComboElecion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboElecionActionPerformed(evt);
            }
        });

        GraficarTablas.setBackground(new java.awt.Color(0, 0, 0));
        GraficarTablas.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        GraficarTablas.setForeground(new java.awt.Color(255, 255, 255));
        GraficarTablas.setText("Graficar");
        GraficarTablas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GraficarTablasActionPerformed(evt);
            }
        });

        panelGrafico.setEnabled(false);

        javax.swing.GroupLayout panelGraficoLayout = new javax.swing.GroupLayout(panelGrafico);
        panelGrafico.setLayout(panelGraficoLayout);
        panelGraficoLayout.setHorizontalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        panelGraficoLayout.setVerticalGroup(
            panelGraficoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 345, Short.MAX_VALUE)
        );

        JCDesde.setToolTipText("");
        JCDesde.setDateFormatString("yyyy-MM-dd");

        JcHasta.setToolTipText("");
        JcHasta.setDateFormatString("yyyy-MM-dd");

        lblHasta.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lblHasta.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lblHasta.setText("Hasta");

        lbldesde.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbldesde.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbldesde.setText("Desde");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btMesero)
                            .addComponent(btVentas))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btProductosmas)
                            .addComponent(btProductosmenos))
                        .addGap(85, 85, 85)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(btClientesrecurrentes)
                            .addComponent(btClientesNuevos, javax.swing.GroupLayout.Alignment.LEADING))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel1))
                        .addContainerGap(875, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                                .addComponent(ComboElecion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(33, 33, 33)
                                .addComponent(lbldesde)
                                .addGap(18, 18, 18)
                                .addComponent(JCDesde, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblHasta)
                                .addGap(18, 18, 18)
                                .addComponent(JcHasta, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 197, Short.MAX_VALUE)
                                .addComponent(GraficarTablas))
                            .addComponent(panelGrafico, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(48, 48, 48))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jLabel1)
                .addGap(20, 20, 20)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(btMesero))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btProductosmas)
                            .addComponent(btClientesrecurrentes)
                            .addComponent(btVentas))
                        .addGap(20, 20, 20)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btProductosmenos)
                            .addComponent(btClientesNuevos))))
                .addGap(25, 25, 25)
                .addComponent(jLabel3)
                .addGap(15, 15, 15)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ComboElecion, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(JCDesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(JcHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbldesde, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(63, 63, 63)
                        .addComponent(panelGrafico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(GraficarTablas, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(36, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(302, 0, 970, 690));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Logo3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 230, 230));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        inicio.setEditable(false);
        inicio.setBackground(new java.awt.Color(0, 0, 0));
        inicio.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        inicio.setForeground(new java.awt.Color(255, 255, 255));
        inicio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inicio.setText("INICIO");
        inicio.setAutoscrolls(false);
        inicio.setBorder(null);
        inicio.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        inicio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inicioMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                inicioMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                inicioMouseExited(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                inicioMousePressed(evt);
            }
        });
        inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inicioActionPerformed(evt);
            }
        });

        lista.setEditable(false);
        lista.setBackground(new java.awt.Color(255, 255, 255));
        lista.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        lista.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        lista.setText("LISTA DE USUARIOS");
        lista.setBorder(null);
        lista.setCaretColor(new java.awt.Color(255, 255, 255));
        lista.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        lista.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listaMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listaMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listaMouseExited(evt);
            }
        });
        lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listaActionPerformed(evt);
            }
        });

        cliente.setEditable(false);
        cliente.setBackground(new java.awt.Color(255, 255, 255));
        cliente.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cliente.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cliente.setText("CLIENTE");
        cliente.setBorder(null);
        cliente.setCaretColor(new java.awt.Color(255, 255, 255));
        cliente.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        cliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clienteMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                clienteMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                clienteMouseExited(evt);
            }
        });
        cliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clienteActionPerformed(evt);
            }
        });

        actividades.setEditable(false);
        actividades.setBackground(new java.awt.Color(255, 255, 255));
        actividades.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        actividades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        actividades.setText("ACTIVIDADES");
        actividades.setBorder(null);
        actividades.setCaretColor(new java.awt.Color(255, 255, 255));
        actividades.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                actividadesMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                actividadesMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                actividadesMouseExited(evt);
            }
        });
        actividades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actividadesActionPerformed(evt);
            }
        });

        cerrar.setEditable(false);
        cerrar.setBackground(new java.awt.Color(255, 255, 255));
        cerrar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        cerrar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cerrar.setText("CERRAR SECCION");
        cerrar.setBorder(null);
        cerrar.setCaretColor(new java.awt.Color(255, 255, 255));
        cerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                cerrarMouseExited(evt);
            }
        });
        cerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cerrarActionPerformed(evt);
            }
        });

        listapoducto.setEditable(false);
        listapoducto.setBackground(new java.awt.Color(255, 255, 255));
        listapoducto.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        listapoducto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        listapoducto.setText("LISTA DE PRODUCTOS");
        listapoducto.setBorder(null);
        listapoducto.setCaretColor(new java.awt.Color(255, 255, 255));
        listapoducto.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        listapoducto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                listapoductoMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                listapoductoMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                listapoductoMouseExited(evt);
            }
        });
        listapoducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listapoductoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(cerrar, javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(inicio, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addComponent(lista, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addComponent(cliente, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
                        .addComponent(actividades, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE))
                    .addComponent(listapoducto, javax.swing.GroupLayout.PREFERRED_SIZE, 309, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lista, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cliente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(listapoducto, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(actividades, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(cerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38))
        );

        getContentPane().add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 290, 303, 400));

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 303, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 690, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 303, 690));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void inicioMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioMouseEntered
        inicio.setForeground(Color.white);
        inicio.setBackground(Color.black);
    }//GEN-LAST:event_inicioMouseEntered

    private void inicioMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioMouseExited
        inicio.setForeground(Color.white);
        inicio.setBackground(Color.black);
    }//GEN-LAST:event_inicioMouseExited

    private void inicioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioMousePressed

    private void inicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inicioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_inicioActionPerformed

    private void listaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseClicked
        Adm_ListaUsuario lista= new Adm_ListaUsuario();
        lista.setVisible(true);
        dispose();
    }//GEN-LAST:event_listaMouseClicked

    private void listaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseEntered
        lista.setForeground(Color.white);
        lista.setBackground(Color.black);
    }//GEN-LAST:event_listaMouseEntered

    private void listaMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listaMouseExited
        lista.setForeground(Color.black);
        lista.setBackground(Color.white);
    }//GEN-LAST:event_listaMouseExited

    private void listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listaActionPerformed

    private void clienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteMouseClicked
        Adm_Cliente cli = new Adm_Cliente();
        cli.setVisible(true);
        dispose();
    }//GEN-LAST:event_clienteMouseClicked

    private void clienteMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteMouseEntered
        cliente.setForeground(Color.white);
        cliente.setBackground(Color.black);
    }//GEN-LAST:event_clienteMouseEntered

    private void clienteMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clienteMouseExited
        cliente.setForeground(Color.black);
        cliente.setBackground(Color.white);
    }//GEN-LAST:event_clienteMouseExited

    private void clienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_clienteActionPerformed

    private void actividadesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actividadesMouseClicked
        Adm_Actividades act= new Adm_Actividades();
        act.setVisible(true);
        dispose();
    }//GEN-LAST:event_actividadesMouseClicked

    private void actividadesMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actividadesMouseEntered
        actividades.setForeground(Color.white);
        actividades.setBackground(Color.black);
    }//GEN-LAST:event_actividadesMouseEntered

    private void actividadesMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_actividadesMouseExited
        actividades.setForeground(Color.black);
        actividades.setBackground(Color.white);
    }//GEN-LAST:event_actividadesMouseExited

    private void actividadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actividadesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_actividadesActionPerformed

    private void cerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseClicked
        Login login = new Login();
        login.setVisible(true);
        dispose();
    }//GEN-LAST:event_cerrarMouseClicked

    private void cerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseEntered
        cerrar.setForeground(Color.white);
        cerrar.setBackground(Color.black);
    }//GEN-LAST:event_cerrarMouseEntered

    private void cerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cerrarMouseExited
        cerrar.setForeground(Color.black);
        cerrar.setBackground(Color.white);
    }//GEN-LAST:event_cerrarMouseExited

    private void cerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cerrarActionPerformed

    }//GEN-LAST:event_cerrarActionPerformed

    private void listapoductoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listapoductoMouseClicked
        Adm_ListaProducto lista=new Adm_ListaProducto();
        lista.setVisible(true);
        dispose();
    }//GEN-LAST:event_listapoductoMouseClicked

    private void listapoductoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listapoductoMouseEntered
        listapoducto.setForeground(Color.white);
        listapoducto.setBackground(Color.black);
    }//GEN-LAST:event_listapoductoMouseEntered

    private void listapoductoMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_listapoductoMouseExited
        listapoducto.setForeground(Color.black);
        listapoducto.setBackground(Color.white);
    }//GEN-LAST:event_listapoductoMouseExited

    private void listapoductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listapoductoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_listapoductoActionPerformed

    private void inicioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inicioMouseClicked
        Administrador adm=new Administrador();
        adm.setVisible(true);
        dispose();
    }//GEN-LAST:event_inicioMouseClicked

    private void GraficarTablasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GraficarTablasActionPerformed
        QuerryGraficos qr=new QuerryGraficos();
        String Fechaincio=Obtenerfecha().split("separacion")[1];
        String FechaFin=Obtenerfecha().split("separacion")[0];
        if (btMesero.isSelected()) {
            qr.graficarclientes(Fechaincio, FechaFin,panelGrafico);
            
        }else if(btVentas.isSelected()){
            
        }else if(btClientesrecurrentes.isSelected()){
            qr.graficarclientesConcurridos(Fechaincio, FechaFin, panelGrafico);
        }else if(btClientesNuevos.isSelected()){
            qr.graficarclientesNuevos(Fechaincio, FechaFin, panelGrafico);
        }else if(btProductosmas.isSelected()){
        }else if(btProductosmenos.isSelected()){
        }
        
        
        pack();
        repaint();
        

    }//GEN-LAST:event_GraficarTablasActionPerformed

    private void btClientesrecurrentesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClientesrecurrentesActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btClientesrecurrentesActionPerformed

    private void btProductosmasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductosmasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btProductosmasActionPerformed

    private void btProductosmenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btProductosmenosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btProductosmenosActionPerformed

    private void ComboElecionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboElecionActionPerformed
        seleccionarcombofecha();
        Obtenerfecha();
    }//GEN-LAST:event_ComboElecionActionPerformed

    private void btClientesNuevosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btClientesNuevosActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btClientesNuevosActionPerformed

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
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Administrador.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Administrador().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> ComboElecion;
    private javax.swing.JButton GraficarTablas;
    private javax.swing.ButtonGroup GrupoBotones;
    public com.toedter.calendar.JDateChooser JCDesde;
    public com.toedter.calendar.JDateChooser JcHasta;
    private javax.swing.JTextField actividades;
    private javax.swing.JRadioButton btClientesNuevos;
    private javax.swing.JRadioButton btClientesrecurrentes;
    private javax.swing.JRadioButton btMesero;
    private javax.swing.JRadioButton btProductosmas;
    private javax.swing.JRadioButton btProductosmenos;
    private javax.swing.JRadioButton btVentas;
    private javax.swing.JTextField cerrar;
    private javax.swing.JTextField cliente;
    private javax.swing.JTextField inicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JLabel lblHasta;
    private javax.swing.JLabel lbldesde;
    private javax.swing.JTextField lista;
    private javax.swing.JTextField listapoducto;
    private java.awt.Panel panelGrafico;
    // End of variables declaration//GEN-END:variables
}

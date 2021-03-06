/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ClienteController;
import controller.RegistroVentaController;
import controller.TipoCambioController;
import entity.Cliente;
import entity.RegistroVenta;
import entity.TipoCambio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reportes.GenerarReporte;

/**
 *
 * @author ARCRODINPC-05
 */

public class CuentasPorCobrar extends javax.swing.JInternalFrame {

    /**
     * Creates new form CuentasPorCobrar
     */
    String filtro="c.razonSocial";
    int tabla;
    int idCliente;
    String razSocial;
    int idRegistroVenta;
    int idRegistroVentaTabla;
    static int idRegistroVentaStatic;
    String numero, serie;
    float cambioVenta;
    static String serieStaticCobro,numeroStaticCobro;
    ClienteController clientecontroler=new ClienteController();
    Cliente cliente;
    
    public CuentasPorCobrar() {
        initComponents();
        listaVentasCobrar(filtro, "%"+txtLike.getText()+"%");
        habilitarInicio();
    }

    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtLike = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        comboBoxFiltro = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        buttonReporteVencidos = new javax.swing.JButton();
        buttonImprimirReporte = new javax.swing.JButton();
        buttonRegistroVenta = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        buttonLimpiarInformacion = new javax.swing.JButton();
        txtRazSocialReport = new javax.swing.JTextField();
        buttonReporteTotal = new javax.swing.JButton();
        buttonImprimirTotal = new javax.swing.JButton();
        labelComprobante = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtNumero = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtNumCotizacion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtNumPedido = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Cuentas por cobrar");

        txtLike.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtLikeKeyReleased(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idCliente", "Razón Social", "R.U.C.", "Monto Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(530);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        comboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "RAZÓN SOCIAL", "FACTURA" }));
        comboBoxFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxFiltroItemStateChanged(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idRVenta", "Factura", "F. Emisión", "Monto $", "Monto S/", "F. Vencimiento", "Deuda", "Observación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(120);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(110);
            jTable2.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTable2.getColumnModel().getColumn(5).setPreferredWidth(110);
            jTable2.getColumnModel().getColumn(6).setPreferredWidth(110);
            jTable2.getColumnModel().getColumn(7).setPreferredWidth(220);
        }

        buttonReporteVencidos.setText("Reporte Vencidos");
        buttonReporteVencidos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonReporteVencidosActionPerformed(evt);
            }
        });

        buttonImprimirReporte.setText("Imprimir Vencidos");
        buttonImprimirReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirReporteActionPerformed(evt);
            }
        });

        buttonRegistroVenta.setText("Ver Registro Venta");
        buttonRegistroVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistroVentaActionPerformed(evt);
            }
        });

        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton6.setText("Limpiar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel16.setText("Todo comprobante para que no esté en la lista de cuentas por cobrar su estado debe ser \"CANCELADO\" o lo que usted considere. Para ver la lista de Estado, revisar la Vista de Ventas.");

        jButton1.setText("Vista Total Comprobantes");

        jButton2.setText("Vista Comprobantes Vencidos");

        buttonLimpiarInformacion.setText("Limpiar");

        buttonReporteTotal.setText("Reporte Total");

        buttonImprimirTotal.setText("Imprimir Total");

        labelComprobante.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelComprobante.setText("COMPROBANTE");

        jLabel2.setText("Serie:");

        jLabel3.setText("Número:");

        jLabel4.setText("N° Cot.:");

        jLabel5.setText("N° Pedido:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addGap(3, 3, 3)
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(jLabel4)
                                        .addGap(2, 2, 2)
                                        .addComponent(txtNumCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addGap(3, 3, 3)
                                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(98, 98, 98)
                                        .addComponent(jButton4))
                                    .addComponent(buttonRegistroVenta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonImprimirReporte, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonReporteVencidos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonReporteTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(buttonImprimirTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtLike, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 901, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel16))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(txtRazSocialReport, javax.swing.GroupLayout.PREFERRED_SIZE, 359, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)
                        .addComponent(buttonLimpiarInformacion, javax.swing.GroupLayout.PREFERRED_SIZE, 127, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(191, 191, 191))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel16)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLike, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(buttonLimpiarInformacion)
                    .addComponent(txtRazSocialReport, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelComprobante)
                            .addComponent(jLabel2)
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4)
                            .addComponent(txtNumCotizacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonReporteTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonReporteVencidos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonImprimirTotal)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonImprimirReporte)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistroVenta)
                        .addGap(23, 23, 23)
                        .addComponent(jButton4)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        limpiarTabla();
        tabla = jTable1.getSelectedRow();
        idCliente = Integer.parseInt(jTable1.getValueAt(tabla, 1).toString());
        razSocial = jTable1.getValueAt(tabla, 5).toString();
        txtRazSocialReport.setText(razSocial);
        listaVentasCobrarReporte("c.razonSocial", razSocial);
    }//GEN-LAST:event_jTable1MouseClicked

    private void comboBoxFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxFiltroItemStateChanged
        if (comboBoxFiltro.getSelectedItem() == "RAZÓN SOCIAL") {
            filtro = "c.razonSocial";
            listaVentasCobrar(filtro, "%" + txtLike.getText() + "%");
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "FACTURA") {
            filtro = "concat(rv.serie,'-', rv.numero)";
            listaVentasCobrar(filtro, "%" + txtLike.getText() + "%");
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "SELECCIONAR") {
            filtro = "c.razonSocial";
            listaVentasCobrar(filtro, "%" + txtLike.getText() + "%");
            System.out.println(filtro);
        }
    }//GEN-LAST:event_comboBoxFiltroItemStateChanged

    private void txtLikeKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtLikeKeyReleased
        listaVentasCobrar(filtro,"%"+ txtLike.getText()+"%");
    }//GEN-LAST:event_txtLikeKeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        limpiarInformacion();
        tabla = jTable2.getSelectedRow();
        idRegistroVentaTabla = Integer.parseInt(jTable2.getValueAt(tabla, 0).toString());
        try {
            consultarRegistroVenta(idRegistroVentaTabla);
        } catch (Exception ex) {
            Logger.getLogger(CuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable2MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        limpiarTabla();
        habilitarInicio();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_UP)) {
            limpiarTabla();
            tabla = jTable1.getSelectedRow();
            idCliente = Integer.parseInt(jTable1.getValueAt(tabla, 1).toString());
            razSocial = jTable1.getValueAt(tabla, 5).toString();
            txtRazSocialReport.setText(razSocial);
            listaVentasCobrarReporte("c.razonSocial", razSocial);
        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void buttonReporteVencidosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonReporteVencidosActionPerformed
         GenerarReporte cuentasReport= new GenerarReporte();
        try {
            cuentasReport.ReporteCuentasCobrar(razSocial, "EN PROCESO");
        } catch (SQLException ex) {
            Logger.getLogger(CuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonReporteVencidosActionPerformed

    private void buttonImprimirReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirReporteActionPerformed
        GenerarReporte cuentasReport= new GenerarReporte();
        try {
            cuentasReport.ImprimirCuentasCobrar(razSocial, "EN PROCESO");
        } catch (SQLException ex) {
            Logger.getLogger(CuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(CuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonImprimirReporteActionPerformed

    private void buttonRegistroVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistroVentaActionPerformed
        RegistroVentas reVet = null;
        try {
            reVet = new RegistroVentas();
        } catch (ParseException ex) {
            Logger.getLogger(CuentasPorCobrar.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(reVet);
        reVet.toFront();
        reVet.setVisible(true);
        RegistroVentas.serieYNumeroDeVentaDeCuentasPorCobrar();
    }//GEN-LAST:event_buttonRegistroVentaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonImprimirReporte;
    private javax.swing.JButton buttonImprimirTotal;
    private javax.swing.JButton buttonLimpiarInformacion;
    private javax.swing.JButton buttonRegistroVenta;
    private javax.swing.JButton buttonReporteTotal;
    private javax.swing.JButton buttonReporteVencidos;
    private javax.swing.JComboBox<String> comboBoxFiltro;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelComprobante;
    private javax.swing.JTextField txtLike;
    private javax.swing.JTextField txtNumCotizacion;
    private javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextField txtNumero;
    private javax.swing.JTextField txtRazSocialReport;
    private javax.swing.JTextField txtSerie;
    // End of variables declaration//GEN-END:variables
 
 RegistroVentaController registroventacontroler=new RegistroVentaController();
 RegistroVenta registroventa;
 
 TipoCambioController tipocambiocontroler = new TipoCambioController();
 TipoCambio tipocambio;
 
//--------------------------------------- Tabla 1 -----------------------------------------
    void listaVentasCobrar(String nombreFiltro, String valorFiltro) {
        List<RegistroVenta> lista;
        try {
            lista = registroventacontroler.RegistroVentaCuentasCobrar(nombreFiltro, valorFiltro);
            verVentas(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    void verVentas(List<RegistroVenta> lista) throws Exception {
        
        DefaultTableModel tabla1 = (DefaultTableModel) jTable1.getModel();
        tabla1.setRowCount(0);
        for (RegistroVenta reve : lista) {
            
            Object[] fila = {reve.getIdRegistroVenta(), reve.getIdCliente(),        reve.getTipoComprobante(),
                             reve.getFechaEmision(),    reve.getNumComprobante(),   reve.getRazonSocial(), 
                             reve.getNumPedido(),       reve.getFechaVencimiento(), reve.getTipo(), 
                             reve.getMoneda(),          reve.getMontoFacturado(),   reve.getDetraccion(), 
                             reve.getAbonado(),         reve.getDeuda()};
            tabla1.addRow(fila);
        }
    }
    
//--------------------------------------- Tabla 2 -----------------------------------------
    void listaVentasCobrarReporte(String nombreFiltro, String valorFiltro) {
        List<RegistroVenta> lista;
        try {
            lista = registroventacontroler.RegistroVentaCuentasCobrar(nombreFiltro, valorFiltro);
            verCobroReporte(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    void verCobroReporte(List<RegistroVenta> lista) throws Exception {
        String dol, sol;
        DefaultTableModel tabla1 = (DefaultTableModel) jTable2.getModel();
        tabla1.setRowCount(0);
        for (RegistroVenta reve : lista) {
            if ("SOLES".equals(reve.getMoneda())) {
                sol = reve.getMontoFacturado();
            } else {
                sol = "00.00";
            }
            if ("DOLARES AMERICANOS".equals(reve.getMoneda())) {
                dol = reve.getMontoFacturado();
            } else {
                dol = "00.00";
            }
                       
            Object[] fila = {reve.getIdRegistroVenta(),reve.getNumComprobante(),
                             reve.getFechaEmision(),dol,sol,reve.getFechaVencimiento(),
                             reve.getDeuda(), reve.getObservacion()};
            tabla1.addRow(fila);
        }
    }
    
//--------------------------- Consultar Registro Venta -------------------------
    
     private void consultarRegistroVenta(int id) throws Exception {
        registroventa = registroventacontroler.RegistroVentaPorId(id);
        if (registroventa != null) {
            
            
            idRegistroVenta=registroventa.getIdRegistroVenta();
            idRegistroVentaStatic=registroventa.getIdRegistroVenta();
            labelComprobante.setText(registroventa.getTipoComprobante());
            serie=registroventa.getSerie();
            txtSerie.setText(registroventa.getSerie());
            serieStaticCobro=registroventa.getSerie();
            numero=registroventa.getNumero();
            txtNumero.setText(registroventa.getNumero());
            numeroStaticCobro=registroventa.getNumero();
            txtNumCotizacion.setText(registroventa.getCodProforma());
            txtNumPedido.setText(registroventa.getNumPedido());
            consultarTipoCambio(registroventa.getFechaEmision());
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Pedido no registrado");
           
            System.out.println("Pedido no registrado");
        }
    }
     
     void consultarTipoCambio(String f) throws Exception {
        tipocambio =tipocambiocontroler.TipoCambioBuscarCambio(f) ;

        if (tipocambio != null) {
            cambioVenta=tipocambio.getPrecioVenta();
        } else {
//            JOptionPane.showMessageDialog(null, "tipo cambio no registradas");
            System.out.println("Error");
        }
    }
    

//------------------------------------ Limpiar ---------------------------------
     
   void limpiarInformacion(){
       labelComprobante.setText("COMPROBANTE");
       txtSerie.setText("");
       txtNumero.setText("");
       txtNumCotizacion.setText("");
       txtNumPedido.setText("");
   }
   
   void limpiarTabla(){
       labelComprobante.setText("COMPROBANTE");
       txtSerie.setText("");
       txtNumero.setText("");
       txtNumCotizacion.setText("");
       txtNumPedido.setText("");
       listaVentasCobrarReporte(filtro,"");
   }
   
//------------------------- Habilitar ----------------------------------
   void habilitarInicio(){
       
       labelComprobante.setEnabled(false);
       txtSerie.setEnabled(false);
       txtNumero.setEnabled(false);
       txtNumCotizacion.setEnabled(false);
       txtNumPedido.setEnabled(false);
       
       buttonReporteVencidos.setEnabled(false);
       buttonImprimirReporte.setEnabled(false);
       buttonRegistroVenta.setEnabled(false);
       buttonLimpiarInformacion.setEnabled(false);
   }
   
   void habilitarBuscar(){
   
       labelComprobante.setEnabled(false);
       txtSerie.setEnabled(false);
       txtNumero.setEnabled(false);
       txtNumCotizacion.setEnabled(false);
       txtNumPedido.setEnabled(false);
       
       buttonReporteVencidos.setEnabled(false);
       buttonImprimirReporte.setEnabled(false);
       buttonRegistroVenta.setEnabled(false);
       buttonLimpiarInformacion.setEnabled(false);
   }

}

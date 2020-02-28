/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ClienteController;
import controller.DireccionAnexaController;
import entity.Cliente;
import entity.DireccionAnexa;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARCRODINPC-06
 */
public class DireccionAGuiaRemision extends javax.swing.JInternalFrame {

    /**
     * Creates new form DireccionAGuiaRemision
     */
    int tabla;
    String dirNexa;
    static String direc;
    
    
    
    public DireccionAGuiaRemision() throws Exception {
        initComponents();
        System.out.println("idCliente: "+GuiaRemision.idCliente);    
        consultarClientePorId(GuiaRemision.idCliente);
        listaDireccionAnexa(GuiaRemision.idCliente);
        direc=txtDirFiscal.getText();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtRazSocial = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtDirFiscal = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtDireccion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        buttonLimpiar = new javax.swing.JButton();
        buttonAgregarAGuia = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();
        buttonAgregarDireccion = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setTitle("Dirección Fiscal y Direcciones Anexas");

        jLabel1.setText("Razón Social:");

        jLabel2.setText("Dirección Fiscal:");

        txtDirFiscal.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtDirFiscalMouseClicked(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id", "Dirección", "Observación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(140);
        }

        jLabel4.setText("Dirección para Guia de Remisión:");

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        buttonAgregarAGuia.setText("Agregar a Guia");
        buttonAgregarAGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarAGuiaActionPerformed(evt);
            }
        });

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        buttonAgregarDireccion.setText("Direcciones Anexas:");
        buttonAgregarDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarDireccionActionPerformed(evt);
            }
        });

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonCerrar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 521, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel4)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addComponent(txtDirFiscal, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 512, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(buttonLimpiar)
                        .addGap(83, 83, 83)
                        .addComponent(buttonAgregarAGuia)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(buttonAgregarDireccion))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDirFiscal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonAgregarDireccion)
                .addGap(8, 8, 8)
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAgregarAGuia)
                    .addComponent(buttonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCerrar)
                .addGap(14, 14, 14))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tabla = jTable1.getSelectedRow();
        dirNexa =jTable1.getValueAt(tabla, 1).toString();
        txtDireccion.setText(dirNexa);
        direc=dirNexa;
        System.out.println("direccion para guia: "+direc);
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        Limpiar();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void txtDirFiscalMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtDirFiscalMouseClicked
        txtDireccion.setText(txtDirFiscal.getText());
        direc=txtDirFiscal.getText();
    }//GEN-LAST:event_txtDirFiscalMouseClicked

    private void buttonAgregarAGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarAGuiaActionPerformed
        GuiaRemision.direccionAGuia();
        dispose();
    }//GEN-LAST:event_buttonAgregarAGuiaActionPerformed

    private void buttonAgregarDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarDireccionActionPerformed
         Clientes.x=GuiaRemision.idCliente;
         if (Clientes.x!= 0) {
            DireccionesAnexasRegistrar dirCliente=null;
             try {
                 dirCliente = new DireccionesAnexasRegistrar();
             } catch (Exception ex) {
                 Logger.getLogger(DireccionAGuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
             }
            Principal.jDesktopPane1.add(dirCliente);
            dirCliente.toFront();
            dirCliente.setVisible(true);
            
        } else {
            JOptionPane.showMessageDialog(null, "Agregar Cliente");

        }
         
    }//GEN-LAST:event_buttonAgregarDireccionActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       listaDireccionAnexa(GuiaRemision.idCliente);
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregarAGuia;
    private javax.swing.JButton buttonAgregarDireccion;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    private javax.swing.JTextField txtDirFiscal;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtRazSocial;
    // End of variables declaration//GEN-END:variables

    ClienteController clientecontroler=new ClienteController();
    Cliente cliente;
    
   static DireccionAnexaController direccioncontroler= new DireccionAnexaController();
   static DireccionAnexa direccion;
    
    private void consultarClientePorId(int iddecliente) throws Exception {
        cliente = clientecontroler.ClienteBuscar1(iddecliente);
        if (cliente != null) {

            //txtRazonSocial.setText(pro.getRazonSocial());
            txtRazSocial.setText(cliente.getRazonSocial());
            txtDirFiscal.setText(cliente.getDireccion());
            txtDireccion.setText(cliente.getDireccion());
            
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no registrado por ahora");
            //System.out.println("Error");
        }
    }

    
    void listaDireccionAnexa(int idCliente) {
        List<DireccionAnexa> lista;
        try {
            
            lista = direccioncontroler.DireccionAnexaListar(idCliente); 
            verDireccion(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    void verDireccion(List<DireccionAnexa> lista) {

        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (DireccionAnexa dir : lista) {
            Object[] fila = {dir.getIdDireccion(),dir.getDireccion(),dir.getObser()};
            tabla.addRow(fila);
        }
    }

void Limpiar(){
    txtDireccion.setText("");
}


}

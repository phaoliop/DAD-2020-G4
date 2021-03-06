/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.RubroController;
import entity.Rubro;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARCRODINPC-06
 */
public class Rubros extends javax.swing.JInternalFrame {

    /**
     * Creates new form Rubros
     */
    int idRub;
    
    int tabla;
    String idRubT;
    
    public Rubros() {
        initComponents();
        listaRubro();
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

        jLabel1 = new javax.swing.JLabel();
        labelIdRubro = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtRubro = new javax.swing.JTextField();
        buttonLimpiar = new javax.swing.JButton();
        buttonNuevo = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton7 = new javax.swing.JButton();

        jLabel1.setText("N°:");

        labelIdRubro.setText("000000");

        jLabel3.setText("Rubro:");

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        buttonNuevo.setText("Nuevo");
        buttonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoActionPerformed(evt);
            }
        });

        buttonRegistrar.setText("Registrar");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
            }
        });

        buttonModificar.setText("Modificar");
        buttonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });

        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idRubro", "Rubro"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(480);
        }

        jButton7.setText("Cerrar");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 516, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelIdRubro))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(buttonLimpiar))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonNuevo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRegistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonEliminar)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton7)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelIdRubro))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNuevo)
                    .addComponent(buttonRegistrar)
                    .addComponent(buttonModificar)
                    .addComponent(buttonGuardar)
                    .addComponent(buttonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton7)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
       dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        habilitarNuevo();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        procesar(1);
        listaRubro();
        limpiar();
        habilitarInicio();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        procesar(2);
        listaRubro();
        habilitarGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        procesar(3);
        listaRubro();
        habilitarEliminar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
       tabla = jTable1.getSelectedRow();
       idRubT= jTable1.getValueAt(tabla, 0).toString();
       idRub=Integer.parseInt(idRubT); 
        try {
            consultar(idRub);
        } catch (Exception ex) {
            Logger.getLogger(Rubros.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscar();
       
    }//GEN-LAST:event_jTable1MouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton jButton7;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelIdRubro;
    private javax.swing.JTextField txtRubro;
    // End of variables declaration//GEN-END:variables
    RubroController rubrocontroler=new RubroController();
    Rubro rubro;
    
    //------------------------- Procesar ---------------------------------------------------------    
    private void procesar(int op) {
       rubro=leerDatos();
        try {
            String msg=rubrocontroler.RubroProcesar(rubro, op);
            JOptionPane.showMessageDialog(null, msg);            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
             System.out.println("Error"+e.getMessage());
        }       
   }
    
   private Rubro leerDatos() {
        Rubro rub = new Rubro();
        
        rub.setIdRubro(idRub);
        rub.setDescripcion(txtRubro.getText());
       
        return rub;
    }
   
   // ------------------------------ Listar Tabla ----------------------------------------
    
    void listaRubro() {
        List<Rubro> lista;
        try {
            
            lista = rubrocontroler.RubroListar();
            verRubro(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

     private void verRubro(List<Rubro> lista) {

        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (Rubro rub : lista) {
            Object[] fila = {rub.getIdRubro(),rub.getDescripcion()};
            tabla.addRow(fila);
        }
    }
     
 //------------------------ Consulta ------------------------------------
     private void consultar(int id) throws Exception {
      rubro = rubrocontroler.RubroBuscar(id);
        if ( rubro!= null) {

            labelIdRubro.setText(rubro.getIdRubro() +"");
            idRub=rubro.getIdRubro();
            txtRubro.setText(rubro.getDescripcion());
                                   
        } else {
            JOptionPane.showMessageDialog(null, "Rubro no registrado");
            //System.out.println("Error");
        }
    }
     
     
//------------------------ Habilitar ---------------------------
     
      void habilitarInicio(){
         
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        
        txtRubro.setEnabled(false);
    }
     
     void habilitarBuscar(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(true);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(true);

         txtRubro.setEnabled(false);
         
     }
     
     void habilitarNuevo(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(true);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(false);

         txtRubro.setEnabled(true);
     }
     
     void habilitarRegistrar(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(true);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(true);

         txtRubro.setEnabled(false);
     }
     
     void habilitarModificar(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(true);
         buttonEliminar.setEnabled(false);

         txtRubro.setEnabled(true);
     }
     
     void habilitarGuardar(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(true);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(true);

         txtRubro.setEnabled(false);
     }
     
     void habilitarEliminar(){
         
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        
        txtRubro.setEnabled(false);
     }
     
     void limpiar(){
     
         txtRubro.setText("");
         labelIdRubro.setText("000000");
     }
}

    


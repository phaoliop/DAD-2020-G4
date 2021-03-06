/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.DetallePedidoController;
import entity.DetallePedido;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ARCRODINPC-02
 */
public class DetallePedidos extends javax.swing.JInternalFrame {
    
  static String Descripcion;
  static int idDetaPedido;

    /**
     * Creates new form DetallePedido
     */
    public DetallePedidos() {
        initComponents();
        System.out.println("idPedido:" + Pedidos.idPedParaDetalle); // idPedido
        labelNumPedido.setText(Integer.toString(Pedidos.numPedParaDetalle));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        labelNumPedido = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jlabelnombrePrecio = new javax.swing.JLabel();
        txtItem = new javax.swing.JTextField();
        txtCantidad = new javax.swing.JTextField();
        txtDescripcion = new javax.swing.JTextField();
        txtPrecioUnitario = new javax.swing.JTextField();
        buttonCerrar = new javax.swing.JButton();
        buttonLimpiar = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetDescrip = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();

        jLabel2.setText("Num. Pedido:");

        labelNumPedido.setText("000000");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel9.setText("Item:");

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel10.setText("Cantidad:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel11.setText("Descripcion:");

        jLabel12.setText("Cuadro de información");

        jlabelnombrePrecio.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jlabelnombrePrecio.setText("Precio Unitario:");

        txtCantidad.setText(" ");

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
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

        txtDetDescrip.setColumns(20);
        txtDetDescrip.setRows(5);
        jScrollPane1.setViewportView(txtDetDescrip);

        jLabel1.setText("interna");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel11)
                                    .addComponent(jlabelnombrePrecio)
                                    .addComponent(jLabel12)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jLabel1)
                                .addGap(42, 42, 42)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(buttonLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonGuardar)
                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jlabelnombrePrecio)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(33, 33, 33)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(labelNumPedido)
                                    .addComponent(jLabel2))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonEliminar)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 32, Short.MAX_VALUE)
                        .addComponent(buttonCerrar)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
//        procesar(1);
////        Pedidos.listaDetallePedido(Pedidos.idPedParaDetalle);
////      try {
////          Pedidos.consultarPago(Pedidos.idPedParaDetalle);
////      } catch (Exception ex) {
////          Logger.getLogger(DetallePedidos.class.getName()).log(Level.SEVERE, null, ex);
////      }
//      habilitarRegistrar();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
//        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
//        procesar(2);   
//        Pedidos.listaDetallePedido(Pedidos.idPedParaDetalle);
//      try {
//          Pedidos.consultarPago(Pedidos.idPedParaDetalle);
//      } catch (Exception ex) {
//          Logger.getLogger(DetallePedidos.class.getName()).log(Level.SEVERE, null, ex);
//      }
//      habilitarGuardar();
      dispose();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        System.out.println("idDetallePedido:" + idDetaPedido);
        if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
//            procesar(3);
//            Pedidos.listaDetallePedido(Pedidos.idPedParaDetalle);
//            try {
//                Pedidos.consultarPago(Pedidos.idPedParaDetalle);
//            } catch (Exception ex) {
//                Logger.getLogger(DetallePedidos.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            habilitarEliminar();
        }
        dispose();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
//        limpiar();
//        deshabilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar;
    public static javax.swing.JButton buttonEliminar;
    public static javax.swing.JButton buttonGuardar;
    public static javax.swing.JButton buttonLimpiar;
    public static javax.swing.JButton buttonModificar;
    public static javax.swing.JButton buttonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jlabelnombrePrecio;
    private javax.swing.JLabel labelNumPedido;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextArea txtDetDescrip;
    public static javax.swing.JTextField txtItem;
    public static javax.swing.JTextField txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables
 
    static DetallePedidoController obj = new DetallePedidoController();
    static DetallePedido pro;
    
// ---------------------------- Procesar --------------------------------    
//        private void procesar(int op) {
//        pro = leerDatos();
//        try {
//            String msg = obj.DetallePedidoProcesar(pro, op);
//            JOptionPane.showMessageDialog(null, msg);
//        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            System.out.println("Error" + e.getMessage());
//        }
//    }
        
 // ------------------------ Consultar -------------------------
     
//        static void consultar(int id, int idDetallePedido) throws Exception {
//                
//        pro = obj.DetallePedidoBuscar(id,idDetallePedido);
//        if (pro != null) {
            
//            idDetaPedido=pro.getIdDetallePedido();
//            System.out.println("idDetaPedidoConsultar:"+idDetaPedido);
//            Pedidos.idPedParaDetalle=pro.getIdPedido();
//            txtItem.setText(pro.getItem());
//            txtCantidad.setText(pro.getCantidad());
//            txtDescripcion.setText(pro.getDescripcion());
//            txtDetDescrip.setText(pro.getDetalleDescrip());
//            txtPrecioUnitario.setText(pro.getPrecioUnitario());

//        } else {
//            JOptionPane.showMessageDialog(null, "Proveedor no registrado");
//            //System.out.println("Error");
//        }
//    }


// -----------------------------Leer Datos ----------------------------------        
//    private DetallePedido leerDatos() {
//
//        DetallePedido detPed = new DetallePedido();
//        
        
//        detPed.setIdPedido(Pedidos.idPedParaDetalle);
//        detPed.setItem(txtItem.getText());
//        detPed.setCantidad(txtCantidad.getText());
//        detPed.setDescripcion(txtDescripcion.getText());
//        detPed.setDetalleDescrip(txtDetDescrip.getText());
//        detPed.setPrecioUnitario(txtPrecioUnitario.getText());
//        detPed.setIdDetallePedido(idDetaPedido);

//        return detPed;
//    }
    
   
//-------------------------- Limpiar ---------------------------------
//    public void limpiar() {
//        txtCantidad.setText("");
//        txtDescripcion.setText("");
//        txtDetDescrip.setText("");
//        txtItem.setText("");
//        txtPrecioUnitario.setText("");
//    }

//-------------------------- Habilitar --------------------
    
//    void deshabilitarInicio(){
//        buttonLimpiar.setEnabled(true);
//        buttonRegistrar.setEnabled(true);
//        buttonModificar.setEnabled(false);
//        buttonGuardar.setEnabled(false);
//        buttonEliminar.setEnabled(false);
//        
//        
//        txtItem.setEnabled(true);
//        txtCantidad.setEnabled(true);
//        txtDescripcion.setEnabled(true);
//        txtDetDescrip.setEnabled(true);
//        txtPrecioUnitario.setEnabled(true);
//    }
//    
//     public void habilitarRegistrar(){
//        
//        buttonLimpiar.setEnabled(true);
//        buttonRegistrar.setEnabled(false);
//        buttonModificar.setEnabled(true);
//        buttonGuardar.setEnabled(false);
//        buttonEliminar.setEnabled(true);        
//        
//        txtItem.setEnabled(false);
//        txtCantidad.setEnabled(false);
//        txtDescripcion.setEnabled(false);
//        txtDetDescrip.setEnabled(false);
//        txtPrecioUnitario.setEnabled(false);
//    }
//     
//    public void habilitarEliminar(){
//        
//        buttonLimpiar.setEnabled(true);
//        buttonRegistrar.setEnabled(false);
//        buttonModificar.setEnabled(false);
//        buttonGuardar.setEnabled(false);
//        buttonEliminar.setEnabled(false);
//        
//        txtItem.setEnabled(false);
//        txtCantidad.setEnabled(false);
//        txtDescripcion.setEnabled(false);
//        txtDetDescrip.setEnabled(false);
//        txtPrecioUnitario.setEnabled(false);
//    }
//         
//    public void habilitarModificar(){
//        
//        buttonLimpiar.setEnabled(true);
//        buttonRegistrar.setEnabled(false);
//        buttonModificar.setEnabled(false);
//        buttonGuardar.setEnabled(true);
//        buttonEliminar.setEnabled(false);
//        
//        txtItem.setEnabled(true);
//        txtCantidad.setEnabled(true);
//        txtDescripcion.setEnabled(true);
//        txtDetDescrip.setEnabled(true);
//        txtPrecioUnitario.setEnabled(true);
//    }
//    
//    public void habilitarGuardar(){
//        
//        buttonLimpiar.setEnabled(true);
//        buttonRegistrar.setEnabled(false);
//        buttonModificar.setEnabled(true);
//        buttonGuardar.setEnabled(false);
//        buttonEliminar.setEnabled(true);
//        
//        txtItem.setEnabled(false);
//        txtCantidad.setEnabled(false);
//        txtDescripcion.setEnabled(false);
//        txtDetDescrip.setEnabled(false);
//        txtPrecioUnitario.setEnabled(false);
//    }
//    
//        static void habilitarTabla(){
//        
//        buttonLimpiar.setEnabled(true);
//        buttonRegistrar.setEnabled(false);
//        buttonModificar.setEnabled(true);
//        buttonGuardar.setEnabled(false);
//        buttonEliminar.setEnabled(true);
//        
//        txtItem.setEnabled(false);
//        txtCantidad.setEnabled(false);
//        txtDescripcion.setEnabled(false);
//        txtDetDescrip.setEnabled(false);
//        txtPrecioUnitario.setEnabled(false);
//    }
}
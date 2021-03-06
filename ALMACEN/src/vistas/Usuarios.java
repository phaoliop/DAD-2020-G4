/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.UsuarioController;
import entity.Usuario;
import exportarexcel.clsExportarExcel;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARCRODINPC-02
 */
public final class Usuarios extends javax.swing.JInternalFrame {

    /**
     * Creates new form pRUEBA
     */
    int idUsuario;
    int tabla;
    
    public Usuarios() {
        initComponents();
        habilitarInicio();
        listaUsuario();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        password = new javax.swing.JPasswordField();
        txtNombres = new javax.swing.JTextField();
        txtApellidos = new javax.swing.JTextField();
        txtCargo = new javax.swing.JTextField();
        txtCorreo = new javax.swing.JTextField();
        txtTlf = new javax.swing.JTextField();
        txtUsuario = new javax.swing.JTextField();
        labelIdUsuario = new javax.swing.JLabel();
        buttonLimpiar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonNuevo = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonExportExcel = new javax.swing.JButton();

        setClosable(true);
        setTitle("Usuarios");

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

        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        jLabel1.setText("N°:");

        jLabel2.setText("Nombres:");

        jLabel3.setText("Apellidos:");

        jLabel4.setText("Cargo: ");

        jLabel5.setText("Telefono: ");

        jLabel6.setText("Correo: ");

        jLabel7.setText("Usuario: ");

        jLabel8.setText("Password:");

        password.setText("jPasswordField1");

        labelIdUsuario.setText("0");

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
            }
        });

        buttonNuevo.setText("Nuevo");
        buttonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idUs", "Nombres", "Apellidos", "Telefono", "Correo", "Cargo", "Usuario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(95);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(145);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(117);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(110);
        }

        buttonExportExcel.setText("ExportExcel");
        buttonExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportExcelActionPerformed(evt);
            }
        });

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
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelIdUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel7)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel5))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel3))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtUsuario)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel8))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel6)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtApellidos, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtCorreo, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(password, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(59, 59, 59)
                                                .addComponent(buttonLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(6, 6, 6)
                                        .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonRegistrar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonModificar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 4, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 786, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonCerrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelIdUsuario))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(txtApellidos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(password, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(txtUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegistrar)
                    .addComponent(buttonEliminar)
                    .addComponent(buttonNuevo)
                    .addComponent(buttonGuardar)
                    .addComponent(buttonModificar)
                    .addComponent(buttonExportExcel))
                .addGap(23, 23, 23)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCerrar)
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        String texto = txtNombres.getText();
        texto = texto.replaceAll(" ", "");
        if (texto.length() == 0) {
            JOptionPane.showMessageDialog(null, "Llenar los campos");
            habilitarInicio();

        } else {
            procesar(1);
            limpiar();
            habilitarRegistrar();
            listaUsuario();
        } 
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            procesar(3);
            listaUsuario();
            limpiar();
            habilitarEliminar();
        }
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
        habilitarInicio();

    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        procesar(2);
        try {
            consultar(idUsuario);
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaUsuario();
        habilitarGuardar();
        
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        // TODO add your handling code here:
        limpiar();
        habilitarNuevo();
  
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        limpiar();
        tabla = jTable1.getSelectedRow();
       idUsuario= Integer.parseInt(jTable1.getValueAt(tabla, 0).toString());
        try { 
            consultar(idUsuario);
        } catch (Exception ex) {
            Logger.getLogger(Usuarios.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscar();
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportExcelActionPerformed
        clsExportarExcel objeto = new clsExportarExcel();
        try {
            objeto.exportarExcel(jTable1);
        } catch (IOException ex) {
            Logger.getLogger(CajaChicaRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonExportExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonExportExcel;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelIdUsuario;
    private javax.swing.JPasswordField password;
    private javax.swing.JTextField txtApellidos;
    private javax.swing.JTextField txtCargo;
    private javax.swing.JTextField txtCorreo;
    private javax.swing.JTextField txtNombres;
    private javax.swing.JTextField txtTlf;
    private javax.swing.JTextField txtUsuario;
    // End of variables declaration//GEN-END:variables

    UsuarioController obj = new UsuarioController();
    Usuario pro;
    
    private void procesar(int op) {
        pro = leerDatos();
        try {
            String msg = obj.UsuarioProcesar(pro, op);
            JOptionPane.showMessageDialog(null, msg);            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }        
    }
    
    private void consultar(int idU) throws Exception {
        pro = obj.UsuarioBuscarPorId(idU);
        if (pro != null) {
            
            labelIdUsuario.setText(pro.getIdUsuario() + "");
            idUsuario=pro.getIdUsuario();
            txtNombres.setText(pro.getNombres());
            txtApellidos.setText(pro.getApellidos());
            txtTlf.setText(pro.getTelefono());
            txtCorreo.setText(pro.getCorreo());
            txtCargo.setText(pro.getCargo());
            txtUsuario.setText(pro.getUsuario());            
            password.setText(pro.getPassword());            
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }
    
    private Usuario leerDatos() {
        Usuario us = new Usuario();
        
        us.setIdUsuario(idUsuario);
        us.setNombres(txtNombres.getText());
        us.setApellidos(txtApellidos.getText());
        us.setTelefono(txtTlf.getText());
        us.setCorreo(txtCorreo.getText());
        us.setCargo(txtCargo.getText());
        us.setUsuario(txtUsuario.getText());
        us.setPassword(Arrays.toString(password.getPassword()));
        
        
        return us;
    }
    
    public void habilitarInicio() {
        
        
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        
        
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCargo.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtTlf.setEnabled(false);
        txtUsuario.setEnabled(false);
        password.setEnabled(false);
        
    }
     public void habilitarBuscar() {
        
        
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        
        
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCargo.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtTlf.setEnabled(false);
        txtUsuario.setEnabled(false);
        password.setEnabled(false);
        
    }
    
     public void habilitarNuevo() {
        
        
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        
        
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtCargo.setEnabled(true);
        txtCorreo.setEnabled(true);
        txtTlf.setEnabled(true);
        txtUsuario.setEnabled(true);
        password.setEnabled(true);
        
    }
     
     public void habilitarRegistrar() {
        
        
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        
        
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCargo.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtTlf.setEnabled(false);
        txtUsuario.setEnabled(false);
        password.setEnabled(false);
        
    }
      public void habilitarModificar() {
        
        
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        
        
        txtNombres.setEnabled(true);
        txtApellidos.setEnabled(true);
        txtCargo.setEnabled(true);
        txtCorreo.setEnabled(true);
        txtTlf.setEnabled(true);
        txtUsuario.setEnabled(true);
        password.setEnabled(true);
        
    }
      
     public void habilitarGuardar() {
        
       
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        
        
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCargo.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtTlf.setEnabled(false);
        txtUsuario.setEnabled(false);
        password.setEnabled(false);
        
    }
     
    public void habilitarEliminar() {
        
        
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        
        
        txtNombres.setEnabled(false);
        txtApellidos.setEnabled(false);
        txtCargo.setEnabled(false);
        txtCorreo.setEnabled(false);
        txtTlf.setEnabled(false);
        txtUsuario.setEnabled(false);
        password.setEnabled(false);
        
    }
    
       
    public void limpiar() {
        labelIdUsuario.setText("0000");
        password.setText("");
        txtApellidos.setText("");
        txtCargo.setText("");
        txtCorreo.setText("");
        txtNombres.setText("");
        txtTlf.setText("");
        txtUsuario.setText("");
        
    }
    
    private void listaUsuario() {
        List<Usuario> lista;
        try {
            lista = obj.UsuarioListar();
            verUsuario(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verUsuario(List<Usuario> lista) {

        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (Usuario u : lista) {
            Object[] fila = {u.getIdUsuario(), u.getNombres(), u.getApellidos(), u.getTelefono(), u.getCorreo(), u.getCargo(), u.getUsuario()};
            tabla.addRow(fila);
        }
    }
    
}

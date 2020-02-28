/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.InventarioController;
import controller.UsuarioController;
import database.AccesoDB;
import entity.Inventario;
import entity.Usuario;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

/**
 *
 * @author ARCRODINPC-06
 */
public final class IinventarioRegistro extends javax.swing.JInternalFrame {

    /**
     * Creates new form IinventarioRegistro
     */
    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    String q;
    
    String campo1="";
    static int idInventario;
    int ultimoId;
    String usuariocombo;
    String estadocombo;
    
    public IinventarioRegistro() throws SQLException, ClassNotFoundException {
        initComponents();
        CargarUsuarios();
        txtFechReg.setText(fechaActual());
        txtFechReal.setText(fechaActual());
         
        comboBoxUsuario.setSelectedIndex(1);
        comboBoxEstado.setSelectedIndex(1);
        
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
        txtFechReg = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFechReal = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboBoxUsuario = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboBoxEstado = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObser = new javax.swing.JTextArea();
        buttonLimpiar = new javax.swing.JButton();
        buttoNuevo = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        txtIdInventario = new javax.swing.JTextField();
        buttonBuscar = new javax.swing.JButton();
        buttonRegistrarTB = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro Inventario");

        jLabel1.setText("Fecha de Registro en el Sistema: ");

        jLabel2.setText("Fecha que se realizó el Inventario:");

        jLabel3.setText("Persona encargada:");

        comboBoxUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxUsuarioItemStateChanged(evt);
            }
        });

        jLabel4.setText("Estado:");

        comboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "ACTUALIZADO", "FINALIZADO" }));
        comboBoxEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxEstadoItemStateChanged(evt);
            }
        });

        jLabel5.setText("Observación:");

        txtObser.setColumns(20);
        txtObser.setRows(5);
        jScrollPane1.setViewportView(txtObser);

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        buttoNuevo.setText("Nuevo");
        buttoNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttoNuevoActionPerformed(evt);
            }
        });

        buttonRegistrar.setText("Registrar");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
            }
        });

        buttonModificar.setText("Modifcar");
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

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        jLabel6.setText("Inventario:");

        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        buttonRegistrarTB.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonRegistrarTB.setText("Registrar Barras y Tubos ");
        buttonRegistrarTB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarTBActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(122, 122, 122)
                        .addComponent(jLabel6)
                        .addGap(33, 33, 33)
                        .addComponent(txtIdInventario, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(buttonBuscar))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel3)
                                    .addComponent(jLabel4)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(112, 112, 112)
                                .addComponent(jLabel5)))
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechReg, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFechReal, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRegistrarTB, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonCerrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttoNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(19, 19, 19))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtIdInventario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBuscar)
                            .addComponent(buttonLimpiar)
                            .addComponent(jLabel6))
                        .addGap(3, 3, 3)
                        .addComponent(buttoNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminar))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtFechReg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(txtFechReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(18, 18, 18)
                .addComponent(buttonRegistrarTB)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 24, Short.MAX_VALUE)
                .addComponent(buttonCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
       dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonRegistrarTBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarTBActionPerformed
        ArticulosDeInventario artDeInv=null;
        try {
            artDeInv = new ArticulosDeInventario();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(IinventarioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(IinventarioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(artDeInv);
        artDeInv.toFront();
        artDeInv.setVisible(true);
    }//GEN-LAST:event_buttonRegistrarTBActionPerformed

    private void comboBoxUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxUsuarioItemStateChanged
         if (comboBoxUsuario.getSelectedIndex() != 0) {
            usuariocombo = comboBoxUsuario.getSelectedItem().toString();
            System.out.println(usuariocombo);
           
        } else {

        }
    }//GEN-LAST:event_comboBoxUsuarioItemStateChanged

    private void comboBoxEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxEstadoItemStateChanged
          if (comboBoxEstado.getSelectedIndex() != 0) {
            estadocombo = comboBoxEstado.getSelectedItem().toString();
            System.out.println(estadocombo);
        } else {
        }
    }//GEN-LAST:event_comboBoxEstadoItemStateChanged

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        procesar(1);
        try {
            consultarUltimoId();
        } catch (Exception ex) {
            Logger.getLogger(IinventarioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultar(ultimoId);
        } catch (Exception ex) {
            Logger.getLogger(IinventarioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarRegistrar();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        procesar(2);
        habilitarGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        procesar(3);
        limpiar();
        habilitarEliminar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        try {
            consultar(Integer.parseInt(txtIdInventario.getText()));
        } catch (Exception ex) {
            Logger.getLogger(IinventarioRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBucar();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        habilitarInicio();
        limpiar();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttoNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttoNuevoActionPerformed
        limpiar();
        habilitarNuevo();
    }//GEN-LAST:event_buttoNuevoActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttoNuevo;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonRegistrarTB;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private javax.swing.JComboBox<String> comboBoxUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txtFechReal;
    private javax.swing.JTextField txtFechReg;
    private javax.swing.JTextField txtIdInventario;
    private javax.swing.JTextArea txtObser;
    // End of variables declaration//GEN-END:variables

InventarioController inventariocontroler = new InventarioController();
Inventario inventario;

UsuarioController usuariocontroler= new UsuarioController();
Usuario usuario;


//------------------------- Procesar --------------

private void procesar(int op) {
       inventario=leerDatos();
        try {
            String msg=inventariocontroler.InventarioProcesar(inventario, op);
            JOptionPane.showMessageDialog(null, msg);            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
             System.out.println("Error"+e.getMessage());
        }       
   }

//--------------------------- Leer Datos -----------------------

     private Inventario leerDatos() {
        Inventario inv = new Inventario();
        
        inv.setFechReg(txtFechReg.getText());
        inv.setFechRea(txtFechReal.getText());
        inv.setEncargado(usuariocombo);
        inv.setEstado(estadocombo);
        inv.setObser(txtObser.getText());
        inv.setCampo1(campo1);
        inv.setIdInventario(idInventario);
        
        return inv;
    }
//------------------------------- Cargar Usuarios ---------------------
     public JComboBox<String> CargarUsuarios() throws SQLException, ClassNotFoundException {
        comboBoxUsuario.removeAllItems();
        comboBoxUsuario.addItem("Usuario");
        try {
            conexion = AccesoDB.obtener();
            q = "select nombres,apellidos from usuario";
            ps = conexion.prepareStatement(q);
            rs = ps.executeQuery();
            while (rs.next()) {
                comboBoxUsuario.addItem(rs.getString("nombres") + " " + rs.getString("apellidos"));
            }
            rs.close();
            ps.close();

        } catch (SQLException e) {
            throw e;

        }
        return comboBoxUsuario;
    }
     
//--------------------------------- Último Id ------------------------------------------
  
    private void consultarUltimoId() throws Exception {
        inventario = inventariocontroler.InventarioBuscarUltimoId();
        if (inventario != null) {
        }
        ultimoId = inventario.getIdInventario();
        idInventario=ultimoId;
        txtIdInventario.setText(Integer.toString(inventario.getIdInventario()));
        
    }
    
//-------------------------------- Consultar Inventario --------------------------------
  void consultar(int id) throws Exception{
        inventario= inventariocontroler.InventarioBuscar(id);
        if (inventario != null) {
            System.out.println("22222222222222222");
            idInventario=inventario.getIdInventario();
            System.out.println("111111111111111111111");
            txtIdInventario.setText(Integer.toString(idInventario));
            txtFechReg.setText(inventario.getFechReg());
            txtFechReal.setText(inventario.getFechRea());
            
            usuariocombo=inventario.getEncargado();
            String testValue2 = usuariocombo;
            for (int i = 0; i < comboBoxUsuario.getModel().getSize(); i++) {
                if (comboBoxUsuario.getItemAt(i).equals(testValue2)) {
                    System.out.println(i);
                    comboBoxUsuario.setSelectedIndex(i);
                    break;
                }
            }
            
            estadocombo=inventario.getEstado();
            String testValue5 = estadocombo;
            for (int i = 0; i < comboBoxEstado.getModel().getSize(); i++) {
                if (comboBoxEstado.getItemAt(i).equals(testValue5)) {
                    System.out.println(i);
                    comboBoxEstado.setSelectedIndex(i);
                    break;
                }
            }
            
            txtObser.setText(inventario.getObser());
            

        } else {
            JOptionPane.showMessageDialog(null, "Inventario no registrado");
            //System.out.println("Error");
            limpiar();
        }
    } 
  
// ------------------------ Fecha --> Indica la fecha del dia --------------------
    public String fechaActual() {
        Date fecha = new Date();

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatofecha.format(fecha);
    }
    

 //--------------------------- Limpiar ----------------------------------------
    void limpiar() {
        txtIdInventario.setText("");
        idInventario=0;
        txtFechReg.setText(fechaActual());
        txtFechReal.setText(fechaActual());
        comboBoxUsuario.setSelectedIndex(1);
        comboBoxEstado.setSelectedIndex(1);
        txtObser.setText("");
    }
    
 //--------------------------- Habilitar --------------------------------------
    void habilitarInicio(){
        buttonBuscar.setEnabled(true);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonRegistrarTB.setEnabled(false);
        
        txtIdInventario.setEnabled(true);
        txtFechReg.setEnabled(false);
        txtFechReal.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        comboBoxEstado.setEnabled(false);
        txtObser.setEnabled(false);
    }
    
    void habilitarBucar() {
        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonRegistrarTB.setEnabled(true);

        txtIdInventario.setEnabled(false);
        txtFechReg.setEnabled(false);
        txtFechReal.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        comboBoxEstado.setEnabled(false);
        txtObser.setEnabled(false);
    }
    
    void habilitarNuevo() {
        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonRegistrarTB.setEnabled(false);

        txtIdInventario.setEnabled(false);
        txtFechReg.setEnabled(true);
        txtFechReal.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        comboBoxEstado.setEnabled(true);
        txtObser.setEnabled(true);
    }

    void habilitarRegistrar() {
        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonRegistrarTB.setEnabled(true);

        txtIdInventario.setEnabled(false);
        txtFechReg.setEnabled(false);
        txtFechReal.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        comboBoxEstado.setEnabled(false);
        txtObser.setEnabled(false);
    }

    void habilitarModificar() {
        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        buttonRegistrarTB.setEnabled(false);

        txtIdInventario.setEnabled(true);
        txtFechReg.setEnabled(true);
        txtFechReal.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        comboBoxEstado.setEnabled(true);
        txtObser.setEnabled(true);
    }

    void habilitarGuardar() {
        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonRegistrarTB.setEnabled(true);

        txtIdInventario.setEnabled(false);
        txtFechReg.setEnabled(false);
        txtFechReal.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        comboBoxEstado.setEnabled(false);
        txtObser.setEnabled(false);
    }

    void habilitarEliminar() {
        buttonBuscar.setEnabled(true);
        buttonLimpiar.setEnabled(true);
        buttoNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonRegistrarTB.setEnabled(false);
        
        txtIdInventario.setEnabled(true);
        txtFechReg.setEnabled(false);
        txtFechReal.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        comboBoxEstado.setEnabled(false);
        txtObser.setEnabled(false);
    }
  
}
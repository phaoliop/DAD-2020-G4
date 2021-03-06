/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ArticuloProveedorController;
import controller.ProveedorArticuloController;
import controller.ProveedorController;
import entity.ArticuloProveedor;
import entity.Proveedor;
import entity.ProveedorArticulo;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARCRODINPC-02
 */
public class ProveedorArticulos extends javax.swing.JInternalFrame {

    /**
     * Creates new form ArticulosProveedor
     */
    
    int idProveeArt;
    int idProvee;
    int idArticuloProveedor;
    
    int tabla;
    String idProvArt;
    
    String articulocombo;
    
    public ProveedorArticulos() throws Exception {
        initComponents();
        consultarProveedor(Proveedores.y);
        idProvee=Proveedores.y;
        listaProveedorArticulo(idProvee);
        habilitarInicio();
        idArticuloProveedor=0;
         comboBoxArticulosProveedor.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxArticulosProveedor.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                     consultarArticuloNombre(comboBoxArticulosProveedor.getItemAt(0));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comboBoxArticulosProveedor.setSelectedIndex(0);
                //    habilitarInicio();
                    if (comparar(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                           consultarArticuloNombre(cadenaEscrita);

                                                      
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    // habilitarInicio();

                    } else {
                        try {
                            for (int i = 0; i <comboBoxArticulosProveedor.getModel().getSize(); i++) {
                                if (comboBoxArticulosProveedor.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarArticuloNombre(comboBoxArticulosProveedor.getItemAt(i));
                                    comboBoxArticulosProveedor.setSelectedIndex(i);
                                }
                            }
                          //  habilitarInicio();
                        //    habilitarBuscar();
                            } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxArticulosProveedor.setModel(articuloproveedorcontroler.ArticuloProveedorListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxArticulosProveedor.getItemCount() > 0) {
                        comboBoxArticulosProveedor.getEditor().setItem(cadenaEscrita);
                        comboBoxArticulosProveedor.showPopup();
                    } else {
                        comboBoxArticulosProveedor.addItem(cadenaEscrita);
                    }
                }
            }
        });
        
    }
    
     private boolean comparar(String cadena) {
        Object[] lista = comboBoxArticulosProveedor.getComponents();
        boolean encontrado = false;
        for (Object object : lista) {
            if (cadena.equals(object)) {
                encontrado = true;
                break;
            }

        }
        return encontrado;
    }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        labelIdProveedorArticulo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtProveedor = new javax.swing.JTextField();
        txtPrecioCompra = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObs = new javax.swing.JTextArea();
        buttonLimpiar = new javax.swing.JButton();
        buttonNuevo = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        comboBoxArticulosProveedor = new javax.swing.JComboBox<>();
        buttonRegistrarArticulo = new javax.swing.JButton();

        setClosable(true);
        setTitle("Proveedores - Productos");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idProArt.", "Articulo/Producto", "Precio Compra", "Observación"
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(25);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(250);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(300);
        }

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        jLabel1.setText("N°:");

        labelIdProveedorArticulo.setText("000000");

        jLabel3.setText("Proveedor:");

        jLabel5.setText("Precio de Compra:");

        jLabel6.setText("Observación:");

        txtObs.setColumns(20);
        txtObs.setRows(5);
        jScrollPane2.setViewportView(txtObs);

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

        comboBoxArticulosProveedor.setEditable(true);

        buttonRegistrarArticulo.setText("Producto:");
        buttonRegistrarArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarArticuloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCerrar)
                .addGap(21, 21, 21))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(buttonRegistrarArticulo)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel1)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(10, 10, 10)
                                            .addComponent(jLabel3))
                                        .addComponent(labelIdProveedorArticulo))))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtProveedor)
                            .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 334, Short.MAX_VALUE)
                            .addComponent(comboBoxArticulosProveedor, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 95, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonNuevo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(83, 83, 83))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelIdProveedorArticulo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxArticulosProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRegistrarArticulo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminar)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        if (idArticuloProveedor==0) {
            JOptionPane.showMessageDialog(null, "Llenar el campo de Producto");
        } else {
            procesar(1);
            listaProveedorArticulo(idProvee);
            habilitarRegistrar();
            limpiar();
        }
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        procesar(2);
        listaProveedorArticulo(idProvee);
        habilitarGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        procesar(3);
        listaProveedorArticulo(idProvee);
        habilitarEliminar();
        limpiar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tabla = jTable1.getSelectedRow();
        idProvArt = jTable1.getValueAt(tabla, 0).toString();
        idProveeArt=Integer.parseInt(idProvArt);
        try {
            consultar(idProveeArt);
        } catch (Exception ex) {
            Logger.getLogger(ProveedorArticulos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscar();
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonRegistrarArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarArticuloActionPerformed
        ArticulosProveedoresRegistro artProveedor= new ArticulosProveedoresRegistro();
        Principal.jDesktopPane1.add(artProveedor);
        artProveedor.toFront();
        artProveedor.setVisible(true);
    }//GEN-LAST:event_buttonRegistrarArticuloActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        habilitarNuevo();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonRegistrarArticulo;
    private javax.swing.JComboBox<String> comboBoxArticulosProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable1;
    private javax.swing.JLabel labelIdProveedorArticulo;
    private javax.swing.JTextArea txtObs;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtProveedor;
    // End of variables declaration//GEN-END:variables

    ProveedorArticuloController proveedorarticulocontroler = new ProveedorArticuloController();
    ProveedorArticulo proveedorarticulo;
    
    ArticuloProveedorController articuloproveedorcontroler = new ArticuloProveedorController();
    ArticuloProveedor articuloproveedor;
    
    ProveedorController proveedorcontroler=new ProveedorController();
    Proveedor proveedor;

    
    private void procesar(int op) {
       proveedorarticulo=leerDatos();
        try {
            String msg=proveedorarticulocontroler.ProveedorArticuloProcesar(proveedorarticulo, op);
            JOptionPane.showMessageDialog(null, msg);            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
             System.out.println("Error"+e.getMessage());
        }       
   }
   
   
   
     private void consultar(int id) throws Exception {
       proveedorarticulo = proveedorarticulocontroler.ProveedorArticuloBuscar(id);
        if (proveedorarticulo!= null) {

            labelIdProveedorArticulo.setText(proveedorarticulo.getIdProvArtPk()+"");
            idProvee = proveedorarticulo.getIdProv();
            consultarProveedor(idProvee);
            idArticuloProveedor = proveedorarticulo.getIdArtProv();
            consultarArticuloId(idArticuloProveedor);           
            txtPrecioCompra.setText(proveedorarticulo.getPrecio());
            txtObs.setText(proveedorarticulo.getObs());
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }
     
    
    private void consultarProveedor(int id) throws Exception {
       proveedor = proveedorcontroler.ProveedorBuscarId(id);
        if (proveedor!= null) {

           txtProveedor.setText(proveedor.getRazonSocial());
            
            
        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no registrado");
            //System.out.println("Error");
        }
    }
    
    
    
     private ProveedorArticulo leerDatos() {
        ProveedorArticulo proArt = new ProveedorArticulo();
        
        proArt.setIdProvArtPk(idProveeArt);
        proArt.setIdProv(idProvee);
        proArt.setIdArtProv(idArticuloProveedor);
        proArt.setPrecio(txtPrecioCompra.getText());
        proArt.setObs(txtObs.getText());
        
        
       
        return proArt;
    }
     
 private void consultarArticuloNombre(String cadena) throws Exception {
        articuloproveedor = articuloproveedorcontroler.ArticuloProveedorBuscarNombre(cadena);
        if (articuloproveedor != null) {

            idArticuloProveedor = articuloproveedor.getIdArticulosProveedores();
            System.out.println("El id del articulo es: "+idArticuloProveedor);
            

        } else {
            JOptionPane.showMessageDialog(null, "Articulo no registrado");
            //System.out.println("Error");
        }
    }
    
 private void consultarArticuloId(int id) throws Exception {
        articuloproveedor = articuloproveedorcontroler.ArticuloProveedorBuscar(id);
        if (articuloproveedor != null) {

            idArticuloProveedor = articuloproveedor.getIdArticulosProveedores();
            comboBoxArticulosProveedor.setSelectedItem(articuloproveedor.getDescripcion());
            System.out.println("El id del articulo es: "+idArticuloProveedor);
            

        } else {
            JOptionPane.showMessageDialog(null, "Articulo no registrado");
            //System.out.println("Error");
        }
    }
     
 
//---------------------------------- Tabla ---------------------------------------------------
     void listaProveedorArticulo(int i) {
        List<ProveedorArticulo> lista;
        try {
            
            lista = proveedorarticulocontroler.ProveedorArticuloListar(i);
            verProveedorArticulo(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

     private void verProveedorArticulo(List<ProveedorArticulo> lista) {

        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (ProveedorArticulo proArt : lista) {
            Object[] fila = {proArt.getIdProvArtPk(),proArt.getArticulo() ,proArt.getPrecio(), proArt.getObs()};
            tabla.addRow(fila);
        }
    }
     
 //--------------------------- Habilitar ---------------------------------------
     
     void habilitarInicio(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(true);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(false);
         
         buttonRegistrarArticulo.setEnabled(true);
         
         txtProveedor.setEnabled(false);
         comboBoxArticulosProveedor.setEnabled(false);
         buttonRegistrarArticulo.setEnabled(false);
         txtObs.setEnabled(false);
         txtPrecioCompra.setEnabled(false);

     }
     
     void habilitarBuscar(){ 
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(true);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(true);
         
         buttonRegistrarArticulo.setEnabled(false);
         
         txtProveedor.setEnabled(false);
         comboBoxArticulosProveedor.setEnabled(false);
         buttonRegistrarArticulo.setEnabled(false);
         txtObs.setEnabled(false);
         txtPrecioCompra.setEnabled(false);
     }
     
     void habilitarNuevo(){
         
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(true);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(false);
         
         buttonRegistrarArticulo.setEnabled(true);
         
         txtProveedor.setEnabled(true);
         comboBoxArticulosProveedor.setEnabled(true);
         buttonRegistrarArticulo.setEnabled(true);
         txtObs.setEnabled(true);
         txtPrecioCompra.setEnabled(true);
     
     }
     
     void habilitarRegistrar(){
     
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(true);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(false);
         
         buttonRegistrarArticulo.setEnabled(false);
         
         txtProveedor.setEnabled(false);
         comboBoxArticulosProveedor.setEnabled(false);
         buttonRegistrarArticulo.setEnabled(false);
         txtObs.setEnabled(false);
         txtPrecioCompra.setEnabled(false);
         
     }
     
     void habilitarModificar(){
     
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(true);
         buttonEliminar.setEnabled(false);
         
         buttonRegistrarArticulo.setEnabled(false);
         
         txtProveedor.setEnabled(true);
         comboBoxArticulosProveedor.setEnabled(true);
         buttonRegistrarArticulo.setEnabled(true);
         txtObs.setEnabled(true);
         txtPrecioCompra.setEnabled(true);
     }
     void habilitarGuardar(){
     
          buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(false);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(true);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(true);
         
         buttonRegistrarArticulo.setEnabled(false);
         
         txtProveedor.setEnabled(false);
         comboBoxArticulosProveedor.setEnabled(false);
         buttonRegistrarArticulo.setEnabled(false);
         txtObs.setEnabled(false);
         txtPrecioCompra.setEnabled(false);
     }
     
     void habilitarEliminar(){
     
         buttonLimpiar.setEnabled(true);
         buttonNuevo.setEnabled(true);
         buttonRegistrar.setEnabled(false);
         buttonModificar.setEnabled(false);
         buttonGuardar.setEnabled(false);
         buttonEliminar.setEnabled(false);
         
         buttonRegistrarArticulo.setEnabled(true);
         
         txtProveedor.setEnabled(false);
         comboBoxArticulosProveedor.setEnabled(false);
         buttonRegistrarArticulo.setEnabled(false);
         txtObs.setEnabled(false);
         txtPrecioCompra.setEnabled(false);
     }

     
     void limpiar(){
     
         comboBoxArticulosProveedor.setSelectedItem("");
         txtObs.setText("");
         txtPrecioCompra.setText("");
         idArticuloProveedor=0;
         labelIdProveedorArticulo.setText("0000");
     }
}

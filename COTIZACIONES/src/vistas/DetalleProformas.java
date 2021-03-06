/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.DetalleProformaController;
import entity.DetalleProforma;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static vistas.Proformas.categoria4;

/**
 *
 * @author ARCRODINPC-02
 */
public class DetalleProformas extends javax.swing.JInternalFrame {

    static String Descripcion;

    /**
     * Creates new form DetalleProformas
     */
    String valorDescripcion = "";
    String valorPrecio = "";
    static String itemS = "";
    static String cantidadS = "";
    static String precioS = "";
    
    static String precioDolarCro;
    static String precioSolCro;
    
    

    public DetalleProformas( String descripcion, String precio) {
        this.valorDescripcion = descripcion;
        this.valorPrecio = precio;

        txtDescripcion.setText(valorDescripcion);
        txtPrecioUnitario.setText(valorPrecio);

    }
    
   
    public DetalleProformas() throws SQLException, ClassNotFoundException {

        initComponents();
        int b = Proformas.b; // idProforma
        String cod = Proformas.cod; //codigoProforma
        labelCodProf.setText(Proformas.cod);
        System.out.println(b + "-" + cod);
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
        labelCodProf = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtItem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtPrecioUnitario = new javax.swing.JTextField();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();
        buttonLimpiar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonNuevo = new javax.swing.JButton();
        buttonPrecio = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtDetDescrip = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        buttonPrecioCromo = new javax.swing.JButton();

        setClosable(true);
        setTitle("Detalle de Cotización ");

        jLabel1.setText("Cotización:");

        labelCodProf.setText("0000");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel3.setText("Item: ");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel4.setText("Cantidad: ");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel5.setText("Descripcion:");

        txtDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDescripcionActionPerformed(evt);
            }
        });
        txtDescripcion.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtDescripcionKeyTyped(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel7.setText("Precio Unitario: ");

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

        buttonPrecio.setText("Precio Barras/Tubos");
        buttonPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrecioActionPerformed(evt);
            }
        });

        txtDetDescrip.setColumns(20);
        txtDetDescrip.setRows(5);
        jScrollPane1.setViewportView(txtDetDescrip);

        jLabel2.setText("Cuadro de información");

        jLabel8.setText("interna");

        jLabel10.setText("Este cuadro no será mostrada en la cotización, ni al cliente.");

        buttonPrecioCromo.setText("Precio Cromado");
        buttonPrecioCromo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPrecioCromoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelCodProf, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(78, 78, 78)
                                        .addComponent(jLabel3))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addGap(49, 49, 49)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel5)
                                                .addComponent(jLabel4)))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                            .addGap(19, 19, 19)
                                            .addComponent(jLabel7))))
                                .addGap(0, 16, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(33, 33, 33)
                                        .addComponent(jLabel8)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 482, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonPrecio)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonPrecioCromo, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 446, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 15, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buttonLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(buttonRegistrar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(2, 2, 2)
                            .addComponent(buttonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(buttonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonGuardar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(13, 13, 13))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5))
                        .addGap(11, 11, 11)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtPrecioUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonPrecio)
                            .addComponent(jLabel7)
                            .addComponent(buttonPrecioCromo))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel8)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addContainerGap(18, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonLimpiar)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(labelCodProf)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNuevo)
                        .addGap(4, 4, 4)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCerrar)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
      
        dispose();

    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        procesar(1);

        Proformas.listaDetalleProforma();
        try {
            Proformas.consultarPago();
        } catch (Exception ex) {
            Logger.getLogger(DetalleProformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("DOLARES AMERICANOS".equals(Proformas.categoria4) && "SERVICIO".equals(Proformas.categoria2)) {
            try {
                Proformas.consultarTotal(Proformas.txtFecha.getText());
                if (Float.parseFloat(Proformas.total) >700) {
                        Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                    } else if (Float.parseFloat(Proformas.total) < 700 || Float.parseFloat(Proformas.total)==700 ) {
                            Proformas.txtDetraccion.setText("");
                        }else if ("DOLARES AMERICANOS".equals(Proformas.categoria4) && "PRODUCTO".equals(Proformas.categoria2)) {
                                Proformas.txtDetraccion.setText("");
                            }
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                 }
               
            } else if ("SOLES".equals(Proformas.categoria4) && "SERVICIO".equals(Proformas.categoria2)) {
                    if (Float.parseFloat(Proformas.labelTotal.getText()) > 700) {
                            Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                        } else if (Float.parseFloat(Proformas.labelTotal.getText()) <700 || Float.parseFloat(Proformas.labelTotal.getText()) ==700) {
                                Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                            }else if ("SOLES".equals(Proformas.categoria4) && "PRODUCTO".equals(Proformas.categoria2)) {
                                    Proformas.txtDetraccion.setText("");
                                }
                } else {
                        Proformas.txtDetraccion.setText("");
                    }
        Descripcion = txtDescripcion.getText();
        Proformas.procesarProfUpdate(2);
        limpiar();
        habilitarInicio();
        
//        try {
//            consultar(txtDescripcion.getText(), Proformas.b);
//        } catch (Exception ex) {
//            Logger.getLogger(DetalleProformas.class.getName()).log(Level.SEVERE, null, ex);
//        }

    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        // TODO add your handling code here:
        procesar1(2);
        Proformas.listaDetalleProforma();
        try {
            
            Proformas.consultarPago();
        } catch (Exception ex) {
            Logger.getLogger(DetalleProformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("DOLARES AMERICANOS".equals(Proformas.categoria4) && "SERVICIO".equals(Proformas.categoria2)) {
            try {
              
                Proformas.consultarTotal(Proformas.txtFecha.getText());
                if (Float.parseFloat(Proformas.total) > 700) {
                    Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                            
                    } else if (Float.parseFloat(Proformas.total) < 700 || Float.parseFloat(Proformas.total)==700) {
                             Proformas.txtDetraccion.setText("");
                
                        } else if ("DOLARES AMERICANOS".equals(Proformas.categoria4) && "PRODUCTO".equals(Proformas.categoria2)) {
                                Proformas.txtDetraccion.setText("");
                    }
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
             
        } else if ("SOLES".equals(Proformas.categoria4) && "SERVICIO".equals(Proformas.categoria2)) {
                if (Float.parseFloat(Proformas.labelTotal.getText()) > 700) {
                            Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                        } else if (Float.parseFloat(Proformas.labelTotal.getText()) <700 || Float.parseFloat(Proformas.labelTotal.getText())==700) {
                                Proformas.txtDetraccion.setText("");
                            } else if ("SOLES".equals(Proformas.categoria4) && "PRODUCTO".equals(Proformas.categoria2)) {
                                         Proformas.txtDetraccion.setText("");
                      }
            } else {
                Proformas.txtDetraccion.setText("");
        }

        Proformas.procesarProfUpdate(2);
       
        habilitarGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            procesar1(3);
            limpiar();
            Proformas.listaDetalleProforma();
            try {
                Proformas.consultarPago();
            } catch (Exception ex) {
                Logger.getLogger(DetalleProformas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ("DOLARES AMERICANOS".equals(Proformas.categoria4) && "SERVICIO".equals(Proformas.categoria2)) {
                try {
                    Proformas.consultarTotal(Proformas.txtFecha.getText());
                    if (Float.parseFloat(Proformas.total) > 700) {
                        Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                    } else if (Float.parseFloat(Proformas.total) < 700 || Float.parseFloat(Proformas.total) == 700) {
                        Proformas.txtDetraccion.setText("");
                    } else if ("DOLARES AMERICANOS".equals(Proformas.categoria4) && "PRODUCTO".equals(Proformas.categoria2)) {
                        Proformas.txtDetraccion.setText("");
                    }
                } catch (Exception ex) {
                    Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else if ("SOLES".equals(Proformas.categoria4) && "SERVICIO".equals(Proformas.categoria2)) {
                if (Float.parseFloat(Proformas.labelTotal.getText()) > 700) {
                    Proformas.txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                } else if (Float.parseFloat(Proformas.labelTotal.getText()) < 700 || Float.parseFloat(Proformas.labelTotal.getText()) == 700) {
                    Proformas.txtDetraccion.setText("");
                } else if ("SOLES".equals(Proformas.categoria4) && "PRODUCTO".equals(Proformas.categoria2)) {
                    Proformas.txtDetraccion.setText("");
                }
            } else {

                Proformas.txtDetraccion.setText("");
            }

            Proformas.listaDetalleProforma();
            Proformas.procesarProfUpdate(2);
            habilitarEliminar();
        }
        dispose();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrecioActionPerformed
        ValidarPrecio1 calcularPrecio = null;
        
        try {
            calcularPrecio = new ValidarPrecio1();
        } catch (Exception ex) {
            Logger.getLogger(DetalleProformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(calcularPrecio);
        calcularPrecio.toFront();
        calcularPrecio.setVisible(true);
    }//GEN-LAST:event_buttonPrecioActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        habilitarNuevo();
        limpiar();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void txtDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDescripcionActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDescripcionActionPerformed

    private void buttonPrecioCromoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPrecioCromoActionPerformed
        PrecioCromadoParaDetalleProforma preCro = new PrecioCromadoParaDetalleProforma();
        Principal.jDesktopPane1.add(preCro);
        preCro.toFront();
        preCro.setVisible(true);
    }//GEN-LAST:event_buttonPrecioCromoActionPerformed

    private void txtDescripcionKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtDescripcionKeyTyped
        String Caracteres = txtDescripcion.getText();
        if (Caracteres.length() >= 72) {
            JOptionPane.showMessageDialog(null, "Ya no puede ingresar más letras.");
            evt.consume();
        }
    }//GEN-LAST:event_txtDescripcionKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar;
    public static javax.swing.JButton buttonEliminar;
    public static javax.swing.JButton buttonGuardar;
    public static javax.swing.JButton buttonLimpiar;
    public static javax.swing.JButton buttonModificar;
    public static javax.swing.JButton buttonNuevo;
    public static javax.swing.JButton buttonPrecio;
    public static javax.swing.JButton buttonPrecioCromo;
    public static javax.swing.JButton buttonRegistrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private static javax.swing.JLabel labelCodProf;
    public static javax.swing.JTextField txtCantidad;
    public static javax.swing.JTextField txtDescripcion;
    public static javax.swing.JTextArea txtDetDescrip;
    public static javax.swing.JTextField txtItem;
    public static javax.swing.JTextField txtPrecioUnitario;
    // End of variables declaration//GEN-END:variables

    static DetalleProformaController obj = new DetalleProformaController();
    static DetalleProforma pro;

    // ------------------------- Procesar --------------------
    static private void procesar(int op) {
        pro = leerDatos();
        try {
            String msg = obj.DetalleProformaProcesar(pro, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
    static private void procesar1(int op) {
        pro = leerDatos1();
        try {
            String msg = obj.DetalleProformaProcesar(pro, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
    static  void procesarProductoServ(String des,int op) {
        pro = leerDatosProducto(des);
        try {
            String msg = obj.DetalleProformaProcesar(pro, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }

    // ------------------------ Consultar -------------------------
      static void consultar( int id, int idDetalleProf) throws Exception {
        //pro = obj.DetalleProformaBuscar(Descripcion,Proformas.a);
        pro = obj.DetalleProformaBuscar(id,idDetalleProf);
        if (pro != null) {
            
            txtItem.setText(pro.getItem());
            txtCantidad.setText(pro.getCantidad());
            txtDescripcion.setText(pro.getDescripcion());
            txtDetDescrip.setText(pro.getDetalleDescrip());
            txtPrecioUnitario.setText(pro.getPrecioUnitario());

        } else {
            JOptionPane.showMessageDialog(null, "Producto no registrado");
            //System.out.println("Error");
        }
    }

    // --------------------------- Leer Datos ------------------------
    static private DetalleProforma leerDatos() {
        DetalleProforma detProf = new DetalleProforma();

        labelCodProf.setText(Proformas.cod);
        detProf.setIdProforma(Proformas.b);
        detProf.setItem(txtItem.getText());
        detProf.setCantidad(txtCantidad.getText());
        detProf.setDescripcion(txtDescripcion.getText());
        detProf.setDetalleDescrip(txtDetDescrip.getText());
        detProf.setPrecioUnitario(txtPrecioUnitario.getText());
        
        return detProf;
    }
    
    static private DetalleProforma leerDatos1() {
        DetalleProforma detProf = new DetalleProforma();

        labelCodProf.setText(Proformas.cod);
        detProf.setIdProforma(Proformas.b);
        detProf.setItem(txtItem.getText());
        detProf.setCantidad(txtCantidad.getText());
        detProf.setDescripcion(txtDescripcion.getText());
        detProf.setDetalleDescrip(txtDetDescrip.getText());
        detProf.setPrecioUnitario(txtPrecioUnitario.getText());
        detProf.setIdDetalleProforma(Proformas.idDetalleProforma);
        
        return detProf;
    }
    
    static private DetalleProforma leerDatosProducto( String des) {
        DetalleProforma detProf = new DetalleProforma();

        
        detProf.setIdProforma(Proformas.b);
        detProf.setItem("");
        detProf.setCantidad("");
        detProf.setDescripcion(des);
        detProf.setDetalleDescrip("");
        detProf.setPrecioUnitario("");

        return detProf;
    }
    

    //-------------------------- Limpiar ---------------------------------
    public void limpiar() {
        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtDetDescrip.setText("");
        txtItem.setText("");
        txtPrecioUnitario.setText("");

    }
    
    public void habilitarInicio(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonPrecio.setEnabled(true);
        buttonPrecioCromo.setEnabled(true);
        
        txtItem.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtDetDescrip.setEnabled(true);
        txtPrecioUnitario.setEnabled(true);
    }
    
    public void habilitarNuevo(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonPrecio.setEnabled(true);
        buttonPrecioCromo.setEnabled(true);
        
        txtItem.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtDetDescrip.setEnabled(true);
        txtPrecioUnitario.setEnabled(true);
    }
    
     public void habilitarRegistrar(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonPrecio.setEnabled(false);
        buttonPrecioCromo.setEnabled(false);
        
        txtItem.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtDetDescrip.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
    }
     
         public void habilitarEliminar(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonPrecio.setEnabled(false);
        buttonPrecioCromo.setEnabled(false);
        
        txtItem.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtDetDescrip.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
    }
         
    public void habilitarModificar(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        buttonPrecio.setEnabled(true);
        buttonPrecioCromo.setEnabled(true);
        
        txtItem.setEnabled(true);
        txtCantidad.setEnabled(true);
        txtDescripcion.setEnabled(true);
        txtDetDescrip.setEnabled(true);
        txtPrecioUnitario.setEnabled(true);
    }
    
    public void habilitarGuardar(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonPrecio.setEnabled(false);
        buttonPrecioCromo.setEnabled(false);
        
        txtItem.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtDetDescrip.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
    }
    
        static void habilitarTabla(){
        
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonPrecio.setEnabled(false);
        buttonPrecioCromo.setEnabled(false);
        
        txtItem.setEnabled(false);
        txtCantidad.setEnabled(false);
        txtDescripcion.setEnabled(false);
        txtDetDescrip.setEnabled(false);
        txtPrecioUnitario.setEnabled(false);
    }
        
        
   // ----------------------------- Método jala precio del cromado ---------------------
        static void precioCromadoDolares(){
        precioDolarCro=PrecioCromadoParaDetalleProforma.txtCostoDolar.getText();
        txtPrecioUnitario.setText(precioDolarCro);
        }
        
        static void precioCromadoSoles(){
        precioSolCro=PrecioCromadoParaDetalleProforma.txtCostoSoles.getText();
        txtPrecioUnitario.setText(precioSolCro);
        }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.sun.glass.events.KeyEvent;
import controller.ClienteController;
import controller.ContactoClienteController;
import controller.DetallePedidoController;
import controller.DetalleProformaController;
import controller.ProformaController;
import entity.Cliente;
import entity.ContactoCliente;
import entity.DetallePedido;
import entity.DetalleProforma;
import entity.Proforma;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import static vistas.Clientes.x;

/**
 *
 * @author ARCRODINPC-06
 */
public class ProformaAPedido extends javax.swing.JInternalFrame {

    /**
     * Creates new form ProformaAPedido
     */
    
    int idProforma;
    int idCliente;
    int idConCli;
    int idDetPedido;
    int idPedido;
    int numPedido;
    String numCotizacion;
    String comboContacto;
    String nombre;
    String apellido;
    String dni;
    String atencion;
    String categoria;
    String cant;
    String descripcion;
    String item;
    String valorUnitario;
    int tabla;
    int anioCot;
    
    public ProformaAPedido() throws Exception {
        initComponents();
        idPedido = Pedidos.idPedParaDetalle;
        numPedido = Pedidos.numPedParaDetalle;
        numCotizacion = Pedidos.codProformaParaDetalle;
        anioCot = Pedidos.anioParaDetalleAPedido;
        labelNumPedido.setText(Integer.toString(numPedido));
        txtNumCotiz.setText(numCotizacion);
        consultarProforma(txtNumCotiz.getText(), anioCot);
        listaDetalleProforma();
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
        buttonBuscarCotizacion = new javax.swing.JButton();
        buttonLimpiar = new javax.swing.JButton();
        comboBoxContactoCli = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        txtCliente = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        txtItem = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtCantidad = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtDescripcion = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtValorUnitario = new javax.swing.JTextField();
        buttonRegistrar = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        labelNumPedido = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtDetalleDescripcion = new javax.swing.JTextField();
        txtNumCotiz = new javax.swing.JTextField();
        buttonAgregarContacto = new javax.swing.JButton();

        setClosable(true);
        setTitle("Información del detalle de la Cotización para el Pedido");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("N° de Cotización: ");

        buttonBuscarCotizacion.setText("Buscar");
        buttonBuscarCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarCotizacionActionPerformed(evt);
            }
        });

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        comboBoxContactoCli.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " " }));

        jLabel3.setText("Cliente:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idDetCot", "Item", "Cant.", "Descripción", "Valor Unit.", "Precio Unit.", "Importe"
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(380);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(100);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(100);
        }

        jLabel4.setText("Item");

        jLabel5.setText("Cantidad");

        jLabel6.setText("Descripción");

        jLabel7.setText("Valor Unitario");

        buttonRegistrar.setText("Registrar");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
            }
        });

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel8.setText("Número de Pedido:");

        labelNumPedido.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        labelNumPedido.setText("00000000000000");

        jLabel9.setText("Detalle Descripción:");

        txtNumCotiz.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtNumCotizKeyPressed(evt);
            }
        });

        buttonAgregarContacto.setText("Contacto:");
        buttonAgregarContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarContactoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelNumPedido)
                                .addGap(350, 350, 350))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 791, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 454, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel1)
                                                .addGap(3, 3, 3)
                                                .addComponent(txtNumCotiz, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonBuscarCotizacion)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(buttonLimpiar)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(buttonAgregarContacto)))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxContactoCli, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel9)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                    .addComponent(txtCantidad))
                                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                    .addComponent(jLabel4)
                                                    .addGap(41, 41, 41)
                                                    .addComponent(jLabel5))))
                                        .addGap(18, 18, 18)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(jLabel6)
                                            .addComponent(txtDescripcion, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE)
                                            .addComponent(txtDetalleDescripcion))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel7))))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(328, 328, 328)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCerrar)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(labelNumPedido))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(buttonBuscarCotizacion)
                    .addComponent(buttonLimpiar)
                    .addComponent(comboBoxContactoCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumCotiz, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAgregarContacto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtItem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtValorUnitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtDetalleDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCerrar)
                    .addComponent(buttonRegistrar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
       dispose();  
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonBuscarCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarCotizacionActionPerformed
        try {
            consultarProforma(txtNumCotiz.getText(),anioCot);
            listaDetalleProforma();
        } catch (Exception ex) {
            Logger.getLogger(ProformaAPedido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonBuscarCotizacionActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        
        tabla = jTable1.getSelectedRow();
        item=jTable1.getValueAt(tabla, 1).toString();
        cant=jTable1.getValueAt(tabla, 2).toString();
        descripcion=jTable1.getValueAt(tabla, 3).toString();
        valorUnitario=jTable1.getValueAt(tabla, 4).toString();
        txtItem.setText(item);
        txtCantidad.setText(cant);
        txtDescripcion.setText(descripcion);
        txtValorUnitario.setText(valorUnitario);
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
//        procesar(1);
//        Pedidos.listaDetallePedido(Pedidos.idPedParaDetalle);
//        try {
//            Pedidos.consultarPago(Pedidos.idPedParaDetalle);
//        } catch (Exception ex) {
//            Logger.getLogger(ProformaAPedido.class.getName()).log(Level.SEVERE, null, ex);
//        }
//        limpiar();
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void txtNumCotizKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtNumCotizKeyPressed
        if(evt.getKeyCode()==KeyEvent.VK_ENTER){
             try {
                consultarProforma(txtNumCotiz.getText(),anioCot);
                listaDetalleProforma();
            } catch (Exception ex) {
            Logger.getLogger(ProformaAPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtNumCotizKeyPressed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonAgregarContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarContactoActionPerformed
        x=idCliente;
        if (x!= 0) {
            ContactosClientes conCliente = null;
            try {
                conCliente = new ContactosClientes();
            } catch (Exception ex) {
                Logger.getLogger(ProformaAPedido.class.getName()).log(Level.SEVERE, null, ex);
            }
            Principal.jDesktopPane1.add(conCliente);
            conCliente.toFront();
            conCliente.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Agregar Cliente");
        }
    }//GEN-LAST:event_buttonAgregarContactoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregarContacto;
    private javax.swing.JButton buttonBuscarCotizacion;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JComboBox<String> comboBoxContactoCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel labelNumPedido;
    private javax.swing.JTextField txtCantidad;
    private javax.swing.JTextField txtCliente;
    private javax.swing.JTextField txtDescripcion;
    private javax.swing.JTextField txtDetalleDescripcion;
    private javax.swing.JTextField txtItem;
    private javax.swing.JTextField txtNumCotiz;
    private javax.swing.JTextField txtValorUnitario;
    // End of variables declaration//GEN-END:variables

    ProformaController proformacontroler = new ProformaController();
    Proforma proforma;
   
    DetallePedidoController detallepedidocontroler = new DetallePedidoController();
    DetallePedido detallepedido;
    
    DetalleProformaController detalleproformacontroler = new DetalleProformaController();
    DetalleProforma detalleproforma;
    
    ClienteController clientecontroler = new ClienteController();
    Cliente cliente;

    ContactoClienteController contactoclientecontroler = new ContactoClienteController();
    ContactoCliente contactocliente;

    //----------------------------- Procesar -------------------------------------
//    private void procesar(int op) {
//        detallepedido = leerDatos();
//        try {
//            String msg = detallepedidocontroler.DetallePedidoProcesar(detallepedido, op);
//            JOptionPane.showMessageDialog(null, msg);
//        } catch (Exception e) {
//            //JOptionPane.showMessageDialog(null, e.getMessage());
//            System.out.println("Error" + e.getMessage());
//        }
//    }
    
    // -----------------------------Leer Datos ----------------------------------        
    private DetallePedido leerDatos() {

        DetallePedido detPed = new DetallePedido();
        
        
//        detPed.setIdPedido(Pedidos.idPedParaDetalle);
//        detPed.setItem(txtItem.getText());
//        detPed.setCantidad(txtCantidad.getText());
//        detPed.setDescripcion(txtDescripcion.getText());
//        detPed.setDetalleDescrip(txtDetalleDescripcion.getText());
//        detPed.setPrecioUnitario(txtValorUnitario.getText());
//        detPed.setIdDetallePedido(idDetPedido);

        return detPed;
    }
    
    //----------------------------- Consultar ------------------------------------
    
    private void consultarContactoCliente(String nombre, int iddecliente) throws Exception {

        contactocliente = contactoclientecontroler.ContactoClienteBuscarDni(nombre, iddecliente);
        if (contactocliente != null) {
            idConCli = contactocliente.getIdContactoCliente();
            System.out.println("idCliente"+idConCli);
            dni=contactocliente.getDni();
            atencion="ATENCION: "+nombre+" DNI:"+dni;
            System.out.println(atencion);

        } else {
            JOptionPane.showMessageDialog(null, "EL Cliente no tiene contactos, Registre un contacto!!!!");
            //System.out.println("Error");
        }
    } 
    
    private void consultarContactoCliente1(int idContacto) throws Exception {

        contactocliente = contactoclientecontroler.ContactoClienteBuscar2(idContacto);
        if (contactocliente != null) {

            nombre = contactocliente.getNombres();
            apellido = contactocliente.getApellidos();
            categoria = nombre + " " + apellido;
            System.out.println(categoria);

        } else {
            JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    } 
    
    private void consultarClientePorId(int iddecliente) throws Exception {
        cliente = clientecontroler.ClienteBuscar1(iddecliente);
        if (cliente != null) {

            //txtRazonSocial.setText(pro.getRazonSocial());
            txtCliente.setText(cliente.getRazonSocial());
       

        } else {
            JOptionPane.showMessageDialog(null, "Cliente no registrado por ahora");
            //System.out.println("Error");
        }
    }
    
    private void consultarProforma(String codigo, int anio) throws Exception {
        proforma = proformacontroler.ProformaBuscar(codigo, anio);
        if (proforma != null) {

           idProforma=proforma.getIdProforma();
            
           idCliente = proforma.getIdCliente();
         
            
           consultarClientePorId(idCliente);
            
           comboBoxContactoCli.setModel(proformacontroler.ListarCombodeContacto(txtCliente.getText(), idCliente));

            idConCli = proforma.getIdContactoCliente();
            System.out.println("idCCli:" + idConCli);
            consultarContactoCliente1(idConCli);
            String testValue1 = categoria;
            System.out.println("proforma contacto cliente:"+categoria);
            
            for (int i = 0; i < comboBoxContactoCli.getModel().getSize(); i++) {
                if (comboBoxContactoCli.getItemAt(i).equals(testValue1)) {
                    System.out.println("for "+i);
                    comboBoxContactoCli.setSelectedIndex(i);
                    break;
                }
            }

            consultarContactoCliente(categoria, idCliente);

          
        } else {
            JOptionPane.showMessageDialog(null, "Proforma no registrada");
            //System.out.println("Error");
        }
    }
    
 //----------------------------- Tabla ----------------------------------------
     void listaDetalleProforma() {
        List<DetalleProforma> lista;
        try {
            lista = detalleproformacontroler.DetalleProformaFiltrar(idProforma);
            verDetalleProforma(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verDetalleProforma(List<DetalleProforma> lista) {
        String it,vu,f;
        float uv;
        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (DetalleProforma detProf : lista) {
            if(!"".equals(detProf.getPrecioUnitario())){
                it=detProf.getImporte();
            }else{
                it="";
          }
            if(!"".equals(detProf.getPrecioUnitario())){
                f=detProf.getItem1();
            }else{
                f="";
          }
           
                Object[] fila = {detProf.getIdDetalleProforma(), detProf.getItem(), detProf.getCantidad(), detProf.getDescripcion(), detProf.getPrecioUnitario(), f, it};
                tabla.addRow(fila);
            }
        
    }    
    
//------------------------------- Limpiar --------------------------------------
    void limpiar() {
        txtItem.setText("");
        txtCantidad.setText("");
        txtDescripcion.setText("");
        txtDetalleDescripcion.setText("");
        txtValorUnitario.setText("");
    }

}

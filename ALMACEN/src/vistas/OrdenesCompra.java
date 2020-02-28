/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ContactoProveedorController;
import controller.DetalleOrdenCompraController;
import controller.OrdenCompraController;
import controller.ProveedorController;
import controller.TipoCambioController;
import controller.UsuarioController;
import database.AccesoDB;
import entity.ContactoProveedor;
import entity.DetalleOrdenCompra;
import entity.OrdenCompra;
import entity.Proveedor;
import entity.TipoCambio;
import entity.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reportes.GenerarReporte;

/**
 *
 * @author Diego Lopez
 */
public class OrdenesCompra extends javax.swing.JInternalFrame {

    /**
     * Creates new form OrdenCompraVista
     */
    private int idOrdenCompra;
    static int idOrdenCompraParaDetalle;
    static int numOrdenCompraParaDetalle;
    static String anioStatic;
    private int idProveedor;
    private int idContactoProveedor;
    private int idUsuario;
    private int numeroOrdenCompra;
    private String campo2 = "";

    private String comboEstadoOrdenCompra;
    private String comboUsuario;
    private String comboMoneda;
    private String comboContactoProveedor;
    
    private boolean mensaje=false;
    
    int tabla;
    static int idDetalleOrdenCompra;
    
    private int ultimoNum;
    private int generarNumOrd;
    
    String razSocialProveedor;

    ProveedorController proveedorcontroler = new ProveedorController();
    Proveedor proveedor;

    ContactoProveedorController contactoproveedorcontroler = new ContactoProveedorController();
    ContactoProveedor contactoproveedor;

    UsuarioController usuariocontroler = new UsuarioController();
    Usuario usuario;

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    private String q;

    public OrdenesCompra() throws SQLException, ClassNotFoundException, Exception {
        initComponents();
        
        CargarUsuarios();
        txtFechaEmision.setText(fechaActual());
        txtFechaEntrega.setText(fechaActual());
        txtAnio.setText(anioActual());
        consultarTipoCambio(fechaActual());
        txtDocExterno.setText("SEGÚN COT. N°");
        txtTiempoEntrega.setText("1 A 2 DIAS HABILES");
        habilitarInicio();
        
        comboBoxProveedor.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {

                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxProveedor.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    limpiarComboProveedor();
                    try {
                        consultarComboProveedor(comboBoxProveedor.getItemAt(0));
                        comboBoxContactoProveedor.setModel(contactoproveedorcontroler.ListarCombodeContacto(comboBoxProveedor.getItemAt(0), idProveedor));

                        //aCargarContactosCombo(cadenaEscrita);
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (comparar(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarComboProveedor(cadenaEscrita);
                            comboBoxContactoProveedor.setModel(contactoproveedorcontroler.ListarCombodeContacto(cadenaEscrita, idProveedor));

                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            for (int i = 0; i < comboBoxProveedor.getModel().getSize(); i++) {
                                if (comboBoxProveedor.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarComboProveedor(comboBoxProveedor.getItemAt(i));
                                    comboBoxContactoProveedor.setModel(contactoproveedorcontroler.ListarCombodeContacto(comboBoxProveedor.getItemAt(i), idProveedor));

                                    break;
                                }
                            }

                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxProveedor.setModel(proveedorcontroler.ProveedorListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxProveedor.getItemCount() > 0) {
                        comboBoxProveedor.getEditor().setItem(cadenaEscrita);
                        comboBoxProveedor.showPopup();
                    } else {
                        comboBoxProveedor.addItem(cadenaEscrita);
                    }
                }
            }
        });

        comboBoxUsuario.setSelectedIndex(1);
        comboBoxFormaPago.setSelectedIndex(3);
        comboBoxMoneda.setSelectedIndex(1);
        comboBoxEstadoOrdenCompra.setSelectedIndex(1);
        comboBoxLugarEntrega.setSelectedIndex(0);
    }

    public void limpiarComboProveedor() {
        comboBoxProveedor.setSelectedItem("");
        comboBoxContactoProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        txtRuc.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtTlf.setText("");
    }

    private boolean comparar(String cadena) {
        Object[] lista = comboBoxProveedor.getComponents();
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

        jLabel1 = new javax.swing.JLabel();
        labelIdOrdC = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtFechaEmision = new javax.swing.JTextField();
        buttonAgregarProveedor = new javax.swing.JButton();
        comboBoxProveedor = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        buttonAgregarContactoProveedor = new javax.swing.JButton();
        comboBoxContactoProveedor = new javax.swing.JComboBox<>();
        txtEmail = new javax.swing.JTextField();
        txtTlf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        comboBoxFormaPago = new javax.swing.JComboBox<>();
        txtTiempoEntrega = new javax.swing.JTextField();
        txtFechaEntrega = new javax.swing.JTextField();
        comboBoxUsuario = new javax.swing.JComboBox<>();
        comboBoxMoneda = new javax.swing.JComboBox<>();
        buttonNuevo = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        buttonAgregarDescripcion = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        txtCambioCompra = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        txtCambioVenta = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel17 = new javax.swing.JLabel();
        comboBoxEstadoOrdenCompra = new javax.swing.JComboBox<>();
        buttonPDF = new javax.swing.JButton();
        buttonImprimir = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        buttonCerrar = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        buttonAnterior = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        buttonBuscar = new javax.swing.JButton();
        buttonLimpiar = new javax.swing.JButton();
        txtAnio = new javax.swing.JTextField();
        txtNumOrdC = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtAreaObservacion = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        comboBoxLugarEntrega = new javax.swing.JComboBox<>();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        labelSubtotal = new javax.swing.JLabel();
        labelIgv = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtDocExterno = new javax.swing.JTextField();
        buttonRegistrarCompra = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro de Orden de Compra");

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel1.setText("N°");

        labelIdOrdC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelIdOrdC.setText("0000");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel3.setText("AÑO:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel4.setText("N°:");

        txtFechaEmision.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonAgregarProveedor.setText("Señor(es)");
        buttonAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarProveedorActionPerformed(evt);
            }
        });

        comboBoxProveedor.setEditable(true);

        jLabel6.setText("       R.U.C.");

        jLabel7.setText("     Direccion");

        buttonAgregarContactoProveedor.setText("Contacto");
        buttonAgregarContactoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarContactoProveedorActionPerformed(evt);
            }
        });

        comboBoxContactoProveedor.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxContactoProveedorItemStateChanged(evt);
            }
        });
        comboBoxContactoProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxContactoProveedorActionPerformed(evt);
            }
        });

        jLabel8.setText("      E-mail");

        jLabel9.setText("   Telefono");

        jLabel10.setText(" Moneda");

        jLabel13.setText("Fecha Entrega");

        jLabel11.setText("Elaborado Por");

        comboBoxFormaPago.setEditable(true);
        comboBoxFormaPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA A 30 DIAS", "FACTURA A 15 DIAS", "FACTURA A 45 DIAS", "CONTRA ENTREGA", "PREVIO DEPÓSITO", "50% DE ADELANTO Y SALDO CON FACTURA A 30 DIAS", "50% DE ADELANTO Y SALDO CON CHEQUE A 30 DIAS", "50% DE ADELANTO Y SALDO CONTRA ENTREGA.", "CHEQUE A 1 SEMANA", "CHEQUE A 30 DIAS", "LETRA A 30 DIAS" }));

        comboBoxUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxUsuarioItemStateChanged(evt);
            }
        });

        comboBoxMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "DOLARES AMERICANOS", "SOLES" }));
        comboBoxMoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxMonedaItemStateChanged(evt);
            }
        });
        comboBoxMoneda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxMonedaActionPerformed(evt);
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

        buttonAgregarDescripcion.setText("Agregar Detalle");
        buttonAgregarDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarDescripcionActionPerformed(evt);
            }
        });

        jLabel14.setText("Tipo de cambio del dia:");

        jLabel15.setText("Compra:");

        txtCambioCompra.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jLabel16.setText("Venta:");

        txtCambioVenta.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idOrdC", "Item", "Cant", "Descripcion", "PrecioUnit", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(1).setMinWidth(30);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(1).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(2).setMinWidth(30);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(70);
            jTable1.getColumnModel().getColumn(2).setMaxWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(570);
            jTable1.getColumnModel().getColumn(4).setMinWidth(30);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(220);
            jTable1.getColumnModel().getColumn(4).setMaxWidth(80);
            jTable1.getColumnModel().getColumn(5).setMinWidth(30);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(220);
            jTable1.getColumnModel().getColumn(5).setMaxWidth(80);
        }

        jLabel17.setText("Estado:");

        comboBoxEstadoOrdenCompra.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "APROBADO", "ANULADO" }));
        comboBoxEstadoOrdenCompra.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxEstadoOrdenCompraItemStateChanged(evt);
            }
        });

        buttonPDF.setText("PDF");
        buttonPDF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPDFActionPerformed(evt);
            }
        });

        buttonImprimir.setText("Imprimir");
        buttonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirActionPerformed(evt);
            }
        });

        jLabel18.setText("Observación:");

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        buttonSiguiente.setText("Siguiente>>");
        buttonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiguienteActionPerformed(evt);
            }
        });

        buttonAnterior.setText("<<Anterior");
        buttonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnteriorActionPerformed(evt);
            }
        });

        jLabel2.setText("Fecha Emisión");

        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        txtAnio.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtNumOrdC.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        txtAreaObservacion.setColumns(20);
        txtAreaObservacion.setRows(5);
        jScrollPane2.setViewportView(txtAreaObservacion);

        jLabel5.setText("Forma de Entrega");

        jLabel12.setText("Tiempo de Entrega");

        jLabel19.setText("Lugar de Entrega");

        comboBoxLugarEntrega.setEditable(true);
        comboBoxLugarEntrega.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MZA. D LOTE. 19 ASOC.VIV.SUIZA PERUANA LIMA - LIMA - LOS OLIVOS", "MZA. C LOTE. 32 APV. HUERTOS DE TUNGASUCA LIMA - LIMA - COMAS" }));

        jLabel20.setText("Subtotal:");

        jLabel21.setText("I.G.V.:");

        jLabel22.setText("Total:");

        labelSubtotal.setText("000000.00");

        labelIgv.setText("000000.00");

        labelTotal.setText("000000.00");

        jLabel23.setText("Doc. Externo");

        buttonRegistrarCompra.setText("Reg. Compra");
        buttonRegistrarCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarCompraActionPerformed(evt);
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonAgregarProveedor)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(buttonAgregarContactoProveedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                        .addGap(14, 14, 14)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(comboBoxContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 259, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 8, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel19, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 3, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel23)
                                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, 230, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboBoxEstadoOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxLugarEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTiempoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDocExterno, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(22, 22, 22)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(labelIdOrdC, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumOrdC, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBuscar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLimpiar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCambioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCambioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(514, 514, 514)
                                .addComponent(buttonAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                .addComponent(buttonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 504, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(149, 149, 149)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel22)
                                    .addComponent(jLabel20)
                                    .addComponent(jLabel21))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(labelIgv, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(labelTotal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addComponent(labelSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(buttonAgregarDescripcion, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonPDF, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRegistrarCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtFechaEmision)
                        .addComponent(jLabel14)
                        .addComponent(jLabel15)
                        .addComponent(txtCambioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel16)
                        .addComponent(txtCambioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel4)
                        .addComponent(txtNumOrdC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelIdOrdC, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(buttonBuscar)
                            .addComponent(buttonLimpiar))
                        .addComponent(txtAnio, javax.swing.GroupLayout.Alignment.LEADING)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(comboBoxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonNuevo)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel5)
                                .addComponent(comboBoxFormaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(buttonRegistrar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtTiempoEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel12))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(txtFechaEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(comboBoxLugarEntrega, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonAgregarDescripcion)
                                    .addComponent(jLabel23)
                                    .addComponent(txtDocExterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonAgregarProveedor)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel17)
                                .addComponent(comboBoxEstadoOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAgregarContactoProveedor)
                            .addComponent(comboBoxContactoProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel20)
                                            .addComponent(labelSubtotal))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel21)
                                            .addComponent(labelIgv))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(jLabel22)
                                            .addComponent(labelTotal)))
                                    .addComponent(jLabel18))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(buttonCerrar)
                                    .addComponent(buttonSiguiente)
                                    .addComponent(buttonAnterior))
                                .addContainerGap())
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonPDF)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistrarCompra)
                        .addContainerGap())))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboBoxMonedaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxMonedaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxMonedaActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        procesar(3);
        try {
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarEliminar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonAgregarDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarDescripcionActionPerformed
        DetalleOrdenCompraRegistrar detOrdC = new DetalleOrdenCompraRegistrar();
        Principal.jDesktopPane1.add(detOrdC);
        detOrdC.toFront();
        detOrdC.setVisible(true);

    }//GEN-LAST:event_buttonAgregarDescripcionActionPerformed

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void comboBoxContactoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxContactoProveedorActionPerformed
        try {
            consultarComboProveedor(comboBoxProveedor.getSelectedItem().toString().trim());
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarContactoProveedor(comboContactoProveedor, idProveedor);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxContactoProveedorActionPerformed

    private void comboBoxContactoProveedorItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxContactoProveedorItemStateChanged
        if (!"SELECCIONE".equals(comboMoneda)) {
            comboContactoProveedor = comboBoxContactoProveedor.getSelectedItem().toString();
            System.out.println(comboContactoProveedor);
        } else {
            comboContactoProveedor = "";
        }
    }//GEN-LAST:event_comboBoxContactoProveedorItemStateChanged

    private void comboBoxMonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxMonedaItemStateChanged
        if (!"SELECCIONE".equals(comboMoneda)) {
            comboMoneda = comboBoxMoneda.getSelectedItem().toString();
            System.out.println(comboMoneda);
        } else {
            comboMoneda = "";
        }
    }//GEN-LAST:event_comboBoxMonedaItemStateChanged

    private void comboBoxUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxUsuarioItemStateChanged
        if (comboBoxUsuario.getSelectedIndex() != 0) {
            comboUsuario = comboBoxUsuario.getSelectedItem().toString();
            System.out.println(comboUsuario);
            try {
                consultarUsuario();
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }
    }//GEN-LAST:event_comboBoxUsuarioItemStateChanged

    private void comboBoxEstadoOrdenCompraItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxEstadoOrdenCompraItemStateChanged
        if (comboBoxEstadoOrdenCompra.getSelectedIndex() != 0) {
            comboEstadoOrdenCompra = comboBoxEstadoOrdenCompra.getSelectedItem().toString();
            System.out.println(comboEstadoOrdenCompra);
        } else {
            comboEstadoOrdenCompra = "";

        }
    }//GEN-LAST:event_comboBoxEstadoOrdenCompraItemStateChanged

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
       
        if (comboBoxProveedor.getEditor().getItem().toString().equals("") ){
            JOptionPane.showMessageDialog(null, "Lllenar el campo de Proveedor");
            
        } else if(comboBoxContactoProveedor.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Elegir Contacto");
        
        }else{
            try {
                GenerarCodigo();
            } catch (Exception ex) {
                Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            procesar(1);
            numeroOrdenCompra = Integer.parseInt(txtNumOrdC.getText());
            try {
                consultarOrdenCompra(numeroOrdenCompra, Integer.parseInt(txtAnio.getText()));
            } catch (Exception ex) {
                Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarRegistrar();
        }
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        numeroOrdenCompra=Integer.parseInt(txtNumOrdC.getText());
        try {
            consultarOrdenCompra(numeroOrdenCompra, Integer.parseInt(txtAnio.getText()));
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
                
        listaDetalleOrdenCompra(idOrdenCompra);
        try {
            consultarPago(idOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        habilitarBuscar();
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        procesar(2);
        numeroOrdenCompra = Integer.parseInt(txtNumOrdC.getText());
        try {
            consultarOrdenCompra(numeroOrdenCompra, Integer.parseInt(txtAnio.getText()));
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleOrdenCompra(idOrdenCompra);
        try {
            consultarPago(idOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarGuardar();
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        try {
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
       habilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
        int numero;
        int anio;
        anio = Integer.parseInt(txtAnio.getText());
        numero = Integer.parseInt(txtNumOrdC.getText()) - 1;
        try {
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarOrdenCompra(numero, anio);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleOrdenCompra(idOrdenCompra);
        try {
            consultarPago(idOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscar();

        if (mensaje == true) {
            numero = numero + 1;
            try {
                limpiar();
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                consultarOrdenCompra(numero, anio);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleOrdenCompra(idOrdenCompra);
            try {
                consultarPago(idOrdenCompra);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarBuscar();

        }
        mensaje = false;
    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed
        int numero;
        int anio;
        anio=Integer.parseInt(txtAnio.getText());
        numero=Integer.parseInt(txtNumOrdC.getText())+1;
        try {
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarOrdenCompra(numero, anio);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleOrdenCompra(idOrdenCompra);
        try {
            consultarPago(idOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscar();
        
        if (mensaje == true) {
            numero = numero- 1;
            try {
                limpiar();
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                consultarOrdenCompra(numero, anio);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleOrdenCompra(idOrdenCompra);
            try {
                consultarPago(idOrdenCompra);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarBuscar();

        }
        mensaje = false;
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        try {
            limpiar();
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarNuevo();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
         tabla = jTable1.getSelectedRow();
         idDetalleOrdenCompra = Integer.parseInt(jTable1.getValueAt(tabla, 0).toString());
         
        DetalleOrdenCompraRegistrar detOrdC = new DetalleOrdenCompraRegistrar();
        Principal.jDesktopPane1.add(detOrdC);
        detOrdC.toFront();
        detOrdC.setVisible(true);
        
        DetalleOrdenCompraRegistrar.habilitarTabla();
        
        try {
            DetalleOrdenCompraRegistrar.consultar(idDetalleOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarProveedorActionPerformed
        Proveedores proveedorAdd = null;
        try {
            proveedorAdd = new Proveedores();
        } catch (Exception ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(proveedorAdd);
        proveedorAdd.toFront();
        proveedorAdd.setVisible(true);
    }//GEN-LAST:event_buttonAgregarProveedorActionPerformed

    private void buttonAgregarContactoProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarContactoProveedorActionPerformed
        Proveedores.y = idProveedor;
        if (Proveedores.y != 0) {
            RegistrarContactosProveedor contactoAdd = null;
            try {
                contactoAdd = new RegistrarContactosProveedor();
            } catch (Exception ex) {
                Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
            }
            Principal.jDesktopPane1.add(contactoAdd);
            contactoAdd.toFront();
            contactoAdd.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Agregar Cliente");

        }
    }//GEN-LAST:event_buttonAgregarContactoProveedorActionPerformed

    private void buttonPDFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPDFActionPerformed
         GenerarReporte ordC = new GenerarReporte();
         System.out.println("");
        try {
            ordC.ReporteOrdenCompraGuardar(idOrdenCompra,String.format("%06d", numeroOrdenCompra), txtFechaEmision.getText(), razSocialProveedor, txtDocExterno.getText());
        } catch (SQLException ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonPDFActionPerformed

    private void buttonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirActionPerformed
         GenerarReporte ordC = new GenerarReporte();
        try {
            ordC.ImprimirOrdenCompra(idOrdenCompra);
        } catch (SQLException ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonImprimirActionPerformed

    private void buttonRegistrarCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarCompraActionPerformed
        RegistrarCompras reCom = null;
        try {
            reCom = new RegistrarCompras();
        } catch (ParseException ex) {
            Logger.getLogger(OrdenesCompra.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(reCom);
        reCom.toFront();
        reCom.setVisible(true);
        RegistrarCompras.numOrdenCompraYAnioRegistro();
        dispose();
    }//GEN-LAST:event_buttonRegistrarCompraActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregarContactoProveedor;
    private javax.swing.JButton buttonAgregarDescripcion;
    private javax.swing.JButton buttonAgregarProveedor;
    private javax.swing.JButton buttonAnterior;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonImprimir;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonPDF;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonRegistrarCompra;
    private javax.swing.JButton buttonSiguiente;
    private javax.swing.JComboBox<String> comboBoxContactoProveedor;
    private javax.swing.JComboBox<String> comboBoxEstadoOrdenCompra;
    private javax.swing.JComboBox<String> comboBoxFormaPago;
    private javax.swing.JComboBox<String> comboBoxLugarEntrega;
    private javax.swing.JComboBox<String> comboBoxMoneda;
    private javax.swing.JComboBox<String> comboBoxProveedor;
    private javax.swing.JComboBox<String> comboBoxUsuario;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JLabel labelIdOrdC;
    public static javax.swing.JLabel labelIgv;
    public static javax.swing.JLabel labelSubtotal;
    public static javax.swing.JLabel labelTotal;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextArea txtAreaObservacion;
    private javax.swing.JTextField txtCambioCompra;
    private javax.swing.JTextField txtCambioVenta;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtDocExterno;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFechaEmision;
    private javax.swing.JTextField txtFechaEntrega;
    public static javax.swing.JTextField txtNumOrdC;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTiempoEntrega;
    private javax.swing.JTextField txtTlf;
    // End of variables declaration//GEN-END:variables

    OrdenCompraController ordencompracontroler = new OrdenCompraController();
    OrdenCompra ordencompra;

    static DetalleOrdenCompraController detalleordencompracontroler = new DetalleOrdenCompraController();
    static DetalleOrdenCompra detalleordencompra;

    TipoCambioController tipocambiocontroler = new TipoCambioController();
    TipoCambio tipocambio;

//------------------ comboBox cargar Usuarios ------------------------
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

//-------------------------------- Consultar combo Cliente -------------------------------------
    
    private void consultarComboProveedor(String razonsocial) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscar(razonsocial);
        if (proveedor != null) {
            //CREAR ID CLIENTE Y ASIGNARLE EL ID CLIENTE.
            idProveedor = proveedor.getIdProveedor();
            comboBoxProveedor.setSelectedItem(proveedor.getRazonSocial());
            txtRuc.setText(proveedor.getRuc());
            txtDireccion.setText(proveedor.getDireccion());

            System.out.println("Proveedor información: " + proveedor.getIdProveedor() + ", " + proveedor.getRazonSocial()
                    + ", " + proveedor.getRuc() + ", " + proveedor.getDireccion());

        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no esta registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarProveedor(int idP) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscarId(idP);
        if (proveedor != null) {
            
            idProveedor = proveedor.getIdProveedor();
            comboBoxProveedor.setSelectedItem(proveedor.getRazonSocial());
            razSocialProveedor=proveedor.getRazonSocial();
            txtRuc.setText(proveedor.getRuc());
            txtDireccion.setText(proveedor.getDireccion());

            System.out.println("Proveedor información: " + proveedor.getIdProveedor() + ", " + proveedor.getRazonSocial()
                    + ", " + proveedor.getRuc() + ", " + proveedor.getDireccion());

        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no esta registrado");
            //System.out.println("Error");
        }
    }

//-----------------------------Consultar contacto proveedor ------------------
    private void consultarContactoProveedor(String cbContacto, int idP) throws Exception {

        contactoproveedor = contactoproveedorcontroler.ContactoProveedorBuscar(cbContacto, idP);
        if (contactoproveedor != null) {
            idContactoProveedor = contactoproveedor.getIdContactoProveedor();
            txtTlf.setText(contactoproveedor.getTlf1());
            txtEmail.setText(contactoproveedor.getCorreo());

        } else {
            JOptionPane.showMessageDialog(null, "EL Proveedor no tiene contactos, Registre un contacto!!!!");
            //System.out.println("Error");
        }
    }
    
    private void consultarContactoProveedorId(int idCP) throws Exception {

        contactoproveedor = contactoproveedorcontroler.ContactoProveedorBuscar(idCP);
        if (contactoproveedor != null) {
            idContactoProveedor = contactoproveedor.getIdContactoProveedor();
            comboContactoProveedor=contactoproveedor.getNombres()+" "+contactoproveedor.getApellidos();
            
        } else {
            JOptionPane.showMessageDialog(null, "EL Proveedor no tiene contactos, Registre un contacto!!!!");
            //System.out.println("Error");
        }
    }

//--------------------------- Concultar Usuario -------------------------------    
    private void consultarUsuario() throws Exception {
        usuario = usuariocontroler.UsuarioBuscar1(comboUsuario);
        if (usuario != null) {

            idUsuario = usuario.getIdUsuario();
            System.out.println(idUsuario);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarUsuario(int idUs) throws Exception {
        usuario = usuariocontroler.UsuarioBuscar2(idUs);
        if (usuario != null) {
            comboUsuario = usuario.getNombres()+ " " +usuario.getApellidos();;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }

// -------------------------- Fecha --> Indica la fecha del dia --------------------
    public String fechaActual() {
        Date fecha = new Date();

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatofecha.format(fecha);
    }

    public String anioActual() {
        Date fecha = new Date();

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy");
        return formatofecha.format(fecha);
    }

//-------------------------------------- Extraer año a fecha -----------------------------------------
    public String anioActual(String f) throws ParseException {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;

        fechaDate = formato.parse(f);

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy");
        return formatofecha.format(fechaDate);

    }

//------------------------- Consultar Tipo de Cambio según fecha actual ---------------------
    private void consultarTipoCambio(String fecha) throws Exception {
        tipocambio = tipocambiocontroler.TipoCambioBuscarCambio(fecha);
        if (tipocambio != null) {

            txtCambioCompra.setText(Float.toString(tipocambio.getPrecioCompra()));
            txtCambioVenta.setText(Float.toString(tipocambio.getPrecioVenta()));

        } else {
            JOptionPane.showMessageDialog(null, "Operacion Invalida");
            //System.out.println("Error");
        }
    }

//-------------------- Generar Numero de Orden de Compra -------------------------

    private void consultarUltimoNumOC(int anio) throws Exception {
        ordencompra = ordencompracontroler.OrdenCompraBuscarUltimoNumOC(anio);
        if (ordencompra != null) {
            ultimoNum = ordencompra.getOc_num();
            System.out.println("ultimoId : " + ultimoNum);
        } else {
            numeroOrdenCompra = 0;
            generarNumOrd = numeroOrdenCompra;
            System.out.println("generar id por numPedido: " + generarNumOrd);
        }

    }
    
    private void GenerarCodigo() throws Exception {
        consultarUltimoNumOC(Integer.parseInt(txtAnio.getText()));
        generarNumOrd = ultimoNum + 1;
        txtNumOrdC.setText(String.format("%06d",generarNumOrd));
        
    }
//-------------------- PROCESAR (Registrar - Modificar - Eliminar)------------------

  void procesar(int op) {
        ordencompra = leerDatos();
        try {
                 String msg = ordencompracontroler.OrdenCompraProcesar(ordencompra, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    } 
  
  OrdenCompra leerDatos() {
        OrdenCompra ordcom = new OrdenCompra();
        
        System.out.println("entrando a leer datos");
        
        ordcom.setOc_id(idOrdenCompra);
//        numeroOrdenCompra=Integer.parseInt(txtNumOrdC.getText());
        ordcom.setOc_num(Integer.parseInt(txtNumOrdC.getText()));
        ordcom.setOc_fecha(txtFechaEmision.getText());
        ordcom.setOc_idProveedor_fk(idProveedor);
        ordcom.setOc_idContactoProv_fk(idContactoProveedor);
        ordcom.setOc_moneda(comboMoneda);
        ordcom.setOc_formaPago(comboBoxFormaPago.getEditor().getItem().toString());
        ordcom.setOc_tiempoEntrega(txtTiempoEntrega.getText());
        ordcom.setOc_fechaEntrega(txtFechaEntrega.getText());
        ordcom.setOc_docExterno(txtDocExterno.getText());
        ordcom.setOc_idUsuario_fk(idUsuario);
        ordcom.setOc_lugarEntrega(comboBoxLugarEntrega.getEditor().getItem().toString());
        ordcom.setOc_estado(comboEstadoOrdenCompra);
        ordcom.setOc_observacion(txtAreaObservacion.getText());
        ordcom.setOc_campoAdd2(campo2);
            
        return ordcom;
    }
  
//--------------------------- Consultar Orden Compra ---------------------------
  
  private void consultarOrdenCompra(int numordcompra, int aio) throws Exception {
        ordencompra = ordencompracontroler.OrdenCompraBuscar(numordcompra, aio);
        if (ordencompra != null) {
            
            idOrdenCompra = ordencompra.getOc_id();
            idOrdenCompraParaDetalle=ordencompra.getOc_id();
            labelIdOrdC.setText(Integer.toString(ordencompra.getOc_id()));
            txtNumOrdC.setText(String.format("%06d", ordencompra.getOc_num()));
            numeroOrdenCompra=ordencompra.getOc_num();
            numOrdenCompraParaDetalle=ordencompra.getOc_num();
            txtFechaEmision.setText(ordencompra.getOc_fecha());
            anioStatic=anioActual(ordencompra.getOc_fecha());
            consultarTipoCambio(ordencompra.getOc_fecha());
            idProveedor = ordencompra.getOc_idProveedor_fk();
            consultarProveedor(idProveedor);
            comboBoxContactoProveedor.setModel(contactoproveedorcontroler.ListarCombodeContacto(comboBoxProveedor.getEditor().getItem().toString(), idProveedor));

            idContactoProveedor = ordencompra.getOc_idContactoProv_fk();
            consultarContactoProveedorId(idContactoProveedor);
            
            String testValue1 = comboContactoProveedor;
            System.out.println("Orden Compra contacto proveedor:" + comboContactoProveedor);

            for (int i = 0; i < comboBoxContactoProveedor.getModel().getSize(); i++) {
                if (comboBoxContactoProveedor.getItemAt(i).equals(testValue1)) {
                    System.out.println("for " + i);
                    comboBoxContactoProveedor.setSelectedIndex(i);
                    System.out.println("for 3333333333");
                    break;
                }
            }
            consultarContactoProveedor(comboContactoProveedor, idProveedor);
            
            comboMoneda = ordencompra.getOc_moneda();
            System.out.println("moneda" + comboMoneda);
            String testValue3 = comboMoneda;
            for (int i = 0; i < comboBoxMoneda.getModel().getSize(); i++) {
                if (comboBoxMoneda.getItemAt(i).equals(testValue3)) {
                    System.out.println(i);
                    comboBoxMoneda.setSelectedIndex(i);
                    break;
                }
            }
            
            comboBoxFormaPago.setSelectedItem(ordencompra.getOc_formaPago());
            txtTiempoEntrega.setText(ordencompra.getOc_tiempoEntrega());
            txtFechaEntrega.setText(ordencompra.getOc_fechaEntrega());
            txtDocExterno.setText(ordencompra.getOc_docExterno());
            idUsuario=ordencompra.getOc_idUsuario_fk();
            consultarUsuario(idUsuario);
            
            String testValue2 = comboUsuario;
            for (int i = 0; i < comboBoxUsuario.getModel().getSize(); i++) {
                if (comboBoxUsuario.getItemAt(i).equals(testValue2)) {
                    System.out.println(i);
                    comboBoxUsuario.setSelectedIndex(i);
                    break;
                }
            }
            
            comboBoxLugarEntrega.setSelectedItem(ordencompra.getOc_lugarEntrega());
            
            comboEstadoOrdenCompra=ordencompra.getOc_estado();
            System.out.println("Estado de orden de compra" +comboEstadoOrdenCompra);

            String testValue4 = comboEstadoOrdenCompra;
            
            for (int i = 0; i < comboBoxEstadoOrdenCompra.getModel().getSize(); i++) {
                if (comboBoxEstadoOrdenCompra.getItemAt(i).equals(testValue4)) {
                    System.out.println(i);
                    comboBoxEstadoOrdenCompra.setSelectedIndex(i);
                    break;
                }else {
                    comboBoxEstadoOrdenCompra.setSelectedIndex(0);
                }
            }
            
            txtAreaObservacion.setText(ordencompra.getOc_observacion());
        } else {
            JOptionPane.showMessageDialog(null, "Orden de Compra no registrado");
            System.out.println("Orden de Compra no registrado");
            mensaje=true;
        }
    }

//--------------------------- Consultar Pago ---------------------------------
    
    static void consultarPago(int idoc) throws Exception {
        detalleordencompra = detalleordencompracontroler.DetalleOrdenCompraTotal(idoc);

        if (detalleordencompra != null) {
            if (detalleordencompra.getSubtotal() != null) {
                labelSubtotal.setText(detalleordencompra.getSubtotal());
            } else {
                labelSubtotal.setText("000000.00");
            }
            if (detalleordencompra.getIgv() != null) {
                labelIgv.setText(detalleordencompra.getIgv());
            } else {
                labelIgv.setText("000000.00");
            }
            if (detalleordencompra.getTotal() != null) {
                labelTotal.setText(detalleordencompra.getTotal());
            } else {
                labelTotal.setText("000000.00");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Cuentas no registradas");
            //System.out.println("Error");
        }
    }  
    
//------------------------- Lista detalle Pedido ---------------------------
    static void listaDetalleOrdenCompra(int id) {
        List<DetalleOrdenCompra> lista;
        try {
            lista = detalleordencompracontroler.DetalleOrdenCompraFiltrar(id);
            verDetalleOrdenCompra(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

     static private void verDetalleOrdenCompra(List<DetalleOrdenCompra> lista) {
       String it, f;
        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (DetalleOrdenCompra detoc : lista) {
            if (!"".equals(detoc.getDoc_precioUnit())) {
                it = detoc.getDoc_total();
            } else {
                it = "";
            }
        
            Object[] fila = {detoc.getDoc_id(), detoc.getDoc_item(), detoc.getDoc_cantidad(), 
                             detoc.getDoc_desc(), detoc.getDoc_precioUnit(),it};
            tabla.addRow(fila);
        }

    }
     
//---------------------------------- numOrdenCompra desde Vista ------------------------
     
    static void NumOrdenCompra() {
       System.out.println(OrdenCompraVista.numOrdenCompraParaModificar);
       txtNumOrdC.setText(String.format("%06d",OrdenCompraVista.numOrdenCompraParaModificar ));     
    }

//------------------------------------- Limpiar ---------------------------------------
  
    void limpiar() throws Exception {
        
        labelIdOrdC.setText("0000");
        txtAnio.setText(anioActual());
        txtNumOrdC.setText("");
        txtFechaEmision.setText(fechaActual());
        comboBoxProveedor.setSelectedItem("");
        txtRuc.setText("");
        txtDireccion.setText("");
        comboBoxContactoProveedor.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        txtEmail.setText("");
        txtTlf.setText("");
        comboBoxMoneda.setSelectedIndex(1);
        comboBoxFormaPago.setSelectedIndex(3);
        txtTiempoEntrega.setText("1 A 2 DIAS HABILES");
        txtFechaEntrega.setText(fechaActual());
        comboBoxLugarEntrega.setSelectedIndex(0);
        comboBoxUsuario.setSelectedIndex(1);
        txtAreaObservacion.setText("");
        comboBoxEstadoOrdenCompra.setSelectedIndex(1);
        idOrdenCompra = 0;
        idProveedor = 0;
        idContactoProveedor = 0;
        listaDetalleOrdenCompra(0);
        labelSubtotal.setText("000000.00");
        labelIgv.setText("000000.00");
        labelTotal.setText("000000.00");
        consultarTipoCambio(fechaActual());
        txtDocExterno.setText("SEGÚN COT. N°");
    }
    
//------------------------------- Habilitar ------------------------------------
    void habilitarInicio(){
        
        txtAnio.setEnabled(true);
        txtNumOrdC.setEnabled(true);
        txtFechaEmision.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoProveedor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        comboBoxFormaPago.setEnabled(false);
        txtTiempoEntrega.setEnabled(false);
        txtFechaEntrega.setEnabled(false);
        txtDocExterno.setEnabled(false);
        comboBoxLugarEntrega.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtAreaObservacion.setEnabled(false);
        comboBoxEstadoOrdenCompra.setEnabled(false);

        buttonBuscar.setEnabled(true);

        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);

        buttonAgregarDescripcion.setEnabled(false);

        buttonPDF.setEnabled(false);
        buttonImprimir.setEnabled(false);

        buttonAnterior.setEnabled(false);
        buttonSiguiente.setEnabled(false);
    }
    
    void habilitarNuevo(){
        
        txtAnio.setEnabled(true);
        txtNumOrdC.setEnabled(true);
        txtFechaEmision.setEnabled(true);
        comboBoxProveedor.setEnabled(true);
        txtRuc.setEnabled(true);
        txtDireccion.setEnabled(true);
        comboBoxContactoProveedor.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTlf.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        comboBoxFormaPago.setEnabled(true);
        txtTiempoEntrega.setEnabled(true);
        txtFechaEntrega.setEnabled(true);
        txtDocExterno.setEnabled(true);
        comboBoxLugarEntrega.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        txtAreaObservacion.setEnabled(true);
        comboBoxEstadoOrdenCompra.setEnabled(true);

        buttonBuscar.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);

        buttonAgregarDescripcion.setEnabled(false);

        buttonPDF.setEnabled(false);
        buttonImprimir.setEnabled(false);

        buttonAnterior.setEnabled(false);
        buttonSiguiente.setEnabled(false);
    }
    
    void habilitarRegistrar(){
        
        txtAnio.setEnabled(false);
        txtNumOrdC.setEnabled(false);
        txtFechaEmision.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoProveedor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        comboBoxFormaPago.setEnabled(false);
        txtTiempoEntrega.setEnabled(false);
        txtFechaEntrega.setEnabled(false);
        txtDocExterno.setEnabled(false);
        comboBoxLugarEntrega.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtAreaObservacion.setEnabled(false);
        comboBoxEstadoOrdenCompra.setEnabled(false);

        buttonBuscar.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);

        buttonAgregarDescripcion.setEnabled(true);

        buttonPDF.setEnabled(true);
        buttonImprimir.setEnabled(true);

        buttonAnterior.setEnabled(true);
        buttonSiguiente.setEnabled(true);
    }
    
    void habilitarModificar(){
        
        txtAnio.setEnabled(false);
        txtNumOrdC.setEnabled(true);
        txtFechaEmision.setEnabled(true);
        comboBoxProveedor.setEnabled(true);
        txtRuc.setEnabled(true);
        txtDireccion.setEnabled(true);
        comboBoxContactoProveedor.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTlf.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        comboBoxFormaPago.setEnabled(true);
        txtTiempoEntrega.setEnabled(true);
        txtFechaEntrega.setEnabled(true);
        txtDocExterno.setEnabled(true);
        comboBoxLugarEntrega.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        txtAreaObservacion.setEnabled(true);
        comboBoxEstadoOrdenCompra.setEnabled(true);

        buttonBuscar.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);

        buttonAgregarDescripcion.setEnabled(false);

        buttonPDF.setEnabled(false);
        buttonImprimir.setEnabled(false);

        buttonAnterior.setEnabled(false);
        buttonSiguiente.setEnabled(false);
    }
    
    void habilitarGuardar(){
        
        txtAnio.setEnabled(false);
        txtNumOrdC.setEnabled(false);
        txtFechaEmision.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoProveedor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        comboBoxFormaPago.setEnabled(false);
        txtTiempoEntrega.setEnabled(false);
        txtFechaEntrega.setEnabled(false);
        txtDocExterno.setEnabled(false);
        comboBoxLugarEntrega.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtAreaObservacion.setEnabled(false);
        comboBoxEstadoOrdenCompra.setEnabled(false);

        buttonBuscar.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);

        buttonAgregarDescripcion.setEnabled(true);

        buttonPDF.setEnabled(true);
        buttonImprimir.setEnabled(true);

        buttonAnterior.setEnabled(true);
        buttonSiguiente.setEnabled(true);
    }
    
    void habilitarEliminar(){
        
        txtAnio.setEnabled(true);
        txtNumOrdC.setEnabled(true);
        txtFechaEmision.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoProveedor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        comboBoxFormaPago.setEnabled(false);
        txtTiempoEntrega.setEnabled(false);
        txtFechaEntrega.setEnabled(false);
        txtDocExterno.setEnabled(false);
        comboBoxLugarEntrega.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtAreaObservacion.setEnabled(false);
        comboBoxEstadoOrdenCompra.setEnabled(false);

        buttonBuscar.setEnabled(true);

        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);

        buttonAgregarDescripcion.setEnabled(false);

        buttonPDF.setEnabled(false);
        buttonImprimir.setEnabled(false);

        buttonAnterior.setEnabled(false);
        buttonSiguiente.setEnabled(false);
    }
    
    void habilitarBuscar(){
        
        txtAnio.setEnabled(false);
        txtNumOrdC.setEnabled(false);
        txtFechaEmision.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoProveedor.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        comboBoxFormaPago.setEnabled(false);
        txtTiempoEntrega.setEnabled(false);
        txtFechaEntrega.setEnabled(false);
        txtDocExterno.setEnabled(false);
        comboBoxLugarEntrega.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtAreaObservacion.setEnabled(false);
        comboBoxEstadoOrdenCompra.setEnabled(false);

        buttonBuscar.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);

        buttonAgregarDescripcion.setEnabled(true);

        buttonPDF.setEnabled(true);
        buttonImprimir.setEnabled(true);

        buttonAnterior.setEnabled(true);
        buttonSiguiente.setEnabled(true);
    }
  
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ClienteController;
import controller.ContactoClienteController;
import controller.DetallePedidoController;
import controller.DetalleProformaController;
import controller.PedidoController;
import controller.ProformaController;
import controller.TipoCambioController;
import controller.UsuarioController;
import database.AccesoDB;
import entity.Cliente;
import entity.ContactoCliente;
import entity.DetallePedido;
import entity.DetalleProforma;
import entity.Pedido;
import entity.Proforma;
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
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.log4j.BasicConfigurator;

/**
 *
 * @author ARCRODINPC-02
 */
public final class Pedidos extends javax.swing.JInternalFrame {

//    private static String url = "tcp://192.168.1.3:61616";
    private static String url = "tcp://localhost:61616";
    private static String subject = "Almacen";

    int idPedido;
    static int idPedParaDetalle;
    int idProf;
    String codProforma;
    static String codProformaParaDetalle;
    int idCliente;
    int idClienteDeCombo;
    int idContactoCliente;
    String comboContacto;
    int idUs; //id del usuario
    String comboUsuario;
    String moneda;
    String tipo;// si es servicio o producto
    String comboMoneda;
    String comboTipo;
    int numPedido;
    static int numPedParaDetalle;
    String estadoPedido = "EN PROCESO";
    String observacionInterna = "", campo2 = "", campo3 = "";
    int dia1, dia2;

    String formPago; //Proforma
    String tiempoEntrega;//Proforma
    String detracion;//Proforma
    String observacion;//Proforma
    String estadoProf;//Proforma
    String fechaProf;//Proforma
    String validezProf;//Proforma

    String nombre;
    String apellido;
    String nombreUs;
    String apellidoUs;

    int profDia1;
    int profDia2;

    int tabla;
    int idDetallePedido;

    boolean mensaje = false;

    Connection conexion;
    CallableStatement cs = null;
    ResultSet rs = null;
    Statement s = null;
    PreparedStatement ps = null;
    ContactoCliente contCli;
    String q;

    int generarId;
    int ultimoId;

    static int anioParaDetalleAPedido;

    ProformaController proformacontroler = new ProformaController();
    Proforma proforma;

    ClienteController clientecontroler = new ClienteController();
    Cliente cliente;

    ContactoClienteController contactoclientecontroler = new ContactoClienteController();
    ContactoCliente contactocliente;

    UsuarioController usuariocontroler = new UsuarioController();
    Usuario usuario;

    /**
     * Creates new form Pedido
     *
     * @throws java.sql.SQLException
     * @throws java.lang.ClassNotFoundException
     */
    public Pedidos() throws SQLException, ClassNotFoundException, Exception {
        initComponents();
        CargarUsuarios();
        txtFecha.setText(fechaActual());
        txtAnio.setText(anioActual());
        txtAnioCot.setText(anioActual());
        consultarTipoCambio(txtFecha.getText());
        deshabilitarInicio();

        comboBoxRazSocial.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

            @Override
            public void keyReleased(KeyEvent evt) {

                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxRazSocial.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    limpiarComboCliente();
                    try {
                        consultarComboCliente(comboBoxRazSocial.getItemAt(0));
                        comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboBoxRazSocial.getItemAt(0), idCliente));

                        //aCargarContactosCombo(cadenaEscrita);
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (comparar(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarComboCliente(cadenaEscrita);
                            comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(cadenaEscrita, idCliente));

                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            for (int i = 0; i < comboBoxRazSocial.getModel().getSize(); i++) {
                                if (comboBoxRazSocial.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarComboCliente(comboBoxRazSocial.getItemAt(i));
                                    comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboBoxRazSocial.getItemAt(i), idCliente));

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
                        comboBoxRazSocial.setModel(clientecontroler.ClienteListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxRazSocial.getItemCount() > 0) {
                        comboBoxRazSocial.getEditor().setItem(cadenaEscrita);
                        comboBoxRazSocial.showPopup();
                    } else {
                        comboBoxRazSocial.addItem(cadenaEscrita);
                    }
                }
            }
        });

        comboBoxTipo.setSelectedIndex(1);
        comboBoxMoneda.setSelectedIndex(1);
        comboBoxUsuario.setSelectedIndex(1);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtCodProforma = new javax.swing.JTextField();
        buttonBuscarCodProf = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtNumPedido = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        buttonCerrar = new javax.swing.JButton();
        buttonNuevo = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        labelSubtotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        labelIgv = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboBoxContactoCliente = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtTlf = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        comboBoxMoneda = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        txtFormPago = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        comboBoxTipo = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        comboBoxUsuario = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        buttonBuscarPedido = new javax.swing.JButton();
        buttonLimpiarTodo = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        buttonNumCot = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtDia2 = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        comboBoxRazSocial = new javax.swing.JComboBox<>();
        txtDia1 = new javax.swing.JTextField();
        buttonAnterior = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        jLabel26 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        jLabel27 = new javax.swing.JLabel();
        txtAnioCot = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        buttonGenerarOrdenCorte = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        labelCambioCompra = new javax.swing.JLabel();
        labelCambioVenta = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        txtOrdenCompra = new javax.swing.JTextField();
        recibircotizacion = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro de Pedido");

        txtCodProforma.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonBuscarCodProf.setText("Buscar");
        buttonBuscarCodProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarCodProfActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idDetPed", "Item", "Cantidad", "Descripcion", "Valor Unitario", "Precio Unitario", "Importe"
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(46);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(61);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(500);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(110);
        }

        jLabel2.setText("Num. Pedido:");

        txtNumPedido.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        jLabel4.setText("Razon Social:");

        jLabel5.setText("R.U.C.:");

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
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

        jLabel7.setText("Subtotal:");

        labelSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSubtotal.setText("000000.00");

        jLabel9.setText("I.G.V.(18%):");

        labelIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelIgv.setText("000000.00");

        jLabel11.setText("Total:");

        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal.setText("000000.00");

        jLabel8.setText("Atencion: ");

        comboBoxContactoCliente.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxContactoClienteItemStateChanged(evt);
            }
        });
        comboBoxContactoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxContactoClienteActionPerformed(evt);
            }
        });

        jLabel10.setText("E-mail:");

        jLabel12.setText("Telefono:");

        jLabel13.setText("Moneda:");

        comboBoxMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "DOLARES AMERICANOS", "SOLES" }));
        comboBoxMoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxMonedaItemStateChanged(evt);
            }
        });

        jLabel14.setText("Forma de Pago: ");

        jLabel15.setText("Tipo:");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "PRODUCTO", "SERVICIO" }));
        comboBoxTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTipoItemStateChanged(evt);
            }
        });

        jLabel16.setText("Fecha de Emision:");

        txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel17.setText("Elab. por: ");

        comboBoxUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxUsuarioItemStateChanged(evt);
            }
        });

        jLabel18.setText("Observacion: ");

        jLabel19.setText("Tiempo de Entrega:");

        buttonBuscarPedido.setText("Buscar");
        buttonBuscarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarPedidoActionPerformed(evt);
            }
        });

        buttonLimpiarTodo.setText("Limpiar Todo");
        buttonLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarTodoActionPerformed(evt);
            }
        });

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane2.setViewportView(txtObservacion);

        buttonNumCot.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonNumCot.setText("Num. Cotización");
        buttonNumCot.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNumCotActionPerformed(evt);
            }
        });

        jLabel21.setText("A");

        jLabel1.setText("DIAS HABILES");

        comboBoxRazSocial.setEditable(true);

        buttonAnterior.setText("<<Anterior");
        buttonAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAnteriorActionPerformed(evt);
            }
        });

        buttonSiguiente.setText("Siguiente>>");
        buttonSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSiguienteActionPerformed(evt);
            }
        });

        jLabel26.setText("Año:");

        txtAnio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel27.setText("Año:");

        jLabel28.setText("Dirección:");

        buttonGenerarOrdenCorte.setText("Generar Corte");
        buttonGenerarOrdenCorte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerarOrdenCorteActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setText("Tipo de Cambio:");

        jLabel24.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel24.setText("Compra:");

        jLabel25.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel25.setText("Venta:");

        labelCambioCompra.setText("00.00");

        labelCambioVenta.setText("00.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel24, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelCambioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCambioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(jLabel3)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel24)
                    .addComponent(labelCambioCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel25)
                    .addComponent(labelCambioVenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel6.setText("Orden de Compra:");

        recibircotizacion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        recibircotizacion.setText("Recibir COT");
        recibircotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                recibircotizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel10)
                                    .addComponent(jLabel28))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxContactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel22)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel14)
                                                    .addComponent(jLabel13))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(txtFormPago, javax.swing.GroupLayout.PREFERRED_SIZE, 164, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                .addGap(11, 11, 11))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                    .addComponent(jLabel15)
                                                    .addComponent(jLabel19)
                                                    .addComponent(jLabel17)
                                                    .addComponent(jLabel6))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                        .addComponent(jLabel21, javax.swing.GroupLayout.PREFERRED_SIZE, 7, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel1))
                                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtOrdenCompra, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(comboBoxTipo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                                        .addGap(38, 38, 38)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 84, Short.MAX_VALUE))))
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 408, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                .addGroup(layout.createSequentialGroup()
                                                    .addComponent(buttonGenerarOrdenCorte, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                    .addGap(1, 1, 1))
                                                .addComponent(buttonNuevo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(buttonRegistrar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(buttonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(buttonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(recibircotizacion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(jLabel27)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnioCot, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonNumCot)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCodProforma, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBuscarCodProf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonBuscarPedido)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonLimpiarTodo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(595, 595, 595)
                        .addComponent(buttonAnterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtCodProforma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscarCodProf)
                    .addComponent(jLabel2)
                    .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonBuscarPedido)
                    .addComponent(buttonNumCot)
                    .addComponent(jLabel16)
                    .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel26)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiarTodo)
                    .addComponent(jLabel27)
                    .addComponent(txtAnioCot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jLabel13)
                            .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxRazSocial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(91, 91, 91)
                                .addComponent(jLabel22))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(txtFormPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(jLabel14)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(jLabel28)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel21)
                                    .addComponent(txtDia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel8)
                                    .addComponent(comboBoxContactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(txtOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel10)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel17)
                                        .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(jLabel12)
                                        .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminar))
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addComponent(buttonGenerarOrdenCorte)
                        .addGap(20, 20, 20)
                        .addComponent(recibircotizacion)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(labelSubtotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(labelIgv))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(labelTotal))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 27, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonCerrar)
                            .addComponent(buttonSiguiente)
                            .addComponent(buttonAnterior)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel18)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void comboBoxContactoClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxContactoClienteItemStateChanged
        if (!"Elegir Contacto".equals(comboContacto)) {
            comboContacto = comboBoxContactoCliente.getSelectedItem().toString();
            System.out.println(comboContacto);
        } else {
            comboContacto = "";
        }
    }//GEN-LAST:event_comboBoxContactoClienteItemStateChanged

    private void comboBoxContactoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxContactoClienteActionPerformed
        try {
            consultarContactoCliente();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxContactoClienteActionPerformed

    private void comboBoxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTipoItemStateChanged
        if (comboBoxTipo.getSelectedIndex() != 0) {
            comboTipo = comboBoxTipo.getSelectedItem().toString();
            System.out.println(comboTipo);
        } else {
        }
    }//GEN-LAST:event_comboBoxTipoItemStateChanged

    private void comboBoxMonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxMonedaItemStateChanged
        if (comboBoxMoneda.getSelectedIndex() != 0) {
            comboMoneda = comboBoxMoneda.getSelectedItem().toString();
            System.out.println(comboMoneda);
        } else {
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

    private void buttonBuscarCodProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarCodProfActionPerformed
        String numeroProforma = String.format("%06d", (Integer.parseInt(txtCodProforma.getText())));
        try {
            consultarProforma(numeroProforma, Integer.parseInt(txtAnioCot.getText()));
            codProformaParaDetalle = txtCodProforma.getText();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("11111idProformaBuscar: " + idProf);
        listaDetalleProforma(idProf);
        try {
            consultarCalculoProforma(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            GenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        procesar(1);
        try {
            consultarPedido(Integer.parseInt(txtNumPedido.getText()), Integer.parseInt(txtAnio.getText()));
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarRegistrar();

    }//GEN-LAST:event_buttonBuscarCodProfActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        try {
            GenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("".equals(txtDia1.getText()) && "".equals(txtDia2.getText())) {
            JOptionPane.showMessageDialog(null, "No puede dejar espacios en blanco");
        } else {
            System.out.println("ent prof");
            procesarProforma(2);
            System.out.println("sal prof");
            procesar(1);
            try {
                consultarPedido(Integer.parseInt(txtNumPedido.getText()), Integer.parseInt(txtAnio.getText()));
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarRegistrar();
        }
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        procesar(3);
        habilitarEliminar();
        try {
            limpiarTodo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        System.out.println("numero de pedido en guardar:" + numPedido);
        procesar(2);
        System.out.println("numero de pedido despues de procesar:" + numPedido);
        try {
            consultarPedido(Integer.parseInt(txtNumPedido.getText()), Integer.parseInt(txtAnio.getText()));
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleProforma(idProf);
        try {
            consultarCalculoProforma(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarGuardar();

    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonBuscarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarPedidoActionPerformed

        try {
            consultarPedido(Integer.parseInt(txtNumPedido.getText()), Integer.parseInt(txtAnio.getText()));
            idPedParaDetalle = idPedido;
            System.out.println("numpedido en buscar: " + numPedido);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("idPedido" + idPedido);
        listaDetalleProforma(idProf);
        try {
            consultarCalculoProforma(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscarPedido();
    }//GEN-LAST:event_buttonBuscarPedidoActionPerformed

    private void buttonNumCotActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNumCotActionPerformed
        CotizacionesParaPedido ped = new CotizacionesParaPedido();
        Principal.jDesktopPane1.add(ped);
        ped.toFront();
        ped.setVisible(true);
    }//GEN-LAST:event_buttonNumCotActionPerformed

    private void buttonLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarTodoActionPerformed
        try {
            limpiarTodo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        deshabilitarInicio();
    }//GEN-LAST:event_buttonLimpiarTodoActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        numPedido = 0;
        idPedido = 0;
        habilitarNuevo();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed
        int numero;
        int anio;
        anio = Integer.parseInt(txtAnio.getText());
        numero = Integer.parseInt(txtNumPedido.getText()) + 1;
        try {
            limpiarTodo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarPedido(numero, anio);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleProforma(idProf);
        try {
            consultarCalculoProforma(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscarPedido();

        if (mensaje == true) {
            numero = numero - 1;
            try {
                limpiarTodo();
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                consultarPedido(numero, anio);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleProforma(idProf);
            try {
                consultarCalculoProforma(idProf);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarBuscarPedido();

        }
        mensaje = false;
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
        int numero;
        int anio;
        anio = Integer.parseInt(txtAnio.getText());
        numero = Integer.parseInt(txtNumPedido.getText()) - 1;
        try {
            limpiarTodo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarPedido(numero, anio);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleProforma(idProf);
        try {
            consultarCalculoProforma(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscarPedido();

        if (mensaje == true) {
            numero = numero + 1;
            try {
                limpiarTodo();
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                consultarPedido(numero, anio);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleProforma(idProf);
            try {
                consultarCalculoProforma(idProf);
            } catch (Exception ex) {
                Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarBuscarPedido();

        }
        mensaje = false;
    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonGenerarOrdenCorteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerarOrdenCorteActionPerformed
        OrdenesCorte ped = new OrdenesCorte();
        Principal.jDesktopPane1.add(ped);
        ped.toFront();
        ped.setVisible(true);
        OrdenesCorte.numDesdePedido();
    }//GEN-LAST:event_buttonGenerarOrdenCorteActionPerformed

    private void recibircotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_recibircotizacionActionPerformed

        BasicConfigurator.configure();

        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory(url);
        javax.jms.Connection connection = null;
        try {
            connection = connectionFactory.createConnection();
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            connection.start();
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }

        Session session = null;
        try {
            session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }

        Destination destination = null;
        try {
            destination = session.createQueue(subject);
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }

        MessageConsumer consumer = null;
        try {
            consumer = session.createConsumer(destination);
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }

        Message message = null;

        try {
            message = consumer.receive();
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (message instanceof TextMessage) {
            TextMessage textMessage = (TextMessage) message;
            try {

                String numerocot = null;
                numerocot = textMessage.getText(); // recibe el mensaje y lo carga

               String numeroProforma = String.format("%06d", (Integer.parseInt(numerocot)));
        try {
            consultarProforma(numeroProforma, Integer.parseInt(txtAnioCot.getText()));
            codProformaParaDetalle = txtCodProforma.getText();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("11111idProformaBuscar: " + idProf);
        listaDetalleProforma(idProf);
        try {
            consultarCalculoProforma(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            GenerarCodigo();
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        procesar(1);
        try {
            consultarPedido(Integer.parseInt(txtNumPedido.getText()), Integer.parseInt(txtAnio.getText()));
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarRegistrar();
                
                
                
                
                
                
            } catch (JMSException ex) {
                Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                //            JOptionPane.showMessageDialog(null, "Recepciona mensaje: '" + textMessage.getText() + "'");
                System.out.println("Recepciona mensaje: '" + textMessage.getText() + "'");
            } catch (JMSException ex) {
                Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        try {
            connection.close();
        } catch (JMSException ex) {
            Logger.getLogger(PedidosVista.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_recibircotizacionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAnterior;
    private javax.swing.JButton buttonBuscarCodProf;
    private javax.swing.JButton buttonBuscarPedido;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGenerarOrdenCorte;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonLimpiarTodo;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonNumCot;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonSiguiente;
    private static javax.swing.JComboBox<String> comboBoxContactoCliente;
    private javax.swing.JComboBox<String> comboBoxMoneda;
    private javax.swing.JComboBox<String> comboBoxRazSocial;
    private javax.swing.JComboBox<String> comboBoxTipo;
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
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    private javax.swing.JLabel labelCambioCompra;
    private javax.swing.JLabel labelCambioVenta;
    public static javax.swing.JLabel labelIgv;
    public static javax.swing.JLabel labelSubtotal;
    public static javax.swing.JLabel labelTotal;
    private javax.swing.JButton recibircotizacion;
    public static javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtAnioCot;
    public static javax.swing.JTextField txtCodProforma;
    private javax.swing.JTextField txtDia1;
    private javax.swing.JTextField txtDia2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtFecha;
    private javax.swing.JTextField txtFormPago;
    public static javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtOrdenCompra;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTlf;
    // End of variables declaration//GEN-END:variables

    static DetallePedidoController detallepedidocontroler = new DetallePedidoController();
    static DetallePedido detallePedido;

    TipoCambioController tipCamControl = new TipoCambioController();
    TipoCambio tipCam;

    PedidoController pedidocontroler = new PedidoController();
    Pedido pedido;

    DetalleProformaController detalleproformacontroler = new DetalleProformaController();
    DetalleProforma detalleproforma;

//------------------------ Procesar -------------------------------------
    void procesar(int op) {
        pedido = leerDatos();
        try {
            String msg = pedidocontroler.PedidoProcesar(pedido, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }

    //----------------------- Leer Datos ----------------------------------------
    Pedido leerDatos() {
        Pedido ped = new Pedido();
        System.out.println("entrando a leer datos");
        ped.setIdPedido(idPedido);
        System.out.println("el idPedido de leer datos:" + idPedido);
        ped.setNumPedido(Integer.parseInt(txtNumPedido.getText()));
        ped.setIdProforma(idProf);
        ped.setIdCliente(idCliente);
        ped.setIdContactoCliente(idContactoCliente);
        ped.setIdUsuario(idUs);
        ped.setMoneda(comboMoneda);
        ped.setFormPago(txtFormPago.getText());
        ped.setTipo(comboTipo);
        ped.setFechaEmision(txtFecha.getText());
        ped.setDia1(Integer.parseInt(txtDia1.getText()));
        ped.setDia2(Integer.parseInt(txtDia2.getText()));
        ped.setOrdenCompra(txtOrdenCompra.getText());
        ped.setEstado(estadoPedido);
        ped.setObservacion(txtObservacion.getText());
        ped.setObservacionInterna(observacionInterna);
        ped.setCampo2(campo2);
        ped.setCampo3(campo3);

        return ped;
    }

//------------------------ Consultas ------------------------------------------
    private void consultarClientePorId(int iddecliente) throws Exception {
        cliente = clientecontroler.ClienteBuscar1(iddecliente);
        if (cliente != null) {

            //txtRazonSocial.setText(pro.getRazonSocial());
            comboBoxRazSocial.setSelectedItem(cliente.getRazonSocial());
            txtRuc.setText(cliente.getRuc());
            txtDireccion.setText(cliente.getDireccion());

        } else {
            JOptionPane.showMessageDialog(null, "Cliente no registrado por ahora");
            //System.out.println("Error");
        }
    }

    private void consultarContactoCliente1() throws Exception {

        contactocliente = contactoclientecontroler.ContactoClienteBuscar2(idContactoCliente);
        if (contactocliente != null) {

            nombre = contactocliente.getNombres();
            apellido = contactocliente.getApellidos();
            comboContacto = nombre + " " + apellido;
            System.out.println(comboContacto);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarContactoCliente() throws Exception {

        contactocliente = contactoclientecontroler.ContactoClienteBuscar1(comboContacto, idCliente);
        if (contactocliente != null) {
            idContactoCliente = contactocliente.getIdContactoCliente();
            System.out.println(idContactoCliente);

            txtTlf.setText(contactocliente.getTlf1());
            txtEmail.setText(contactocliente.getCorreo());

        } else {
            JOptionPane.showMessageDialog(null, "EL Cliente no tiene contactos, Registre un contacto!!!!");
            //System.out.println("Error");
        }
    }

    private void consultarUsuario1() throws Exception {
        usuario = usuariocontroler.UsuarioBuscar2(idUs);
        if (usuario != null) {
            nombreUs = usuario.getNombres();
            apellidoUs = usuario.getApellidos();
            comboUsuario = nombreUs + " " + apellidoUs;
        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarUsuario() throws Exception {
        usuario = usuariocontroler.UsuarioBuscar1(comboUsuario);
        if (usuario != null) {

            idUs = usuario.getIdUsuario();
            System.out.println(idUs);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarTipoCambio(String fecha) throws Exception {
        tipCam = tipCamControl.TipoCambioBuscarCambio(fecha);
        if (tipCam != null) {

            labelCambioCompra.setText(Float.toString(tipCam.getPrecioCompra()));
            labelCambioVenta.setText(Float.toString(tipCam.getPrecioVenta()));

        } else {
            JOptionPane.showMessageDialog(null, "Operacion Invalida");
            //System.out.println("Error");
        }
    }
//-------------consultas para generar codigo -------------------    

    private void consultarPedidoNum(int id) throws Exception {

        pedido = pedidocontroler.PedidoBuscarNumPed(id);
        if (pedido != null) {

            numPedido = pedido.getNumPedido();
            generarId = numPedido;
            System.out.println("generar id por numPedido: " + generarId);
        } else {
            numPedido = 0;
            generarId = numPedido;
            System.out.println("generar id por numPedido: " + generarId);
//JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarUltimoId(int anio) throws Exception {
        pedido = pedidocontroler.PedidoBuscarUltimoId(anio);
        if (pedido != null) {
            ultimoId = pedido.getIdPedido();
            System.out.println("ultimoId : " + ultimoId);
        } else {
            numPedido = 0;
            generarId = numPedido;
            System.out.println("generar id por numPedido: " + generarId);
        }

    }

    private void GenerarCodigo() throws Exception {
        consultarUltimoId(Integer.parseInt(txtAnio.getText()));
        System.out.println("ultimo Id 2:" + ultimoId);
        consultarPedidoNum(ultimoId);
        System.out.println("generar Id 2:" + generarId);
        generarId = generarId + 1;
        txtNumPedido.setText(Integer.toString(generarId));

    }

    // ---------------------- Consultar Proforma ---------------------------------  
    private void consultarProforma(String codigo, int an) throws Exception {
        proforma = proformacontroler.ProformaBuscar(codigo, an);
        if (proforma != null) {

            idProf = proforma.getIdProforma();
            System.out.println("idProformaBuscar: " + idProf);
            txtCodProforma.setText(proforma.getCodProforma());
            codProformaParaDetalle = proforma.getCodProforma();
            idCliente = proforma.getIdCliente();

            consultarClientePorId(idCliente);
            comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboBoxRazSocial.getEditor().getItem().toString(), idCliente));

            idContactoCliente = proforma.getIdContactoCliente();
            System.out.println("idCCli:" + idContactoCliente);
            consultarContactoCliente1();
            //consultar contacto asigna a categoria1 su nombre concatenado
            String testValue1 = comboContacto;
            System.out.println("proforma contacto cliente:" + comboContacto);

            for (int i = 0; i < comboBoxContactoCliente.getModel().getSize(); i++) {
                System.out.println("for 1111");
                if (comboBoxContactoCliente.getItemAt(i).equals(testValue1)) {
                    System.out.println("for222222222");
                    System.out.println("for " + i);
                    comboBoxContactoCliente.setSelectedIndex(i);
                    System.out.println("for 3333333333");
                    break;
                }
            }

            consultarContactoCliente();

            idUs = proforma.getIdUsuario();
            System.out.println("idUs" + idUs);
            consultarUsuario1();
            String testValue2 = comboUsuario;
            for (int i = 0; i < comboBoxUsuario.getModel().getSize(); i++) {
                if (comboBoxUsuario.getItemAt(i).equals(testValue2)) {
                    System.out.println("for idUs 222222222");
                    System.out.println(i);
                    comboBoxUsuario.setSelectedIndex(i);
                    System.out.println("for idUs 3333333333");
                    break;
                }
            }

            moneda = proforma.getMoneda();
            System.out.println("moneda" + moneda);
            String testValue3 = moneda;
            for (int i = 0; i < comboBoxMoneda.getModel().getSize(); i++) {
                if (comboBoxMoneda.getItemAt(i).equals(testValue3)) {
                    System.out.println(i);
                    comboBoxMoneda.setSelectedIndex(i);
                    break;
                }
            }

            txtFormPago.setText(proforma.getFormPago());
            formPago = proforma.getFormPago();
            tipo = proforma.getTipo();
            System.out.println("tipo" + tipo);

            String testValue = tipo;
            for (int i = 0; i < comboBoxTipo.getModel().getSize(); i++) {
                if (comboBoxTipo.getItemAt(i).equals(testValue)) {
                    System.out.println(i);
                    comboBoxTipo.setSelectedIndex(i);
                    break;
                }
            }

            validezProf = proforma.getValidez();
            fechaProf = proforma.getFechaEmision();
            profDia1 = proforma.getDia1();
            txtDia1.setText(Integer.toString(proforma.getDia1()));
            profDia2 = proforma.getDia2();
            txtDia2.setText(Integer.toString(proforma.getDia2()));
            detracion = proforma.getDetraccion();
            observacion = proforma.getObservacion();
            estadoProf = proforma.getEstado();
            txtObservacion.setText(proforma.getObservacion() + " " + proforma.getDetraccion());

        } else {
            JOptionPane.showMessageDialog(null, "Pedido no registrado");

            System.out.println("Pedido no registrado");
        }
    }

    //---------------------------- PROCESAR PROFORMA, ACTUALIZAR A APROBADO -----------------------------
    void procesarProforma(int op) {
        proforma = leerDatosProforma();
        try {
            System.out.println("11111111111");
            String msg = proformacontroler.ProformaProcesar(proforma, op);
            JOptionPane.showMessageDialog(null, "Cotización APROBADA");
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }

    Proforma leerDatosProforma() {
        Proforma prof = new Proforma();

        prof.setIdCliente(idCliente);
        prof.setIdContactoCliente(idContactoCliente);
        prof.setIdUsuario(idUs);
        prof.setMoneda(moneda);
        prof.setValidez(validezProf);
        prof.setFormPago(formPago);
        prof.setTipo(tipo);
        prof.setDetraccion(detracion);
        prof.setFechaEmision(fechaProf);
        prof.setDia1(profDia1);
        prof.setDia2(profDia2);
        prof.setEstado("APROBADO");
        prof.setObservacion(observacion);
        prof.setIdProforma(idProf);

        return prof;
    }

    //------------------ Consultar Pedido ------------------------------
    private void consultarCodigoProforma(int id) throws Exception {

        proforma = proformacontroler.ProformaBuscar1(id);
        if (proforma != null) {

            codProforma = proforma.getCodProforma();
            codProformaParaDetalle = proforma.getCodProforma();

        } else {
            //JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarPedido(int numeroPedido, int anio) throws Exception {
        pedido = pedidocontroler.PedidoBuscar(numeroPedido, anio);
        if (pedido != null) {

            idPedido = pedido.getIdPedido();
            idPedParaDetalle = pedido.getIdPedido();
            txtNumPedido.setText(String.format("%06d", pedido.getNumPedido()));
            numPedido = pedido.getNumPedido();
            numPedParaDetalle = pedido.getNumPedido();
            idProf = pedido.getIdProforma();
            consultarCodigoProforma(idProf);
            txtCodProforma.setText(codProforma);
            idCliente = pedido.getIdCliente();

            consultarClientePorId(idCliente);
            comboBoxContactoCliente.setModel(pedidocontroler.ListarCombodeContacto(comboBoxRazSocial.getEditor().getItem().toString()));

            idContactoCliente = pedido.getIdContactoCliente();
            System.out.println("idCCli:" + idContactoCliente);
            consultarContactoCliente1();
            //consultar contacto asigna a categoria1 su nombre concatenado
            String testValue1 = comboContacto;
            System.out.println("proforma contacto cliente:" + comboContacto);

            for (int i = 0; i < comboBoxContactoCliente.getModel().getSize(); i++) {
                System.out.println("for 1111");
                if (comboBoxContactoCliente.getItemAt(i).equals(testValue1)) {
                    System.out.println("for222222222");
                    System.out.println("for " + i);
                    comboBoxContactoCliente.setSelectedIndex(i);
                    System.out.println("for 3333333333");
                    break;
                }
            }

            consultarContactoCliente();

            idUs = pedido.getIdUsuario();
            System.out.println("idUs" + idUs);
            consultarUsuario1();
            String testValue2 = comboUsuario;
            for (int i = 0; i < comboBoxUsuario.getModel().getSize(); i++) {
                if (comboBoxUsuario.getItemAt(i).equals(testValue2)) {
                    System.out.println("for idUs 222222222");
                    System.out.println(i);
                    comboBoxUsuario.setSelectedIndex(i);
                    System.out.println("for idUs 3333333333");
                    break;
                }
            }

            moneda = pedido.getMoneda();
            System.out.println("moneda" + moneda);
            String testValue3 = moneda;
            for (int i = 0; i < comboBoxMoneda.getModel().getSize(); i++) {
                if (comboBoxMoneda.getItemAt(i).equals(testValue3)) {
                    System.out.println(i);
                    comboBoxMoneda.setSelectedIndex(i);
                    break;
                }
            }

            txtFormPago.setText(pedido.getFormPago());

            tipo = pedido.getTipo();
            System.out.println("tipo" + tipo);

            String testValue = tipo;
            for (int i = 0; i < comboBoxTipo.getModel().getSize(); i++) {
                if (comboBoxTipo.getItemAt(i).equals(testValue)) {
                    System.out.println(i);
                    comboBoxTipo.setSelectedIndex(i);
                    break;
                }
            }

            txtFecha.setText(pedido.getFechaEmision());
            consultarTipoCambio(pedido.getFechaEmision());
            anioParaDetalleAPedido = Integer.parseInt(anioActual(pedido.getFechaEmision()));
            txtDia1.setText(Integer.toString(pedido.getDia1()));
            txtDia2.setText(Integer.toString(pedido.getDia2()));
            txtOrdenCompra.setText(pedido.getOrdenCompra());

            estadoPedido = pedido.getEstado();
            System.out.println("Estado de pedido: " + estadoPedido);

            txtObservacion.setText(pedido.getObservacion());

            observacionInterna = pedido.getObservacionInterna();

        } else {
            JOptionPane.showMessageDialog(null, "Pedido no registrado");
            idPedido = 0;
            limpiarTodo();
            deshabilitarInicio();
            mensaje = true;
            System.out.println("Pedido no registrado");
        }
    }

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

    //----------------------------- Consultar combo Cliente -----------------------
    private void consultarComboCliente(String razonsocial) throws Exception {
        cliente = clientecontroler.ClienteBuscar(razonsocial);
        if (cliente != null) {
            //CREAR ID CLIENTE Y ASIGNARLE EL ID CLIENTE.
            idCliente = cliente.getIdCliente();
            idClienteDeCombo = cliente.getIdCliente();
            comboBoxRazSocial.setSelectedItem(cliente.getRazonSocial());
            txtRuc.setText(cliente.getRuc());
            txtDireccion.setText(cliente.getDireccion());

        } else {
            JOptionPane.showMessageDialog(null, "Cliente no esta registrado");
            //System.out.println("Error");
        }
    }

    //------------ Limpiar combo Cliente ---------------------
    public void limpiarComboCliente() {
        comboBoxRazSocial.setSelectedItem("");
        comboBoxContactoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        txtRuc.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtTlf.setText("");
    }

    private boolean comparar(String cadena) {
        Object[] lista = comboBoxRazSocial.getComponents();
        boolean encontrado = false;
        for (Object object : lista) {
            if (cadena.equals(object)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }

    // ------------------------ Fecha --> Indica la fecha del dia --------------------
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
    static public String anioActual(String f) throws ParseException {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;

        fechaDate = formato.parse(f);

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy");
        return formatofecha.format(fechaDate);

    }

    //------------------- Codigo que jala el numero de cotización ---------------------------  
    static void NumCotParaPedido() {
        CotizacionesParaPedido.NumeroCotizacion = CotizacionesParaPedido.txtNumCotiz.getText();
        System.out.println(CotizacionesParaPedido.NumeroCotizacion);
        txtCodProforma.setText(CotizacionesParaPedido.NumeroCotizacion);
    }

    // --------------------- Limpiar ----------------------------
    public void limpiarTodo() throws Exception {

        txtCodProforma.setText("");
        txtAnio.setText(anioActual());
        codProformaParaDetalle = "";
        numPedParaDetalle = 0;
        txtNumPedido.setText("");
        txtFecha.setText(fechaActual());
        txtFormPago.setText("");
        comboBoxRazSocial.setSelectedItem("");
        txtRuc.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtTlf.setText("");
        txtDia1.setText("");
        txtDia2.setText("");
        txtOrdenCompra.setText("");
        txtObservacion.setText("");
        labelSubtotal.setText("00000000.00");
        labelIgv.setText("00000000.00");
        labelTotal.setText("00000000.00");
        comboBoxTipo.setSelectedIndex(1);
        comboBoxMoneda.setSelectedIndex(1);
        comboBoxUsuario.setSelectedIndex(1);
        comboBoxContactoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        comboContacto = "";
//        listaDetallePedido(0);
//        consultarPago(0);
        listaDetalleProforma(0);
        labelIgv.setText("000000.00");
        labelSubtotal.setText("000000.00");
        labelTotal.setText("000000.00");
        numPedido = 0;
        idPedido = 0;

    }

    // ------------------------------------ TABLA  DETALLE PROFORMA ---------------------------------------
    void listaDetalleProforma(int idpro) {
        List<DetalleProforma> lista;
        try {
            lista = detalleproformacontroler.DetalleProformaFiltrar(idpro);
            verDetalleProforma(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verDetalleProforma(List<DetalleProforma> lista) {
        String it, vu, f;
        float uv;
        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (DetalleProforma detProf : lista) {
            if (!"".equals(detProf.getPrecioUnitario())) {
                it = detProf.getImporte();
            } else {
                it = "";
            }
            if (!"".equals(detProf.getPrecioUnitario())) {
                f = detProf.getItem1();
            } else {
                f = "";
            }

            Object[] fila = {detProf.getIdDetalleProforma(), detProf.getItem(), detProf.getCantidad(), detProf.getDescripcion(), detProf.getPrecioUnitario(), f, it};
            tabla.addRow(fila);
        }
    }

    void consultarCalculoProforma(int iddp) throws Exception {
        detalleproforma = detalleproformacontroler.DetalleProformaBuscar1(iddp);

        if (detalleproforma != null) {

            labelIgv.setText(detalleproforma.getIgv());
            labelSubtotal.setText(detalleproforma.getSubtotal());
            labelTotal.setText(detalleproforma.getTotal());
        } else {
            System.out.println("Error");
        }
    }

// --------------------------- Habilitar, deshabilitar campos y botones --------------------
    void deshabilitarInicio() {

        buttonNumCot.setEnabled(false);
        buttonBuscarCodProf.setEnabled(false);
        buttonBuscarPedido.setEnabled(true);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);
        buttonGenerarOrdenCorte.setEnabled(false);

        txtCodProforma.setEnabled(false);
        txtNumPedido.setEnabled(true);
        txtAnio.setEnabled(true);
        txtFecha.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        txtOrdenCompra.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    void habilitarNuevo() {

        buttonNumCot.setEnabled(true);
        buttonBuscarCodProf.setEnabled(true);
        buttonBuscarPedido.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);
        buttonGenerarOrdenCorte.setEnabled(false);

        txtCodProforma.setEnabled(true);
        txtNumPedido.setEnabled(true);
        txtAnio.setEnabled(true);
        txtFecha.setEnabled(true);
        comboBoxRazSocial.setEnabled(true);
        txtRuc.setEnabled(true);
        txtDireccion.setEnabled(true);
        comboBoxContactoCliente.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTlf.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        txtFormPago.setEnabled(true);
        txtDia1.setEnabled(true);
        txtDia2.setEnabled(true);
        txtOrdenCompra.setEnabled(true);
        comboBoxTipo.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        txtObservacion.setEnabled(true);
    }

    void habilitarRegistrar() {

        buttonNumCot.setEnabled(false);
        buttonBuscarCodProf.setEnabled(false);
        buttonBuscarPedido.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);
        buttonGenerarOrdenCorte.setEnabled(true);

        txtCodProforma.setEnabled(false);
        txtNumPedido.setEnabled(false);
        txtAnio.setEnabled(false);
        txtFecha.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        txtOrdenCompra.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    void habilitarModificar() {

        buttonNumCot.setEnabled(false);
        buttonBuscarCodProf.setEnabled(false);
        buttonBuscarPedido.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);
        buttonGenerarOrdenCorte.setEnabled(false);

        txtCodProforma.setEnabled(false);
        txtNumPedido.setEnabled(true);
        txtAnio.setEnabled(true);
        txtFecha.setEnabled(true);
        comboBoxRazSocial.setEnabled(true);
        txtRuc.setEnabled(true);
        txtDireccion.setEnabled(true);
        comboBoxContactoCliente.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTlf.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        txtFormPago.setEnabled(true);
        txtDia1.setEnabled(true);
        txtDia2.setEnabled(true);
        txtOrdenCompra.setEnabled(true);
        comboBoxTipo.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        txtObservacion.setEnabled(true);
    }

    void habilitarGuardar() {

        buttonNumCot.setEnabled(false);
        buttonBuscarCodProf.setEnabled(false);
        buttonBuscarPedido.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);
        buttonGenerarOrdenCorte.setEnabled(true);

        txtCodProforma.setEnabled(false);
        txtNumPedido.setEnabled(false);
        txtAnio.setEnabled(false);
        txtFecha.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        txtOrdenCompra.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    void habilitarEliminar() {

        buttonNumCot.setEnabled(false);
        buttonBuscarCodProf.setEnabled(false);
        buttonBuscarPedido.setEnabled(true);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);
        buttonGenerarOrdenCorte.setEnabled(false);

        txtCodProforma.setEnabled(false);
        txtNumPedido.setEnabled(true);
        txtAnio.setEnabled(true);
        txtFecha.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        txtOrdenCompra.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    void habilitarBuscarPedido() {

        buttonNumCot.setEnabled(false);
        buttonBuscarCodProf.setEnabled(false);
        buttonBuscarPedido.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);
        buttonGenerarOrdenCorte.setEnabled(true);

        txtCodProforma.setEnabled(false);
        txtNumPedido.setEnabled(false);
        txtAnio.setEnabled(false);
        txtFecha.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        txtOrdenCompra.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

//----------------- metodo q jala numero desde Proformas vista -------------
    static void NumProformasPed() {
        ProformasVista.numeroCotizacion = ProformasVista.txtCodProf.getText();
        System.out.println(ProformasVista.numeroCotizacion);
        txtCodProforma.setText(ProformasVista.numeroCotizacion);
    }
//----------------- metodo q jala numero desde Pedidos vista -------------

    static void NumPedido() {
        System.out.println(PedidosVista.numero);
        txtNumPedido.setText(PedidosVista.numero);
    }

//----------------- metodo q jala numero desde Proformas (Registrar) -------------
    static void NumProformasReg() {
        Proformas.cod = Proformas.txtCodProf.getText(); //codigoProforma 
        txtCodProforma.setText(Proformas.cod);
    }
//----------------- metodo q jala numero desde Vista Registro Venta -------------

    static void NumPedidoRegistroVenta() {
        System.out.println(PedidosVista.numero);
        txtNumPedido.setText(String.format("%06d", RegistroVentasVista.numPedidoStatic));
        txtAnio.setText(RegistroVentasVista.anioPedidoStatic);
    }

}

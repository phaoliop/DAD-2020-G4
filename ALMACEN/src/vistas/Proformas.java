package vistas;

import controller.ClienteController;
import controller.ContactoClienteController;
import controller.DetalleProformaController;
import controller.ProformaController;
import controller.TipoCambioController;
import controller.UsuarioController;
import database.AccesoDB;
import entity.Cliente;
import entity.ContactoCliente;
import entity.DetalleProforma;
import entity.Proforma;
import entity.TipoCambio;
import entity.Usuario;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.view.JasperViewer;
import reportes.GenerarReporte;
import static vistas.Clientes.x;

public final class Proformas extends javax.swing.JInternalFrame {

    static Connection conexion;
    static CallableStatement cs = null;
    static ResultSet rs = null;
    static Statement s = null;
    static PreparedStatement ps = null;
    static ContactoCliente contCli;
    static String q;
    static boolean mensaje=false;
    
    String categoria3;// usuario
    static String categoria2 = null; //tipo 
    String categoria1 = ""; //contactoCliente 
    static String categoria4; //moneda

    static int idContactoCliente;
    static int idUs;
    int idProf;
    int tabla;
    static int idCliente;
    String tipo;
    String moneda;
    static String estado="";
    String nombre;
    String apellido;
    static String observacionInterna="";
    int ultimoId;
    String codProforma;
    int generarId;
    static String total = "0";

    static String a;
    static int b;
    static String cod;

    static int idDetalleProforma;
    
    boolean comprobar=false; //generarpedido
    int numPed;

    static ProformaController proformacontroler = new ProformaController();
    static Proforma proforma;

    ClienteController clientecontroler = new ClienteController();
    Cliente cliente;

    ContactoClienteController contactoclientecontroler = new ContactoClienteController();
    ContactoCliente contactocliente;

    UsuarioController usuariocontroler = new UsuarioController();
    Usuario usuario;

    static DetalleProformaController detalleproformacontroler = new DetalleProformaController();
    static DetalleProforma detalleproforma;

    public Proformas() throws SQLException, ClassNotFoundException, Exception {

        initComponents();
        deshabilitarInicio();
        txtDia1.setText("1");
        txtDia2.setText("2");
        txtFecha.setText(fechaActual());
        System.out.println(txtFecha.getText());
        consultarTipoCambio(txtFecha.getText());
        CargarUsuarios();
        System.out.println("año actual: "+anioActual());
        txtAnio.setText(anioActual());
        listaDetalleProforma();
        comboRazonSocial.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {

                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboRazonSocial.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    limpiarComboCliente();
                    try {
                        
                        consultarComboCliente(comboRazonSocial.getItemAt(0));
                        comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboRazonSocial.getItemAt(0),idCliente ));

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
                            

                            buttonEliminar.setEnabled(true);
                            a = labelIdProforma.getText();
                            b = Integer.parseInt(a);
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            for (int i = 0; i < comboRazonSocial.getModel().getSize(); i++) {
                                if (comboRazonSocial.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarComboCliente(comboRazonSocial.getItemAt(i));
                                    comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboRazonSocial.getItemAt(i), idCliente));

                                    break;
                                }
                            }
                            a = labelIdProforma.getText();
                            b = Integer.parseInt(a);
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboRazonSocial.setModel(clientecontroler.ClienteListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboRazonSocial.getItemCount() > 0) {
                        comboRazonSocial.getEditor().setItem(cadenaEscrita);
                        comboRazonSocial.showPopup();
                    } else {
                        comboRazonSocial.addItem(cadenaEscrita);
                    }
                }
            }
        });

        comboBoxTipo.setSelectedIndex(1);
        comboBoxMoneda.setSelectedIndex(1);
        comboBoxUsuario.setSelectedIndex(1);
        txtFormPago.setSelectedIndex(4);
        

    }

    private boolean comparar(String cadena) {
        Object[] lista = comboRazonSocial.getComponents();
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

        jLabel9 = new javax.swing.JLabel();
        labelIdProforma = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        txtCodProf = new javax.swing.JTextField();
        buttonBuscarProf = new javax.swing.JButton();
        jLabel21 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel22 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonCerrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtEmail = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        txtTlf = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        comboBoxMoneda = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        comboBoxTipo = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        txtFecha = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        labelSubtotal = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        labelIgv = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        buttonProducto = new javax.swing.JButton();
        comboBoxContactoCliente = new javax.swing.JComboBox<>();
        buttonEliminar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonNuevo = new javax.swing.JButton();
        buttonLimpiarTodo = new javax.swing.JButton();
        comboBoxUsuario = new javax.swing.JComboBox<>();
        txtValidez = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtObservacion = new javax.swing.JTextArea();
        comboRazonSocial = new javax.swing.JComboBox<>();
        btnRazonSocial = new javax.swing.JButton();
        btnContactoCliente = new javax.swing.JButton();
        txtCatCliente = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtDetraccion = new javax.swing.JTextField();
        jLabel24 = new javax.swing.JLabel();
        buttonGuardarVerPdf = new javax.swing.JButton();
        buttonIprimir = new javax.swing.JButton();
        buttonDescripcion = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        buttonAnterior = new javax.swing.JButton();
        txtFormPago = new javax.swing.JComboBox<>();
        jLabel23 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        buttonGenerarPedido = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        labelCambioCompra = new javax.swing.JLabel();
        labelCambioVenta = new javax.swing.JLabel();
        txtDia1 = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txtDia2 = new javax.swing.JTextField();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();

        setClosable(true);
        setTitle("Registro de Cotización ");
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel9.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel9.setText("N°:");

        labelIdProforma.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        labelIdProforma.setText("0000");

        jLabel19.setText("COTIZACION:");

        txtCodProf.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        buttonBuscarProf.setText("Buscar");
        buttonBuscarProf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarProfActionPerformed(evt);
            }
        });

        jLabel21.setText("R.U.C.:");

        jLabel22.setText("Direccion:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idDetalleProforma", "Item", "Cant.", "Descripcion", "Valor Unit", "Precio Unitario", "Importe"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setToolTipText("");
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(45);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(490);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(110);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(110);
        }

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        jLabel2.setText("E-mail:");

        jLabel3.setText("Telefono: ");

        jLabel4.setText("Moneda:");

        comboBoxMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "DOLARES AMERICANOS", "SOLES" }));
        comboBoxMoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxMonedaItemStateChanged(evt);
            }
        });

        jLabel5.setText("Validez de cot: ");

        jLabel8.setText("Tipo:");

        comboBoxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "PRODUCTO", "SERVICIO" }));
        comboBoxTipo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxTipoItemStateChanged(evt);
            }
        });
        comboBoxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxTipoActionPerformed(evt);
            }
        });

        jLabel10.setText("Fecha de Emision:");

        txtFecha.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel11.setText("Elab. por:");

        jLabel12.setText("Observacion:");

        jLabel13.setText("Subtotal: ");

        labelSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSubtotal.setText("00000000000000000");

        jLabel15.setText("I.G.V.(18%):");

        labelIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelIgv.setText("00000000000000000");

        jLabel17.setText("Total:");

        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal.setText("00000000000000000");

        buttonProducto.setText("Agregar Detalle");
        buttonProducto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProductoActionPerformed(evt);
            }
        });
        buttonProducto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                buttonProductoKeyReleased(evt);
            }
        });

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
        comboBoxContactoCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                comboBoxContactoClienteKeyReleased(evt);
            }
        });

        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        buttonModificar.setText("Modificar");
        buttonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarActionPerformed(evt);
            }
        });

        buttonRegistrar.setText("Registrar");
        buttonRegistrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarActionPerformed(evt);
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

        buttonLimpiarTodo.setText("Limpiar todo");
        buttonLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarTodoActionPerformed(evt);
            }
        });

        comboBoxUsuario.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxUsuarioItemStateChanged(evt);
            }
        });

        txtValidez.setText("10 DIAS HABILES");
        txtValidez.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtValidezActionPerformed(evt);
            }
        });

        jLabel14.setText("Tiempo de Entrega:");

        txtObservacion.setColumns(20);
        txtObservacion.setRows(5);
        jScrollPane1.setViewportView(txtObservacion);

        comboRazonSocial.setEditable(true);

        btnRazonSocial.setText("Señor(es)");
        btnRazonSocial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRazonSocialActionPerformed(evt);
            }
        });

        btnContactoCliente.setText("Atencion");
        btnContactoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactoClienteActionPerformed(evt);
            }
        });

        txtCatCliente.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txtCatCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCatClienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Cat. Cliente");

        jButton1.setText("forma de pago:");

        jLabel24.setText("Detraccion:");

        buttonGuardarVerPdf.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonGuardarVerPdf.setText(" PDF");
        buttonGuardarVerPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarVerPdfActionPerformed(evt);
            }
        });

        buttonIprimir.setText("Imprimir");
        buttonIprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonIprimirActionPerformed(evt);
            }
        });

        buttonDescripcion.setText("Descripcion");
        buttonDescripcion.setToolTipText("");
        buttonDescripcion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonDescripcionActionPerformed(evt);
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

        txtFormPago.setEditable(true);
        txtFormPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "FACTURA A 30 DIAS", "FACTURA A 15 DIAS", "FACTURA A 45 DIAS", "CONTRA ENTREGA", "PREVIO DEPÓSITO", "50% DE ADELANTO Y SALDO CON FACTURA A 30 DIAS", "50% DE ADELANTO Y SALDO CON CHEQUE A 30 DIAS", "50% DE ADELANTO Y SALDO CONTRA ENTREGA.", "CHEQUE A 1 SEMANA", "CHEQUE A 30 DIAS", "LETRA A 30 DIAS" }));

        jLabel23.setText("Año:");

        txtAnio.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        buttonGenerarPedido.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        buttonGenerarPedido.setText("GenerarPedido");
        buttonGenerarPedido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGenerarPedidoActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel20.setText("Tipo de cambio");

        jLabel16.setText("Compra:");

        jLabel18.setText("Venta:");

        labelCambioCompra.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCambioCompra.setText("00.00");

        labelCambioVenta.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelCambioVenta.setText("00.00");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel18)
                            .addComponent(jLabel16))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(labelCambioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelCambioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jLabel20)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jLabel20)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel16)
                    .addComponent(labelCambioCompra))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(labelCambioVenta))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel7.setText("A");

        jLabel26.setText("DIAS HÁBILES");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel24)
                        .addGap(10, 10, 10)
                        .addComponent(txtDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(208, 208, 208)
                        .addComponent(buttonAnterior)
                        .addGap(29, 29, 29)
                        .addComponent(buttonSiguiente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addComponent(jLabel21))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135)
                                .addComponent(txtValidez, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(31, 31, 31)
                                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addComponent(jLabel3)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel25)))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel12)
                        .addGap(10, 10, 10)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(272, 272, 272)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel13))
                            .addComponent(jLabel15)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(36, 36, 36)
                                .addComponent(jLabel17)))
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelSubtotal)
                            .addComponent(labelIgv)
                            .addComponent(labelTotal)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnRazonSocial))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(17, 17, 17)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelIdProforma, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addComponent(comboRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(75, 75, 75)
                                .addComponent(jLabel4))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(jLabel23)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtCodProf, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonBuscarProf)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonLimpiarTodo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel10)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtCatCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(91, 91, 91)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(228, 228, 228)
                                        .addComponent(jButton1))))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(btnContactoCliente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxContactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel14)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtFormPago, javax.swing.GroupLayout.PREFERRED_SIZE, 345, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtDia1, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel7)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDia2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel26))
                                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(36, 36, 36)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(buttonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(511, 511, 511)
                        .addComponent(jLabel8)
                        .addGap(251, 251, 251)
                        .addComponent(buttonProducto, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 926, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(496, 496, 496)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(buttonGenerarPedido, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonDescripcion, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonIprimir, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(buttonGuardarVerPdf, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(26, 26, 26)
                        .addComponent(buttonNuevo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonRegistrar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonModificar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGuardar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonEliminar)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel19)
                            .addComponent(txtCodProf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel23)
                            .addComponent(labelIdProforma)
                            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonBuscarProf)
                            .addComponent(buttonLimpiarTodo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel10)
                            .addComponent(jLabel1)
                            .addComponent(txtCatCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnRazonSocial)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(comboRazonSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel21))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton1)
                                .addComponent(txtFormPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonProducto))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5)
                                    .addComponent(txtValidez, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(btnContactoCliente)
                                    .addComponent(comboBoxContactoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtDia1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7)
                                    .addComponent(txtDia2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel26))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel25)
                                    .addComponent(jLabel2)
                                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxTipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(1, 1, 1)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(txtTlf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel3)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                            .addComponent(comboBoxUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel11))))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonGuardarVerPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonIprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonDescripcion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonGenerarPedido))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addGap(7, 7, 7)
                        .addComponent(jLabel15)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel17))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSubtotal)
                        .addGap(7, 7, 7)
                        .addComponent(labelIgv)
                        .addGap(6, 6, 6)
                        .addComponent(labelTotal)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(3, 3, 3)
                        .addComponent(jLabel24))
                    .addComponent(txtDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAnterior)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonSiguiente)
                        .addComponent(buttonCerrar))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonProductoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProductoActionPerformed

        DetalleProformas detProf = null;

        try {
            detProf = new DetalleProformas();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(detProf);
        detProf.toFront();
        detProf.setVisible(true);

//        if(b!=0){
//        DetalleProformas detProf = new DetalleProformas();
//        Principal.jDesktopPane1.add(detProf);
//        detProf.toFront();
//        detProf.setVisible(true);
//        }else{
//            
//        JOptionPane.showMessageDialog(null, "Agregar Proforma");
//       }

    }//GEN-LAST:event_buttonProductoActionPerformed

    private void comboBoxContactoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxContactoClienteActionPerformed
//        if(categoria1!="Elegir Contacto"){
        try {
            consultarContactoCliente();
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
//        }else{
//          JOptionPane.showMessageDialog(null, "Agregar Contacto");
//        }


    }//GEN-LAST:event_comboBoxContactoClienteActionPerformed

    private void comboBoxContactoClienteItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxContactoClienteItemStateChanged

        // categoria1 = comboBoxContactoCliente.getSelectedItem().toString();
        if (categoria1 != "Elegir Contacto") {
            categoria1 = comboBoxContactoCliente.getSelectedItem().toString();
            System.out.println(categoria1);
        } else {
            categoria1 = "";
        }
    }//GEN-LAST:event_comboBoxContactoClienteItemStateChanged

    private void buttonLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarTodoActionPerformed
        System.out.println("limpiar");
        limpiar();
        System.out.println("total: " + total);
        deshabilitarInicio();
    }//GEN-LAST:event_buttonLimpiarTodoActionPerformed

    private void comboBoxTipoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxTipoItemStateChanged
        if (comboBoxTipo.getSelectedIndex() != 0) {
            categoria2 = comboBoxTipo.getSelectedItem().toString();
            System.out.println(categoria2);
            } else {
        }
    }//GEN-LAST:event_comboBoxTipoItemStateChanged

    private void comboBoxUsuarioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxUsuarioItemStateChanged

        if (comboBoxUsuario.getSelectedIndex() != 0) {
            categoria3 = comboBoxUsuario.getSelectedItem().toString();
            System.out.println(categoria3);
            try {
                consultarUsuario();
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {

        }


    }//GEN-LAST:event_comboBoxUsuarioItemStateChanged

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        String df,fg;
        df=txtDia1.getText();
        fg=txtDia2.getText();
        if(comboRazonSocial.getEditor().getItem().toString().equals("")){
            JOptionPane.showMessageDialog(null, "Debe ingresar un Cliente");
        } else if(categoria1.equals("")){
            JOptionPane.showMessageDialog(null, "Seleccionar Contacto de Cliente");
        } else if(txtFormPago.getEditor().getItem().toString().equals("")){
            JOptionPane.showMessageDialog(null, "Ingresar Forma de Pago");
        } else if("".equals(df)){
            JOptionPane.showMessageDialog(null, "Ingresar el primer Dia de Tiempo de Entrega");
        } else if("".equals(fg)){
            JOptionPane.showMessageDialog(null, "Ingresar el segundo Dia de Tiempo de Entrega");
        }else{
            
            try {
                GenerarCodigo();
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            procesar(1);
            comboBoxContactoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
            try {
                consultarProforma(txtCodProf.getText(), Integer.parseInt(txtAnio.getText()));
                a = labelIdProforma.getText();
                b = Integer.parseInt(a);
                cod = txtCodProf.getText();

            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleProforma();
            try {
                consultarPago();
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
            deshabilitarRegistrar();
        }

    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonBuscarProfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarProfActionPerformed
        int numa=(Integer.parseInt(txtCodProf.getText()));
        String numeroProforma= String.format("%06d",numa );
        System.out.println("numero de Cotizacion: "+numeroProforma);
        
        try {

            //consultarProforma(txtCodProf.getText());
            consultarProforma(numeroProforma, Integer.parseInt(txtAnio.getText()));
            consultarTipoCambio(txtFecha.getText());
                        
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        a = labelIdProforma.getText();
        b = Integer.parseInt(a);
        cod = txtCodProf.getText();
        listaDetalleProforma();
        habilitarBuscar();

        try {
            consultarPago();
            System.out.println("Total incluido IGV: " + total);

        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        if ("DOLARES AMERICANOS".equals(categoria4) && "SERVICIO".equals(categoria2)) {
            try {
                consultarTotal(txtFecha.getText());
                System.out.println("total consultado" + total);
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (Float.parseFloat(total) > 700) {
                txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
            } else {
                txtDetraccion.setText("");
            }
        } else if ("SOLES".equals(categoria4) && "SERVICIO".equals(categoria2)) {
            if (Float.parseFloat(labelTotal.getText()) > 700) {
                txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
            } else {
                txtDetraccion.setText("");
            }
        } else {
            txtDetraccion.setText("");
        }
        procesarProfUpdate(2);
        habilitarBuscar();
    }//GEN-LAST:event_buttonBuscarProfActionPerformed

    private void comboBoxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_comboBoxTipoActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        if (jTable1.getRowCount() == 0 && jTable1.getSelectedRow() == -1) {
            procesar1(3);
            limpiar();
            deshabilitarInicio();
        } else {
            JOptionPane.showMessageDialog(null, "Elimine los elementos de la tabla de Detalle de Productos");
        }
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
      //  procesar1(2);
        listaDetalleProforma();

        try {
            consultarPago();
            System.out.println("Total incluido IGV: " + total);

        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if ("DOLARES AMERICANOS".equals(categoria4) && "SERVICIO".equals(categoria2)) {
            try {
                consultarTotal(txtFecha.getText());
                if (Float.parseFloat(total) > 700) {
                        txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                        System.out.println("SUJETO A DETRACCIÓN 12%(COD.OP. 022) dolares");

                    } else if (Float.parseFloat(total) <700 || Float.parseFloat(total)==700) {
                            txtDetraccion.setText("");

                        }else  if ("DOLARES AMERICANOS".equals(categoria4) && "PRODUCTO".equals(categoria2)) {
                                txtDetraccion.setText("");
                             }
                } catch (Exception ex) {
                        Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
        } else if ("SOLES".equals(categoria4) && "SERVICIO".equals(categoria2)) {

            if (Float.parseFloat(labelTotal.getText()) > 700) {
                    txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                    System.out.println("SUJETO A DETRACCIÓN 12%(COD.OP. 022) soles");

                } else if (Float.parseFloat(labelTotal.getText())<700 || Float.parseFloat(labelTotal.getText())==700) {
                        txtDetraccion.setText("");
                
                    }  else if ("SOLES".equals(categoria4) && "PRODUCTO".equals(categoria2)) {
                            txtDetraccion.setText("");
                        }
        } else {
            txtDetraccion.setText("");
        }
        procesar1(2);
        try {
            consultarProforma(txtCodProf.getText(), Integer.parseInt(txtAnio.getText()));
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarGuardar();

    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonProductoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_buttonProductoKeyReleased
    }//GEN-LAST:event_buttonProductoKeyReleased

    private void comboBoxMonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxMonedaItemStateChanged
        if (comboBoxMoneda.getSelectedIndex() != 0) {
            categoria4 = comboBoxMoneda.getSelectedItem().toString();
            System.out.println(categoria4);
        } else {
        }
    }//GEN-LAST:event_comboBoxMonedaItemStateChanged

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tabla = jTable1.getSelectedRow();
        DetalleProformas.Descripcion = jTable1.getValueAt(tabla, 3).toString();
        idDetalleProforma = Integer.parseInt(jTable1.getValueAt(tabla, 0).toString());
        //  DetalleProformas.txtItem.setText(jTable1.getValueAt(tabla, 0).toString());
        DetalleProformas detProforma = null;
        try {
            detProforma = new DetalleProformas();
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(detProforma);
        detProforma.toFront();
        detProforma.setVisible(true);
        DetalleProformas.habilitarTabla();
        System.out.println("idDetalle " + idDetalleProforma);

        try {
            DetalleProformas.consultar(b, idDetalleProforma);
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void comboBoxContactoClienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_comboBoxContactoClienteKeyReleased
        System.out.println("RATAS");
    }//GEN-LAST:event_comboBoxContactoClienteKeyReleased

    private void btnRazonSocialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRazonSocialActionPerformed
        Clientes clienteAdd = new Clientes();
        Principal.jDesktopPane1.add(clienteAdd);
        clienteAdd.toFront();
        clienteAdd.setVisible(true);

    }//GEN-LAST:event_btnRazonSocialActionPerformed

    private void btnContactoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactoClienteActionPerformed

        if (x != 0) {
            ContactosClientes conCliente = null;
            try {
                conCliente = new ContactosClientes();
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }
            Principal.jDesktopPane1.add(conCliente);
            conCliente.toFront();
            conCliente.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Agregar Cliente");

        }
    }//GEN-LAST:event_btnContactoClienteActionPerformed

    private void txtValidezActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtValidezActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtValidezActionPerformed

    private void txtCatClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCatClienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCatClienteActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        habilitarNuevo();

    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonGuardarVerPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarVerPdfActionPerformed
        GenerarReporte profRep = new GenerarReporte();

        try {
            profRep.ReporteProforma(Integer.parseInt(labelIdProforma.getText()), txtCodProf.getText(), txtFecha.getText(), tipo, comboRazonSocial.getEditor().getItem().toString());
        } catch (SQLException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_buttonGuardarVerPdfActionPerformed

    private void buttonIprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonIprimirActionPerformed
        GenerarReporte profRep = new GenerarReporte();
        try {
            profRep.Imprimir(Integer.parseInt(labelIdProforma.getText()));
        } catch (SQLException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonIprimirActionPerformed

    private void buttonDescripcionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonDescripcionActionPerformed

        if ("SERVICIO".equals(tipo)) {
            DetalleProformas.procesarProductoServ("", 1);
            DetalleProformas.procesarProductoServ("SERVICIO INCLUYE:", 1);
            DetalleProformas.procesarProductoServ("PRE-RECTIFICADO", 1);
            DetalleProformas.procesarProductoServ("CROMADO DURO", 1);
            DetalleProformas.procesarProductoServ("RECTIFICADO FINAL", 1);
            DetalleProformas.procesarProductoServ("PULIDO", 1);
            DetalleProformas.procesarProductoServ("", 1);
        } else if ("PRODUCTO".equals(tipo)) {
            System.out.println("el tipo es producto descripcion");
            DetalleProformas.procesarProductoServ("", 1);
            DetalleProformas.procesarProductoServ("DATOS TECNICOS:", 1);
            DetalleProformas.procesarProductoServ("MATERIAL: ACERO 1045 ENDURECIDO POR INDUCCIÓN", 1);
            DetalleProformas.procesarProductoServ("CAPA DE CROMO: 20 MICRAS", 1);
            DetalleProformas.procesarProductoServ("DUREZA: 55 - 62 HRC", 1);
            DetalleProformas.procesarProductoServ("MARCA: STELMI ITALIA SpA", 1);
            DetalleProformas.procesarProductoServ("", 1);
        }
        listaDetalleProforma();
    }//GEN-LAST:event_buttonDescripcionActionPerformed

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed

        String codigo;
        int anio=Integer.parseInt(txtAnio.getText());

        System.out.println("codigo +1:" + (Integer.parseInt(txtCodProf.getText()) + 1));
        System.out.println("codigo +1:" + String.format("%06d", (Integer.parseInt(txtCodProf.getText()) + 1)));
        codigo = String.format("%06d", (Integer.parseInt(txtCodProf.getText()) + 1));
        limpiar();

        try {
            consultarProforma(codigo, anio);
            consultarTipoCambio(txtFecha.getText());
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }

        a = labelIdProforma.getText();
        b = Integer.parseInt(a);
        cod = txtCodProf.getText();
        listaDetalleProforma();
        habilitarBuscar();

        try {
            consultarPago();
            System.out.println("Total incluido IGV: " + total);
            if ("DOLARES AMERICANOS".equals(categoria4) && "SERVICIO".equals(categoria2)) {
            try {
                consultarTotal(txtFecha.getText());
                if (Float.parseFloat(total) > 700) {
                        txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                        System.out.println("SUJETO A DETRACCIÓN 12%(COD.OP. 022) dolares");

                    } else if (Float.parseFloat(total) <700 || Float.parseFloat(total)==700) {
                            txtDetraccion.setText("");

                        }else  if ("DOLARES AMERICANOS".equals(categoria4) && "PRODUCTO".equals(categoria2)) {
                                txtDetraccion.setText("");
                             }
                } catch (Exception ex) {
                        Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
        } else if ("SOLES".equals(categoria4) && "SERVICIO".equals(categoria2)) {

            if (Float.parseFloat(labelTotal.getText()) > 700) {
                    txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                    System.out.println("SUJETO A DETRACCIÓN 12%(COD.OP. 022) soles");

                } else if (Float.parseFloat(labelTotal.getText())<700 || Float.parseFloat(labelTotal.getText())==700) {
                        txtDetraccion.setText("");
                
                    }  else if ("SOLES".equals(categoria4) && "PRODUCTO".equals(categoria2)) {
                            txtDetraccion.setText("");
                        }
        } else {
                txtDetraccion.setText("");
            }
                procesarProfUpdate(2);
                try {
                            consultarProforma(codigo, anio);
                        } catch (Exception ex) {
                                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                             }

            } catch (Exception ex) {
                    Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                }
       
        if (mensaje == true) {
            codigo = (String.format("%06d", (Integer.parseInt(codigo) - 1)));
            try {
                consultarProforma(codigo, anio);
                consultarTipoCambio(txtFecha.getText());
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }

            a = labelIdProforma.getText();
            b = Integer.parseInt(a);
            cod = txtCodProf.getText();
            listaDetalleProforma();
            habilitarBuscar();

            try {
                consultarPago();
//            verDetraccion(categoria4, categoria2,txtFecha.getText(), total, Float.parseFloat(labelTotal.getText()));
                System.out.println("Total incluido IGV: " + total);
            
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);

            }
            
        }
        mensaje=false;
     
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
        String codigo;
        int anio=Integer.parseInt(txtAnio.getText());
        System.out.println("codigo -1:" + (Integer.parseInt(txtCodProf.getText()) - 1));
        System.out.println("codigo +1:" + String.format("%06d", (Integer.parseInt(txtCodProf.getText()) - 1)));
        codigo = String.format("%06d", (Integer.parseInt(txtCodProf.getText()) - 1));
        limpiar();
        try {
            consultarProforma(codigo, anio);
            consultarTipoCambio(txtFecha.getText());
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }
        a = labelIdProforma.getText();
        b = Integer.parseInt(a);
        cod = txtCodProf.getText();
        listaDetalleProforma();
        habilitarBuscar();

        try {
            consultarPago();
//            verDetraccion(categoria4, categoria2,txtFecha.getText(), total, Float.parseFloat(labelTotal.getText()));
            System.out.println("Total incluido IGV: " + total);
                        if ("DOLARES AMERICANOS".equals(categoria4) && "SERVICIO".equals(categoria2)) {
            try {
                consultarTotal(txtFecha.getText());
                if (Float.parseFloat(total) > 700) {
                        txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                        System.out.println("SUJETO A DETRACCIÓN 12%(COD.OP. 022) dolares");

                    } else if (Float.parseFloat(total) <700 || Float.parseFloat(total)==700) {
                            txtDetraccion.setText("");

                        }else  if ("DOLARES AMERICANOS".equals(categoria4) && "PRODUCTO".equals(categoria2)) {
                                txtDetraccion.setText("");
                             }
                } catch (Exception ex) {
                        Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                    }
           
        } else if ("SOLES".equals(categoria4) && "SERVICIO".equals(categoria2)) {

            if (Float.parseFloat(labelTotal.getText()) > 700) {
                    txtDetraccion.setText("SUJETO A DETRACCIÓN 12%(COD.OP. 022)");
                    System.out.println("SUJETO A DETRACCIÓN 12%(COD.OP. 022) soles");

                } else if (Float.parseFloat(labelTotal.getText())<700 || Float.parseFloat(labelTotal.getText())==700) {
                        txtDetraccion.setText("");
                
                    }  else if ("SOLES".equals(categoria4) && "PRODUCTO".equals(categoria2)) {
                            txtDetraccion.setText("");
                        }
        } else {
                txtDetraccion.setText("");
            }
                procesarProfUpdate(2);
                try {
                            consultarProforma(codigo, anio);
                        } catch (Exception ex) {
                                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
                             }

        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (mensaje == true) {
            codigo = (String.format("%06d", (Integer.parseInt(codigo)+1)));
            try {
                consultarProforma(codigo, anio);
                consultarTipoCambio(txtFecha.getText());
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
            }

            a = labelIdProforma.getText();
            b = Integer.parseInt(a);
            cod = txtCodProf.getText();
            listaDetalleProforma();
            habilitarBuscar();

            try {
                consultarPago();
//            verDetraccion(categoria4, categoria2,txtFecha.getText(), total, Float.parseFloat(labelTotal.getText()));
                System.out.println("Total incluido IGV: " + total);
                        
            } catch (Exception ex) {
                Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);

            }
            
        }
        mensaje=false;

    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonGenerarPedidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGenerarPedidoActionPerformed
        try {
            consultarCotizacionParaPedido(idProf);
        } catch (Exception ex) {
            Logger.getLogger(Proformas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (comprobar == false) {

            JOptionPane.showMessageDialog(null, "Cotización ya tiene pedido");
            habilitarBuscar();

        } else if (comprobar == true) {

            Pedidos ped = null;
            try {
                ped = new Pedidos();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ProformasVista.class.getName()).log(Level.SEVERE, null, ex);
            } catch (Exception ex) {
                Logger.getLogger(ProformasVista.class.getName()).log(Level.SEVERE, null, ex);
            }
            Principal.jDesktopPane1.add(ped);
            ped.toFront();
            ped.setVisible(true);
            Pedidos.NumProformasReg();
        }
    }//GEN-LAST:event_buttonGenerarPedidoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnContactoCliente;
    private javax.swing.JButton btnRazonSocial;
    private javax.swing.JButton buttonAnterior;
    private javax.swing.JButton buttonBuscarProf;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonDescripcion;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGenerarPedido;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonGuardarVerPdf;
    private javax.swing.JButton buttonIprimir;
    private javax.swing.JButton buttonLimpiarTodo;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonProducto;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonSiguiente;
    private static javax.swing.JComboBox<String> comboBoxContactoCliente;
    private javax.swing.JComboBox<String> comboBoxMoneda;
    private javax.swing.JComboBox<String> comboBoxTipo;
    private javax.swing.JComboBox<String> comboBoxUsuario;
    private javax.swing.JComboBox<String> comboRazonSocial;
    private javax.swing.JButton jButton1;
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
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private static javax.swing.JTable jTable1;
    private javax.swing.JLabel labelCambioCompra;
    private javax.swing.JLabel labelCambioVenta;
    private static javax.swing.JLabel labelIdProforma;
    private static javax.swing.JLabel labelIgv;
    private static javax.swing.JLabel labelSubtotal;
    public static javax.swing.JLabel labelTotal;
    private javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCatCliente;
    public static javax.swing.JTextField txtCodProf;
    public static javax.swing.JTextField txtDetraccion;
    public static javax.swing.JTextField txtDia1;
    public static javax.swing.JTextField txtDia2;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtEmail;
    public static javax.swing.JTextField txtFecha;
    public static javax.swing.JComboBox<String> txtFormPago;
    public static javax.swing.JTextArea txtObservacion;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtTlf;
    public static javax.swing.JTextField txtValidez;
    // End of variables declaration//GEN-END:variables

    TipoCambioController tipCamControl = new TipoCambioController();
    TipoCambio tipCam;
// --------------------------- Procesar ---------------------------

    private void procesar(int op) {
        proforma = leerDatos();
        try {
            System.out.println("11111111111");
            String msg = proformacontroler.ProformaProcesar(proforma, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }

  static   void procesar1(int op) {
        proforma = leerDatos1();
        try {
            System.out.println("11111111111");
            String msg = proformacontroler.ProformaProcesar(proforma, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
  static   void procesarProfUpdate(int op) {
        proforma = leerDatos1();
        try {
            System.out.println("11111111111");
            String msg = proformacontroler.ProformaProcesar(proforma, op);
            
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }

    // ------------------------------- Consultar ------------------------------------
//    private void consultarCliente() throws Exception {
//        cliente = clientecontroler.ClienteBuscar1(Integer.parseInt(XXXXXXXXXXXXXXXXXXXXXXXXXXX.getText()));
//        if (cliente != null) {
//
//            //txtRazonSocial.setText(pro.getRazonSocial());
//            comboRazonSocial.setSelectedItem(cliente.getRazonSocial());
//            txtRuc.setText(cliente.getRuc());
//            txtDireccion.setText(cliente.getDireccion());
//
//        } else {
//            JOptionPane.showMessageDialog(null, "Cliente no registrado por ahora");
//            //System.out.println("Error");
//        }
//    }
    // se esta usando este metodo para ambas partes
    // este metodo busca por nombre concatenado que esta en el combobox
    private void consultarContactoCliente() throws Exception {

        contactocliente = contactoclientecontroler.ContactoClienteBuscar1(categoria1, idCliente);
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

    // este metodo carga todos los clientes, en el combo
    private void consultarComboCliente(String razonsocial) throws Exception {
        cliente = clientecontroler.ClienteBuscar(razonsocial);
        if (cliente != null) {
            //CREAR ID CLIENTE Y ASIGNARLE EL ID CLIENTE.
            idCliente = cliente.getIdCliente();
            System.out.println("2222222222222222222222222MIRA AQUI!!!");
            System.out.println("idCliente de consultar comboCliente:"+idCliente);
            x = cliente.getIdCliente();
            comboRazonSocial.setSelectedItem(cliente.getRazonSocial());
            txtRuc.setText(cliente.getRuc());
            txtDireccion.setText(cliente.getDireccion());
            txtCatCliente.setText(cliente.getTipo());
//            txtObsCliente.setText(cliente.getObservacion());

        } else {
            JOptionPane.showMessageDialog(null, "Cliente no esta registrado");
            //System.out.println("Error");
        }
    }
    // metodo que busca por id cliente

    private void consultarClientePorId(int iddecliente) throws Exception {
        cliente = clientecontroler.ClienteBuscar1(iddecliente);
        if (cliente != null) {
            
            comboRazonSocial.setSelectedItem(cliente.getRazonSocial());
            txtRuc.setText(cliente.getRuc());
            txtDireccion.setText(cliente.getDireccion());
            txtCatCliente.setText(cliente.getTipo());
//            txtObsCliente.setText(cliente.getObservacion());

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
            categoria1 = nombre + " " + apellido;
            System.out.println(categoria1);

        } else {
            JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarUsuario() throws Exception {
        usuario = usuariocontroler.UsuarioBuscar1(categoria3);
        if (usuario != null) {

            idUs = usuario.getIdUsuario();
            System.out.println(idUs);

        } else {
            JOptionPane.showMessageDialog(null, "Usuario no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarUsuario1() throws Exception {
        usuario = usuariocontroler.UsuarioBuscar2(idUs);
        if (usuario != null) {
            nombre = usuario.getNombres();
            apellido = usuario.getApellidos();
            categoria3 = nombre + " " + apellido;
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarProforma1(int id) throws Exception {

        proforma = proformacontroler.ProformaBuscar1(id);
        if (proforma != null) {

            codProforma = proforma.getCodProforma();
            generarId = Integer.parseInt(codProforma);
        } else {
            //JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }

    private void consultarUltimoId(int anio) throws Exception {
        proforma = proformacontroler.ProformaBuscarUltimoId(anio);
        if (proforma != null) {
            ultimoId = proforma.getIdProforma();
        }
       
    }

    private void GenerarCodigo() throws Exception {
        consultarUltimoId(Integer.parseInt(txtAnio.getText()));
        consultarProforma1(ultimoId);
        generarId = generarId + 1;
        txtCodProf.setText(String.format("%06d", generarId));
        System.out.println("codigo generado:" + String.format("%06d", generarId));
    }

    private void consultarProforma(String codigo, int anio) throws Exception {
        proforma = proformacontroler.ProformaBuscar(codigo, anio);
        if (proforma != null) {
            
            labelIdProforma.setText(Integer.toString(proforma.getIdProforma()));
            idProf=proforma.getIdProforma();
            txtCodProf.setText(proforma.getCodProforma());
            idCliente = proforma.getIdCliente();
            Clientes.x=proforma.getIdCliente();

            consultarClientePorId(idCliente);

            comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboRazonSocial.getEditor().getItem().toString(), idCliente));

            idContactoCliente = proforma.getIdContactoCliente();
            System.out.println("idCCli:" + idContactoCliente);
            consultarContactoCliente1();
            //consultar contacto asigna a categoria1 su nombre concatenado
            String testValue1 = categoria1;
            System.out.println("proforma contacto cliente:" + categoria1);

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
            String testValue2 = categoria3;
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

            txtValidez.setText(proforma.getValidez());
            txtFormPago.setSelectedItem(proforma.getFormPago());

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

            txtDetraccion.setText(proforma.getDetraccion());
            txtFecha.setText(proforma.getFechaEmision());
            txtDia1.setText(Integer.toString(proforma.getDia1()));
            txtDia2.setText(Integer.toString(proforma.getDia2()));

            estado = proforma.getEstado();

            txtObservacion.setText(proforma.getObservacion());
            observacionInterna=proforma.getObservacionInterna();

        } else {
            JOptionPane.showMessageDialog(null, "Proforma no registrada");
            limpiar();
            deshabilitarInicio();
            mensaje=true;
            System.out.println("Proforma no registrada");
        }
    }

    static void consultarPago() throws Exception {
        detalleproforma = detalleproformacontroler.DetalleProformaBuscar1(Integer.parseInt(labelIdProforma.getText()));

        if (detalleproforma != null) {
            if (detalleproforma.getSubtotal() != null) {
                labelSubtotal.setText(detalleproforma.getSubtotal());
            } else {
                labelSubtotal.setText("00.00");
            }
            if (detalleproforma.getIgv() != null) {
                labelIgv.setText(detalleproforma.getIgv());
            } else {
                labelIgv.setText("00.00");
            }
            if (detalleproforma.getTotal() != null) {
                labelTotal.setText(detalleproforma.getTotal());
            } else {
                labelTotal.setText("00.00");
            }

        } else {
            JOptionPane.showMessageDialog(null, "Cuentas no registradas");
            //System.out.println("Error");
        }
    }

    static void consultarTotal(String fecha) throws Exception {
        detalleproforma = detalleproformacontroler.DetalleProformaBuscar(fecha, Integer.parseInt(labelIdProforma.getText()));

        if (detalleproforma != null) {

            total = detalleproforma.getTotal();
            System.out.println("sexto: "+ total);

            if (detalleproforma.getTotal() == null) {
                total = "0";
            }

        } else {

            JOptionPane.showMessageDialog(null, "Cuentas no registradas");
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

    // ----------------------------Combo Box ----------------------------------------
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

    // --------------------------- Leer Datos ---------------------------------------------
    private Proforma leerDatos() {
        Proforma prof = new Proforma();

        prof.setCodProforma(txtCodProf.getText());
        prof.setIdCliente(idCliente);
        prof.setIdContactoCliente(idContactoCliente);
        prof.setIdUsuario(idUs);
        prof.setMoneda(categoria4);
        prof.setValidez(txtValidez.getText());
        prof.setFormPago(txtFormPago.getEditor().getItem().toString());
        prof.setTipo(categoria2);
        prof.setDetraccion(txtDetraccion.getText());
        prof.setFechaEmision(txtFecha.getText());
        prof.setDia1(Integer.parseInt(txtDia1.getText()));
        prof.setDia2(Integer.parseInt(txtDia2.getText()));
        prof.setEstado(estado);
        prof.setObservacion(txtObservacion.getText());
        prof.setObservacionInterna(observacionInterna);

        return prof;
    }

    static Proforma leerDatos1() {
        Proforma prof = new Proforma();

        prof.setIdCliente(idCliente);
        prof.setIdContactoCliente(idContactoCliente);
        prof.setIdUsuario(idUs);
        prof.setMoneda(categoria4);
        prof.setValidez(txtValidez.getText());
        prof.setFormPago(txtFormPago.getEditor().getItem().toString());
        prof.setTipo(categoria2);
        prof.setDetraccion(txtDetraccion.getText());
        prof.setFechaEmision(txtFecha.getText());
        prof.setDia1(Integer.parseInt(txtDia1.getText()));
        prof.setDia2(Integer.parseInt(txtDia2.getText()));
        prof.setEstado(estado);
        prof.setObservacion(txtObservacion.getText());
        prof.setObservacionInterna(observacionInterna);
        prof.setIdProforma(Integer.parseInt(labelIdProforma.getText()));

        return prof;
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

    // ------------------------------ Limpiar ----------------------------------------
    public void limpiar() {
        labelIdProforma.setText("0");
        listaDetalleProforma();
        txtCodProf.setText("");
        txtFecha.setText(fechaActual());
        txtAnio.setText(anioActual());
        txtFormPago.setSelectedIndex(4);
        comboRazonSocial.setSelectedItem("");
        txtRuc.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtTlf.setText("");
        txtValidez.setText("10 DIAS HABILES");
        txtObservacion.setText("");
        txtCatCliente.setText("");
        txtDetraccion.setText("");
        labelSubtotal.setText("000000000000000000");
        labelIgv.setText("000000000000000000");
        labelTotal.setText("000000000000000000");
        comboBoxTipo.setSelectedIndex(1);
        comboBoxMoneda.setSelectedIndex(1);
        comboBoxUsuario.setSelectedIndex(1);
        estado="";
//        categoria5 ="";
        comboBoxContactoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        categoria1 = "";
        total = "00.00";
        txtDia1.setText("1");
        txtDia2.setText("2");
    }
    
    
   

    public void limpiarComboCliente() {
        comboRazonSocial.setSelectedItem("");
        comboBoxContactoCliente.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        txtRuc.setText("");
        txtDireccion.setText("");
        txtEmail.setText("");
        txtTlf.setText("");
        txtCatCliente.setText("");
//        txtObsCliente.setText("");

    }

    // ------------------------------------ TABLA ---------------------------------------
    static void listaDetalleProforma() {
        List<DetalleProforma> lista;
        try {
            lista = detalleproformacontroler.DetalleProformaFiltrar(Integer.parseInt(labelIdProforma.getText()));
            verDetalleProforma(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static private void verDetalleProforma(List<DetalleProforma> lista) {
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

            Object[] fila = {detProf.getIdDetalleProforma(), detProf.getItem(), detProf.getCantidad(), detProf.getDescripcion(), detProf.getPrecioUnitario(),f, it};
            tabla.addRow(fila);
        }

    }
    //-------------------------------- Habilitar --------------

    void deshabilitarInicio() {

        buttonBuscarProf.setEnabled(true);
        buttonLimpiarTodo.setEnabled(true);
        btnRazonSocial.setEnabled(false);
        btnContactoCliente.setEnabled(false);
        jButton1.setEnabled(false);//buttonFormPago
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonProducto.setEnabled(false);
        buttonGuardarVerPdf.setEnabled(false);
        buttonIprimir.setEnabled(false);
        buttonDescripcion.setEnabled(false);
        buttonGenerarPedido.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        txtCodProf.setEnabled(true);
        txtFecha.setEnabled(false);
        comboRazonSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtValidez.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        txtDetraccion.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtCatCliente.setEnabled(false);
        txtObservacion.setEnabled(false);

    }

    void habilitarBuscar() {

        buttonBuscarProf.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        btnRazonSocial.setEnabled(false);
        btnContactoCliente.setEnabled(false);
        jButton1.setEnabled(false);//buttonFormPago
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonProducto.setEnabled(true);
        buttonGuardarVerPdf.setEnabled(true);
        buttonIprimir.setEnabled(true);
        buttonDescripcion.setEnabled(true);
        buttonGenerarPedido.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);

        txtCodProf.setEnabled(true);
        txtFecha.setEnabled(false);
        comboRazonSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtValidez.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        txtDetraccion.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtCatCliente.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    void habilitarModificar() {

        buttonBuscarProf.setEnabled(false);
        buttonLimpiarTodo.setEnabled(false);
        btnRazonSocial.setEnabled(false);
        btnContactoCliente.setEnabled(true);
        jButton1.setEnabled(true);//buttonFormPago
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        buttonProducto.setEnabled(false);
        buttonGuardarVerPdf.setEnabled(false);
        buttonIprimir.setEnabled(false);
        buttonDescripcion.setEnabled(false);
        buttonGenerarPedido.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        txtCodProf.setEnabled(true);
        txtFecha.setEnabled(true);
        comboRazonSocial.setEnabled(true);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTlf.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        txtFormPago.setEnabled(true);
        txtValidez.setEnabled(true);
        txtDia1.setEnabled(true);
        txtDia2.setEnabled(true);
        comboBoxTipo.setEnabled(true);
        txtDetraccion.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        txtCatCliente.setEnabled(false);
        txtObservacion.setEnabled(true);
    }

    void habilitarNuevo() {

        buttonBuscarProf.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        btnRazonSocial.setEnabled(true);
        btnContactoCliente.setEnabled(true);
        jButton1.setEnabled(true);//buttonFormPago
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonProducto.setEnabled(false);
        buttonGuardarVerPdf.setEnabled(false);
        buttonIprimir.setEnabled(false);
        buttonDescripcion.setEnabled(false);
        buttonGenerarPedido.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        txtCodProf.setEnabled(true);
        txtFecha.setEnabled(true);
        comboRazonSocial.setEnabled(true);
        txtRuc.setEnabled(true);
        txtDireccion.setEnabled(true);
        comboBoxContactoCliente.setEnabled(true);
        txtEmail.setEnabled(true);
        txtTlf.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        txtFormPago.setEnabled(true);
        txtValidez.setEnabled(true);
        txtDia1.setEnabled(true);
        txtDia2.setEnabled(true);
        txtDetraccion.setEnabled(true);
        comboBoxTipo.setEnabled(true);
        comboBoxUsuario.setEnabled(true);
        txtCatCliente.setEnabled(false);
        txtObservacion.setEnabled(true);
    }

    void habilitarGuardar() {

        buttonBuscarProf.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        btnRazonSocial.setEnabled(false);
        btnContactoCliente.setEnabled(false);
        jButton1.setEnabled(false);//buttonFormPago
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonProducto.setEnabled(true);
        buttonGuardarVerPdf.setEnabled(true);
        buttonIprimir.setEnabled(true);
        buttonDescripcion.setEnabled(true);
        buttonGenerarPedido.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);

        txtCodProf.setEnabled(true);
        txtFecha.setEnabled(false);
        comboRazonSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtValidez.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        txtDetraccion.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtCatCliente.setEnabled(false);
        txtObservacion.setEnabled(false);
    }

    void deshabilitarRegistrar() {

        buttonBuscarProf.setEnabled(false);
        buttonLimpiarTodo.setEnabled(true);
        btnRazonSocial.setEnabled(false);
        btnContactoCliente.setEnabled(false);
        jButton1.setEnabled(false);//buttonFormPago
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonProducto.setEnabled(true);
        buttonGuardarVerPdf.setEnabled(true);
        buttonIprimir.setEnabled(true);
        buttonDescripcion.setEnabled(true);
        buttonGenerarPedido.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);

        txtCodProf.setEnabled(true);
        txtFecha.setEnabled(false);
        comboRazonSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtDireccion.setEnabled(false);
        comboBoxContactoCliente.setEnabled(false);
        txtEmail.setEnabled(false);
        txtTlf.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtFormPago.setEnabled(false);
        txtValidez.setEnabled(false);
        txtDia1.setEnabled(false);
        txtDia2.setEnabled(false);
        comboBoxTipo.setEnabled(false);
        txtDetraccion.setEnabled(false);
        comboBoxUsuario.setEnabled(false);
        txtCatCliente.setEnabled(false);
        txtObservacion.setEnabled(false);

    }
    
 //----------------- metodo q jala numero desde Proformas vista -------------
    static void NumProformas() {
        ProformasVista.numeroCotizacion= ProformasVista.txtCodProf.getText();
        System.out.println(ProformasVista.numeroCotizacion);
        txtCodProf.setText(ProformasVista.numeroCotizacion);
        
    }
    
    static void NumProformaPedido() {
    
        txtCodProf.setText(PedidosVista.numCot);

    }
 
// ---------------------------- Para generar pedido -------------------
    private void consultarCotizacionParaPedido(int ic) throws Exception {
        proforma = proformacontroler.ProformaBuscarCotizacionParaPedido(ic);
        if (proforma != null) {
            idProf=proforma.getIdProforma();
            numPed=proforma.getNumPedido();
            comprobar=false;
        } else {
            comprobar=true;
          }
    }

}

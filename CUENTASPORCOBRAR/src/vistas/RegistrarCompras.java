/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ComprobanteSunatController;
import controller.DetalleOrdenCompraController;
import controller.GlosaController;
import controller.OrdenCompraController;
import controller.ProveedorController;
import controller.RegistroCompraController;
import controller.TipoCambioController;
import entity.ComprobanteSunat;
import entity.DetalleOrdenCompra;
import entity.Glosa;
import entity.OrdenCompra;
import entity.Proveedor;
import entity.RegistroCompra;
import entity.TipoCambio;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARCRODINPC-05
 */
public class RegistrarCompras extends javax.swing.JInternalFrame {
    int idRegistroCompras;
    int idRegistroComprasStatic;
    int idProveedor=0;
    int idGlosa=0;
    int idComprobante=0; 
    int idOrdenCompra=0;
    String monedaCombo;
    String detraccionCombo;
    String estado="EN PROCESO";
    String observacion="";
    
    String tipoCambioVenta;
    float totalDolares;
    float totalSolesCalculo;
    float totalDetraccion;
    
    boolean mensaje=true;
    boolean mensajeCompra=true;
    
    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
    /**
     * Creates new form RegistrarCompras
     */
    
    ProveedorController proveedorcontroler= new ProveedorController();
    Proveedor proveedor;
    
    ComprobanteSunatController comprobantecontroler= new ComprobanteSunatController() ;
    ComprobanteSunat comprobante;
    
    GlosaController glosacontroler= new GlosaController();
    Glosa glosa;
    
    public RegistrarCompras() throws ParseException {
        initComponents();
        habilitarInicio();
        txtFechaRegistro.setText(fechaActual());
        dateChooserFechaEmision.setCalendar(convertirFechaDate(fechaActual()));
        dateChooserFechaAprobacion.setCalendar(convertirFechaDate(fechaActual()));
        dateChooserFechaVencimiento.setCalendar(convertirFechaDate(fechaActual()));
        comboBoxProveedor.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxProveedor.getEditor().getItem().toString().trim();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        consultarProveedor(comboBoxProveedor.getItemAt(0));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comboBoxProveedor.setSelectedIndex(0);

                    if (compararProveedor(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarProveedor(cadenaEscrita);
//                            habilitarEnter();
                           
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        try {
                            for (int i = 0; i < comboBoxProveedor.getModel().getSize(); i++) {
                                if (comboBoxProveedor.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarProveedor(comboBoxProveedor.getItemAt(i));
                                    comboBoxProveedor.setSelectedIndex(i);
                                }
                            }
//                            habilitarEnter();
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
        
        
        comboBoxComprobante.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxComprobante.getEditor().getItem().toString().trim();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        consultarComprobante(comboBoxComprobante.getItemAt(0));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comboBoxComprobante.setSelectedIndex(0);

                    if (compararComprobante(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarComprobante(cadenaEscrita);
//                            habilitarEnter();
                           
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        try {
                            for (int i = 0; i < comboBoxComprobante.getModel().getSize(); i++) {
                                if (comboBoxComprobante.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarComprobante(comboBoxComprobante.getItemAt(i));
                                    comboBoxComprobante.setSelectedIndex(i);
                                }
                            }
//                            habilitarEnter();
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxComprobante.setModel(comprobantecontroler.ComprobanteSunatListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxComprobante.getItemCount() > 0) {
                        comboBoxComprobante.getEditor().setItem(cadenaEscrita);
                        comboBoxComprobante.showPopup();
                    } else {
                        comboBoxComprobante.addItem(cadenaEscrita);
                    }
                }
            }
        });
        
        
        comboBoxConcepto.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent evt) {
                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxConcepto.getEditor().getItem().toString().trim();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                        consultarGlosa(comboBoxConcepto.getItemAt(0));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comboBoxConcepto.setSelectedIndex(0);

                    if (compararGlosa(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarGlosa(cadenaEscrita);
//                            habilitarEnter();
                           
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    } else {
                        try {
                            for (int i = 0; i < comboBoxConcepto.getModel().getSize(); i++) {
                                if (comboBoxConcepto.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarGlosa(comboBoxConcepto.getItemAt(i));
                                    comboBoxConcepto.setSelectedIndex(i);
                                }
                            }
//                            habilitarEnter();
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }

                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxConcepto.setModel(glosacontroler.GlosaListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxConcepto.getItemCount() > 0) {
                        comboBoxConcepto.getEditor().setItem(cadenaEscrita);
                        comboBoxConcepto.showPopup();
                    } else {
                        comboBoxConcepto.addItem(cadenaEscrita);
                    }
                }
            }
        });
    }
    
    private boolean compararProveedor(String cadena) {
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
    
    private boolean compararComprobante(String cadena) {
        Object[] lista = comboBoxComprobante.getComponents();
        boolean encontrado = false;
        for (Object object : lista) {
            if (cadena.equals(object)) {
                encontrado = true;
                break;
            }

        }
        return encontrado;
    }
    
    private boolean compararGlosa(String cadena) {
        Object[] lista = comboBoxConcepto.getComponents();
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
        txtAnioOrdenCompra = new javax.swing.JTextField();
        txtNumOrdenCompraBuscar = new javax.swing.JTextField();
        buttonOrdenCompra = new javax.swing.JButton();
        buttonBuscarOrdenCompra = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtSerieBuscar = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        buttonBuscar = new javax.swing.JButton();
        buttonLimpiar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtFechaRegistro = new javax.swing.JTextField();
        buttonProveedor = new javax.swing.JButton();
        comboBoxProveedor = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        txtRuc = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txtNumeroOrdenCompra = new javax.swing.JTextField();
        buttonComprobante = new javax.swing.JButton();
        comboBoxComprobante = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        txtSerie = new javax.swing.JTextField();
        txtNumero = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        dateChooserFechaEmision = new com.toedter.calendar.JDateChooser();
        jLabel10 = new javax.swing.JLabel();
        dateChooserFechaAprobacion = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        txtCambioVenta = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        dateChooserFechaVencimiento = new com.toedter.calendar.JDateChooser();
        comboBoxConcepto = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        comboBoxMoneda = new javax.swing.JComboBox<>();
        buttonNuevo = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonConcepto = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        txtNumeroBuscar = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        txtMontoFacturado = new javax.swing.JTextField();
        labelSimboloSoles = new javax.swing.JLabel();
        txtCondicionSoles = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        comboBoxDetraccion = new javax.swing.JComboBox<>();
        jLabel18 = new javax.swing.JLabel();
        txtMontoDetraccion = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();
        labelIgv = new javax.swing.JLabel();
        labelSubtotal = new javax.swing.JLabel();
        buttonRegistroPagos = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Registro de Compras");

        jLabel1.setText("Año:");

        buttonOrdenCompra.setText("N° O. Compra");
        buttonOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonOrdenCompraActionPerformed(evt);
            }
        });

        buttonBuscarOrdenCompra.setText("Buscar");
        buttonBuscarOrdenCompra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarOrdenCompraActionPerformed(evt);
            }
        });

        jLabel2.setText("Serie Comprobante:");

        txtSerieBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel3.setText("Número Comprobante:");

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

        jLabel4.setText("Fecha Registro:");

        buttonProveedor.setText("Proveedor");
        buttonProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonProveedorActionPerformed(evt);
            }
        });

        comboBoxProveedor.setEditable(true);

        jLabel5.setText("R.U.C.:");

        jLabel6.setText("N° O.Compra:");

        buttonComprobante.setText("Comprobante");
        buttonComprobante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonComprobanteActionPerformed(evt);
            }
        });

        comboBoxComprobante.setEditable(true);

        jLabel7.setText("Serie:");

        jLabel8.setText("Número:");

        jLabel9.setText("Fecha Emisión:");

        dateChooserFechaEmision.setDateFormatString("yyyy-MM-dd");

        jLabel10.setText("Fecha Aprobación:");

        dateChooserFechaAprobacion.setDateFormatString("yyyy-MM-dd");

        jLabel11.setText("Cambio Venta:");

        jLabel12.setText("Fecha Vencimiento:");

        dateChooserFechaVencimiento.setDateFormatString("yyyy-MM-dd");

        comboBoxConcepto.setEditable(true);

        jLabel14.setText("Moneda:");

        comboBoxMoneda.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "DOLARES AMERICANOS", "SOLES" }));
        comboBoxMoneda.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxMonedaItemStateChanged(evt);
            }
        });

        buttonNuevo.setText("Nuevo");
        buttonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoActionPerformed(evt);
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
                "idOrdC", "Item", "Cant", "Descripción", "PrecioUnit", "Total"
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
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(60);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(400);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(150);
        }

        buttonConcepto.setText("Concepto");
        buttonConcepto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConceptoActionPerformed(evt);
            }
        });

        jLabel13.setText("Obs: En caso el comprobante de pago tenga Orden de Compra se mostrará el detalle, de lo contrario no.");

        txtNumeroBuscar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        jLabel15.setText("Monto Facturado:");

        labelSimboloSoles.setText("S/.");

        jLabel17.setText("Detracción:");

        comboBoxDetraccion.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONE", "APLICA", "NO APLICA" }));
        comboBoxDetraccion.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxDetraccionItemStateChanged(evt);
            }
        });

        jLabel18.setText("Monto Detracción:");

        jLabel19.setText("en Soles.");

        jLabel20.setText("Subtotal:");

        jLabel21.setText("I.G.V.:");

        jLabel22.setText("Total:");

        labelTotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelTotal.setText("000000.00");

        labelIgv.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelIgv.setText("000000.00");

        labelSubtotal.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        labelSubtotal.setText("000000.00");

        buttonRegistroPagos.setText("Registrar Pagos de Compra");
        buttonRegistroPagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistroPagosActionPerformed(evt);
            }
        });

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
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
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel6)
                                        .addComponent(buttonComprobante)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel8))
                                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(buttonProveedor, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 158, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel14))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonConcepto))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(comboBoxComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel12))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtNumeroOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel10))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jLabel11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(comboBoxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel9)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(dateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(comboBoxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(dateChooserFechaAprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel18))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtCambioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(jLabel17))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(dateChooserFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addGap(18, 18, 18)
                                                        .addComponent(jLabel15)))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtMontoFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(labelSimboloSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(txtCondicionSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addComponent(txtMontoDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                        .addComponent(jLabel19))
                                                    .addComponent(comboBoxDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonModificar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 841, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonRegistroPagos))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAnioOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonOrdenCompra)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNumOrdenCompraBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonBuscarOrdenCompra)
                                        .addGap(0, 0, 0)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtSerieBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtNumeroBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonBuscar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonLimpiar)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel13))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel21, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel20, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(labelSubtotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(333, 333, 333))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelIgv, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(buttonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtAnioOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumOrdenCompraBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonOrdenCompra)
                    .addComponent(buttonBuscarOrdenCompra)
                    .addComponent(jLabel2)
                    .addComponent(txtSerieBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(buttonBuscar)
                    .addComponent(buttonLimpiar)
                    .addComponent(jLabel4)
                    .addComponent(txtFechaRegistro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNumeroBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonProveedor)
                        .addComponent(comboBoxProveedor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel9))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonNuevo)
                            .addComponent(jLabel15)
                            .addComponent(txtMontoFacturado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(labelSimboloSoles)
                            .addComponent(txtCondicionSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(dateChooserFechaEmision, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel5)
                        .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel11)
                        .addComponent(txtCambioVenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel17)
                        .addComponent(comboBoxDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(buttonRegistrar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel6)
                        .addComponent(txtNumeroOrdenCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(dateChooserFechaAprobacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonModificar)
                        .addComponent(jLabel18)
                        .addComponent(txtMontoDetraccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(buttonComprobante)
                        .addComponent(comboBoxComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel12))
                    .addComponent(dateChooserFechaVencimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGuardar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboBoxConcepto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(buttonConcepto))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(comboBoxMoneda, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(buttonEliminar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 172, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(buttonRegistroPagos)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(labelSubtotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelIgv)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel22)
                    .addComponent(labelTotal)
                    .addComponent(buttonCerrar))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonComprobanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonComprobanteActionPerformed
        RegistroComprobanteSunat reCoSu = new RegistroComprobanteSunat();
        Principal.jDesktopPane1.add(reCoSu);
        reCoSu.toFront();
        reCoSu.setVisible(true);
    }//GEN-LAST:event_buttonComprobanteActionPerformed

    private void buttonConceptoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConceptoActionPerformed
        RegistrarGlosa regGlo = new RegistrarGlosa();
        Principal.jDesktopPane1.add(regGlo);
        regGlo.toFront();
        regGlo.setVisible(true);
    }//GEN-LAST:event_buttonConceptoActionPerformed

    private void comboBoxMonedaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxMonedaItemStateChanged
       if (comboBoxMoneda.getSelectedIndex() != 0) {
            monedaCombo = comboBoxMoneda.getSelectedItem().toString();
            System.out.println(monedaCombo);
        } else {
        }
    }//GEN-LAST:event_comboBoxMonedaItemStateChanged

    private void comboBoxDetraccionItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDetraccionItemStateChanged
        if (comboBoxDetraccion.getSelectedIndex() != 0) {
            detraccionCombo = comboBoxDetraccion.getSelectedItem().toString();
            System.out.println(detraccionCombo);
        } else {
        }
    }//GEN-LAST:event_comboBoxDetraccionItemStateChanged

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        if (idProveedor == 0 || "".equals(comboBoxProveedor.getSelectedItem().toString())) {

            JOptionPane.showMessageDialog(null, "No seleccionó correctamente al Proveedor(ENTER), REVISAR!!!");
        } else if (idComprobante == 0) {

            JOptionPane.showMessageDialog(null, "No seleccionó correctamente el Comprobante(ENTER), REVISAR!!!");
        } else if ("".equals(txtSerie.getText())) {

            JOptionPane.showMessageDialog(null, "La Serie de Comprobante de Compra está vacío, REVISAR!!!");
        } else if ("".equals(txtNumero.getText())) {

            JOptionPane.showMessageDialog(null, "El número de Comprobante de Compra está vacío, REVISAR!!!");
        } else if (idGlosa == 0) {

            JOptionPane.showMessageDialog(null, "El Cooncepto del Comprobante de Compra está vacío, REVISAR!!!");
        } else if (comboBoxMoneda.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(null, "Seleccionar MONEDA, REVISAR!!!");
        } else if (comboBoxDetraccion.getSelectedIndex() == 0) {

            JOptionPane.showMessageDialog(null, "Seleccionar si Aplica o no la detraccion, REVISAR!!!");
        } else{

            try {
                consultarTipoCambio(convertirFechaString(dateChooserFechaEmision.getDate()));
            } catch (Exception ex) {
                Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
            }

            try {
                consultarRegistroCompraExiste(txtSerie.getText(), txtNumero.getText());
            } catch (Exception ex) {
                Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (mensaje == true) {
                if (mensajeCompra == false) {
                    procesar(1);
                    try {
                        consultarRegistroCompra(txtSerie.getText(), txtNumero.getText());
                    } catch (Exception ex) {
                        Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    habilitarRegistrar();
                    try {
                        consultarOrdenCompra(txtNumeroOrdenCompra.getText());
                                } catch (Exception ex) {
                        Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        consultarPago(idOrdenCompra);
                    } catch (Exception ex) {
                        Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    listaDetalleOrdenCompra(idOrdenCompra);
                    
                } else if (mensajeCompra == true) {
                    JOptionPane.showMessageDialog(null, "Registro de Compra EXISTE!!! , REVISAR!!!");
                }

            } else if (mensaje == false) {
                JOptionPane.showMessageDialog(null, "No existe Tipo de Cambio para esa Fecha de Emisión, REVISAR!!!");
            }

        }
        
        
    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Se MODIFICARÁ el registro, ¿desea continuar?",
                "Modificar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            procesar(2);
            try {
                consultarRegistroCompra(txtSerie.getText(), txtNumero.getText());
            } catch (Exception ex) {
                Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarGuardar();
        }
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        try {
            consultarRegistroCompra(txtSerieBuscar.getText(), txtNumeroBuscar.getText());
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscar();
        try {
            consultarOrdenCompra(txtNumeroOrdenCompra.getText());
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarPago(idOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleOrdenCompra(idOrdenCompra);
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        if (JOptionPane.showConfirmDialog(rootPane, "Se eliminará el registro, ¿desea continuar?",
                "Eliminar Registro", JOptionPane.WARNING_MESSAGE, JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            procesar(3);
            try {
                limpiar();
            } catch (Exception ex) {
                Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarEliminar();
        }
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonOrdenCompraActionPerformed
        OrdenCompraParaRegistroCompras ordenCompraReg = new OrdenCompraParaRegistroCompras();
        Principal.jDesktopPane1.add(ordenCompraReg);
        ordenCompraReg.toFront();
        ordenCompraReg.setVisible(true);
        
    }//GEN-LAST:event_buttonOrdenCompraActionPerformed

    private void buttonBuscarOrdenCompraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarOrdenCompraActionPerformed
        try {
            consultarOrdenCompra(Integer.parseInt( txtNumOrdenCompraBuscar.getText()), Integer.parseInt(txtAnioOrdenCompra.getText()));
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarPago(idOrdenCompra);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        txtMontoFacturado.setText(labelTotal.getText());
        listaDetalleOrdenCompra(idOrdenCompra);
    }//GEN-LAST:event_buttonBuscarOrdenCompraActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        try {
            limpiar();
        } catch (ParseException ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonProveedorActionPerformed
        Proveedores prov = null;
        try {
            prov = new Proveedores();
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(prov);
        prov.toFront();
        prov.setVisible(true);
    }//GEN-LAST:event_buttonProveedorActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        habilitarNuevo();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void buttonRegistroPagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistroPagosActionPerformed
        RegistrarPagosCompra rePaCo = null;
        try {
            rePaCo = new RegistrarPagosCompra();
        } catch (Exception ex) {
            Logger.getLogger(RegistrarCompras.class.getName()).log(Level.SEVERE, null, ex);
        }
        RegistrarPagosCompra.idRegistroCompraStatic=idRegistroCompras;
        Principal.jDesktopPane1.add(rePaCo);
        rePaCo.toFront();
        rePaCo.setVisible(true);
    }//GEN-LAST:event_buttonRegistroPagosActionPerformed

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonBuscarOrdenCompra;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonComprobante;
    private javax.swing.JButton buttonConcepto;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonOrdenCompra;
    private javax.swing.JButton buttonProveedor;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonRegistroPagos;
    private javax.swing.JComboBox<String> comboBoxComprobante;
    private javax.swing.JComboBox<String> comboBoxConcepto;
    private javax.swing.JComboBox<String> comboBoxDetraccion;
    private javax.swing.JComboBox<String> comboBoxMoneda;
    private javax.swing.JComboBox<String> comboBoxProveedor;
    private com.toedter.calendar.JDateChooser dateChooserFechaAprobacion;
    private com.toedter.calendar.JDateChooser dateChooserFechaEmision;
    private com.toedter.calendar.JDateChooser dateChooserFechaVencimiento;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    public static javax.swing.JLabel labelIgv;
    private javax.swing.JLabel labelSimboloSoles;
    public static javax.swing.JLabel labelSubtotal;
    public static javax.swing.JLabel labelTotal;
    public static javax.swing.JTextField txtAnioOrdenCompra;
    private javax.swing.JTextField txtCambioVenta;
    private javax.swing.JTextField txtCondicionSoles;
    private javax.swing.JTextField txtFechaRegistro;
    private javax.swing.JTextField txtMontoDetraccion;
    private javax.swing.JTextField txtMontoFacturado;
    public static javax.swing.JTextField txtNumOrdenCompraBuscar;
    private javax.swing.JTextField txtNumero;
    public static javax.swing.JTextField txtNumeroBuscar;
    private javax.swing.JTextField txtNumeroOrdenCompra;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtSerie;
    public static javax.swing.JTextField txtSerieBuscar;
    // End of variables declaration//GEN-END:variables

    TipoCambioController tipocambiocontroler = new TipoCambioController();
    TipoCambio tipocambio;

    OrdenCompraController ordencompracontroler = new OrdenCompraController();
    OrdenCompra ordencompra;
    
    DetalleOrdenCompraController detalleordencompracontroler=new DetalleOrdenCompraController();
    DetalleOrdenCompra detalleordencompra;

    RegistroCompraController registrocompracontroler= new RegistroCompraController();
    RegistroCompra registrocompra;
    
//--------------------------- Consutar Proveedor Segun razonSocial--------------------------
    
    private void consultarProveedor(String razonsocial) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscar(razonsocial);
        if (proveedor != null) {

            idProveedor=proveedor.getIdProveedor();
            // txtNombres.setText(pro.getNombres());
            comboBoxProveedor.setSelectedItem(proveedor.getRazonSocial());
            txtRuc.setText(proveedor.getRuc());

        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarProveedor(int idProvee) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscarId(idProvee);
        if (proveedor != null) {

            idProveedor=proveedor.getIdProveedor();
            // txtNombres.setText(pro.getNombres());
            comboBoxProveedor.setSelectedItem(proveedor.getRazonSocial());
            txtRuc.setText(proveedor.getRuc());

        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no registrado");
            //System.out.println("Error");
        }
    }
    
  
 //--------------------------- Consutar Comrpobante segun descripcion --------------------------
    
    private void consultarComprobante(String comprob) throws Exception {
        comprobante = comprobantecontroler.ComprobanteSunatBuscarDescripcion(comprob);
        if (comprobante != null) {

            idComprobante=comprobante.getIdComprobanteSunat();
            comboBoxComprobante.setSelectedItem(comprobante.getDescripcion());

        } else {
            JOptionPane.showMessageDialog(null, "Comprobante no registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarComprobante(int idComprob) throws Exception {
        comprobante = comprobantecontroler.ComprobanteSunatBuscar(idComprob);
        if (comprobante != null) {

            idComprobante=comprobante.getIdComprobanteSunat();
            comboBoxComprobante.setSelectedItem(comprobante.getDescripcion());

        } else {
            JOptionPane.showMessageDialog(null, "Comprobante no registrado");
            //System.out.println("Error");
        }
    }
  
//--------------------------- Consutar Glosa segun concepto --------------------------
     private void consultarGlosa(String glos) throws Exception {
        glosa = glosacontroler.GlosaBuscarConcepto(glos);
        if (glosa != null) {

            idGlosa=glosa.getIdGlosa();
            comboBoxConcepto.setSelectedItem(glosa.getConcepto());

        } else {
            JOptionPane.showMessageDialog(null, "Concepto no registrado");
            //System.out.println("Error");
        }
    }
     
    
    private void consultarGlosa(int idGlosa) throws Exception {
        glosa = glosacontroler.GlosaBuscar(idGlosa);
        if (glosa != null) {

            idGlosa=glosa.getIdGlosa();
            comboBoxConcepto.setSelectedItem(glosa.getConcepto());
            
        } else {
            JOptionPane.showMessageDialog(null, "Glosa no registrada");
            //System.out.println("Error");
        }
    }
     
//----------------------------------- ConsultarRegistroCompra -----------------------------------
     
     private void consultarRegistroCompra(String ser, String num) throws Exception {
        registrocompra = registrocompracontroler.RegistroCompraBuscar(ser, num);
        if (registrocompra != null) {
            
            txtSerieBuscar.setText(registrocompra.getSerie());
            txtNumeroBuscar.setText(registrocompra.getNumero());
            idRegistroCompras=registrocompra.getIdRegistroCompra();
            RegistrarPagosCompra.idRegistroCompraStatic=registrocompra.getIdRegistroCompra();
            txtFechaRegistro.setText(registrocompra.getFechaRegistro());
            idProveedor=registrocompra.getIdProveedor_fk();
            consultarProveedor(registrocompra.getIdProveedor_fk());
            txtNumeroOrdenCompra.setText(registrocompra.getNumOrdenCompra());
            idComprobante=registrocompra.getIdComprobanteSunat_fk();
            consultarComprobante(registrocompra.getIdComprobanteSunat_fk());
            txtSerie.setText(registrocompra.getSerie());
            txtNumero.setText(registrocompra.getNumero()); 
            dateChooserFechaEmision.setCalendar(convertirFechaDate(registrocompra.getFechaEmision()));
            consultarTipoCambio(registrocompra.getFechaEmision());
            dateChooserFechaAprobacion.setCalendar(convertirFechaDate(registrocompra.getFechaAprobacion()));
            dateChooserFechaVencimiento.setCalendar(convertirFechaDate(registrocompra.getFechaVencimiento()));
            idGlosa=registrocompra.getIdGlosa_fk();
            consultarGlosa(idGlosa);
            monedaCombo = registrocompra.getMoneda();
            String testValue1 = monedaCombo;
            for (int i = 0; i < comboBoxMoneda.getModel().getSize(); i++) {
                if (comboBoxMoneda.getItemAt(i).equals(testValue1)) {
                    System.out.println(i);
                    comboBoxMoneda.setSelectedIndex(i);
                    break;
                }
            }
            
            txtMontoFacturado.setText(registrocompra.getMontoFacturado());
            
            detraccionCombo=registrocompra.getDetraccion();
             String testValue2 = detraccionCombo;
            for (int i = 0; i < comboBoxDetraccion.getModel().getSize(); i++) {
                if (comboBoxDetraccion.getItemAt(i).equals(testValue2)) {
                    System.out.println(i);
                    comboBoxDetraccion.setSelectedIndex(i);
                    break;
                }
            }
            
            calcularMontoDetraccionYMontoSoles();
            
            estado=registrocompra.getEstado();
            observacion=registrocompra.getObservacion();
       
          
        } else {
            JOptionPane.showMessageDialog(null, "Venta no registrado");
            System.out.println("Venta no registrada");
        }
    }
     
     
     private void consultarRegistroCompraExiste(String ser, String num) throws Exception {
        registrocompra = registrocompracontroler.RegistroCompraBuscar(ser, num);
        if (registrocompra != null) {
          
            mensajeCompra=true;
          
        } else {
            mensajeCompra=false;
            System.out.println("Compra no registrada");
        }
    }
     

//----------------------------------------- Procesar ---------------------------------------------

    void procesar(int op) {
        registrocompra = leerDatos();
        try {
            String msg = registrocompracontroler.RegistroCompraProcesar(registrocompra, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    } 

    
    RegistroCompra leerDatos() {
        RegistroCompra reco = new RegistroCompra();

        reco.setIdRegistroCompra(idRegistroCompras);
        reco.setFechaRegistro(txtFechaRegistro.getText());
        reco.setIdProveedor_fk(idProveedor);
        reco.setNumOrdenCompra(txtNumeroOrdenCompra.getText());
        reco.setIdComprobanteSunat_fk(idComprobante);
        reco.setSerie(txtSerie.getText());
        reco.setNumero(txtNumero.getText());
        reco.setFechaEmision(convertirFechaString(dateChooserFechaEmision.getDate()));
        reco.setFechaAprobacion(convertirFechaString(dateChooserFechaAprobacion.getDate()));
        reco.setFechaVencimiento(convertirFechaString(dateChooserFechaVencimiento.getDate()));
        reco.setIdGlosa_fk(idGlosa);
        reco.setMoneda(monedaCombo);
        reco.setMontoFacturado(txtMontoFacturado.getText());
        reco.setDetraccion(detraccionCombo);
        reco.setEstado(estado);
        reco.setObservacion(observacion);
        
        return reco;
    }
    
    

    
    
//--------------------- Consultar Orden Compra ----------------------  
    
    
    
    private void consultarOrdenCompra(int numordcompra, int aio) throws Exception {
        ordencompra = ordencompracontroler.OrdenCompraBuscar(numordcompra, aio);
        if (ordencompra != null) {
            
            idOrdenCompra = ordencompra.getOc_id();

            txtNumeroOrdenCompra.setText(ordencompra.getOc_num()+"-"+anioFecha(ordencompra.getOc_fecha()));

            idProveedor = ordencompra.getOc_idProveedor_fk();
            consultarProveedor(idProveedor);

            monedaCombo = ordencompra.getOc_moneda();
            System.out.println("moneda" + monedaCombo);
            String testValue3 = monedaCombo;
            for (int i = 0; i < comboBoxMoneda.getModel().getSize(); i++) {
                if (comboBoxMoneda.getItemAt(i).equals(testValue3)) {
                    System.out.println(i);
                    comboBoxMoneda.setSelectedIndex(i);
                    break;
                }
            }
            
            
        } else {
            
        }
    }
    
    
     private void consultarOrdenCompra(String ordComp) throws Exception {
        ordencompra = ordencompracontroler.OrdenCompraBuscar(ordComp);
        if (ordencompra != null) {
            
            idOrdenCompra = ordencompra.getOc_id();

            txtNumeroOrdenCompra.setText(ordencompra.getOc_num()+"-"+anioFecha(ordencompra.getOc_fecha()));

            idProveedor = ordencompra.getOc_idProveedor_fk();
            consultarProveedor(idProveedor);

            monedaCombo = ordencompra.getOc_moneda();
            System.out.println("moneda" + monedaCombo);
            String testValue3 = monedaCombo;
            for (int i = 0; i < comboBoxMoneda.getModel().getSize(); i++) {
                if (comboBoxMoneda.getItemAt(i).equals(testValue3)) {
                    System.out.println(i);
                    comboBoxMoneda.setSelectedIndex(i);
                    break;
                }
            }
            
            
        } else {
            
        }
    }
    
     void consultarPago(int idoc) throws Exception {
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
        }
    }  
    
    void listaDetalleOrdenCompra(int id) {
        List<DetalleOrdenCompra> lista;
        try {
            lista = detalleordencompracontroler.DetalleOrdenCompraFiltrar(id);
            verDetalleOrdenCompra(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
//            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verDetalleOrdenCompra(List<DetalleOrdenCompra> lista) {
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
                detoc.getDoc_desc(), detoc.getDoc_precioUnit(), it};
            tabla.addRow(fila);
        }

    }
    
//--------------------------- Consultar Tipo Cambio  -------------------------------

    void consultarTipoCambio(String f) throws Exception {
        tipocambio =tipocambiocontroler.TipoCambioBuscarCambio(f) ;

        if (tipocambio != null) {
            mensaje=true;
            tipoCambioVenta=Float.toString(tipocambio.getPrecioVenta());
            txtCambioVenta.setText(Float.toString(tipocambio.getPrecioVenta()));
        } else {
            mensaje=false;
//            JOptionPane.showMessageDialog(null, "tipo cambio no registradas");
            System.out.println("Error");
        }
    }


//    ----------------------- Muestra el monto en soles si en caso fuese dolares 
//                            y calcula el valor de detraccion segun el monto facturado  --------------------------

    void calcularMontoDetraccionYMontoSoles() {
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat df = new DecimalFormat("0.000", separadoresPersonalizados);

        try {
            consultarTipoCambio(convertirFechaString(dateChooserFechaEmision.getDate()));
        } catch (Exception ex) {
            Logger.getLogger(RegistroVentas.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (comboBoxMoneda.getSelectedIndex() == 1) {

            try {
                consultarTipoCambio(convertirFechaString(dateChooserFechaEmision.getDate()));
            } catch (Exception ex) {
                Logger.getLogger(RegistroVentas.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtCondicionSoles.setEnabled(true);
            labelSimboloSoles.setText("S/.");
            totalDolares = Float.parseFloat(txtMontoFacturado.getText());
            totalSolesCalculo = totalDolares * Float.parseFloat(tipoCambioVenta);
            txtCondicionSoles.setText(df.format(totalSolesCalculo));

            if (comboBoxDetraccion.getSelectedIndex() == 1) {

                totalDetraccion = (float) (Float.parseFloat(txtCondicionSoles.getText()) * 0.12);
                txtMontoDetraccion.setText(df.format(totalDetraccion));

            } else if (comboBoxDetraccion.getSelectedIndex() == 2) {

                txtMontoDetraccion.setText("");

            }

        } else if (comboBoxMoneda.getSelectedIndex() == 2) {

            txtCondicionSoles.setEnabled(false);
            txtCondicionSoles.setText("");
            labelSimboloSoles.setText("");
            totalSolesCalculo = Float.parseFloat(txtMontoFacturado.getText());

            if (comboBoxDetraccion.getSelectedIndex() == 1) {

                totalDetraccion = (float) (totalSolesCalculo * 0.12);
                txtMontoDetraccion.setText(df.format(totalDetraccion));

            } else if (comboBoxDetraccion.getSelectedIndex() == 2) {

                txtMontoDetraccion.setText("");

            }

        }

    }

    
//-------------------------------- Num OrdenCompra de Vista             ---------------------------------------------
    
static void numOrdenCompraYAnio(){
       txtNumOrdenCompraBuscar.setText(String.format("%06d",OrdenCompraVista.numOrdenCompraParaModificar));
       txtAnioOrdenCompra.setText(OrdenCompraVista.anio);
}

//-------------------------------- Num OrdenCompra de Registro             ---------------------------------------------
    
static void numOrdenCompraYAnioRegistro(){
       txtNumOrdenCompraBuscar.setText(String.format("%06d",OrdenesCompra.numOrdenCompraParaDetalle));
       txtAnioOrdenCompra.setText(OrdenesCompra.anioStatic);
}

//-------------------------------- Num OrdenCompra de Vista             ---------------------------------------------
    
static void numOrdenCompraYAnioParaRegistro(){
       txtNumOrdenCompraBuscar.setText(String.format("%06d",OrdenCompraParaRegistroCompras.numOrdenCompraParaModificar));
       txtAnioOrdenCompra.setText(OrdenCompraParaRegistroCompras.anio);
}
    
//--------------------------------- Fecha y año ---------------------------------------------
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

    
    Calendar convertirFechaDate(String ah) throws ParseException{
   
       Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;

        fechaDate=formato.parse(ah);

        fecha.setTime(fechaDate);
        
        return fecha;
    }
    
    
    String convertirFechaString(Date fech) {

        Format formatter = new SimpleDateFormat("yyyy-MM-dd ");

        return formatter.format(fech);

    }
    
    public String anioFecha(String f) throws ParseException {
        Calendar fecha = Calendar.getInstance();
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date fechaDate = null;

        fechaDate = formato.parse(f);

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy");
        return formatofecha.format(fechaDate);
    }
    
    // --------------------------- Mostrar Serie y Número --------------------------------
       static void SerieYNumeroDeVistaCompras() {
        txtSerieBuscar.setText(RegistroCompraVista.serieStatic);
        txtNumeroBuscar.setText(RegistroCompraVista.numeroStatic);
    }

   //--------------------------- Limpiar --------------------------------
    
    void limpiar() throws ParseException, Exception{
        
        txtAnioOrdenCompra.setText(anioActual());
        txtNumOrdenCompraBuscar.setText("");
        txtFechaRegistro.setText(fechaActual());
        comboBoxProveedor.setSelectedItem("");
        txtRuc.setText("");
        txtNumeroOrdenCompra.setText("");
        comboBoxComprobante.setSelectedItem("");
        txtSerie.setText("");
        txtNumero.setText("");
        dateChooserFechaEmision.setCalendar(convertirFechaDate(fechaActual()));
        txtCambioVenta.setText("");
        dateChooserFechaAprobacion.setCalendar(convertirFechaDate(fechaActual()));
        dateChooserFechaVencimiento.setCalendar(convertirFechaDate(fechaActual()));
        comboBoxConcepto.setSelectedItem("");
        comboBoxMoneda.setSelectedIndex(0);
        txtMontoFacturado.setText("");
        txtCondicionSoles.setEnabled(false);
        txtCondicionSoles.setText("");
        labelSimboloSoles.setText("");
        comboBoxDetraccion.setSelectedIndex(0);
        txtMontoDetraccion.setText("");
        
        txtSerieBuscar.setText("");
        txtNumeroBuscar.setText("");
        
        idProveedor = 0;
        idGlosa = 0;
        idComprobante = 0;
        idOrdenCompra = 0;
        consultarPago(0);
        listaDetalleOrdenCompra(0);
    }
    
 // ------------------------------ Habilitar ------------------------------------
    
    void habilitarInicio(){
        
        txtAnioOrdenCompra.setEnabled(false);
        buttonOrdenCompra.setEnabled(false);
        txtNumOrdenCompraBuscar.setEnabled(false);
        buttonBuscarOrdenCompra.setEnabled(false);

        txtSerieBuscar.setEnabled(true);
        txtNumeroBuscar.setEnabled(true);
        buttonBuscar.setEnabled(true);

        txtFechaRegistro.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtNumeroOrdenCompra.setEnabled(false);
        comboBoxComprobante.setEnabled(false);
        txtSerie.setEnabled(false);
        txtNumero.setEnabled(false);
        dateChooserFechaEmision.setEnabled(false);
        txtCambioVenta.setEnabled(false);
        dateChooserFechaAprobacion.setEnabled(false);
        dateChooserFechaVencimiento.setEnabled(false);
        comboBoxConcepto.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtMontoFacturado.setEnabled(false);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(false);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonRegistroPagos.setEnabled(false);
    }
    
    void habilitarNuevo(){
        
        txtAnioOrdenCompra.setEnabled(true);
        buttonOrdenCompra.setEnabled(true);
        txtNumOrdenCompraBuscar.setEnabled(true);
        buttonBuscarOrdenCompra.setEnabled(true);

        txtSerieBuscar.setEnabled(false);
        txtNumeroBuscar.setEnabled(false);
        buttonBuscar.setEnabled(false);

        txtFechaRegistro.setEnabled(true);
        comboBoxProveedor.setEnabled(true);
        txtRuc.setEnabled(true);
        txtNumeroOrdenCompra.setEnabled(true);
        comboBoxComprobante.setEnabled(true);
        txtSerie.setEnabled(true);
        txtNumero.setEnabled(true);
        dateChooserFechaEmision.setEnabled(true);
        txtCambioVenta.setEnabled(true);
        dateChooserFechaAprobacion.setEnabled(true);
        dateChooserFechaVencimiento.setEnabled(true);
        comboBoxConcepto.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        txtMontoFacturado.setEnabled(true);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(true);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonRegistroPagos.setEnabled(false);
    }
    
    void habilitarRegistrar(){
        
        txtAnioOrdenCompra.setEnabled(false);
        buttonOrdenCompra.setEnabled(false);
        txtNumOrdenCompraBuscar.setEnabled(false);
        buttonBuscarOrdenCompra.setEnabled(false);

        txtSerieBuscar.setEnabled(false);
        txtNumeroBuscar.setEnabled(false);
        buttonBuscar.setEnabled(false);

        txtFechaRegistro.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtNumeroOrdenCompra.setEnabled(false);
        comboBoxComprobante.setEnabled(false);
        txtSerie.setEnabled(false);
        txtNumero.setEnabled(false);
        dateChooserFechaEmision.setEnabled(false);
        txtCambioVenta.setEnabled(false);
        dateChooserFechaAprobacion.setEnabled(false);
        dateChooserFechaVencimiento.setEnabled(false);
        comboBoxConcepto.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtMontoFacturado.setEnabled(false);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(false);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonRegistroPagos.setEnabled(true);
    }
    
    void habilitarBuscar(){
        
        txtAnioOrdenCompra.setEnabled(false);
        buttonOrdenCompra.setEnabled(false);
        txtNumOrdenCompraBuscar.setEnabled(false);
        buttonBuscarOrdenCompra.setEnabled(false);

        txtSerieBuscar.setEnabled(false);
        txtNumeroBuscar.setEnabled(false);
        buttonBuscar.setEnabled(false);

        txtFechaRegistro.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtNumeroOrdenCompra.setEnabled(false);
        comboBoxComprobante.setEnabled(false);
        txtSerie.setEnabled(false);
        txtNumero.setEnabled(false);
        dateChooserFechaEmision.setEnabled(false);
        txtCambioVenta.setEnabled(false);
        dateChooserFechaAprobacion.setEnabled(false);
        dateChooserFechaVencimiento.setEnabled(false);
        comboBoxConcepto.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtMontoFacturado.setEnabled(false);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(false);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonRegistroPagos.setEnabled(true);
    }
    
    void habilitarModificar(){
        
        txtAnioOrdenCompra.setEnabled(true);
        buttonOrdenCompra.setEnabled(true);
        txtNumOrdenCompraBuscar.setEnabled(true);
        buttonBuscarOrdenCompra.setEnabled(true);

        txtSerieBuscar.setEnabled(false);
        txtNumeroBuscar.setEnabled(false);
        buttonBuscar.setEnabled(false);

        txtFechaRegistro.setEnabled(true);
        comboBoxProveedor.setEnabled(true);
        txtRuc.setEnabled(true);
        txtNumeroOrdenCompra.setEnabled(true);
        comboBoxComprobante.setEnabled(true);
        txtSerie.setEnabled(true);
        txtNumero.setEnabled(true);
        dateChooserFechaEmision.setEnabled(true);
        txtCambioVenta.setEnabled(true);
        dateChooserFechaAprobacion.setEnabled(true);
        dateChooserFechaVencimiento.setEnabled(true);
        comboBoxConcepto.setEnabled(true);
        comboBoxMoneda.setEnabled(true);
        txtMontoFacturado.setEnabled(true);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(true);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        buttonRegistroPagos.setEnabled(false);
    }
    
    void habilitarGuardar(){
        
        txtAnioOrdenCompra.setEnabled(false);
        buttonOrdenCompra.setEnabled(false);
        txtNumOrdenCompraBuscar.setEnabled(false);
        buttonBuscarOrdenCompra.setEnabled(false);

        txtSerieBuscar.setEnabled(false);
        txtNumeroBuscar.setEnabled(false);
        buttonBuscar.setEnabled(false);

        txtFechaRegistro.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtNumeroOrdenCompra.setEnabled(false);
        comboBoxComprobante.setEnabled(false);
        txtSerie.setEnabled(false);
        txtNumero.setEnabled(false);
        dateChooserFechaEmision.setEnabled(false);
        txtCambioVenta.setEnabled(false);
        dateChooserFechaAprobacion.setEnabled(false);
        dateChooserFechaVencimiento.setEnabled(false);
        comboBoxConcepto.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtMontoFacturado.setEnabled(false);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(false);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonRegistroPagos.setEnabled(true);
    }
    
    void habilitarEliminar(){
        
        txtAnioOrdenCompra.setEnabled(false);
        buttonOrdenCompra.setEnabled(false);
        txtNumOrdenCompraBuscar.setEnabled(false);
        buttonBuscarOrdenCompra.setEnabled(false);

        txtSerieBuscar.setEnabled(true);
        txtNumeroBuscar.setEnabled(true);
        buttonBuscar.setEnabled(true);

        txtFechaRegistro.setEnabled(false);
        comboBoxProveedor.setEnabled(false);
        txtRuc.setEnabled(false);
        txtNumeroOrdenCompra.setEnabled(false);
        comboBoxComprobante.setEnabled(false);
        txtSerie.setEnabled(false);
        txtNumero.setEnabled(false);
        dateChooserFechaEmision.setEnabled(false);
        txtCambioVenta.setEnabled(false);
        dateChooserFechaAprobacion.setEnabled(false);
        dateChooserFechaVencimiento.setEnabled(false);
        comboBoxConcepto.setEnabled(false);
        comboBoxMoneda.setEnabled(false);
        txtMontoFacturado.setEnabled(false);
        txtCondicionSoles.setEnabled(false);
        comboBoxDetraccion.setEnabled(false);
        txtMontoDetraccion.setEnabled(false);

        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonRegistroPagos.setEnabled(false);
    
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ClienteController;
import controller.DetalleGuiaDeRemisionController;
import controller.GuiaDeRemisionController;
import controller.MotivoGuiaController;
import controller.TransportistaController;
import entity.Cliente;
import entity.DetalleGuiaDeRemision;
import entity.GuiaDeRemision;
import entity.MotivoGuia;
import entity.Transportista;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reportes.GenerarReporte;

/**
 *
 * @author ARCRODINPC-06
 */
public class GuiaRemision extends javax.swing.JInternalFrame {
    static int idCliente;
    static int idGuiaRem;
    static String serieGuia;
    static String numGuia;
    String idDetalleGuia;
    int idDetGuia1;
    int tabla;
    String descrip;
    String dir;
    String tipo;
    String estadoCombo;
    String estado="PENDIENTE";
    String observacion="";
    
    String numeroGuia;
    boolean mensa=false;
    
    int generarNum;
    
    String conclu;
    int idMotivo;
    String motivo;
    
    MotivoGuiaController motivoguiacontroler=new MotivoGuiaController();
    MotivoGuia motivoguia;
    
    int idTransportista;
    
    TransportistaController transportistacontroler = new TransportistaController();
    Transportista transportista;

    /**
     * Creates new form GuiaRemision
     */
    public GuiaRemision() throws Exception {
        initComponents();
        habilitarInicio();
        txtFechEmi.setText(fechaActual());
        txtFechTras.setText(fechaActual());
        txtAnio.setText(anioActual());
        comboBoxNumSerie.setSelectedIndex(0);
        txtTipoComp.setText("FACTURA");
        txtNumCorr.setText("");
        
        comboBoxMotivo.setModel(motivoguiacontroler.ListarComboMotivoGuia());
        
        
        comboBoxRazSocial.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           
            public void keyReleased(KeyEvent evt) {

                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxRazSocial.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    limpiarComboCliente();
                    try {
                        consultarComboCliente(comboBoxRazSocial.getItemAt(0));
                             
                        //aCargarContactosCombo(cadenaEscrita);
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (comparar(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarComboCliente(cadenaEscrita);
                                                      
//                            a = labelIdProforma.getText();
//                            b = Integer.parseInt(a);
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            for (int i = 0; i < comboBoxRazSocial.getModel().getSize(); i++) {
                                if (comboBoxRazSocial.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarComboCliente(comboBoxRazSocial.getItemAt(i));
//                                    comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboRazonSocial.getItemAt(i)));
                                    
                                    break;
                                }
                            }
//                            a = labelIdProforma.getText();
//                            b = Integer.parseInt(a);
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
     comboBoxDirPar.setSelectedIndex(0);
     comboBoxMotivo.setSelectedIndex(0);
     txtNumComp.setText("FE0X-00000XXX");
     
     comboBoxTransportista.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           
            public void keyReleased(KeyEvent evt) {

                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxTransportista.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    limpiarComboTransportista();
                    try {
                        consultarTransportista(comboBoxTransportista.getItemAt(0));
                             
                        //aCargarContactosCombo(cadenaEscrita);
                    } catch (Exception ex) {
                        Logger.getLogger(Transportista.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (compararTrans(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarTransportista(cadenaEscrita);
                                                      
//                            a = labelIdProforma.getText();
//                            b = Integer.parseInt(a);
                        } catch (Exception ex) {
                            Logger.getLogger(Transportista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            for (int i = 0; i < comboBoxTransportista.getModel().getSize(); i++) {
                                if (comboBoxTransportista.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarTransportista(comboBoxTransportista.getItemAt(i));
//                                    comboBoxContactoCliente.setModel(proformacontroler.ListarCombodeContacto(comboRazonSocial.getItemAt(i)));
                                    
                                    break;
                                }
                            }
//                            a = labelIdProforma.getText();
//                            b = Integer.parseInt(a);
                        } catch (Exception ex) {
                            Logger.getLogger(Transportista.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxTransportista.setModel(transportistacontroler.TransportistaListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Transportista.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxTransportista.getItemCount() > 0) {
                        comboBoxTransportista.getEditor().setItem(cadenaEscrita);
                        comboBoxTransportista.showPopup();
                    } else {
                       comboBoxTransportista.addItem(cadenaEscrita);
                    }
                }
            }
        });
     
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        buttonBuscar = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        buttonLimpiar = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonCerrar = new javax.swing.JButton();
        jLabel18 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        txtFechEmi = new javax.swing.JTextField();
        txtFechTras = new javax.swing.JTextField();
        comboBoxRazSocial = new javax.swing.JComboBox<>();
        txtRuc = new javax.swing.JTextField();
        txtTipoDoc = new javax.swing.JTextField();
        txtMarcaVeh = new javax.swing.JTextField();
        txtCertVeh = new javax.swing.JTextField();
        txtLicVeh = new javax.swing.JTextField();
        txtNomTrans = new javax.swing.JTextField();
        txtRucTrans = new javax.swing.JTextField();
        txtTipoComp = new javax.swing.JTextField();
        txtNumComp = new javax.swing.JTextField();
        buttonNuevo = new javax.swing.JButton();
        buttonRegistrar = new javax.swing.JButton();
        buttonModificar = new javax.swing.JButton();
        buttonGuardar = new javax.swing.JButton();
        txtConclusion = new javax.swing.JTextField();
        buttonAgregar = new javax.swing.JButton();
        buttonEliminar = new javax.swing.JButton();
        txtNumCorr = new javax.swing.JTextField();
        comboBoxDirPar = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        comboBoxMotivo = new javax.swing.JComboBox<>();
        buttonVistaPdf = new javax.swing.JButton();
        buttonImprimirGuia = new javax.swing.JButton();
        buttonAgregarDesdeCoti = new javax.swing.JButton();
        buttonSiguiente = new javax.swing.JButton();
        buttonAnterior = new javax.swing.JButton();
        jLabel24 = new javax.swing.JLabel();
        txtAnio = new javax.swing.JTextField();
        txtDirLle = new javax.swing.JTextField();
        buttonAgregarDireccion = new javax.swing.JButton();
        comboBoxNumSerie = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        txtNumPedido = new javax.swing.JTextField();
        buttonTransportista = new javax.swing.JButton();
        comboBoxTransportista = new javax.swing.JComboBox<>();

        setClosable(true);
        setTitle("Guia de Remisión ");

        jLabel1.setText("Num. Serie:");

        jLabel2.setText("-");

        jLabel3.setText("Num. Correlativo:");

        buttonBuscar.setText("Buscar");
        buttonBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonBuscarActionPerformed(evt);
            }
        });

        jLabel4.setText("GUIA DE REMISION");

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        jLabel5.setText("Fecha Emision: ");

        jLabel6.setText("Fecha Traslado: ");

        jLabel7.setText("Direccion Partida:");

        jLabel9.setText("Razon Social:");

        jLabel10.setText("R.U.C.:");

        jLabel11.setText("Tipo y N° de Documento de identidad:");

        jLabel12.setText("Conclusión:");

        jLabel13.setText("VEHICULO");

        jLabel14.setText("Marca y Placa:");

        jLabel16.setText("Certificado Inscripcion: ");

        jLabel17.setText("Licencia de Conducir:");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idDetalleGuia", "CANT.", "CODIGO", "DESCRIPCION", "UNIDAD DE MEDIDA"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
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
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jTable1MouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(20);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(50);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(90);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(495);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        jLabel18.setText("Nombre Transportista:");

        jLabel19.setText("R.U.C.:");

        jLabel20.setText("Tipo Comprobante:");

        jLabel21.setText("Número Comprobante");

        jLabel23.setText("COMPROBANTE DE PAGO");

        comboBoxRazSocial.setEditable(true);

        buttonNuevo.setText("Nuevo");
        buttonNuevo.setToolTipText("Nuevo Registro");
        buttonNuevo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoActionPerformed(evt);
            }
        });

        buttonRegistrar.setText("Registrar");
        buttonRegistrar.setToolTipText("Registrar la Guia , pero antes verificar que la serie y el numero de guía estén digitados.");
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

        buttonAgregar.setText("Agregar Detalle");
        buttonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarActionPerformed(evt);
            }
        });

        buttonEliminar.setText("Eliminar");
        buttonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarActionPerformed(evt);
            }
        });

        comboBoxDirPar.setEditable(true);
        comboBoxDirPar.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "MZA. C LOTE. 32 APV. HUERTOS DE TUNGASUCA LIMA - LIMA - COMAS", "MZA. D LOTE. 19 ASOC.VIV.SUIZA PERUANA LIMA - LIMA - LOS OLIVOS" }));
        comboBoxDirPar.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxDirParItemStateChanged(evt);
            }
        });

        jLabel15.setText("TIPO");

        comboBoxMotivo.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxMotivoItemStateChanged(evt);
            }
        });

        buttonVistaPdf.setText("VistaGuia");
        buttonVistaPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVistaPdfActionPerformed(evt);
            }
        });

        buttonImprimirGuia.setText("ImprimirGuia");
        buttonImprimirGuia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirGuiaActionPerformed(evt);
            }
        });

        buttonAgregarDesdeCoti.setText("Agregar desde Pedido");
        buttonAgregarDesdeCoti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarDesdeCotiActionPerformed(evt);
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

        jLabel24.setText("Año:");

        buttonAgregarDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonAgregarDireccion.setText("Dirección LLegada:");
        buttonAgregarDireccion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarDireccionActionPerformed(evt);
            }
        });

        comboBoxNumSerie.setEditable(true);
        comboBoxNumSerie.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { ".", "0001", "0002" }));

        jLabel26.setText("Número Pedido");

        buttonTransportista.setText("TRANSPORTISTA");
        buttonTransportista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonTransportistaActionPerformed(evt);
            }
        });

        comboBoxTransportista.setEditable(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel23)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(txtTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel21)
                                        .addComponent(jLabel20)
                                        .addComponent(jLabel26))
                                    .addGap(18, 18, 18)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtTipoComp)
                                        .addComponent(txtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel7)
                                        .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel6))
                                .addGap(74, 74, 74)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtFechTras, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtFechEmi, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxDirPar, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboBoxRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addGap(10, 10, 10)
                                        .addComponent(txtConclusion))
                                    .addComponent(comboBoxMotivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(71, 71, 71)
                                                .addComponent(jLabel19)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtRucTrans))
                                            .addComponent(jLabel13)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel18)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtNomTrans, javax.swing.GroupLayout.PREFERRED_SIZE, 203, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(jLabel15)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(buttonTransportista)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(comboBoxTransportista, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(buttonGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonRegistrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonNuevo, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(buttonModificar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(buttonEliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(buttonAnterior)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonSiguiente)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonCerrar, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonAgregarDireccion)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(361, 361, 361)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel16)
                                                    .addComponent(jLabel14))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel17)
                                                .addGap(22, 22, 22)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addComponent(txtCertVeh, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 192, Short.MAX_VALUE)
                                            .addComponent(txtMarcaVeh, javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(txtLicVeh)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtDirLle, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(493, 493, 493)
                                .addComponent(buttonAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonAgregarDesdeCoti, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel24)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumCorr, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonBuscar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonLimpiar))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonVistaPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(buttonImprimirGuia, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(buttonBuscar)
                    .addComponent(jLabel4)
                    .addComponent(buttonLimpiar)
                    .addComponent(txtNumCorr, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel24)
                    .addComponent(txtAnio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxNumSerie, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtFechEmi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(txtFechTras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboBoxMotivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonNuevo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboBoxDirPar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txtConclusion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonRegistrar)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(txtRuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAgregarDireccion)
                            .addComponent(txtDirLle, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel11)
                            .addComponent(txtTipoDoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel20)
                            .addComponent(txtTipoComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(txtNumComp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(11, 11, 11)
                                .addComponent(buttonModificar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonGuardar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(buttonEliminar))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(comboBoxRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonTransportista)
                                    .addComponent(comboBoxTransportista, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(1, 1, 1)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel18)
                                    .addComponent(txtNomTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel19)
                                    .addComponent(txtRucTrans, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel13)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel14)
                            .addComponent(txtMarcaVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(txtCertVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel17)
                            .addComponent(txtLicVeh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(1, 1, 1)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel26)
                    .addComponent(txtNumPedido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonAgregar)
                    .addComponent(buttonAgregarDesdeCoti))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(buttonVistaPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonImprimirGuia))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buttonAnterior)
                            .addComponent(buttonSiguiente)
                            .addComponent(buttonCerrar))))
                .addContainerGap())
        );

        buttonAgregarDesdeCoti.getAccessibleContext().setAccessibleDescription("");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonBuscarActionPerformed
        int numa = Integer.parseInt(txtNumCorr.getText());
        String numi = String.format("%06d", numa);
        if (".".equals(comboBoxNumSerie.getEditor().getItem().toString())) {
            JOptionPane.showMessageDialog(null, "Verificar el Número de Serie sea correcto");
        } else {
            try {
                consultarGuiaRemision(comboBoxNumSerie.getEditor().getItem().toString(), numi, Integer.parseInt(txtAnio.getText()));
            } catch (Exception ex) {
                Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleGuiaDeRemision();
            habilitarBuscar();
        }
    }//GEN-LAST:event_buttonBuscarActionPerformed

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarActionPerformed
        DetallesGuiaRemision detGuia = new DetallesGuiaRemision();
        Principal.jDesktopPane1.add(detGuia);
        detGuia.toFront();
        detGuia.setVisible(true);
    }//GEN-LAST:event_buttonAgregarActionPerformed

    private void buttonRegistrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarActionPerformed
        String cadenaDondeBuscar = txtNumComp.getText();
        String loQueQuieroBuscar = "X";
        boolean si = false;
        String[] palabras = loQueQuieroBuscar.split("\\W+");
        for (String palabra : palabras) {
            if (cadenaDondeBuscar.contains(palabra)) {
                si = true;
            } else {
                si = false;
            }
        }
       
        if (idCliente == 0) {
            JOptionPane.showMessageDialog(null, "Llenar el campo de Cliente(Dar enter)");
        } else if ("".equals(txtDirLle.getText())) {
            JOptionPane.showMessageDialog(null, "Llenar el campo de Dirección del Cliente");
        } else if (si == true) {
            JOptionPane.showMessageDialog(null, "Llenar correctamente el campo Número de Comprobante(No debe contener \"X\")");
        } else if (".".equals(comboBoxNumSerie.getEditor().getItem().toString())) {
            JOptionPane.showMessageDialog(null, "Verificar el Número de Serie sea correcto");
        } else if(comboBoxMotivo.getSelectedIndex()==0){
            JOptionPane.showMessageDialog(null, "Debe elegir el motivo de traslado");
        }else {
            try {
                txtNumCorr.setText(String.format("%06d", GenerarCodigo( comboBoxNumSerie.getEditor().getItem().toString())));
            } catch (Exception ex) {
                Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
            }
            procesar(1);
            try {
                consultarGuiaRemision(comboBoxNumSerie.getEditor().getItem().toString(), txtNumCorr.getText(), Integer.parseInt(txtAnio.getText()));
            } catch (Exception ex) {
                Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
            }
            habilitarRegistrar();
        }

    }//GEN-LAST:event_buttonRegistrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        procesar(2);
        habilitarGuardar();
        try {
            consultarGuiaRemision(comboBoxNumSerie.getEditor().getItem().toString(), txtNumCorr.getText(), Integer.parseInt(txtAnio.getText()));
            listaDetalleGuiaDeRemision();
        } catch (Exception ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonGuardarActionPerformed

    private void buttonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarActionPerformed
        procesar(3);
        limpiar();
        habilitarEliminar();
    }//GEN-LAST:event_buttonEliminarActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
        listaDetalleGuiaDeRemision();
        habilitarInicio();      
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonNuevoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoActionPerformed
        limpiar();
        habilitarNuevo();
    }//GEN-LAST:event_buttonNuevoActionPerformed

    private void buttonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarActionPerformed
        habilitarModificar();
    }//GEN-LAST:event_buttonModificarActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tabla = jTable1.getSelectedRow();
        idDetalleGuia=jTable1.getValueAt(tabla, 0).toString();
        descrip=jTable1.getValueAt(tabla, 3).toString();
        idDetGuia1=Integer.parseInt(idDetalleGuia);
        DetallesGuiaRemision detGuia = new DetallesGuiaRemision();
        Principal.jDesktopPane1.add(detGuia);
        detGuia.toFront();
        detGuia.setVisible(true);
        
        try {
            DetallesGuiaRemision.consultar(idDetGuia1);
        } catch (Exception ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        
//        try {
//            DetallesGuiaRemision.consultar(idGuiaRem);
//        } catch (Exception ex) {
//            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
        DetallesGuiaRemision.habilitarTabla();        
    }//GEN-LAST:event_jTable1MouseClicked

    private void comboBoxDirParItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxDirParItemStateChanged
    
    }//GEN-LAST:event_comboBoxDirParItemStateChanged

    private void comboBoxMotivoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxMotivoItemStateChanged
        if (comboBoxMotivo.getSelectedIndex() != 0 && comboBoxMotivo.getSelectedIndex() != 13  ) {
            tipo = comboBoxMotivo.getSelectedItem().toString();
            System.out.println(tipo);
            try {
                consultarMotivo(tipo);
            } catch (Exception ex) {
                Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else if (comboBoxMotivo.getSelectedIndex()==13){
            tipo = comboBoxMotivo.getSelectedItem().toString();
            System.out.println(tipo);
            try {
                consultarMotivo(tipo);
            } catch (Exception ex) {
                Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
            }
            txtConclusion.setText("PIEZA TERMINADA");
        }else{
        
        }
    }//GEN-LAST:event_comboBoxMotivoItemStateChanged

    private void buttonVistaPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVistaPdfActionPerformed
         GenerarReporte guiaRep= new GenerarReporte();
        try {
            guiaRep.ReporteGuia(idGuiaRem);
        } catch (SQLException ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonVistaPdfActionPerformed

    private void buttonImprimirGuiaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirGuiaActionPerformed
       GenerarReporte guiaRep= new GenerarReporte();
        try {
            guiaRep.ImprimirGuia(idGuiaRem);
        } catch (SQLException ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        procesarUpdateEstado(2);
        
    }//GEN-LAST:event_buttonImprimirGuiaActionPerformed

    private void buttonAgregarDesdeCotiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarDesdeCotiActionPerformed
        PedidosAGuiaRemision pedGuia = new PedidosAGuiaRemision();
        Principal.jDesktopPane1.add(pedGuia);
        pedGuia.toFront();
        pedGuia.setVisible(true);
    }//GEN-LAST:event_buttonAgregarDesdeCotiActionPerformed

    private void jTable1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseEntered

    private void buttonSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSiguienteActionPerformed
        numeroGuia = String.format("%06d", (Integer.parseInt(txtNumCorr.getText()) + 1));
        System.out.println(numeroGuia);
        String nums = comboBoxNumSerie.getEditor().getItem().toString();
        int aio = Integer.parseInt(txtAnio.getText());
        limpiar();

        try {
            consultarGuiaRemision(nums, numeroGuia, aio);
        } catch (Exception ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleGuiaDeRemision();
        habilitarBuscar();

        if (mensa == true) {
            numeroGuia = String.format("%06d", (Integer.parseInt(numeroGuia) - 1));
            try {
                consultarGuiaRemision(nums, numeroGuia, aio);
            } catch (Exception ex) {
                Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
            }
            listaDetalleGuiaDeRemision();
            habilitarBuscar();
        }
        mensa = false;
    }//GEN-LAST:event_buttonSiguienteActionPerformed

    private void buttonAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAnteriorActionPerformed
         numeroGuia = String.format("%06d", (Integer.parseInt(txtNumCorr.getText()) -1));
         String nums=comboBoxNumSerie.getEditor().getItem().toString();
         int aio=Integer.parseInt(txtAnio.getText());
    limpiar();
    
    try {
            consultarGuiaRemision(nums,numeroGuia,aio);
        } catch (Exception ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleGuiaDeRemision();
        habilitarBuscar();
        
       if (mensa == true) {
       numeroGuia = String.format("%06d", (Integer.parseInt(numeroGuia)+1));
      
        try {
            consultarGuiaRemision(nums,numeroGuia,aio);
        } catch (Exception ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        listaDetalleGuiaDeRemision();
        habilitarBuscar();
       }
       mensa=false;
    }//GEN-LAST:event_buttonAnteriorActionPerformed

    private void buttonAgregarDireccionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarDireccionActionPerformed
       if(idCliente!=0){
        DireccionAGuiaRemision dirGuia = null;
        try {
            dirGuia = new DireccionAGuiaRemision();
        } catch (Exception ex) {
            Logger.getLogger(GuiaRemision.class.getName()).log(Level.SEVERE, null, ex);
        }
        Principal.jDesktopPane1.add(dirGuia);
        dirGuia.toFront();
        dirGuia.setVisible(true);
       }else {
       
           JOptionPane.showMessageDialog(null, "Llenar el campo de Cliente (Dar Enter)");
       
       }
    }//GEN-LAST:event_buttonAgregarDireccionActionPerformed

    private void buttonTransportistaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonTransportistaActionPerformed
        TransportistaRegistro transRe = new TransportistaRegistro();
        Principal.jDesktopPane1.add(transRe);
        transRe.toFront();
        transRe.setVisible(true);
    }//GEN-LAST:event_buttonTransportistaActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonAgregar;
    private javax.swing.JButton buttonAgregarDesdeCoti;
    private javax.swing.JButton buttonAgregarDireccion;
    private javax.swing.JButton buttonAnterior;
    private javax.swing.JButton buttonBuscar;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonEliminar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JButton buttonImprimirGuia;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonModificar;
    private javax.swing.JButton buttonNuevo;
    private javax.swing.JButton buttonRegistrar;
    private javax.swing.JButton buttonSiguiente;
    private javax.swing.JButton buttonTransportista;
    private javax.swing.JButton buttonVistaPdf;
    private javax.swing.JComboBox<String> comboBoxDirPar;
    private javax.swing.JComboBox<String> comboBoxMotivo;
    public static javax.swing.JComboBox<String> comboBoxNumSerie;
    private javax.swing.JComboBox<String> comboBoxRazSocial;
    private javax.swing.JComboBox<String> comboBoxTransportista;
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
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextField txtAnio;
    private javax.swing.JTextField txtCertVeh;
    private javax.swing.JTextField txtConclusion;
    public static javax.swing.JTextField txtDirLle;
    private javax.swing.JTextField txtFechEmi;
    private javax.swing.JTextField txtFechTras;
    private javax.swing.JTextField txtLicVeh;
    private javax.swing.JTextField txtMarcaVeh;
    private javax.swing.JTextField txtNomTrans;
    private javax.swing.JTextField txtNumComp;
    public static javax.swing.JTextField txtNumCorr;
    public static javax.swing.JTextField txtNumPedido;
    private javax.swing.JTextField txtRuc;
    private javax.swing.JTextField txtRucTrans;
    private javax.swing.JTextField txtTipoComp;
    private javax.swing.JTextField txtTipoDoc;
    // End of variables declaration//GEN-END:variables

ClienteController clientecontroler= new ClienteController();
Cliente cliente;

GuiaDeRemisionController guiacontroler=new GuiaDeRemisionController();
GuiaDeRemision guia;

static DetalleGuiaDeRemisionController detGuiaController = new DetalleGuiaDeRemisionController();
static DetalleGuiaDeRemision detGuia;


//------------------------------ RazonSocial ---------------------------------------------------

    private void consultarComboCliente(String razonsocial) throws Exception {
        cliente = clientecontroler.ClienteBuscar(razonsocial);
        if (cliente != null) {
            //CREAR ID CLIENTE Y ASIGNARLE EL ID CLIENTE.
            idCliente = cliente.getIdCliente();
            comboBoxRazSocial.setSelectedItem(cliente.getRazonSocial());
            txtRuc.setText(cliente.getRuc());
           // txtDirLle.setText(cliente.getDireccion());
            
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no esta registrado");
            //System.out.println("Error");
        }
    }
    
    public void limpiarComboCliente() {
        comboBoxRazSocial.setSelectedItem("");
        txtRuc.setText("");
        txtDirLle.setText("");
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
 
    
 //-------------------------------- ComboBox Transportista ---------------------
    
    public void limpiarComboTransportista() {
        comboBoxTransportista.setSelectedItem("");
        txtNomTrans.setText("");
        txtRucTrans.setText("");
        txtMarcaVeh.setText("");
        txtCertVeh.setText("");
        txtLicVeh.setText("");
    }
    
    private boolean compararTrans(String cadena) {
        Object[] lista = comboBoxTransportista.getComponents();
        boolean encontrado = false;
        for (Object object : lista) {
            if (cadena.equals(object)) {
                encontrado = true;
                break;
            }
        }
        return encontrado;
    }
    
//------------------------------------ Consultar -------------------------------
private void consultarGuiaRemision(int idguia) throws Exception {
        guia= guiacontroler.GuiaDeRemisionBuscar(idguia);
        if (guia != null) {

            idGuiaRem = guia.getIdGuiaRemi();
            System.out.println(idGuiaRem);
            
            String testValue1 = guia.getSerieGuia();
            for (int i = 0; i < comboBoxNumSerie.getModel().getSize(); i++) {
                if (comboBoxNumSerie.getItemAt(i).equals(testValue1)) {
                    System.out.println(i);
                    comboBoxNumSerie.setSelectedIndex(i);
                    break;
                }
            }
            serieGuia=guia.getSerieGuia();
            txtNumCorr.setText(guia.getNumGuia());
            numGuia=guia.getNumGuia();
            txtFechEmi.setText(guia.getFechEmi());
            txtFechTras.setText(guia.getFechaTraslado());
            dir = guia.getDirecPart();
            System.out.println("dirPart" + dir);
            String testValue = dir;
            for (int i = 0; i < comboBoxDirPar.getModel().getSize(); i++) {
                if (comboBoxDirPar.getItemAt(i).equals(testValue)) {
                    System.out.println(i);
                    comboBoxDirPar.setSelectedIndex(i);
                    break;
                }
            }
            txtDirLle.setText(guia.getDirecLleg());
            idCliente=guia.getIdCliente();
            consultarClientePorId(idCliente);
            txtTipoDoc.setText(guia.getTipoDocCli());
            
            conclu=guia.getMotivoDescripcion();
            idMotivo=guia.getIdMotivoGuia();
            consultarMotivo(idMotivo);
            String testValueM = tipo;
            for (int i = 0; i < comboBoxMotivo.getModel().getSize(); i++) {
                if (comboBoxMotivo.getItemAt(i).equals(testValueM)) {
                    System.out.println(i);
                    comboBoxMotivo.setSelectedIndex(i);
                    txtConclusion.setText(guia.getMotivoDescripcion());
                    break;
                }
            }
            txtConclusion.setText(guia.getMotivoDescripcion());
            txtNomTrans.setText(guia.getNomTransp());
            txtRucTrans.setText(guia.getRucTransp());
            txtTipoComp.setText(guia.getTipoComprobante());
            txtNumComp.setText(guia.getNumComprobante());
            txtMarcaVeh.setText(guia.getVehMarca());
            txtCertVeh.setText(guia.getVehCertif());
            txtLicVeh.setText(guia.getVehLic());
            estado=guia.getEstado();
            observacion=guia.getObservacion();
            txtNumPedido.setText(guia.getNumPedi());

        } else {
            JOptionPane.showMessageDialog(null, "Guia de Remision no registrada");
                     //System.out.println("Error");
                     mensa=true;
        }
    }

private void consultarGuiaRemision(String serie, String num, int anio) throws Exception {
        guia= guiacontroler.GuiaDeRemisionBuscar(serie, num, anio);
        if (guia != null) {

            idGuiaRem = guia.getIdGuiaRemi();
            System.out.println(idGuiaRem);
            String testValue1 = guia.getSerieGuia();
            for (int i = 0; i < comboBoxNumSerie.getModel().getSize(); i++) {
                if (comboBoxNumSerie.getItemAt(i).equals(testValue1)) {
                    System.out.println(i);
                    comboBoxNumSerie.setSelectedIndex(i);
                    break;
                }
            }
            serieGuia=guia.getSerieGuia();
            txtNumCorr.setText(guia.getNumGuia());
            numGuia=guia.getNumGuia();
            txtFechEmi.setText(guia.getFechEmi());
            txtFechTras.setText(guia.getFechaTraslado());
            dir = guia.getDirecPart();
            System.out.println("dirPart" + dir);
            comboBoxDirPar.setSelectedItem(guia.getDirecPart());
            txtDirLle.setText(guia.getDirecLleg());
            idCliente=guia.getIdCliente();
            consultarClientePorId(idCliente);
            txtTipoDoc.setText(guia.getTipoDocCli());
            conclu=guia.getMotivoDescripcion();
            idMotivo=guia.getIdMotivoGuia();
            consultarMotivo(idMotivo);
            String testValueM = tipo;
            for (int i = 0; i < comboBoxMotivo.getModel().getSize(); i++) {
                if (comboBoxMotivo.getItemAt(i).equals(testValueM)) {
                    System.out.println(i);
                    comboBoxMotivo.setSelectedIndex(i);
                    txtConclusion.setText(guia.getMotivoDescripcion());
                    break;
                }
            }
            txtConclusion.setText(guia.getMotivoDescripcion());
            txtNomTrans.setText(guia.getNomTransp());
            txtRucTrans.setText(guia.getRucTransp());
            txtTipoComp.setText(guia.getTipoComprobante());
            txtNumComp.setText(guia.getNumComprobante());
            txtMarcaVeh.setText(guia.getVehMarca());
            txtCertVeh.setText(guia.getVehCertif());
            txtLicVeh.setText(guia.getVehLic());
            estado=guia.getEstado();
            observacion=guia.getObservacion();
            txtNumPedido.setText(guia.getNumPedi());

        } else {
            JOptionPane.showMessageDialog(null, "Guia de Remision no registrada");
                     //System.out.println("Error");
                     mensa=true;
        }
    }

 private void consultarClientePorId(int iddecliente) throws Exception {
        cliente = clientecontroler.ClienteBuscar1(iddecliente);
        if (cliente != null) {

            //txtRazonSocial.setText(pro.getRazonSocial());
            comboBoxRazSocial.setSelectedItem(cliente.getRazonSocial());
            txtRuc.setText(cliente.getRuc());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no registrado por ahora");
            //System.out.println("Error");
        }
 }

//------------------------------------- Procesar ----------------------------------
    private void procesar(int op) {
        guia= leerDatos();
        try {
            System.out.println("11111111111");
            String msg = guiacontroler.GuiaDeRemisionProcesar(guia, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
    private void consultarMotivo(String cg) throws Exception {
        motivoguia = motivoguiacontroler.MotivoGuiaBuscarCombox(cg);
        if (motivoguia != null) {

            idMotivo=motivoguia.getIdMotivoGuia();
            motivo=motivoguia.getMotivo();

        } else {
            JOptionPane.showMessageDialog(null, "Motivo no registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarMotivo(int im) throws Exception {
        motivoguia = motivoguiacontroler.MotivoGuiaBuscarId(im);
        if (motivoguia != null) {

            idMotivo=motivoguia.getIdMotivoGuia();
            motivo=motivoguia.getMotivo();
            tipo=motivoguia.getIdMotivoGuia()+".- "+motivoguia.getMotivo();

        } else {
            JOptionPane.showMessageDialog(null, "Motivo no registrado");
            //System.out.println("Error");
        }
    }
    
    
    private void procesarUpdateEstado(int op) {
        guia= leerDatosUpdate();
        try {
            System.out.println("11111111111");
            String msg = guiacontroler.GuiaDeRemisionProcesar(guia, op);
            JOptionPane.showMessageDialog(null, "El estado de la Guía es \"EMITIDO\"");
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
  //----------------------- Consultar Transportista ----------------------------
    
    private void consultarTransportista(String nombreTrans) throws Exception {
        transportista = transportistacontroler.TransportistaBuscarNombre(nombreTrans);
        if (transportista != null) {
            //CREAR ID CLIENTE Y ASIGNARLE EL ID CLIENTE.
            idTransportista = transportista.getIdTransportista();
            comboBoxTransportista.setSelectedItem(transportista.getNombre());
            txtNomTrans.setText(transportista.getNombre());
            txtRucTrans.setText(transportista.getRuc());
            txtMarcaVeh.setText(transportista.getMarcaYPlaca());
            txtCertVeh.setText(transportista.getCertificadoInscripcion());
            txtLicVeh.setText(transportista.getLicenciaConducir());

            // txtDirLle.setText(cliente.getDireccion());
        } else {
            JOptionPane.showMessageDialog(null, "Transportista no esta registrado");
            //System.out.println("Error");
        }
    }
    
 //------------------------------------- Leer Datos --------------------------------
    
private GuiaDeRemision leerDatos() {
        GuiaDeRemision guiaRem = new GuiaDeRemision();
        
        guiaRem.setIdGuiaRemi(idGuiaRem);
        guiaRem.setNumGuia(txtNumCorr.getText());
        numGuia=txtNumCorr.getText();
        guiaRem.setSerieGuia(comboBoxNumSerie.getEditor().getItem().toString());
        guiaRem.setFechEmi(txtFechEmi.getText());
        guiaRem.setFechaTraslado(txtFechTras.getText());
        guiaRem.setDirecPart(comboBoxDirPar.getEditor().getItem().toString());
        guiaRem.setDirecLleg(txtDirLle.getText());
        guiaRem.setIdCliente(idCliente);
        guiaRem.setTipoDocCli(txtTipoDoc.getText());
        guiaRem.setIdMotivoGuia(idMotivo);
        guiaRem.setMotivoDescripcion(txtConclusion.getText());
        guiaRem.setVehMarca(txtMarcaVeh.getText());
        guiaRem.setVehCertif(txtCertVeh.getText());
        guiaRem.setVehLic(txtLicVeh.getText());
        guiaRem.setNomTransp(txtNomTrans.getText());
        guiaRem.setRucTransp(txtRucTrans.getText());
        guiaRem.setTipoComprobante(txtTipoComp.getText());
        guiaRem.setNumComprobante(txtNumComp.getText());
        guiaRem.setEstado(estado);
        guiaRem.setObservacion(observacion);
        guiaRem.setNumPedi(txtNumPedido.getText());
        
        return guiaRem;
    }

private GuiaDeRemision leerDatosUpdate() {
        GuiaDeRemision guiaRem = new GuiaDeRemision();
        
        guiaRem.setIdGuiaRemi(idGuiaRem);
        guiaRem.setNumGuia(txtNumCorr.getText());
        numGuia=txtNumCorr.getText();
        guiaRem.setSerieGuia(comboBoxNumSerie.getEditor().getItem().toString());
        guiaRem.setFechEmi(txtFechEmi.getText());
        guiaRem.setFechaTraslado(txtFechTras.getText());
        guiaRem.setDirecPart(comboBoxDirPar.getEditor().getItem().toString());
        guiaRem.setDirecLleg(txtDirLle.getText());
        guiaRem.setIdCliente(idCliente);
        guiaRem.setTipoDocCli(txtTipoDoc.getText());
        guiaRem.setIdMotivoGuia(idMotivo);
        guiaRem.setMotivoDescripcion(txtConclusion.getText());
        guiaRem.setVehMarca(txtMarcaVeh.getText());
        guiaRem.setVehCertif(txtCertVeh.getText());
        guiaRem.setVehLic(txtLicVeh.getText());
        guiaRem.setNomTransp(txtNomTrans.getText());
        guiaRem.setRucTransp(txtRucTrans.getText());
        guiaRem.setTipoComprobante(txtTipoComp.getText());
        guiaRem.setNumComprobante(txtNumComp.getText());
        guiaRem.setEstado("EMITIDO");
        guiaRem.setObservacion(observacion);
        guiaRem.setNumPedi(txtNumPedido.getText());
        
        return guiaRem;
    }
//--------------------------------- Tabla ---------------------------------------
static void listaDetalleGuiaDeRemision() {
        List<DetalleGuiaDeRemision> lista;
        try {
            lista = detGuiaController.DetalleGuiaDeRemisionFiltrar(idGuiaRem);
            verDetalleGuiaDeRemision(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    static private void verDetalleGuiaDeRemision(List<DetalleGuiaDeRemision> lista) {
        
        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (DetalleGuiaDeRemision detGuia : lista) {
           
           
                Object[] fila = {detGuia.getIdDetalleGuia(),detGuia.getCant(), detGuia.getCod(), detGuia.getDescrip(), detGuia.getUniMed()};
                tabla.addRow(fila);
            }
        
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

//------------------------------------ Limpiar -------------------------------
void limpiar() {
        idGuiaRem=0;
        idCliente=0;
        listaDetalleGuiaDeRemision();
        comboBoxNumSerie.setSelectedIndex(0);
        txtNumCorr.setText("");
        txtFechEmi.setText(fechaActual());
        txtFechTras.setText(fechaActual());
        comboBoxDirPar.setSelectedIndex(0);
        txtDirLle.setText("");
        comboBoxRazSocial.setSelectedItem("");
        txtRuc.setText("");
        txtTipoDoc.setText("");
        txtConclusion.setText("");
        txtNomTrans.setText("");
        txtRucTrans.setText("");
        txtTipoComp.setText("FACTURA");
        txtNumComp.setText("FE0X-00000XXX");
        comboBoxMotivo.setSelectedIndex(0);
        txtMarcaVeh.setText("");
        txtCertVeh.setText("");
        txtLicVeh.setText("");
        txtNumPedido.setText("");
        comboBoxTransportista.setSelectedItem("");
    }
//------------------------------------- Habilitar ------------------------

    void habilitarInicio() {

        buttonBuscar.setEnabled(true);
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonAgregar.setEnabled(false);
        buttonAgregarDesdeCoti.setEnabled(false);
        buttonVistaPdf.setEnabled(false);
        buttonImprimirGuia.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        comboBoxNumSerie.setEnabled(true);
        txtNumCorr.setEnabled(true);
        txtFechEmi.setEnabled(false);
        txtFechTras.setEnabled(false);
        comboBoxDirPar.setEnabled(false);
        comboBoxMotivo.setEnabled(false);
        txtDirLle.setEnabled(false);
        buttonAgregarDireccion.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtTipoDoc.setEnabled(false);
        txtConclusion.setEnabled(false);
        txtNomTrans.setEnabled(false);
        txtRucTrans.setEnabled(false);
        txtTipoComp.setEnabled(false);
        txtNumComp.setEnabled(false);
        txtMarcaVeh.setEnabled(false);
        txtCertVeh.setEnabled(false);
        txtLicVeh.setEnabled(false);
        txtNumPedido.setEnabled(false);
        comboBoxTransportista.setEnabled(false);
        buttonTransportista.setEnabled(false);
    }

    void habilitarNuevo() {

        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(true);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonAgregar.setEnabled(false);
        buttonAgregarDesdeCoti.setEnabled(false);
        buttonVistaPdf.setEnabled(false);
        buttonImprimirGuia.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        comboBoxNumSerie.setEnabled(true);
        txtNumCorr.setEnabled(true);
        txtFechEmi.setEnabled(true);
        txtFechTras.setEnabled(true);
        comboBoxDirPar.setEnabled(true);
        comboBoxMotivo.setEnabled(true);
        txtDirLle.setEnabled(true);
        buttonAgregarDireccion.setEnabled(true);
        comboBoxRazSocial.setEnabled(true);
        txtRuc.setEnabled(false);
        txtTipoDoc.setEnabled(true);
        txtConclusion.setEnabled(true);
        txtNomTrans.setEnabled(true);
        txtRucTrans.setEnabled(true);
        txtTipoComp.setEnabled(true);
        txtNumComp.setEnabled(true);
        txtMarcaVeh.setEnabled(true);
        txtCertVeh.setEnabled(true);
        txtLicVeh.setEnabled(true);
        txtNumPedido.setEnabled(true);
        comboBoxTransportista.setEnabled(true);
        buttonTransportista.setEnabled(true);
    }

    void habilitarBuscar() {

        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonAgregar.setEnabled(true);
        buttonAgregarDesdeCoti.setEnabled(true);
        buttonVistaPdf.setEnabled(true);
        buttonImprimirGuia.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);

        comboBoxNumSerie.setEnabled(false);
        txtNumCorr.setEnabled(false);
        txtFechEmi.setEnabled(false);
        txtFechTras.setEnabled(false);
        comboBoxDirPar.setEnabled(false);
        txtDirLle.setEnabled(false);
        buttonAgregarDireccion.setEnabled(false);
        comboBoxMotivo.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtTipoDoc.setEnabled(false);
        txtConclusion.setEnabled(false);
        txtNomTrans.setEnabled(false);
        txtRucTrans.setEnabled(false);
        txtTipoComp.setEnabled(false);
        txtNumComp.setEnabled(false);
        txtMarcaVeh.setEnabled(false);
        txtCertVeh.setEnabled(false);
        txtLicVeh.setEnabled(false);
        txtNumPedido.setEnabled(false);
        comboBoxTransportista.setEnabled(false);
        buttonTransportista.setEnabled(false);
    }
    
void habilitarRegistrar() {

        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonAgregar.setEnabled(true);
        buttonAgregarDesdeCoti.setEnabled(true);
        buttonVistaPdf.setEnabled(true);
        buttonImprimirGuia.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);
        
        comboBoxNumSerie.setEnabled(false);
        txtNumCorr.setEnabled(false);
        txtFechEmi.setEnabled(false);
        txtFechTras.setEnabled(false);
        comboBoxDirPar.setEnabled(false);
        comboBoxMotivo.setEnabled(false);
        txtDirLle.setEnabled(false);
        buttonAgregarDireccion.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtTipoDoc.setEnabled(false);
        txtConclusion.setEnabled(false);
        txtNomTrans.setEnabled(false);
        txtRucTrans.setEnabled(false);
        txtTipoComp.setEnabled(false);
        txtNumComp.setEnabled(false);
        txtMarcaVeh.setEnabled(false);
        txtCertVeh.setEnabled(false);
        txtLicVeh.setEnabled(false);
        txtNumPedido.setEnabled(false);
        comboBoxTransportista.setEnabled(false);
        buttonTransportista.setEnabled(false);
    }

void habilitarModificar() {

        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(false);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(true);
        buttonEliminar.setEnabled(false);
        buttonAgregar.setEnabled(false);
        buttonAgregarDesdeCoti.setEnabled(false);
        buttonVistaPdf.setEnabled(false);
        buttonImprimirGuia.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        comboBoxNumSerie.setEnabled(true);
        txtNumCorr.setEnabled(true);
        txtFechEmi.setEnabled(true);
        txtFechTras.setEnabled(true);
        comboBoxDirPar.setEnabled(true);
        comboBoxMotivo.setEnabled(true);
        txtDirLle.setEnabled(true);
        buttonAgregarDireccion.setEnabled(true);
        comboBoxRazSocial.setEnabled(true);
        txtRuc.setEnabled(true);
        txtTipoDoc.setEnabled(true);
        txtConclusion.setEnabled(true);
        txtNomTrans.setEnabled(true);
        txtRucTrans.setEnabled(true);
        txtTipoComp.setEnabled(true);
        txtNumComp.setEnabled(true);
        txtMarcaVeh.setEnabled(true);
        txtCertVeh.setEnabled(true);
        txtLicVeh.setEnabled(true);
        txtNumPedido.setEnabled(true);
        comboBoxTransportista.setEnabled(true);
        buttonTransportista.setEnabled(true);
    }

void habilitarGuardar() {

        buttonBuscar.setEnabled(false);
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(false);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(true);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(true);
        buttonAgregar.setEnabled(true);
        buttonAgregarDesdeCoti.setEnabled(true);
        buttonVistaPdf.setEnabled(true);
        buttonImprimirGuia.setEnabled(true);
        buttonSiguiente.setEnabled(true);
        buttonAnterior.setEnabled(true);

        comboBoxNumSerie.setEnabled(false);
        txtNumCorr.setEnabled(false);
        txtFechEmi.setEnabled(false);
        txtFechTras.setEnabled(false);
        comboBoxDirPar.setEnabled(false);
        comboBoxMotivo.setEnabled(false);
        txtDirLle.setEnabled(false);
        buttonAgregarDireccion.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtTipoDoc.setEnabled(false);
        txtConclusion.setEnabled(false);
        txtNomTrans.setEnabled(false);
        txtRucTrans.setEnabled(false);
        txtTipoComp.setEnabled(false);
        txtNumComp.setEnabled(false);
        txtMarcaVeh.setEnabled(false);
        txtCertVeh.setEnabled(false);
        txtLicVeh.setEnabled(false);
        txtNumPedido.setEnabled(false);
        comboBoxTransportista.setEnabled(false);
        buttonTransportista.setEnabled(false);
    }

void habilitarEliminar() {

        buttonBuscar.setEnabled(true);
        buttonLimpiar.setEnabled(true);
        buttonNuevo.setEnabled(true);
        buttonRegistrar.setEnabled(false);
        buttonModificar.setEnabled(false);
        buttonGuardar.setEnabled(false);
        buttonEliminar.setEnabled(false);
        buttonAgregar.setEnabled(false);
        buttonAgregarDesdeCoti.setEnabled(false);
        buttonVistaPdf.setEnabled(false);
        buttonImprimirGuia.setEnabled(false);
        buttonSiguiente.setEnabled(false);
        buttonAnterior.setEnabled(false);

        comboBoxNumSerie.setEnabled(false);
        txtNumCorr.setEnabled(true);
        txtFechEmi.setEnabled(false);
        txtFechTras.setEnabled(false);
        comboBoxDirPar.setEnabled(false);
        comboBoxMotivo.setEnabled(false);
        txtDirLle.setEnabled(false);
        buttonAgregarDireccion.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        txtRuc.setEnabled(false);
        txtTipoDoc.setEnabled(false);
        txtConclusion.setEnabled(false);
        txtNomTrans.setEnabled(false);
        txtRucTrans.setEnabled(false);
        txtTipoComp.setEnabled(false);
        txtNumComp.setEnabled(false);
        txtMarcaVeh.setEnabled(false);
        txtCertVeh.setEnabled(false);
        txtLicVeh.setEnabled(false);
        txtNumPedido.setEnabled(false);
        comboBoxTransportista.setEnabled(false);
        buttonTransportista.setEnabled(false);
    }

//----------------- metodo que jala desde Guia Remisión Vista  -------------
    static void pasarGuiaRemision() {
        txtAnio.setText(Integer.toString(GuiaRemisionVista.anio));
        String testValuex= GuiaRemisionVista.serie;
            for (int i = 0; i < comboBoxNumSerie.getModel().getSize(); i++) {
                if (comboBoxNumSerie.getItemAt(i).equals(testValuex)) {
                    System.out.println(i);
                    comboBoxNumSerie.setSelectedIndex(i);
                    break;
                }
            }
        txtNumCorr.setText(GuiaRemisionVista.num);
              
    }
    
 // ------------------- metodo que jala la direccion del cliente  ---------------------
    static void direccionAGuia(){
    
        txtDirLle.setText(DireccionAGuiaRemision.direc);
    
    }
    
 //-------------consultas para generar codigo -------------------    

    int GenerarCodigo(String nums) throws Exception {
        String nug;
        guia = guiacontroler.GuiaDeRemisionBuscarUltimoId( nums);
        if (guia != null) {
            nug = guia.getNumGuia();
            generarNum = Integer.parseInt(nug);
            System.out.println("ultimoNum : " + generarNum);
            generarNum = generarNum + 1;
            if ("".equals(guia.getNumGuia())) {
                generarNum = 1;
            }
        } else {
            generarNum = 1;
            System.out.println("ultimoNum : " + generarNum);
        }
        return generarNum;
       
    }

}

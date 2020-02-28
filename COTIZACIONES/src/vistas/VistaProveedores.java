/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ArticuloProveedorController;
import controller.ContactoProveedorController;
import controller.DireccionAnexaProvController;
import controller.ProveedorArticuloController;
import controller.ProveedorController;
import controller.ProveedorRubroController;
import controller.RubroController;
import controller.SucursalController;
import entity.ArticuloProveedor;
import entity.ContactoProveedor;
import entity.DireccionAnexaProv;
import entity.Proveedor;
import entity.ProveedorArticulo;
import entity.ProveedorRubro;
import entity.Rubro;
import entity.Sucursal;
import exportarexcel.clsExportarExcel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ARCRODINPC-06
 */
public class VistaProveedores extends javax.swing.JInternalFrame {

    /**
     * Creates new form VistaProveedores
     */
    
    //---------------- Atributos/ Variables para la 1ra pestaña ----------------------
    
    int idConPro;
    int idProv;
    String dni;
    String nombre;
    String ape;
    String cargo;
    String correo;
    String telefono1;
    String telefono2;
    String sucur;
    String obs;
    
    String prov;
    String dir;
    String contactoPro;
    
    int tabla;
    
    String filtro="p.razonSocial";
        
    //---------------- Atributos/ Variables para la 2da pestaña ----------------------
    
    int idProRub;
    int idPro;
    String prov2;
    int idRubro;
    String rubro1;
    String obs2;
    String estado;
    String estadoCombo;
    
    int tabla2;
    
    String filtro2="r.rubroDesc";
    
    
    //---------------- Atributos/ Variables para la 3ra pestaña ----------------------
    
    int idProArt;
    int idProvee;
    String prov3;
    int idArtPro;
    String artPro;
    String preCom;
    String obs3; 
    
    String articulo;
      
    int tabla3;
    
    String filtro3="ap.descripcion";
    
    public VistaProveedores() {
        initComponents();
        habilitarInicio();
    //----------------------------- PESTAÑA CONTACTOS DE PROVEEDORES / PRIMERA PESTAÑA -------------------------------
     comboBoxSucursal.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

         @Override
         public void keyReleased(KeyEvent evt) {
                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxSucursal.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                     consultarSucursalNombre(comboBoxSucursal.getItemAt(0));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comboBoxSucursal.setSelectedIndex(0);
                   
                    if (comparar(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                           consultarSucursalNombre(cadenaEscrita);

                                                      
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    

                    } else {
                        try {
                            for (int i = 0; i <comboBoxSucursal.getModel().getSize(); i++) {
                                if (comboBoxSucursal.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarSucursalNombre(comboBoxSucursal.getItemAt(i));
                                    comboBoxSucursal.setSelectedIndex(i);
                                }
                            }
                            
                            } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxSucursal.setModel(sucursalcontroler.SucursalListarCombo(cadenaEscrita));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxSucursal.getItemCount() > 0) {
                        comboBoxSucursal.getEditor().setItem(cadenaEscrita);
                        comboBoxSucursal.showPopup();
                    } else {
                        comboBoxSucursal.addItem(cadenaEscrita);
                    }
                }
            }
        });
    
    
    
    
        listaContactoProveedor(filtro, txtFiltro1.getText());
    //----------------------------- PESTAÑA PROVEEDORES POR RUBRO / SEGUNDA PESTAÑA -------------------------------
        listaProveedorRubro(filtro2, txtFiltro2.getText());
    //----------------------------- PESTAÑA PROVEEDORES POR ARTICULOS / TERCERA PESTAÑA -------------------------------
        listaProveedorArticulo(filtro3, txtFiltro3.getText());
    }
    //----------------------------- PESTAÑA CONTACTOS DE PROVEEDORES / PRIMERA PESTAÑA -------------------------------

    private boolean comparar(String cadena) {
        Object[] lista = comboBoxSucursal.getComponents();
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

        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel3 = new javax.swing.JPanel();
        buttonCerrar1 = new javax.swing.JButton();
        comboBoxFiltro = new javax.swing.JComboBox<>();
        txtFiltro1 = new javax.swing.JTextField();
        buttonLimpiar1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        txtProveedor1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        buttonModificar1 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        txtDireccion = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtObs = new javax.swing.JTextField();
        buttonGuardar1 = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        txtContacto = new javax.swing.JTextField();
        buttonSucursal = new javax.swing.JButton();
        comboBoxSucursal = new javax.swing.JComboBox<>();
        buttonExportExcel = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        buttonCerrar2 = new javax.swing.JButton();
        comboBoxFiltro2 = new javax.swing.JComboBox<>();
        txtFiltro2 = new javax.swing.JTextField();
        buttonLimpiar2 = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTable3 = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        txtProveedor2 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtRubro = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txtObs2 = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        buttonModificar2 = new javax.swing.JButton();
        buttonGuardar2 = new javax.swing.JButton();
        comboBoxEstado = new javax.swing.JComboBox<>();
        jPanel6 = new javax.swing.JPanel();
        comboBoxFiltro3 = new javax.swing.JComboBox<>();
        txtFiltro3 = new javax.swing.JTextField();
        buttonLimpiar3 = new javax.swing.JButton();
        buttonCerrar3 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel11 = new javax.swing.JLabel();
        txtProveedor3 = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txtArticulo = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txtPrecioCompra = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        txtObs3 = new javax.swing.JTextField();
        buttonModificar3 = new javax.swing.JButton();
        buttonGuardar3 = new javax.swing.JButton();

        setClosable(true);
        setTitle("Vista Contactos/Rubro/Articulos de Proveedores");

        jTabbedPane2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jTabbedPane2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N

        buttonCerrar1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonCerrar1.setText("Cerrar");
        buttonCerrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrar1ActionPerformed(evt);
            }
        });

        comboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CONTACTO", "CORREO", "TELÉFONO", "PROVEEDOR", "SUCURSAL" }));
        comboBoxFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxFiltroItemStateChanged(evt);
            }
        });

        txtFiltro1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro1KeyReleased(evt);
            }
        });

        buttonLimpiar1.setText("Limpiar");
        buttonLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiar1ActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idContPro", "Contacto", "Telefono", "Correo", "Proveedor", "Sucursal", "Observación"
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
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(200);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(130);
            jTable1.getColumnModel().getColumn(3).setPreferredWidth(170);
            jTable1.getColumnModel().getColumn(4).setPreferredWidth(275);
            jTable1.getColumnModel().getColumn(5).setPreferredWidth(105);
            jTable1.getColumnModel().getColumn(6).setPreferredWidth(190);
        }

        jLabel1.setText("Proveedor:");

        jLabel2.setText("Contacto:");

        buttonModificar1.setText("Modificar");
        buttonModificar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificar1ActionPerformed(evt);
            }
        });

        jLabel4.setText("Dirección Fiscal:");

        jLabel5.setText("Obs. del Contacto:");

        buttonGuardar1.setText("Guardar");
        buttonGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar1ActionPerformed(evt);
            }
        });

        jLabel6.setText("Direcciones Anexas:");

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idDirAnex", "Dirección", "Observación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(576);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(320);
        }

        buttonSucursal.setText("Sucursal:");
        buttonSucursal.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSucursalActionPerformed(evt);
            }
        });

        comboBoxSucursal.setEditable(true);

        buttonExportExcel.setText("ExportExcel");
        buttonExportExcel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonExportExcelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCerrar1))
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGap(79, 79, 79)
                                .addComponent(jScrollPane2))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel1))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtContacto)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(buttonSucursal)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(comboBoxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(9, 9, 9))
                                    .addGroup(jPanel3Layout.createSequentialGroup()
                                        .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, 542, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtObs, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE)))))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(buttonModificar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buttonGuardar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(42, 42, 42)
                        .addComponent(buttonLimpiar1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonExportExcel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiar1)
                    .addComponent(buttonExportExcel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 188, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtProveedor1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(buttonModificar1)
                    .addComponent(txtContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonSucursal)
                    .addComponent(comboBoxSucursal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(txtObs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonGuardar1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addComponent(buttonCerrar1)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Contactos de Proveedores   ", jPanel3);

        buttonCerrar2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonCerrar2.setText("Cerrar");
        buttonCerrar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrar2ActionPerformed(evt);
            }
        });

        comboBoxFiltro2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "PROVEEDOR", "RUBRO", "ESTADO" }));
        comboBoxFiltro2.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxFiltro2ItemStateChanged(evt);
            }
        });

        txtFiltro2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro2KeyReleased(evt);
            }
        });

        buttonLimpiar2.setText("Limpiar");
        buttonLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiar2ActionPerformed(evt);
            }
        });

        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idProvRub", "Proveedores", "Rubro", "Observación", "Estado"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable3.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable3MouseClicked(evt);
            }
        });
        jTable3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable3KeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jTable3);
        if (jTable3.getColumnModel().getColumnCount() > 0) {
            jTable3.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable3.getColumnModel().getColumn(1).setPreferredWidth(400);
            jTable3.getColumnModel().getColumn(2).setPreferredWidth(160);
            jTable3.getColumnModel().getColumn(3).setPreferredWidth(283);
            jTable3.getColumnModel().getColumn(4).setPreferredWidth(150);
        }

        jLabel7.setText("Proveedor:");

        jLabel8.setText("Rubro:");

        jLabel9.setText("Observación:");

        jLabel10.setText("Estado:");

        buttonModificar2.setText("Modificar");
        buttonModificar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificar2ActionPerformed(evt);
            }
        });

        buttonGuardar2.setText("Guardar");
        buttonGuardar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar2ActionPerformed(evt);
            }
        });

        comboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "ACTIVO", "INACTIVO" }));
        comboBoxEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxEstadoItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCerrar2))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(comboBoxFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonLimpiar2))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel7)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel10))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel5Layout.createSequentialGroup()
                                        .addComponent(buttonModificar2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonGuardar2, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(txtProveedor2)
                                        .addComponent(txtObs2, javax.swing.GroupLayout.DEFAULT_SIZE, 430, Short.MAX_VALUE)
                                        .addComponent(txtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 234, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 1015, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 45, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiar2))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(txtProveedor2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txtRubro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(txtObs2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(42, 42, 42)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonModificar2)
                    .addComponent(buttonGuardar2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 76, Short.MAX_VALUE)
                .addComponent(buttonCerrar2)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Proveedores por Rubro   ", jPanel5);

        comboBoxFiltro3.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "PROVEEDOR", "ARTICULO/PRODUCTO" }));
        comboBoxFiltro3.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxFiltro3ItemStateChanged(evt);
            }
        });

        txtFiltro3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltro3KeyReleased(evt);
            }
        });

        buttonLimpiar3.setText("Limpiar");
        buttonLimpiar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiar3ActionPerformed(evt);
            }
        });

        buttonCerrar3.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        buttonCerrar3.setText("Cerrar");
        buttonCerrar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrar3ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idProvArt", "Proveedor", "Producto", "Precio Compra", "Observación"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable4.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jTable4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable4KeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);
        if (jTable4.getColumnModel().getColumnCount() > 0) {
            jTable4.getColumnModel().getColumn(0).setPreferredWidth(10);
            jTable4.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable4.getColumnModel().getColumn(2).setPreferredWidth(200);
            jTable4.getColumnModel().getColumn(3).setPreferredWidth(150);
            jTable4.getColumnModel().getColumn(4).setPreferredWidth(250);
        }

        jLabel11.setText("Proveedor:");

        jLabel12.setText("Producto:");

        jLabel13.setText("Precio Compra:");

        jLabel14.setText("Observación:");

        buttonModificar3.setText("Modificar");
        buttonModificar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificar3ActionPerformed(evt);
            }
        });

        buttonGuardar3.setText("Guardar");
        buttonGuardar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardar3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCerrar3))
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addComponent(comboBoxFiltro3, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtFiltro3, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonLimpiar3))
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 972, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel6Layout.createSequentialGroup()
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel6Layout.createSequentialGroup()
                                        .addGap(24, 24, 24)
                                        .addComponent(jLabel11))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel6Layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel13, javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.TRAILING))))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtProveedor3, javax.swing.GroupLayout.DEFAULT_SIZE, 434, Short.MAX_VALUE)
                                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtObs3))))
                        .addGap(0, 88, Short.MAX_VALUE)))
                .addContainerGap())
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel6Layout.createSequentialGroup()
                .addGap(132, 132, 132)
                .addComponent(buttonModificar3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonGuardar3)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxFiltro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiar3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtProveedor3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(txtArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(txtPrecioCompra, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtObs3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14))
                .addGap(50, 50, 50)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonModificar3)
                    .addComponent(buttonGuardar3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 58, Short.MAX_VALUE)
                .addComponent(buttonCerrar3)
                .addContainerGap())
        );

        jTabbedPane2.addTab("Proveedores por Producto  ", jPanel6);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrar1ActionPerformed
        dispose(); //PRIMERA PESTAÑA
    }//GEN-LAST:event_buttonCerrar1ActionPerformed

    private void buttonCerrar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrar2ActionPerformed
        dispose(); //SEGUNDA PESTAÑA
    }//GEN-LAST:event_buttonCerrar2ActionPerformed

    private void buttonCerrar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrar3ActionPerformed
        dispose(); //TERCERA PESTAÑA
    }//GEN-LAST:event_buttonCerrar3ActionPerformed

    private void txtFiltro1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro1KeyReleased
        //--- PRIMERA PESTAÑA / PESTAÑA CONTACTOS DE PROVEEDORES 
        listaContactoProveedor(filtro, txtFiltro1.getText()); 
    }//GEN-LAST:event_txtFiltro1KeyReleased

    private void comboBoxFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxFiltroItemStateChanged
       
    //--- PRIMERA PESTAÑA / PESTAÑA CONTACTOS DE PROVEEDORES 
        
        if (comboBoxFiltro.getSelectedItem() == "CONTACTO") {
            filtro = "concat(cp.nombres,' ',cp.apellidos)";
            listaContactoProveedor(filtro, txtFiltro1.getText()); 
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "CORREO") {
            filtro = "cp.correo";
            listaContactoProveedor(filtro, txtFiltro1.getText()); 
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "TELÉFONO") {
            filtro = "cp.telefono1";
            listaContactoProveedor(filtro, txtFiltro1.getText()); 
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "PROVEEDOR") {
            filtro = "p.razonSocial";
            listaContactoProveedor(filtro, txtFiltro1.getText()); 
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "SUCURSAL") {
            filtro = "cp.sucursal";
            listaContactoProveedor(filtro, txtFiltro1.getText()); 
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "SELECCIONAR") {
            filtro = "p.razonSocial";
            listaContactoProveedor(filtro, txtFiltro1.getText()); 
            System.out.println(filtro);
        }
    }//GEN-LAST:event_comboBoxFiltroItemStateChanged

    private void buttonGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar1ActionPerformed
        // PRIMERA PESTAÑA / PESTAÑA CONTACTOS DE PROVEEDORES 
        procesar(2);
        listaContactoProveedor(filtro, txtFiltro1.getText()); 
        habilitarInicio();
        
    }//GEN-LAST:event_buttonGuardar1ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // PRIMERA PESTAÑA / PESTAÑA CONTACTOS DE PROVEEDORES
        tabla = jTable1.getSelectedRow();
        idConPro = Integer.parseInt(jTable1.getValueAt(tabla, 0).toString());
        try {
            consultar(idConPro);
        } catch (Exception ex) {
            Logger.getLogger(VistaProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTable1MouseClicked

    private void comboBoxFiltro2ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxFiltro2ItemStateChanged
    //  SEGUNDA PESTAÑA /   PESTAÑA PROVEEDORES POR RUBRO
        
        if (comboBoxFiltro2.getSelectedItem() == "PROVEEDOR") {
            filtro2 = "p.razonSocial";
            listaProveedorRubro(filtro2, txtFiltro2.getText());
            System.out.println(filtro2);
        } else if (comboBoxFiltro2.getSelectedItem() == "RUBRO") {
            filtro2 = "r.rubroDesc";
            listaProveedorRubro(filtro2, txtFiltro2.getText()); 
            System.out.println(filtro2);
        } else if (comboBoxFiltro2.getSelectedItem() == "ESTADO") {
            filtro2 = "pr.estado";
            listaProveedorRubro(filtro2, txtFiltro2.getText()); 
            System.out.println(filtro2);
        } else if (comboBoxFiltro2.getSelectedItem() == "SELECCIONAR") {
            filtro2 = "p.razonSocial";
            listaProveedorRubro(filtro2, txtFiltro2.getText()); 
            System.out.println(filtro2);
        }
    }//GEN-LAST:event_comboBoxFiltro2ItemStateChanged

    private void buttonGuardar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar2ActionPerformed
       //  SEGUNDA PESTAÑA /   PESTAÑA PROVEEDORES POR RUBRO
       procesar2(2);
       listaProveedorRubro(filtro2, txtFiltro2.getText()); 
       habilitarInicio();
    }//GEN-LAST:event_buttonGuardar2ActionPerformed

    private void jTable3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable3MouseClicked
        //  SEGUNDA PESTAÑA /   PESTAÑA PROVEEDORES POR RUBRO
        tabla2 = jTable3.getSelectedRow();
        idProRub = Integer.parseInt(jTable3.getValueAt(tabla2, 0).toString());
        System.out.println("Id del proveedor rubr: "+idProRub);
        try {
            consultarProveedorRubro(idProRub);
        } catch (Exception ex) {
            Logger.getLogger(VistaProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_jTable3MouseClicked

    private void txtFiltro2KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro2KeyReleased
        //  SEGUNDA PESTAÑA / PESTAÑA PROVEEDORES POR RUBRO
        listaProveedorRubro(filtro2, txtFiltro2.getText());
    }//GEN-LAST:event_txtFiltro2KeyReleased

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
    //  TERCERA PESTAÑA / PESTAÑA PROVEEDORES POR ARTICULOS 
        tabla3 = jTable4.getSelectedRow();
        idProArt = Integer.parseInt(jTable4.getValueAt(tabla3, 0).toString());
      
        try {
            consultarProveedorArticulo(idProArt);
        } catch (Exception ex) {
            Logger.getLogger(VistaProveedores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void buttonGuardar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardar3ActionPerformed
    //  TERCERA PESTAÑA / PESTAÑA PROVEEDORES POR ARTICULOS     
        procesar3(2);
        listaProveedorArticulo(filtro3, txtFiltro3.getText());
        habilitarInicio();
    }//GEN-LAST:event_buttonGuardar3ActionPerformed

    private void comboBoxFiltro3ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxFiltro3ItemStateChanged
    //  TERCERA PESTAÑA / PESTAÑA PROVEEDORES POR ARTICULOS     
        if (comboBoxFiltro3.getSelectedItem() == "PROVEEDOR") {
            filtro3 = "p.razonSocial";
            listaProveedorArticulo(filtro3, txtFiltro3.getText());
            System.out.println(filtro3);
        } else if (comboBoxFiltro3.getSelectedItem() == "ARTICULO/PRODUCTO") {
            filtro3 = "ap.descripcion";
            listaProveedorArticulo(filtro3, txtFiltro3.getText()); 
            System.out.println(filtro3);
        } else if (comboBoxFiltro3.getSelectedItem() == "SELECCIONAR") {
            filtro3 = "p.razonSocial";
            listaProveedorArticulo(filtro3, txtFiltro3.getText()); 
            System.out.println(filtro3);
        }
    }//GEN-LAST:event_comboBoxFiltro3ItemStateChanged

    private void txtFiltro3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltro3KeyReleased
    //  TERCERA PESTAÑA / PESTAÑA PROVEEDORES POR ARTICULOS 
        listaProveedorArticulo(filtro3, txtFiltro3.getText());
    }//GEN-LAST:event_txtFiltro3KeyReleased

    private void buttonLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiar2ActionPerformed
        limpiar2();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiar2ActionPerformed

    private void buttonModificar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificar2ActionPerformed
       habilitarModificar2();
    }//GEN-LAST:event_buttonModificar2ActionPerformed

    private void buttonLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiar1ActionPerformed
        limpiar1();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiar1ActionPerformed

    private void buttonModificar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificar1ActionPerformed
        habilitarModificar1();
    }//GEN-LAST:event_buttonModificar1ActionPerformed

    private void buttonLimpiar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiar3ActionPerformed
        limpiar3();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiar3ActionPerformed

    private void buttonModificar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificar3ActionPerformed
      habilitarModificar3();
    }//GEN-LAST:event_buttonModificar3ActionPerformed

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
    // PRIMERA PESTAÑA / PESTAÑA CONTACTOS DE PROVEEDORES   
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_UP)) {

            tabla = jTable1.getSelectedRow();
            idConPro = Integer.parseInt(jTable1.getValueAt(tabla, 0).toString());
            try {
                consultar(idConPro);
            } catch (Exception ex) {
                Logger.getLogger(VistaProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_jTable1KeyReleased

    private void jTable3KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable3KeyReleased
    //  SEGUNDA PESTAÑA /   PESTAÑA PROVEEDORES POR RUBRO
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_UP)) {
            tabla2 = jTable3.getSelectedRow();
            idProRub = Integer.parseInt(jTable3.getValueAt(tabla2, 0).toString());
            System.out.println("Id del proveedor rubr: " + idProRub);
            try {
                consultarProveedorRubro(idProRub);
            } catch (Exception ex) {
                Logger.getLogger(VistaProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable3KeyReleased

    private void jTable4KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable4KeyReleased
     //  TERCERA PESTAÑA / PESTAÑA PROVEEDORES POR ARTICULOS 
        if ((evt.getKeyCode() == KeyEvent.VK_DOWN) || (evt.getKeyCode() == KeyEvent.VK_UP)) {
            tabla3 = jTable4.getSelectedRow();
            idProArt = Integer.parseInt(jTable4.getValueAt(tabla3, 0).toString());

            try {
                consultarProveedorArticulo(idProArt);
            } catch (Exception ex) {
                Logger.getLogger(VistaProveedores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_jTable4KeyReleased

    private void comboBoxEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxEstadoItemStateChanged
        if (comboBoxEstado.getSelectedIndex() != 0) {
            estadoCombo = comboBoxEstado.getSelectedItem().toString();
        } else {
            estadoCombo = "";
        }
    }//GEN-LAST:event_comboBoxEstadoItemStateChanged

    private void buttonSucursalActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSucursalActionPerformed
        SucursalRegistro sucuRe= new SucursalRegistro();
        Principal.jDesktopPane1.add(sucuRe);
        sucuRe.toFront();
        sucuRe.setVisible(true);
    }//GEN-LAST:event_buttonSucursalActionPerformed

    private void buttonExportExcelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonExportExcelActionPerformed
        clsExportarExcel objeto = new clsExportarExcel();
        try {
            objeto.exportarExcel(jTable1);
        } catch (IOException ex) {
            Logger.getLogger(CajaChicaRegistro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonExportExcelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar1;
    private javax.swing.JButton buttonCerrar2;
    private javax.swing.JButton buttonCerrar3;
    private javax.swing.JButton buttonExportExcel;
    private javax.swing.JButton buttonGuardar1;
    private javax.swing.JButton buttonGuardar2;
    private javax.swing.JButton buttonGuardar3;
    private javax.swing.JButton buttonLimpiar1;
    private javax.swing.JButton buttonLimpiar2;
    private javax.swing.JButton buttonLimpiar3;
    private javax.swing.JButton buttonModificar1;
    private javax.swing.JButton buttonModificar2;
    private javax.swing.JButton buttonModificar3;
    private javax.swing.JButton buttonSucursal;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private javax.swing.JComboBox<String> comboBoxFiltro;
    private javax.swing.JComboBox<String> comboBoxFiltro2;
    private javax.swing.JComboBox<String> comboBoxFiltro3;
    private javax.swing.JComboBox<String> comboBoxSucursal;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JTable jTable3;
    private javax.swing.JTable jTable4;
    private javax.swing.JTextField txtArticulo;
    private javax.swing.JTextField txtContacto;
    private javax.swing.JTextField txtDireccion;
    private javax.swing.JTextField txtFiltro1;
    private javax.swing.JTextField txtFiltro2;
    private javax.swing.JTextField txtFiltro3;
    private javax.swing.JTextField txtObs;
    private javax.swing.JTextField txtObs2;
    private javax.swing.JTextField txtObs3;
    private javax.swing.JTextField txtPrecioCompra;
    private javax.swing.JTextField txtProveedor1;
    private javax.swing.JTextField txtProveedor2;
    private javax.swing.JTextField txtProveedor3;
    private javax.swing.JTextField txtRubro;
    // End of variables declaration//GEN-END:variables


    ProveedorController proveedorcontroler = new ProveedorController();
    Proveedor proveedor;
    
    ContactoProveedorController contactocontroler = new ContactoProveedorController();
    ContactoProveedor contacto;
    
    ProveedorRubroController proveedorrubrocontroler = new ProveedorRubroController();
    ProveedorRubro proveedorrubro;
    
    RubroController rubrocontroler = new RubroController();
    Rubro rubro;
    
    ArticuloProveedorController articuloproveedorcontroler = new ArticuloProveedorController();
    ArticuloProveedor articuloproveedor;
    
    ProveedorArticuloController proveedorarticulocontroler = new ProveedorArticuloController();
    ProveedorArticulo proveedorarticulo;
    
    DireccionAnexaProvController direccionprovcontroler= new DireccionAnexaProvController();
    DireccionAnexaProv direccionprov;
 
    SucursalController sucursalcontroler= new SucursalController();
    Sucursal sucursal;
    
//----------------------------- PESTAÑA CONTACTOS DE PROVEEDORES ----------------------------------
 
    //---------------------------------- Tabla-----------------------------

    private void listaContactoProveedor(String filtrar, String pal) {
        List<ContactoProveedor> lista;
        try {
            lista = contactocontroler.ContactoProveedorFiltrar(filtrar,pal);
            verOrdenCorte(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verOrdenCorte(List<ContactoProveedor> lista) {
        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (ContactoProveedor conPro : lista) {
            
            Object[] fila = {conPro.getIdContactoProveedor(), conPro.getContacto(), conPro.getTlf1(), conPro.getCorreo(),
                             conPro.getRazSocial(),conPro.getSucursal(), conPro.getObservacion()};
            tabla.addRow(fila);
        }
    }

//---------------------------- Consultar --------------------------------------------
     private void consultar(int id ) throws Exception {
        contacto = contactocontroler.ContactoProveedorBuscar(id);
        if (contacto!= null) {

            idConPro=contacto.getIdContactoProveedor();
            idProv=contacto.getIdProveedor();
            consultarProveedor(idProv);
            listaDireccionAnexaProv(idProv);
            dni=contacto.getDni();
            nombre=contacto.getNombres();
            ape=contacto.getApellidos();
            contactoPro=nombre+" "+ape;
            txtContacto.setText(contactoPro);
            cargo=contacto.getCargo();
            correo=contacto.getCorreo();
            telefono1=contacto.getTlf1();
            telefono2=contacto.getTlf2();
            sucur=contacto.getSucursal();
            comboBoxSucursal.setSelectedItem(contacto.getSucursal());
            obs=contacto.getObservacion();
            txtObs.setText(obs);
 

        } else {
            JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }
     
     private void consultarProveedor(int id ) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscarId(id);
        if (proveedor!= null) {

            prov=proveedor.getRazonSocial();
            txtProveedor1.setText(prov);
            dir=proveedor.getDireccion();
            txtDireccion.setText(dir);
           
        
        } else {
            JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarSucursalNombre(String cadena) throws Exception {
        sucursal = sucursalcontroler.SucursalBuscarNombre(cadena);
        if (sucursal != null) {

            sucur=sucursal.getSucursal();
            System.out.println("la sucursal es: "+sucur);
            

        } else {
            JOptionPane.showMessageDialog(null, "sucursal no registrado");
            //System.out.println("Error");
        }
    } 
// ----------------------------- Tabla Dirección Anexa --------------------------
   
     private void listaDireccionAnexaProv(int id) {
        List<DireccionAnexaProv> lista;
        try {
            lista =direccionprovcontroler.DireccionAnexaProvListar(id) ;
            verDireccionAnexa(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verDireccionAnexa(List<DireccionAnexaProv> lista) {
        DefaultTableModel tabla = (DefaultTableModel) jTable2.getModel();
        tabla.setRowCount(0);
        for (DireccionAnexaProv direc : lista) {
            
            Object[] fila = {direc.getIdDirAnexa(), direc.getDirec(), direc.getObs()};
            tabla.addRow(fila);
        }
    }
 //---------------------------- Procesar ----------------------------------------
    private void procesar(int op) {

        contacto = leerDatos();

        try {

            String msg = contactocontroler.ContactoProveedorProcesar(contacto, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
     private ContactoProveedor leerDatos() {
        ContactoProveedor conPro = new ContactoProveedor();

        
        conPro.setIdContactoProveedor(idConPro);
        conPro.setIdProveedor(idProv);
        conPro.setDni(dni);
        conPro.setNombres(nombre);
        conPro.setApellidos(ape);
        conPro.setCargo(cargo);
        conPro.setCorreo(correo);
        conPro.setTlf1(telefono1);
        conPro.setTlf2(telefono2);
        conPro.setSucursal(comboBoxSucursal.getEditor().getItem().toString());
        conPro.setObservacion(txtObs.getText());
        

        return conPro;
    }
     
 
//----------------------------- PESTAÑA PROVEEDORES POR RUBRO // 2DA PESTAÑA ----------------------------------

     
     //---------------------------------- Tabla-----------------------------

    private void listaProveedorRubro(String filtrar, String pal) {
        List<ProveedorRubro> lista;
        try {
            lista = proveedorrubrocontroler.ProveedorRubroFiltrar(filtrar,pal);
            verProveedorRubro(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verProveedorRubro(List<ProveedorRubro> lista) {
        DefaultTableModel tabla = (DefaultTableModel) jTable3.getModel();
        tabla.setRowCount(0);
        for (ProveedorRubro proRub : lista) {
            
            Object[] fila = {proRub.getIdProvRubpk(), proRub.getProvee(), proRub.getRubro(), proRub.getObs(), 
                             proRub.getEstado()};
            tabla.addRow(fila);
        }
    }

//---------------------------- Consultar --------------------------------------------
     private void consultarProveedorRubro(int id ) throws Exception {
         proveedorrubro = proveedorrubrocontroler.ProveedorRubroBuscar(id);
         if (proveedorrubro != null) {
             idProRub = proveedorrubro.getIdProvRubpk();
             idPro = proveedorrubro.getIdProv();
             consultarProveedorParaRubro(idPro);
             idRubro = proveedorrubro.getIdRubro();
             consultarRubro(idRubro);
             obs2 = proveedorrubro.getObs();
             txtObs2.setText(proveedorrubro.getObs());
             estado = proveedorrubro.getEstado();
             estado = proveedorrubro.getEstado();
             if ("".equals(estado)) {
                 comboBoxEstado.setSelectedIndex(0);

             } else {
                 String testValue1 = estado;
                 for (int i = 0; i < comboBoxEstado.getModel().getSize(); i++) {
                     if (comboBoxEstado.getItemAt(i).toString().equals(testValue1)) {
                         System.out.println(i);
                         comboBoxEstado.setSelectedIndex(i);
                         break;
                     }
                 }
             }
 

        } else {
            JOptionPane.showMessageDialog(null, "Rubro no registrado");
            //System.out.println("Error");
        }
    }
     
     private void consultarProveedorParaRubro(int id ) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscarId(id);
        if (proveedor!= null) {

            prov2=proveedor.getRazonSocial();
            txtProveedor2.setText(prov2);
            
        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no registrado");
            //System.out.println("Error");
        }
    }
     
     private void consultarRubro(int id ) throws Exception {
        rubro = rubrocontroler.RubroBuscar(id);
        if (rubro!= null) {

            rubro1=rubro.getDescripcion();
            txtRubro.setText(rubro1);
            
        } else {
            JOptionPane.showMessageDialog(null, "Rubro no registrado");
            //System.out.println("Error");
        }
    }
  
  //---------------------------- Procesar ----------------------------------------
    private void procesar2(int op) {

        proveedorrubro = leerDatos2();

        try {

            String msg = proveedorrubrocontroler.ProveedorRubroProcesar(proveedorrubro, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
     private ProveedorRubro leerDatos2() {
        ProveedorRubro proRub = new ProveedorRubro();

        proRub.setIdProvRubpk(idProRub);
        proRub.setIdProv(idPro);
        proRub.setIdRubro(idRubro);
        proRub.setObs(txtObs2.getText());
        proRub.setEstado(estadoCombo);
     
        return proRub;
    }

 //------------------------ PESTAÑA PROVEEDORES POR ARTICULOS // 3RA PESTAÑA ----------------------------
     
     //---------------------------------- Tabla-----------------------------

    private void listaProveedorArticulo(String filtrar, String pal) {
        List<ProveedorArticulo> lista;
        try {
            lista = proveedorarticulocontroler.ProveedorArticuloFiltrar(filtrar,pal);
            verProveedorArticulo(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verProveedorArticulo(List<ProveedorArticulo> lista) {
        DefaultTableModel tabla = (DefaultTableModel) jTable4.getModel();
        tabla.setRowCount(0);
        for (ProveedorArticulo proArt : lista) {
            
            Object[] fila = {proArt.getIdProvArtPk(), proArt.getRazSocial(),proArt.getArticulo(), 
                             proArt.getPrecio(), proArt.getObs() };
            tabla.addRow(fila);
        }
    }

//---------------------------- Consultar --------------------------------------------
    
     private void consultarProveedorArticulo(int id ) throws Exception {
         proveedorarticulo= proveedorarticulocontroler.ProveedorArticuloBuscar(id);
        if (proveedorarticulo!= null) {
            
            idProArt = proveedorarticulo.getIdProvArtPk();
            idProvee = proveedorarticulo.getIdProv();
            consultarProveedorParaArticulo(idProvee);
            idArtPro = proveedorarticulo.getIdArtProv();
            consultarArticulo(idArtPro);
            preCom = proveedorarticulo.getPrecio();
            txtPrecioCompra.setText(proveedorarticulo.getPrecio()); 
            obs3 = proveedorarticulo.getObs();
            txtObs3.setText(proveedorarticulo.getObs());
 
            } else {
            JOptionPane.showMessageDialog(null, "Articulo no registrado");
            //System.out.println("Error");
        }
    }
     
     private void consultarProveedorParaArticulo(int id ) throws Exception {
        proveedor = proveedorcontroler.ProveedorBuscarId(id);
        if (proveedor!= null) {

            prov3=proveedor.getRazonSocial();
            txtProveedor3.setText(prov3);
            
        } else {
            JOptionPane.showMessageDialog(null, "Proveedor no registrado");
            //System.out.println("Error");
        }
    }
     
     private void consultarArticulo(int id ) throws Exception {
        articuloproveedor = articuloproveedorcontroler.ArticuloProveedorBuscar(id);
        if (articuloproveedor!= null) {

            articulo=articuloproveedor.getDescripcion();
            txtArticulo.setText(articuloproveedor.getDescripcion());
        } else {
            JOptionPane.showMessageDialog(null, "Articulo no registrado");
            //System.out.println("Error");
        }
    }
  
  //---------------------------- Procesar ----------------------------------------
    private void procesar3(int op) {

        proveedorarticulo = leerDatos3();

        try {

            String msg = proveedorarticulocontroler.ProveedorArticuloProcesar(proveedorarticulo, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    }
    
     private ProveedorArticulo leerDatos3() {
        ProveedorArticulo proArt = new ProveedorArticulo();
        
        proArt.setIdProvArtPk(idProArt);
        proArt.setIdProv(idProvee);
        proArt.setIdArtProv(idArtPro);
        proArt.setPrecio(txtPrecioCompra.getText());
        proArt.setObs(txtObs3.getText());
           
        return proArt;
    }
     
 //--------------------------------- Habilitar Inico para las tres pestañas -------------------------
     
    void habilitarInicio() {
//--- PRIMERA PESTAÑA / PESTAÑA CONTACTOS DE PROVEEDORES 
        buttonLimpiar1.setEnabled(true);
        buttonModificar1.setEnabled(true);
        buttonGuardar1.setEnabled(false);
        buttonSucursal.setEnabled(false);

        txtFiltro1.setEnabled(true);
        txtProveedor1.setEnabled(true);
        txtContacto.setEnabled(true);
        txtDireccion.setEnabled(true);
        comboBoxSucursal.setEnabled(false);
        txtObs.setEnabled(false);

//  SEGUNDA PESTAÑA / PESTAÑA PROVEEDORES POR RUBRO         
        buttonLimpiar2.setEnabled(true);
        buttonModificar2.setEnabled(true);
        buttonGuardar2.setEnabled(false);

        txtFiltro2.setEnabled(true);
        txtProveedor2.setEnabled(true);
        txtRubro.setEnabled(true);
        comboBoxEstado.setEnabled(false);
        txtObs2.setEnabled(false);

//  TERCERA PESTAÑA / PESTAÑA PROVEEDORES POR ARTICULOS          
        buttonLimpiar3.setEnabled(true);
        buttonModificar3.setEnabled(true);
        buttonGuardar3.setEnabled(false);

        txtFiltro3.setEnabled(true);
        txtProveedor3.setEnabled(true);
        txtArticulo.setEnabled(true);
        txtPrecioCompra.setEnabled(false);
        txtObs3.setEnabled(false);

    }
    void habilitarModificar1() {
        buttonModificar1.setEnabled(false);
        buttonGuardar1.setEnabled(true);
        buttonSucursal.setEnabled(true);

        comboBoxSucursal.setEnabled(true);
        txtObs.setEnabled(true);
    }

    void habilitarModificar2() {
        buttonModificar2.setEnabled(false);
        buttonGuardar2.setEnabled(true);

        comboBoxEstado.setEnabled(true);
        txtObs2.setEnabled(true);
    }

    void habilitarModificar3() {
        buttonModificar3.setEnabled(false);
        buttonGuardar3.setEnabled(true);

        txtPrecioCompra.setEnabled(true);
        txtObs3.setEnabled(true);
    }
    
    void limpiar1() {
        txtFiltro1.setText("");
        txtProveedor1.setText("");
        txtContacto.setText("");
        comboBoxSucursal.setSelectedItem("");
        txtDireccion.setText("");
        txtObs.setText("");
    }

    void limpiar2() {
        txtFiltro2.setText("");
        txtProveedor2.setText("");
        txtRubro.setText("");
        comboBoxEstado.setSelectedIndex(0);
        txtObs2.setText("");
    }

    void limpiar3() {
        txtFiltro3.setText("");
        txtProveedor3.setText("");
        txtArticulo.setText("");
        txtPrecioCompra.setText("");
        txtObs3.setText("");
    }
}


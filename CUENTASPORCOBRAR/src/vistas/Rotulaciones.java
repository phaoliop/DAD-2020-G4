/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ClienteController;
import controller.ContactoClienteController;
import controller.RotulacionAtencionController;
import controller.RotulacionController;
import controller.SucursalController;
import entity.Cliente;
import entity.ContactoCliente;
import entity.Rotulacion;
import entity.RotulacionAtencion;
import entity.Sucursal;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import reportes.GenerarReporte;
import static vistas.Clientes.x;

/**
 *
 * @author ARCRODINPC-05
 */
public final class Rotulaciones extends javax.swing.JInternalFrame {

    /**
     * Creates new form Rotulaciones
     */
    int idRotulacion;
    int idRotAtencion;
    int idCliente;
    String razSocial;
    int idContactoCliente;
    String comboContacto;
    String atencion;
   
    String filtro="c.razonSocial";
    
    int tablaRotulacion;
    
    int tablaAtencion;
    
    String sucursalNombre;
    
    int ultimoIdRotulacion;
    int ultimoIdAtencion;
    
    public Rotulaciones() {
        initComponents();
        listaRotulacion(filtro, txtFiltro.getText());
        habilitarInicio();
        
         comboBoxRazSocial.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {
           
             @Override
             public void keyReleased(KeyEvent evt) {

                // aca falta validar que me ejecute el campo
                String cadenaEscrita = comboBoxRazSocial.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    limpiarComboCliente();
                    try {
                        consultarComboCliente(comboBoxRazSocial.getItemAt(0));
//                        comboBoxContacto.setModel(contactocontroler.ListarCombodeContacto(comboBoxRazSocial.getItemAt(0), idCliente));

                        //aCargarContactosCombo(cadenaEscrita);
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    if (comparar(cadenaEscrita)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                            consultarComboCliente(cadenaEscrita);
//                            comboBoxContacto.setModel(contactocontroler.ListarCombodeContacto(cadenaEscrita, idCliente));

                                                       
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        try {
                            for (int i = 0; i < comboBoxRazSocial.getModel().getSize(); i++) {
                                if (comboBoxRazSocial.getItemAt(i).equals(cadenaEscrita)) {
                                    consultarComboCliente(comboBoxRazSocial.getItemAt(i));
//                                    comboBoxContacto.setModel(contactocontroler.ListarCombodeContacto(comboBoxRazSocial.getItemAt(i), idCliente));

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
    
         
         comboBoxDestino.getEditor().getEditorComponent().addKeyListener(new KeyAdapter() {

         @Override
         public void keyReleased(KeyEvent evt) {
                // aca falta validar que me ejecute el campo
                String cadenaEscritaDestino = comboBoxDestino.getEditor().getItem().toString();
                if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
                    try {
                     consultarSucursalNombre(comboBoxDestino.getItemAt(0));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    comboBoxDestino.setSelectedIndex(0);
                   
                    if (compararDestino(cadenaEscritaDestino)) {
                        try {
                            // compara si el texto escrito se ecuentra en la lista
                            // busca el texto escrito en la base de datos, solo pasa si es identico
                           consultarSucursalNombre(cadenaEscritaDestino);

                                                      
                        } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    

                    } else {
                        try {
                            for (int i = 0; i <comboBoxDestino.getModel().getSize(); i++) {
                                if (comboBoxDestino.getItemAt(i).equals(cadenaEscritaDestino)) {
                                    consultarSucursalNombre(comboBoxDestino.getItemAt(i));
                                    comboBoxDestino.setSelectedIndex(i);
                                }
                            }
                            
                            } catch (Exception ex) {
                            Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

                if (evt.getKeyCode() >= 65 && evt.getKeyCode() <= 90 || evt.getKeyCode() >= 96 && evt.getKeyCode() <= 105 || evt.getKeyCode() == 8) {
                    try {
                        comboBoxDestino.setModel(sucursalcontroler.SucursalListarCombo(cadenaEscritaDestino));
                    } catch (Exception ex) {
                        Logger.getLogger(Clientes.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    if (comboBoxDestino.getItemCount() > 0) {
                        comboBoxDestino.getEditor().setItem(cadenaEscritaDestino);
                        comboBoxDestino.showPopup();
                    } else {
                        comboBoxDestino.addItem(cadenaEscritaDestino);
                    }
                }
            }
        });
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
    
    private boolean compararDestino(String cadena) {
        Object[] lista = comboBoxDestino.getComponents();
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
        labelIdRotulacion = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        comboBoxRemitente = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        comboBoxRazSocial = new javax.swing.JComboBox<>();
        buttonLimpiar = new javax.swing.JButton();
        buttonNuevoRotulacion = new javax.swing.JButton();
        buttonRegistrarRotulacion = new javax.swing.JButton();
        buttonModificarRotulacion = new javax.swing.JButton();
        buttonGuardarRotulacion = new javax.swing.JButton();
        buttonEliminarRotulacion = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        buttonCerrar = new javax.swing.JButton();
        buttonContactoCliente = new javax.swing.JButton();
        comboBoxContacto = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable2 = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        labelIdRotulacionAtencion = new javax.swing.JLabel();
        comboBoxFiltro = new javax.swing.JComboBox<>();
        txtFiltro = new javax.swing.JTextField();
        buttonRegistrarAtencion = new javax.swing.JButton();
        buttonModificarAtencion = new javax.swing.JButton();
        buttonGuardarAtencion = new javax.swing.JButton();
        buttonEliminarAtencion = new javax.swing.JButton();
        buttonActualizarContactos = new javax.swing.JButton();
        buttonLimpiarAtencion = new javax.swing.JButton();
        buttonVerPdf = new javax.swing.JButton();
        buttonImprimir = new javax.swing.JButton();
        buttonAgregarDestino = new javax.swing.JButton();
        comboBoxDestino = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        txtDni = new javax.swing.JTextField();

        setClosable(true);
        setTitle("Registro de Rotulaciones ");

        jLabel1.setText("N° de Rotulación:");

        labelIdRotulacion.setText("0000000");

        jLabel3.setText("Remitente:");

        comboBoxRemitente.setEditable(true);
        comboBoxRemitente.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "ARCRODIN S.A.C.", "DIEGO MAX LOPEZ TORRES", "ABEL LOPEZ TORRES", "CARMEN LOPEZ TORRES", "ROSAURA LOPEZ TORRES", "LUIS BERROCAL ROMERO" }));

        jLabel4.setText("Destinatario:");

        comboBoxRazSocial.setEditable(true);

        buttonLimpiar.setText("Limpiar");
        buttonLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarActionPerformed(evt);
            }
        });

        buttonNuevoRotulacion.setText("Nuevo");
        buttonNuevoRotulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonNuevoRotulacionActionPerformed(evt);
            }
        });

        buttonRegistrarRotulacion.setText("Registrar");
        buttonRegistrarRotulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarRotulacionActionPerformed(evt);
            }
        });

        buttonModificarRotulacion.setText("Modificar");
        buttonModificarRotulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarRotulacionActionPerformed(evt);
            }
        });

        buttonGuardarRotulacion.setText("Guardar");
        buttonGuardarRotulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarRotulacionActionPerformed(evt);
            }
        });

        buttonEliminarRotulacion.setText("Eliminar");
        buttonEliminarRotulacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarRotulacionActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idAtencion", "idRotulacion", "Atención"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
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
            jTable1.getColumnModel().getColumn(1).setPreferredWidth(15);
            jTable1.getColumnModel().getColumn(2).setPreferredWidth(380);
        }

        buttonCerrar.setText("Cerrar");
        buttonCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCerrarActionPerformed(evt);
            }
        });

        buttonContactoCliente.setText("Contacto :");
        buttonContactoCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonContactoClienteActionPerformed(evt);
            }
        });

        comboBoxContacto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxContactoItemStateChanged(evt);
            }
        });
        comboBoxContacto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxContactoActionPerformed(evt);
            }
        });

        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "idRotulacion", "Cliente", "Remitente", "Destino"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable2.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(jTable2);
        if (jTable2.getColumnModel().getColumnCount() > 0) {
            jTable2.getColumnModel().getColumn(0).setPreferredWidth(15);
            jTable2.getColumnModel().getColumn(1).setPreferredWidth(350);
            jTable2.getColumnModel().getColumn(2).setPreferredWidth(350);
            jTable2.getColumnModel().getColumn(3).setPreferredWidth(200);
        }

        jLabel6.setText("N° de Atención:");

        labelIdRotulacionAtencion.setText("00000");

        comboBoxFiltro.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "CLIENTE", "REMITENTE", "DESTINO" }));
        comboBoxFiltro.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxFiltroItemStateChanged(evt);
            }
        });

        txtFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtFiltroKeyReleased(evt);
            }
        });

        buttonRegistrarAtencion.setText("Registrar");
        buttonRegistrarAtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonRegistrarAtencionActionPerformed(evt);
            }
        });

        buttonModificarAtencion.setText("Modificar");
        buttonModificarAtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonModificarAtencionActionPerformed(evt);
            }
        });

        buttonGuardarAtencion.setText("Guardar");
        buttonGuardarAtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarAtencionActionPerformed(evt);
            }
        });

        buttonEliminarAtencion.setText("Eliminar");
        buttonEliminarAtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEliminarAtencionActionPerformed(evt);
            }
        });

        buttonActualizarContactos.setText("Actualizar");
        buttonActualizarContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonActualizarContactosActionPerformed(evt);
            }
        });

        buttonLimpiarAtencion.setText("Limpiar");
        buttonLimpiarAtencion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarAtencionActionPerformed(evt);
            }
        });

        buttonVerPdf.setText("Visualizar PDF");
        buttonVerPdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonVerPdfActionPerformed(evt);
            }
        });

        buttonImprimir.setText("Imprimir");
        buttonImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonImprimirActionPerformed(evt);
            }
        });

        buttonAgregarDestino.setText("Destino");
        buttonAgregarDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonAgregarDestinoActionPerformed(evt);
            }
        });

        comboBoxDestino.setEditable(true);

        jLabel2.setText("D.N.I.:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 332, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonLimpiar)
                        .addGap(227, 227, 227))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelIdRotulacion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonNuevoRotulacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonRegistrarRotulacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonModificarRotulacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonGuardarRotulacion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonEliminarRotulacion))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, 207, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, 334, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(buttonAgregarDestino)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(comboBoxDestino, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelIdRotulacionAtencion))
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 914, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonRegistrarAtencion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonModificarAtencion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonGuardarAtencion)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonEliminarAtencion))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonContactoCliente)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(comboBoxContacto, javax.swing.GroupLayout.PREFERRED_SIZE, 304, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(218, 218, 218)
                                                .addComponent(buttonCerrar))
                                            .addComponent(buttonVerPdf, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(buttonImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(buttonActualizarContactos)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(buttonLimpiarAtencion, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboBoxFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonLimpiar))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(labelIdRotulacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(comboBoxRemitente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(comboBoxRazSocial, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonAgregarDestino)
                    .addComponent(comboBoxDestino, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonNuevoRotulacion)
                    .addComponent(buttonRegistrarRotulacion)
                    .addComponent(buttonModificarRotulacion)
                    .addComponent(buttonGuardarRotulacion)
                    .addComponent(buttonEliminarRotulacion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(labelIdRotulacionAtencion))
                .addGap(8, 8, 8)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonContactoCliente)
                    .addComponent(comboBoxContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonActualizarContactos)
                    .addComponent(buttonLimpiarAtencion)
                    .addComponent(txtDni, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonRegistrarAtencion)
                    .addComponent(buttonModificarAtencion)
                    .addComponent(buttonGuardarAtencion)
                    .addComponent(buttonEliminarAtencion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(buttonVerPdf)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buttonImprimir)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(buttonCerrar)))
                .addGap(19, 19, 19))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
       dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void comboBoxContactoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxContactoItemStateChanged
        if (!"Elegir Contacto".equals(comboContacto)) {
            comboContacto = comboBoxContacto.getSelectedItem().toString();
            System.out.println(comboContacto);
            try {
                consultarContactoCliente(comboContacto,idCliente);
            } catch (Exception ex) {
                Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            comboContacto = "";
        }
    }//GEN-LAST:event_comboBoxContactoItemStateChanged

    private void comboBoxContactoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxContactoActionPerformed
        try {
            consultarContactoCliente(comboContacto, idCliente);
        } catch (Exception ex) {
            Logger.getLogger(Pedidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_comboBoxContactoActionPerformed

    private void comboBoxFiltroItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxFiltroItemStateChanged
       if (comboBoxFiltro.getSelectedItem() == "CLIENTE") {
            filtro = "c.razonSocial";
            listaRotulacion(filtro, txtFiltro.getText());
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "REMITENTE") {
            filtro = "r.remitente";
            listaRotulacion(filtro, txtFiltro.getText());
            System.out.println(filtro);
        } else if (comboBoxFiltro.getSelectedItem() == "DESTINO") {
            filtro = "r.destino";
            listaRotulacion(filtro, txtFiltro.getText());
            System.out.println(filtro);
        }  else if (comboBoxFiltro.getSelectedItem() == "SELECCIONAR") {
            filtro = "c.razonSocial";
            listaRotulacion(filtro, txtFiltro.getText());
            System.out.println(filtro);
        }
    }//GEN-LAST:event_comboBoxFiltroItemStateChanged

    private void txtFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFiltroKeyReleased
        listaRotulacion(filtro, txtFiltro.getText());
    }//GEN-LAST:event_txtFiltroKeyReleased

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        limpiar();
        tablaRotulacion = jTable2.getSelectedRow();
        idRotulacion = Integer.parseInt(jTable2.getValueAt(tablaRotulacion, 0).toString());
        try {
            consultarRotulacion(idRotulacion);
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        listaRotulacionAtencion(idRotulacion);
        habilitarAgregarAtencion();

    }//GEN-LAST:event_jTable2MouseClicked

    private void buttonRegistrarRotulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarRotulacionActionPerformed
        procesar(1);
        listaRotulacion(filtro, txtFiltro.getText());
        try {
            consultarUltimoId();
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            consultarRotulacion(ultimoIdRotulacion);
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarAgregarAtencion();
    }//GEN-LAST:event_buttonRegistrarRotulacionActionPerformed

    private void buttonGuardarRotulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarRotulacionActionPerformed
        procesar(2);
        listaRotulacion(filtro, txtFiltro.getText());
        habilitarGuardarRotulacion();
    }//GEN-LAST:event_buttonGuardarRotulacionActionPerformed

    private void buttonEliminarRotulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarRotulacionActionPerformed
        procesar(3);
        listaRotulacion(filtro, txtFiltro.getText());
        habilitarEliminarRotulacion();
    }//GEN-LAST:event_buttonEliminarRotulacionActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        tablaAtencion=jTable1.getSelectedRow();
        idRotAtencion = Integer.parseInt(jTable1.getValueAt(tablaAtencion, 0).toString());
        try {
            consultarRotulacionAtencion(idRotAtencion);
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarBuscarAtencion();
    }//GEN-LAST:event_jTable1MouseClicked

    private void buttonRegistrarAtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonRegistrarAtencionActionPerformed
        procesarAtencion(1);
        listaRotulacionAtencion(idRotulacion);
        try {
            limpiarContacto();
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarRegistrarAtencion();
        try {
            limpiarContacto();
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonRegistrarAtencionActionPerformed

    private void buttonGuardarAtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarAtencionActionPerformed
        procesarAtencion(2);
        listaRotulacionAtencion(idRotulacion);
        habilitarGuardarAtencion();
    }//GEN-LAST:event_buttonGuardarAtencionActionPerformed

    private void buttonEliminarAtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEliminarAtencionActionPerformed
        procesarAtencion(3);
        listaRotulacionAtencion(idRotulacion);
        habilitarEliminarAtencion();
        try {
            limpiarContacto();
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonEliminarAtencionActionPerformed

    private void buttonContactoClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonContactoClienteActionPerformed
       Clientes.x=idCliente;
        if (Clientes.x != 0) {
            ContactosClientes conCliente = null;
           try {
               conCliente = new ContactosClientes();
           } catch (Exception ex) {
               Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
           }
            Principal.jDesktopPane1.add(conCliente);
            conCliente.toFront();
            conCliente.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(null, "Agregar Cliente");

        }
        habilitarContactoCliente();
        
    }//GEN-LAST:event_buttonContactoClienteActionPerformed

    private void buttonActualizarContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonActualizarContactosActionPerformed
        
        try {
            comboBoxContacto.setModel(contactocontroler.ListarCombodeContacto(razSocial, idCliente));
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarActualizarContactos();

    }//GEN-LAST:event_buttonActualizarContactosActionPerformed

    private void buttonLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarActionPerformed
        limpiar();
        habilitarInicio();
    }//GEN-LAST:event_buttonLimpiarActionPerformed

    private void buttonLimpiarAtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarAtencionActionPerformed
        try {
            consultarRotulacion(idRotulacion);
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            limpiarContacto();
        } catch (Exception ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        habilitarAgregarAtencion();
    }//GEN-LAST:event_buttonLimpiarAtencionActionPerformed

    private void buttonNuevoRotulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonNuevoRotulacionActionPerformed
        habilitarNuevoRotulacion();
    }//GEN-LAST:event_buttonNuevoRotulacionActionPerformed

    private void buttonModificarRotulacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarRotulacionActionPerformed
        habilitarModificarRotulacion();
    }//GEN-LAST:event_buttonModificarRotulacionActionPerformed

    private void buttonModificarAtencionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonModificarAtencionActionPerformed
        habilitarModificarAtencion();
    }//GEN-LAST:event_buttonModificarAtencionActionPerformed

    private void buttonAgregarDestinoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonAgregarDestinoActionPerformed
        SucursalRegistro sucuRe= new SucursalRegistro();
        Principal.jDesktopPane1.add(sucuRe);
        sucuRe.toFront();
        sucuRe.setVisible(true);
    }//GEN-LAST:event_buttonAgregarDestinoActionPerformed

    private void buttonVerPdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonVerPdfActionPerformed
        GenerarReporte rotu = new GenerarReporte();
        try {
            rotu.ReporteRotulacion(idRotulacion);
        } catch (SQLException ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }//GEN-LAST:event_buttonVerPdfActionPerformed

    private void buttonImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonImprimirActionPerformed
       GenerarReporte rotu = new GenerarReporte();
        try {
            rotu.ImprimirRotulacion(idRotulacion);
        } catch (SQLException ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Rotulaciones.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonImprimirActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonActualizarContactos;
    private javax.swing.JButton buttonAgregarDestino;
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonContactoCliente;
    private javax.swing.JButton buttonEliminarAtencion;
    private javax.swing.JButton buttonEliminarRotulacion;
    private javax.swing.JButton buttonGuardarAtencion;
    private javax.swing.JButton buttonGuardarRotulacion;
    private javax.swing.JButton buttonImprimir;
    private javax.swing.JButton buttonLimpiar;
    private javax.swing.JButton buttonLimpiarAtencion;
    private javax.swing.JButton buttonModificarAtencion;
    private javax.swing.JButton buttonModificarRotulacion;
    private javax.swing.JButton buttonNuevoRotulacion;
    private javax.swing.JButton buttonRegistrarAtencion;
    private javax.swing.JButton buttonRegistrarRotulacion;
    private javax.swing.JButton buttonVerPdf;
    private javax.swing.JComboBox<String> comboBoxContacto;
    private javax.swing.JComboBox<String> comboBoxDestino;
    private javax.swing.JComboBox<String> comboBoxFiltro;
    private javax.swing.JComboBox<String> comboBoxRazSocial;
    private javax.swing.JComboBox<String> comboBoxRemitente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable jTable2;
    private javax.swing.JLabel labelIdRotulacion;
    private javax.swing.JLabel labelIdRotulacionAtencion;
    private javax.swing.JTextField txtDni;
    private javax.swing.JTextField txtFiltro;
    // End of variables declaration//GEN-END:variables
    
    RotulacionController rotulacioncontroler = new RotulacionController();
    Rotulacion rotulacion;
    
    RotulacionAtencionController rotulacionatencioncontroler = new RotulacionAtencionController();
    RotulacionAtencion rotulacionatencion;
    
    ClienteController clientecontroler = new ClienteController();
    Cliente cliente;
    
    ContactoClienteController contactocontroler = new ContactoClienteController();
    ContactoCliente contacto;
    
    SucursalController sucursalcontroler= new SucursalController();
    Sucursal sucursal;
    
//------------------------------ Consultar ---------------------------------------- 
    
    
    private void consultarComboCliente(String razonsocial) throws Exception {
        cliente = clientecontroler.ClienteBuscar(razonsocial);
        if (cliente != null) {
            //CREAR ID CLIENTE Y ASIGNARLE EL ID CLIENTE.
            idCliente = cliente.getIdCliente();
            comboBoxRazSocial.setSelectedItem(cliente.getRazonSocial());
         
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no esta registrado");
            //System.out.println("Error");
        }
    }
    
    private void consultarClientePorId(int iddecliente) throws Exception {
        cliente = clientecontroler.ClienteBuscar1(iddecliente);
        if (cliente != null) {
            idCliente = cliente.getIdCliente();
            Clientes.x=cliente.getIdCliente();
            System.out.println("idCliente: "+idCliente);
            razSocial=cliente.getRazonSocial();
            comboBoxRazSocial.setSelectedItem(cliente.getRazonSocial());
        } else {
            JOptionPane.showMessageDialog(null, "Cliente no registrado por ahora");
            //System.out.println("Error");
        }
    }
    
    
    private void consultarContactoCliente(String nombre,int i) throws Exception {

        contacto = contactocontroler.ContactoClienteBuscar1(nombre,i);
        if (contacto != null) {
            idContactoCliente = contacto.getIdContactoCliente();
            System.out.println(idContactoCliente);
            txtDni.setText(contacto.getDni());
            atencion=contacto.getNombres()+" "+contacto.getApellidos()+" D.N.I.:"+contacto.getDni();
            
        } else {
            JOptionPane.showMessageDialog(null, "EL Cliente no tiene contactos, Registre un contacto!!!!");
            //System.out.println("Error");
        }
    }
    
    private void consultarContactoCliente1( int i) throws Exception {

        contacto = contactocontroler.ContactoClienteBuscar(i);
        if (contacto != null) {

            comboContacto = contacto.getNombres() + " " + contacto.getApellidos();
            System.out.println(comboContacto);
            atencion=contacto.getNombres() + " " + contacto.getApellidos()+" D.N.I.:"+contacto.getDni() ;

        } else {
            JOptionPane.showMessageDialog(null, "Contacto no registrado");
            //System.out.println("Error");
        }
    }
     

    void consultarRotulacion(int idR) throws Exception {

        rotulacion = rotulacioncontroler.RotulacionBuscar(idR);
        if (rotulacion != null) {
            idRotulacion = rotulacion.getIdRotulacion();
            labelIdRotulacion.setText(Integer.toString(rotulacion.getIdRotulacion()));
            comboBoxRemitente.setSelectedItem(rotulacion.getRemitente());
            idCliente = rotulacion.getIdCliente();
            Clientes.x=rotulacion.getIdCliente();
            consultarClientePorId(idCliente);
            comboBoxContacto.setModel(contactocontroler.ListarCombodeContacto(razSocial, rotulacion.getIdCliente()));
            comboBoxDestino.setSelectedItem(rotulacion.getDestino());
        } else {
            JOptionPane.showMessageDialog(null, "Pedido no registrado");
        }
    }
    
    void consultarRotulacionAtencion(int idAt) throws Exception {

        rotulacionatencion = rotulacionatencioncontroler.RotulacionAtencionBuscar(idAt);
        if (rotulacionatencion != null) {
            idRotAtencion = rotulacionatencion.getIdRotulacionAtencion();
            labelIdRotulacionAtencion.setText(Integer.toString(rotulacionatencion.getIdRotulacionAtencion()));
            idRotulacion = rotulacionatencion.getIdRotulacion();
            idContactoCliente = rotulacionatencion.getIdContactoCliente();
            consultarContactoCliente1(idContactoCliente);
            //consultar contacto asigna a categoria1 su nombre concatenado
            String testValue1 = comboContacto;
            System.out.println("proforma contacto cliente:" + comboContacto);
            for (int i = 0; i < comboBoxContacto.getModel().getSize(); i++) {
                System.out.println("for 1111");
                if (comboBoxContacto.getItemAt(i).equals(testValue1)) {
                    System.out.println("for222222222");
                    System.out.println("for " + i);
                    comboBoxContacto.setSelectedIndex(i);
                    System.out.println("for 3333333333");
                    break;
                }
            }
            consultarContactoCliente(comboContacto, idCliente);
        } else {
            JOptionPane.showMessageDialog(null, "Pedido no registrado");
        }
    }

    private void consultarSucursalNombre(String cadena) throws Exception {
        sucursal = sucursalcontroler.SucursalBuscarNombre(cadena);
        if (sucursal != null) {

            sucursalNombre=sucursal.getSucursal();
            System.out.println("la sucursal es: "+sucursalNombre);
            

        } else {
            JOptionPane.showMessageDialog(null, "sucursal no registrado");
            //System.out.println("Error");
        }
    }
//-------------------------------- Procesar -----------------------------------------
    
 void procesar(int op) {
        rotulacion = leerDatos();
        try {
                 String msg = rotulacioncontroler.RotulacionProcesar(rotulacion, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    } 
 
 void procesarAtencion(int op) {
        rotulacionatencion = leerDatosAtencion();
        try {
                 String msg = rotulacionatencioncontroler.RotulacionAtencionProcesar(rotulacionatencion, op);
            JOptionPane.showMessageDialog(null, msg);
        } catch (Exception e) {
            //JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("Error" + e.getMessage());
        }
    } 
 //----------------------- Leer Datos ----------------------------------------
 
    Rotulacion leerDatos() {
        Rotulacion rot = new Rotulacion();
        
        rot.setIdRotulacion(idRotulacion);
        rot.setRemitente(comboBoxRemitente.getEditor().getItem().toString());
        rot.setIdCliente(idCliente);
        rot.setDestino(comboBoxDestino.getEditor().getItem().toString());
        
        return rot;
    }
    
    RotulacionAtencion leerDatosAtencion() {
        RotulacionAtencion rotAt = new RotulacionAtencion();
        
        rotAt.setIdRotulacionAtencion(idRotAtencion);
        rotAt.setIdRotulacion(idRotulacion);
        rotAt.setIdContactoCliente(idContactoCliente);
        
        return rotAt;
    }
    
    
//--------------------------------- Limpiar -----------------------------------------
    
    public void limpiarComboCliente() {
        comboBoxRazSocial.setSelectedItem("");
        comboBoxContacto.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
    }
    
//------------------------- Lista Rotulaciones  ---------------------------
    
  void listaRotulacion(String filt, String valor) {
        List<Rotulacion> lista;
        try {
            lista = rotulacioncontroler.RotulacionFiltrar(filt, valor);
            verRotulacion(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verRotulacion(List<Rotulacion> lista) {
       
        DefaultTableModel tabla = (DefaultTableModel) jTable2.getModel();
        tabla.setRowCount(0);
        for (Rotulacion rotu : lista) {
            Object[] fila = {rotu.getIdRotulacion(), rotu.getRazSocial(), rotu.getRemitente(), rotu.getDestino()};
            tabla.addRow(fila);
        }

    }
    
//------------------------- Lista Atencion  ---------------------------
    
  void listaRotulacionAtencion(int idA) {
        List<RotulacionAtencion> lista;
        try {
            lista = rotulacionatencioncontroler.AtencionListar(idA);
            verRotulacionAtencion(lista);

        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, e.getMessage());
            System.out.println("ERROR: " + e.getMessage());
        }
    }

    private void verRotulacionAtencion(List<RotulacionAtencion> lista) {
       
        DefaultTableModel tabla = (DefaultTableModel) jTable1.getModel();
        tabla.setRowCount(0);
        for (RotulacionAtencion rotAt : lista) {
            Object[] fila = {rotAt.getIdRotulacionAtencion(),rotAt.getIdRotulacion(), rotAt.getAtencion()};
            tabla.addRow(fila);
        }

    }  
    
//------------------------------------------- Limpiar -----------------------------------------
    
    void limpiar() {
        
        labelIdRotulacion.setText("0000");
        idRotulacion = 0;
        comboBoxRemitente.setSelectedItem("");
        comboBoxRazSocial.setSelectedItem("");
        comboBoxDestino.setSelectedItem("");
        
        labelIdRotulacionAtencion.setText("0000");
        idRotAtencion=0;
        comboBoxContacto.setModel(new javax.swing.DefaultComboBoxModel(new String[]{}));
        listaRotulacionAtencion(0);
        
    }
    
    void limpiarContacto() throws Exception{
        labelIdRotulacionAtencion.setText("0000");
        comboBoxContacto.setModel(contactocontroler.ListarCombodeContacto(razSocial, idCliente));
        txtDni.setText("");
        idContactoCliente=0;
    
    }

//------------------------------------------- Habilitar ---------------------------------------
    
    void habilitarInicio() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(true);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);
        buttonContactoCliente.setEnabled(false);

        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);

        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarNuevoRotulacion() {

        comboBoxRemitente.setEnabled(true);
        comboBoxRazSocial.setEnabled(true);
        comboBoxDestino.setEnabled(true);
        buttonAgregarDestino.setEnabled(true);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(true);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        txtDni.setEnabled(false);
        
        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);

    }
    
    void habilitarRegistrarRotulacion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(true);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(true);

        buttonContactoCliente.setEnabled(false);
        txtDni.setEnabled(false);
        
        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarBuscarRotulacion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(true);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(true);

        buttonContactoCliente.setEnabled(false);
        txtDni.setEnabled(false);
        
        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(true);
        buttonImprimir.setEnabled(true);
    }
    
    void habilitarModificarRotulacion() {

        comboBoxRemitente.setEnabled(true);
        comboBoxRazSocial.setEnabled(true);
        comboBoxDestino.setEnabled(true);
        buttonAgregarDestino.setEnabled(true);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(true);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        txtDni.setEnabled(false);
        
        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);

    }
    
    void habilitarGuardarRotulacion() {

         comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(true);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(true);

        buttonContactoCliente.setEnabled(false);
        txtDni.setEnabled(false);
        
        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(true);
        buttonImprimir.setEnabled(true);
    }
    
    void habilitarEliminarRotulacion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(true);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        txtDni.setEnabled(false);
        
        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarAgregarAtencion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(true);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(true);

        buttonContactoCliente.setEnabled(true);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(true);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(true);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(true);
        buttonImprimir.setEnabled(true);
    }
    
    void habilitarContactoCliente() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(false);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarActualizarContactos() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(true);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(true);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(true);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarRegistrarAtencion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(true);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(true);
        txtDni.setEnabled(true);
        
        buttonRegistrarAtencion.setEnabled(true);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(true);
        buttonImprimir.setEnabled(true);
    }
    
    void habilitarModificarAtencion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(true);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(true);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(true);
        buttonEliminarAtencion.setEnabled(false);
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarGuardarAtencion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(false);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(true);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(true);
        
        buttonVerPdf.setEnabled(true);
        buttonImprimir.setEnabled(true);
    }
    
    void habilitarEliminarAtencion() {
        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(true);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);  
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarLimpiarAtencion() {
        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        buttonLimpiarAtencion.setEnabled(false);

        comboBoxContacto.setEnabled(false);
        buttonActualizarContactos.setEnabled(true);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(false);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(false);  
        
        buttonVerPdf.setEnabled(false);
        buttonImprimir.setEnabled(false);
    }
    
    void habilitarBuscarAtencion() {

        comboBoxRemitente.setEnabled(false);
        comboBoxRazSocial.setEnabled(false);
        comboBoxDestino.setEnabled(false);
        buttonAgregarDestino.setEnabled(false);

        buttonNuevoRotulacion.setEnabled(false);
        buttonRegistrarRotulacion.setEnabled(false);
        buttonModificarRotulacion.setEnabled(false);
        buttonGuardarRotulacion.setEnabled(false);
        buttonEliminarRotulacion.setEnabled(false);

        buttonContactoCliente.setEnabled(false);
        buttonActualizarContactos.setEnabled(true);
        buttonLimpiarAtencion.setEnabled(true);

        comboBoxContacto.setEnabled(false);
        txtDni.setEnabled(false);
        
        buttonRegistrarAtencion.setEnabled(false);
        buttonModificarAtencion.setEnabled(true);
        buttonGuardarAtencion.setEnabled(false);
        buttonEliminarAtencion.setEnabled(true);
        
        buttonVerPdf.setEnabled(true);
        buttonImprimir.setEnabled(true);
    }
 //--------------------------- Consultar Último Id ---------------------------------
    private void consultarUltimoId() throws Exception {
        rotulacion = rotulacioncontroler.RotulacionBuscarUltimoId();
        if (rotulacion != null) {
            ultimoIdRotulacion= rotulacion.getIdRotulacion();
        }
       
    }
 
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ProformaController;
import entity.Proforma;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ARCRODINPC-06
 */
public class ProformaEstado extends javax.swing.JInternalFrame {

    String comboEstado;
    int valor;//recbe el valor de idProforma
    String valor2;//recibe el valor de codigoProforma
    String razSocial;

    //Proforma
    int idCli;
    int idConCli;
    int idUs;
    String moneda;
    String validez;
    String formPag;
    String tipo;
    String tiemEnt;
    String obs;

    public ProformaEstado(int id, String cod) throws Exception {
        initComponents();
        this.valor = id;
        this.valor2 = cod;

        System.out.println("id ProformaEstado" + valor);
        System.out.println("cod ProformaEstado" + valor2);
        consultarProforma(valor2);

    }

    /**
     * Creates new form ProformaEstado
     */
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
        comboBoxEstado = new javax.swing.JComboBox<>();
        buttonGuardar = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();

        jLabel1.setText("jLabel1");

        setClosable(true);
        setTitle("Modificar el estado de la Cotización");

        jLabel2.setText("ESTADO DE LA COTIZACION:");

        comboBoxEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "SELECCIONAR", "APROBADO", "PENDIENTE", "NO APROBADO", "ANULADO" }));
        comboBoxEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                comboBoxEstadoItemStateChanged(evt);
            }
        });
        comboBoxEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboBoxEstadoActionPerformed(evt);
            }
        });

        buttonGuardar.setText("Guardar");
        buttonGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonGuardarActionPerformed(evt);
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
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 25, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonCerrar)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(141, 141, 141)
                .addComponent(buttonGuardar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(comboBoxEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(buttonGuardar)
                .addGap(26, 26, 26)
                .addComponent(buttonCerrar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
        dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void comboBoxEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboBoxEstadoActionPerformed

    }//GEN-LAST:event_comboBoxEstadoActionPerformed

    private void comboBoxEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_comboBoxEstadoItemStateChanged
        if (comboBoxEstado.getSelectedIndex() != 0) {
            comboEstado = comboBoxEstado.getSelectedItem().toString();
            System.out.println(comboEstado);
        } else {
        }
    }//GEN-LAST:event_comboBoxEstadoItemStateChanged

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
        try {
            procesar(2);
        } catch (Exception ex) {
            Logger.getLogger(ProformaEstado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JComboBox<String> comboBoxEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables

    ProformaController proformacontroler = new ProformaController();
    Proforma proforma;

//---------------------------------- Consultar -----------------------------
    private void consultarProforma(String codigo) throws Exception {
        proforma = proformacontroler.ProformaBuscar(codigo);
        if (proforma != null) {

            idCli = proforma.getIdCliente();
            idConCli = proforma.getIdContactoCliente();
            idUs = proforma.getIdUsuario();
            moneda = proforma.getMoneda();
            validez = proforma.getValidez();
            formPag = proforma.getFormPago();
            tipo = proforma.getTipo();
//            tiemEnt = proforma.getTiempoEntrega();
            obs = proforma.getObservacion();

        } else {
            JOptionPane.showMessageDialog(null, "Proforma no registrada");
            //System.out.println("Error");
        }
    }

//----------------------------------- Procesar -----------------------------------------
    private void procesar(int op) throws Exception {
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

//-------------------------------------- Leer datos ----------------------------------
    private Proforma leerDatos() throws Exception {

        Proforma prof = new Proforma();

        prof.setIdCliente(idCli);
        prof.setIdContactoCliente(idConCli);
        prof.setIdUsuario(idUs);
        prof.setMoneda(moneda);
        prof.setValidez(validez);
        prof.setFormPago(formPag);
        prof.setTipo(tipo);
//        prof.setTiempoEntrega(tiemEnt);
        prof.setEstado(comboEstado);
        prof.setObservacion(obs);
        prof.setIdProforma(valor);

        return prof;
    }

}
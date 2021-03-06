/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.TipoCambioController;
import entity.TipoCambio;
import java.math.BigInteger;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author ARCRODINPC-05
 */
public class PrecioCromadoParaDetalleProforma extends javax.swing.JInternalFrame {

    /**
     * Creates new form CalculoPesos */
   //constante pi
    float pi =  (float) 3.1416;
    
    //Calculo de área
    float diametro, longitud, costo,costoTotal, area;
    float costoSoles, igvD, igvS,ventaD, ventaS;
    float precioVenta;
    String fecha;
    
    //Calculo pesos materiales
    String comboConst;
    String comboTipo;
    
     
    float constAcero=7850;
    float constBronce=8950;
    float constTextolita=1400;
    
    float valor1, valor2, valor3,peso;
    
    float num,nume,deno,resFra,res;
    
    DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();

    
    public PrecioCromadoParaDetalleProforma() {
        separadoresPersonalizados.setDecimalSeparator('.');
        initComponents();
        txtNum.setText("0");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        txtDiametro = new javax.swing.JTextField();
        txtLong = new javax.swing.JTextField();
        txtCosto = new javax.swing.JTextField();
        buttonCalcular = new javax.swing.JButton();
        txtAreadm2 = new javax.swing.JTextField();
        txtCostoDolar = new javax.swing.JTextField();
        txtCostoSoles = new javax.swing.JTextField();
        txtIgvDolar = new javax.swing.JTextField();
        txtIgvSoles = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNum = new javax.swing.JTextField();
        buttonConvertir2 = new javax.swing.JButton();
        txtNumMm = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        buttonLimpiar2 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        buttonLimpiarTodo = new javax.swing.JButton();
        buttonLimpiar3 = new javax.swing.JButton();
        txtVentaDolar = new javax.swing.JTextField();
        txtVentaSoles = new javax.swing.JTextField();
        txtNumerador = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        txtDenominador = new javax.swing.JTextField();
        buttonCostoDolares = new javax.swing.JButton();
        buttonCostoSoles = new javax.swing.JButton();

        setClosable(true);
        setTitle("Cálculo Precio de Cromado");
        setName(""); // NOI18N

        buttonCalcular.setText("Calcular");
        buttonCalcular.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCalcularActionPerformed(evt);
            }
        });

        txtAreadm2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtAreadm2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Diametro (mm)");

        jLabel2.setText("Longitud (mm)");

        jLabel3.setText("costo dm2 ($)");

        jLabel6.setText("Area en dm2");

        jLabel7.setText("Costo ( $ )");

        jLabel8.setText("Costo ( S/. )");

        jLabel10.setText("Conversor de pulgadas a milimetros");

        jLabel11.setText("Ingrese en (in)");

        buttonConvertir2.setText("Convertir");
        buttonConvertir2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConvertir2ActionPerformed(evt);
            }
        });

        txtNumMm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNumMmActionPerformed(evt);
            }
        });

        jLabel12.setText("Salida en (mm)");

        jLabel13.setText("Calculo de costos según área (para piezas circulares) - Costos ya incluyen IGV");

        jLabel15.setText("IGV (18%)");

        jLabel16.setText("Precio Venta");

        buttonLimpiar2.setText("Limpiar");
        buttonLimpiar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiar2ActionPerformed(evt);
            }
        });

        jButton4.setText("Cerrar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        buttonLimpiarTodo.setText("Limpiar Todo");
        buttonLimpiarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiarTodoActionPerformed(evt);
            }
        });

        buttonLimpiar3.setText("Limpiar");
        buttonLimpiar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonLimpiar3ActionPerformed(evt);
            }
        });

        jLabel19.setText("/");

        buttonCostoDolares.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonCostoDolares.setText("Costo Dolares");
        buttonCostoDolares.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCostoDolaresActionPerformed(evt);
            }
        });

        buttonCostoSoles.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        buttonCostoSoles.setText("Costo Soles");
        buttonCostoSoles.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCostoSolesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtLong, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(buttonCalcular, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addGap(83, 83, 83))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(25, 25, 25))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGap(107, 107, 107)))
                                .addComponent(buttonLimpiar3))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(txtDiametro, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(187, 187, 187)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtAreadm2, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 78, Short.MAX_VALUE)
                                        .addGap(164, 164, 164))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(txtCostoDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtIgvDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(txtCostoSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(txtIgvSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(10, 10, 10)
                                .addComponent(txtNumerador, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtDenominador, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonConvertir2)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNumMm, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(buttonLimpiar2))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(buttonLimpiarTodo)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton4))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtVentaSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVentaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(77, 77, 77)))))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(287, 287, 287)
                .addComponent(buttonCostoDolares, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(buttonCostoSoles, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel11)
                        .addComponent(txtNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtNumerador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel19)
                        .addComponent(txtDenominador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonConvertir2)
                        .addComponent(jLabel12)
                        .addComponent(txtNumMm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(buttonLimpiar2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel13)
                .addGap(13, 13, 13)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtDiametro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel1))
                            .addComponent(jLabel16, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtLong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(buttonCalcular)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel7)
                                    .addComponent(txtCostoDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtIgvDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtVentaDolar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(txtCosto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addComponent(buttonLimpiar3))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtCostoSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtIgvSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel8)
                            .addComponent(txtVentaSoles, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(2, 2, 2)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(txtAreadm2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonCostoDolares)
                    .addComponent(buttonCostoSoles))
                .addGap(25, 25, 25)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonLimpiarTodo)
                    .addComponent(jButton4))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtAreadm2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtAreadm2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtAreadm2ActionPerformed

    private void buttonCalcularActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCalcularActionPerformed
        try {
            calculoArea();
        } catch (Exception ex) {
            Logger.getLogger(PrecioCromadoParaDetalleProforma.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_buttonCalcularActionPerformed

    private void txtNumMmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNumMmActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNumMmActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void buttonConvertir2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConvertir2ActionPerformed
        if("".equals(txtNumerador.getText())&& "".equals(txtDenominador.getText())){
            calculo();
            System.out.println("vacio");
        }else{
            calculoFracion();
            System.out.println("no vacio");
        }
    }//GEN-LAST:event_buttonConvertir2ActionPerformed

    private void buttonLimpiar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiar2ActionPerformed
        limpiarConvertir();
    }//GEN-LAST:event_buttonLimpiar2ActionPerformed

    private void buttonLimpiar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiar3ActionPerformed
       limpiarArea();
    }//GEN-LAST:event_buttonLimpiar3ActionPerformed

    private void buttonLimpiarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonLimpiarTodoActionPerformed
        limpiarTodo();
    }//GEN-LAST:event_buttonLimpiarTodoActionPerformed

    private void buttonCostoDolaresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCostoDolaresActionPerformed
       DetalleProformas.precioCromadoDolares();
       dispose();
    }//GEN-LAST:event_buttonCostoDolaresActionPerformed

    private void buttonCostoSolesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCostoSolesActionPerformed
       DetalleProformas.precioCromadoSoles();
       dispose();
    }//GEN-LAST:event_buttonCostoSolesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCalcular;
    private javax.swing.JButton buttonConvertir2;
    private javax.swing.JButton buttonCostoDolares;
    private javax.swing.JButton buttonCostoSoles;
    private javax.swing.JButton buttonLimpiar2;
    private javax.swing.JButton buttonLimpiar3;
    private javax.swing.JButton buttonLimpiarTodo;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField txtAreadm2;
    private javax.swing.JTextField txtCosto;
    public static javax.swing.JTextField txtCostoDolar;
    public static javax.swing.JTextField txtCostoSoles;
    private javax.swing.JTextField txtDenominador;
    private javax.swing.JTextField txtDiametro;
    private javax.swing.JTextField txtIgvDolar;
    private javax.swing.JTextField txtIgvSoles;
    private javax.swing.JTextField txtLong;
    private javax.swing.JTextField txtNum;
    private javax.swing.JTextField txtNumMm;
    private javax.swing.JTextField txtNumerador;
    private javax.swing.JTextField txtVentaDolar;
    private javax.swing.JTextField txtVentaSoles;
    // End of variables declaration//GEN-END:variables

    TipoCambioController tipCamControl = new TipoCambioController();
    TipoCambio tipCam;

    void calculoArea() throws Exception {
        DecimalFormat df = new DecimalFormat("0.00", separadoresPersonalizados);
        diametro = Float.parseFloat(txtDiametro.getText());
        longitud = Float.parseFloat(txtLong.getText());
        area = (diametro * longitud * pi) / 10000;
        System.out.println(area);

        costo = Float.parseFloat(txtCosto.getText());
        txtAreadm2.setText(df.format(area));

        costoTotal = costo * area;
        System.out.println(costoTotal);
        txtCostoDolar.setText(df.format(costoTotal));
       //txtCostoDolar.setText(Float.toString(costoTotal));
        System.out.println("costoD:"+costoTotal);
         igvD = (float) (costoTotal * 0.18);
        txtIgvDolar.setText(df.format(igvD));
        ventaD = (float) (costoTotal+igvD);
        txtVentaDolar.setText(df.format(ventaD));
        System.out.println("IgvD:"+igvD);
        System.out.println("VentaD"+ventaD);
       
        fecha=fechaActual();
        System.out.println(fecha);
        consultarTipoCambio(fecha);
        System.out.println("cambio:"+precioVenta);
        
        costoSoles=costoTotal*precioVenta;
        igvS=(float) (costoSoles*0.18);
        ventaS=(float) (costoSoles+igvS);
        
        txtCostoSoles.setText(df.format(costoSoles));
        txtIgvSoles.setText(df.format(igvS));
        txtVentaSoles.setText(df.format(ventaS));
        
        System.out.println("costoS:"+costoSoles);
        System.out.println("igvS:"+igvS);
        System.out.println("ventaS"+ventaS);
        
    }

    void limpiarArea() {
        
        txtDiametro.setText("");
        txtLong.setText("");
        txtCosto.setText("");
        txtAreadm2.setText("");
        txtCostoDolar.setText("");
        txtIgvDolar.setText("");
        txtVentaDolar.setText("");
        txtCostoSoles.setText("");
        txtIgvSoles.setText("");
        txtVentaSoles.setText("");
    
    
    }
    private void consultarTipoCambio(String fecha) throws Exception {
        tipCam = tipCamControl.TipoCambioBuscarCambio(fecha);
        if (tipCam != null) {

            precioVenta=tipCam.getPrecioVenta();

        } else {
            JOptionPane.showMessageDialog(null, "Operacion Invalida");
            //System.out.println("Error");
        }
    }
    
    public String fechaActual() {
        Date fecha = new Date();

        SimpleDateFormat formatofecha = new SimpleDateFormat("yyyy-MM-dd");
        return formatofecha.format(fecha);
    }
    
  
    
    
     
     void calculoFracion() {
         
        DecimalFormat df = new DecimalFormat("0.00",separadoresPersonalizados);
        String cero;
        num = Float.parseFloat(txtNum.getText());
        nume = Float.parseFloat(txtNumerador.getText());
        deno = Float.parseFloat(txtDenominador.getText());
        cero=Float.toString(num);
        
         resFra = num + (nume / deno);
         res = (float) (resFra * 25.4);
        
     
        txtNumMm.setText(df.format(res));
    }

    void calculo() {

        DecimalFormat df = new DecimalFormat("0.00",separadoresPersonalizados);
        num = Float.parseFloat(txtNum.getText());

        res = (float) (num * 25.4);
        txtNumMm.setText(df.format(res));
 
    }
    void limpiarConvertir(){
        txtNumerador.setText("");
        txtDenominador.setText("");
        txtNum.setText("0");
        txtNumMm.setText("");
    }
    void limpiarTodo(){
        txtDiametro.setText("");
        txtLong.setText("");
        txtCosto.setText("");
        txtAreadm2.setText("");
        txtCostoDolar.setText("");
        txtIgvDolar.setText("");
        txtVentaDolar.setText("");
        txtCostoSoles.setText("");
        txtIgvSoles.setText("");
        txtVentaSoles.setText("");
        txtNumerador.setText("");
        txtDenominador.setText("");
        txtNum.setText("0");
        txtNumMm.setText("");
    
    }
}

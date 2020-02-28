/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controller.ArticuloController;
import entity.Articulo;
import javax.swing.JOptionPane;

/**
 *
 * @author ARCRODINPC-06
 */
public class ArticuloLongitudReal extends javax.swing.JInternalFrame {

    /**
     * Creates new form ArticuloLongitudReal
     */
    String valorCodArt;
    String valorCodUbi;
    String valorLongReal;
    
    
    int idArticulo;
    String codArti;
    String codUbicacion;
    String nombre;
    String nomRed;
    String dia;
    String longReal; //diametroInterno
    String unidadDia;
    String longi;
    String unidadLong;
    int cant;
    String Proce;
      
     
    public ArticuloLongitudReal(String codArt, String codUbi, String longReal) throws Exception {
        initComponents();
        this.valorCodArt=codArt;
        this.valorCodUbi=codUbi;
        this.valorLongReal=longReal;
        consultar(valorCodArt);
        txtUbicacion.setText(valorCodUbi);
        txtLongReal.setText(valorLongReal);
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
        txtUbicacion = new javax.swing.JTextField();
        txtLongReal = new javax.swing.JTextField();
        buttonGuardar = new javax.swing.JButton();
        buttonCerrar = new javax.swing.JButton();

        setClosable(true);
        setTitle("Articulos-Modificar-LongReal y/o Cod. Ubicación");

        jLabel1.setText("Codigo de Ubicación:");

        jLabel2.setText("Longitud Real:");

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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtUbicacion)
                            .addComponent(txtLongReal, javax.swing.GroupLayout.DEFAULT_SIZE, 122, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonCerrar)))
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(139, 139, 139)
                .addComponent(buttonGuardar)
                .addContainerGap(134, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtUbicacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtLongReal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addComponent(buttonGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addComponent(buttonCerrar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonCerrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCerrarActionPerformed
      dispose();
    }//GEN-LAST:event_buttonCerrarActionPerformed

    private void buttonGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonGuardarActionPerformed
//      procesar(2);
    }//GEN-LAST:event_buttonGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonCerrar;
    private javax.swing.JButton buttonGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JTextField txtLongReal;
    private javax.swing.JTextField txtUbicacion;
    // End of variables declaration//GEN-END:variables

    ArticuloController articuloControll =new ArticuloController();
    Articulo articulo;
    
//--------------------- consultar -------------------------------------
    
    private void consultar(String codigo) throws Exception {
        articulo = articuloControll.ArticuloBuscar(codigo);
        if (articulo != null) {

            idArticulo = articulo.getAidArticulo();
            codArti = articulo.getCodigoArticulo();
            codUbicacion = articulo.getCodigoUbicacion();
            nombre = articulo.getNombre();
            nomRed = articulo.getObservacion();
            dia = articulo.getDiametro();
            longReal = articulo.getLongitudReal(); //diametroInterno
            unidadDia = articulo.getUnidadMedidaDia();
            longi = articulo.getLongitud();
            unidadLong = articulo.getUnidadMedidaLong();
            cant = articulo.getCantidad();
            Proce = articulo.getProcedencia();

        } else {
            JOptionPane.showMessageDialog(null, "Articulo no registrado");
            //System.out.println("Error");
        }
    }
    //----------------------- Procesar ---------------------
    
//    private void procesar(int op) {
//       articulo=leerDatos();
//        try {
//            String msg=articuloControll.ArticuloProcesar(articulo, op);
//            JOptionPane.showMessageDialog(null, msg);            
//        } catch (Exception e) {
//            //JOptionPane.showMessageDialog(null, e.getMessage());
//             System.out.println("Error"+e.getMessage());
//        }       
//   }
//    
 //--------------------------- Leer Datos -------------------------------
    
   private Articulo leerDatos() {
        Articulo art = new Articulo();
        
        art.setCodigoArticulo(codArti);
        art.setCodigoUbicacion(codUbicacion);
        art.setNombre(nombre);
        art.setObservacion(nomRed);
        art.setDiametro(dia);
        art.setLongitudReal(txtLongReal.getText());//longitud real
        art.setUnidadMedidaDia(unidadDia);
        art.setLongitud(longi);
        art.setUnidadMedidaLong(unidadLong);
        art.setCantidad(cant);
        art.setProcedencia(Proce);
        
        return art;
    }
}

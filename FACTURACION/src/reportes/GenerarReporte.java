/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reportes;

import database.AccesoDB;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
/*
 *
 * @author ARCRODINPC-06
 */
public class GenerarReporte {

    Connection conexion;

    public void ReporteProforma(int idProforma, String codigo, String fecha, String tipo, String cliente) throws SQLException, ClassNotFoundException, IOException {
        try {
            String urlPdf;

            conexion = AccesoDB.obtener();
//            JasperReport reporte= (JasperReport) JRLoader.loadObject("report3.jasper");

            Map parametro = new HashMap();

            parametro.put("idProforma_fk", idProforma);

//            String dir= System.getProperty("user.dir")+"\\src\\reportes\\reportProforma_1.jrxml";
//            JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
//            JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, parametro, conexion);
            InputStream reporte = getClass().getResourceAsStream("/reportes/reportProforma_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);
            urlPdf = "\\\\ARCRODINPC-01\\Archivos Arcrodin\\AREA VENTAS\\COTIZACIONES\\COTIZACION " + codigo + "-" + fecha + "-" + tipo + "-" + cliente + ".pdf";
            JasperExportManager.exportReportToPdfFile(mostrarReporte, urlPdf);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte: " + e);
        }
    }

    public void ReporteProforma(int idProforma) throws SQLException, ClassNotFoundException, IOException {
        try {
//            String urlPdf;

            conexion = AccesoDB.obtener();
//            JasperReport reporte= (JasperReport) JRLoader.loadObject("report3.jasper");

            Map parametro = new HashMap();

            parametro.put("idProforma_fk", idProforma);

//            String dir= System.getProperty("user.dir")+"\\src\\reportes\\reportProforma_1.jrxml";
//            JasperReport reporteJasper = JasperCompileManager.compileReport(dir);
//            JasperPrint mostrarReporte = JasperFillManager.fillReport(reporteJasper, parametro, conexion);
            InputStream reporte = getClass().getResourceAsStream("/reportes/reportProforma_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);
//            urlPdf="\\\\ARCRODINPC-01\\Archivos Arcrodin 2017\\COTIZACIONES\\COTIZACIONES SISTEMA\\COTIZACION "+codigo+"-"+fecha+"-"+tipo+"-"+cliente+".pdf";
//            JasperExportManager.exportReportToPdfFile(mostrarReporte, urlPdf);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e);
            System.out.println("Error al mostrar el Reporte: " + e);

        }

    }

    public void Imprimir(int idProforma) throws SQLException, ClassNotFoundException {

        //  JasperReport reporte;
        JasperPrint jasperprint;

        try {
            conexion = AccesoDB.obtener();
            Map parametro = new HashMap();
            parametro.put("idProforma_fk", idProforma);

            InputStream reporte = getClass().getResourceAsStream("/reportes/reportProforma_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);

            //se carga el reporte
            //se procesa el archivo jasper
            // jasperprint = JasperFillManager.fillReport(reporte, parametro, conexion);
            //impresion de reporte
            //TRUE : muestra la ventana de dialogo "preferenciade impresion" false imprime directamente con la impresora predeterminada
            JasperPrintManager.printReport(mostrarReporte, true);
            // JasperViewer.viewReport(jasperprint,false);
        } catch (Exception e) {

            JOptionPane.showMessageDialog(null, "Ocurrio un error: " + e);
            System.out.println(e);
        }

    }

    public void ReporteGuia(int idGuia) throws SQLException, ClassNotFoundException {
        try {

            conexion = AccesoDB.obtener();
//            JasperReport reporte= (JasperReport) JRLoader.loadObject("report3.jasper");

            Map parametro = new HashMap();

            parametro.put("idGuia", idGuia);

//            JasperPrint j=JasperFillManager.fillReport(reporte, parametro,conexion);
//            JasperViewer jv= new JasperViewer(j,false);
//            jv.setTitle("Reporte Proforma");
//            jv.setVisible(true);
            //String dir = "C:\\Users\\ARCRODINPC-06\\Desktop\\Trabajo\\29.01.19\\Prueba\\src\\reportes\\report3.jrxml";
            InputStream reporte = getClass().getResourceAsStream("/reportes/ReportGuiaRemision_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }

    }

    public void ImprimirGuia(int idGuia) throws SQLException, ClassNotFoundException {

        //  JasperReport reporte;
        JasperPrint jasperprint;

        try {
            conexion = AccesoDB.obtener();
            Map parametro = new HashMap();

            parametro.put("idGuia", idGuia);
            //se carga el reporte
            InputStream reporte = getClass().getResourceAsStream("/reportes/ReportGuiaRemision_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);
            //impresion de reporte
            //TRUE : muestra la ventana de dialogo "preferenciade impresion" false imprime directamente con la impresora predeterminada
            JasperPrintManager.printReport(mostrarReporte, true);
            // JasperViewer.viewReport(jasperprint,false);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error e");
            System.out.println(e);
        }

    }
    
    public void ReporteCajaChica(String fechIni, String fechFin, String dia1, String dia2, String mes1, String mes2, String anio) throws SQLException, ClassNotFoundException {
        try {
            String urlPdf;
            
            conexion = AccesoDB.obtener();
//            JasperReport reporte= (JasperReport) JRLoader.loadObject("report3.jasper");

            Map parametros = new HashMap();
            
            InputStream subreporte = getClass().getResourceAsStream("/reportes/report3_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            parametros.put("fechaIni", fechIni);
            parametros.put("fechaFin", fechFin);
            parametros.put("SUBREPORT_DIR",subjr);


            InputStream reporte = getClass().getResourceAsStream("/reportes/report3.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);   
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);
            urlPdf = "\\\\ARCRODINPC-01\\Archivos Arcrodin\\AREA CONTABILIDAD Y TESORERIA\\CAJA\\CAJA CHICA "+anio+" "+ dia1 + "." + mes1 + " - " + dia2 + "." + mes2 + ".pdf";
            JasperExportManager.exportReportToPdfFile(mostrarReporte, urlPdf);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }

    }

    public void ReporteRotulacion(int idRotulacion) throws SQLException, ClassNotFoundException {
        try {

            conexion = AccesoDB.obtener();
//            JasperReport reporte= (JasperReport) JRLoader.loadObject("report3.jasper");

            Map parametros = new HashMap();
            
            InputStream subreporte = getClass().getResourceAsStream("/reportes/rotulacion_atencion.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            parametros.put("idRotulacion", idRotulacion);
            parametros.put("SUBREPORT_DIR",subjr);


            InputStream reporte = getClass().getResourceAsStream("/reportes/rotulacion.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);   
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }

    }
    
    
    public void ImprimirRotulacion(int idRotulacion) throws SQLException, ClassNotFoundException {

        //  JasperReport reporte;
        JasperPrint jasperprint;

        try {
            conexion = AccesoDB.obtener();
            Map parametro = new HashMap();
            
            InputStream subreporte = getClass().getResourceAsStream("/reportes/rotulacion_atencion.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            parametro.put("idRotulacion", idRotulacion);
            parametro.put("SUBREPORT_DIR",subjr);
            
            //se carga el reporte
            InputStream reporte = getClass().getResourceAsStream("/reportes/rotulacion.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);
            //impresion de reporte
            //TRUE : muestra la ventana de dialogo "preferenciade impresion" false imprime directamente con la impresora predeterminada
            JasperPrintManager.printReport(mostrarReporte, true);
            // JasperViewer.viewReport(jasperprint,false);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error e");
            System.out.println(e);
        }

    }
    
    public void ReporteOrdenTrabajo(int idOrdenTrabajo) throws SQLException, ClassNotFoundException {
        try {

            conexion = AccesoDB.obtener();

            Map parametros = new HashMap();

            InputStream subreporte = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            InputStream subreporte2 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport2.jrxml");
            JasperDesign disesub2 = JRXmlLoader.load(subreporte2);
            JasperReport subjr2 = JasperCompileManager.compileReport(disesub2);

            InputStream subreporte3 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport3.jrxml");
            JasperDesign disesub3 = JRXmlLoader.load(subreporte3);
            JasperReport subjr3 = JasperCompileManager.compileReport(disesub3);

            InputStream subreporte4 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport4.jrxml");
            JasperDesign disesub4 = JRXmlLoader.load(subreporte4);
            JasperReport subjr4 = JasperCompileManager.compileReport(disesub4);

            parametros.put("idOrdenTrabajo", idOrdenTrabajo);
            parametros.put("SUBREPORT_DIR1", subjr);
            parametros.put("SUBREPORT_DIR2", subjr2);
            parametros.put("SUBREPORT_DIR3", subjr3);
            parametros.put("SUBREPORT_DIR4", subjr4);

            InputStream reporte = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }

    }
    
    public void ReporteOrdenTrabajoGuardar(int idOrdenTrabajo,String  nOt, String fechaAnio, String nCot, String cliente ) throws SQLException, ClassNotFoundException {
        try {
            String urlPdf;
            
            conexion = AccesoDB.obtener();

            Map parametros = new HashMap();

            InputStream subreporte = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            InputStream subreporte2 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport2.jrxml");
            JasperDesign disesub2 = JRXmlLoader.load(subreporte2);
            JasperReport subjr2 = JasperCompileManager.compileReport(disesub2);

            InputStream subreporte3 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport3.jrxml");
            JasperDesign disesub3 = JRXmlLoader.load(subreporte3);
            JasperReport subjr3 = JasperCompileManager.compileReport(disesub3);

            InputStream subreporte4 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport4.jrxml");
            JasperDesign disesub4 = JRXmlLoader.load(subreporte4);
            JasperReport subjr4 = JasperCompileManager.compileReport(disesub4);

            parametros.put("idOrdenTrabajo", idOrdenTrabajo);
            parametros.put("SUBREPORT_DIR1", subjr);
            parametros.put("SUBREPORT_DIR2", subjr2);
            parametros.put("SUBREPORT_DIR3", subjr3);
            parametros.put("SUBREPORT_DIR4", subjr4);

            InputStream reporte = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);
            urlPdf = "\\\\ARCRODINPC-01\\Archivos Arcrodin\\AREA PRODUCCION\\ORDENES DE TRABAJO\\ORDEN DE TRABAJO " + nOt + "-" + fechaAnio + " SEGUN COT " + nCot + "- " + cliente + ".pdf";
            JasperExportManager.exportReportToPdfFile(mostrarReporte, urlPdf);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }  
    }
    
    public void ImprimirOrdenTrabajo(int idOrdenTrabajo) throws SQLException, ClassNotFoundException {

        //  JasperReport reporte;
        JasperPrint jasperprint;

        try {
            conexion = AccesoDB.obtener();

            Map parametros = new HashMap();

            InputStream subreporte = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            InputStream subreporte2 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport2.jrxml");
            JasperDesign disesub2 = JRXmlLoader.load(subreporte2);
            JasperReport subjr2 = JasperCompileManager.compileReport(disesub2);

            InputStream subreporte3 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport3.jrxml");
            JasperDesign disesub3 = JRXmlLoader.load(subreporte3);
            JasperReport subjr3 = JasperCompileManager.compileReport(disesub3);

            InputStream subreporte4 = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1_subreport4.jrxml");
            JasperDesign disesub4 = JRXmlLoader.load(subreporte4);
            JasperReport subjr4 = JasperCompileManager.compileReport(disesub4);

            parametros.put("idOrdenTrabajo", idOrdenTrabajo);
            parametros.put("SUBREPORT_DIR1", subjr);
            parametros.put("SUBREPORT_DIR2", subjr2);
            parametros.put("SUBREPORT_DIR3", subjr3);
            parametros.put("SUBREPORT_DIR4", subjr4);

            InputStream reporte = getClass().getResourceAsStream("/reportes/OrdenTrabajoPrueba_1.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);

            JasperPrintManager.printReport(mostrarReporte, true);
            
        } catch (ClassNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error e");
            System.out.println(e);
        }

    }
    
    public void ReporteOrdenCompraGuardar(int idOrdenCompra, String  nOC, String fechaEmision, String proveedor, String docExt ) throws SQLException, ClassNotFoundException {
        try {
            String urlPdf;
            
            conexion = AccesoDB.obtener();

            Map parametros = new HashMap();

            InputStream subreporte = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            InputStream subreporte2 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport2.jrxml");
            JasperDesign disesub2 = JRXmlLoader.load(subreporte2);
            JasperReport subjr2 = JasperCompileManager.compileReport(disesub2);

            InputStream subreporte3 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport3.jrxml");
            JasperDesign disesub3 = JRXmlLoader.load(subreporte3);
            JasperReport subjr3 = JasperCompileManager.compileReport(disesub3);

            InputStream subreporte4 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport4.jrxml");
            JasperDesign disesub4 = JRXmlLoader.load(subreporte4);
            JasperReport subjr4 = JasperCompileManager.compileReport(disesub4);

            InputStream subreporte5 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport5.jrxml");
            JasperDesign disesub5 = JRXmlLoader.load(subreporte5);
            JasperReport subjr5 = JasperCompileManager.compileReport(disesub5);

            parametros.put("idOrdenCompra", idOrdenCompra);
            parametros.put("SUBREPORT_DIR1", subjr);
            parametros.put("SUBREPORT_DIR2", subjr2);
            parametros.put("SUBREPORT_DIR3", subjr3);
            parametros.put("SUBREPORT_DIR4", subjr4);
            parametros.put("SUBREPORT_DIR5", subjr5);

            InputStream reporte = getClass().getResourceAsStream("/reportes/OrdenCompraReport.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);
            urlPdf = "\\\\ARCRODINPC-01\\Archivos Arcrodin\\AREA COMPRAS\\ORDENES DE COMPRA\\ORDEN DE COMPRA " + nOC + "- " + fechaEmision+ "- " + proveedor + "- " + docExt + ".pdf";
            JasperExportManager.exportReportToPdfFile(mostrarReporte, urlPdf);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }  
    }
    
    public void ReporteOrdenCompra(int idOrdenCompra) throws SQLException, ClassNotFoundException {
        try {

            conexion = AccesoDB.obtener();

            Map parametros = new HashMap();

            InputStream subreporte = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            InputStream subreporte2 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport2.jrxml");
            JasperDesign disesub2 = JRXmlLoader.load(subreporte2);
            JasperReport subjr2 = JasperCompileManager.compileReport(disesub2);

            InputStream subreporte3 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport3.jrxml");
            JasperDesign disesub3 = JRXmlLoader.load(subreporte3);
            JasperReport subjr3 = JasperCompileManager.compileReport(disesub3);

            InputStream subreporte4 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport4.jrxml");
            JasperDesign disesub4 = JRXmlLoader.load(subreporte4);
            JasperReport subjr4 = JasperCompileManager.compileReport(disesub4);

            InputStream subreporte5 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport5.jrxml");
            JasperDesign disesub5 = JRXmlLoader.load(subreporte5);
            JasperReport subjr5 = JasperCompileManager.compileReport(disesub5);

            parametros.put("idOrdenCompra", idOrdenCompra);
            parametros.put("SUBREPORT_DIR1", subjr);
            parametros.put("SUBREPORT_DIR2", subjr2);
            parametros.put("SUBREPORT_DIR3", subjr3);
            parametros.put("SUBREPORT_DIR4", subjr4);
            parametros.put("SUBREPORT_DIR5", subjr5);

            InputStream reporte = getClass().getResourceAsStream("/reportes/OrdenCompraReport.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);
            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }

    }
    
    public void ImprimirOrdenCompra(int idOrdenCompra) throws SQLException, ClassNotFoundException {

        //  JasperReport reporte;
        JasperPrint jasperprint;

        try {
            conexion = AccesoDB.obtener();

            Map parametros = new HashMap();

            InputStream subreporte = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport1.jrxml");
            JasperDesign disesub = JRXmlLoader.load(subreporte);
            JasperReport subjr = JasperCompileManager.compileReport(disesub);

            InputStream subreporte2 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport2.jrxml");
            JasperDesign disesub2 = JRXmlLoader.load(subreporte2);
            JasperReport subjr2 = JasperCompileManager.compileReport(disesub2);

            InputStream subreporte3 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport3.jrxml");
            JasperDesign disesub3 = JRXmlLoader.load(subreporte3);
            JasperReport subjr3 = JasperCompileManager.compileReport(disesub3);

            InputStream subreporte4 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport4.jrxml");
            JasperDesign disesub4 = JRXmlLoader.load(subreporte4);
            JasperReport subjr4 = JasperCompileManager.compileReport(disesub4);

            InputStream subreporte5 = getClass().getResourceAsStream("/reportes/OrdenCompraReport_subreport5.jrxml");
            JasperDesign disesub5 = JRXmlLoader.load(subreporte5);
            JasperReport subjr5 = JasperCompileManager.compileReport(disesub5);

            parametros.put("idOrdenCompra", idOrdenCompra);
            parametros.put("SUBREPORT_DIR1", subjr);
            parametros.put("SUBREPORT_DIR2", subjr2);
            parametros.put("SUBREPORT_DIR3", subjr3);
            parametros.put("SUBREPORT_DIR4", subjr4);
            parametros.put("SUBREPORT_DIR5", subjr5);

            InputStream reporte = getClass().getResourceAsStream("/reportes/OrdenCompraReport.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);

            JasperPrintManager.printReport(mostrarReporte, true);
            
        } catch (ClassNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error e");
            System.out.println(e);
        }

    }
    
    public void ReporteCuentasCobrar(String razSocial, String estado) throws SQLException, ClassNotFoundException {
        try {
            String urlPdf;

            conexion = AccesoDB.obtener();
//            JasperReport reporte= (JasperReport) JRLoader.loadObject("report3.jasper");

            Map parametros = new HashMap();

            parametros.put("razSocial", razSocial);
            parametros.put("estado", estado);

            InputStream reporte = getClass().getResourceAsStream("/reportes/reportDeudaPrueba.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametros, conexion);

            JasperViewer.viewReport(mostrarReporte, false);

        } catch (JRException e) {

            System.out.println("Error al mostrar el Reporte" + e);

        }

    }
    
    public void ImprimirCuentasCobrar(String razSocial, String estado) throws SQLException, ClassNotFoundException {

        //  JasperReport reporte;
        JasperPrint jasperprint;

        try {
            conexion = AccesoDB.obtener();
            Map parametro = new HashMap();

            parametro.put("razSocial", razSocial);
            parametro.put("estado", estado);
            //se carga el reporte
            InputStream reporte = getClass().getResourceAsStream("/reportes/reportDeudaPrueba.jrxml");
            JasperDesign dise = JRXmlLoader.load(reporte);
            JasperReport jr = JasperCompileManager.compileReport(dise);
            JasperPrint mostrarReporte = JasperFillManager.fillReport(jr, parametro, conexion);
            //impresion de reporte
            //TRUE : muestra la ventana de dialogo "preferenciade impresion" false imprime directamente con la impresora predeterminada
            JasperPrintManager.printReport(mostrarReporte, true);
            // JasperViewer.viewReport(jasperprint,false);
        } catch (ClassNotFoundException | SQLException | JRException e) {
            JOptionPane.showMessageDialog(null, "Ocurrio un error e");
            System.out.println(e);
        }

    }

}


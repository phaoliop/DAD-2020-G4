CREATE DATABASE  IF NOT EXISTS `dbgestion` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `dbgestion`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: dbgestion
-- ------------------------------------------------------
-- Server version	5.7.22-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `articulo`
--

DROP TABLE IF EXISTS `articulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulo` (
  `idArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `idInventariofk` int(11) NOT NULL,
  `codigoArticulo` varchar(11) DEFAULT NULL,
  `codigoUbicacion` varchar(11) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `diametro` varchar(20) DEFAULT NULL,
  `diametroPulg` varchar(27) DEFAULT NULL,
  `unidadMedidaDiam` varchar(5) DEFAULT NULL,
  `longitud` varchar(20) DEFAULT NULL,
  `longitudReal` varchar(20) DEFAULT NULL,
  `unidadMedidaLong` varchar(5) DEFAULT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `procedencia` varchar(85) DEFAULT NULL,
  `observacion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idArticulo`),
  UNIQUE KEY `idArticulo_UNIQUE` (`idArticulo`),
  KEY `idInventariofk_idx` (`idInventariofk`),
  CONSTRAINT `idInventariofk` FOREIGN KEY (`idInventariofk`) REFERENCES `inventario` (`idInventario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=483 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `articulosproveedores`
--

DROP TABLE IF EXISTS `articulosproveedores`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `articulosproveedores` (
  `idArticulosProveedores` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` text NOT NULL,
  `campo1` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idArticulosProveedores`),
  UNIQUE KEY `idArticuloProveedor_UNIQUE` (`idArticulosProveedores`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cajachica`
--

DROP TABLE IF EXISTS `cajachica`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cajachica` (
  `idCajaChica` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCaja` date DEFAULT NULL,
  `descripcionCaja` varchar(200) DEFAULT NULL,
  `egreso` varchar(12) DEFAULT NULL,
  `ingreso` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`idCajaChica`)
) ENGINE=InnoDB AUTO_INCREMENT=164 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `cliente`
--

DROP TABLE IF EXISTS `cliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `cliente` (
  `idCliente` int(11) NOT NULL AUTO_INCREMENT,
  `codigoCliente` varchar(11) DEFAULT NULL,
  `razonSocial` varchar(250) NOT NULL,
  `ruc` varchar(12) DEFAULT NULL,
  `tipo` varchar(2) DEFAULT NULL,
  `direccion` text,
  `obserCliente` text,
  PRIMARY KEY (`idCliente`),
  UNIQUE KEY `idCliente_UNIQUE` (`idCliente`)
) ENGINE=InnoDB AUTO_INCREMENT=716 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `comprobantesunat`
--

DROP TABLE IF EXISTS `comprobantesunat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comprobantesunat` (
  `idComprobanteSunat` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) DEFAULT NULL,
  `codigo` varchar(6) DEFAULT NULL,
  PRIMARY KEY (`idComprobanteSunat`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contactocliente`
--

DROP TABLE IF EXISTS `contactocliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactocliente` (
  `idContactoCliente` int(11) NOT NULL AUTO_INCREMENT,
  `idCliente_fk` int(11) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(80) DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `telefono1` varchar(15) DEFAULT NULL,
  `telefono2` varchar(15) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `sucursal` varchar(45) DEFAULT NULL,
  `obserContactoCliente` text,
  PRIMARY KEY (`idContactoCliente`),
  UNIQUE KEY `idContactoCliente_UNIQUE` (`idContactoCliente`),
  KEY `idCliente_idx` (`idCliente_fk`),
  CONSTRAINT `idCliente_fk` FOREIGN KEY (`idCliente_fk`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=396 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `contactoproveedor`
--

DROP TABLE IF EXISTS `contactoproveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `contactoproveedor` (
  `idContactoProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `idProveedor_fk` int(11) NOT NULL,
  `dni` varchar(9) DEFAULT NULL,
  `nombres` varchar(70) NOT NULL,
  `apellidos` varchar(80) DEFAULT NULL,
  `cargo` varchar(50) DEFAULT NULL,
  `correo` varchar(70) DEFAULT NULL,
  `telefono1` varchar(15) DEFAULT NULL,
  `telefono2` varchar(15) DEFAULT NULL,
  `sucursal` varchar(45) NOT NULL,
  `ObsContactoProveedor` text,
  PRIMARY KEY (`idContactoProveedor`),
  UNIQUE KEY `idContactoProveedor_UNIQUE` (`idContactoProveedor`),
  KEY `idProveedor _idx` (`idProveedor_fk`),
  CONSTRAINT `idProveedor ` FOREIGN KEY (`idProveedor_fk`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `control`
--

DROP TABLE IF EXISTS `control`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `control` (
  `c_parametro` varchar(20) NOT NULL,
  `c_valor` int(6) DEFAULT NULL,
  `c_anio` int(4) DEFAULT NULL,
  PRIMARY KEY (`c_parametro`),
  UNIQUE KEY `c_parametro_UNIQUE` (`c_parametro`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleguiaremision`
--

DROP TABLE IF EXISTS `detalleguiaremision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalleguiaremision` (
  `idDetalleGuiaRemision` int(11) NOT NULL AUTO_INCREMENT,
  `idGuiaRemisionfk` int(11) NOT NULL,
  `cant` varchar(12) DEFAULT NULL,
  `codigo` varchar(45) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `unidadMedida` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDetalleGuiaRemision`),
  UNIQUE KEY `idDetalleGuiaRemision_UNIQUE` (`idDetalleGuiaRemision`),
  KEY `idGuiaRemisionfk_idx` (`idGuiaRemisionfk`),
  CONSTRAINT `idGuiaRemisionfk` FOREIGN KEY (`idGuiaRemisionfk`) REFERENCES `guiaremision` (`idGuiaRemision`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=649 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleordencompra`
--

DROP TABLE IF EXISTS `detalleordencompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalleordencompra` (
  `doc_id` int(11) NOT NULL AUTO_INCREMENT,
  `doc_oc_id` int(11) NOT NULL,
  `doc_item` varchar(6) DEFAULT NULL,
  `doc_cantidad` varchar(6) DEFAULT NULL,
  `doc_desc` varchar(300) DEFAULT NULL,
  `doc_detalleDesc` varchar(300) DEFAULT NULL,
  `doc_precioUnit` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`doc_id`),
  KEY `doc_oc_id_idx` (`doc_oc_id`),
  CONSTRAINT `doc_oc_id` FOREIGN KEY (`doc_oc_id`) REFERENCES `ordencompra` (`oc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleordencorte`
--

DROP TABLE IF EXISTS `detalleordencorte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalleordencorte` (
  `idDetalleOrdenCorte` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenCortefk` int(11) NOT NULL,
  `cantidadOrden` int(11) NOT NULL,
  `diametroDet` varchar(45) NOT NULL,
  `idArticulofk` int(11) NOT NULL,
  `longitudOrden` varchar(45) NOT NULL,
  PRIMARY KEY (`idDetalleOrdenCorte`),
  UNIQUE KEY `idDetalleOrdenCorte_UNIQUE` (`idDetalleOrdenCorte`),
  KEY `idArticulofk_idx` (`idArticulofk`),
  KEY `idOrdenCortefk` (`idOrdenCortefk`),
  CONSTRAINT `idArticulofk` FOREIGN KEY (`idArticulofk`) REFERENCES `articulo` (`idArticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idOrdenCortefk` FOREIGN KEY (`idOrdenCortefk`) REFERENCES `ordencorte` (`idOrdenCorte`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=202 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleordentrabajo`
--

DROP TABLE IF EXISTS `detalleordentrabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalleordentrabajo` (
  `idDetalleOrdenTrabajo` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenTrabajofk` int(11) NOT NULL,
  `item` varchar(8) DEFAULT NULL,
  `cant` varchar(8) DEFAULT NULL,
  `descripcion` varchar(160) DEFAULT NULL,
  `tolerancia` varchar(35) DEFAULT NULL,
  `campo1` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`idDetalleOrdenTrabajo`),
  KEY `idOrdenTrabajofk_idx` (`idOrdenTrabajofk`),
  CONSTRAINT `idOrdenTrabajofk` FOREIGN KEY (`idOrdenTrabajofk`) REFERENCES `ordendetrabajo` (`idOrdenDeTrabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=88 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detallepedido`
--

DROP TABLE IF EXISTS `detallepedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detallepedido` (
  `idDetallePedido` int(11) NOT NULL AUTO_INCREMENT,
  `fk_idPedido` int(11) NOT NULL,
  `item` varchar(12) DEFAULT NULL,
  `cantidad` varchar(12) DEFAULT NULL,
  `descripcion` text,
  `detalleDescrip` text,
  `precioUnitario` varchar(15) DEFAULT NULL,
  PRIMARY KEY (`idDetallePedido`),
  UNIQUE KEY `idDetallePedido_UNIQUE` (`idDetallePedido`),
  KEY `idPedido_idx` (`fk_idPedido`),
  CONSTRAINT `fk_idPedido` FOREIGN KEY (`fk_idPedido`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=345 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `detalleproforma`
--

DROP TABLE IF EXISTS `detalleproforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `detalleproforma` (
  `idDetalleProforma` int(11) NOT NULL AUTO_INCREMENT,
  `idProforma_fk` int(11) NOT NULL,
  `item` varchar(8) NOT NULL,
  `cantidad` varchar(11) NOT NULL,
  `descripcion` text NOT NULL,
  `detalleDescrip` text,
  `precioUnitario` varchar(20) NOT NULL,
  PRIMARY KEY (`idDetalleProforma`),
  UNIQUE KEY `idDetalleProforma_UNIQUE` (`idDetalleProforma`),
  KEY `idProforma_idx` (`idProforma_fk`),
  CONSTRAINT `idProforma_fk` FOREIGN KEY (`idProforma_fk`) REFERENCES `proforma` (`idProforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6750 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `direccionanexaprov`
--

DROP TABLE IF EXISTS `direccionanexaprov`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccionanexaprov` (
  `idDireccionAnexaProv` int(11) NOT NULL AUTO_INCREMENT,
  `fkidProveedor` int(11) NOT NULL,
  `direccion` tinytext NOT NULL,
  `observacion` tinytext,
  `campo1` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDireccionAnexaProv`),
  UNIQUE KEY `idDireccionAnexaProv_UNIQUE` (`idDireccionAnexaProv`),
  KEY `fkidProveedor_idx` (`fkidProveedor`),
  CONSTRAINT `fkidProveedor` FOREIGN KEY (`fkidProveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `direccionesanexascliente`
--

DROP TABLE IF EXISTS `direccionesanexascliente`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `direccionesanexascliente` (
  `idDireccionesAnexasCliente` int(11) NOT NULL AUTO_INCREMENT,
  `idClientefkk` int(11) NOT NULL,
  `direccion` tinytext NOT NULL,
  `observacion` tinytext,
  `campo1` varchar(45) DEFAULT NULL,
  `campo2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idDireccionesAnexasCliente`),
  UNIQUE KEY `idDireccionesAnexasCliente_UNIQUE` (`idDireccionesAnexasCliente`),
  KEY `idClientefkk_idx` (`idClientefkk`),
  CONSTRAINT `idClientefkk` FOREIGN KEY (`idClientefkk`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `formadepago`
--

DROP TABLE IF EXISTS `formadepago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `formadepago` (
  `idFormaDePago` int(11) NOT NULL AUTO_INCREMENT,
  `descripcion` varchar(100) NOT NULL,
  `dias` int(11) NOT NULL,
  PRIMARY KEY (`idFormaDePago`),
  UNIQUE KEY `idFormaDePago_UNIQUE` (`idFormaDePago`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `glosa`
--

DROP TABLE IF EXISTS `glosa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `glosa` (
  `idGlosa` int(11) NOT NULL AUTO_INCREMENT,
  `concepto` varchar(150) DEFAULT NULL,
  `codigo` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`idGlosa`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `guiaremision`
--

DROP TABLE IF EXISTS `guiaremision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `guiaremision` (
  `idGuiaRemision` int(11) NOT NULL AUTO_INCREMENT,
  `serieGuia` varchar(45) NOT NULL,
  `numGuia` varchar(45) NOT NULL,
  `fechaEmision` date NOT NULL,
  `fechaTraslado` date DEFAULT NULL,
  `direccionPartida` varchar(250) DEFAULT NULL,
  `direccionLlegada` varchar(250) DEFAULT NULL,
  `fkidCliente` int(11) NOT NULL,
  `tipoDocCli` varchar(45) DEFAULT NULL,
  `fkidmotivoGuia` int(11) NOT NULL,
  `motivoDescripcion` varchar(45) DEFAULT NULL,
  `vehMarca` varchar(150) DEFAULT NULL,
  `vehCertificado` varchar(45) DEFAULT NULL,
  `vehLicencia` varchar(45) DEFAULT NULL,
  `nombreTransp` varchar(200) DEFAULT NULL,
  `rucTransp` varchar(45) DEFAULT NULL,
  `tipoComprobante` varchar(70) DEFAULT NULL,
  `numComprobante` varchar(45) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `observacion` text,
  `numPedido` varchar(8) DEFAULT NULL,
  PRIMARY KEY (`idGuiaRemision`),
  UNIQUE KEY `idGuiaRemision_UNIQUE` (`idGuiaRemision`),
  KEY `fkidCliente_idx` (`fkidCliente`),
  KEY `fkidMotivoTraslado_idx` (`fkidmotivoGuia`),
  CONSTRAINT `fkidCliente` FOREIGN KEY (`fkidCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fkidMotivoGuia` FOREIGN KEY (`fkidmotivoGuia`) REFERENCES `motivoguia` (`idMotivoGuia`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `inventario`
--

DROP TABLE IF EXISTS `inventario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `inventario` (
  `idInventario` int(11) NOT NULL AUTO_INCREMENT,
  `fechaRealizado` date DEFAULT NULL,
  `fechaRegistro` date DEFAULT NULL,
  `encargado` varchar(200) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observacion` tinytext,
  `campo1` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idInventario`),
  UNIQUE KEY `idInventario_UNIQUE` (`idInventario`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `listaprecio`
--

DROP TABLE IF EXISTS `listaprecio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `listaprecio` (
  `idListaPrecio` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) NOT NULL,
  `diametro` varchar(150) NOT NULL,
  `unidadDiametro` varchar(15) NOT NULL,
  `precio` float NOT NULL,
  `tipoCod` varchar(12) NOT NULL,
  PRIMARY KEY (`idListaPrecio`),
  UNIQUE KEY `idListaPrecio_UNIQUE` (`idListaPrecio`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medidasingreso`
--

DROP TABLE IF EXISTS `medidasingreso`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medidasingreso` (
  `idMedidasIngreso` int(11) NOT NULL AUTO_INCREMENT,
  `fkidOrdenTrabajo` int(11) NOT NULL,
  `medida` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idMedidasIngreso`),
  KEY `fkidOrdenTrabajo_idx` (`fkidOrdenTrabajo`),
  CONSTRAINT `fkidOrdenTrabajo` FOREIGN KEY (`fkidOrdenTrabajo`) REFERENCES `ordendetrabajo` (`idOrdenDeTrabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `medidassalida`
--

DROP TABLE IF EXISTS `medidassalida`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `medidassalida` (
  `idMedidasSalida` int(11) NOT NULL AUTO_INCREMENT,
  `idOrdenTrabajo_fk` int(11) NOT NULL,
  `medida` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`idMedidasSalida`),
  KEY `idOrdenTrabajo_fk_idx` (`idOrdenTrabajo_fk`),
  CONSTRAINT `idOrdenTrabajo_fk` FOREIGN KEY (`idOrdenTrabajo_fk`) REFERENCES `ordendetrabajo` (`idOrdenDeTrabajo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `motivoguia`
--

DROP TABLE IF EXISTS `motivoguia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `motivoguia` (
  `idMotivoGuia` int(11) NOT NULL AUTO_INCREMENT,
  `motivo` varchar(150) DEFAULT NULL,
  `campo1` varchar(2) DEFAULT NULL,
  PRIMARY KEY (`idMotivoGuia`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordencompra`
--

DROP TABLE IF EXISTS `ordencompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordencompra` (
  `oc_id` int(11) NOT NULL AUTO_INCREMENT,
  `oc_num` int(11) NOT NULL,
  `oc_fecha` date DEFAULT NULL,
  `oc_idProveedor_fk` int(11) NOT NULL,
  `oc_idContactoProv_fk` int(11) NOT NULL,
  `oc_moneda` varchar(20) DEFAULT NULL,
  `oc_formaPago` varchar(70) DEFAULT NULL,
  `oc_tiempoEntrega` varchar(30) DEFAULT NULL,
  `oc_fechaEntrega` date DEFAULT NULL,
  `oc_docExterno` varchar(45) DEFAULT NULL,
  `oc_idUsuario_fk` int(11) NOT NULL,
  `oc_lugarEntrega` varchar(200) DEFAULT NULL,
  `oc_estado` varchar(25) DEFAULT NULL,
  `oc_observacion` varchar(200) DEFAULT NULL,
  `oc_campoAdd2` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`oc_id`),
  UNIQUE KEY `oc_id_UNIQUE` (`oc_id`),
  KEY `oc_idProveedor_fk_idx` (`oc_idProveedor_fk`),
  KEY `oc_idContactoProv_fk_idx` (`oc_idContactoProv_fk`),
  KEY `oc_idUsuario_fk_idx` (`oc_idUsuario_fk`),
  CONSTRAINT `oc_idContactoProv_fk` FOREIGN KEY (`oc_idContactoProv_fk`) REFERENCES `contactoproveedor` (`idContactoProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `oc_idProveedor_fk` FOREIGN KEY (`oc_idProveedor_fk`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `oc_idUsuario_fk` FOREIGN KEY (`oc_idUsuario_fk`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordencorte`
--

DROP TABLE IF EXISTS `ordencorte`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordencorte` (
  `idOrdenCorte` int(11) NOT NULL AUTO_INCREMENT,
  `idPedidofk` int(11) NOT NULL,
  `numOrdCort` varchar(45) NOT NULL,
  `fecha` date NOT NULL,
  `motivo` varchar(45) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `observacion` text,
  PRIMARY KEY (`idOrdenCorte`),
  UNIQUE KEY `idOrdenCorte_UNIQUE` (`idOrdenCorte`),
  KEY `idPedidofk_idx` (`idPedidofk`),
  CONSTRAINT `idPedidofk` FOREIGN KEY (`idPedidofk`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=144 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `ordendetrabajo`
--

DROP TABLE IF EXISTS `ordendetrabajo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ordendetrabajo` (
  `idOrdenDeTrabajo` int(11) NOT NULL AUTO_INCREMENT,
  `idPedidofkk` int(11) NOT NULL,
  `idCotizacion` int(11) NOT NULL,
  `numOrdenTrabajo` varchar(8) DEFAULT NULL,
  `tipoServicio` varchar(90) DEFAULT NULL,
  `recepcion` varchar(45) DEFAULT NULL,
  `destino` varchar(90) DEFAULT NULL,
  `fechaEmision` date DEFAULT NULL,
  `fechaInicio` date DEFAULT NULL,
  `fechaFinal` date DEFAULT NULL,
  `fechaEntrega` date DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observacion` varchar(250) DEFAULT NULL,
  `medidasIngreso` tinytext,
  `medidasFinaliza` tinytext,
  PRIMARY KEY (`idOrdenDeTrabajo`),
  KEY `idPedidofkk_idx` (`idPedidofkk`),
  KEY `idCotizacion_idx` (`idCotizacion`),
  CONSTRAINT `idCotizacion` FOREIGN KEY (`idCotizacion`) REFERENCES `proforma` (`idProforma`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idPedidofkk` FOREIGN KEY (`idPedidofkk`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pedido` (
  `idPedido` int(11) NOT NULL AUTO_INCREMENT,
  `numPedido` int(11) NOT NULL,
  `idProforma_fk` int(11) NOT NULL,
  `idClientefk` int(11) NOT NULL,
  `fk_idContactoCliente` int(11) NOT NULL,
  `fk_idUsuario` int(11) NOT NULL,
  `moneda` varchar(45) NOT NULL,
  `formaPago` tinytext NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `fechaPedido` date NOT NULL,
  `dia1` int(11) NOT NULL,
  `dia2` int(11) NOT NULL,
  `ordenCompra` varchar(100) DEFAULT NULL,
  `estado` varchar(70) DEFAULT NULL,
  `observacion` text,
  `observacionInterna` tinytext,
  `campo2` tinytext,
  `campo3` tinytext,
  PRIMARY KEY (`idPedido`),
  UNIQUE KEY `idPedido_UNIQUE` (`idPedido`),
  KEY `idProfroma_idx` (`idProforma_fk`),
  KEY `idCliente_idx` (`idClientefk`),
  KEY `fk_idUsuario_idx` (`fk_idUsuario`),
  KEY `fk_idContactoCliente_idx` (`fk_idContactoCliente`),
  CONSTRAINT `fk_idContactoCliente` FOREIGN KEY (`fk_idContactoCliente`) REFERENCES `contactocliente` (`idContactoCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_idUsuario` FOREIGN KEY (`fk_idUsuario`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idClientefk` FOREIGN KEY (`idClientefk`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idProfroma_fk` FOREIGN KEY (`idProforma_fk`) REFERENCES `proforma` (`idProforma`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=216 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `precio`
--

DROP TABLE IF EXISTS `precio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `precio` (
  `idPrecio` int(11) NOT NULL AUTO_INCREMENT,
  `idArticulo_fk` int(11) NOT NULL,
  `fechaPrecio` date NOT NULL,
  `precioVenta` float NOT NULL,
  PRIMARY KEY (`idPrecio`),
  UNIQUE KEY `idPrecio_UNIQUE` (`idPrecio`),
  KEY `idArticulo_idx` (`idArticulo_fk`),
  CONSTRAINT `idArticulo_fk` FOREIGN KEY (`idArticulo_fk`) REFERENCES `articulo` (`idArticulo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proforma`
--

DROP TABLE IF EXISTS `proforma`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proforma` (
  `idProforma` int(11) NOT NULL AUTO_INCREMENT,
  `codProforma` varchar(12) NOT NULL,
  `fk_idCliente` int(11) NOT NULL,
  `idContactoCliente_fk` int(11) NOT NULL,
  `idUsuario_fk` int(11) NOT NULL,
  `moneda` varchar(45) NOT NULL,
  `validez` varchar(45) NOT NULL,
  `formaPago` text NOT NULL,
  `tipo` varchar(45) NOT NULL,
  `detraccion` varchar(250) DEFAULT NULL,
  `fechaEmision` date NOT NULL,
  `dia1` int(11) DEFAULT NULL,
  `dia2` int(11) DEFAULT NULL,
  `estado` varchar(45) DEFAULT NULL,
  `observacion` text,
  `observacionInterna` text,
  PRIMARY KEY (`idProforma`),
  UNIQUE KEY `idProforma_UNIQUE` (`idProforma`),
  KEY `idUsuario_idx` (`idUsuario_fk`),
  KEY `idContactoCliente_idx` (`idContactoCliente_fk`),
  KEY `idCliente_idx` (`fk_idCliente`),
  CONSTRAINT `fk_idCliente` FOREIGN KEY (`fk_idCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idContactoCliente_fk` FOREIGN KEY (`idContactoCliente_fk`) REFERENCES `contactocliente` (`idContactoCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idUsuario_fk` FOREIGN KEY (`idUsuario_fk`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=796 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proveedor`
--

DROP TABLE IF EXISTS `proveedor`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedor` (
  `idProveedor` int(11) NOT NULL AUTO_INCREMENT,
  `razonSocial` varchar(250) NOT NULL,
  `ruc` varchar(12) DEFAULT NULL,
  `direccion` text,
  `rubro` varchar(45) DEFAULT NULL,
  `obsProveedor` text,
  PRIMARY KEY (`idProveedor`),
  UNIQUE KEY `idProveedor_UNIQUE` (`idProveedor`),
  UNIQUE KEY `razonSocial_UNIQUE` (`razonSocial`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proveedorarticulo`
--

DROP TABLE IF EXISTS `proveedorarticulo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedorarticulo` (
  `idProveedorArticulo` int(11) NOT NULL AUTO_INCREMENT,
  `idProveedorfk` int(11) NOT NULL,
  `idArticuloProveedorfk` int(11) NOT NULL,
  `PrecioCompra` varchar(15) DEFAULT NULL,
  `ObservArtProv` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`idProveedorArticulo`),
  UNIQUE KEY `idProveedorArticulo_UNIQUE` (`idProveedorArticulo`),
  KEY `idArticuloProveedorfk_idx` (`idArticuloProveedorfk`),
  KEY `idProveedorfk_idx` (`idProveedorfk`),
  CONSTRAINT `idArticuloProveedorfk` FOREIGN KEY (`idArticuloProveedorfk`) REFERENCES `articulosproveedores` (`idArticulosProveedores`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idProveedorfk` FOREIGN KEY (`idProveedorfk`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `proveedorrubro`
--

DROP TABLE IF EXISTS `proveedorrubro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `proveedorrubro` (
  `idProveedorRubro` int(11) NOT NULL AUTO_INCREMENT,
  `fk_idProveedor` int(11) NOT NULL,
  `idRubro_fk` int(11) NOT NULL,
  `observ` varchar(200) DEFAULT NULL,
  `estado` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`idProveedorRubro`),
  UNIQUE KEY `idProveedorRubro_UNIQUE` (`idProveedorRubro`),
  KEY `idRubro_fk_idx` (`idRubro_fk`),
  KEY `fk_idProveedor_idx` (`fk_idProveedor`),
  CONSTRAINT `fk_idProveedor` FOREIGN KEY (`fk_idProveedor`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idRubro_fk` FOREIGN KEY (`idRubro_fk`) REFERENCES `rubro` (`idRubro`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registrocompra`
--

DROP TABLE IF EXISTS `registrocompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registrocompra` (
  `idRegistroCompra` int(11) NOT NULL AUTO_INCREMENT,
  `fechaRegistro` date DEFAULT NULL,
  `idProveedor_fk` int(11) NOT NULL,
  `numOrdenCompra` varchar(10) DEFAULT NULL,
  `idComprobanteSunat_fk` int(11) NOT NULL,
  `serie` varchar(25) DEFAULT NULL,
  `numero` varchar(25) DEFAULT NULL,
  `fechaEmision` date DEFAULT NULL,
  `fechaAprobacion` date DEFAULT NULL,
  `fechaVencimiento` date DEFAULT NULL,
  `idGlosa_fk` int(11) NOT NULL,
  `moneda` varchar(25) DEFAULT NULL,
  `montoFacturado` varchar(12) DEFAULT NULL,
  `detraccion` varchar(20) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `observacion` tinytext,
  PRIMARY KEY (`idRegistroCompra`),
  KEY `idProveedorfk_idx` (`idProveedor_fk`),
  KEY `idComprobanteSunatfk_idx` (`idComprobanteSunat_fk`),
  KEY `idGlosafk_idx` (`idGlosa_fk`),
  CONSTRAINT `idComprobanteSunatfk` FOREIGN KEY (`idComprobanteSunat_fk`) REFERENCES `comprobantesunat` (`idComprobanteSunat`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idGlosafk` FOREIGN KEY (`idGlosa_fk`) REFERENCES `glosa` (`idGlosa`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idProveedor_fk` FOREIGN KEY (`idProveedor_fk`) REFERENCES `proveedor` (`idProveedor`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registropago`
--

DROP TABLE IF EXISTS `registropago`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registropago` (
  `idRegistroPago` int(11) NOT NULL AUTO_INCREMENT,
  `idRegistroVenta_fk` int(11) NOT NULL,
  `tipoPago` varchar(15) DEFAULT NULL,
  `fechaAbono` date DEFAULT NULL,
  `moneda` varchar(20) DEFAULT NULL,
  `montoAbonado` varchar(12) DEFAULT NULL,
  `numeroOperacion` tinytext,
  `observacion` tinytext,
  PRIMARY KEY (`idRegistroPago`),
  KEY `idRegistroVenta_fk_idx` (`idRegistroVenta_fk`),
  CONSTRAINT `idRegistroVenta_fk` FOREIGN KEY (`idRegistroVenta_fk`) REFERENCES `registroventa` (`idRegistroVenta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registropagocompra`
--

DROP TABLE IF EXISTS `registropagocompra`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registropagocompra` (
  `idRegistroPagoCompra` int(11) NOT NULL AUTO_INCREMENT,
  `idRegistroComprafk` int(11) DEFAULT NULL,
  `tipoPago` varchar(15) DEFAULT NULL,
  `fechaAbono` date DEFAULT NULL,
  `moneda` varchar(20) DEFAULT NULL,
  `montoAbonado` varchar(45) DEFAULT NULL,
  `numeroOperacion` tinytext,
  `observacion` tinytext,
  PRIMARY KEY (`idRegistroPagoCompra`),
  KEY `idRegistroComprafk_idx` (`idRegistroComprafk`),
  CONSTRAINT `idRegistroComprafk` FOREIGN KEY (`idRegistroComprafk`) REFERENCES `registrocompra` (`idRegistroCompra`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `registroventa`
--

DROP TABLE IF EXISTS `registroventa`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `registroventa` (
  `idRegistroVenta` int(11) NOT NULL AUTO_INCREMENT,
  `fechaRegistro` date DEFAULT NULL,
  `idPedido_fkk` int(11) NOT NULL,
  `guia` varchar(15) DEFAULT NULL,
  `tipoComprobante` varchar(10) DEFAULT NULL,
  `serie` varchar(8) DEFAULT NULL,
  `numero` varchar(12) DEFAULT NULL,
  `fechaEmision` date DEFAULT NULL,
  `fechaAprobacion` date DEFAULT NULL,
  `fechaVencimiento` date DEFAULT NULL,
  `tipo` varchar(10) DEFAULT NULL,
  `moneda` varchar(20) DEFAULT NULL,
  `montoFacturado` varchar(12) DEFAULT NULL,
  `detraccion` varchar(12) DEFAULT NULL,
  `estado` varchar(15) DEFAULT NULL,
  `observacion` tinytext,
  PRIMARY KEY (`idRegistroVenta`),
  KEY `idPedidofkk_idx` (`idPedido_fkk`),
  CONSTRAINT `idPedido_fkk` FOREIGN KEY (`idPedido_fkk`) REFERENCES `pedido` (`idPedido`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rotulacion`
--

DROP TABLE IF EXISTS `rotulacion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rotulacion` (
  `idRotulacion` int(11) NOT NULL AUTO_INCREMENT,
  `remitente` varchar(120) NOT NULL,
  `fkkidCliente` int(11) NOT NULL,
  `destino` varchar(120) NOT NULL,
  PRIMARY KEY (`idRotulacion`),
  UNIQUE KEY `idRotulacion_UNIQUE` (`idRotulacion`),
  KEY `fkkidCliente_idx` (`fkkidCliente`),
  CONSTRAINT `fkkidCliente` FOREIGN KEY (`fkkidCliente`) REFERENCES `cliente` (`idCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rotulacionatencion`
--

DROP TABLE IF EXISTS `rotulacionatencion`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rotulacionatencion` (
  `idRotulacionAtencion` int(11) NOT NULL AUTO_INCREMENT,
  `idRotulacionfk` int(11) NOT NULL,
  `fkkidContactoCliente` int(11) NOT NULL,
  PRIMARY KEY (`idRotulacionAtencion`),
  UNIQUE KEY `idRotulacionAtencion_UNIQUE` (`idRotulacionAtencion`),
  KEY `fkkidContactoCliente_idx` (`fkkidContactoCliente`),
  KEY `idRotulacionfk_idx` (`idRotulacionfk`),
  CONSTRAINT `fkkidContactoCliente` FOREIGN KEY (`fkkidContactoCliente`) REFERENCES `contactocliente` (`idContactoCliente`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `idRotulacionfk` FOREIGN KEY (`idRotulacionfk`) REFERENCES `rotulacion` (`idRotulacion`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `rubro`
--

DROP TABLE IF EXISTS `rubro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `rubro` (
  `idRubro` int(11) NOT NULL AUTO_INCREMENT,
  `rubroDesc` varchar(100) NOT NULL,
  PRIMARY KEY (`idRubro`),
  UNIQUE KEY `idRubro_UNIQUE` (`idRubro`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sucursal`
--

DROP TABLE IF EXISTS `sucursal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `sucursal` (
  `idSucursal` int(11) NOT NULL AUTO_INCREMENT,
  `sucursal` varchar(150) DEFAULT NULL,
  `clave` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idSucursal`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tipodecambio`
--

DROP TABLE IF EXISTS `tipodecambio`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tipodecambio` (
  `idTipoDeCambio` int(11) NOT NULL AUTO_INCREMENT,
  `fechaCambio` date NOT NULL,
  `cambioCompra` float NOT NULL,
  `cambioVenta` float NOT NULL,
  PRIMARY KEY (`idTipoDeCambio`),
  UNIQUE KEY `idTipoDeCambio_UNIQUE` (`idTipoDeCambio`)
) ENGINE=InnoDB AUTO_INCREMENT=173 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `transportista`
--

DROP TABLE IF EXISTS `transportista`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `transportista` (
  `idTransportista` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(200) NOT NULL,
  `ruc` varchar(20) DEFAULT NULL,
  `marcaYPlaca` varchar(100) DEFAULT NULL,
  `certificadoInscripcion` varchar(80) DEFAULT NULL,
  `licenciaConducir` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idTransportista`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombres` varchar(70) COLLATE utf8_bin NOT NULL,
  `apellidos` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `telefono` varchar(15) CHARACTER SET utf8 DEFAULT NULL,
  `correo` varchar(80) COLLATE utf8_bin DEFAULT NULL,
  `cargo` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `usuario` varchar(25) COLLATE utf8_bin NOT NULL,
  `clave` varchar(40) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`idUsuario`),
  UNIQUE KEY `usuario_UNIQUE` (`usuario`),
  UNIQUE KEY `password_UNIQUE` (`clave`),
  UNIQUE KEY `idUsuario_UNIQUE` (`idUsuario`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-02-03  0:58:33

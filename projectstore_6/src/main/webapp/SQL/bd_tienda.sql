# LIMITE DE CODIGO 	<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
# NOTA:
# 1. IMPORTANTE, SELECCIONEN TODO EL CODIGO HASTA EL LIMITE DEFINIDO Y CORRER EL CODIGO
# 2. EN LA PARTE DE INFERIOR DEJE UNOS CODIGOS PARA REALIZAR PEQUEÑAS ACCIONES


# ---------------------------------------------------------------------------
# CREACION DE BASE DE DATOS
CREATE DATABASE `bd_tienda` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

# ---------------------------------------------------------------------------
# IMPLEMENTACION DE DATABASE
use bd_tienda;

# ---------------------------------------------------------------------------
# CREACION DE TABLA usuarios
CREATE TABLE `usuarios` (
  `cedula_usuario` bigint NOT NULL,
  `nombre_usuario` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email_usuario` varchar(255) NOT NULL,
  `rol` varchar(35) DEFAULT NULL,
  PRIMARY KEY (`cedula_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# ---------------------------------------------------------------------------
# CREACION DE TABLA clientes
CREATE TABLE `clientes` (
  `cedula_cliente` bigint NOT NULL,
  `nombre_cliente` varchar(255) NOT NULL,
  `direccion_cliente` varchar(255) DEFAULT NULL,
  `telefono_cliente` varchar(255) DEFAULT NULL,
  `email_cliente` varchar(255) NOT NULL,
  PRIMARY KEY (`cedula_cliente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# ---------------------------------------------------------------------------
# CREACION DE TABLA proveedores
CREATE TABLE `proveedores` (
  `nit_proveedor` bigint NOT NULL,
  `nombre_proveedor` varchar(255) DEFAULT NULL,
  `direccion_proveedor` varchar(255) DEFAULT NULL,
  `telefono_proveedor` varchar(255) DEFAULT NULL,
  `ciudad_proveedor` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`nit_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# ---------------------------------------------------------------------------
# CREACION DE TABLA productos
CREATE TABLE `productos` (
  `codigo_producto` bigint NOT NULL,
  `iva_compra` double DEFAULT NULL,
  `nit_proveedor` bigint NOT NULL,
  `nombre_producto` varchar(255) DEFAULT NULL,
  `precio_compra` double DEFAULT NULL,
  `precio_venta` double DEFAULT NULL,
  PRIMARY KEY (`codigo_producto`),
  KEY `nit_proveedor` (`nit_proveedor`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`nit_proveedor`) REFERENCES `proveedores` (`nit_proveedor`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# ---------------------------------------------------------------------------
# CREACION DE TABLA ventas
CREATE TABLE `ventas` (
  `codigo_venta` bigint NOT NULL,
  `cedula_cliente` bigint NOT NULL,
  `cedula_usuario` bigint NOT NULL,
  `iva_venta` double DEFAULT NULL,
  `total_venta` double DEFAULT NULL,
  `valor_venta` double DEFAULT NULL,
  PRIMARY KEY (`codigo_venta`),
  KEY `cedula_cliente` (`cedula_cliente`),
  KEY `cedula_usuario` (`cedula_usuario`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`cedula_cliente`) REFERENCES `clientes` (`cedula_cliente`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`cedula_usuario`) REFERENCES `usuarios` (`cedula_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# ---------------------------------------------------------------------------
# CREACION DE TABLA detalle_ventas
CREATE TABLE `detalle_ventas` (
  `codigo_detalle_venta` bigint NOT NULL,
  `cantidad_producto` int DEFAULT NULL,
  `codigo_producto` bigint NOT NULL,
  `codigo_venta` bigint NOT NULL,
  `valor_total` double DEFAULT NULL,
  `valor_venta` double DEFAULT NULL,
  `valor_iva` double DEFAULT NULL,
  PRIMARY KEY (`codigo_detalle_venta`),
  KEY `codigo_producto` (`codigo_producto`),
  KEY `codigo_venta` (`codigo_venta`),
  CONSTRAINT `detalle_ventas_ibfk_1` FOREIGN KEY (`codigo_producto`) REFERENCES `productos` (`codigo_producto`),
  CONSTRAINT `detalle_ventas_ibfk_2` FOREIGN KEY (`codigo_venta`) REFERENCES `ventas` (`codigo_venta`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

# ---------------------------------------------------------------------------
# INSERCION DE DATOS A TABLA usuarios
insert into bd_tienda.usuarios values(101,"admin",123,"admin@tienda.com","Administrador");
insert into bd_tienda.usuarios values(102,"andersson",123,"adersson@tienda.com","Administrador");
insert into bd_tienda.usuarios values(103,"fabian",123,"fabian@tienda.com","cliente");
insert into bd_tienda.usuarios values(104,"johanna",123,"johanna@tienda.com","Administrador");
insert into bd_tienda.usuarios values(105,"juan",123,"juan@tienda.com","cliente");
insert into bd_tienda.usuarios values(106,"luis",123,"luis@tienda.com","Administrador");

# ---------------------------------------------------------------------------
# INSERCION DE DATOS A TABLA clientes
insert into bd_tienda.clientes values(201,"cliente1","direccion # cliente1",32011111,"cliente1@personal.com");
insert into bd_tienda.clientes values(202,"cliente2","direccion # cliente2","32022222","cliente2@personal.com");
insert into bd_tienda.clientes values(203,"cliente3","direccion # cliente3","32033333","cliente3@personal.com");
insert into bd_tienda.clientes values(204,"cliente4","direccion # cliente4","32044444","cliente4@personal.com");
insert into bd_tienda.clientes values(205,"cliente5","direccion # cliente5","32055555","cliente5@personal.com");

# ---------------------------------------------------------------------------
# INSERCION DE DATOS A TABLA proveedores
insert into bd_tienda.proveedores values(301,"proveedor1","direccion # proveedor1","33011111","proveedor1@empresa.com");
insert into bd_tienda.proveedores values(302,"proveedor2","direccion # proveedor2","33022222","proveedor2@empresa.com");
insert into bd_tienda.proveedores values(303,"proveedor3","direccion # proveedor3","33033333","proveedor3@empresa.com");
insert into bd_tienda.proveedores values(304,"proveedor4","direccion # proveedor4","33044444","proveedor4@empresa.com");
insert into bd_tienda.proveedores values(305,"proveedor5","direccion #5 proveedor","33055555","proveedor5@empresa.com");

# ---------------------------------------------------------------------------
# INSERCION DE DATOS A TABLA productos
insert into bd_tienda.productos values(401,0.19,301,"producto 401",10,15);
insert into bd_tienda.productos values(402,0.19,302,"producto 402",20,27);
insert into bd_tienda.productos values(403,0.09,301,"producto 403",13,16);
insert into bd_tienda.productos values(404,0.06,303,"producto 404",25,31);
insert into bd_tienda.productos values(405,0.09,304,"producto 405",18,27);
insert into bd_tienda.productos values(406,0.19,305,"producto 406",33,38);
insert into bd_tienda.productos values(407,0.19,303,"producto 407",7,11);

# LIMITE DE CODIGO 	>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


# MENU DE USO 			<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<

# IMPLEMENTACION DE DATABASE
use bd_tienda;

# VISUALIZACION DE TABLAS
select * from bd_tienda.usuarios;
select * from bd_tienda.clientes;
select * from bd_tienda.proveedores;
select * from bd_tienda.productos;
select * from bd_tienda.detalle_ventas;
select * from bd_tienda.ventas;

# VISUALIZACION DE TABLAS CON RANGO DE DATOS
# MUESTRA LOS DATOS EN UN RANGO DE FILAS DEFINIDO, EL PRIMER DATO UTILIZA LA UBICACION 1
# database_ 		: INDICAR BASE DE DATOS
# table_ 			: INDICAR TABLA DE BASE DE DATOS
# limit N,N+1 		: RANGO DE SELECCION
select * from database_.table_ limit 0,2;

# MODIFICAR DATOS EN TABLA
# update			: ASIGNAR DATOS
# database_ 		: INDICAR BASE DE DATOS
# table_ 			: INDICAR TABLA DE BASE DE DATOS
# columna_2		: SELECCION DE COLUMNA DE DATO A MODIFICAR = "Modificar" ES EL NUEVO VALOR A MODIFICAR
# columna_1		: 
update database_.table_ set columna_2 = "Modificacion" where columna_1 = "DatoDeBusqueda";

# ELIMINACION DE TABLA
# CONTIENE UNA VALIDACION DE EXISTENCIA, SI NO ENCUENTRA LA TABLA NO DETIENE LA EJECUCION
# database_ 		: INDICAR BASE DE DATOS
# table_ 			: INDICAR TABLA DE BASE DE DATOS
drop table if exists database_.table_;

# ELIMINAR FILA DE TABLA
# database_ 		: INDICAR BASE DE DATOS
# table_ 			: INDICAR TABLA DE BASE DE DATOS
# columna_			: INDICA LA COLUMNA DE BUSQUEDA
# data_				: VALOR DE BUSQUEDA PARA SU UBICACION Y ELIMINACION (TEXTO = "TEXTO" / NUMERO = 000)
/* database_ INDICA LA BASE DE DATOS A UTILIZAR / table_ INDICA LA TABLA / columna_ INDICA LA COLUMNA A EN QUE BUSCAR / 00000 BUSCA EL VALOR Y UBICACION DE LA FILA Y LO ELIMINA */
delete from database_.table_ where columna_ = "data_";

# INSERTAR FILA A TABLA
# database_ 		: INDICAR BASE DE DATOS
# table_ 			: INDICAR TABLA DE BASE DE DATOS
# N					: DATOS DE INGRESO DE LA FILA
insert into database_.table_ values(1,2,3,4,5);

# INSERTAR COLUMNA
alter table database_.table_ add columna_ double;

# MENU DE USO 			>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
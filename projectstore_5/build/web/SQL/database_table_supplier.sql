# TABLA DE PROVEEDORES

# implementacion de base de datos
use proyecto;

# creacion de tabla de base de datos y sus parametros de entrada
create table table_supplier(
	SupplierNit int primary key,
	SupplierName varchar(50),
	SupplierAddress varchar(50),
	SupplierPhone int(50),
	SupplierCity varchar(50)
);

# insercion de datos en tabla
insert into table_supplier values(9001,'proveedor1','carrera 9 calle 90',908070,'Bogota');
insert into table_supplier values(9002,'proveedor2','carrera 10 calle 100',998877,'Villavicencio');

# visualizacion de tabla
select * from proyecto.table_supplier;
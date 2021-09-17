# TABLA DE CLIENTES

# implementacion de base de datos
use proyecto;

# creacion de tabla de base de datos y sus parametros de entrada
create table table_customer(
	CustomerId int primary key,
	CustomerNameFull varchar(50),
	CustomerAddress varchar(50),
	CustomerPhone int(50),
	CustomerEmail varchar(50)
);

# insercion de datos en tabla
insert into table_customer values(1001,'cliente1','calle 1 carrera 10',11223344,'personal@gmail.com');
insert into table_customer values(1002,'cliente2','calle 2 carrera 20',55667788,'personal@hotmail.com');

# visualizacion de tabla
select * from proyecto.table_customer;
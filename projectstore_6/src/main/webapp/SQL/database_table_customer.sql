# TABLA DE CLIENTES

# implementacion de base de datos
use proyecto;

# creacion de tabla de base de datos y sus parametros de entrada
create table table_customer(
	CustomerId int(20) primary key,
	CustomerNameFull varchar(100),
	CustomerAddress varchar(100),
	CustomerPhone int(50),
	CustomerEmail varchar(50)
);

# insercion de datos en tabla
insert into table_customer values(1001,'cliente1','calle 1 carrera 10',11223344,'personal@gmail.com');
insert into table_customer values(1002,'cliente2','calle 2 carrera 20',55667788,'personal@hotmail.com');
insert into table_customer values(1003,'cliente3','calle 3 carrera 30',33333,'personal@hotmail.com');
insert into table_customer values(1004,'cliente4','calle 4 carrera 40',44444,'personal@hotmail.com');
insert into table_customer values(1005,'cliente5','calle 5 carrera 50',55555,'personal@hotmail.com');

# visualizacion de tabla
select * from proyecto.table_customer;
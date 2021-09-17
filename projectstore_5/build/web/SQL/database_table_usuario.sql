# TABLA DE USUARIOS

# implementacion de base de datos
use proyecto;

# creacion de tabla de base de datos y sus parametros de entrada
create table usuario(
	idUsuario int primary key,
	nombreUsuario varchar(50),
	clave varchar(50),
	correo varchar(50),
	tipoUsuario varchar(50)/* Administrador || Cliente*/
);

# insercion de datos en tabla
insert into usuario values(1,'admin','123','admin@floresta.edu.co','Administrador');
insert into usuario values(2,'cliente','321','cliente@floresta.edu.co','Cliente');

# visualizacion de tabla
select * from proyecto.usuario;
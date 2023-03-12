create schema proyectoT;
use proyectoT;
create table usuario(
acceso int ,
codigo int primary key,
nombre varchar(20),
apellido varchar(30),
usert varchar(20),
clave varchar(20));
insert into usuario (acceso,codigo, nombre, apellido, usert, clave) values
(0,1727664599, "kathy","cangahuamin","michi","admin123"),(1,1111111111,"cajero","cajeroq", "cajero", "cajero123");
select * from usuario;
create table clientes(
cedula int primary key,
nombre varchar(20),
apellido varchar(20),
telefono int,
direccion varchar(220),
sexo char);
insert into clientes(cedula, nombre, apellido, telefono,direccion, sexo) values
(1721994026, "Geovana"," Cangahuamin",0980192565, "sangolqui-cotogchoa", 'f');
select * from clientes;
create table prfrutas(
codigo int primary key,
producto varchar(30),
stock int ,
precio decimal(6,2)
);
insert into prfrutas(codigo, producto) values
(0,"seleccione un articulo");

insert into prfrutas(codigo, producto, stock, precio) values
(1,"manzana", 30, 0.25),
(2,"pera", 50, 0.40),
(3,"sandia", 30, 2.50),
(4,"melon", 130, 1.50);
select *from prfrutas;
create table carrito(
codigo int,
producto varchar(30),
venta int ,
preciou decimal(6,2),
preciop decimal(6,2)
);
select *from carrito;
create table venta(
factura int auto_increment primary key,
subtotal decimal(8,2),
numproductos int,
dinero decimal(8,2),
cambio decimal(8,2)
)


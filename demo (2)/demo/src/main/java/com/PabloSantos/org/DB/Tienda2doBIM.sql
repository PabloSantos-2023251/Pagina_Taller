drop database if exists TiendaSegundoBimestre_in5cm;
create database TiendaSegundoBimestre_in5cm;
use TiendaSegundoBimestre_in5cm;

create table Usuarios(
    codigo_usuario int not null,
    username varchar(45),
    password varchar(45),
    email varchar(60),
    rol varchar(45),
    estado int,
    primary key (codigo_usuario)
);

create table Productos(
    codigo_producto int not null,
    nombre_producto varchar(60),
    precio decimal(10,2),
    stock int,
    estado int,
    primary key (codigo_producto)
);

create table Clientes(
    dpi_cliente int not null,
    nombre_cliente varchar(50),
    apellido_cliente varchar(50),
    direccion varchar(100),
    estado int,
    primary key (dpi_cliente)
);

create table Ventas(
    codigo_venta int not null,
    fecha_venta date,
    total decimal(10,2),
    estado int,
    Clientes_dpi_cliente int,
    Usuarios_codigo_usuario int,
    primary key (codigo_venta),
    constraint fk_Ventas_Clientes foreign key (Clientes_dpi_cliente) 
        references Clientes(dpi_cliente),
    constraint fk_Ventas_Usuarios foreign key (Usuarios_codigo_usuario) 
        references Usuarios(codigo_usuario)
);

create table DetalleVenta(
    codigo_detalle_venta int not null,
    cantidad int,
    precio_unitario decimal(10,2),
    subtotal decimal(10,2),
    Productos_codigo_producto int,
    Ventas_codigo_venta int,
    primary key (codigo_detalle_venta),
    constraint fk_Detalle_Productos foreign key (Productos_codigo_producto) 
    references Productos(codigo_producto),
    constraint fk_Detalle_Ventas foreign key (Ventas_codigo_venta) 
    references Ventas(codigo_venta)
);

delimiter $$

create procedure sp_agregarusuario(in id int, in user varchar(45), in pass varchar(45), in mail varchar(60), in rol varchar(45), in est int)
begin
    insert into Usuarios (codigo_usuario, username, password, email, rol, estado) 
    values (id, user, pass, mail, rol, est);
end$$

create procedure sp_actualizarusuario(in id int, in user varchar(45), in pass varchar(45), in mail varchar(60), in rol varchar(45), in est int)
begin
    update Usuarios set username = user, password = pass, email = mail, rol = rol, estado = est 
    where codigo_usuario = id;
end$$

create procedure sp_buscarusuario(in id int)
begin
    select * from Usuarios where codigo_usuario = id;
end$$

create procedure sp_borrarusuario(in id int)
begin
    delete from Usuarios where codigo_usuario = id;
end$$

create procedure sp_agregarproducto(in id int, in nom varchar(60), in prec decimal(10,2), in stock int, in est int)
begin
    insert into Productos (codigo_producto, nombre_producto, precio, stock, estado) 
    values (id, nom, prec, stock, est);
end$$

create procedure sp_actualizarproducto(in id int, in nom varchar(60), in prec decimal(10,2), in stock int, in est int)
begin
    update Productos set nombre_producto = nom, precio = prec, stock = stock, estado = est 
    where codigo_producto = id;
end$$

create procedure sp_buscarproducto(in id int)
begin
    select * from Productos where codigo_producto = id;
end$$

create procedure sp_borrarproducto(in id int)
begin
    delete from Productos where codigo_producto = id;
end$$

create procedure sp_agregarcliente(in dpi int, in nom varchar(50), in ape varchar(50), in dir varchar(100), in est int)
begin
    insert into Clientes (dpi_cliente, nombre_cliente, apellido_cliente, direccion, estado) 
    values (dpi, nom, ape, dir, est);
end$$

create procedure sp_actualizarcliente(in dpi int, in nom varchar(50), in ape varchar(50), in dir varchar(100), in est int)
begin
    update Clientes set nombre_cliente = nom, apellido_cliente = ape, direccion = dir, estado = est 
    where dpi_cliente = dpi;
end$$

create procedure sp_buscarcliente(in dpi int)
begin
    select * from Clientes where dpi_cliente = dpi;
end$$

create procedure sp_borrarcliente(in dpi int)
begin
    delete from Clientes where dpi_cliente = dpi;
end$$

create procedure sp_agregarventa(in id int, in fec date, in tot decimal(10,2), in est int, in cli int, in usu int)
begin
    insert into Ventas (codigo_venta, fecha_venta, total, estado, Clientes_dpi_cliente, Usuarios_codigo_usuario) 
    values (id, fec, tot, est, cli, usu);
end$$

create procedure sp_actualizarventa(in id int, in fec date, in tot decimal(10,2), in est int, in cli int, in usu int)
begin
    update Ventas set fecha_venta = fec, total = tot, estado = est, Clientes_dpi_cliente = cli, Usuarios_codigo_usuario = usu 
    where codigo_venta = id;
end$$

create procedure sp_buscarventa(in id int)
begin
    select * from Ventas where codigo_venta = id;
end$$

create procedure sp_borrarventa(in id int)
begin
    delete from Ventas where codigo_venta = id;
end$$

create procedure sp_agregardetalleventa(in id int, in cant int, in preu decimal(10,2), in sub decimal(10,2), in prod int, in ven int)
begin
    insert into DetalleVenta (codigo_detalle_venta, cantidad, precio_unitario, subtotal, Productos_codigo_producto, Ventas_codigo_venta) 
    values (id, cant, preu, sub, prod, ven);
end$$

create procedure sp_actualizardetalleventa(in id int, in cant int, in preu decimal(10,2), in sub decimal(10,2), in prod int, in ven int)
begin
    update DetalleVenta set cantidad = cant, precio_unitario = preu, subtotal = sub, Productos_codigo_producto = prod, Ventas_codigo_venta = ven 
    where codigo_detalle_venta = id;
end$$

create procedure sp_buscardetalleventa(in id int)
begin
    select * from DetalleVenta where codigo_detalle_venta = id;
end$$

create procedure sp_borrardetalleventa(in id int)
begin
    delete from DetalleVenta where codigo_detalle_venta = id;
end$$

delimiter ;
call sp_agregarusuario(1, 'admin_juan', 'pass123', 'juan@tienda.com', 'Administrador', 1);
call sp_agregarusuario(2, 'pedro_ventas', 'pedro88', 'pedro@tienda.com', 'Usuario', 1);
call sp_agregarusuario(3, 'maria_conta', 'mery77', 'maria@tienda.com', 'Usuario', 1);
call sp_agregarusuario(4, 'lucia_shop', 'lu123', 'lucia@tienda.com', 'Usuario', 1);
call sp_agregarusuario(5, 'carlos_gerente', 'car99', 'carlos@tienda.com', 'Usuario', 1);

call sp_agregarproducto(101, 'Laptop Dell Inspiron', 7500.00, 15, 1);
call sp_agregarproducto(102, 'Mouse Gamer RGB', 250.50, 50, 1);
call sp_agregarproducto(103, 'Teclado Mecánico', 450.00, 30, 1);
call sp_agregarproducto(104, 'Monitor 24 Pulgadas', 1200.00, 10, 1);
call sp_agregarproducto(105, 'Audífonos Bluetooth', 350.25, 25, 1);

call sp_agregarcliente(2001, 'Roberto', 'Gómez', 'Ciudad de Guatemala', 1);
call sp_agregarcliente(2002, 'Ana', 'Martínez', 'Antigua Guatemala', 1);
call sp_agregarcliente(2003, 'Luis', 'Hernández', 'Quetzaltenango', 1);
call sp_agregarcliente(2004, 'Sofía', 'López', 'Escuintla', 1);
call sp_agregarcliente(2005, 'Diego', 'Ramírez', 'Chimaltenango', 1);

call sp_agregarventa(501, '2023-10-01', 7750.50, 1, 2001, 2);
call sp_agregarventa(502, '2023-10-02', 450.00, 1, 2002, 4);
call sp_agregarventa(503, '2023-10-03', 1200.00, 1, 2003, 2);
call sp_agregarventa(504, '2023-10-04', 700.50, 1, 2004, 4);
call sp_agregarventa(505, '2023-10-05', 7500.00, 1, 2005, 2);

call sp_agregardetalleventa(1, 1, 7500.00, 7500.00, 101, 501);
call sp_agregardetalleventa(2, 1, 250.50, 250.50, 102, 501);
call sp_agregardetalleventa(3, 1, 450.00, 450.00, 103, 502);
call sp_agregardetalleventa(4, 1, 1200.00, 1200.00, 104, 503);
call sp_agregardetalleventa(5, 2, 350.25, 700.50, 105, 504);
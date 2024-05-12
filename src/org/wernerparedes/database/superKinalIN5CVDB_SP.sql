use superKinalIN5CVDB;

-- CRUD CLIENTES

DELIMITER $$
create procedure sp_agregarCliente(nom varchar(30), ape varchar(30), tel varchar(15), dir varchar(150), n varchar (15))
BEGIN
	insert into Clientes(nombre, apellido, telefono, direccion, nit)values
    (nom,ape,tel,dir, n);
END $$
DELIMITER ;

call sp_agregarCliente('Robin','Sisimit','2222-2222','Totonicapan','5549594');

-- Listar: Clientes

DELIMITER //
	CREATE PROCEDURE sp_ListarClientes()
		BEGIN
			SELECT 
				Clientes.clienteId,
                Clientes.nombre,
                Clientes.apellido,
                Clientes.telefono,
                Clientes.direccion,
                Clientes.nit
					FROM Clientes;
		END//
DELIMITER ;

CALL sp_ListarClientes();


-- Eliminar: Clientes
DELIMITER //
	CREATE PROCEDURE sp_EliminarCliente(IN cliId INT)
		BEGIN
			DELETE
				FROM Clientes
                WHERE clienteId = cliId;
		END//
DELIMITER ;

-- CALL sp_EliminarCliente(8);

-- DROP PROCEDURE sp_EliminarCliente;

-- Buscar: Clientes
DELIMITER //
	CREATE PROCEDURE sp_BuscarClientes(IN cliId INT)
		BEGIN
			SELECT
				Clientes.clienteId,
                Clientes.nombre,
                Clientes.apellido,
                Clientes.telefono,
                Clientes.direccion,
                Clientes.nit
					FROM Clientes
                    WHERE clienteId = cliId;
		END//
DELIMITER ;

CALL sp_BuscarClientes(2);

-- Editar: Clientes

DELIMITER $$
	CREATE PROCEDURE sp_EditarCliente(IN cliId INT, IN nom VARCHAR(30), IN ape VARCHAR(30), IN tel VARCHAR(15), IN dir VARCHAR(150), IN n VARCHAR (15))
		BEGIN
        UPDATE Clientes
			SET
				nombre = nom,
                apellido = ape,
                telefono = tel,
                direccion = dir,
                nit = n
                WHERE clienteId = cliId;
		END$$
DELIMITER ;

-- DROP PROCEDURE sp_EditarCliente;

CALL sp_EditarCliente(3, 'Juan', 'Araujo', '454548124', 'Mazate', '52524567');

select * from clientes;


-- CRUD CARGOS

-- Agregar: Cargos
DELIMITER $$
	create procedure sp_agregarCargo(nomCar varchar(30),desCar varchar(100))
    begin
		insert into Cargos(nombreCargo,descripcionCargo) values
			(nomCar, desCar);
    end $$
DELIMITER ;
 
-- Listar: Cargos
delimiter $$
create procedure sp_listarCargos()
	begin
		select * from Cargos;
    end $$
delimiter ;
 
-- Eliminar: Cargos
delimiter $$
create procedure sp_eliminarCargo(carId int)
	begin
		delete from Cargos
		where cargoId = carId;
    end $$
delimiter ;
 
-- Buscar: Cargos
delimiter $$
create procedure sp_buscarCargo(carId int)
	begin 
		select *from Cargos
        where cargoId = carId;
    end $$
delimiter ;
 
-- Editar: Cargos
delimiter $$
create procedure sp_editarCargos(carId int, nomCar varchar(30), desCar varchar(100)  )
	begin
        update Cargos
			set
            nombreCargo = nomCar,
            descripcionCargo = desCar
            where cargoId = carId;			
    end $$
delimiter ;


 
-- CRUD DISTRIBUIDORES

-- Agregar:Distribuidores
DELIMITER $$
	create procedure sp_agregarDistribuidores(nomDis varchar(30), dirDis varchar(200), nitDis varchar(15), telDis varchar(15), w varchar(50))
    begin
		insert into Distribuidores(nombreDistribuidor,direccionDistribuidor,nitDistribuidor,telefonoDistribuidor,web) values
			(nomDis, dirDis, nitDis, telDis, w);
    end $$
DELIMITER ;
 
-- Listar:Distribuidores
delimiter $$
create procedure sp_listarDistribuidores()
	begin
		select 
			*
        from Distribuidores;
    end $$
delimiter ;
 
-- Eliminar:Distribuidores
delimiter $$
create procedure sp_eliminarDistribuidores(disId int)
	begin
		delete from Distribuidores
		where distribuidorId = disId;
    end $$
delimiter ;
 
-- Buscar:Distribuidores
delimiter $$
create procedure sp_buscarDistribuidores(disId int)
	begin 
		select *from Distribuidores
        where distribuidorId = disId;
    end $$
delimiter ;
 
-- Editar:Distribuidores
delimiter $$
create procedure sp_editarDistribuidores(disId int,nomDis varchar(30), dirDis varchar(200), nitDis varchar(15), telDis varchar(15), web varchar(50))
	begin
        update Distribuidores
			set
            nombreDistribuidor = nomDis,
            direccionDistribuidor = dirDis,
            nitDistribuidor = nitDis,
            telefonoDistribuidor = telDis,
            web = web
            where distribuidorId = disId;			
    end $$
delimiter ;
 
-- CRUD  CATEGORIA PRODUCTOS

-- Agregar: CategoriaProducto
DELIMITER $$
	create procedure sp_agregarCategoriaProducto(nomCat varchar(30), desCat varchar(100))
    begin
		insert into CategoriaProductos(nombreCategoria, descripcionCategoria) values
			(nomCat, desCat);
    end $$
DELIMITER ;
 
-- Listar: CategoriaProducto
delimiter $$
create procedure sp_listarCategoriaProductos()
	begin
		select * from CategoriaProductos;
    end $$
delimiter ;
 
-- Eliminar: CategoriaProducto
delimiter $$
create procedure sp_eliminarCategoriaProducto(catId int)
	begin
		delete from CategoriaProductos
		where categoriaProductoId = catId;
    end $$
delimiter ;
 
-- Buscar: CategoriaProducto
delimiter $$
create procedure sp_buscarCategoriaProducto(catId int)
	begin 
		select *from CategoriaProductos
        where categoriaProductoId = catId;
    end $$
delimiter ;
 
-- Editar: CategoriaProducto
delimiter $$
create procedure sp_editarCategoriaProductos(catId int,nomCat varchar(30), desCat varchar(100))
	begin
        update CategoriaProductos
			set
            nombreCategoria = nomCat,
            descripcionCategoria = desCat
            where categoriaProductoId = catId;			
    end $$
delimiter ;

-- CRUD Compras

-- Agregar: Compras
DELIMITER $$
CREATE PROCEDURE sp_AgregarCompra(IN fec DATE,IN tot DECIMAL(10,2))
BEGIN
    INSERT INTO Compras(fechaCompra, totalCompra)
    VALUES (fec, tot);
END $$
DELIMITER ;

-- Listar: Compras
DELIMITER $$
CREATE PROCEDURE sp_ListarCompras()
BEGIN
    SELECT
        *
		FROM Compras;
END $$
DELIMITER ;

-- Eliminar: Compras
DELIMITER $$
CREATE PROCEDURE sp_EliminarCompra(IN comId INT)
BEGIN
    DELETE 
		FROM Compras
		WHERE compraId = comId;
END $$
DELIMITER ;

-- Buscar: Compras
DELIMITER $$
CREATE PROCEDURE sp_BuscarCompra(IN comId INT)
BEGIN
    SELECT
       *
		FROM Compras
		WHERE compraId = comId;
END $$
DELIMITER ;

-- Editar: Compras

DELIMITER $$
CREATE PROCEDURE sp_EditarCompra(IN comId INT,IN fec DATE,IN tot DECIMAL(10,2))
BEGIN
    UPDATE Compras
    SET
        fechaCompra = fec,
        totalCompra = tot
    WHERE compraId = comId;
END $$
DELIMITER ;

-- CRUD Empleados

-- Agregar: Empleados

DELIMITER $$
CREATE PROCEDURE sp_AgregarEmpleado(IN nom VARCHAR(30),IN ape VARCHAR(30),IN sue DECIMAL(10,2),IN horen TIME,IN horsa TIME,IN carId INT,IN enc INT)
BEGIN
    INSERT INTO Empleados(nombreEmpleado, apellidoEmpleado, sueldo, horaEntrada, horaSalida, cargoId, encargado)
    VALUES (nom, ape, sue, horen, horsa, carId, enc);
END $$
DELIMITER ;

-- Listar: Empleados


DELIMITER $$
CREATE PROCEDURE sp_ListarEmpleados()
BEGIN
    SELECT 
        EP.empleadoId, EP.nombreEmpleado, EP.apellidoEmpleado, EP.sueldo, EP.horaEntrada, EP.horaSalida, 
        CONCAT('Id: ', C.cargoId, ' | ', C.nombreCargo, ': ', C.descripcionCargo) AS 'cargo',
        CONCAT('Id: ', E.empleadoId, ' | ', E.nombreEmpleado ,' ', E.apellidoEmpleado) AS 'encargado' 
    FROM Empleados EP
    JOIN Cargos C ON EP.cargoId = C.cargoId
    LEFT JOIN Empleados E ON EP.encargado = E.empleadoId;
END $$
DELIMITER ;

-- Eliminar: Empleados
DELIMITER $$
CREATE PROCEDURE sp_EliminarEmpleado(IN empId INT)
BEGIN
    DELETE 
		FROM Empleados
		WHERE empleadoId = empId;
END $$
DELIMITER ;

-- Buscar: Empleados
DELIMITER $$
CREATE PROCEDURE sp_BuscarEmpleado(IN empId INT)
BEGIN
    SELECT E1.empleadoId, E1.nombreEmpleado, E1.apellidoEmpleado, E1.sueldo, E1.horaEntrada, E1.horaSalida,
           C.nombreCargo,
           E2.nombreEmpleado AS Encargado
    FROM Empleados E1
    JOIN Cargos C ON C.cargoId = E1.cargoId
    LEFT JOIN Empleados E2 ON E1.encargado = E2.empleadoId
    WHERE E1.empleadoId = empId;
END $$
DELIMITER ;

-- Editar: Empleados
DELIMITER $$
CREATE PROCEDURE sp_EditarEmpleado(IN empId INT,IN nom VARCHAR(30),IN ape VARCHAR(30),IN sue DECIMAL(10,2),IN horen TIME,IN horsa TIME,IN carId INT,IN enc INT)
BEGIN
    UPDATE Empleados
    SET
        nombreEmpleado = nom,
        apellidoEmpleado = ape,
        sueldo = sue,
        horaEntrada = horen,
        horaSalida = horsa,
        cargoId = carId,
        encargado = enc
    WHERE empleadoId = empId;
END $$
DELIMITER ;

-- ASIGNAR ENCARGADO
DELIMITER $$
CREATE PROCEDURE sp_asignarEncargado(empId int, encId int)
BEGIN
	UPDATE empleados 
		set encargadoId = encId
			where empleadoId = empId;
END $$
DELIMITER ;

call sp_ListarEmpleados();


-- CRUD Facturas

-- Agregar: Facturas
DELIMITER $$
CREATE PROCEDURE sp_AgregarFactura(IN fec DATE,IN hor TIME,IN tot DECIMAL(10,2),IN cliId INT,IN empId INT)
BEGIN
    INSERT INTO Facturas(fecha, hora, total, clienteId, empleadoId)
    VALUES (fec, hor, tot, cliId, empId);
END $$
DELIMITER ;

-- Listar: Facturas
DELIMITER $$
CREATE PROCEDURE sp_ListarFacturas()
BEGIN
    SELECT
			F.facturaId, F.fecha,F.hora, F.total,
        		CONCAT('{Id:',C.clienteId,'}','{Nombre:',C.nombre, C.apellido,'}')AS 'Cliente',
        		CONCAT('{Id:',E.empleadoId,'}','{Nombre:',E.nombreEmpleado, E.apellidoEmpleado,'}')AS 'Empleado'
    FROM Facturas F
	join Clientes C on F.clienteId = C.clienteId
	join Empleados E on F.empleadoId = E.empleadoId;

END $$
DELIMITER ;

-- Eliminar: Facturas
DELIMITER $$
CREATE PROCEDURE sp_EliminarFactura(IN facId INT)
BEGIN
    DELETE 
		FROM Facturas
		WHERE facturaId = facId;
END $$
DELIMITER ;

-- Buscar:Facturas
DELIMITER $$
CREATE PROCEDURE sp_BuscarFactura(IN facId INT)
BEGIN
    SELECT
        *
		FROM Facturas
		WHERE facturaId = facId;
END $$
DELIMITER ;

-- Editar: Facturas
DELIMITER $$
CREATE PROCEDURE sp_EditarFactura(IN facId INT,IN fec DATE,IN hor TIME,IN tot DECIMAL(10,2),IN cliId INT,IN empId INT)
BEGIN
    UPDATE Facturas
    SET
        fecha = fec,
        hora = hor,
        total = tot,
        clienteId = cliId,
        empleadoId = empId
    WHERE facturaId = facId;
END $$
DELIMITER ;

-- CRUD Productos
-- Agregar: Productos
DELIMITER $$
CREATE PROCEDURE sp_agregarProducto(nom varchar(50),des varchar(100),cant int,pvu decimal(10,2),pvm decimal(10,2),pc decimal(10,2),img LONGBLOB,disId int,catId int)
BEGIN
    INSERT INTO Productos(nombreProducto, descripcionProducto, cantidadStock, precioVentaUnitario, precioVentaMayor, precioCompra, imagenProducto, distribuidorId, categoriaProductoId)
    VALUES (nom, des, cant, pvu, pvm, pc, img, disId, catId);
END $$
DELIMITER ;

-- Listar: Productos
DELIMITER $$
CREATE PROCEDURE sp_ListarProductos()
BEGIN
    SELECT P.productoId, P.nombreProducto, P.descripcionProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor,
        CONCAT('{Id:', D.distribuidorId,'}','{Nombre:',D.nombreDistribuidor,'}')AS 'Distribuidor',
		CONCAT('{Id:', C.categoriaProductoId,'}','{Nombre:',C.nombreCategoria,'}')AS 'Categoria'
		FROM Productos P
        join Distribuidores D on P.distribuidorId = D.distribuidorId
		join CategoriaProductos C on P.categoriaProductoId = C.categoriaProductoId;
END $$
DELIMITER ;

-- Eliminar: Productos
DELIMITER $$
CREATE PROCEDURE sp_EliminarProducto(IN proId INT)
BEGIN
    DELETE 
		FROM Productos
		WHERE productoId = proId;
END $$
DELIMITER ;

-- Buscar: Productos
DELIMITER $$
CREATE PROCEDURE sp_BuscarProducto(IN proId INT)
BEGIN
    SELECT
        P.productoId, P.nombreProducto, P.cantidadStock, P.precioVentaUnitario, P.precioVentaMayor, P.precioCompra, P.imagenProducto
		FROM Productos P
		WHERE productoId = proId;
END $$
DELIMITER ;

-- Editar: Productos
DELIMITER $$
CREATE PROCEDURE sp_editarProducto(prodId int,nom varchar(50),des varchar(100),cant int,pvu decimal(10,2),pvm decimal(10,2),pc decimal(10,2),img LONGBLOB,disId int,catId int)
BEGIN
    UPDATE Productos
    SET
        nombreProducto = nom,
        descripcionProducto = des,
        cantidadStock = cant,
        precioVentaUnitario = pvu,
        precioVentaMayor = pvm,
        precioCompra = pc,
        imagenProducto = img,
        distribuidorId = disId,
        categoriaProductosId = catId
    WHERE productoId = prodId;
END $$
DELIMITER ;


-- CRUD Detalle Factura
-- Agregar: Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_AgregarDetalleFactura(IN facId INT,IN proId INT)
BEGIN
    INSERT INTO DetalleFactura(facturaId, productoId)
    VALUES (facId, proId);
END $$
DELIMITER ;

-- Listar: Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_ListarDetallesFactura()
BEGIN
    SELECT
        *
		FROM DetalleFactura;
END $$
DELIMITER ;

-- Eliminar: Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleFactura(IN detId INT)
BEGIN
    DELETE 
		FROM DetalleFactura
		WHERE detalleFacturaId = detId;
END $$
DELIMITER ;

-- Buscar: Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleFactura(IN detId INT)
BEGIN
    SELECT
        *
		FROM DetalleFactura
		WHERE detalleFacturaId = detId;
END $$
DELIMITER ;

-- Editar: Detalle Factura
DELIMITER $$
CREATE PROCEDURE sp_EditarDetalleFactura(IN detId INT,IN facId INT,IN proId INT)
BEGIN
    UPDATE DetalleFactura
    SET
        facturaId = facId,
        productoId = proId
    WHERE detalleFacturaId = detId;
END $$
DELIMITER ;


-- CRUD Detalle Compra

-- Agregar: Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_AgregarDetalleCompra(IN comId INT,IN proId INT)
BEGIN
    INSERT INTO DetalleCompra(compraId, productoId)
    VALUES (comId, proId);
END $$
DELIMITER ;

-- Listar: Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_ListarDetallesCompra()
BEGIN
    SELECT
        *
		FROM DetalleCompra;
END $$
DELIMITER ;

-- Eliminar: Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_EliminarDetalleCompra(IN detId INT)
BEGIN
    DELETE 
		FROM DetalleCompra
		WHERE detalleCompraId = detId;
END $$
DELIMITER ;

-- Buscar: Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_BuscarDetalleCompra(IN detId INT)
BEGIN
    SELECT
        *
		FROM DetalleCompra
		WHERE detalleCompraId = detId;
END $$
DELIMITER ;


-- Editar: Detalle Compra
DELIMITER $$
CREATE PROCEDURE sp_EditarDetalleCompra(IN detId INT,IN comId INT,IN proId INT)
BEGIN
    UPDATE DetalleCompra
    SET
        compraId = comId,
        productoId = proId
    WHERE detalleCompraId = detId;
END $$
DELIMITER ;



-- CRUD Promociones
-- Agregar: Promociones
DELIMITER $$
CREATE PROCEDURE sp_AgregarPromocion(IN pre DECIMAL(10,2),IN des VARCHAR(100),IN fecini DATE,IN fecfin DATE,IN proId INT)
BEGIN
    INSERT INTO Promociones(precioPromocion, descripcionPromocion, fechaInicio, fechaFinalizacion, productoId)
    VALUES (pre, des, fecini, fecfin, proId);
END $$
DELIMITER ;

-- Listar: Promociones
DELIMITER $$
CREATE PROCEDURE sp_ListarPromociones()
BEGIN
   select PS.promocionId, PS.precioPromocion, PS.descripcionPromocion, PS.fechaInicio, PS.fechaFinalizacion,  
			P.nombreProducto from Promociones PS
            join Productos P on PS.productoId = P.productoId;
END $$
DELIMITER ;

call sp_ListarPromociones();

-- Eliminar: Promociones
DELIMITER $$
CREATE PROCEDURE sp_EliminarPromocion(IN proId INT)
BEGIN
    DELETE 
		FROM Promociones
		WHERE promocionId = proId;
END $$
DELIMITER ;

-- Buscar: Promociones
DELIMITER $$
CREATE PROCEDURE sp_BuscarPromocion(IN proId INT)
BEGIN
    SELECT
        *
		FROM Promociones
		WHERE promocionId = proId;
END $$
DELIMITER ;

-- Editar: Promociones
delimiter $$
create procedure sp_EditarPromocion(in promoId int, in prePro decimal(10, 2), in descPro varchar(100), in feIni date, in feFina date, in proId int)
	begin
		update Promociones
			set 
            precioPromocion = prePro,
            descripcionPromocion = descPro,
            fechaInicio = feIni,
            fechaFinalizacion = feFina,
            productoId = proId
            where promocionId = promoId;
    end $$
delimiter ;

call sp_EditarPromocion(1, 300.00, 'noseeee' , '2024-07-07', '2024-08-08' , 1);

-- CRUD Ticket Soporte
-- Agregar: Ticket Soporte
DELIMITER $$
CREATE PROCEDURE sp_AgregarTicketSoporte(des varchar(250), cliId int, facId int)
BEGIN
    INSERT INTO TicketSoporte(descripcionTicket, estatus, clienteId, facturaId)
    VALUES (des, 'Recién Creado', cliId, facId);
END $$
DELIMITER ;

-- Listar: Ticket Soporte
DELIMITER $$
CREATE PROCEDURE sp_ListarTicketsSoporte()
BEGIN
    SELECT TS.ticketSoporteId, TS.descripcionTicket, TS.estatus,
		CONCAT('{Id:',C.clienteId,'}','{Nombre:',C.nombre, C.apellido,'}')AS 'Cliente',
		CONCAT('{Id:',F.facturaId,'}','{Hora:',F.hora,'} {Total:', F.total,'}')AS 'Factura'
	FROM TicketSoporte TS
        join Clientes C on TS.clienteId = C.clienteId
        join Facturas F on TS.facturaId = F.facturaId;
END $$
DELIMITER ;

call sp_ListarTicketsSoporte();

-- Eliminar: Ticket Soporte
DELIMITER $$
CREATE PROCEDURE sp_EliminarTicketSoporte(IN ticId INT)
BEGIN
    DELETE 
		FROM TicketSoporte
		WHERE ticketSoporteId = ticId;
END $$
DELIMITER ;

-- Buscar: Ticket Soporte
DELIMITER $$
CREATE PROCEDURE sp_BuscarTicketSoporte(IN ticId INT)
BEGIN
    SELECT
        *
		FROM TicketSoporte
		WHERE ticketSoporteId = ticId;
END $$
DELIMITER ;

-- Editar: Ticket Soporte


DELIMITER $$
CREATE PROCEDURE sp_EditarTicketSoporte(
IN ticId INT,IN des VARCHAR(250),IN est VARCHAR(30),IN cliId INT,IN facId INT)
BEGIN
    UPDATE TicketSoporte
    SET
        descripcionTicket = des,
        estatus = est,
        clienteId = cliId,
        facturaId = facId
    WHERE ticketSoporteId = ticId;
END $$
DELIMITER ;

select * from productos;

call sp_ListarFacturas();

call sp_ListarProductos();

call sp_ListarPromociones();
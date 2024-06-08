use superKinalIN5CVDB;

DELIMITER $$
	create function fn_calcularTotal(factId int) returns decimal(10,2) deterministic
    begin
		declare total decimal(10,2) default 0.0;
        declare precio decimal(10,2);
        declare i int default 1;
        declare curFacturaId, curProductoId int;
        declare cursorDetalleFactura cursor for 
			select DF.facturaId, DF.productoId from DetalleFactura DF;
		open cursorDetalleFactura;
        totalLoop : loop
        fetch cursorDetalleFactura into curFacturaId,curProductoId;
        if (factID = curFacturaId)then
			set precio = (select P.precioVentaUnitario from Productos P where P.productoId = curProductoId);
            set total = total + precio;
        end if;
        if i = 	(select count(*)from DetalleFactura)then
        leave totalLoop;
        end if;
        set i = i + 1;
        end loop totalLoop;
        call sp_asignarTotal(total,factId);
        return total;
    end$$
DELIMITER ;
select fn_calcularTotal(1);
 
 

DELIMITER $$
	create procedure sp_asignarTotal(in tot decimal(10,2),in factId int)
    begin
		update Facturas
			set total = tot * (1 + 0.12)
            where facturaId = factId;
    end$$
DELIMITER ;
 
 
delimiter $$
create trigger tg_totalFactura
after insert on DetalleFactura
for each row
begin
 
	declare total decimal(10,2);
    set total = fn_calcularTotal(NEW.facturaId);
 
end$$
delimiter ;
 
 
delimiter $$
create procedure sp_manejoStock(in proId int)
begin
	update Productos
		set
			cantidadStock = cantidadStock - 1
            where productoId = proId;
end$$
delimiter ;
 
 
delimiter $$
create trigger tg_restarStock
before insert on DetalleFactura
for each row
begin
    if (select P.cantidadStock from Productos P where productoId = NEW.productoId) = 0 then
		signal sqlstate'45000'
			set message_text="No contamos con este producto en stock :(";
    else
		call sp_manejoStock(new.productoId);
	end if;
end $$
delimiter ;
 
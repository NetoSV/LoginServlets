drop database if exists Login;
create database Login;

use Login;

show databases;
create table Usuarios(
	idUsu int not null primary key auto_increment,
    nomUsu varchar(200) not null,
	passUsu varchar(60) not null
);

drop procedure if exists spValidaUsuario;
delimiter **
create procedure spValidaUsuario(in usr nvarchar(200), in psw  nvarchar(60))
begin
declare existe int;
declare msj nvarchar(200);
declare idPer int;


set existe = (select count(*) from Usuarios where nomUsu = usr and passUsu = md5(psw));

if (existe = 1) then

	set msj = 'Ok';
	select idUsu as idPer, nomUsu as NombreC, msj from Usuarios where nomUsu = usr and passUsu = md5(psw);

else
	set msj = 'no existe';
	select 0 as idPer, msj;

end if;


end; **
delimiter ;

drop procedure if exists guardaUsuario;

delimiter **
create procedure guardaUsuario(in idE int, in nom nvarchar(120), in cont nvarchar(120))
begin 
declare aidi int;
declare existe int;
declare msj nvarchar(200);

set aidi = 0;

if idE = 0 then
	
    set existe = (select count(*) from Usuarios where nomUsu = nom);

    
    if (existe = 0)  then

		set aidi = (select ifnull(max(idUsu), 0) + 1 from Usuarios);
        
		insert into Usuarios (idUsu, nomUsu, passUsu)
					values(aidi, nom, md5(cont));
		
        
                    
		set msj =  'Cliente guardado con exito';
    
    else
		
		set msj = 'Ya existe un usuario con ese nombre';
            
		
    end if;
    
else
set aidi = idE;

	if((select count(*) from Usuarios where idUsu = aidi = 1)) then
		set msj =  'actualizo';
        
        update Usuarios set nomUsu = nom where idUsu = aidi;
        
        
    else
		set msj =  'nel';
        
    end if;
end if;

select idUsu as usuarioId, concat(nomUsu) NombreC, msj from Usuarios where nomUsu = nom;

end; **

delimiter ;



call guardaUsuario(0,'Adrian','1234');
call spValidaUsuario('Adrian','1234');
select * from Usuarios;
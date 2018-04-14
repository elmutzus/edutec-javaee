insert into ROL values (1, 'ADMIN', 'Administrador del Sistema');
insert into ROL values (2, 'AUDITOR', 'Auditor');
insert into ROL values (3, 'CAJERO', 'Cajero');

insert into usuario values(1, 'admin', 'admin@admon.com', 'Administrador del Sistema', null, '1234124');
insert into usuario values(2, 'yesteban', 'yesteban09@gmail.com', 'Yoli Esteban', null, '3423423');
insert into usuario values(3, 'jlpazos', 'leokirap014@gmail.com', 'Jorge Leonel Lam Pazos', null, '534563');
insert into usuario values(4, 'mmutzus', 'elmutzus@gmail.com', 'Manuel Mutzus', null, '3456456');
insert into usuario values(5, 'arigalt', 'arigalt@gmail.com', 'Alejandro Rigalt', null, '57456567');
insert into usuario values(6, 'btorres', 'bernyt0rr3s@gmail.com', 'Bern Torres', null, '4567567');

insert into tarjeta values (1, 'Tarjeta VISA', 20000, '2394824912', 0)

COMMIT;
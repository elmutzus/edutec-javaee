CREATE DATABASE `university`;

create table ROL (
	id int  not null, 
	nombre varchar(100), 
	descripcion varchar(255),
	primary key (id)
);
insert into ROL values (1, 'ADMIN', 'Administrador del Sistema');
insert into ROL values (2, 'PROFE', 'Profesor');
insert into ROL values (3, 'ESTUDIANTE', 'Estudiante regular');

create table USUARIO (
	id int  not null, 
	codigo varchar(255), 
	email varchar(255), 
	nombre varchar(255), 
	password varchar(255), 
	telefono varchar(255),
	id_rol int not null,
	primary key (id),
	foreign key (id_rol) references rol(id)
);
insert into usuario values(1, 'admin', 'admin@admon.com', 'Administrador del Sistema', null, '1234124', 1);
insert into usuario values(2, 'yesteban', 'yesteban09@gmail.com', 'Yoli Esteban', null, '3423423', 2);
insert into usuario values(3, 'jlpazos', 'leokirap014@gmail.com', 'Jorge Leonel Lam Pazos', null, '534563', 2);
insert into usuario values(4, 'mmutzus', 'elmutzus@gmail.com', 'Manuel Mutzus', null, '3456456', 1);
insert into usuario values(5, 'arigalt', 'arigalt@gmail.com', 'Alejandro Rigalt', null, '57456567', 2);
insert into usuario values(6, 'btorres', 'bernyt0rr3s@gmail.com', 'Bern Torres', null, '4567567', 2);


create table PARAMETRO_SISTEMA (
	id int  not null,
	nombre varchar(50),
	valor varchar(255), 
	primary key (id)
);
insert into PARAMETRO_SISTEMA VALUES (1, 'MODO_DEBUG', '0');
insert into PARAMETRO_SISTEMA VALUES (2, 'NUMERO_DECIMALES', '2');


create table DEPARTAMENTO (
	id int  not null,
	codigo varchar(100),
	nombre varchar(255),
	primary key (id)
);
insert into DEPARTAMENTO values (1, 'GUA', 'Guatemala');
insert into DEPARTAMENTO values (2, 'SAC', 'Sacatepéquez');


create table MUNICIPIO (
	id int  not null,
	codigo varchar(100),
	nombre varchar(255),
	id_departamento int not null,
	primary key (id),
	foreign key (id_departamento) references departamento(id)
);
insert into MUNICIPIO values (1, 'GUATE', 'Guatemala', 1);
insert into MUNICIPIO values (2, 'MXC', 'Mixco', 1);
insert into MUNICIPIO values (3, 'VLLN', 'Villa Nueva', 1);

create table SEDE (
	id int  not null,
	codigo varchar(100),
	nombre varchar(255),
	direccion varchar(255),
	id_municipio int not null,
	primary key (id),
	foreign key (id_municipio) references municipio(id)
);
insert into SEDE values (1, 'EDUTEC', 'Edutec', 'Zona 4 Guatemala', 1);
insert into SEDE values (2, 'USAC', 'Universidad de San Carlos de Guatema', 'Zona 12 Guatemala', 1);

create table SALON (
	id int  not null,
	codigo varchar(100),
	id_sede int,
	primary key (id),
	foreign key (id_sede) references sede(id)	
);
insert into SALON values (1, '201', 1);
insert into SALON values (2, '202', 1);
insert into SALON values (3, '203', 1);


create table CICLO (
	id int  not null,
	codigo varchar(100),
	primary key (id)
);
insert into CICLO values (1, '2017');
insert into CICLO values (2, '2018');

create table CURSO (
	id int  not null,
	codigo varchar(100),
	descripcion varchar(255),
	id_ciclo int,
	id_salon int,
	-- id_requisito, agregar luego un curso requisito
	primary key (id),
	foreign key (id_ciclo) references ciclo(id),
	foreign key (id_salon) references salon(id)
);
insert into CURSO values (1, 'MATE1', 'Matemática 1', 1, 2);
insert into CURSO values (2, 'JAVAEE', 'Java Empresarial', 1, 2);
insert into CURSO values (3, '.NET', '.NET Web API empresarial', 1, 2);
insert into CURSO values (4, 'NG5', 'Angular 5', 1, 2);


create table ESTUDIANTE (
	id int not null,
	carnet varchar(25) not null,	
	nombre varchar(255) not null,
	direccion varchar(255) not null,
	primary key(id),
	unique key(carnet)
);
insert into estudiante values(1, '1111', 'Yoli Esteban', 'Guatemala');
insert into estudiante values(2, '2222', 'Manuel Mutzua', 'Guatemala');
insert into estudiante values(3, '3333', 'Jorge Lam', 'Guatemala');
insert into estudiante values(4, '4444', 'Berny Torres', 'Guatemala');
insert into estudiante values(5, '5555', 'Alejandro Rigalt', 'Guatemala');

create table ASIGNACION_ESTUDIANTE (
	id int  not null,
	id_estudiante int  not null,
	id_curso int not null,
	zona decimal(8,2) not null, 
	examen_final decimal(8,2) not null, 
	nota_final decimal(8,2) not null,
	primary key(id),
	unique key(id_estudiante, id_curso)
);
insert into ASIGNACION_ESTUDIANTE values(1, 1, 2, 0, 0, 0);
insert into ASIGNACION_ESTUDIANTE values(2, 2, 2, 0, 0, 0);
insert into ASIGNACION_ESTUDIANTE values(3, 3, 2, 0, 0, 0);
insert into ASIGNACION_ESTUDIANTE values(4, 4, 2, 0, 0, 0);
insert into ASIGNACION_ESTUDIANTE values(5, 5, 2, 0, 0, 0);


create table PROFESOR (
	id int not null,
	carnet varchar(25) not null,	
	nombre varchar(255) not null,
	direccion varchar(255) not null,
	primary key(id),
	unique key(carnet)
);
insert into PROFESOR values(1, '1111', 'Nahum Alarcón', 'Guatemala');

create table ASIGNACION_PROFESOR (
	id_estudiante int  not null,
	id_curso int not null,
	primary key(id_estudiante, id_curso)
);
insert into ASIGNACION_PROFESOR values(1, 2);

COMMIT;
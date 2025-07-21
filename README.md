
# Proyecto Hibernate - Base de Datos UniversidadEducomser

Este documento describe cómo crear y poblar una base de datos `UniversidadEducomser` en SQL Server para su uso con un proyecto en Hibernate. Incluye definiciones de esquema, relaciones, y datos de ejemplo.

---

## Creación de la Base de Datos

```sql
CREATE DATABASE UniversidadEducomser;
GO
USE UniversidadEducomser;
GO
```

---

## Creación del Esquema

```sql
CREATE SCHEMA pregrado;
```

---

## Definición de Tablas y Relaciones

```sql
CREATE TABLE pregrado.Carrera (
    CarreraID INT IDENTITY PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Edificio VARCHAR(50)
);

CREATE TABLE pregrado.Docente (
    DocenteID INT IDENTITY PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    Password VARCHAR(250) NOT NULL,
    Email VARCHAR(250) NOT NULL,
    SitioWeb VARCHAR(250) NOT NULL,
    CarreraID INT,
    FOREIGN KEY (CarreraID) REFERENCES pregrado.Carrera(CarreraID)
);

CREATE TABLE pregrado.Asignatura (
    Sigla VARCHAR(6) PRIMARY KEY,
    Titulo VARCHAR(100) NOT NULL,
    Descripcion TEXT,
    Creditos INT,
    CarreraID INT,
    FOREIGN KEY (CarreraID) REFERENCES pregrado.Carrera(CarreraID)
);

CREATE TABLE pregrado.Estudiante (
    Matricula INT PRIMARY KEY,
    Nombre VARCHAR(100) NOT NULL,
    Apellido VARCHAR(100) NOT NULL,
    FechaNacimiento DATE,
    Password VARCHAR(250) NOT NULL,
    Email VARCHAR(250) NOT NULL,
    Estado BIT NOT NULL,
    CarreraID INT,
    FOREIGN KEY (CarreraID) REFERENCES pregrado.Carrera(CarreraID)
);

CREATE TABLE pregrado.Inscripcion (
    InscripcionID INT IDENTITY PRIMARY KEY,
    EstudianteID INT,
    AsignaturaID VARCHAR(6),
    FechaInscripcion DATE,
    Calificacion DECIMAL(3,2),
    FOREIGN KEY (EstudianteID) REFERENCES pregrado.Estudiante(Matricula),
    FOREIGN KEY (AsignaturaID) REFERENCES pregrado.Asignatura(Sigla)
);
```

---

## Relaciones entre Tablas

- **Carrera** tiene una relación 1:N con **Docente**, **Asignatura** y **Estudiante**.
- **Estudiante** y **Asignatura** están relacionados a través de **Inscripcion** con relaciones N:M, representadas mediante claves foráneas en la tabla intermedia.

---

## Inserción de Datos

### Carrera

```sql
INSERT INTO pregrado.Carrera (Nombre, Edificio) VALUES
('Mecatronica','Facultad tecnologica'),
('Psicologia','Humanidades'),
('Mecanica Aeronautica','Facultad tecnologica'),
('Telecominucaciones','Facultad tecnologica'),
('Informatica','Informatica'),
('Filosofia','Humanidades');

INSERT INTO pregrado.Carrera (Nombre) VALUES
('Matematicas'),
('Fisica');
```

### Docente

```sql
INSERT INTO pregrado.Docente (Nombre, Apellido, Password, Email, SitioWeb, CarreraID) VALUES
('Pepe','Perales','123456','pepe@gmail.com','https://ftec.ueducomser.edu/profile/pepe',2),
('Ana','Sosa','123abc','ana@hotmail.com','https://ftec.ueducomser.edu/profile/ana',2),
('Sofia','Roca','ABCxyz','sofi@gmail.com','https://fhum.ueducomser.edu/profile/sofia',3),
('Carlos','Fuentes','ABC123','charly@gmail.com','https://ftec.ueducomser.edu/profile/carlos',4),
('Samantha','Mendoza','654321','sammy@outlook.com','https://ftec.ueducomser.edu/profile/samantha',5),
('Lidia','Molina','abc123','lidia@gmail.com','https://fcpn.ueducomser.edu/profile/lidia',6),
('Ernesto','Tapia','123ABC','ernes.to@gmail.com','https://fcpn.ueducomser.edu/profile/ernesto',6),
('Pepe','Peralta','12345678','pepe1991@gmail.com','https://fhum.ueducomser.edu/profile/pepe2',7),
('Luz','Lima','12345','luz@hotmail.com','https://fcpn.ueducomser.edu/profile/luz',8),
('Ramiro','Paredes','abcdef','rami@outlook.com','https://fcpn.ueducomser.edu/profile/ramiro',9),
('Ramiro','Rocha','123123','ramiro.r@gmail.com','https://fcpn.ueducomser.edu/profile/ramiro2',9),
('Samantha','Tapia','321321','samantha@gmail.com','https://fcpn.ueducomser.edu/profile/samantha2',9),
('Celia','Tarquino','abcabc','celia.t@gmail.com','https://fcpn.ueducomser.edu/profile/celia',9);
```

### Asignatura

```sql
INSERT INTO
	pregrado.Asignatura (Sigla, Titulo, Descripcion, Creditos, CarreraID)
VALUES
	('ELE101','Electronica 1','Introduccion a la electronica aplicada',10,2),
	('FIS100','Fisica 1','Fisica clasica y fundamentos del movimiento de la particula',20,2),
	('FIS101','Fisica 2','Mecanica de fluidos, optica y acustica',30,2),
	('PSI045','La Personalidad','Un analisis extendido del ente en su individualidad.',20,3),
	('PSI050','Transtornos de la conducta','Estudio de los transtornos, sus causas y síntomas',30,3),
	('PSI030','Sociedad','Observaciones sobre el individuo ante la sociedad y la sociedad ante el individuo.',10,3),
	('MAT200','Cálculo 2','Técnicas de integración sobre funciones indeterminadas',30,4),
	('FIS120','Física 2','Dinámica',20,4),
	('AER100','Aeromodelado','Análisis de flujo de corriente, fuerza de rozamiento y turbulencia.',15,4),
	('RED100','Protocolos','Estudio de los protocolos de comunicación',30,5),
	('RED101','Telecominucación 1','Introducción a la comunicación y la telemetría entre dispositivos remotos',20,5),
	('RED200','Telecomunicación 2','Análisis de la comunicación y el envío de señales en un medio no ideal.',20,5),
	('INF112','Electrónica digital','Fundamentos de compuertas lógicas, optimización y lógica NAND',20,6),
	('INF113','Programación Web','Implementación de HTML5 y CSS3 en aplicaciones Web modernas.',10,6),
	('INF273','Telemática','Redes de computadoras, su configuración de clientes y servidores con BIND y DNS',15,6),
	('FIL005','Ser','Introducción a las ramas filosóficas nacidas en la antigua Grecia respecto del ser pensante.',20,7);
INSERT INTO 
	pregrado.Asignatura (Sigla, Titulo, CarreraID)
VALUES
	('FIL040','Existencialismo',7),
	('FIL100','Realidad',7),
	('MAT210','Cálculo Complejo',8),
	('MAT220','Cálculo en el infinito',8),
	('MAT300','Matrices multidimensionales complejas',8),
	('FIS200','Astronomía',9),
	('LAB200','Laboratorio de astronomía',9),
	('EST100','Estadística I',9);
```

### Estudiante

```sql
INSERT INTO 
	pregrado.Estudiante (Matricula,Nombre,Apellido,FechaNacimiento,Password,Email,Estado,CarreraID)
VALUES
	(60021,'Ana','Rocabado','2000-12-31','123','a@gmail.com',1,2),
	(60022,'Carlos','Paredes','2001-10-20','1234','b@gmail.com',1,2),
	(60023,'Silvia','Cajas','1999-01-02','12345','c@gmail.com',0,2),
	(60024,'Arturo','Rocha','2003-05-03','123456','d@gmail.com',1,3),
	(60025,'Pepe','Paredes','2002-04-12','654','e@gmail.com',1,3),
	(60026,'Julio','Mendez','2000-01-01','6453','f@gmail.com',1,4),
	(60030,'Raul','Molina','2003-12-31','654321','h@outlook.com',1,4),
	(60033,'Viviana','Rendón','1998-10-15','112233','i@gmail.com',1,4),
	(60034,'Fatima','Lima','2004-01-01','asd','j@gmail.com',1,4),
	(60035,'Pedro','Villa','2003-05-02','asdxc','k@gmail.com',1,5),
	(60036,'Enrique','Quiroz','2002-03-13','dsdasqw','l@gmail.com',0,5),
	(60037,'Alfonso','Barrios','2005-07-20','dsaw23','m@gmail.com',1,5),
	(60038,'Carla','Sosa','2004-07-23','sds2323','n@gmail.com',1,5),
	(60039,'Blanca','Sosa','2000-06-09','dsdsdsd','o@gmail.com',1,6),
	(60045,'Mario','Tapia','2001-10-08','123asd','p@gmail.com',1,7),
	(60055,'Tito','Montes','2003-11-30','qwerty','r@hotmail.com',1,7),
	(60056,'Rosa','Pinto','2004-02-20','1q2w3e4r','s@gmail.com',1,8),
	(60057,'María','Siles','1999-09-13','aqweds','t@gmail.com',1,8),
	(60058,'Fernanda','Machicado','2003-01-02','qwe123','v@gmail.com',0,9),
	(60059,'Mariana','Gonzales','2003-02-01','asdasdasd','w@gmail.com',1,9),
	(60060,'Gonzalo','Perales','1996-04-02','qwe12333','x@gmail.com',1,9),
	(60061,'Ronald','Peralta','2005-12-31','223344er','z@hotmail.com',1,9),
	(60062,'Juan','Mora','2001-11-14','11112223','a1@gmail.com',1,9),
	(60063,'Bruno','Tapia','2000-05-27','asdrte','b1@gmail.com',1,9),
	(60064,'Ana','Roca','2002-05-04','werwe434345','c1@gmail.com',1,9),
	(60065,'Samantha','Muñoz','2002-11-01','sdfwer34436','d1@gmail.com',1,9),
	(60066,'Daniela','Molina','1998-10-09','asdqwe123','e1@gmail.com',0,9),
	(60070,'Tania','Maldonado','2004-11-30','asdqwe123','f1@hotmail.com',1,9),
	(60081,'Oscar','Peralta','2001-03-03','ddsaewq21','g1@gmail.com',1,9),
	(60082,'Luis','Hidalgo','2001-05-11','qwe45tyui','h1@gmail.com',1,9);
INSERT INTO 
	pregrado.Estudiante (Matricula,Nombre,Apellido,Password,Email,Estado,CarreraID)
VALUES
	(60087,'Nadia','Nina','65432','g@gmail.com',1,4),
	(60089,'Julio','Fernandez','123qwerty','q@gmail.com',1,7),
	(60093,'Manuel','Ríos','123ewq','u@gmail.com',0,8),
	(60094,'Leonor','Mendoza','rew543','y@gmail.com',1,9);
```

### Inscripcion

```sql
INSERT INTO 
	pregrado.Inscripcion (EstudianteID, AsignaturaID, FechaInscripcion,Calificacion)
VALUES
	(60030, 'AER100','2023-3-01',8.0),
	(60030, 'MAT200','2023-3-01',9.9),
	(60030, 'FIS120','2023-3-01',8.5),
	(60055, 'FIL005','2022-7-01',5.2),
	(60055, 'FIL100','2023-3-01',8.6),
	(60066, 'FIS200','2022-7-01',3.0),
	(60066, 'LAB200','2023-3-01',8.0),
	(60066, 'EST100','2023-7-02',9.1),
	(60062, 'LAB200','2023-3-01',5.0),
	(60062, 'EST100','2023-7-01',7.0),
	(60094, 'EST100','2023-3-01',9.9),
	(60045, 'FIL005','2022-3-02',4.5),
	(60045, 'FIL040','2023-3-01',8.0),
	(60045, 'FIL100','2023-7-03',7.4),
	(60034, 'MAT200','2021-3-01',9.8),
	(60034, 'FIS120','2023-7-01',9.0);

SELECT * FROM pregrado.Inscripcion;

INSERT INTO 
	pregrado.Inscripcion (EstudianteID, AsignaturaID, FechaInscripcion)
VALUES
	(60094, 'FIS200','2024-3-01'),
	(60094, 'LAB200','2024-3-02'),
	(60070, 'FIS200','2024-3-01'),
	(60070, 'LAB200','2024-3-01'),
	(60070, 'EST100','2024-3-01'),
	(60081, 'FIS200','2024-3-03'),
	(60081, 'LAB200','2024-3-01'),
	(60081, 'EST100','2024-3-01'),
	(60082, 'FIS200','2024-3-02'),
	(60082, 'LAB200','2024-3-02'),
	(60082, 'EST100','2024-3-01'),
	(60057, 'MAT210','2024-3-03'),
	(60057, 'MAT220','2024-3-01'),
	(60057, 'MAT300','2024-3-01'),
	(60058, 'LAB200','2024-3-02'),
	(60059, 'LAB200','2024-3-01');
```

---

## Recomendaciones para Hibernate

- Usar `hibernate.hbm2ddl.auto=validate` si ya se tiene la estructura creada.
- Mapear correctamente las relaciones `@ManyToOne`, `@OneToMany`, y `@JoinColumn`.
- Añadir `@Entity`, `@Table(schema="pregrado")` en las clases.


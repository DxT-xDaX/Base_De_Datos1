SET NAMES 'UTF8MB4';
DROP DATABASE IF EXISTS gabinete_abogados;
CREATE DATABASE IF NOT EXISTS gabinete_abogados DEFAULT CHARACTER SET UTF8MB4;
USE gabinete_abogados; 

CREATE TABLE cliente(
id_cte				INT NOT NULL AUTO_INCREMENT,
curp				VARCHAR(18) NOT NULL UNIQUE,
nombre				VARCHAR(20) NOT NULL,
apellido_paterno	VARCHAR(20) NOT NULL,
apellido_materno	VARCHAR(20) NOT NULL,
direccion			VARCHAR(100) NOT NULL,
telefono			VARCHAR(10) NOT NULL UNIQUE,
correo				VARCHAR(50) NOT NULL UNIQUE,

PRIMARY KEY (id_cte)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO cliente(curp, nombre, apellido_paterno, apellido_materno, direccion, telefono, correo)
VALUES  ('HFGL2004113MCLRSA1','MIKE','SANCHEZ','HERNANDEZ','asdasdasd','1234567891','DEMO1@DEMO.com'),
		('AAHL2004111MCLRSA2','GAEL','GONZALEZ','SANCHEZ','adasdaadl','1234567892','DEMO2@DEMO.com'),
		('AAHL20045111JLRSA3','JAIME','PEREZ','CAICEDO','asddasasd','1234567893','DEMO3@DEMO.com'),
		('AAHL2004561MCLRSA2','ANGEL','FLORES','LEAL','asldasdal','1234567894','DEMO4@DEMO.com'),
		('BHAH20064587MCRLE3','WILLIAM','IBANEZ','CAZTRO','sdasdas','123456795','DEMO5@DEMO.com'),
		('BHAH20064587MCRLT6','ROLANDO','FLORES','BOSQUES','sdasdgajhsdas','123456796','DEMO6@DEMO.com'),
		('BHAH19954587MCRLE3','RAUL','TORRES','CLARO','sdatrsdas','123456797','DEMO7@DEMO.com'),
		('LAFC20064587MCRLE3','RAFAEL','CERDA','YANEZ','sdasadas','123456798','DEMO8@DEMO.com'),
		('MUNF20064587MCRLE3','SEBASTIAN','RIOS','OCA','sdassdakghadas','123456799','DEMO9@DEMO.com'),
		('BOUN20064587MCRLE3','MIGUEL','RODRIGUEZ','REAL','sdadsfdssdas','123456790','DEMO0@DEMO.com');

CREATE TABLE estado(
id_edo				INT NOT NULL,
tipo_estado			VARCHAR(20) NOT NULL,
PRIMARY KEY (id_edo)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO estado(id_edo, tipo_estado)
VALUES  (1,'inicio'),
		(2,'en espera'),
		(3,'archivado'),
		(4,'en tramites'),
		(5, 'finalizado');

CREATE TABLE caso(
id_tipocaso				INT NOT NULL,
tipocaso		        VARCHAR(20) NOT NULL,
PRIMARY KEY (id_tipocaso)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO caso(id_tipocaso, tipocaso)
VALUES  (1,'divorcio'),
		(2,'allanamiento'),
		(3,'violencia'),
		(4,'contrato'),
		(5, 'disturbios');        
       
CREATE TABLE abogado(
id_abo				INT NOT NULL AUTO_INCREMENT,
cedula_profesional	VARCHAR(8) NOT NULL UNIQUE,
nombre				VARCHAR(20) NOT NULL,
apellido_paterno	VARCHAR(20) NOT NULL,
apellido_materno	VARCHAR(20) NOT NULL,
curp				VARCHAR(18) NOT NULL,
telefono			VARCHAR(10) NOT NULL UNIQUE,
correo				VARCHAR(50) NOT NULL UNIQUE,
PRIMARY KEY (id_abo)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO abogado(cedula_profesional, nombre, apellido_paterno, apellido_materno, curp, telefono, correo)
VALUES  ('12345671','BRYAN','SANCHEZ','MARTINEZ','AAHL20041111JLRSA4','123266781','DEMO1@DEMO.com'),
		('12378912','DANIEL','TREVINO','HERNANDEZ','AAHL2004136MCLRSA1','247256782','DEMO2@DEMO.com'),
		('12345873','JOSE LUIS','LEIJA','ORTEGA','AAHL20041111J45SA4','323986781','DEMO3@DEMO.com'),
		('12378944','ADRIAN','CAMACHO','RAMIREZ','AAHL200411145LRSA1','423418782','DEMO4@DEMO.com'),
		('12345432','TANIA','SUAREZ','VELAZQUEZ','AAHL20041111J45SA4','323366781','DEMO5@DEMO.com'),
		('12345680','ULISES','PRIETO','QUINO','AAHL20041100J44323','323456780','DEMO6@DEMO.com'),
		('12345681','JUAN','QUINO','TORRES','AAHL20041122J45SA4','323489791','DEMO7@DEMO.com'),
		('12345682','JONATHAN','SANCHEZ','HERNANDEZ','AAHL20041133J45SA4','3231856792','DEMO8@DEMO.com'),
		('12345683','ITZEL','REYES','ARMENTA','AAHL20041144J45SA4','323478793','DEMO9@DEMO.com'),
		('12345673','JOSE EMILIO','ALVARADO','CLARO','AAHL20041155J45SA4','413456794','DEMO0@DEMO.com');
		
CREATE TABLE procurador(
id_pcr				INT NOT NULL AUTO_INCREMENT,
cedula_profesional	VARCHAR(8) NOT NULL UNIQUE,
nombre				VARCHAR(20) NOT NULL,
apellido_paterno	VARCHAR(20) NOT NULL,
apellido_materno	VARCHAR(20) NOT NULL,
curp				VARCHAR(18) NOT NULL,
telefono			VARCHAR(10) NOT NULL UNIQUE,
correo				VARCHAR(50) NOT NULL UNIQUE,
PRIMARY KEY (id_pcr)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO procurador(cedula_profesional, nombre, apellido_paterno, apellido_materno, curp, telefono, correo)
VALUES  ('12345678','DANIEL','RAMIREZ','TORRES','AAHL20041111JLRSA4','123456781','DEMO@DEMO.com'),
		('12378912','RAUL','CASTANEDA','GONZALEZ','AAHL2004111MCLRSA1','223456781','DEMO2@DEMO.com'),
		('12345146','MARIO','PEREZ','QUINTA','AAHL20041111JLR124','323456781','DEMO3@DEMO.com'),
		('12372157','CRUZ','RODRIGUEZ','ESTE','AAHL2004111MCLR784','423445781','DEMO4@DEMO.com'),
		('12372160','AXEL','CASTRO','REALES','AAHL1990111MCLR784','423456781','DEMO5@DEMO.com'),
		('12372161','MIGUEL','SANCHEZ','HERNANDEZ','AAHL1991111MCLR784','423456750','DEMO6@DEMO.com'),
		('12372162','ALVARO','POZOS','ORTIZ','AAHL1992111MCLR784','423456751','DEMO7@DEMO.com'),
		('12372163','EDUARDO','DIAZ','SCOTT','AAHL1993111MCLR784','423456752','DEMO8@DEMO.com'),
		('12372164','HECTOR','VELAZQUEZ','VAZQUEZ','AAHL1994111MCLR784','423456753','DEMO9@DEMO.com'),
		('12372165','CLAUDIO','BADILLO','OLEA','AAHL1995111MCLR784','423456745','DEMO0@DEMO.com');
		
CREATE TABLE expediente(
id_ete				INT AUTO_INCREMENT NOT NULL,
id_cso				VARCHAR(6) NOT NULL,
id_cte				INT NOT NULL,
id_edo	 			INT NOT NULL,
id_tipocaso         INT NOT NULL,
fecha_inicio		DATETIME NOT NULL,
fecha_archivo		DATETIME DEFAULT NULL,
fecha_final			DATETIME DEFAULT NULL,
observacion 		VARCHAR(1000) NOT NULL,

PRIMARY KEY (id_ete,id_cso),
CONSTRAINT fk_expediente_cliente FOREIGN KEY(id_cte) REFERENCES cliente(id_cte),
CONSTRAINT fk_expediente_estado FOREIGN KEY(id_edo) REFERENCES estado(id_edo),
CONSTRAINT fk_expediente_caso FOREIGN KEY(id_tipocaso) REFERENCES caso(id_tipocaso)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO expediente(id_cso,id_tipocaso,id_cte, id_edo, fecha_inicio, fecha_archivo, fecha_final,observacion)
VALUES  ('A1315R',1,1,1,'2025_01_3:10:20',null,null,'Revision de documentos'),
		('A2315R',2,2,1,'2025_01_3:10:35',null,null,'Revision de documentos'),
		('A3315R',3,7,4,'2025_01_3:11:10',null,null,'Tramite de documentos '),
		('A4315R',1,4,3,'2025_02_3:12:50','2025_03_4:12:00',null,'Espera de respuesta del cliente'),
		('A5315R',1,5,3,'2025_02_3:13:25','2026_05_2:15:20',null,'Espera de tramites'),
		('A6315R',1,5,5,'2025_03_3:14:35','2026_05_2:15:20','2026_08_4:10:40','Caso cerrado'),
		('A3315R',3,7,5,'2025_03_3:14:50','2025_04_7:10:40','2025_04_7:10:40','Cargos retirados'),
		('A8315R',5,8,1,'2025_04_3:10:10',null,null,'Revision de documentos'),
		('A9315R',4,9,2,'2025_04_3:09:00',null,null,'Esperando respuesta de la delegacion Gustavo A. Madero'),
		('A1015R',4,10,3,'2025_05_3:10:35','2025_07_4:12:50',null,''),
        ('A1315R',1,1,2,'2025_01_3:10:20','2025_02_4:10:20',null,'Revision de documentos'),
        ('A1315R',1,1,5,'2025_01_3:10:20','2025_02_4:10:20','2025_03_5:10:20','Caso cerrado');
		
		
CREATE TABLE expediente_abogado(
id_abo				INT NOT NULL,
id_ete				INT NOT NULL,
id_cso				VARCHAR(6) NOT NULL,
situacion			VARCHAR(20) NOT NULL,

PRIMARY KEY (id_abo, id_ete,id_cso,situacion),

CONSTRAINT fk_abogado1 FOREIGN KEY(id_abo) REFERENCES abogado(id_abo),
CONSTRAINT fk_abogado2 FOREIGN KEY(id_ete,id_cso) REFERENCES expediente(id_ete,id_cso)
) DEFAULT CHARACTER SET UTF8MB4;

INSERT INTO expediente_abogado(id_abo, id_ete,id_cso, situacion)
VALUES  (2,1,'A1315R','Vigente'),
		(3,2,'A2315R','No Vigente'),
		(4,3,'A3315R','Vigente'),
		(6,4,'A4315R','No Vigente'),
		(7,5,'A5315R','Vigente'),
		(9,6,'A6315R','No Vigente'),
		(1,7,'A3315R','Vigente'),
		(5,8,'A8315R','Vigente'),
		(8,9,'A9315R','No Vigente'),
		(10,10,'A1015R','Vigente'),
         (2,11,'A1315R','Vigente'),
        (2,12,'A1315R','Vigente');

		

CREATE TABLE expediente_procurador(
id_pcr				INT NOT NULL,
id_ete				INT NOT NULL,
id_cso				VARCHAR(6) NOT NULL,
situacion			VARCHAR(20) NOT NULL,

PRIMARY KEY (id_pcr, id_ete,id_cso,situacion),

CONSTRAINT fk_procurador1 FOREIGN KEY(id_pcr) REFERENCES procurador(id_pcr),
CONSTRAINT fk_procurador2 FOREIGN KEY(id_ete,id_cso) REFERENCES expediente(id_ete,id_cso)

) DEFAULT CHARACTER SET UTF8MB4;
INSERT INTO expediente_procurador(id_pcr, id_ete,id_cso, situacion)
VALUES  (2,1,'A1315R','Vigente'),
		(2,2,'A2315R','No Vigente'),
		(4,3,'A3315R','Vigente'),
		(6,4,'A4315R','No Vigente'),
		(7,5,'A5315R','Vigente'),
		(9,6,'A6315R','No Vigente'),
		(1,7,'A3315R','Vigente'),
		(5,8,'A8315R','Vigente'),
		(8,9,'A9315R','No Vigente'),
		(10,10,'A1015R','Vigente'),
        (2,11,'A1315R','Vigente'),
        (2,12,'A1315R','Vigente');
        


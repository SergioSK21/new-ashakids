CREATE DATABASE ashakids;
drop database ashakids;
use ashakids;
SHOW TABLES;

create table usuarios (
	id_usuario int primary key auto_increment,
    nombre varchar(20),
    contraseña varchar(20),
    edad varchar(3)
);

insert into usuarios(nombre, contraseña, edad) values
('admin','admin','21'),
('sergio','123','21'),
('nicolas','123','21'),
('amparito','123','21'),
('alan','123','21'),
('manuel','123','21');

select * from usuarios;

CREATE TABLE reuniones (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tema VARCHAR(255) NOT NULL,
    fecha DATE NOT NULL,
    hora TIME NOT NULL,
    duracion INT NOT NULL, -- duración en minutos
    terapeuta VARCHAR(255) NOT NULL,
    creado_zoom BOOLEAN DEFAULT FALSE,
    link_zoom TEXT
);
INSERT INTO reuniones (tema, fecha, hora, duracion, terapeuta)
VALUES ('Sesión de Lenguaje', '2025-05-10', '10:00:00', 30, 'Susana Horia');

CREATE TABLE terapeutas (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(255) NOT NULL,
    servicio VARCHAR(255) NOT NULL,
    fecha_hora DATETIME NOT NULL,
    correo VARCHAR(255) NOT NULL,
    monto DECIMAL(10, 2) NOT NULL
);

-- Insertar el primer registro de ejemplo:
INSERT INTO terapeutas (nombre, servicio, fecha_hora, correo, monto)
VALUES ('Susana Horia', 'Cita individual', '2025-05-09 12:00:00', 'susana@asha.com', 140.00);
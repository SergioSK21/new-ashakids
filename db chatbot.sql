CREATE DATABASE ashakids_bot;
DROP DATABASE ashakids_bot;
USE ashakids_bot;


CREATE TABLE mensajes (
  id INT AUTO_INCREMENT PRIMARY KEY,
  numero VARCHAR(20),
  mensaje TEXT,
  respuesta TEXT,
  fecha TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE citas (
  id INT AUTO_INCREMENT PRIMARY KEY,
  nombre VARCHAR(100),
  especialidad VARCHAR(100),
  fecha_cita DATE,
  numero VARCHAR(20),
  fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

DROP TABLE citas;

select * from citas
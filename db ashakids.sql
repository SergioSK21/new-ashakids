Create DataBase Ashakids
use AshaKids

-- =========================
-- 1. Tabla: Usuarios
-- =========================
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    correo VARCHAR(100) UNIQUE,
    contraseña VARCHAR(100),
    rol ENUM('padre', 'terapeuta', 'admin') NOT NULL,
    telefono VARCHAR(20)
);

-- =========================
-- 2. Tabla: Padres
-- =========================
CREATE TABLE padres (
    id_padre INT PRIMARY KEY,
    direccion VARCHAR(255),
    -- otros campos específicos del padre
    FOREIGN KEY (id_padre) REFERENCES usuarios(id_usuario)
);

-- =========================
-- 3. Tabla: Terapeutas
-- =========================
CREATE TABLE terapeutas (
    id_terapeuta INT PRIMARY KEY,
    especialidad VARCHAR(100),
    FOREIGN KEY (id_terapeuta) REFERENCES usuarios(id_usuario)
);

-- =========================
-- 4. Tabla: Administradores
-- =========================
CREATE TABLE administradores (
    id_admin INT PRIMARY KEY,
    nivel_acceso VARCHAR(50),
    FOREIGN KEY (id_admin) REFERENCES usuarios(id_usuario)
);


-- =========================
-- 5. Tabla: Niños
-- =========================
CREATE TABLE ninos (
    id_nino INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100),
    fecha_nacimiento DATE,
    id_padre INT,
    FOREIGN KEY (id_padre) REFERENCES padres(id_padre)
);

-- =========================
-- 6. Tabla: Disponibilidad
-- =========================
CREATE TABLE disponibilidad (
    id_disponibilidad INT AUTO_INCREMENT PRIMARY KEY,
    id_terapeuta INT,
    fecha DATE,
    hora_inicio TIME,
    hora_fin TIME,
    FOREIGN KEY (id_terapeuta) REFERENCES terapeutas(id_terapeuta)
);

-- =========================
-- 7. Tabla: Recursos
-- =========================
CREATE TABLE recursos (
    id_recurso INT AUTO_INCREMENT PRIMARY KEY,
    titulo VARCHAR(100),
    tipo VARCHAR(50),
    url TEXT,
    edad_recomendada VARCHAR(20),
    categoria VARCHAR(50),
    creado_por INT,
    FOREIGN KEY (creado_por) REFERENCES administradores(id_admin)
);

-- =========================
-- 8. Tabla: Citas
-- =========================
CREATE TABLE citas (
    id_cita INT AUTO_INCREMENT PRIMARY KEY,
    id_nino INT,
    id_terapeuta INT,
    fecha DATE,
    hora TIME,
    modalidad VARCHAR(20),
    enlace_o_direccion VARCHAR(255),
    estado VARCHAR(20) DEFAULT 'programada',
    retroalimentacion_padre TEXT,
    FOREIGN KEY (id_nino) REFERENCES ninos(id_nino),
    FOREIGN KEY (id_terapeuta) REFERENCES terapeutas(id_terapeuta)
);

-- =========================
-- 9. Tabla: Mensajes
-- =========================
CREATE TABLE mensajes (
    id_mensaje INT AUTO_INCREMENT PRIMARY KEY,
    id_padre INT,
    id_terapeuta INT,
    emisor VARCHAR(20),
    mensaje TEXT,
    fecha_envio DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_padre) REFERENCES padres(id_padre),
    FOREIGN KEY (id_terapeuta) REFERENCES terapeutas(id_terapeuta)
);

-- =========================
-- 10. Tabla: Recursos_Usuarios
-- =========================
CREATE TABLE recursos_usuarios (
    id INT AUTO_INCREMENT PRIMARY KEY,
    id_padre INT,
    id_recurso INT,
    fecha_acceso DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_padre) REFERENCES padres(id_padre),
    FOREIGN KEY (id_recurso) REFERENCES recursos(id_recurso)
);

-- =========================
-- 11. Tabla: Logs
-- =========================
CREATE TABLE logs (
    id_log INT AUTO_INCREMENT PRIMARY KEY,
    id_admin INT,
    accion VARCHAR(255),
    fecha DATETIME DEFAULT CURRENT_TIMESTAMP,
    descripcion TEXT,
    FOREIGN KEY (id_admin) REFERENCES administradores(id_admin)
);

-- =========================
-- 12. Tabla: Notas Clínicas
-- =========================
CREATE TABLE notas_clinicas (
    id_nota INT AUTO_INCREMENT PRIMARY KEY,
    id_cita INT,
    observaciones TEXT,
    recomendaciones TEXT,
    fecha_registro DATETIME DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (id_cita) REFERENCES citas(id_cita)
);

-- ===========
-- ===========
-- INSERCIONES
-- ===========
-- ===========

select * from usuarios;
select * from terapeutas;

INSERT INTO usuarios (nombre, correo, contraseña, rol, telefono)
VALUES ('javier', 'javier@asha.com', 'mantequilla', 'padre', '666666666');
VALUES ('Susana Horia', 'susanahoria@ashakids.com', 'quesadilla', 'terapeuta', '999999999');

INSERT INTO padres (id_padre, direccion)
VALUES (1, 'Av. Falsa 123');

INSERT INTO ninos (nombre, fecha_nacimiento, id_padre)
VALUES ('Mateo Fernández', '2018-06-15', 1);

INSERT INTO terapeutas (id_terapeuta, especialidad)
VALUES (2, 'Terapia infantil');

INSERT INTO citas (
  id_nino,
  id_terapeuta,
  fecha,
  hora,
  modalidad,
  enlace_o_direccion,
  estado,
  retroalimentacion_padre
) VALUES (
  1,
  2,
  '2025-06-10',
  '10:00:00',
  'Virtual',
  NULL,
  'programada',
  NULL
);
DROP DATABASE IF EXISTS Athletix;
CREATE DATABASE Athletix;
USE Athletix;


CREATE TABLE Tiposnotificaciones (
    IDtipos INT AUTO_INCREMENT,
    Titulo VARCHAR(100) NOT NULL,
    PRIMARY KEY (IDtipos)
);

CREATE TABLE Tipousuario (
    ID INT AUTO_INCREMENT,
    descripcion VARCHAR(100),
    PRIMARY KEY (ID)
);

CREATE TABLE Usuarios (
    ID INT AUTO_INCREMENT,
    Usuario VARCHAR(50) NOT NULL,
    Contrasena VARCHAR(100) NOT NULL,
    Email VARCHAR(100) NOT NULL,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Apellido2 VARCHAR(50),
    Genero VARCHAR(10),
    Pueblo VARCHAR(50),
    Altura FLOAT,
    Peso FLOAT,
    Telefono VARCHAR(15),
    Imagen VARCHAR (255),
    IDentrenador INT,
    IDtipousuario INT,
    PRIMARY KEY (ID),
    CONSTRAINT fk_usuario_entrenador FOREIGN KEY (IDentrenador) REFERENCES Usuarios(ID),
    CONSTRAINT fk_usuario_tipo FOREIGN KEY (IDtipousuario) REFERENCES Tipousuario(ID)
);

CREATE TABLE Notificaciones (
    IDnotificacion INT AUTO_INCREMENT,
    texto VARCHAR(255) NOT NULL,
    IDtipo INT,
    fecha DATE,
    PRIMARY KEY (IDnotificacion),
    CONSTRAINT fk_notificacion_tipo FOREIGN KEY (IDtipo) REFERENCES Tiposnotificaciones(IDtipos)
);

CREATE TABLE NotificacionesUsuario (
    IDnotificacion INT,
    IDusuario INT,
    PRIMARY KEY (IDnotificacion, IDusuario),
    CONSTRAINT fk_notif_usuario_notif FOREIGN KEY (IDnotificacion) REFERENCES Notificaciones(IDnotificacion),
    CONSTRAINT fk_notif_usuario_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID)
);

CREATE TABLE Amigos (
    ID INT AUTO_INCREMENT,
    IDusuario INT,
    IDamigo INT,
    PRIMARY KEY (ID),
    CONSTRAINT fk_amigo_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID),
    CONSTRAINT fk_amigo_amigo FOREIGN KEY (IDamigo) REFERENCES Usuarios(ID)
);

CREATE TABLE Eventos (
    IDevento INT AUTO_INCREMENT,
    titulo VARCHAR(100),
    descripcion TEXT,
    fecha DATE,
    kilometro FLOAT,
    direccion VARCHAR(255),
    IDcreador INT,
    PRIMARY KEY (IDevento),
    CONSTRAINT fk_eventos_usuario FOREIGN KEY (IDcreador) REFERENCES Usuarios(ID)
);

CREATE TABLE Retos (
    IDreto INT AUTO_INCREMENT,
    titulo VARCHAR(100),
    descripcion TEXT,
    kilometro FLOAT,
    duracion TIME,
    IDentrenador INT,
    PRIMARY KEY (IDreto),
	CONSTRAINT fk_retos_usuario FOREIGN KEY (IDentrenador) REFERENCES Usuarios(ID)
);

CREATE TABLE Deportes (
    ID INT AUTO_INCREMENT,
    Deporte VARCHAR(50),
    PRIMARY KEY (ID)
);

CREATE TABLE Grupos (
    ID INT AUTO_INCREMENT,
    Nombre VARCHAR(100),
    Descripción TEXT,
    PRIMARY KEY (ID)
);

CREATE TABLE UsuarioGrupo (
    IDgrupo INT,
    IDusuario INT,
    PRIMARY KEY (IDgrupo, IDusuario),
    CONSTRAINT fk_usuariogrupo_grupo FOREIGN KEY (IDgrupo) REFERENCES Grupos(ID),
    CONSTRAINT fk_usuariogrupo_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID)
);

CREATE TABLE Chat (
    IDchat INT AUTO_INCREMENT,
    IDamigo INT,
    IDgrupo INT,
    PRIMARY KEY (IDchat),
    CONSTRAINT fk_chat_amigo FOREIGN KEY (IDamigo) REFERENCES Amigos(ID),
    CONSTRAINT fk_chat_grupo FOREIGN KEY (IDgrupo) REFERENCES Grupos(ID)
);

CREATE TABLE Mensajes (
    IDmensaje INT AUTO_INCREMENT,
    IDchat INT,
    fecha_mensaje DATETIME,
    mensaje TEXT,
    PRIMARY KEY (IDmensaje),
    CONSTRAINT fk_mensaje_chat FOREIGN KEY (IDchat) REFERENCES Chat(IDchat)
);

CREATE TABLE EventosUsuarios (
    IDevento INT,
    IDusuario INT,
    PRIMARY KEY (IDevento, IDusuario),
    CONSTRAINT fk_eventousuario_evento FOREIGN KEY (IDevento) REFERENCES Eventos(IDevento),
    CONSTRAINT fk_eventousuario_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID)
);

CREATE TABLE RetosUsuarios (
    IDreto INT,
    IDusuario INT,
    fecha DATE,
    km FLOAT,
    duracion TIME,
    PRIMARY KEY (IDreto, IDusuario,fecha),
    CONSTRAINT fk_retousuario_reto FOREIGN KEY (IDreto) REFERENCES Retos(IDreto),
    CONSTRAINT fk_retousuario_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID)
);

CREATE TABLE Seguimiento (
    IDseguimiento INT AUTO_INCREMENT,
    kilometro FLOAT,
    duracion TIME,
    fecha DATE,
    titulo VARCHAR(100),
    deskripcion TEXT,
    IDusuario int,
    PRIMARY KEY (IDseguimiento),
	CONSTRAINT fk_seguimiento_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID)
    
);

CREATE TABLE DeportesUsuarios (
    IDusuario INT,
    IDdeporte INT,
    Descripcion TEXT,
    PRIMARY KEY (IDusuario, IDdeporte),
    CONSTRAINT fk_deporteusuario_usuario FOREIGN KEY (IDusuario) REFERENCES Usuarios(ID),
    CONSTRAINT fk_deporteusuario_deporte FOREIGN KEY (IDdeporte) REFERENCES Deportes(ID)
);

CREATE TABLE Imagen (
    IDimagen INT AUTO_INCREMENT,
    IDreto INT,
    IDseguimiento INT,
    IDevento INT,
    imagen BLOB,
    PRIMARY KEY (IDimagen),
    CONSTRAINT fk_imagen_reto_ FOREIGN KEY (IDreto) REFERENCES Retos(IDreto),
    CONSTRAINT fk_imagen_seguimiento FOREIGN KEY (IDseguimiento) REFERENCES Seguimiento(IDseguimiento),
    CONSTRAINT fk_imagen_evento FOREIGN KEY (IDevento) REFERENCES Eventos(IDevento)
);

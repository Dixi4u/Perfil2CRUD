CREATE TABLE tbUsuarios(
UUID_usuario VARCHAR2(50),
Nombre VARCHAR2(50),
Correo VARCHAR2(50),
Contrasena VARCHAR2(50)
);

CREATE TABLE Ticket(
UUID_ticket VARCHAR2(50),
Titulo VARCHAR2(50),
Descripcion VARCHAR2(250),
Autor VARCHAR2(50),
Email VARCHAR2(50),
Fecha_Creacion DATE,
Estado VARCHAR2(15),
Fecha_FInalizacion DATE
);
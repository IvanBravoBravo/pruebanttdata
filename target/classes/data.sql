CREATE TABLE usuarios (
	id varchar NOT NULL DEFAULT random_uuid(),
	creado timestamp DEFAULT now(),
	modificado timestamp DEFAULT now(),
	ultimo_login timestamp NULL,
	token varchar NULL,
	activo boolean NOT NULL DEFAULT true,
	nombre varchar NULL,
	correo varchar NOT NULL,
	contraseña varchar NOT NULL
);

ALTER TABLE usuarios ADD CONSTRAINT usuarios_pk PRIMARY KEY (id);

CREATE TABLE telefonos (
    id serial NOT NULL,
	numero varchar NOT NULL,
	codigo_ciudad integer NOT NULL,
	codigo_pais varchar NOT NULL,
	usuario_id varchar NOT NULL
);
ALTER TABLE telefonos ADD CONSTRAINT telefonos_pk PRIMARY KEY (id);
ALTER TABLE telefonos ADD CONSTRAINT telefonos_fk FOREIGN KEY (usuario_id) REFERENCES usuarios(id) ON DELETE CASCADE ON UPDATE CASCADE;

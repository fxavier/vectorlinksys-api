CREATE TABLE actor_type(
id SERIAL,
name VARCHAR(100) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE actor(
id BIGSERIAL,
name VARCHAR(100) NOT NULL,
actor_type_id INTEGER,
active BOOLEAN NOT NULL DEFAULT 'true',
PRIMARY KEY(id),
CONSTRAINT fk_actor_actor_type
     FOREIGN KEY(actor_type_id) REFERENCES actor_type(id)
);
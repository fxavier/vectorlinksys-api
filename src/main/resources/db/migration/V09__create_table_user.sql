CREATE TABLE users(
id BIGSERIAL,
name VARCHAR(100) NOT NULL,
email VARCHAR(150) NOT NULL,
active BOOLEAN NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE role(
id BIGSERIAL,
description VARCHAR(100) NOT NULL,
PRIMARY KEY(id)
);

CREATE TABLE user_role(
user_id BIGINT NOT NULL,
role_id BIGINT NOT NULL,
PRIMARY KEY(user_id, role_id),
CONSTRAINT fk_user_role_user
    FOREIGN KEY(user_id) REFERENCES users(id),
CONSTRAINT fk_user_role_role
    FOREIGN KEY(role_id) REFERENCES role(id)
);
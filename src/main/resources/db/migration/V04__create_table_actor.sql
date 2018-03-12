CREATE TABLE actor(
id BIGSERIAL,
actor_code BIGINT NOT NULL,
name VARCHAR(100) NOT NULL,
actor_type VARCHAR(20) NOT NULL,
district_id BIGINT NOT NULL,
active BOOLEAN DEFAULT true,
PRIMARY KEY(id),
CONSTRAINT fk_actor_district FOREIGN KEY(district_id) REFERENCES district(id)
);
CREATE TABLE province(
id BIGSERIAL,
name VARCHAR(100) NOT NULL,
country_id INTEGER,
PRIMARY KEY(id),
active BOOLEAN NOT NULL DEFAULT 'true',
CONSTRAINT fk_province_country 
     FOREIGN KEY(country_id) REFERENCES country(country_id)
);

CREATE TABLE district(
id BIGSERIAL NOT NULL,
name VARCHAR(100) NOT NULL,
province_id BIGINT, 
active BOOLEAN NOT NULL DEFAULT 'true',
PRIMARY KEY(id),
CONSTRAINT fk_province_district
     FOREIGN KEY(province_id) REFERENCES province(id)
);

CREATE TABLE operational_site(
id SERIAL NOT NULL,
name VARCHAR(100) NOT NULL,
target BIGINT NOT NULL DEFAULT '0',
district_id BIGINT,
active BOOLEAN NOT NULL DEFAULT 'true',
PRIMARY KEY(id),
CONSTRAINT fk_op_site_district
     FOREIGN KEY(district_id) REFERENCES district(id)
);

CREATE TABLE locality(
id BIGSERIAL NOT NULL,
name VARCHAR(100) NOT NULL,
operational_site_id INTEGER,
active BOOLEAN NOT NULL DEFAULT 'true',
PRIMARY KEY(id),
CONSTRAINT fk_op_site_locality
     FOREIGN KEY(operational_site_id) REFERENCES operational_site(id)
);

CREATE TABLE village(
id BIGSERIAL NOT NULL,
name VARCHAR(100) NOT NULL,
locality_id INTEGER,
revisits BIGINT NOT NULL DEFAULT '0',
active BOOLEAN NOT NULL DEFAULT 'true',
PRIMARY KEY(id),
CONSTRAINT fk_village_locality
     FOREIGN KEY(locality_id) REFERENCES village(id)
);
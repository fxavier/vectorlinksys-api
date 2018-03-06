CREATE TABLE spray_totals(
id BIGSERIAL,
spray_date DATE NOT NULL,
sprayed_structures BIGINT NOT NULL,
sprayed_man BIGINT NOT NULL,
sprayed_women BIGINT NOT NULL,
sprayed_pregnant_women BIGINT NOT NULL,
sprayed_children BIGINT NOT NULL,
structurs_not_sprayed BIGINT NOT NULL,
not_sprayed_men BIGINT NOT NULL,
not_sprayed_women BIGINT NOT NULL,
not_sprayed_pregnant_women BIGINT NOT NULL,
not_sprayed_children BIGINT NOT NULL,
rooms_found BIGINT NOT NULL,
rooms_sprayed BIGINT NOT NULL,
insecticide_issued INTEGER,
insecticide_full_returned INTEGER,
insecticide_empty_returned INTEGER,
reference VARCHAR(100),
village_id BIGINT,
actor_id BIGINT,
PRIMARY KEY(id),
CONSTRAINT fk_total_spray_actor
     FOREIGN KEY(actor_id) REFERENCES actor(id),
CONSTRAINT fk_total_sprat_village
     FOREIGN KEY(village_id) REFERENCES village(id)     

);

CREATE TABLE spray_details(
id BIGSERIAL,
spray_date DATE  NOT NULL,
household_name VARCHAR(100) NOT NULL,
gender_person_interviwed VARCHAR(10),
household_id VARCHAR(30) NOT NULL,
status VARCHAR(50) NOT NULL,
sprayed_men BIGINT,
sprayed_women BIGINT,
sprayed_pregnant_women BIGINT,
sprayed_children BIGINT,
not_sprayed_men BIGINT,
not_sprayed_women BIGINT,
not_sprayed_pregnant_women BIGINT,
not_sprayed_children BIGINT,
insecticide_issued INTEGER,
insecticide_full_returned INTEGER,
insecticide_empty_returned INTEGER,
rooms_found BIGINT,
rooms_sprayed BIGINT,
reference VARCHAR(100),
village_id BIGINT,
actor_id BIGINT,
PRIMARY KEY(id),
CONSTRAINT fk_detail_spray_actor
     FOREIGN KEY(actor_id) REFERENCES actor(id),
CONSTRAINT fk_detail_sprat_village
     FOREIGN KEY(village_id) REFERENCES village(id)     


);
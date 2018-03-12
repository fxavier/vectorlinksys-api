
CREATE TABLE mob_totals(
id BIGSERIAL,
date_mob DATE NOT NULL,
found_structures BIGINT NOT NULL,
sentitized_structures_yes BIGINT NOT NULL,
sentitized_structures_no BIGINT NOT NULL,
men_sentitized_yes BIGINT NOT NULL,
men_sentitized_no BIGINT NOT NULL,
women_sentitized_yes BIGINT NOT NULL,
women_sentitized_no BIGINT NOT NULL,
spray_accpetance_yes BIGINT NOT NULL,
spray_acceptance_no BIGINT NOT NULL,
reference VARCHAR(100),
actor_id BIGINT,
village_id BIGINT,
PRIMARY KEY(id),
CONSTRAINT fk_total_mob_actor
     FOREIGN KEY(actor_id) REFERENCES actor(id),
CONSTRAINT fk_total_mob_village
     FOREIGN KEY(village_id) REFERENCES village(id)     

);

CREATE TABLE mob_details(
id BIGSERIAL,
date_mob DATE NOT NULL,
house_holder_name VARCHAR(100) NOT NULL,
house_holder_id VARCHAR(100) NOT NULL,
sentitized_structure VARCHAR(10),
reason_not_sentitized_id INTEGER DEFAULT NULL,
men_sentitized BIGINT,
women_sentitized BIGINT,
spray_acceptance VARCHAR(10),
reason_not_accepted INTEGER DEFAULT NULL,
reference VARCHAR(100),
actor_id BIGINT,
village_id BIGINT,
PRIMARY KEY(id),
CONSTRAINT fk_detail_mob_actor
     FOREIGN KEY(actor_id) REFERENCES actor(id),
CONSTRAINT fk_detail_mob_village
     FOREIGN KEY(village_id) REFERENCES village(id)     
)

ALTER TABLE village
DROP CONSTRAINT fk_village_locality,
ADD CONSTRAINT fk_village_locality
     FOREIGN KEY(locality_id) REFERENCES locality(id);
     
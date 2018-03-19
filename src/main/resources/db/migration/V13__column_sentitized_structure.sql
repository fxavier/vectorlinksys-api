ALTER TABLE mob_details
DROP COLUMN sentitized_structure,
DROP COLUMN reason_not_sentitized_id,
DROP COLUMN reason_not_accepted,
ADD COLUMN sentitized_structur VARCHAR(30),
ADD COLUMN reason_not_sentitized VARCHAR(50),
ADD COLUMN reason_not_accepted VARCHAR(50);
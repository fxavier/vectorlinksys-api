ALTER TABLE mob_details
DROP COLUMN sentitized_structur,
ADD COLUMN sentitized_structure VARCHAR(30);


ALTER TABLE spray_totals
DROP COLUMN insecticide_issued,
DROP COLUMN insecticide_full_returned,
DROP COLUMN insecticide_empty_returned,
ADD COLUMN insecticide_issued BIGINT,
ADD COLUMN insecticide_full_returned BIGINT,
ADD COLUMN insecticide_empty_returned BIGINT;

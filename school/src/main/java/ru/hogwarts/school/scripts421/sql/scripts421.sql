ALTER TABLE student ADD CONSTRAINT id_age_check CHECK ( age >=16 );
ALTER TABLE student ADD CONSTRAINT id_name_unique UNIQUE (name);
ALTER TABLE student ALTER COLUMN name SET NOT NULL ;

ALTER TABLE faculty ADD CONSTRAINT id_name_color_faculty UNIQUE (name,color);

ALTER TABLE student ALTER COLUMN age SET DEFAULT 20;

CREATE TABLE if not exists image_transformation (
    id text PRIMARY KEY,
    image_path varchar NOT NULL,
    transformed_image_path VARCHAR NOT NULL,
    transformation_timestamp TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
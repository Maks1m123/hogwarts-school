CREATE TABLE car (
    id SERIAL PRIMARY KEY ,
    name VARCHAR (50),
    model VARCHAR(50),
    price INTEGER
);
CREATE TABLE human (
    id SERIAL PRIMARY KEY ,
    name VARCHAR(100),
    age INTEGER,
    has_driver_license BOOLEAN,
    car_id INTEGER REFERENCES car(id)
)
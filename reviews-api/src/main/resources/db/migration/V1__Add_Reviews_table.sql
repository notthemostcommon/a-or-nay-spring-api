CREATE TABLE reviews (
    id SERIAL,
    user_id INTEGER NOT NULL,
    camis VARCHAR(250) NOT NULL,
    dba VARCHAR(250) NOT NULL,
    bldg VARCHAR(250) NOT NULL,
    street VARCHAR(250) NOT NULL,
    boro VARCHAR(250) NOT NULL,
    zip VARCHAR(250) NOT NULL,
    review TEXT NOT NULL,
    rating INTEGER NOT NULL,
    grade VARCHAR(250),
    category VARCHAR(250)
);


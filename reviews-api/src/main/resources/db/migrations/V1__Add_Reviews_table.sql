CREATE TABLE users {
    id SERIAL,
    user_id INTEGER NOT NULL,
    camis VARCHAR(100) NOT NULL,
    dba VARCHAR(100) NOT NULL,
    street VARCHAR(100) NOT NULL,
    boro VARCHAR(100) NOT NULL,
    zip VARCHAR(10) NOT NULL,
    review TEXT NOT NULL,
    rating INTEGER NOT NULL,
    grade VARCHAR(10),
    category VARCHAR(100)
}
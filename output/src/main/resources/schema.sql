
DROP TABLE county;
DROP TABLE state;
DROP TABLE region;

CREATE TABLE region (
    id SMALLINT PRIMARY KEY,
    name VARCHAR(9) NOT NULL,
    abbreviation CHAR(2) UNIQUE,
    min REAL,
    max REAL,
    average REAL,
    standard_deviation REAL,
    rank INTEGER
);

CREATE TABLE state (
    id SERIAL PRIMARY KEY,
    name VARCHAR(15) NOT NULL,
    abbreviation CHAR(2) UNIQUE,
    region CHAR(2) REFERENCES region (abbreviation),
    min REAL,
    max REAL,
    average REAL,
    standard_deviation REAL,
    rank INTEGER
);

CREATE TABLE county (
    id SERIAL PRIMARY KEY,
    name VARCHAR(25) NOT NULL,
    state CHAR(2) REFERENCES state (abbreviation),
    min REAL,
    max REAL,
    average REAL,
    standard_deviation REAL,
    rank INTEGER
);

INSERT INTO region (id, name, abbreviation) VALUES (1, 'West', 'W');
INSERT INTO region (id, name, abbreviation) VALUES (2, 'Midwest', 'MW');
INSERT INTO region (id, name, abbreviation) VALUES (3, 'Northeast', 'NE');
INSERT INTO region (id, name, abbreviation) VALUES (4, 'South', 'S');

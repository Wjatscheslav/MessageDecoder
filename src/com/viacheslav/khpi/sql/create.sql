DROP TABLE LETTER;
DROP TABLE BIGRAMM;
DROP TABLE WORD;

CREATE TABLE LETTER(
	name VARCHAR(1) PRIMARY KEY,
	frequency INT
);

CREATE TABLE BIGRAMM(
	name VARCHAR(2) PRIMARY KEY,
	frequency INT
);

CREATE TABLE WORD(
	name VARCHAR(40) PRIMARY KEY,
	frequency INT
);
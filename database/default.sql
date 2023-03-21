-- create database user
CREATE ROLE loms WITH
	LOGIN
	SUPERUSER
	CREATEDB
	CREATEROLE
	INHERIT
	NOREPLICATION
	CONNECTION LIMIT -1
	PASSWORD loms;

-- create database
CREATE DATABASE loms
    WITH
    OWNER = loms
    ENCODING = 'UTF8'
    CONNECTION LIMIT = -1;

CREATE TABLE account (
	user_id serial PRIMARY KEY,
	username VARCHAR ( 50 ) UNIQUE NOT NULL,
	password VARCHAR ( 50 ) NOT NULL,
	fristname VARCHAR ( 50 ) NOT NULL,
	lastname VARCHAR ( 50 ) NOT NULL,
	email VARCHAR ( 255 ) UNIQUE NOT NULL,
	created_on TIMESTAMP NOT NULL,
    last_login TIMESTAMP
);
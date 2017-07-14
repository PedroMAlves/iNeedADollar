DROP DATABASE IF EXISTS dollar;
CREATE DATABASE dollar;
USE dollar;

CREATE TABLE user (
  user_name     CHAR(15)  NOT NULL,
  user_password CHAR(15) NOT NULL,
  PRIMARY KEY (user_name)
);

CREATE TABLE bio (
  user_bio         CHAR(15),
  name              CHAR(30),
  email             CHAR(30),
  date_birth        CHAR(30),
  location			CHAR(30),
  bio               VARCHAR(100),
  PRIMARY KEY (user_bio),
  FOREIGN KEY (user_bio) REFERENCES user(user_name) ON DELETE CASCADE
);


CREATE TABLE account  (
  user_account	CHAR(15)  NOT NULL,
  balance		INTEGER  ,
  PRIMARY KEY (user_account),
  FOREIGN KEY (user_account) REFERENCES user(user_name) ON DELETE CASCADE
);


CREATE TABLE request (
  request_id 			INTEGER AUTO_INCREMENT,
  user_name_request 	CHAR(15) NOT NULL,
  user_name_answer		CHAR(15),
  operation_id			INTEGER ,
  request_motiv 		CHAR(50),
  request_answer		CHAR(50),
  state 				CHAR(10),
  date_request 			DATE,
  PRIMARY KEY (request_id, user_name_request),
  FOREIGN KEY (user_name_request) REFERENCES user (user_name) ON DELETE CASCADE
);

CREATE TABLE operation (
  operation_id 	INTEGER AUTO_INCREMENT,
  request_id 		INTEGER NOT NULL UNIQUE,
  user_account		CHAR(15) NOT NULL,
  amount     INTEGER  NOT NULL,
  type 		CHAR(15) NOT NULL,
  PRIMARY KEY (operation_id, request_id, user_account),
  FOREIGN KEY (user_account) REFERENCES account(user_account) ON DELETE CASCADE,
  FOREIGN KEY (request_id) REFERENCES request (request_id)

);


ALTER TABLE request ADD FOREIGN KEY (operation_id) REFERENCES operation (operation_id);
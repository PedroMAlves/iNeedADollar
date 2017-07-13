DROP DATABASE IF EXISTS dollar;
CREATE DATABASE dollar;
USE dollar;

CREATE TABLE user (
  user_name     CHAR(15)  NOT NULL,
  user_password CHAR(150) NOT NULL,
  PRIMARY KEY (user_name)
);

CREATE TABLE bio (
  user_bio         CHAR(15),
  name              CHAR(100),
  email             CHAR(100),
  date_birth        CHAR(30),
  location			CHAR(50),
  bio               VARCHAR(500),
  PRIMARY KEY (user_bio),
  FOREIGN KEY (user_bio) REFERENCES user(user_name) ON DELETE CASCADE
);


CREATE TABLE account  ( 
  user_account		CHAR(15)  NOT NULL,
  balance		INTEGER   NOT NULL, 	
  PRIMARY KEY (user_account),
  FOREIGN KEY (user_account) REFERENCES user(user_name) ON DELETE CASCADE
);


CREATE TABLE request (
  request_id 	INTEGER AUTO_INCREMENT, 
  user_name_request 		CHAR(15) NOT NULL,
  user_name_answer		CHAR(15) NOT NULL,
  operation_id			INTEGER NOT NULL,
  request_motiv 		CHAR(50),
  request_answer		CHAR(50),
  state 				CHAR(10), 
  date_request 			DATE,
  PRIMARY KEY (request_id, user_name_request, user_name_answer, operation_id),
  FOREIGN KEY (user_name_request) REFERENCES user (user_name) ,
  FOREIGN KEY (user_name_answer) REFERENCES user (user_name) 
  
);

CREATE TABLE operation (
  operation_id 	INTEGER AUTO_INCREMENT, 
  request_id 		INTEGER NOT NULL,
  user_account		CHAR(15) NOT NULL,
  amount     INTEGER  NOT NULL,
  type 		CHAR(15) NOT NULL,
  PRIMARY KEY (operation_id, request_id, user_account),
  FOREIGN KEY (user_account) REFERENCES account(user_account) ON DELETE CASCADE,
  FOREIGN KEY (request_id) REFERENCES request (request_id) 
);


ALTER TABLE request ADD FOREIGN KEY (operation_id) REFERENCES operation (operation_id);







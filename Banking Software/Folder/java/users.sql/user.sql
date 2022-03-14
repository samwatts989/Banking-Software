DROP TABLE IF EXISTS balance;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
  account_id int NOT NULL,
  username varchar(255) NOT NULL UNIQUE,     -- Username
  password varchar(32) NOT NULL,      -- Password (hashed, not plain-text)
CONSTRAINT pk_id PRIMARY KEY (account_id)
);		  

CREATE TABLE balance(
account_id int NOT NULL,
balance_amount int NOT NULL,
CONSTRAINT pk_account_id PRIMARY KEY (account_id),
CONSTRAINT fk_account_id FOREIGN KEY (account_id) REFERENCES users(account_id)
);

INSERT INTO users(account_id, username, password) 
VALUES(1234,'TESTUSERNAME', 'TESTPASSWORD');

INSERT INTO balance(account_id, balance_amount) VALUES (1234, 1000);




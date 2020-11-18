CREATE TABLE users (
  login VARCHAR(128) PRIMARY KEY,
  hash NOT NULL,
  salt NOT NULL,
);

CREATE TABLE resources (
  ID PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(128),
  Role SET(READ, WRITE, EXECUTE),
  ResourceName VARCHAR(128),
  FOREIGN KEY (login) REFERENCES users(login)
);

CREATE TABLE sessions (
  login VARCHAR(128) PRIMARY KEY,
  role SET(READ, WRITE, EXECUTE),
  resources VARCHAR(128),
  dateStart DATE,
  dateEnd DATE,
  dataSize INT,
  FOREIGN KEY (login) REFERENCES users(login)
);
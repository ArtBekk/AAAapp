CREATE TABLE users(
  login VARCHAR(16) PRIMARY KEY,
  hash VARCHAR(64) NOT NULL,
  salt VARCHAR(64) NOT NULL);

CREATE TABLE resources(
  id INT PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(16),
  role ENUM('READ', 'WRITE', 'EXECUTE'),
  resource_name VARCHAR(64),
  FOREIGN KEY (login) REFERENCES users(login));

CREATE TABLE SESSIONS(
  id INT PRIMARY KEY AUTO_INCREMENT,
  login VARCHAR(16),
  role ENUM('READ', 'WRITE', 'EXECUTE'),
  resources VARCHAR(64),
  date_start DATE,
  date_end DATE,
  data_size INT,
  FOREIGN KEY (login) REFERENCES users(login));
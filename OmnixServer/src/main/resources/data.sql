DROP TABLE IF EXISTS authentication_user;
 
CREATE TABLE authentication_user (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  user_name VARCHAR(250) NOT NULL,
  password VARCHAR(250) NOT NULL,
  token VARCHAR(250) DEFAULT NULL
);
 
INSERT INTO authentication_user (user_name, password) VALUES
  ('hossam', '123');
DROP DATABASE IF EXISTS test;

CREATE DATABASE test DEFAULT CHARACTER SET 'utf8';

USE test;

create table User
(
	id int(8) NOT NULL PRIMARY KEY,
	name varchar(25),
	age int,
	isAdmin bit,
	createdDate timestamp
);

set names 'utf8';
DELIMITER $$
CREATE PROCEDURE insert_test_data()
BEGIN
  DECLARE i INT DEFAULT 1;
  WHILE i < 51 DO
    INSERT INTO User (id, name, age, isAdmin, createdDate)
    values(i, 'Vlad', i%10,  0, now());
    set i = i + 1;
  END WHILE;
END$$
DELIMITER ;
CALL insert_test_data();
DROP PROCEDURE insert_test_data;
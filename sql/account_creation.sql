create user 'mha'@'localhost' identified by 'Strongpassword1!';

GRANT SELECT, INSERT, DELETE, EXECUTE ON mha_db.* TO 'mha'@'localhost';
create user 'mha'@'localhost' identified by 'strongpassword';

GRANT SELECT, INSERT, DELETE, EXECUTE ON mha_db.* TO 'mha'@'localhost';
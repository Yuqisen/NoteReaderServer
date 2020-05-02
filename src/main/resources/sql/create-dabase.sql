# Create Table
CREATE DATABASE `yt_reader` DEFAULT CHARACTER SET 'utf8mb4' default collate 'utf8mb4_general_ci';

# Create User
CREATE USER 'reader'@'%' IDENTIFIED BY 'HCWzEr4X9F@2VnJUV';
GRANT ALL ON yt_reader.* TO 'reader'@'%' IDENTIFIED BY 'HCWzEr4X9F@2VnJUV' WITH GRANT OPTION ;
FLUSH PRIVILEGES ;
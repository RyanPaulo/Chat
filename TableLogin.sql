CREATE DATABASE chat;

use chat;

CREATE TABLE login (

id_usuario int primary key,
nome varchar(50) not null,
email varchar(50) not null,
senha varchar(50) not null

)ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE login
  MODIFY id_usuario int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=01;
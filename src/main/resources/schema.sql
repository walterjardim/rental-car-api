CREATE TABLE IF NOT EXISTS `users` (
  id bigint AUTO_INCREMENT primary key,
  first_name varchar(50) not null,
  last_name varchar(50) not null,
  email varchar(50) not null,
  birthday date not null,
  login varchar(50) not null,
  password varchar(255) not null,
  phone varchar(15) not null
);

CREATE TABLE IF NOT EXISTS `cars` (
  id bigint AUTO_INCREMENT primary key,
  manufacture_year integer not null,
  license_plate varchar(50) not null,
  model varchar(50) not null,
  color varchar(20) not null
);
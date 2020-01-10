DROP SCHEMA IF EXISTS `05-many-to-many`;

CREATE SCHEMA `05-many-to-many`;

use `05-many-to-many`;

SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `driver_detail`;

CREATE TABLE `driver_detail` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `transport_type` varchar(10) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;


DROP TABLE IF EXISTS `driver`;

CREATE TABLE `driver` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  `driver_detail_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_DETAIL_idx` (`driver_detail_id`),
  CONSTRAINT `FK_DETAIL` FOREIGN KEY (`driver_detail_id`) REFERENCES `driver_detail` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `bus_line`;

CREATE TABLE `bus_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(128) DEFAULT NULL,
  `driver_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  UNIQUE KEY `TITLE_UNIQUE` (`title`),

  KEY `FK_DRIVER_idx` (`driver_id`),

  CONSTRAINT `FK_DRIVER`
  FOREIGN KEY (`driver_id`)
  REFERENCES `driver` (`id`)

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `vehicle_on_line`;

CREATE TABLE `vehicle_on_line` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(256) DEFAULT NULL,
  `bus_line_id` int(11) DEFAULT NULL,

  PRIMARY KEY (`id`),

  KEY `FK_BUS_LINE_ID_idx` (`bus_line_id`),

  CONSTRAINT `FK_BUS_LINE`
  FOREIGN KEY (`bus_line_id`)
  REFERENCES `bus_line` (`id`)

  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `passenger`;

CREATE TABLE `passenger` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(45) DEFAULT NULL,
  `last_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP TABLE IF EXISTS `bus_line_passenger`;

CREATE TABLE `bus_line_passenger` (
  `bus_line_id` int(11) NOT NULL,
  `passenger_id` int(11) NOT NULL,
  
  PRIMARY KEY (`bus_line_id`,`passenger_id`),
  
  KEY `FK_passenger_idx` (`passenger_id`),
  
  CONSTRAINT `FK_bus_line_05` FOREIGN KEY (`bus_line_id`) 
  REFERENCES `bus_line` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION,
  
  CONSTRAINT `FK_passenger` FOREIGN KEY (`passenger_id`) 
  REFERENCES `passenger` (`id`) 
  ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

SET FOREIGN_KEY_CHECKS = 1;

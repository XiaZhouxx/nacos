CREATE TABLE `route_info` (
`id` int NOT NULL AUTO_INCREMENT,
`route_id` varchar(1024) DEFAULT NULL,
`route_definition` text,
PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3
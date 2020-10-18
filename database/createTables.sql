SET character_set_client = utf8mb4 ;

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `first_name` varchar (50) NOT NULL,
    `last_name` varchar (50) NOT NULL,
    `email` varchar (50) NOT NULL,
    `password` varchar (80) NOT NULL,
    `birthday_date` timestamp NOT NULL,
    `register_date` timestamp NOT NULL,
    `last_access_date` TIMESTAMP NOT NULL,
    `last_update_date` TIMESTAMP DEFAULT NULL,
    `enabled` boolean NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `authority` varchar (50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `user_authorities`;
CREATE TABLE `user_authorities` (
  `id_user` BIGINT NOT NULL,
  `id_authority` BIGINT NOT NULL,
  PRIMARY KEY (`id_authority`,`id_user`),
  CONSTRAINT `FK_AUTHORITY` FOREIGN KEY (`id_authority`) REFERENCES `authorities` (`id`),
  CONSTRAINT `FK_USER` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
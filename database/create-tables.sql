SET character_set_client = utf8mb4;

DROP TABLE IF EXISTS `users_authorities`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
(
    `id`                   BIGINT      NOT NULL AUTO_INCREMENT,
    `first_name`           VARCHAR(50) NOT NULL,
    `last_name`            VARCHAR(50) NOT NULL,
    `email`                VARCHAR(50) NOT NULL,
    `password`             VARCHAR(80) NOT NULL,
    `birthday_date`        TIMESTAMP   NOT NULL,
    `register_date`        TIMESTAMP   NOT NULL,
    `delete_date`          TIMESTAMP DEFAULT NULL,
    `last_access_date`     TIMESTAMP   NOT NULL,
    `last_update_date`     TIMESTAMP DEFAULT NULL,
    `password_update_date` TIMESTAMP   NOT NULL,
    `enabled`              BOOLEAN     NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `authorities`
(
    `id`   BIGINT      NOT NULL AUTO_INCREMENT,
    `name` VARCHAR(50) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE `users_authorities`
(
    `id_user`      BIGINT NOT NULL,
    `id_authority` BIGINT NOT NULL,
    PRIMARY KEY (`id_authority`, `id_user`),
    CONSTRAINT `FK_AUTHORITY` FOREIGN KEY (`id_authority`) REFERENCES `authorities` (`id`),
    CONSTRAINT `FK_USER` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
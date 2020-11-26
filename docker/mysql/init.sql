SET character_set_client = utf8mb4;

CREATE TABLE IF NOT EXISTS `users`
(
    `id`                      BIGINT       NOT NULL AUTO_INCREMENT,
    `first_name`              VARCHAR(50)  NOT NULL,
    `last_name`               VARCHAR(50)  NOT NULL,
    `email`                   VARCHAR(50)  NOT NULL,
    `password`                VARCHAR(80)  NOT NULL,
    `birthday_date`           TIMESTAMP    NOT NULL,
    `created_by`              VARCHAR(100) NOT NULL,
    `created_at`              TIMESTAMP    NOT NULL,
    `last_updated_by`         VARCHAR(100) NOT NULL,
    `last_updated_at`         TIMESTAMP    NOT NULL,
    `locked_at`               TIMESTAMP DEFAULT NULL,
    `last_access_at`          TIMESTAMP DEFAULT NULL,
    `last_password_update_at` TIMESTAMP DEFAULT NULL,
    `enabled`                 BOOLEAN   DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `authorities`
(
    `id`              BIGINT       NOT NULL AUTO_INCREMENT,
    `name`            VARCHAR(50)  NOT NULL,
    `created_by`      VARCHAR(100) NOT NULL,
    `created_at`      TIMESTAMP    NOT NULL,
    `last_updated_by` VARCHAR(100) NOT NULL,
    `last_updated_at` TIMESTAMP    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

CREATE TABLE IF NOT EXISTS `users_authorities`
(
    `id_user`      BIGINT NOT NULL,
    `id_authority` BIGINT NOT NULL,
    PRIMARY KEY (`id_authority`, `id_user`),
    CONSTRAINT `FK_AUTHORITY` FOREIGN KEY (`id_authority`) REFERENCES `authorities` (`id`),
    CONSTRAINT `FK_USER` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
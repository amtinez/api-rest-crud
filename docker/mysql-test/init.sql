SET character_set_client = utf8mb4;

DROP TABLE IF EXISTS `users_authorities`;
DROP TABLE IF EXISTS `authorities`;
DROP TABLE IF EXISTS `users`;

CREATE TABLE `users`
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

INSERT INTO users (first_name, last_name, email, password, birthday_date, created_by, created_at, last_updated_by, last_updated_at, enabled)
VALUES ('User', 'One', 'user@one.com', 'testing', sysdate(), 'User One', sysdate(), 'User One', sysdate(), TRUE);
INSERT INTO users (first_name, last_name, email, password, birthday_date, created_by, created_at, last_updated_by, last_updated_at, enabled)
VALUES ('User', 'Two', 'user@two.com', 'testing', sysdate(), 'User Two', sysdate(), 'User Two', sysdate(), FALSE);

CREATE TABLE `authorities`
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

INSERT INTO authorities (name, created_by, created_at, last_updated_by, last_updated_at)
VALUES ('Authority_One', 'User One', sysdate(), 'User One', sysdate());
INSERT INTO authorities (name, created_by, created_at, last_updated_by, last_updated_at)
VALUES ('Authority_Two', 'User Two', sysdate(), 'User Two', sysdate());

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

INSERT INTO users_authorities (id_user, id_authority)
VALUES (1, 1);
INSERT INTO users_authorities (id_user, id_authority)
VALUES (2, 2);
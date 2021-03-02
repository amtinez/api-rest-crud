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
    `locked_by`               VARCHAR(100) DEFAULT NULL,
    `locked_at`               TIMESTAMP    DEFAULT NULL,
    `locked_reason`           VARCHAR(100) DEFAULT NULL,
    `last_access_at`          TIMESTAMP    DEFAULT NULL,
    `last_password_update_at` TIMESTAMP    DEFAULT NULL,
    `enabled`                 BOOLEAN      DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_email` (`email`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO users (first_name, last_name, email, password, birthday_date, created_by, created_at, last_updated_by, last_updated_at, last_access_at, last_password_update_at, enabled)
VALUES ('Admin', 'Admin', 'admin@admin.com', '$2a$10$9FLFNGN/dql1T7eyeiSE8e9RuUYQMb9dqj0SL82BTyHkaX0nCXuPC', sysdate(), 'Admin Admin', sysdate(), 'Admin Admin', sysdate(), sysdate(), sysdate(), TRUE);

CREATE TABLE IF NOT EXISTS `roles`
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

INSERT INTO roles (name, created_by, created_at, last_updated_by, last_updated_at)
VALUES ('ADMIN', 'Admin Admin', sysdate(), 'Admin Admin', sysdate());
INSERT INTO roles (name, created_by, created_at, last_updated_by, last_updated_at)
VALUES ('USER', 'Admin Admin', sysdate(), 'Admin Admin', sysdate());

CREATE TABLE IF NOT EXISTS `users_roles`
(
    `id_user` BIGINT NOT NULL,
    `id_role` BIGINT NOT NULL,
    PRIMARY KEY (`id_role`, `id_user`),
    CONSTRAINT `FK_ROLE` FOREIGN KEY (`id_role`) REFERENCES `roles` (`id`),
    CONSTRAINT `FK_USER` FOREIGN KEY (`id_user`) REFERENCES `users` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

INSERT INTO users_roles (id_user, id_role)
VALUES (1, 1);
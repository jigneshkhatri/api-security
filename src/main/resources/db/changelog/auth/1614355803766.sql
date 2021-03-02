

CREATE TABLE `users` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(120) NOT NULL,
  `email` VARCHAR(120) NULL,
  `mobile` VARCHAR(15) NULL,
  `password` VARCHAR(100) NULL,
  `role_id` BIGINT NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

CREATE TABLE `user_tokens` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `user_id` BIGINT NOT NULL,
  `token` VARCHAR(60) NOT NULL,
  `issued_at` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `expires_in` INT NULL,
  `device_id` VARCHAR(100) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `token_UNIQUE` (`token` ASC) VISIBLE);

ALTER TABLE `user_tokens` ADD CONSTRAINT `user_tokens_userFk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

CREATE TABLE `roles` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `code` VARCHAR(45) NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);

ALTER TABLE `users` ADD CONSTRAINT `users_roleFk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

CREATE TABLE `apis` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `code` VARCHAR(45) NOT NULL,
  `path` VARCHAR(60) NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `code_UNIQUE` (`code` ASC) VISIBLE);

CREATE TABLE `role_api` (
  `id` BIGINT NOT NULL AUTO_INCREMENT,
  `role_id` BIGINT NOT NULL,
  `api_id` BIGINT NOT NULL,
  `status` TINYINT(2) NOT NULL,
  `created_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP,
  `updated_on` DATETIME NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`));

ALTER TABLE `role_api` ADD CONSTRAINT `role_api_roleFk` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`);

ALTER TABLE `role_api` ADD CONSTRAINT `role_api_apiFk` FOREIGN KEY (`api_id`) REFERENCES `apis` (`id`);

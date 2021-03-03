
INSERT INTO `roles` (`name`, `code`, `status`) VALUES ('Admin', 'ADMIN', '1');

INSERT INTO `roles` (`name`, `code`, `status`) VALUES ('User', 'USER', '1');

INSERT INTO `apis` (`name`, `code`, `path`, `status`) VALUES ('My Details', 'MY_DETAILS', '/user/s/me', '1');

SET @adminRoleId = (SELECT `id` FROM `roles` WHERE `code`='ADMIN');

SET @myDetailApiId = (SELECT `id` FROM `apis` WHERE `code`='MY_DETAILS');

INSERT INTO `role_api` (`role_id`, `api_id`, `status`) VALUES (@adminRoleId, @myDetailApiId, '1');


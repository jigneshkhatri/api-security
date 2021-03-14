

INSERT INTO `apis` (`name`, `code`, `path`, `status`) VALUES ('My Active Logins', 'ACTIVE_LOGINS', '/user/s/activeLogins', '1');

INSERT INTO `apis` (`name`, `code`, `path`, `status`) VALUES ('Revoke Token', 'REVOKE_TOKEN', '/user/s/revokeToken/{id}', '1');

SET @myDetailsAPI = (SELECT `id` FROM `apis` WHERE `code`='MY_DETAILS');
SET @myActiveLoginsAPI = (SELECT `id` FROM `apis` WHERE `code`='ACTIVE_LOGINS');
SET @revokeTokenAPI = (SELECT `id` FROM `apis` WHERE `code`='REVOKE_TOKEN');

SET @adminRole = (SELECT `id` FROM `roles` WHERE `code`='ADMIN');
SET @userRole = (SELECT `id` FROM `roles` WHERE `code`='USER');

INSERT INTO `role_api` (`role_id`, `api_id`, `status`) VALUES (@userRole, @myDetailsAPI, '1');

INSERT INTO `role_api` (`role_id`, `api_id`, `status`) VALUES (@userRole, @myActiveLoginsAPI, '1');

INSERT INTO `role_api` (`role_id`, `api_id`, `status`) VALUES (@userRole, @revokeTokenAPI, '1');

INSERT INTO `role_api` (`role_id`, `api_id`, `status`) VALUES (@adminRole, @myActiveLoginsAPI, '1');

INSERT INTO `role_api` (`role_id`, `api_id`, `status`) VALUES (@adminRole, @revokeTokenAPI, '1');


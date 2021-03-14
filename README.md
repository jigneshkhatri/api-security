# quallit-spring-boot-starter

Spring boot starter project, with all the abstract classes for controller, service, entity and DTO, and utility classes. It also contains API security layer, which is only enabled by annotation `@EnableQuallitApiSecurity` (details below).

### Source Java version: 11

### Dependencies used:
* Spring boot starter Web/Data JPA/Validation
* Liquibase
* Mapstruct
* Spring security crypto
* Javax Validation

### Abstract components:
* **AbstractEntity:**
  * Class (Mapped Super Class), which contains primary key Id mapping. Can be extended by user-defined entities.
  * Example syntax: `public class TestEntity extends AbstractEntity {..}`
* **AbstractDTO:**
  * Class, which contains Id and other common fields. Can be extended by user-defined DTOs.
  * Example syntax: `public class TestDTO extends AbstractDTO {..}`
* **IAbstractController:**
  * Interface, which contains basic utility methods for controllers. Can be implemented by user-defined controllers.
  * _**IMPORTANT:**_ To use this interface, it is mandatory for entities and DTOs to extend `AbstractEntity` and `AbstractDTO` respectively.
  * Example syntax: `public class TestController implements IAbstractController<Entity, DTO> {..}`
* **IReadService:**
  * Interface, which contains basic common methods for read operations. Can be implemented by user-defined services.
  * _**IMPORTANT:**_ To use this interface, it is mandatory for entities and DTOs to extend `AbstractEntity` and `AbstractDTO` respectively.
  * Example syntax: `public class TestService implements IReadService<Entity> {..}`
* **ICreateUpdateService:**
  * Interface, which contains basic common methods for create and update operations. Can be implemented by user-defined services.
  * _**IMPORTANT:**_ To use this interface, it is mandatory for entities and DTOs to extend `AbstractEntity` and `AbstractDTO` respectively.
  * Example syntax: `public class TestService implements ICreateUpdateService<Entity> {..}`
* **IDeleteService:**
  * Interface, which contains basic common methods for delete operations. Can be implemented by user-defined services.
  * _**IMPORTANT:**_ To use this interface, it is mandatory for entities and DTOs to extend `AbstractEntity` and `AbstractDTO` respectively.
  * Example syntax: `public class TestService implements IDeleteService<Entity> {..}`
* **IAbstractMapper:**
  * Interface, which declares basic methods for entity to DTO conversion and vice versa. Can be extended by user-defind Mapstruct mappers.
  * _**IMPORTANT:**_ To use this interface, it is mandatory for entities and DTOs to extend `AbstractEntity` and `AbstractDTO` respectively.
  * Example syntax: `public interface TestMapper extends IAbstractMapper<Entity, DTO> {..}`

### Utility classes:
* **ObjectUtil:**
  * Contains all static utility methods to work with Objects. To see the full list of methods, please look the source of `com.quallit.springbootstarter.utilities.ObjectUtil` class.
* **ResponseBody:**
  * Class to wrap the response into, and provide standard response to API callers.
  * Example syntax: `return ResponseEntity.ok(new ResponseBody<>(data));`
* **APIError:**
  * Class to wrap the error related fields, and provide standard error response to API callers.
  * This error object can also be wrapped in `ResponseBody` before returning.
  * Example syntax: `return ResponseEntity.ok(new ResponseBody<>(new APIError.APIErrorBuilder(HttpStatus.INTERNAL_SERVER_ERROR, errMsg).build()));`

### Exception classes:
* **QuallitException:**
  * Abstract class, which extends `RuntimeException`, and can be base to other user-defined exceptions (Because this exception is handled in `ControllerAdvice`, which is described below).
* **AuthException:**
  * Extends `QuallitException`, and is thrown whenever any authentication/authorization related error occurs, while using Quallit API Security layer (described below).
* **BusinessRuleValidationException:**
  * Extends `QuallitException`, and is thrown whenever any business rule fails - like if username already exists or password is incorrect, etc., while using Quallit API Security layer.

### Quallit API Security layer:

* To enable Quallit API Security layer, add `@EnableQuallitApiSecurity` annotation on any `configuration` class or `main` class of spring boot project.
* Once it is enabled, it will create below tables in database, with the help of liquibase:
  * `users` - Contains basic user details along with role
  * `roles` - Contains roles, which can be assigned to users
  * `apis` - Should contain all the APIs, which needs to be secured. The APIs which contains `/s/` in their request path, are secured by default when Quallit API Security layer is enabled.
  * `role_api` - Maps APIs, with the allowed roles
  * `user_tokens` - Contains the access tokens, and other user login details

#### Endpoints to consider:
* **User Registration:**
  * Endpoint: `POST /user/register`
  * Request body:
    * ```
      {
          "name": "Jigs",
          "mobile": "1234567890",
          "email": "test@test.com (optional)",
          "password": "123465",
          "role": "ADMIN"
      }
      ```
  * Possible response:
    * 200: When user is created successfully. All user data is returned in response body.
    * 500: When required fields are missing, or mobile number is not unique, or any other validation fails. Proper error message will be included in response body.
  * NOTE: Password is hased by BCrypt alogrithm, and only hash is stored in database.
* **Login:**
  * Endpoint: `POST /user/login`
  * Request body:
    * ```
      {
          "username": "1234567890 (or email)",
          "password": "123465"
      }
      ```
  * Possible response:
    * 200: When user is logged in successully. Token and other login details are returned in response body. Below is the sample response body:
      * ```
        {
          "data": {
              "id": 1,
              "createdOn": "2021-03-12T19:02:39.000+00:00",
              "updatedOn": "2021-03-12T19:02:39.000+00:00",
              "status": "ACTIVE",
              "name": "Jigs",
              "mobile": "123456789",
              "email": "test@test.com",
              "roleId": 1,
              "userToken": {
                  "token": "8uWLA3BTovfvUuTCAfrhGX8zgZKzz3G6",
                  "issuedAt": "2021-03-14T06:04:46.095+00:00",
                  "expiresIn": -1 (Number of seconds to expire this token. -1 or NULL means it will never expire.)
              }
           }
        }
        ```
    * 500: If username and/or password are invalid or user is not active, or there is any error in login. Proper error message will be included in response body.
* **My (User's Own) Details:**
  * Endpoint: `GET /user/s/me`
  * Header: `Q-AUTH` Value: `<token>`
  * Possible response:
    * 200: If token is valid, user's details will be returned in response body.
    * 403: If token is not valid or expired.
    * 500: If anything wents wrong. Proper error message will be included in response body.
* **Active Logins:**
  * Endpoint: `GET /user/s/activeLogins`
  * Header: `Q-AUTH` Value: `<token>`
  * Possible response:
     * 200: If token is valid, all the user's active logins (sessions) will be returned. Below is the sample response body:
       * ```
         {
           "data": [
               {
                   "id": 2,
                   "userId": 1,
                   "issuedAt": "2021-03-12T19:33:48.000+00:00",
                   "expiresIn": -1,
                   "deviceId": "PostmanRuntime/7.26.10",
                   "os": "UnKnown, More-Info: PostmanRuntime/7.26.10",
                   "ip": "127.0.1.1",
                   "location": "127.0.1.1",
                   "browser": "UnKnown, More-Info: PostmanRuntime/7.26.10"
               },
               {
                   "id": 4,
                   "userId": 1,
                   "issuedAt": "2021-03-13T18:51:46.000+00:00",
                   "expiresIn": -1,
                   "deviceId": "PostmanRuntime/7.26.10",
                   "os": "UnKnown, More-Info: PostmanRuntime/7.26.10",
                   "ip": "127.0.1.1",
                   "browser": "UnKnown, More-Info: PostmanRuntime/7.26.10"
               }
            ]
         }
         ```
      * 403: If token is not valid or expired.
      * 500: If anything wents wrong. Proper error message will be included in response body.
* **Revoke any own active token:**
  * Endpoint: `DELETE /user/s/revokeToken/4 (token Id)`
  * Header: `Q-AUTH` Value: `<token>`
  * Possible response:
    * 200: If token is successfully deleted, `true` will be returned in response body.
    * 403: If token is not valid or expired, or passed token Id does not belong to user whose token is passed in `Q-AUTH` header.
    * 500: If anything wents wrong. Proper error message will be included in response body.

#### How it works?
* When user gets authenticated successfully, a random auth token is generated and saved in `user_tokens` table, against that user, along with other user's device details.
* All the secured endpoints (which contains `/s/` in their request path), are intercepted by `AuthInterceptor` and token is verified. If token is invalid or expired, then `403` status is returned. If token is valid, but user's role is not allowed to access that particular API (by checking request path in `role_api` table), then again `403` status is returned. If till here everything is fine, request is forwarded to respective controller from interceptor.

#### Configurations available:
* `token.expiry.duration`: Token expiry time in seconds. If `<0` then it will be considered as infinite, and token will never expire.
  * Default: 3600
* `header.auth.name`: Header name, which should contain access token.
  * Default: Q-AUTH
* `multi.device.login.allowed`: If true, user can have multiple active logins (active tokens) simultaneously. If false, user can have only one login (active token) at a time.
  * Possible values: `true` or `false`
  * Default: true
* `track.user.device.info`: If true, user's device info like IP, OS, Browser, etc. will be saved when user will login. If false, these information will not be saved.
  * Possible values: `true` or `false`
  * Default: true  

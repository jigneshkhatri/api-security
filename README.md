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
* Once it is enabled, it will create below tables in database:
  * `users` - Contains basic user details along with role
  * `roles` - Contains roles, which can be assigned to users
  * `apis` - Should contain all the APIs, which needs to be secured. The APIs which contains `/s/` in their request path, are secured by default when Quallit API Security layer is enabled.
  * `role_api` - Maps roles, with the allowed APIs
  * `user_tokens` - Contains the access tokens, and other user login details

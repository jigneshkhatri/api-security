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
* **IAbstractController:**
  * Interface, which contains basic utility methods for controllers. Can be implemented by user-defined controllers.
  * Example syntax: `public class TestController implements IAbstractController<Entity, DTO> {..}`
* **IReadService:**
  * Interface, which contains basic common methods for read operations. Can be implemented by user-defined services.
  * Example syntax: `public class TestService implements IReadService<Entity> {..}`
* **ICreateUpdateService:**
  * Interface, which contains basic common methods for create and update operations. Can be implemented by user-defined services.
  * Example syntax: `public class TestService implements ICreateUpdateService<Entity> {..}`
* **IDeleteService:**
  * Interface, which contains basic common methods for delete operations. Can be implemented by user-defined services.
  * Example syntax: `public class TestService implements IDeleteService<Entity> {..}`
* **AbstractEntity:**
  * Class (Mapped Super Class), which contains primary key Id mapping. Can be extended by user-defined entities.
  * _**IMPORTANT:**_ To use above abstract controller and service interfaces, it is mandatory for entity to extend `AbstractEntity`.
  * Example syntax: `public class TestEntity extends AbstractEntity {..}`
* **AbstractDTO:**
  * Class, which contains Id and other common fields. Can be extended by user-defined DTOs.
  * _**IMPORTANT:**_ To use above abstract controller and service interfaces, it is mandatory for DTO to extend `AbstractDTO`.
  * Example syntax: `public class TestDTO extends AbstractDTO {..}`

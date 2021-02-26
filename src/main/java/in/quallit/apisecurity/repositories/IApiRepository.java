/**
 * 
 */
package in.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.apisecurity.entities.Api;

/**
 * @author JIGS
 *
 */
@Repository
public interface IApiRepository extends JpaRepository<Api, Long> {

}

/**
 * 
 */
package in.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.apisecurity.entities.RoleApi;

/**
 * @author JIGS
 *
 */
@Repository
public interface IRoleApiRepository extends JpaRepository<RoleApi, Long> {

}

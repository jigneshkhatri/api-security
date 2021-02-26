/**
 * 
 */
package in.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.apisecurity.entities.User;

/**
 * @author JIGS
 *
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

}

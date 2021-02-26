/**
 * 
 */
package in.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import in.quallit.apisecurity.entities.UserToken;

/**
 * @author JIGS
 *
 */
@Repository
public interface IUserTokenRepository extends JpaRepository<UserToken, Long> {

}

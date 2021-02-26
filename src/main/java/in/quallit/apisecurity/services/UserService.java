/**
 * 
 */
package in.quallit.apisecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import in.quallit.apisecurity.entities.User;
import in.quallit.apisecurity.repositories.IUserRepository;
import in.quallit.apisecurity.services.common.ICreateUpdateService;
import in.quallit.apisecurity.services.common.IReadService;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService implements ICreateUpdateService<User>, IReadService<User> {

	@Autowired
	private IUserRepository userRepository;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return this.userRepository;
	}

}

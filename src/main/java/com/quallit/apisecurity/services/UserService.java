/**
 * 
 */
package com.quallit.apisecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quallit.apisecurity.entities.User;
import com.quallit.apisecurity.entities.UserToken;
import com.quallit.apisecurity.exceptions.AuthException;
import com.quallit.apisecurity.repositories.IUserRepository;
import com.quallit.apisecurity.services.common.ICreateUpdateService;
import com.quallit.apisecurity.services.common.IReadService;
import com.quallit.apisecurity.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class UserService implements ICreateUpdateService<User>, IReadService<User> {

	@Autowired
	private IUserRepository userRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserTokenService userTokenService;

	@Override
	public JpaRepository<User, Long> getRepository() {
		return this.userRepository;
	}

	@Transactional
	@Override
	public User saveOrUpdate(User obj) {
		if (ObjectUtil.isNotEmpty(obj.getPassword())) {
			obj.setPassword(passwordEncoder.encode(obj.getPassword()));
		}
		return ICreateUpdateService.super.saveOrUpdate(obj);
	}

	public User myDetails() {
		long userId = ObjectUtil.getCurrentUserId();
		return findById(userId, null);
	}

	@Transactional
	public User login(String username, String password) {
		User user = this.userRepository.findByEmailOrMobile(username);
		if (ObjectUtil.isEmpty(user)) {
			throw new AuthException(AuthException.Codes.QA_005);
		}
		if (this.passwordEncoder.matches(password, user.getPassword())) {
			throw new AuthException(AuthException.Codes.QA_006);
		}
		UserToken userToken = this.userTokenService.saveToken(user.getId());
		user.setUserToken(userToken);
		return user;
	}

}

/**
 * 
 */
package com.quallit.springbootstarter.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quallit.springbootstarter.dtos.LoginDTO;
import com.quallit.springbootstarter.entities.Role;
import com.quallit.springbootstarter.entities.User;
import com.quallit.springbootstarter.entities.UserToken;
import com.quallit.springbootstarter.enums.StatusEnum;
import com.quallit.springbootstarter.exceptions.AuthException;
import com.quallit.springbootstarter.exceptions.BusinessRuleValidationException;
import com.quallit.springbootstarter.repositories.IUserRepository;
import com.quallit.springbootstarter.services.common.ICreateUpdateService;
import com.quallit.springbootstarter.services.common.IReadService;
import com.quallit.springbootstarter.utilities.ObjectUtil;

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

	@Value("${multi.device.login.allowed}")
	private Boolean isMultiDeviceLoginAllowed;

	@Autowired
	private RoleService roleService;

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
		if (ObjectUtil.isNotEmpty(obj.getEmail())
				&& this.userRepository.countByEmail(obj.getEmail(), obj.getId()) > 0l) {
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_002);
		}
		if (ObjectUtil.isNotEmpty(obj.getMobile())
				&& this.userRepository.countByMobile(obj.getMobile(), obj.getId()) > 0l) {
			throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_003);
		}
		if (ObjectUtil.isEmpty(obj.getId())) {
			Role role = this.roleService.findByCode(obj.getRole());
			if (ObjectUtil.isEmpty(role)) {
				throw new BusinessRuleValidationException(BusinessRuleValidationException.Codes.QBRV_001);
			}
			obj.setRoleId(role.getId());
		}
		return ICreateUpdateService.super.saveOrUpdate(obj);
	}

	public User myDetails() {
		long userId = ObjectUtil.getCurrentUserId();
		return findById(userId, null);
	}

	@Transactional
	public User login(LoginDTO loginDTO) {
		User user = this.userRepository.findByEmailOrMobile(loginDTO.getUsername());
		if (ObjectUtil.isEmpty(user)) {
			throw new AuthException(AuthException.Codes.QA_005);
		}
		if (user.getStatus() != StatusEnum.ACTIVE) {
			throw new AuthException(AuthException.Codes.QA_007);
		}
		if (!this.passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
			throw new AuthException(AuthException.Codes.QA_006);
		}
		if (!ObjectUtil.isTrue(this.isMultiDeviceLoginAllowed, true)) {
			// multi device login is not allowed.. so delete older token so user gets logout
			// from older device
			this.userTokenService.deleteByUserId(user.getId());
		}
		UserToken userToken = this.userTokenService.saveToken(user.getId(), loginDTO);
		user.setUserToken(userToken);
		return user;
	}

}

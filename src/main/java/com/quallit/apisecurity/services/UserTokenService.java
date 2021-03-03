/**
 * 
 */
package com.quallit.apisecurity.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quallit.apisecurity.entities.UserToken;
import com.quallit.apisecurity.repositories.IUserTokenRepository;
import com.quallit.apisecurity.services.common.IReadService;
import com.quallit.apisecurity.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class UserTokenService implements IReadService<UserToken> {

	@Autowired
	private IUserTokenRepository userTokenRepository;

	@Value("${token.expiry.duration}")
	private Integer tokenExpiryDuration;

	@Override
	public JpaRepository<UserToken, Long> getRepository() {
		return this.userTokenRepository;
	}

	public UserToken findByToken(String token) {
		if (ObjectUtil.isEmpty(token)) {
			return null;
		}
		return this.userTokenRepository.findByToken(token);
	}

	@Transactional
	public UserToken saveToken(long userId) {
		String token = ObjectUtil.getRandomTokenString();
		UserToken obj = new UserToken();
		obj.setExpiresIn(this.tokenExpiryDuration);
		obj.setIssuedAt(new Date());
		obj.setToken(token);
		obj.setUserId(userId);
		return this.userTokenRepository.save(obj);
	}

	@Transactional
	public void deleteByUserId(long userId) {
		this.userTokenRepository.deleteByUserId(userId);
	}

}

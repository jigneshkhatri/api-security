/**
 * 
 */
package com.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quallit.apisecurity.entities.UserToken;

/**
 * @author JIGS
 *
 */
@Repository
public interface IUserTokenRepository extends JpaRepository<UserToken, Long> {

	public UserToken findByToken(String token);

	public void deleteByUserId(Long userId);
}

/**
 * 
 */
package com.quallit.apisecurity.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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

	public Boolean existsByIdAndUserId(Long id, Long userId);

	@Query(value = "select * FROM user_tokens " + " where ((expires_in is null or expires_in < 0) "
			+ " or (expires_in > -1 and unix_timestamp()-unix_timestamp(issued_at) < ifnull(expires_in, 0))) "
			+ " and user_id=:userId", nativeQuery = true)
	public List<UserToken> findActiveLogins(Long userId);
}

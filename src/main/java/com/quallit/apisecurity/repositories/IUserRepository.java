/**
 * 
 */
package com.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quallit.apisecurity.entities.User;

/**
 * @author JIGS
 *
 */
@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

	@Query("SELECT u FROM User u WHERE u.email=:username OR u.mobile=:username")
	public User findByEmailOrMobile(String username);

	@Query("SELECT COUNT(u.id) FROM User u WHERE u.email=:email AND (:id IS NULL OR u.id != :id)")
	public Long countByEmail(String email, Long id);

	@Query("SELECT COUNT(u.id) FROM User u WHERE u.mobile=:mobile AND (:id IS NULL OR u.id != :id)")
	public Long countByMobile(String mobile, Long id);
}

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
}

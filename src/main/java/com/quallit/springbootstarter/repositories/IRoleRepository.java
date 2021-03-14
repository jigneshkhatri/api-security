/**
 * 
 */
package com.quallit.springbootstarter.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quallit.springbootstarter.entities.Role;
import com.quallit.springbootstarter.enums.StatusEnum;

/**
 * @author JIGS
 *
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

	public Role findByCodeAndStatus(String code, StatusEnum status);
}

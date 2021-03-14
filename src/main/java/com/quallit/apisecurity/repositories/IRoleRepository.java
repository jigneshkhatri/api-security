/**
 * 
 */
package com.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quallit.apisecurity.entities.Role;
import com.quallit.apisecurity.enums.StatusEnum;

/**
 * @author JIGS
 *
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

	public Role findByCodeAndStatus(String code, StatusEnum status);
}

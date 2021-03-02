/**
 * 
 */
package com.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.quallit.apisecurity.entities.Role;

/**
 * @author JIGS
 *
 */
@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {

}

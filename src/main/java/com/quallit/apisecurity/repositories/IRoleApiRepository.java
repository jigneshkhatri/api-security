/**
 * 
 */
package com.quallit.apisecurity.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.quallit.apisecurity.entities.RoleApi;
import com.quallit.apisecurity.enums.StatusEnum;

/**
 * @author JIGS
 *
 */
@Repository
public interface IRoleApiRepository extends JpaRepository<RoleApi, Long> {

	@Query("SELECT COUNT(ra.id) FROM RoleApi ra " + " INNER JOIN Api a ON (ra.apiId=a.id) "
			+ " INNER JOIN Role r ON (ra.roleId=r.id) " + " INNER JOIN User u ON (r.id=u.roleId) "
			+ " WHERE u.id=:userId AND a.path=:apiPath " + " AND ra.status=:status AND a.status=:status "
			+ " AND r.status=:status AND u.status=:status")
	public Long countByUserIdAndApiPath(Long userId, String apiPath, StatusEnum status);
}

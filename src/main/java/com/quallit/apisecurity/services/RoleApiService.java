/**
 * 
 */
package com.quallit.apisecurity.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quallit.apisecurity.entities.RoleApi;
import com.quallit.apisecurity.enums.StatusEnum;
import com.quallit.apisecurity.repositories.IRoleApiRepository;
import com.quallit.apisecurity.services.common.IReadService;

/**
 * @author JIGS
 *
 */
@Service
@Transactional(readOnly = true)
public class RoleApiService implements IReadService<RoleApi> {

	@Autowired
	private IRoleApiRepository roleApiRepository;

	@Override
	public JpaRepository<RoleApi, Long> getRepository() {
		return this.roleApiRepository;
	}

	public boolean existsByUserIdAndApiPaths(long userId, List<String> apiPaths) {
		return this.roleApiRepository.countByUserIdAndApiPaths(userId, apiPaths, StatusEnum.ACTIVE) > 0;
	}

}

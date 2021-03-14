/**
 * 
 */
package com.quallit.springbootstarter.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quallit.springbootstarter.entities.RoleApi;
import com.quallit.springbootstarter.enums.StatusEnum;
import com.quallit.springbootstarter.repositories.IRoleApiRepository;
import com.quallit.springbootstarter.services.common.IReadService;

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

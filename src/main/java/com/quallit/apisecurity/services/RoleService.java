package com.quallit.apisecurity.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.quallit.apisecurity.entities.Role;
import com.quallit.apisecurity.enums.StatusEnum;
import com.quallit.apisecurity.repositories.IRoleRepository;
import com.quallit.apisecurity.services.common.IReadService;
import com.quallit.apisecurity.utilities.ObjectUtil;

@Service
@Transactional(readOnly = true)
public class RoleService implements IReadService<Role> {

	@Autowired
	private IRoleRepository roleRepository;

	@Override
	public JpaRepository<Role, Long> getRepository() {
		return this.roleRepository;
	}

	public Role findByCode(String code) {
		if (ObjectUtil.isEmpty(code)) {
			return null;
		}
		return this.roleRepository.findByCodeAndStatus(code, StatusEnum.ACTIVE);
	}
}

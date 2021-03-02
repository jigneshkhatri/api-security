/**
 * 
 */
package com.quallit.apisecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.quallit.apisecurity.dtos.UserDTO;
import com.quallit.apisecurity.entities.User;
import com.quallit.apisecurity.mappers.common.IAbstractMapper;

/**
 * @author JIGS
 *
 */
@Mapper
public interface IUserMapper extends IAbstractMapper<User, UserDTO> {

	IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
}

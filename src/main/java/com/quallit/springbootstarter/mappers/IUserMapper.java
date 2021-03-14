/**
 * 
 */
package com.quallit.springbootstarter.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.quallit.springbootstarter.dtos.UserDTO;
import com.quallit.springbootstarter.entities.User;
import com.quallit.springbootstarter.mappers.common.IAbstractMapper;

/**
 * @author JIGS
 *
 */
@Mapper
public interface IUserMapper extends IAbstractMapper<User, UserDTO> {

	IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
}

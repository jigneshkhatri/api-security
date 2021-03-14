/**
 * 
 */
package com.quallit.apisecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.quallit.apisecurity.dtos.UserTokenDTO;
import com.quallit.apisecurity.entities.UserToken;
import com.quallit.apisecurity.mappers.common.IAbstractMapper;

/**
 * @author jigs
 *
 */
@Mapper
public interface IUserTokenMapper extends IAbstractMapper<UserToken, UserTokenDTO> {

	IUserTokenMapper INSTANCE = Mappers.getMapper(IUserTokenMapper.class);
}

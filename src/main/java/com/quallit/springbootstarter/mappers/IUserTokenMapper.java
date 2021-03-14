/**
 * 
 */
package com.quallit.springbootstarter.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.quallit.springbootstarter.dtos.UserTokenDTO;
import com.quallit.springbootstarter.entities.UserToken;
import com.quallit.springbootstarter.mappers.common.IAbstractMapper;

/**
 * @author jigs
 *
 */
@Mapper
public interface IUserTokenMapper extends IAbstractMapper<UserToken, UserTokenDTO> {

	IUserTokenMapper INSTANCE = Mappers.getMapper(IUserTokenMapper.class);
}

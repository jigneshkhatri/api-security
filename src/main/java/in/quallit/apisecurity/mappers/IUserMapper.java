/**
 * 
 */
package in.quallit.apisecurity.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import in.quallit.apisecurity.dtos.UserDTO;
import in.quallit.apisecurity.entities.User;
import in.quallit.apisecurity.mappers.common.IAbstractMapper;

/**
 * @author JIGS
 *
 */
@Mapper
public interface IUserMapper extends IAbstractMapper<User, UserDTO> {

	IUserMapper INSTANCE = Mappers.getMapper(IUserMapper.class);
}

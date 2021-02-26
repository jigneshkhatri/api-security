/**
 * 
 */
package in.quallit.apisecurity.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import in.quallit.apisecurity.constants.AppEntityCodes;
import in.quallit.apisecurity.controllers.common.IAbstractController;
import in.quallit.apisecurity.dtos.UserDTO;
import in.quallit.apisecurity.dtos.common.ResponseBody;
import in.quallit.apisecurity.entities.User;
import in.quallit.apisecurity.mappers.IUserMapper;
import in.quallit.apisecurity.services.UserService;

/**
 * @author JIGS
 *
 */
@RestController
@RequestMapping(AppEntityCodes.USER)
public class UserController implements IAbstractController<User, UserDTO> {

	@Autowired
	private UserService userService;

	private IUserMapper userMapper = IUserMapper.INSTANCE;

	@PostMapping(SECURE_PATH)
	public ResponseEntity<ResponseBody<UserDTO>> saveOrUpdate(@RequestBody UserDTO user) {
		createObjectForSaveOrUpdate(user);
		User obj = this.userMapper.toEntity(user);
		obj = userService.saveOrUpdate(obj);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(obj)));
	}

	@GetMapping(SECURE_PATH + "{id}")
	public ResponseEntity<ResponseBody<UserDTO>> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(userService.findById(id, null))));
	}

	@Override
	public void responseCleanUp(User entity) {
		// TODO Auto-generated method stub

	}

}

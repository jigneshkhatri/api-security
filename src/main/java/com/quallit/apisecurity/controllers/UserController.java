/**
 * 
 */
package com.quallit.apisecurity.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quallit.apisecurity.constants.AppEntityCodes;
import com.quallit.apisecurity.controllers.common.IAbstractController;
import com.quallit.apisecurity.dtos.UserDTO;
import com.quallit.apisecurity.dtos.common.ResponseBody;
import com.quallit.apisecurity.entities.User;
import com.quallit.apisecurity.mappers.IUserMapper;
import com.quallit.apisecurity.services.UserService;
import com.quallit.apisecurity.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@RestController
@RequestMapping(AppEntityCodes.USER)
public class UserController implements IAbstractController<User, UserDTO> {

	// routes - start
	private static final String REGISTER = "register";
	private static final String LOGIN = "login";
	private static final String ME = SECURE_PATH + "/me";
	// routes - end

	@Autowired
	private UserService userService;

	private IUserMapper userMapper = IUserMapper.INSTANCE;

	@PostMapping(REGISTER)
	public ResponseEntity<ResponseBody<UserDTO>> register(@RequestBody UserDTO user) {
		createObjectForSaveOrUpdate(user);
		User obj = this.userMapper.toEntity(user);
		obj = userService.saveOrUpdate(obj);
		this.responseCleanUp(obj);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(obj)));
	}

	@PostMapping(LOGIN)
	public ResponseEntity<ResponseBody<UserDTO>> login(@RequestBody Map<String, String> usernameAndPassword) {
		User user = userService.login(usernameAndPassword.get("username"), usernameAndPassword.get("password"));
		this.responseCleanUp(user);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(user)));
	}

	@GetMapping(ME)
	public ResponseEntity<ResponseBody<UserDTO>> myDetails(HttpServletRequest httpServletRequest) {
		User user = userService.myDetails();
		this.responseCleanUp(user);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(user)));
	}

	@GetMapping
	public void test() {

	}

	@Override
	public void responseCleanUp(User entity) {
		if (ObjectUtil.isEmpty(entity)) {
			return;
		}
		entity.setPassword(null);
		if (ObjectUtil.isNotEmpty(entity.getUserToken())) {
			entity.getUserToken().setDeviceId(null);
			entity.getUserToken().setUserId(null);
			entity.getUserToken().setId(null);
		}
	}

}

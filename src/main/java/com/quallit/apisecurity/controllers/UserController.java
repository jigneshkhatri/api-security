/**
 * 
 */
package com.quallit.apisecurity.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.quallit.apisecurity.constants.AppEntityCodes;
import com.quallit.apisecurity.controllers.common.IAbstractController;
import com.quallit.apisecurity.dtos.LoginDTO;
import com.quallit.apisecurity.dtos.UserDTO;
import com.quallit.apisecurity.dtos.UserTokenDTO;
import com.quallit.apisecurity.dtos.common.AbstractDTO;
import com.quallit.apisecurity.dtos.common.ResponseBody;
import com.quallit.apisecurity.entities.User;
import com.quallit.apisecurity.entities.UserToken;
import com.quallit.apisecurity.mappers.IUserMapper;
import com.quallit.apisecurity.mappers.IUserTokenMapper;
import com.quallit.apisecurity.services.UserService;
import com.quallit.apisecurity.services.UserTokenService;
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
	private static final String MY_ALL_ACTIVE_LOGINS = SECURE_PATH + "/activeLogins";
	private static final String REVOKE_TOKEN = SECURE_PATH + "/revokeToken/{id}";
	// routes - end

	@Autowired
	private UserService userService;

	private IUserMapper userMapper = IUserMapper.INSTANCE;

	@Autowired
	private UserTokenService userTokenService;

	private IUserTokenMapper userTokenMapper = IUserTokenMapper.INSTANCE;

	@PostMapping(REGISTER)
	public ResponseEntity<ResponseBody<UserDTO>> register(
			@Validated(AbstractDTO.SaveValidationGroup.class) @RequestBody UserDTO user) {
		createObjectForSaveOrUpdate(user);
		User obj = this.userMapper.toEntity(user);
		obj = userService.saveOrUpdate(obj);
		this.responseCleanUp(obj);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(obj)));
	}

	@PostMapping(LOGIN)
	public ResponseEntity<ResponseBody<UserDTO>> login(@Valid @RequestBody LoginDTO loginDTO,
			HttpServletRequest httpServletRequest) {
		User user = userService.login(loginDTO);
		this.responseCleanUp(user);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(user)));
	}

	@GetMapping(ME)
	public ResponseEntity<ResponseBody<UserDTO>> myDetails(HttpServletRequest httpServletRequest) {
		User user = userService.myDetails();
		this.responseCleanUp(user);
		return ResponseEntity.ok(new ResponseBody<>(this.userMapper.toDTO(user)));
	}

	@GetMapping(MY_ALL_ACTIVE_LOGINS)
	public ResponseEntity<ResponseBody<List<UserTokenDTO>>> activeLogins() {
		List<UserToken> userTokens = this.userTokenService.activeLogins();
		if (ObjectUtil.isNotEmpty(userTokens)) {
			userTokens.forEach(single -> single.setToken(null));
		}
		return ResponseEntity.ok(new ResponseBody<>(this.userTokenMapper.toDTOList(userTokens)));
	}

	@DeleteMapping(REVOKE_TOKEN)
	public ResponseEntity<ResponseBody<Boolean>> revokeToken(@PathVariable("id") Long id) {
		this.userTokenService.deleteById(id);
		return ResponseEntity.ok(new ResponseBody<>(true));
	}

	@Override
	public void responseCleanUp(User entity) {
		if (ObjectUtil.isEmpty(entity)) {
			return;
		}
		entity.setPassword(null);
		if (ObjectUtil.isNotEmpty(entity.getUserToken())) {
			entity.getUserToken().setUserId(null);
			entity.getUserToken().setId(null);
			entity.getUserToken().setDeviceId(null);
			entity.getUserToken().setBrowser(null);
			entity.getUserToken().setIp(null);
			entity.getUserToken().setLocation(null);
			entity.getUserToken().setOs(null);
		}
	}

}

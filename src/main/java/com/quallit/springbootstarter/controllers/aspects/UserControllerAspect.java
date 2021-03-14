/**
 * 
 */
package com.quallit.springbootstarter.controllers.aspects;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.quallit.springbootstarter.dtos.LoginDTO;
import com.quallit.springbootstarter.exceptions.AuthException;
import com.quallit.springbootstarter.services.UserTokenService;
import com.quallit.springbootstarter.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Aspect
@Component
public class UserControllerAspect {

	private final static String LOGIN = "execution(* com.quallit.springbootstarter.controllers.UserController.login(..))";
	private final static String REVOKE_TOKEN = "execution(* com.quallit.springbootstarter.controllers.UserController.revokeToken(..))";

	@Autowired
	private UserTokenService userTokenService;

	@Value("${track.user.device.info}")
	private Boolean trackUserDeviceInfo;

	@Before(LOGIN)
	public void beforeLogin(JoinPoint joinPoint) throws Exception {
		LoginDTO loginDTO = (LoginDTO) joinPoint.getArgs()[0];
		String ip = null;
		String browser = null;
		String deviceId = null;
		String os = null;
		String referer = null;
		if (ObjectUtil.isTrue(trackUserDeviceInfo, false)) {
			// only store if tracking is enabled
			HttpServletRequest httpServletRequest = (HttpServletRequest) joinPoint.getArgs()[1];
			ip = ObjectUtil.getClientIpAddress(httpServletRequest);
			browser = ObjectUtil.getClientBrowser(httpServletRequest);
			deviceId = ObjectUtil.getUserAgent(httpServletRequest);
			os = ObjectUtil.getClientOS(httpServletRequest);
			referer = ObjectUtil.getReferer(httpServletRequest);
		}
		loginDTO.setIp(ip);
		loginDTO.setBrowser(browser);
		loginDTO.setDeviceId(deviceId);
		loginDTO.setOs(os);
		loginDTO.setReferer(referer);
	}

	@Before(REVOKE_TOKEN)
	public void beforeRevokeToken(JoinPoint joinPoint) throws Exception {
		Long tokenId = (Long) joinPoint.getArgs()[0];
		if (!this.userTokenService.verifyTokenIdBelongsToUser(tokenId)) {
			// this token doesnot belongs to user, so do not permit this operation
			throw new AuthException(AuthException.Codes.QA_008);
		}
	}
}

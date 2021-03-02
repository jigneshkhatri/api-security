/**
 * 
 */
package com.quallit.apisecurity.config.interceptors;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

import com.quallit.apisecurity.constants.Constants;
import com.quallit.apisecurity.controllers.common.IAbstractController;
import com.quallit.apisecurity.entities.UserToken;
import com.quallit.apisecurity.exceptions.AuthException;
import com.quallit.apisecurity.services.RoleApiService;
import com.quallit.apisecurity.services.UserTokenService;
import com.quallit.apisecurity.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
@Component
public class AuthInterceptor implements HandlerInterceptor {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthInterceptor.class);

	@Value("${header.auth.name}")
	private String authHeaderName;

	private static final String SECURE_PATH = IAbstractController.SECURE_PATH;

	@Autowired
	private UserTokenService userTokenService;

	@Autowired
	private RoleApiService roleApiService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		LOGGER.info("===> Authenticating Request - Start");

		String apiPath = request.getServletPath();

		List<String> pathTokens = Arrays.asList(apiPath.split("/"));
		if (!pathTokens.contains(SECURE_PATH)) {
			return true;
		}

		String token = request.getHeader(authHeaderName);

		if (ObjectUtil.isEmpty(token)) {
			throw new AuthException(AuthException.Codes.QA_001);
		}

		UserToken ut = this.userTokenService.findByToken(token);

		if (ObjectUtil.isEmpty(ut)) {
			throw new AuthException(AuthException.Codes.QA_002);
		}

		if (ObjectUtil.isNotEmpty(ut.getExpiresIn()) && ut.getExpiresIn() > -1) {
			Date now = new Date();
			long diff = now.getTime() - ut.getIssuedAt().getTime();
			if ((diff / 1000) > ut.getExpiresIn()) {
				throw new AuthException(AuthException.Codes.QA_003);
			}
		}

		if (!this.roleApiService.existsByUserIdAndApiPath(ut.getUserId(), apiPath)) {
			throw new AuthException(AuthException.Codes.QA_004);
		}

		RequestContextHolder.currentRequestAttributes().setAttribute(Constants.AUTHENTICATED_USER_ID_ATTR_KEY,
				ut.getUserId(), RequestAttributes.SCOPE_REQUEST);

		LOGGER.info("===> Authenticating Request - End");
		return true;
	}

}

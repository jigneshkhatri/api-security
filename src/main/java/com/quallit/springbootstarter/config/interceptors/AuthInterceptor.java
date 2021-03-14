/**
 * 
 */
package com.quallit.springbootstarter.config.interceptors;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.quallit.springbootstarter.constants.Constants;
import com.quallit.springbootstarter.controllers.common.IAbstractController;
import com.quallit.springbootstarter.entities.UserToken;
import com.quallit.springbootstarter.exceptions.AuthException;
import com.quallit.springbootstarter.services.RoleApiService;
import com.quallit.springbootstarter.services.UserTokenService;
import com.quallit.springbootstarter.utilities.ObjectUtil;

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

		final String requestMethod = request.getMethod();
		final HandlerMethod method = (HandlerMethod) handler;
		List<String> mappingUrls = this.getMappingUrls(requestMethod, method);

		if (!this.roleApiService.existsByUserIdAndApiPaths(ut.getUserId(), mappingUrls)) {
			throw new AuthException(AuthException.Codes.QA_004);
		}

		RequestContextHolder.currentRequestAttributes().setAttribute(Constants.AUTHENTICATED_USER_ID_ATTR_KEY,
				ut.getUserId(), RequestAttributes.SCOPE_REQUEST);

		LOGGER.info("===> Authenticating Request - End");
		return true;
	}

	private List<String> getMappingUrls(String requestMethod, HandlerMethod handlerMethod) {
		String entityType = "/";
		if (handlerMethod.getBeanType().isAnnotationPresent(RequestMapping.class)) {
			RequestMapping requestMapping = handlerMethod.getBeanType().getAnnotation(RequestMapping.class);
			entityType += requestMapping.value()[0] + "/";
		}
		if (handlerMethod.hasMethodAnnotation(RequestMapping.class)) {
			RequestMapping requestMapping = handlerMethod.getMethodAnnotation(RequestMapping.class);
			return concatStrInArray(requestMapping.value(), entityType);
		}
		switch (requestMethod) {
		case "GET":
			GetMapping getMapping = handlerMethod.getMethodAnnotation(GetMapping.class);
			return concatStrInArray(getMapping.value(), entityType);
		case "POST":
			PostMapping postMapping = handlerMethod.getMethodAnnotation(PostMapping.class);
			return concatStrInArray(postMapping.value(), entityType);
		case "PUT":
			PutMapping putMapping = handlerMethod.getMethodAnnotation(PutMapping.class);
			return concatStrInArray(putMapping.value(), entityType);
		case "DELETE":
			DeleteMapping deleteMapping = handlerMethod.getMethodAnnotation(DeleteMapping.class);
			return concatStrInArray(deleteMapping.value(), entityType);
		case "PATCH":
			PatchMapping patchMapping = handlerMethod.getMethodAnnotation(PatchMapping.class);
			return concatStrInArray(patchMapping.value(), entityType);
		}
		return Collections.emptyList();
	}

	private List<String> concatStrInArray(String[] arr, String prefix) {
		List<String> retValue = new ArrayList<>();
		for (String single : arr) {
			retValue.add(prefix + single);
		}
		return retValue;
	}

}

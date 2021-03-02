/**
 * 
 */
package com.quallit.apisecurity.utilities;

import java.security.SecureRandom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import javax.persistence.Tuple;
import javax.persistence.TupleElement;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.collection.spi.PersistentCollection;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.quallit.apisecurity.constants.Constants;

/**
 * @author JIGS
 *
 */
public class ObjectUtil {

	private static final SecureRandom secureRandom = new SecureRandom();
	private static final Base64.Encoder base64Encoder = Base64.getUrlEncoder();

	public static boolean isTrue(final Object obj, boolean defaultValue) {
		if (isNotEmpty(obj)) {
			if ((obj instanceof Boolean && ((Boolean) obj).booleanValue())
					|| (obj instanceof String && (obj.toString().equalsIgnoreCase("true")))) {
				return true;
			} else if ((obj instanceof Boolean && !((Boolean) obj).booleanValue())
					|| (obj instanceof String && (obj.toString().equalsIgnoreCase("false")))) {
				return false;
			}
		}
		return defaultValue;
	}

	/**
	 * Checks if is not null.
	 *
	 * @param obj the obj
	 * @return true, if is not null
	 */
	public static boolean isNotNull(Object obj) {
		return obj != null;
	}

	/**
	 * Checks if is null.
	 *
	 * @param obj the obj
	 * @return true, if is null
	 */
	public static boolean isNull(Object obj) {
		return !isNotNull(obj);
	}

	/**
	 * Checks if is not empty.
	 *
	 * @param obj the obj
	 * @return true, if is not empty
	 */
	@SuppressWarnings("rawtypes")
	public static boolean isNotEmpty(Object obj) {
		if (isNotNull(obj)) {
			if (obj instanceof Collection) {
				return !((Collection) obj).isEmpty();
			} else if (obj instanceof String) {
				return ((String) obj).length() > 0;
			}
			return true;
		}
		return false;
	}

	/**
	 * Checks if is empty.
	 *
	 * @param obj the obj
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Object obj) {
		return !isNotEmpty(obj);
	}

	/**
	 * Collection stream.
	 *
	 * @param obj the obj
	 * @return the stream
	 */
	// TODO: JIGS: Need to check this method. Not working.
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Stream<Collection> collectionStream(Collection obj) {
		if (isNotEmpty(obj)) {
			return obj.stream();
		}
		return Stream.empty();
	}

	/**
	 * Gets the safe string value.
	 *
	 * @param str the str
	 * @return the safe string value
	 */
	public static String getSafeStringValue(String str) {
		return isEmpty(str) ? "" : str;
	}

	/**
	 * Gets the authorization header.
	 *
	 * @param httpServletRequest the http servlet request
	 * @return the authorization header
	 */
	public static String getAuthorizationHeader(HttpServletRequest httpServletRequest) {
		return httpServletRequest.getHeader("Authorization");
	}

	/**
	 * Was collection initialized.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public static boolean wasCollectionInitialized(Object c) {
		if (!(c instanceof PersistentCollection)) {
			return true;
		}

		PersistentCollection pc = (PersistentCollection) c;
		return pc.wasInitialized();
	}

	/**
	 * Was object initialized.
	 *
	 * @param c the c
	 * @return true, if successful
	 */
	public static boolean wasObjectInitialized(Object c) {
		if (!(c instanceof HibernateProxy)) {
			return true;
		}

		HibernateProxy pc = (HibernateProxy) c;
		return !pc.getHibernateLazyInitializer().isUninitialized();
	}

	/**
	 * Parses the date.
	 *
	 * @param date   the date
	 * @param format the format
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date parseDate(String date, String format) throws ParseException {
		if (ObjectUtil.isEmpty(format)) {
			format = Constants.DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(date);
	}

	/**
	 * Format date to string.
	 *
	 * @param date   the date
	 * @param format the format
	 * @return the string
	 */
	public static String formatDateToString(Date date, String format) {
		if (isEmpty(date)) {
			return null;
		}
		if (ObjectUtil.isEmpty(format)) {
			format = Constants.DEFAULT_DATE_FORMAT;
		}
		return new SimpleDateFormat(format).format(date);
	}

	/**
	 * Format date.
	 *
	 * @param date   the date
	 * @param format the format
	 * @return the date
	 * @throws ParseException the parse exception
	 */
	public static Date formatDate(Date date, String format) throws ParseException {
		if (ObjectUtil.isEmpty(format)) {
			format = Constants.DEFAULT_DATE_FORMAT;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.parse(sdf.format(date));
	}

	/**
	 * Encoded string.
	 *
	 * @param source the source
	 * @return the string
	 */
	public static String encodedString(String source) {
		Base64.Encoder encoder = Base64.getEncoder();
		return encoder.encodeToString(source.getBytes());
	}

	/**
	 * Decoded string.
	 *
	 * @param source the source
	 * @return the string
	 */
	public static String decodedString(String source) {
		Base64.Decoder decoder = Base64.getDecoder();
		return new String(decoder.decode(source));
	}

	/**
	 * Get Current User
	 * 
	 * @param userService
	 * @return User
	 */

//	public static User getCurrentUser(UserService userService) {
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		if (ObjectUtil.isNotNull(authentication) && !(authentication instanceof AnonymousAuthenticationToken)) {
//			String currentUserEmail = authentication.getName();
//			return userService.findByEmail(currentUserEmail, null);
//		}
//		return null;
//	}

	/**
	 * Get Current User Id
	 * 
	 * @param UserService
	 * @return UserId
	 */

	public static Long getCurrentUserId() {
		return (Long) RequestContextHolder.currentRequestAttributes()
				.getAttribute(Constants.AUTHENTICATED_USER_ID_ATTR_KEY, RequestAttributes.SCOPE_REQUEST);
	}

	public static String getEffectiveString(String primary, String secondary, String defaultValue) {
		if (ObjectUtil.isNotEmpty(primary)) {
			return primary;
		}
		return ObjectUtil.isNotEmpty(secondary) ? secondary : defaultValue;
	}

	public static List<String> stringToList(String commaSeparatedStr) {
		if (isEmpty(commaSeparatedStr)) {
			return Collections.emptyList();
		}
		String[] arr = commaSeparatedStr.split(",");
		List<String> retValue = new ArrayList<>();
		for (String single : arr) {
			retValue.add(single.trim());
		}
		return retValue;

	}

	public static String idListToCommaSeparatedString(List<Long> ids) {
		if (isEmpty(ids)) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		String prefix = "";
		for (Long single : ids) {
			str.append(prefix);
			prefix = ",";
			str.append(single);
		}
		return str.toString();
	}

	public static String idListToCommaSeparatedStringWithQuotes(List<?> ids) {
		if (isEmpty(ids)) {
			return "";
		}
		StringBuilder str = new StringBuilder();
		String prefix = "";
		for (Object single : ids) {
			str.append(prefix);
			prefix = ",";
			str.append("\'").append(single.toString()).append("\'");
		}
		return str.toString();
	}

	public static String getNextNo(String currentValue, String prefix, String suffix, Integer startsFrom) {
		prefix = isNotEmpty(prefix) ? prefix : "";
		suffix = isNotEmpty(suffix) ? suffix : "";
		startsFrom = isNotEmpty(startsFrom) ? startsFrom : 1;

		if (isEmpty(currentValue)) {
			return prefix + startsFrom + suffix;
		}
		currentValue = currentValue.replace(prefix, "");
		currentValue = currentValue.replace(suffix, "");
		try {
			Integer seq = Integer.parseInt(currentValue) + 1;
			if (seq > 0) {
				return prefix + seq + suffix;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prefix + startsFrom + suffix;
	}

	public static Double objectToDouble(Object obj, Double defaultValue) {
		if (isEmpty(obj))
			return defaultValue;
		return Double.parseDouble(obj.toString());
	}

	public static Long objectToLong(Object obj, Long defaultValue) {
		if (isEmpty(obj))
			return defaultValue;
		return Long.parseLong(obj.toString());
	}

	public static Date objectToDate(Object obj, Date defaultValue) {
		if (isEmpty(obj))
			return defaultValue;
		return (Date) obj;
	}

	public static List<Map<String, Object>> convertTuplesToMap(List<Tuple> tuples) {
		List<Map<String, Object>> result = new ArrayList<>();
		for (Tuple single : tuples) {
			Map<String, Object> tempMap = new HashMap<>();
			for (TupleElement<?> key : single.getElements()) {
				tempMap.put(key.getAlias(), single.get(key));
			}
			result.add(tempMap);
		}
		return result;
	}

	public static String getRandomTokenString() {
		byte bytes[] = new byte[24];
		secureRandom.nextBytes(bytes);
		return base64Encoder.encodeToString(bytes);
	}
}

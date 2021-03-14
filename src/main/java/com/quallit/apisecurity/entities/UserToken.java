/**
 * 
 */
package com.quallit.apisecurity.entities;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.quallit.apisecurity.entities.common.AbstractEntity;

/**
 * @author JIGS
 *
 */
@Entity
@Table(name = "`user_tokens`")
public class UserToken extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2300518001096998011L;

	private Long userId;
	private String token;

	@Temporal(TemporalType.TIMESTAMP)
	private Date issuedAt;
	private Integer expiresIn;
	private String deviceId;
	private String os;
	private String ip;
	private String location;
	private String browser;
	private String referer;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the token
	 */
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the issuedAt
	 */
	public Date getIssuedAt() {
		return issuedAt;
	}

	/**
	 * @param issuedAt the issuedAt to set
	 */
	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	/**
	 * @return the expiresIn
	 */
	public Integer getExpiresIn() {
		return expiresIn;
	}

	/**
	 * @param expiresIn the expiresIn to set
	 */
	public void setExpiresIn(Integer expiresIn) {
		this.expiresIn = expiresIn;
	}

	/**
	 * @return the deviceId
	 */
	public String getDeviceId() {
		return deviceId;
	}

	/**
	 * @param deviceId the deviceId to set
	 */
	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getBrowser() {
		return browser;
	}

	public void setBrowser(String browser) {
		this.browser = browser;
	}

	public String getReferer() {
		return referer;
	}

	public void setReferer(String referer) {
		this.referer = referer;
	}

}

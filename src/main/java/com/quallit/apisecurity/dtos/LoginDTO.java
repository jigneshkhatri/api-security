/**
 * 
 */
package com.quallit.apisecurity.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

/**
 * @author jigs
 *
 */
public class LoginDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7348087338284289767L;

	@NotNull
	private String username;
	@NotNull
	private String password;
	private String deviceId;
	private String os;
	private String ip;
	private String location;
	private String browser;
	private String referer;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDeviceId() {
		return deviceId;
	}

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

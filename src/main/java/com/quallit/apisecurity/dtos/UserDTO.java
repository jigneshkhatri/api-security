/**
 * 
 */
package com.quallit.apisecurity.dtos;

import javax.validation.constraints.NotNull;

import com.quallit.apisecurity.dtos.common.AbstractDTO;

/**
 * @author JIGS
 *
 */
public class UserDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7663847048422620624L;

	@NotNull(groups = { AbstractDTO.SaveValidationGroup.class })
	private String name;
	private String email;

	@NotNull(groups = { AbstractDTO.SaveValidationGroup.class })
	private String mobile;

	@NotNull(groups = { AbstractDTO.SaveValidationGroup.class })
	private String password;
	private Long roleId;
	private UserTokenDTO userToken;

	@NotNull(groups = { AbstractDTO.SaveValidationGroup.class })
	private String role;

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the mobile
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}

	/**
	 * @return the userToken
	 */
	public UserTokenDTO getUserToken() {
		return userToken;
	}

	/**
	 * @param userToken the userToken to set
	 */
	public void setUserToken(UserTokenDTO userToken) {
		this.userToken = userToken;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

}

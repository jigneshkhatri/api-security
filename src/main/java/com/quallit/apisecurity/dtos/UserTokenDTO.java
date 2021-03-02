/**
 * 
 */
package com.quallit.apisecurity.dtos;

import java.util.Date;

import com.quallit.apisecurity.dtos.common.AbstractDTO;

/**
 * @author JIGS
 *
 */
public class UserTokenDTO extends AbstractDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5024613160908601306L;

	private Long userId;
	private String token;

	private Date issuedAt;
	private Integer expiresIn;

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

}

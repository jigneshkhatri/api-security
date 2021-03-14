/**
 * 
 */
package com.quallit.springbootstarter.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.quallit.springbootstarter.entities.common.AbstractEntity;
import com.quallit.springbootstarter.enums.StatusEnum;

/**
 * @author JIGS
 *
 */
@Entity
@Table(name = "`roles`")
public class Role extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -763328725221623504L;

	private String name;
	private String code;
	private StatusEnum status;

	@Column(name = "created_on", insertable = false, updatable = false)
	private Date createdOn;

	@Column(name = "created_on", insertable = false, updatable = false)
	private Date updatedOn;

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
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the status
	 */
	public StatusEnum getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusEnum status) {
		this.status = status;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

}

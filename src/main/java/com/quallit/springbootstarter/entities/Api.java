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
@Table(name = "`apis`")
public class Api extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2013009470220620901L;

	private String name;
	private String code;
	private String path;
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
	 * @return the path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
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

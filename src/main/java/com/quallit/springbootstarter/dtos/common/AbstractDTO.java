/**
 * 
 */
package com.quallit.springbootstarter.dtos.common;

import java.io.Serializable;
import java.util.Date;

import com.quallit.springbootstarter.enums.StatusEnum;

/**
 * @author JIGS
 *
 */
public abstract class AbstractDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8568508424201024648L;

	private Long id;

	/** The created on. */
	private Date createdOn;

	/** The updated on. */
	private Date updatedOn;

	/** The created by. */
	private Long createdBy;

	/** The updated by. */
	private Long updatedBy;

	/** The active status. */
	private StatusEnum status;

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the createdOn
	 */
	public Date getCreatedOn() {
		return createdOn;
	}

	/**
	 * @param createdOn the createdOn to set
	 */
	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}

	/**
	 * @return the updatedOn
	 */
	public Date getUpdatedOn() {
		return updatedOn;
	}

	/**
	 * @param updatedOn the updatedOn to set
	 */
	public void setUpdatedOn(Date updatedOn) {
		this.updatedOn = updatedOn;
	}

	/**
	 * @return the createdBy
	 */
	public Long getCreatedBy() {
		return createdBy;
	}

	/**
	 * @param createdBy the createdBy to set
	 */
	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * @return the updatedBy
	 */
	public Long getUpdatedBy() {
		return updatedBy;
	}

	/**
	 * @param updatedBy the updatedBy to set
	 */
	public void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
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

	// Default Validation Groups - Start
	public static interface SaveValidationGroup {
	}

	public static interface UpdateValidationGroup {
	}
	// Default Validation Groups - End

}

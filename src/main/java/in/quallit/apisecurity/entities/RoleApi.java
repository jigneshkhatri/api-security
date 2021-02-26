/**
 * 
 */
package in.quallit.apisecurity.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import in.quallit.apisecurity.entities.common.AbstractEntity;
import in.quallit.apisecurity.enums.StatusEnum;

/**
 * @author JIGS
 *
 */
@Entity
@Table(name = "`role_api`")
public class RoleApi extends AbstractEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3804924916002490830L;

	private Long roleId;
	private Long apiId;

	private StatusEnum status;

	@Column(name = "created_on", insertable = false, updatable = false)
	private Date createdOn;

	@Column(name = "created_on", insertable = false, updatable = false)
	private Date updatedOn;

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
	 * @return the apiId
	 */
	public Long getApiId() {
		return apiId;
	}

	/**
	 * @param apiId the apiId to set
	 */
	public void setApiId(Long apiId) {
		this.apiId = apiId;
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

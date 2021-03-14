/**
 * 
 */
package com.quallit.springbootstarter.entities.common;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * @author JIGS
 *
 */
@MappedSuperclass
public abstract class AbstractEntity implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -777337504666843385L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

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

}

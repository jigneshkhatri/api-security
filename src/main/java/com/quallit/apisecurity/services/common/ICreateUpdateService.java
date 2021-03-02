/**
 * 
 */
package com.quallit.apisecurity.services.common;

import java.util.List;

import com.quallit.apisecurity.entities.common.AbstractEntity;

/**
 * @author JIGS
 *
 */
public interface ICreateUpdateService<E extends AbstractEntity> extends IAbstractService<E> {

	default E saveOrUpdate(E obj) {
		return getRepository().save(obj);
	}

	default List<E> saveOrUpdateAll(List<E> objs) {
		return getRepository().saveAll(objs);
	}
}

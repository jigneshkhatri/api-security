/**
 * 
 */
package com.quallit.springbootstarter.services.common;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.quallit.springbootstarter.entities.common.AbstractEntity;

/**
 * @author JIGS
 *
 */
public interface IReadService<E extends AbstractEntity> extends IAbstractService<E> {

	default List<E> findAll(List<String> mappingObjects) {
		List<E> data = getRepository().findAll();
		initializeLazyObjects(mappingObjects, data);
		return data;
	}

	default List<E> findAll(int limit, int pageNo, List<String> mappingObjects) {
		Pageable pageable = PageRequest.of(pageNo, limit);
		List<E> data = getRepository().findAll(pageable).getContent();
		initializeLazyObjects(mappingObjects, data);
		return data;
	}

	default List<E> findAll(Pageable pageable, List<String> mappingObjects) {
		List<E> data = getRepository().findAll(pageable).getContent();
		initializeLazyObjects(mappingObjects, data);
		return data;
	}

	default E findById(long id, List<String> mappingObjects) {
		E data = getRepository().findById(id).orElse(null);
		initializeLazyObjects(mappingObjects, data);
		return data;
	}

	default boolean existsById(long id) {
		return getRepository().existsById(id);
	}
}

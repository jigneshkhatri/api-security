/**
 * 
 */
package in.quallit.apisecurity.services.common;

import in.quallit.apisecurity.entities.common.AbstractEntity;

/**
 * @author JIGS
 *
 */
public interface IDeleteService<E extends AbstractEntity> extends IAbstractService<E> {

	default void deleteById(long id) {
		getRepository().deleteById(id);
	}

	default void delete(E obj) {
		getRepository().delete(obj);
	}
}

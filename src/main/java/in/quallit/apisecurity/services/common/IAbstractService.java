/**
 * 
 */
package in.quallit.apisecurity.services.common;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.springframework.data.jpa.repository.JpaRepository;

import in.quallit.apisecurity.entities.common.AbstractEntity;
import in.quallit.apisecurity.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
public interface IAbstractService<E extends AbstractEntity> {

	public JpaRepository<E, Long> getRepository();

	@SuppressWarnings("unchecked")
	default void initializeLazyObjects(List<String> mappingObjects, Object value) {
		if (ObjectUtil.isNotEmpty(mappingObjects) && ObjectUtil.isNotEmpty(value)) {
			if (value instanceof Iterable) {
				for (E val : (Iterable<E>) value) {
					initialize(mappingObjects, val);
				}
			} else if (value instanceof AbstractEntity) {
				initialize(mappingObjects, (E) value);
			}
		}
	}

	private void initialize(List<String> mappingObjects, E value) {
		for (String mapping : mappingObjects) {
			final String methodName = "get" + mapping.substring(0, 1).toUpperCase() + mapping.substring(1);
			try {
				Hibernate.initialize(value.getClass().getMethod(methodName).invoke(value));
			} catch (HibernateException | NoSuchMethodException | InvocationTargetException | IllegalAccessException
					| SecurityException e) {
				e.printStackTrace();
			}
		}
	}
}

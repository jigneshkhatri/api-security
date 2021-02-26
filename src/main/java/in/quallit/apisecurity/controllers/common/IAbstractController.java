/**
 * 
 */
package in.quallit.apisecurity.controllers.common;

import in.quallit.apisecurity.dtos.common.AbstractDTO;
import in.quallit.apisecurity.entities.common.AbstractEntity;
import in.quallit.apisecurity.enums.StatusEnum;
import in.quallit.apisecurity.utilities.ObjectUtil;

/**
 * @author JIGS
 *
 */
public interface IAbstractController<E extends AbstractEntity, D extends AbstractDTO> {

	public static final String SECURE_PATH = "s/";

	default void createObjectForSaveOrUpdate(D obj) {
		if (ObjectUtil.isEmpty(obj.getId())) {
			// only at the time of new create

			if (ObjectUtil.isEmpty(obj.getStatus())) {
				obj.setStatus(StatusEnum.ACTIVE);
			}
		}

//		Long currentUserId = ObjectUtil.getCurrentUserId(getUserServc);
//		if (ObjectUtil.isEmpty(obj.getId())) {
//			// only at the time of new create
//			obj.setCreatedBy(currentUserId);
//		}
//		obj.setUpdatedBy(currentUserId);
	}

	/**
	 * Response clean up.
	 *
	 * @param entity the entity
	 */
	public void responseCleanUp(E entity);

}

/**
 * 
 */
package in.quallit.apisecurity.enums;

/**
 * @author JIGS
 *
 */
public enum StatusEnum {

	INACTIVE(0), ACTIVE(1);

	private int value;

	StatusEnum(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}
}

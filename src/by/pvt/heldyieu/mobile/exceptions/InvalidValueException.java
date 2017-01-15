/**
 * 
 */
package by.pvt.heldyieu.mobile.exceptions;


/**
 * @author i.heldyieu
 */
public class InvalidValueException extends Exception {
	private static final long serialVersionUID = 4081837278749194746L;
	
	public InvalidValueException() {
		super();
	}

	public InvalidValueException(final String message, Number value) {
		super(message+"Некорректный параметр : " + value);
	}
}

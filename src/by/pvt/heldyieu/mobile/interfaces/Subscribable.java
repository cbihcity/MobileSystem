/**
 * 
 */
package by.pvt.heldyieu.mobile.interfaces;

/**
 * @author i.heldyieu
 *	this interface provide subscribe action by clients for different tarrifs
 */
public interface Subscribable {
	void subscribe(String passport, StringBuilder clientInformation);
}

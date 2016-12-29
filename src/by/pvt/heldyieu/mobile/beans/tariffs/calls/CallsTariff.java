/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.calls;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author HeroDishonest
 *
 */
public abstract class CallsTariff extends MobileTariff {
	private double callsPrice;		//стоимость звонков
	
	public CallsTariff(String tariffname, double abonementPrice, double callsPrice) throws InvalidValueException {
		super(tariffname, abonementPrice);
		this.callsPrice = callsPrice;
	}

	public CallsTariff() {
		
	}

	public double getcallsPrice() {
		return callsPrice;
	}

		
}

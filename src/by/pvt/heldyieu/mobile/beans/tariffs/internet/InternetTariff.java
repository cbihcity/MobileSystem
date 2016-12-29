/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.internet;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author HeroDishonest
 *
 */
public abstract class InternetTariff extends MobileTariff {
	private double internetPrice;											//стоимость интернета
	
	public InternetTariff(String tariffname, double abonementPrice, double internetPrice) throws InvalidValueException {
		super(tariffname, abonementPrice);
		this.internetPrice = internetPrice;
	}

	public InternetTariff() {
		
	}

	public double getInternetPrice() {
		return internetPrice;
	}

		
	
}

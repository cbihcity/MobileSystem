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
	private double internetPrice; // стоимость интернета

	public InternetTariff(String tariffname, double abonementPrice,
			double internetPrice) throws InvalidValueException {
		super(tariffname, abonementPrice);
		if (internetPrice < 0) {
			throw new InvalidValueException(
					"Стоимость интернета не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.");
		} else {
			this.internetPrice = internetPrice;
		}
	}

	public InternetTariff() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "; Стоимость интернета - "
				+ getInternetPrice();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(internetPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!super.equals(obj)) {
			return false;
		}
		if (!(obj instanceof InternetTariff)) {
			return false;
		}
		InternetTariff other = (InternetTariff) obj;
		if (Double.doubleToLongBits(internetPrice) != Double
				.doubleToLongBits(other.internetPrice)) {
			return false;
		}
		return true;
	}

	public double getInternetPrice() {
		return internetPrice;
	}

}

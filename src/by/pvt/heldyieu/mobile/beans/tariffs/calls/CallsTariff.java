/**
 * 
 */
package by.pvt.heldyieu.mobile.beans.tariffs.calls;

import by.pvt.heldyieu.mobile.beans.tariffs.MobileTariff;
import by.pvt.heldyieu.mobile.exceptions.InvalidValueException;

/**
 * @author i.heldyieu 
 *
 */
public abstract class CallsTariff extends MobileTariff {
	private static final long serialVersionUID = 5388869734650337223L;
	private double callsPrice; // cost of calls
	
	/**
	 * Creates new entity of the class <b>{@code CallsTariff}</b> and
	 * initialize it
	 * @param tariffname - @see MobileTariff
	 * @param abonementPrice - @see MobileTariff
	 * @param callsPrice - cost of calls
	 * @throws InvalidValueException - @see MobileTariff
	 */
	public CallsTariff(String tariffname, double abonementPrice,
			double callsPrice) throws InvalidValueException {
		super(tariffname, abonementPrice);
		if (callsPrice < 0) {
			throw new InvalidValueException(
					"Стоимость звонков не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.",
					callsPrice);
		} else {
			this.callsPrice = callsPrice;
		}
	}

	public CallsTariff() {
		super();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "; Стоимость звонков - " + getcallsPrice();
	}

	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(callsPrice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/*
	 * (non-Javadoc)
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
		if (!(obj instanceof CallsTariff)) {
			return false;
		}
		CallsTariff other = (CallsTariff) obj;
		if (Double.doubleToLongBits(callsPrice) != Double
				.doubleToLongBits(other.callsPrice)) {
			return false;
		}
		return true;
	}

	public double getcallsPrice() {
		return callsPrice;
	}
	
	public abstract int getFreeMinutes();
}

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
public class LimitedInternetTariff extends InternetTariff {
	private double freeGb; // size of free internet traffic
	
	/**
	 * 
	 */
	public LimitedInternetTariff() {
		super();
	}

	/**
	 * @param tariffname
	 * @param abonementPrice
	 * @param internetPrice
	 * @param freeGb
	 * @throws InvalidValueException
	 */
	public LimitedInternetTariff(String tariffname, double abonementPrice,
			double internetPrice, double freeGb) throws InvalidValueException {
		super(tariffname, abonementPrice, internetPrice);
		if (freeGb < 0) {
			throw new InvalidValueException(
					"Количество свободного интернет траффика не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.");
		} else {
			this.freeGb = freeGb;
		}
	}
	
	

	/**
	 * @return the freeGb
	 */
	public double getFreeGb() {
		return freeGb;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		long temp;
		temp = Double.doubleToLongBits(freeGb);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		LimitedInternetTariff other = (LimitedInternetTariff) obj;
		if (Double.doubleToLongBits(freeGb) != Double
				.doubleToLongBits(other.freeGb))
			return false;
		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return super.toString() + "; Количество свободного интернет траффика - "+getFreeGb()+"; Суммарная стоимость - " + getCost();
	}
	
	public void printClients() {
		super.printClients();
	}

	public void subscribe(String passport, String surname, String firstname) {
		clients.put(passport, surname + " " + firstname);
	}

	public void unsubscribe(String passport) {
		clients.remove(passport);
	}

	public int getClientsNumbers() {
		return clients.size();
	}

	public double getCost() {
		return getAbonementPrice() + getInternetPrice();
	}

	@Override
	public int compareTo(MobileTariff o) {
		return Double.compare(this.getAbonementPrice(), o.getAbonementPrice());
	}

}
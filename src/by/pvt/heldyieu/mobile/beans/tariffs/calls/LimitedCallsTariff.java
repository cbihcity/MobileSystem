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
public class LimitedCallsTariff extends CallsTariff {
	private int freeMinutes; //free minutes for this tariff
	
	
	/**
	 * 
	 */
	public LimitedCallsTariff() {
		super();
	}

	/**
	 * @param tariffname
	 * @param abonementPrice
	 * @param callsPrice
	 * @param freeMinutes
	 * @throws InvalidValueException
	 */
	public LimitedCallsTariff(String tariffname, double abonementPrice,
			double callsPrice, int freeMinutes) throws InvalidValueException {
		super(tariffname, abonementPrice, callsPrice);
		if (freeMinutes < 0) {
			throw new InvalidValueException(
					"Количество свободных минут не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.", callsPrice);
		} else {
			this.freeMinutes = freeMinutes;
			MobileTariff.addTariffInstance(this);
		}
	}
	
	/**
	 * @return the freeMinutes
	 */
	public int getFreeMinutes() {
		return freeMinutes;
	}

	

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + freeMinutes;
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
		LimitedCallsTariff other = (LimitedCallsTariff) obj;
		if (freeMinutes != other.freeMinutes)
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
		return super.toString()+"; Количество свободных минут - "+getFreeMinutes()+"; Суммарная стоимость - " + getCost();
	}
	
	/**
	 * @return the clients
	 */
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
		return getAbonementPrice() + getcallsPrice();
	}

	@Override
	public int compareTo(MobileTariff o) {
		return Double.compare(this.getAbonementPrice(), o.getAbonementPrice());
	}
}

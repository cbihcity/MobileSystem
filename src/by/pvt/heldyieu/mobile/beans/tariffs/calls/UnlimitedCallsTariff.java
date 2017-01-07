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
public class UnlimitedCallsTariff extends CallsTariff {
	private int freeMinutes; //free minutes for this tariff
	
	
	/**
	 * 
	 */
	public UnlimitedCallsTariff() {
		super();
	}

	/**
	 * @param tariffname
	 * @param abonementPrice
	 * @param callsPrice
	 * @param freeMinutes
	 * @throws InvalidValueException
	 */
	public UnlimitedCallsTariff(String tariffname, double abonementPrice,
			double callsPrice, int freeMinutes) throws InvalidValueException {
		super(tariffname, abonementPrice, callsPrice);
		if (freeMinutes < 0) {
			throw new InvalidValueException(
					"Количество свободных минут не может быть < 0. Объект класса "
							+ this.getClass().getName() + " не создан.");
		} else {
			this.freeMinutes = freeMinutes;
		}
	}

	/**
	 * @return the freeMinutes
	 */
	public int getFreeMinutes() {
		return freeMinutes;
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
		result = prime * result + ((clients == null) ? 0 : clients.hashCode());
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
		if (!(obj instanceof UnlimitedCallsTariff)) {
			return false;
		}
		UnlimitedCallsTariff other = (UnlimitedCallsTariff) obj;
		if (clients == null) {
			if (other.clients != null) {
				return false;
			}
		} else if (!clients.equals(other.clients)) {
			return false;
		}
		return true;
	}

	/**
	 * @return the clients
	 */
	public void printClients() {
		super.printClients();
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
